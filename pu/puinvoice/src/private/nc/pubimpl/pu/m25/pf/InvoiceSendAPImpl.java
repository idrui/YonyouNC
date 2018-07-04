/**
 * 
 */
package nc.pubimpl.pu.m25.pf;

import nc.bs.pu.m25.ap.CancelSendApBP;
import nc.bs.pu.m25.ap.SendApBP;
import nc.bs.pu.m25.plugin.InvoicePluginPoint;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubimpl.pu.m25.pf.rule.FilterSendAPInvoiceRule;
import nc.pubitf.pu.m25.pf.IInvoiceSendAP;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.pub.rule.pf.UpdatePflowVORule;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ӧ��ʵ�����</li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-2 ����04:37:35
 */
public class InvoiceSendAPImpl implements IInvoiceSendAP {

  /**
   * ȡ����Ӧ��
   */
  @Override
  public InvoiceVO[] cancelSendAP(InvoiceVO[] invoiceVOs, InvoiceUIToBSEnv env)
      throws BusinessException {
    InvoiceVO[] vos = invoiceVOs;
    if (!SysInitGroupQuery.isAPEnabled()) {
      return invoiceVOs;
    }
    try {
      BillTransferTool<InvoiceVO> tool = new BillTransferTool<InvoiceVO>(vos);
      InvoiceVO[] orgVos = tool.getOriginBills();
      vos = tool.getClientFullInfoBill();
      CancelSendApBP bp = new CancelSendApBP();
      bp.setIsmanual(InvoiceVOUtil.isManualCancelSendAP(env));
      bp.cancelSendAp(vos, orgVos);
      vos = tool.getBillForToClient(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return vos;
  }

  /**
   * ��Ӧ��
   */
  @Override
  public InvoiceVO[] sendAP(InvoiceVO[] invoiceVOs, InvoiceUIToBSEnv env,
      PfParameterVO pfVo) throws BusinessException {
    if (!SysInitGroupQuery.isAPEnabled()) {
      return invoiceVOs;
    }
    if (ArrayUtils.isEmpty(invoiceVOs)) {
      return null;// ��������ʽ���������������е������Ѿ�Ϊ��
    }
    InvoiceVO[] vos = invoiceVOs;
    try {
      InvoiceVO[] orgVos = null;
      BillTransferTool<InvoiceVO> tool = null;
      // �����Ʊ������Ӧ���������vosӦ���Ѿ���ȫ���ˣ���Ϊǰ̨���������������Զ���ȫ
      // ����ֱ����vos�Ŀ�¡��ΪorgVos
      // ����ö��µĵ�������ע���࣬�����ﶼ���ô������Ϳ����Ƿ��ֹ��ıŷ���������
      if (InvoiceVOUtil.isManualSendAP(env)) {
        tool = new BillTransferTool<InvoiceVO>(vos);
        orgVos = tool.getOriginBills();
        vos = tool.getClientFullInfoBill();
      }
      else {
        orgVos = InvoiceVOUtil.getCloneVOs(vos);
      }
      CompareAroundProcesser<InvoiceVO> processer =
          new CompareAroundProcesser<InvoiceVO>(InvoicePluginPoint.SEND_AP_IMPL);
      this.addSendAPRule(processer, pfVo, env);
      vos = processer.before(vos, orgVos);
      if (ArrayUtils.isEmpty(vos)) {
        return vos;
      }
      InvoiceVO[] updateVos = new SendApBP(env).sendAP(vos, orgVos);
      processer.after(updateVos, orgVos);
      // ����һ��Ҫʹ��vos���в�ȫ����Ϊbp���ص�updateVos�е�VO�п����Ѿ����޸Ĺ������У����㣩
      if (InvoiceVOUtil.isManualSendAP(env) && tool != null) {
        return tool.getBillForToClient(vos);
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return vos;
  }

  private void addSendAPRule(CompareAroundProcesser<InvoiceVO> processer,
      PfParameterVO pfVo, InvoiceUIToBSEnv env) {
    // ���˵����ܴ�Ӧ����VO
    processer.addBeforeFinalRule(new FilterSendAPInvoiceRule(InvoiceVOUtil
        .isManualSendAP(env)));
    // ��������ƽ̨��VO--ǰ������봦�����ǰ������Ѿ�����Ϊ�գ�������Ͳ�ִ����
    // ƽ̨�ű��л���ԭ����VO���򻹻��Ƴ�Ӧ����
    processer.addBeforeFinalRule(new UpdatePflowVORule<InvoiceVO>(pfVo));
    // ���˵����ܴ�Ӧ����VO--�������Ҫ�ٴ���һ��
    processer.addAfterFinalRule(new FilterSendAPInvoiceRule(true));
    // �������ҲҪ���Ӵ˹�����Ϊ��Ӧ������֮�󣬷�Ʊ���ܲ��ô�Ӧ���ˣ����ҲҪ��������ƽ̨�ű��е�VO
    processer.addAfterFinalRule(new UpdatePflowVORule<InvoiceVO>(pfVo));
  }

}
