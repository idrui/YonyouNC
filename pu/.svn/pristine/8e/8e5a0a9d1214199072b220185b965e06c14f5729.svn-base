package nc.vo.pu.m27.merge;

import java.io.Serializable;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;

/**
 * 存放匹配执行时的发票、入库单等数据<br>
 * 先做好，用于重构结算的接口，过滤规则等实现（by zhaoyha at 2011.4.29)<br>
 * 
 * @since 6.0
 * @version 2011-4-29 下午10:18:15
 * @author zhaoyha
 */
public class MatchMergeData implements Serializable {

  private static final long serialVersionUID = 8097492053040469933L;

  /** 调整发票 **/
  private FeeDiscountSettleVO[] adjustInvcVos;

  /** 折扣发票 **/
  private FeeDiscountSettleVO[] discountInvcVos;

  /** 费用发票 **/
  private FeeDiscountSettleVO[] feeInvcVos;

  /** 货物发票 **/
  private InvoiceSettleVO[] goodsInvcVos;

  /** 入库单 **/
  private StockSettleVO[] stockVos;

  /**
   * @param stockVos 入库单结算VO数组
   * @param goodsInvcVos　货物发票结算VO数组
   * @param discountInvcVos　折扣发票结算VO数组
   * @param feeInvcVos　费用发票结算VO数组
   */
  public MatchMergeData(StockSettleVO[] stockVos,
      InvoiceSettleVO[] goodsInvcVos, FeeDiscountSettleVO[] feeInvcVos,
      FeeDiscountSettleVO[] discountInvcVos) {
    super();
    this.stockVos = stockVos;
    this.goodsInvcVos = goodsInvcVos;
    this.discountInvcVos = discountInvcVos;
    this.feeInvcVos = feeInvcVos;
  }

  public MatchMergeData(StockSettleVO[] stockVos,
      InvoiceSettleVO[] goodsInvcVos, FeeDiscountSettleVO[] feeInvcVos,
      FeeDiscountSettleVO[] discountInvcVos, FeeDiscountSettleVO[] adjustInvcVos) {
    this(stockVos, goodsInvcVos, feeInvcVos, discountInvcVos);
    this.adjustInvcVos = adjustInvcVos;
  }

  public FeeDiscountSettleVO[] getAdjustInvcVos() {
    return this.adjustInvcVos;
  }

  public FeeDiscountSettleVO[] getDiscountInvcVos() {
    return this.discountInvcVos;
  }

  public FeeDiscountSettleVO[] getFeeInvcVos() {
    return this.feeInvcVos;
  }

  public InvoiceSettleVO[] getGoodsInvcVos() {
    return this.goodsInvcVos;
  }

  public StockSettleVO[] getStockVos() {
    return this.stockVos;
  }

  public void setAdjustInvcVos(FeeDiscountSettleVO[] adjustInvcVos) {
    this.adjustInvcVos = adjustInvcVos;
  }

  public void setDiscountInvcVos(FeeDiscountSettleVO[] discountInvcVos) {
    this.discountInvcVos = discountInvcVos;
  }

  public void setFeeInvcVos(FeeDiscountSettleVO[] feeInvcVos) {
    this.feeInvcVos = feeInvcVos;
  }

  public void setGoodsInvcVos(InvoiceSettleVO[] goodsInvcVos) {
    this.goodsInvcVos = goodsInvcVos;
  }

  public void setStockVos(StockSettleVO[] stockVos) {
    this.stockVos = stockVos;
  }

}
