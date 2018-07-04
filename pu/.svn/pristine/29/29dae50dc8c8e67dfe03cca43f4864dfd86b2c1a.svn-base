package nc.pubimpl.pu.it.bp;

import nc.impl.pu.m27.bp.SettleBillToIABP;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.pub.SettleEnvironment;

/**
 * @since 6.31
 * @version 2013-11-20 下午03:03:22
 * @author mengjian
 */
public class SettleBillToIABPForIT extends SettleBillToIABP {

  public SettleBillToIABPForIT(SettleBillVO bill, SettleEnvironment settleEnv) {
    super(bill, settleEnv);
  }

  // 不需这样处理
  // @Override
  // protected void procAdjustGoodsItems() {
  // for (SettleBillItemVO voSettleItem : this.getBill().getChildrenVO()) {
  // Integer frowtype = voSettleItem.getFrowtype();
  // StockSettleVO voStockItem =
  // this.getStockInfo().getStockSettleVO(voSettleItem.getPk_stock_b());
  //
  // // mengjian 结算单表体为进口货物调整的一定传调整单
  // if (EnumMatchRowType.AdjustGoods.value().equals(frowtype)
  // && null != voStockItem
  // && SettleBillItemVOUtil.isSettleToIA(voSettleItem, voStockItem)) {
  // this.getToIASet().addToI9Items(voSettleItem);
  // }
  // }
  // }

}
