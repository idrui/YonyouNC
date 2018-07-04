package nc.bs.pu.m27.feesettle.rule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.pu.m27.feesettle.FeeSettleBP;
import nc.bs.pu.m27.feesettle.util.FeeSettlePrivateUtil;
import nc.bs.pu.m27.settlebill.rule.StockInfoUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.pub.SettleEnvironment;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 * 完成费用结算的业务实现(提供给其他非费用结算，但是又寻源)
 * @scene
 * 结算完毕保存结算单
 * @param
 * envi 结算的环境信息
 *
 * @since 6.3
 * @version 2014-10-22 下午4:21:06
 * @author zhangshqb
 */
public class FeeSettleForGoodsRule implements IRule<SettleBillVO> {

  // 结算环境
  private SettleEnvironment envi;

  // 库存单据信息的工具类
  private StockInfoUtil stockinfo;

  public FeeSettleForGoodsRule(SettleEnvironment envi) {
    this.envi = envi;
  }

  @Override
  public void process(SettleBillVO[] vos) {
    // 需要进行费用分摊的结算单
    List<SettleBillVO> myvos = FeeSettlePrivateUtil.findNeedFeeSettleVO(vos);
    if (myvos == null || myvos.size() == 0) {
      return;
    }
    // 这里必须重新初始化，保证为最新的数据
    this.stockinfo = new StockInfoUtil(vos);
    // 需要进行费用结算的结算单、费用结算所需要的入库单
    SettleBillVO[] bills = myvos.toArray(new SettleBillVO[0]);
    StockSettleVO[] stocks = this.getFeeSettleNeedsStocks(bills);

    // 刷新采购财务入的入库单(货物结算时已经更新了累计结算、冲销暂估等字段),
    // 这里不用刷新了，因为getFeeSettleNeedsStocks（）方法中取得的数据已经是最新的了
    // stocks = this.refreashM45StockSettleVO(stocks);
    if (ArrayUtils.isEmpty(stocks)) {
      return;
    }
    // 进行费用结算
    new FeeSettleBP(stocks, this.envi).doFeeSettle(bills);
  }

  private StockSettleVO[] getFeeSettleNeedsStocks(SettleBillVO[] bills) {
    if (this.stockinfo == null || bills == null || bills.length == 0) {
      return null;
    }
    // 获得费用结算所需要的入库单
    Set<StockSettleVO> mystocks = new HashSet<StockSettleVO>();
    for (SettleBillVO bill : bills) {
      for (SettleBillItemVO item : bill.getChildrenVO()) {
        String stockbid = item.getPk_stock_b();
        if (StringUtils.isEmpty(stockbid)) {
          continue;
        }
        if (this.stockinfo.getStockSettleVO(stockbid) == null) {
          continue;
        }
        mystocks.add(this.stockinfo.getStockSettleVO(stockbid));
      }
    }
    return mystocks.toArray(new StockSettleVO[0]);
  }

}
