package nc.pubimpl.pu.m25.dm.m4812;

import nc.bs.pu.m25.plugin.InvoicePluginPoint;
import nc.bs.pub.pf.PfUtilTools;
import nc.impl.pu.m25.action.InvoiceDeleteAction;
import nc.impl.pu.m25.action.InvoiceInsertAction;
import nc.impl.pu.m25.action.rule.approve.UpdateApproveInfoRule;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.pubitf.pu.m25.dm.m4812.IInvoiceApproveFor4812;
import nc.vo.dm.m4812.entity.ApInvoiceVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.rule.approve.SupplierInvoiceFrozenChkRule;
import nc.vo.pu.m25.rule.approve.UnApproveStatusChkRule;
import nc.vo.pu.m25.rule.unapprove.CancelSendAPDriveChkRule;
import nc.vo.pu.m25.rule.unapprove.SettledCheckRule;
import nc.vo.pu.pub.enumeration.PuBusiLogActionCode;
import nc.vo.pu.pub.enumeration.PuBusiLogPathCode;
import nc.vo.pu.pub.rule.ApproverPermissionRule;
import nc.vo.pu.pub.rule.busilog.WriteOperateLogRule;
import nc.vo.pu.pub.rule.pf.ApprovedVOFilterRule;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.DMBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * �ɹ���Ʊ���˷�Ӧ����Ʊ�ӿ�ʵ����
 * 
 * @since 6.31
 * @author zhangshqb
 */
public class InvoiceApproveFor4812Impl implements IInvoiceApproveFor4812 {

  @Override
  public void approve(ApInvoiceVO[] vos) throws BusinessException {
    try {
      AggregatedValueObject[] destVOs =
          PfUtilTools.runChangeDataAry(DMBillType.ApInvoice.getCode(),
              POBillType.Invoice.getCode(), vos);
      InvoiceInsertAction insertAction = new InvoiceInsertAction();
      insertAction.insert((InvoiceVO[]) destVOs, null);
      CompareAroundProcesser<InvoiceVO> prcr =
          new CompareAroundProcesser<InvoiceVO>(InvoicePluginPoint.APPROVE);
      // // ��Ʊ�ܷ�������״̬(���ᡢ�������ȼ�顢���ⷢƱ)
      // prcr.addBeforeFinalRule(new ApproveStatusChkRule());
      // ��Ӧ�̿�Ʊ������
      prcr.addBeforeFinalRule(new SupplierInvoiceFrozenChkRule());
      prcr.before((InvoiceVO[]) destVOs, (InvoiceVO[]) destVOs);
      // дҵ����־
      prcr.addAfterFinalRule(new WriteOperateLogRule<InvoiceVO>(
          PuBusiLogPathCode.invoiceApprovePath.getCode(),
          PuBusiLogActionCode.approve.getCode()));
      // ������������Ϣ
      prcr.addAfterFinalRule(new UpdateApproveInfoRule());

      // �����ĺ����ֻ���˵������Ѿ���������VO����������ע���жϿ�
      prcr.addAfterFinalRule(new ApprovedVOFilterRule<InvoiceVO>());
      prcr.after((InvoiceVO[]) destVOs, (InvoiceVO[]) destVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void unapprove(String[] codes) throws BusinessException {
    try {
      SqlBuilder sql = new SqlBuilder();
      sql.append("select distinct pk_invoice from po_invoice_b where dr=0 and ");
      sql.append(InvoiceItemVO.CSOURCEID, codes);
      IRowSet rowset = new DataAccessUtils().query(sql.toString());
      String[] ids = rowset.toOneDimensionStringArray();
      InvoiceVO[] destVOs =
          new BillQuery<InvoiceVO>(InvoiceVO.class).query(ids);
      CompareAroundProcesser<InvoiceVO> prcr =
          new CompareAroundProcesser<InvoiceVO>(InvoicePluginPoint.UNAPPROVE);
      // ��Ʊ��ȡ������״̬���
      prcr.addBeforeFinalRule(new UnApproveStatusChkRule());
      // �Ƿ��Ѿ����������
      prcr.addBeforeFinalRule(new SettledCheckRule());
      prcr.addBeforeRule(new ApproverPermissionRule<InvoiceVO>(
          POBillType.Invoice.getCode()));
      // �ж��Ƿ�������ȡ����Ӧ��
      prcr.addBeforeFinalRule(new CancelSendAPDriveChkRule());
      prcr.before(destVOs, destVOs);
      // дҵ����־
      prcr.addAfterFinalRule(new WriteOperateLogRule<InvoiceVO>(
          PuBusiLogPathCode.invoiceApprovePath.getCode(),
          PuBusiLogActionCode.unapprove.getCode()));
      prcr.after(destVOs, destVOs);
      for (InvoiceVO vo : destVOs) {
        vo.getParentVO().setFbillstatus(Integer.valueOf(0));
      }
      InvoiceDeleteAction deleteAction = new InvoiceDeleteAction();
      deleteAction.delete(destVOs, null);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
