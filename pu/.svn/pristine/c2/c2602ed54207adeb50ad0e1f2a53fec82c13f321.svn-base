/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-19 上午11:16:26
 */
package nc.vo.pu.m25.entity;

import java.util.HashSet;
import java.util.Set;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算时回写采购发票VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-19 上午11:16:26
 */
public class InvoiceWriteBackVO {
  /** 金额差 **/
  private UFDouble diffMny;

  // /**上次数量**/
  // private UFDouble oldNum;
  // /**本次数量**/
  // private UFDouble newNum;
  // /**上次金额**/
  // private UFDouble oldMny;
  // /**本次金额**/
  // private UFDouble newMny;
  /** 数量差 **/
  private UFDouble diffNum;

  /** 采购发票头ID **/
  private String pk_invoice;

  /** 采购发票体ID **/
  private String pk_invoice_b;

  /** 结算单中入库单对应的采购订单头ID **/
  private String pk_order;

  /** 结算单中入库单对应的采购订单体ID **/
  private String pk_order_b;

  /**
   * 方法功能描述：得到发票表体ID数组。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-2-2 上午10:28:01
   */
  public static String[] getInvoiceBIds(InvoiceWriteBackVO[] vos) {
    Set<String> ids = new HashSet<String>();
    for (InvoiceWriteBackVO vo : vos) {
      ids.add(vo.getPk_invoice_b());
    }
    return ids.toArray(new String[ids.size()]);
  }

  /**
   * @return diffMny
   */
  public UFDouble getDiffMny() {
    return this.diffMny;
  }

  /**
   * @return diffNum
   */
  public UFDouble getDiffNum() {
    return this.diffNum;
  }

  /**
   * @return pk_invoice
   */
  public String getPk_invoice() {
    return this.pk_invoice;
  }

  /**
   * @return pk_invoice_b
   */
  public String getPk_invoice_b() {
    return this.pk_invoice_b;
  }

  /**
   * @return pk_order
   */
  public String getPk_order() {
    return this.pk_order;
  }

  /**
   * @return pk_order_b
   */
  public String getPk_order_b() {
    return this.pk_order_b;
  }

  /**
   * @param diffMny 要设置的 diffMny
   */
  public void setDiffMny(UFDouble diffMny) {
    this.diffMny = diffMny;
  }

  /**
   * @param diffNum 要设置的 diffNum
   */
  public void setDiffNum(UFDouble diffNum) {
    this.diffNum = diffNum;
  }

  /**
   * @param pk_invoice 要设置的 pk_invoice
   */
  public void setPk_invoice(String pk_invoice) {
    this.pk_invoice = pk_invoice;
  }

  /**
   * @param pk_invoice_b 要设置的 pk_invoice_b
   */
  public void setPk_invoice_b(String pk_invoice_b) {
    this.pk_invoice_b = pk_invoice_b;
  }

  /**
   * @param pk_order 要设置的 pk_order
   */
  public void setPk_order(String pk_order) {
    this.pk_order = pk_order;
  }

  /**
   * @param pk_order_b 要设置的 pk_order_b
   */
  public void setPk_order_b(String pk_order_b) {
    this.pk_order_b = pk_order_b;
  }

}
