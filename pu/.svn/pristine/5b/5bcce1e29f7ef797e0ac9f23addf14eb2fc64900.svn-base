/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 下午03:41:40
 */
package nc.impl.pu.m21.action;

import nc.bs.pu.m21.maintain.rule.SupplierFrozeChkRule;
import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m21.action.rule.approve.ApproveAfterEventRule;
import nc.impl.pu.m21.action.rule.approve.ApproveBeforeEventRule;
import nc.impl.pu.m21.action.rule.approve.ApproveBudgetCtrlRule;
import nc.impl.pu.m21.action.rule.approve.ApproveSupplyRule;
import nc.impl.pu.m21.action.rule.approve.ApproveVOValidateRule;
import nc.impl.pu.m21.action.rule.approve.FillNcaninnumRule;
import nc.impl.pu.m21.action.rule.approve.FilterOrderByStatusRule;
import nc.impl.pu.m21.action.rule.approve.InsertPayPlanRule;
import nc.impl.pu.m21.action.rule.approve.InsertStatusOnWayRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.pu.reference.ic.ATPServices;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.enumeration.PuBusiLogActionCode;
import nc.vo.pu.pub.enumeration.PuBusiLogPathCode;
import nc.vo.pu.pub.rule.busilog.WriteOperateLogRule;
import nc.vo.pu.pub.rule.pf.UpdatePflowVORule;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>执行采购订单的审核操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-13 下午03:41:40
 */
public class OrderApproveAction {

