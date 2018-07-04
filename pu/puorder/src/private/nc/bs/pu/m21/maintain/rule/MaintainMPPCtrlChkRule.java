/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 上午09:54:01
 */
package nc.bs.pu.m21.maintain.rule;

import nc.vo.pu.m21.entity.OrderVO;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.bs.pu.m21.maintain.rule.iadapter.MPPAdapter;

import nc.impl.pubapp.pattern.rule.ICompareRule;

/**
 * @description
 *              采购计划控制检查
 * @scene
 *        采购订单删除、修改保存
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午8:45:02
 * @author luojw
 */
public class MaintainMPPCtrlChkRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (!SysInitGroupQuery.isMPPEnabled()) {
      return;
    }
    MPPAdapter mppAdapter = new MPPAdapter();
    mppAdapter.budgetCtrl(vos, originVOs);

  }
}
