package nc.bs.pu.m27.match.rule;

import nc.bs.pu.m27.feesettle.distribute.FirstDistributeUtil;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.util.StockSettleInfoUpdate;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 自动结算，模拟结算后，进行费用分摊的处理规则
 * 
 * @since 6.0
 * @version 2011-3-25 上午10:12:19
 * @author zhaoyha
 */
public class AutoMatchFeeDistributeRule {
  // modify by liangchen1 631进出口 private to protected
  protected StockSettleVO[] ssVos;

  /**
   * 自动结算时,进行模拟结算及费用分摊处理
   * 
   * @param ssVos 模拟结算前原始的库存结算VO
   */
  public AutoMatchFeeDistributeRule(StockSettleVO[] ssVos) {
    this.ssVos = ssVos;
  }

  /**
   * 进行根据模拟结算单信息,更新库存结算数据的结算信息
   * 
   * @return
   */
  public StockSettleVO[] process(SettleBillVO[] vos) {
    // 更新库存结算VO的货物结算信息
    this.updateGoodsSettleInfo(vos);
    for (SettleBillVO vo : vos) {
      this.distFeeDiscount(vo);
    }
    return this.ssVos;
  }

  private boolean isNeedUpdateGoodsSettleInfo() {
    // 所有入库单行均暂估或确认过也不用模拟结算
    for (StockSettleVO ssVo : this.ssVos) {
      if (!EnumToIAFlag.ConfirmToIA.toInteger().equals(ssVo.getFdirtoiatype())
          && MathTool.isZero(ssVo.getNestnum())) {
        return true;
      }
    }
    return false;
  }

  private void updateGoodsSettleInfo(SettleBillVO[] vos) {
    if (!this.isNeedUpdateGoodsSettleInfo()) {
      return;
    }
    // 更新库存结算VO中的货物结算信息
    StockSettleInfoUpdate ssUpdate = new StockSettleInfoUpdate(this.ssVos);
    ssUpdate.process(vos);
    // 这里一定要重置,因为update中会clone
    this.ssVos = ssUpdate.getUpdatedSSVO();
  }

  // modify by liangchen1 631进出口 private to protected
  protected void distFeeDiscount(SettleBillVO vo) {
    FirstDistributeUtil util = new FirstDistributeUtil(vo, this.ssVos);
    // 进行分摊,结果会同时记录到StockSettleVO上
    util.distribute();
  }

}