  public OrderVO[] approve(OrderVO[] vos, AbstractCompiler2 script) {
    PfParameterUtil<OrderVO> util =
        new PfParameterUtil<OrderVO>(script == null ? null
            : script.getPfParameterVO(), vos);
    OrderVO[] originBills = util.getClientOrignBills();
    OrderVO[] clientBills = util.getClientFullInfoBill();
    AroundProcesser<OrderVO> processer =
        new AroundProcesser<OrderVO>(OrderPluginPoint.APPROVE);
    this.addBeforeRule(processer);
    this.addAfterRule(processer, null != script ? script.getPfParameterVO()
        : null);

    // 前规则
    processer.before(clientBills);

    // 调用平台脚本进行审批
    if (null != script) {
      try {
        script.procFlowBacth(script.getPfParameterVO());
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }

    // 可用量更新前处理
    this.atpBeforeUpdate(clientBills);

    // 把VO持久化到数据库中
    BillUpdate<OrderVO> update = new BillUpdate<OrderVO>();
    OrderVO[] returnVos = update.update(clientBills, originBills);

    // 可用量更新
    this.atpUpdate(returnVos);
    // 预留
    // this.auditSupply(returnVos);

    // 后规则
    processer.after(returnVos);
    return returnVos;
  }

  private void addAfterRule(AroundProcesser<OrderVO> processer,
      PfParameterVO pfParameterVO) {
    // 写业务日志要放到FilterOrderByStatusRule的前面，否则vo有可能会被过滤掉，就没法记录日志了
    processer.addAfterRule(new WriteOperateLogRule<OrderVO>(
        PuBusiLogPathCode.orderApprovePath.getCode(),
        PuBusiLogActionCode.approve.getCode()));

    processer.addAfterRule(new FilterOrderByStatusRule(POEnumBillStatus.APPROVE
        .toInt()));
    processer.addAfterRule(new ApproveSupplyRule());
    // 采购计划检查
    processer.addAfterRule(new ApproveBudgetCtrlRule());
    // 往在途状态表中插入数据
    processer.addAfterRule(new InsertStatusOnWayRule());
    processer.addAfterRule(new InsertPayPlanRule());
    // 设置可入库数量，为审核后直接推入库单提供支持
    processer.addAfterRule(new FillNcaninnumRule());
    // 审批后事件
    processer.addAfterRule(new ApproveAfterEventRule());

    // 更新流程平台的VO--必须处理，如果前面规则已经过滤为空，则后规则就不执行了
    // 平台脚本中还是原来的VO，则还会驱动下游
    processer.addAfterFinalRule(new UpdatePflowVORule<OrderVO>(pfParameterVO));

  }

  private void addBeforeRule(AroundProcesser<OrderVO> processer) {
    // 审核VO状态检查
    processer.addBeforeRule(new ApproveVOValidateRule());
    // 供应商冻结检查
    processer.addBeforeRule(new SupplierFrozeChkRule());
    // 审批前事件
    processer.addBeforeRule(new ApproveBeforeEventRule());
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
    // OrderVO[] noPassVOs = this.getNoPassVOs(vos);
    // if (noPassVOs != null) {
    ATPServices.modifyATPBefore(POBillType.Order.getCode(), vos);
    // }
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
    // OrderVO[] noPassVOs = this.getNoPassVOs(vos);
    // if (noPassVOs != null) {
    ATPServices.modifyATPAfter(POBillType.Order.getCode(), vos);
    // }
  }

  /**
   * 方法功能描述：预留
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 上午09:52:38
   */
  // private void auditSupply(OrderVO[] vos) {
  // // Map<String, PoTransTypeVO> transtypeMap = this.getTransType(vos);
  // // List<OrderVO> list = new ArrayList<OrderVO>();
  // // for (OrderVO vo : vos) {
  // // String vtrantypecode = vo.getHVO().getVtrantypecode();
  // // PoTransTypeVO transTypeVO = transtypeMap.get(vtrantypecode);
  // // if (null == transTypeVO) {
  // // ExceptionUtils.wrappBusinessException("采购订单交易类型" + vtrantypecode
  // // + "扩展表没有数据，请检查");
  // // return;
  // // }
  // // if (UFBoolean.TRUE.equals(transTypeVO.getBdirect())) {
  // // continue;
  // // }
  // // List<OrderItemVO> itemList = new ArrayList<OrderItemVO>();
  // // OrderItemVO[] itemVOs = vo.getBVO();
  // // for (OrderItemVO itemVO : itemVOs) {
  // // if (itemVO.getDplanarrvdate() != null) {
  // // itemList.add(itemVO);
  // // }
  // // }
  // // if (0 == itemList.size()) {
  // // continue;
  // // }
  // // OrderVO reserveVO = (OrderVO) vo.clone();
  // // OrderItemVO[] reserveItems =
  // // itemList.toArray(new OrderItemVO[itemList.size()]);
  // // reserveVO.setBVO(reserveItems);
  // // list.add(reserveVO);
  // // }
  // // if (0 == list.size()) {
  // // return;
  // // }
  // OrderVO[] reserveVOs = OrderVOUtil.getInsance().getSupplyVO(vos);
  // if (ArrayUtils.isEmpty(reserveVOs)) {
  // return;
  // }
  // ReserveServices.auditSupply(reserveVOs);
  // }
  /**
   * 方法功能描述：得到审批不通过的订单
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-2 下午01:39:45
   */
  // private OrderVO[] getNoPassVOs(OrderVO[] vos) {
  // Set<OrderVO> noPassVOs = new HashSet<OrderVO>();
  //
  // for (OrderVO vo : vos) {
  // if (POEnumBillStatus.NOPASS.value().equals(vo.getHVO().getForderstatus()))
  // {
  // noPassVOs.add(vo);
  // }
  // }
  //
  // if (!noPassVOs.isEmpty()) {
  // return noPassVOs.toArray(new OrderVO[0]);
  // }
  //
  // return null;
  // }

  // private Map<String, PoTransTypeVO> getTransType(OrderVO[] vos) {
  // Set<String> set = new HashSet<String>();
  // for (OrderVO vo : vos) {
  // String vtrantypecode = vo.getHVO().getVtrantypecode();
  // set.add(vtrantypecode);
  // }
  //
  // IPoTransTypeQuery service =
  // NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
  // try {
  // return service
  // .queryAttrByTypes(set.toArray(new String[set.size()]), null);
  // }
  // catch (BusinessException e) {
  // ExceptionUtils.wrappException(e);
  // }
  //
  // return null;
  // }
}
