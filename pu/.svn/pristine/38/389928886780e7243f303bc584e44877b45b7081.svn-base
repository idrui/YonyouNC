package nc.impl.pu.m21.action;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.rule.ApprovingEditCheckRule;
import nc.vo.pu.pub.rule.ApprovingEditSendMsgRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.util.TimeUtils;

import nc.bs.pu.m21.maintain.OrderSaveBP;
import nc.bs.pu.m21.plugin.OrderPluginPoint;

import nc.impl.pu.m21.action.rule.approve.FilterOrderByStatusRule;
import nc.impl.pu.m21.action.rule.approve.OrderPriceMaintainRule;
import nc.impl.pu.m21.action.rule.revise.BrefwhenreturnCheckRule;
import nc.impl.pu.m21.action.rule.revise.ItemChangeCheckRule;
import nc.impl.pu.m21.action.rule.revise.ItemRule;
import nc.impl.pu.m21.action.rule.revise.ModifyStatusOnWayRule;
import nc.impl.pu.m21.action.rule.revise.ModifySupplyRule;
import nc.impl.pu.m21.action.rule.revise.OneContractCheckRule;
import nc.impl.pu.m21.action.rule.revise.ReviseBudgetCtrlRule;
import nc.impl.pu.m21.action.rule.revise.ReviseNumCheckRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单修订
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-16 上午09:44:47
 */
public class OrderReviseAction {

  /**
   * 方法功能描述：采购订单修订操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVos
   * @param ctx
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-16 上午09:50:28
   */
  public OrderVO[] revise(OrderVO[] orderVos, OrderContext ctx) {
    if (ArrayUtils.isEmpty(orderVos)) {
      return null;
    }

    BillTransferTool<OrderVO> tool = new BillTransferTool<OrderVO>(orderVos);
    OrderVO[] voSaved = tool.getClientFullInfoBill();
    OrderVO[] voOrginal = tool.getOriginBills();

    // 执行最新版的采购订单保存
    OrderVO[] voRevised = this.saveNewVO(voSaved, voOrginal, ctx);

    // 设置历史记录
    this.setOrignialVO(voOrginal, voSaved);

    // 执行历史订单保存
    this.saveHistoryVO(voOrginal);

    return tool.getBillForToClient(voRevised);

  }

  /**
   * 方法功能描述：设置旧VO的值，使其变成历史记录。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param sReviseUserId
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-17 下午07:02:21
   */
  public void setOrignialVO(OrderVO[] voOrginal, OrderVO[] voSaved) {
    if (ArrayUtils.isEmpty(voOrginal)) {
      return;
    }

    UFDate correctdate = TimeUtils.getsrvBaseDate();
    for (int i = 0; i < voOrginal.length; ++i) {
      // 头
      OrderHeaderVO voHead = voOrginal[i].getHVO();
      voHead.setStatus(VOStatus.NEW);
      voHead.setPk_order(null);
      voHead.setBislatest(UFBoolean.FALSE);
      voHead.setTs(null);

      // 体
      OrderItemVO[] voItems = voOrginal[i].getBVO();
      for (int j = 0; j < voItems.length; ++j) {
        voItems[j].setStatus(VOStatus.NEW);
        voItems[j].setPk_srcorder_b(voItems[j].getPk_order_b());
        voItems[j].setPk_order_b(null);
        voItems[j].setPk_order(null);
        voItems[j].setDcorrectdate(correctdate);
        voItems[j].setChandler(voSaved[i].getHVO().getCrevisepsn());
        voItems[j].setFisactive((Integer) EnumActive.REVISEHISTORY.value());
        voItems[j].setTs(null);
      }
      /*
       * add by wandl 清空付款协议主键，修订含有付款协议的订单会报违反唯一约束！
       */
      OrderPaymentVO[] paymentItem = voOrginal[i].getPaymentVO();
      for (OrderPaymentVO payment : paymentItem) {
        payment.setPk_payment(null);
      }
    }
  }

