package nc.vo.pu.m27.query;

import java.io.Serializable;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算关系信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-7-13 下午03:11:05
 */
public class SettleBillInfo implements Serializable {
  private static final long serialVersionUID = 5789568357045139533L;

  private UFBoolean bwashest;

  private UFDouble noppconfirmapmny;

  private UFDouble nsettlenum;

  private String pk_invoice;

  private String pk_invoice_b;

  private String pk_rushinvoice;

  private String pk_rushinvoice_b;

  private String pk_rushstock;

  private String pk_rushstock_b;

  private String pk_settlebill_b;

  private String pk_stock;

  private String pk_stock_b;

  private String pk_stockorder;

  private String pk_stockorder_b;

  private String vstockbilltype;

  public UFBoolean getBwashest() {
    return this.bwashest;
  }

  /**
   * 方法功能描述：结算明细相应的确认应付应付原币价税合计。
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-27 下午07:56:21
   */
  public UFDouble getNoppconfirmapmny() {
    return this.noppconfirmapmny;
  }

  /**
   * 方法功能描述：数量
   * <p>
   * <b>参数说明</b>
   * 
   * @return 数量
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-13 下午04:22:34
   */
  public UFDouble getNsettlenum() {
    return this.nsettlenum;
  }

  /**
   * 方法功能描述：发票的ID
   * <p>
   * <b>参数说明</b>
   * 
   * @return 发票的ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-15 下午04:52:32
   */
  public String getPk_invoice() {
    return this.pk_invoice;
  }

  /**
   * 方法功能描述：发票的行ID
   * <p>
   * <b>参数说明</b>
   * 
   * @return 发票的行ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-15 下午04:52:49
   */
  public String getPk_invoice_b() {
    return this.pk_invoice_b;
  }

  /**
   * 方法功能描述：对冲发票的ID
   * <p>
   * <b>参数说明</b>
   * 
   * @return 对冲发票的ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-15 下午05:00:29
   */
  public String getPk_rushinvoice() {
    return this.pk_rushinvoice;
  }

  /**
   * 方法功能描述：对冲的发票行ID<br>
   * 1、如果是入库单发票匹配的结算行，则该值为空<br>
   * 2、如果是费用发票，则该值为空<br>
   * <p>
   * <b>参数说明</b>
   * 
   * @return 对冲的发票行ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-13 下午04:21:06
   */
  public String getPk_rushinvoice_b() {
    return this.pk_rushinvoice_b;
  }

  /**
   * 方法功能描述：对冲入库单的ID
   * <p>
   * <b>参数说明</b>
   * 
   * @return 对冲入库单的ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-27 上午11:01:17
   */
  public String getPk_rushstock() {
    return this.pk_rushstock;
  }

  /**
   * 方法功能描述：对冲入库单的明细ID
   * <p>
   * <b>参数说明</b>
   * 
   * @return 对冲入库单的明细ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-27 上午11:01:44
   */
  public String getPk_rushstock_b() {
    return this.pk_rushstock_b;
  }

  /**
   * 方法功能描述：结算单明细ID
   * <p>
   * <b>参数说明</b>
   * 
   * @return 结算单明细ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-21 下午04:17:21
   */
  public String getPk_settlebill_b() {
    return this.pk_settlebill_b;
  }

  /**
   * 方法功能描述：对应入库单的单据ID<br>
   * 如果是红蓝发票对冲，则该值为空<br>
   * <p>
   * <b>参数说明</b>
   * 
   * @return 对应入库单的单据ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-15 下午04:51:31
   */
  public String getPk_stock() {
    return this.pk_stock;
  }

  /**
   * 方法功能描述：对应入库单的单据行ID<br>
   * 如果是红蓝发票对冲，则该值为空<br>
   * <p>
   * <b>参数说明</b>
   * 
   * @return 对应入库单的单据行ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-13 下午04:18:34
   */
  public String getPk_stock_b() {
    return this.pk_stock_b;
  }

  /**
   * @return pk_stockorder
   */
  public String getPk_stockorder() {
    return this.pk_stockorder;
  }

  /**
   * 方法功能描述：对应入库单的源头采购订单行（要传到应付，以便应付单和付款单核销）
   * <p>
   * <b>参数说明</b>
   * 
   * @return 对应入库单的源头采购订单行
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-13 下午04:19:05
   */
  public String getPk_stockorder_b() {
    return this.pk_stockorder_b;
  }

  /**
   * 方法功能描述：对应入库单的单据类型（如果是红蓝发票对冲，则该值为空）
   * <p>
   * <b>参数说明</b>
   * 
   * @return 对应入库单的单据类型
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-13 下午04:17:56
   */
  public String getVstockbilltype() {
    return this.vstockbilltype;
  }

  public void setBwashest(UFBoolean bwashest) {
    this.bwashest = bwashest;
  }

  /**
   * 方法功能描述：结算明细相应的确认应付应付原币价税合计。
   * <p>
   * <b>参数说明</b>
   * 
   * @param noppconfirmapmny = 结算数量 * 入库单确认应付原币价税合计(有尾差倒挤)
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-28 上午08:59:14
   */
  public void setNoppconfirmapmny(UFDouble noppconfirmapmny) {
    this.noppconfirmapmny = noppconfirmapmny;
  }

  public void setNsettlenum(UFDouble nsettlenum) {
    this.nsettlenum = nsettlenum;
  }

  public void setPk_invoice(String pkInvoice) {
    this.pk_invoice = pkInvoice;
  }

  public void setPk_invoice_b(String pkInvoiceB) {
    this.pk_invoice_b = pkInvoiceB;
  }

  public void setPk_rushinvoice(String pkRushinvoice) {
    this.pk_rushinvoice = pkRushinvoice;
  }

  public void setPk_rushinvoice_b(String pkRushinvoiceB) {
    this.pk_rushinvoice_b = pkRushinvoiceB;
  }

  public void setPk_rushstock(String pkRushstock) {
    this.pk_rushstock = pkRushstock;
  }

  public void setPk_rushstock_b(String pkRushstockB) {
    this.pk_rushstock_b = pkRushstockB;
  }

  public void setPk_settlebill_b(String pkSettlebillB) {
    this.pk_settlebill_b = pkSettlebillB;
  }

  public void setPk_stock(String pkStock) {
    this.pk_stock = pkStock;
  }

  public void setPk_stock_b(String pkStockB) {
    this.pk_stock_b = pkStockB;
  }

  /**
   * @param pk_stockorder 要设置的 pk_stockorder
   */
  public void setPk_stockorder(String pk_stockorder) {
    this.pk_stockorder = pk_stockorder;
  }

  public void setPk_stockorder_b(String pkStockorderB) {
    this.pk_stockorder_b = pkStockorderB;
  }

  public void setVstockbilltype(String vstockbilltype) {
    this.vstockbilltype = vstockbilltype;
  }

}
