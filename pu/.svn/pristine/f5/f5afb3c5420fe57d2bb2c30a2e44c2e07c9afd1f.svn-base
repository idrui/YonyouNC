/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 下午03:42:08
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单的弃审操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-13 下午03:42:08
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
    // 可用量更新前处理
    this.atpBeforeUpdate(clientBills);

    // 调用平台脚本进行弃审
    if (null != script) {
      try {
        script.procUnApproveFlow(script.getPfParameterVO());
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }

    // 把VO持久化到数据库中
    BillUpdate<OrderVO> update = new BillUpdate<OrderVO>();
    OrderVO[] returnVos = update.update(clientBills, originBills);

    // 可用量更新
    this.atpUpdate(returnVos);

    processer.after(returnVos, originBills);

    return returnVos;
  }

  private void addAfterRule(CompareAroundProcesser<OrderVO> processer,
      OrderVO[] originBills) {
    // 写业务日志要放到OrderUnApproveFilterRule的前面，否则vo会被过滤掉，就没法记录日志了
    processer.addAfterRule(new WriteOperateLogRule<OrderVO>(
        PuBusiLogPathCode.orderApprovePath.getCode(),
        PuBusiLogActionCode.unapprove.getCode()));

    // processer.addAfterRule(new ATPUpdateRule()); //可用量更新
    processer.addAfterRule(new OrderUnApproveFilterRule(originBills));
    // 采购计划
    processer.addAfterRule(new UnApproveBudgetCtrlRule());
    processer.addAfterRule(new ClearPrePayMnyRule());// 清除预付款
    // processer.addAfterRule(new MPPCtrlChkRule());// 采购计划检查
    processer.addAfterRule(new UnAuditSupplyRule());// 预留
    processer.addAfterRule(new DeleteStatusOnWayRule()); // 删除在途状态
    processer.addAfterRule(new DeletePayPlanRule());
    // 弃审后事件处理
    processer.addAfterRule(new UnApproveAfterEventRule());

    // 更新价格表
    processer.addAfterRule(new OrderPriceMaintainRule());
  }

  private void addBeforeRule(CompareAroundProcesser<OrderVO> processer) {
    processer.addBeforeRule(new UnApproveVOValidateRule()); // 审核VO状态检查
    processer.addBeforeRule(new ReservedNumChkRule()); // 预留相关检查规则
    processer.addBeforeRule(new FollowupBillChkRule()); // 生成下游单据检查
    processer.addBeforeRule(new ApproverPermissionRule<OrderVO>(
        POBillType.Order.getCode()));
    // processer.addBeforeRule(new ATPBeforeUpdateRule()); //可用量更新前处理
    // 弃审前事件处理
    processer.addBeforeRule(new UnApproveBeforeEventRule());
  }

  /**
   * 方法功能描述：可用量更新前处理。只有审批不通过才执行。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-7-2 下午01:24:05
   */
  private void atpBeforeUpdate(OrderVO[] vos) {
    ATPServices.modifyATPBefore(POBillType.Order.getCode(), vos);
  }

  /**
   * 方法功能描述：可用量更新
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-2 下午01:39:23
   */
  private void atpUpdate(OrderVO[] vos) {
    ATPServices.modifyATPAfter(POBillType.Order.getCode(), vos);
  }

}
