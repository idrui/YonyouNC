/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-23 上午11:18:20
 */
package nc.vo.pu.est.entity.fee;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>费用分摊参数VO，且返回分摊得到的金额
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-23 上午11:18:20
 */
public class FeeMnyDivideVO {
  // 表头主键
  private String billhpk;

  private UFDouble billmny;

  private UFDouble billnum;

  private String billpk;

  // 单据类型
  private String billtype;

  private UFDouble byvalue;

  /** 分摊到的金额（原币价税合计） */
  private UFDouble dividedmny;

  /** 是否倒挤行，即最后一行 */
  private boolean lastRow = false;

  private String mvid;

  /** 要分摊的总的记成本金额 **/
  private UFDouble totalCalcostMny;

  /** 要分摊的总的计税金额 **/
  private UFDouble totalCaltaxMny;

  /** 要分摊的总的价税合计（本币） **/
  private UFDouble totalMny;

  /** 要分摊的总的不可抵扣税额（本币） **/
  private UFDouble totalNoSubTaxMny;

  /** 要分摊的总的无税金额（原币） **/
  private UFDouble totalOrigMny;

  /** 要分摊的总的税额（本币） **/
  private UFDouble totalOrigTax;

  /** 要分摊的总的价税合计（原币） **/
  private UFDouble totalOrigTaxMny;

  /** 要分摊的总的税额（本币） **/
  private UFDouble totalTax;

  /** 要分摊的总的无税金额（本币） **/
  private UFDouble totalTaxMny;

  /**
   * FeeMnyDivideVO 的构造子
   * 
   * @param billmny
   *          单据金额，用于以金额分摊方式
   * @param billnum
   *          单据数量，用于除金额分摊方式外的其它分摊方式
   * @param pk_material
   *          物料VID
   */
  public FeeMnyDivideVO(UFDouble billmny, UFDouble billnum, String pk_material) {
    this.billmny = billmny;
    this.billnum = billnum;
    this.mvid = pk_material;
  }

  public String getBillhpk() {
    return this.billhpk;
  }

  /**
   * @return billmny
   */
  public UFDouble getBillmny() {
    return this.billmny;
  }

  /**
   * @return billnum
   */
  public UFDouble getBillnum() {
    return this.billnum;
  }

  /**
   * @return billpk
   */
  public String getBillpk() {
    return this.billpk;
  }

  public String getBilltype() {
    return this.billtype;
  }

  /**
   * @return byvalue 分摊依据的值(数量、金额、体积、重量)
   */
  public UFDouble getByvalue() {
    return this.byvalue;
  }

  /**
   * @return dividedmny 分摊到的金额
   */
  public UFDouble getDividedmny() {
    return this.dividedmny;
  }

  /**
   * @return mvid
   */
  public String getMvid() {
    return this.mvid;
  }

  public UFDouble getTotalCalcostMny() {
    return this.totalCalcostMny;
  }

  public UFDouble getTotalCaltaxMny() {
    return this.totalCaltaxMny;
  }

  public UFDouble getTotalMny() {
    return this.totalMny;
  }

  public UFDouble getTotalNoSubTaxMny() {
    return this.totalNoSubTaxMny;
  }

  public UFDouble getTotalOrigMny() {
    return this.totalOrigMny;
  }

  public UFDouble getTotalOrigTax() {
    return this.totalOrigTax;
  }

  public UFDouble getTotalOrigTaxMny() {
    return this.totalOrigTaxMny;
  }

  public UFDouble getTotalTax() {
    return this.totalTax;
  }

  public UFDouble getTotalTaxMny() {
    return this.totalTaxMny;
  }

  public boolean isLastRow() {
    return this.lastRow;
  }

  public void setBillhpk(String billhpk) {
    this.billhpk = billhpk;
  }

  /**
   * @param billmny
   *          要设置的 billmny
   */
  public void setBillmny(UFDouble billmny) {
    this.billmny = billmny;
  }

  /**
   * @param billnum
   *          要设置的 billnum
   */
  public void setBillnum(UFDouble billnum) {
    this.billnum = billnum;
  }

  /**
   * @param billpk
   *          要设置的 billpk
   */
  public void setBillpk(String billpk) {
    this.billpk = billpk;
  }

  public void setBilltype(String billtype) {
    this.billtype = billtype;
  }

  /**
   * @param byvalue
   *          要设置的 分摊依据的值((数量、金额、体积、重量)
   */
  public void setByvalue(UFDouble byvalue) {
    this.byvalue = byvalue;
  }

  /**
   * @param dividedmny
   *          要设置的 dividedmny
   */
  public void setDividedmny(UFDouble dividedmny) {
    this.dividedmny = dividedmny;
  }

  public void setLastRow(boolean lastRow) {
    this.lastRow = lastRow;
  }

  /**
   * @param mvid
   *          要设置的 mvid
   */
  public void setMvid(String mvid) {
    this.mvid = mvid;
  }

  public void setTotalCalcostMny(UFDouble totalCalcostMny) {
    this.totalCalcostMny = totalCalcostMny;
  }

  public void setTotalCaltaxMny(UFDouble totalCaltaxMny) {
    this.totalCaltaxMny = totalCaltaxMny;
  }

  public void setTotalMny(UFDouble totalMny) {
    this.totalMny = totalMny;
  }

  public void setTotalNoSubTaxMny(UFDouble totalNoSubTaxMny) {
    this.totalNoSubTaxMny = totalNoSubTaxMny;
  }

  public void setTotalOrigMny(UFDouble totalOrigMny) {
    this.totalOrigMny = totalOrigMny;
  }

  public void setTotalOrigTax(UFDouble totalOrigTax) {
    this.totalOrigTax = totalOrigTax;
  }

  public void setTotalOrigTaxMny(UFDouble totalOrigTaxMny) {
    this.totalOrigTaxMny = totalOrigTaxMny;
  }

  public void setTotalTax(UFDouble totalTax) {
    this.totalTax = totalTax;
  }

  public void setTotalTaxMny(UFDouble totalTaxMny) {
    this.totalTaxMny = totalTaxMny;
  }

}