  /**
   * 方法功能描述：保存后规则
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-26 下午06:41:30
   */
  private void addAfterRule(CompareAroundProcesser<OrderVO> processer,
      OrderContext ctx) {
    // 修订数量合法性检查，修订后的数量与原数量同正负，且不能小于后续数量
    processer.addAfterRule(new ReviseNumCheckRule(ctx
        .getReviseToleranceConfirm().booleanValue()));
    // 只处理审批后的数据（因审批中修订会有未审批通过的数据过来）
    processer.addAfterRule(new FilterOrderByStatusRule(POEnumBillStatus.APPROVE
        .toInt()));
    processer.addAfterRule(new ModifySupplyRule());
    processer.addAfterRule(new ReviseBudgetCtrlRule());
    // 修改在途状态表中数据
    processer.addAfterRule(new ModifyStatusOnWayRule());
    // 更新价格表
    processer.addAfterRule(new OrderPriceMaintainRule());
  }

  /**
   * 方法功能描述：保存前规则
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-26 下午06:41:24
   */
  private void addBeforeRule(CompareAroundProcesser<OrderVO> processer) {

    // 参数合法性检查
    processer.addBeforeRule(new ItemRule());

    // 表头退货(库)基于原订单补货检查，有后续单据不允许修改退货/库基于原订单补货字段
    processer.addBeforeRule(new BrefwhenreturnCheckRule());

    // 表体不能改变项检查有后续单据，表体批次号、需求部门、项目、收货仓库、收货地址、赠品不能修改
    processer.addBeforeRule(new ItemChangeCheckRule());

    // 合同检查，修订前单据来自同一个合同，则不能将订单改为来自多个合同的订单
    processer.addBeforeRule(new OneContractCheckRule());

    // 支持审批中修改（修订）的单据，检查是否应该由当前审批人修改（修订）
    processer.addBeforeFinalRule(new ApprovingEditCheckRule<OrderVO>(
        POBillType.Order));

    // 支持审批中修改（修订）的单据，后台向所有已经处理过审批任务的人发送消息
    processer.addBeforeFinalRule(new ApprovingEditSendMsgRule<OrderVO>());

  }

  /**
   * 方法功能描述：历史订单保存
   * <p>
   * <b>参数说明</b>
   * 
   * @param voOrginal
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-26 下午06:43:11
   */
  private void saveHistoryVO(OrderVO[] voOrginal) {
    if (!ArrayUtils.isEmpty(voOrginal)) {
      for (OrderVO vo : voOrginal) {
        vo.setChildren(PayPlanVO.class, null);
      }
      new BillInsert<OrderVO>().insert(voOrginal);
    }
  }

  /**
   * 方法功能描述：最新版的采购订单保存
   * <p>
   * <b>参数说明</b>
   * 
   * @param voSaved
   * @param voOrginal
   * @param ctx
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-26 下午06:45:12
   */
  private OrderVO[] saveNewVO(OrderVO[] voSaved, OrderVO[] voOrginal,
      OrderContext ctx) {
    if (ArrayUtils.isEmpty(voSaved)) {
      return null;
    }
    // 特殊处理
    // 前台差异处理时，因为界面没有付款计划，所以将其设置为删除
    // 设置成不变
    this.setPayPlanStatus(voSaved);
    // 规则
    CompareAroundProcesser<OrderVO> processer =
        new CompareAroundProcesser<OrderVO>(OrderPluginPoint.REVISE);
    this.addBeforeRule(processer);
    this.addAfterRule(processer, ctx);

    // 执行最新版的采购订单保存
    processer.before(voSaved, voOrginal);
    OrderVO[] retVOs = new OrderSaveBP(ctx).save(voSaved, voOrginal);

    processer.after(retVOs, voOrginal);

    return retVOs;
  }

  private void setPayPlanStatus(OrderVO[] voSaved) {

    for (OrderVO vo : voSaved) {
      ISuperVO[] vos = vo.getChildren(PayPlanVO.class);
      if (ArrayUtils.isEmpty(vos)) {
        continue;
      }
      PayPlanVO[] payplanVOs = ArrayUtil.convertArrayType(vos);
      for (PayPlanVO payplanVO : payplanVOs) {
        payplanVO.setStatus(VOStatus.UNCHANGED);
      }
    }
  }

}
