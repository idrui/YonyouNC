/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 ����03:42:08
 */
package nc.impl.pu.m21.action;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m21.action.rule.approve.ClearPrePayMnyRule;
import nc.impl.pu.m21.action.rule.approve.DeletePayPlanRule;
import nc.impl.pu.m21.action.rule.approve.DeleteStatusOnWayRule;
import nc.impl.pu.m21.action.rule.approve.FollowupBillChkRule;
import nc.impl.pu.m21.action.rule.approve.OrderPriceMaintainRule;
import nc.impl.pu.m21.action.rule.approve.OrderUnApproveFilterRule;
import nc.impl.pu.m21.action.rule.approve.ReservedNumChkRule;
import nc.impl.pu.m21.action.rule.approve.UnApproveAfterEventRule;
import nc.impl.pu.m21.action.rule.approve.UnApproveBeforeEventRule;
import nc.impl.pu.m21.action.rule.approve.UnApproveBudgetCtrlRule;
import nc.impl.pu.m21.action.rule.approve.UnApproveVOValidateRule;
import nc.impl.pu.m21.action.rule.approve.UnAuditSupplyRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.pu.reference.ic.ATPServices;
import nc.vo.pu.m21.entity.OrderVO;
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
 * <li>�ɹ��������������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-13 ����03:42:08
 */
public class OrderUnArppoveAction {

  public OrderVO[] unapprove(OrderVO[] vos, AbstractCompiler2 script) {
    PfParameterUtil<OrderVO> util =
        new PfParameterUtil<OrderVO>(script == null ? null
            : script.getPfParameterVO(), vos);
    OrderVO[] originBills = util.getClientOrignBills();
    OrderVO[] clientBills = util.getClientFullInfoBill();

    CompareAroundProcesser<OrderVO> processer =
        new CompareAroundProcesser<OrderVO>(OrderPluginPoint.UNAPPROVE);
    this.addBeforeRule(processer);
    this.addAfterRule(processer, originBills);
    processer.before(clientBills, originBills);
    // ����������ǰ����
    this.atpBeforeUpdate(clientBills);

    // ����ƽ̨�ű���������
    if (null != script) {
      try {
        script.procUnApproveFlow(script.getPfParameterVO());
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }

    // ��VO�־û������ݿ���
    BillUpdate<OrderVO> update = new BillUpdate<OrderVO>();
    OrderVO[] returnVos = update.update(clientBills, originBills);

    // ����������
    this.atpUpdate(returnVos);

    processer.after(returnVos, originBills);

    return returnVos;
  }

  private void addAfterRule(CompareAroundProcesser<OrderVO> processer,
      OrderVO[] originBills) {
    // дҵ����־Ҫ�ŵ�OrderUnApproveFilterRule��ǰ�棬����vo�ᱻ���˵�����û����¼��־��
    processer.addAfterRule(new WriteOperateLogRule<OrderVO>(
        PuBusiLogPathCode.orderApprovePath.getCode(),
        PuBusiLogActionCode.unapprove.getCode()));

    // processer.addAfterRule(new ATPUpdateRule()); //����������
    processer.addAfterRule(new OrderUnApproveFilterRule(originBills));
    // �ɹ��ƻ�
    processer.addAfterRule(new UnApproveBudgetCtrlRule());
    processer.addAfterRule(new ClearPrePayMnyRule());// ���Ԥ����
    // processer.addAfterRule(new MPPCtrlChkRule());// �ɹ��ƻ����
    processer.addAfterRule(new UnAuditSupplyRule());// Ԥ��
    processer.addAfterRule(new DeleteStatusOnWayRule()); // ɾ����;״̬
    processer.addAfterRule(new DeletePayPlanRule());
    // ������¼�����
    processer.addAfterRule(new UnApproveAfterEventRule());

    // ���¼۸��
    processer.addAfterRule(new OrderPriceMaintainRule());
  }

  private void addBeforeRule(CompareAroundProcesser<OrderVO> processer) {
    processer.addBeforeRule(new UnApproveVOValidateRule()); // ���VO״̬���
    processer.addBeforeRule(new ReservedNumChkRule()); // Ԥ����ؼ�����
    processer.addBeforeRule(new FollowupBillChkRule()); // �������ε��ݼ��
    processer.addBeforeRule(new ApproverPermissionRule<OrderVO>(
        POBillType.Order.getCode()));
    // processer.addBeforeRule(new ATPBeforeUpdateRule()); //����������ǰ����
    // ����ǰ�¼�����
    processer.addBeforeRule(new UnApproveBeforeEventRule());
  }

  /**
   * ������������������������ǰ����ֻ��������ͨ����ִ�С�
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-7-2 ����01:24:05
   */
  private void atpBeforeUpdate(OrderVO[] vos) {
    ATPServices.modifyATPBefore(POBillType.Order.getCode(), vos);
  }

  /**
   * ������������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-2 ����01:39:23
   */
  private void atpUpdate(OrderVO[] vos) {
    ATPServices.modifyATPAfter(POBillType.Order.getCode(), vos);
  }

}
