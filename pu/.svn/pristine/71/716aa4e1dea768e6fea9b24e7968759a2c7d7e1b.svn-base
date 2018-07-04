package nc.pubimpl.pu.it.merge;

import java.util.List;

import nc.impl.pu.m27.merge.FeeMatchMerge;
import nc.pubimpl.pu.it.rule.MatchMergeFeeDiscountAdjustRule;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumSettleOrderType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.util.SettlePublicUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 进口费用结算匹配规则
 * 
 * @since 6.31
 * @version 2013-10-30 上午09:07:18
 * @author mengjian
 */
public class FeeMatchMergeForIT extends FeeMatchMerge {

  public FeeMatchMergeForIT(StockSettleVO[] stocks, FeeDiscountSettleVO[] fees,
      FeeDiscountSettleVO[] discounts, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment envi) {
    super(stocks, fees, discounts, adjustInvcVos, envi);
  }

  @Override
  protected void recordFeeDiscountInfo(SettleBillItemVO[] voaItem,
      StockSettleVO[] m_voaStock) {
    new MatchMergeFeeDiscountAdjustRule(this.getSettleEnv(), m_voaStock)
        .process(voaItem);
  }

  /**
   * //设置结算类型 0：采购 ；1：进口
   * 
   * @param splitVos
   */
  @Override
  protected void setFsettletypes(SettleBillVO[] splitVos) {
    for (SettleBillVO bill : splitVos) {
      // 设置结算类型 0：采购 ；1：进口
      bill.getParentVO().setFsettletype(EnumSettleOrderType.IT.toInteger());
    }
  }

  @Override
  protected void setStockFeeRowMnyNumInfo(List<SettleBillItemVO> listItemVO) {
    CostfactorViewVO[] cfviews = this.getSettleEnv().getCostFactorViews();
    for (SettleBillItemVO item : listItemVO) {
      item.setNgoodsmoney(null);
      item.setNgoodsprice(null);
      item.setNprice(null);
      item.setNsettlenum(null);
      UFDouble sumMny = SettlePublicUtil.getSumCostfactor(item, cfviews);
      sumMny =
          MathTool.add(sumMny,
              MathTool.add(item.getNdiscount(), item.getNadjustmny()));
      item.setNmoney(sumMny);
    }
  }
}
