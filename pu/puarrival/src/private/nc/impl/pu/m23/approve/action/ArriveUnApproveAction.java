package nc.impl.pu.m23.approve.action;

import nc.bs.pu.m23.maintain.rule.update.UpdateChkRule;
import nc.bs.pu.m23.plugin.ArriveActionPlugInPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m23.approve.rule.ChkCanUnApproveRule;
import nc.impl.pu.m23.approve.rule.UnApproveAfterEventRule;
import nc.impl.pu.m23.approve.rule.UnApproveBeforeEventRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.ArriveATPUpdateRule;
import nc.vo.pu.pub.enumeration.PuBusiLogActionCode;
import nc.vo.pu.pub.enumeration.PuBusiLogPathCode;
import nc.vo.pu.pub.rule.ApproverPermissionRule;
import nc.vo.pu.pub.rule.busilog.WriteOperateLogRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������������Ӧ��Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 ����11:14:26
 */
public class ArriveUnApproveAction {

  /**
   * ���������������������������Ӧ��Action
   * <p>
   * <b>����˵��</b>
   * 
   * @param voArray
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 ����11:14:57
   */
  public ArriveVO[] unApproveArrive(ArriveVO[] voArray, AbstractCompiler2 script) {
    PfParameterUtil<ArriveVO> util =
        new PfParameterUtil<ArriveVO>(script == null ? null
            : script.getPfParameterVO(), voArray);
    ArriveVO[] originBills = util.getOrginBills();

    // ��ӹ���
    AroundProcesser<ArriveVO> processer =
        new AroundProcesser<ArriveVO>(
            ArriveActionPlugInPoint.ArriveUnApproveAction);
    this.addBeforeRule(processer);
    this.addAfterRule(processer);
    this.addATPRule(processer);
    processer.before(voArray);
    // ����ƽ̨�ű�����ȡ������
    if (null != script) {
      try {
        script.procUnApproveFlow(script.getPfParameterVO());
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }
    if (script == null || script.getPfParameterVO().m_preValueVos == null) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
              "04004040-0116")/* @res "������ȡ������ʱ�����ֵ�������Ϊ�գ�" */;
      ExceptionUtils.wrappBusinessException(msg);
      return null;
    }
    BillUpdate<ArriveVO> update = new BillUpdate<ArriveVO>();
    ArriveVO[] returnVos = update.update(voArray, originBills);

    processer.after(returnVos);

    return returnVos;
  }

  private void addAfterRule(AroundProcesser<ArriveVO> processer) {

    // дҵ����־
    processer.addAfterRule(new WriteOperateLogRule<ArriveVO>(
        PuBusiLogPathCode.puarrivalApprovePath.getCode(),
        PuBusiLogActionCode.unapprove.getCode()));
    // ������¼�����
    processer.addAfterRule(new UnApproveAfterEventRule());
  }

  private void addATPRule(AroundProcesser<ArriveVO> processer) {
    // ���������ǰ����
    processer.addBeforeRule(new ArriveATPUpdateRule(true));
    // ��������������
    processer.addAfterRule(new ArriveATPUpdateRule(false));
  }

  private void addBeforeRule(AroundProcesser<ArriveVO> processer) {
    // ����Ƿ������
    processer.addBeforeRule(new ChkCanUnApproveRule());
    processer.addBeforeRule(new ApproverPermissionRule<ArriveVO>(
        POBillType.Arrive.getCode()));
    // ����ǰ�¼�����
    processer.addBeforeRule(new UnApproveBeforeEventRule());
  }
}
