package nc.bs.pu.m27.feesettle.util;

import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>记录对于同一入库单的同一费用项的费用结算分摊明细与费用暂估分摊明细的关系(一一对应)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-27 下午02:04:57
 */
public class FeeSettleEstMapping {
  // 暂估费用分摊明细
  private FeeEstDistVO estallotvo;

  // 暂估费用
  private FeeEstVO feeestvo;

  // 费用结算分摊明细
  private SettleFeeAllotDetailVO settleallotvo;

  public FeeSettleEstMapping(FeeEstVO feeestvo, FeeEstDistVO estallotvo,
      SettleFeeAllotDetailVO settleallotvo) {
    super();
    this.feeestvo = feeestvo;
    this.estallotvo = estallotvo;
    this.settleallotvo = settleallotvo;
  }

  public FeeEstDistVO getEstallotvo() {
    return this.estallotvo;
  }

  public FeeEstVO getFeeestvo() {
    return this.feeestvo;
  }

  public SettleFeeAllotDetailVO getSettleallotvo() {
    return this.settleallotvo;
  }

}
