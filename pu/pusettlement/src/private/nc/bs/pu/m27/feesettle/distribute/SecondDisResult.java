package nc.bs.pu.m27.feesettle.distribute;

import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>记录货物结算单、货物暂估之间的费用分摊明细(第二次分摊)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-7 下午07:35:23
 */
public class SecondDisResult {

  // 是否暂估费用物料的第一次结算
  private UFBoolean bestfirstsettle;

  // TRUE:费用分摊,FALSE:折扣分摊
  private boolean bfeedistribute;

  // 分摊依据金额
  private UFDouble bymny;

  // 分摊依据数量
  private UFDouble bynum;

  // 费用分摊时所依赖的成本要素VO
  private CostfactorViewVO costfactorvo;

  // 分摊金额
  private UFDouble dismny;

  // 成本要素索引号
  private int factorindex;

  // 费用发票的行主键
  private String invoicebid;

  // 分摊是所依赖的单据子主键
  private String pk_allotbillbid;

  // 分摊是所依赖的单据主键
  private String pk_allotbillid;

  // 费用或者劳务物料OID
  private String pk_material;

  // 费用或者劳务物料VID
  private String pk_srcmaterial;

  // 供应商
  private String pk_supplier;

  // 入库单的行主键
  private String stockbid;

  // 分摊所依据的单据类型
  private String vallotbilltype;

  public UFBoolean getBestfirstsettle() {
    return this.bestfirstsettle;
  }

  /**
   * 分摊依据金额
   */
  public UFDouble getBymny() {
    return this.bymny;
  }

  /**
   * 分摊依据数据
   */
  public UFDouble getBynum() {
    return this.bynum;
  }

  public CostfactorViewVO getCostfactorvo() {
    return this.costfactorvo;
  }

  public UFDouble getDismny() {
    return this.dismny;
  }

  public int getFactorindex() {
    return this.factorindex;
  }

  public String getInvoicebid() {
    return this.invoicebid;
  }

  public String getPk_allotbillbid() {
    return this.pk_allotbillbid;
  }

  public String getPk_allotbillid() {
    return this.pk_allotbillid;
  }

  public String getPk_material() {
    return this.pk_material;
  }

  public String getPk_srcmaterial() {
    return this.pk_srcmaterial;
  }

  public String getPk_supplier() {
    return this.pk_supplier;
  }

  public String getStockbid() {
    return this.stockbid;
  }

  public String getVallotbilltype() {
    return this.vallotbilltype;
  }

  public boolean isBfeedistribute() {
    return this.bfeedistribute;
  }

  public void setBestfirstsettle(UFBoolean bestfirstsettle) {
    this.bestfirstsettle = bestfirstsettle;
  }

  public void setBfeedistribute(boolean bfeedistribute) {
    this.bfeedistribute = bfeedistribute;
  }

  /**
   * 分摊依据金额
   * 
   * @param bymny
   */
  public void setBymny(UFDouble bymny) {
    this.bymny = bymny;
  }

  /**
   * 分摊依据数量
   */
  public void setBynum(UFDouble bynum) {
    this.bynum = bynum;
  }

  public void setCostfactorvo(CostfactorViewVO costfactorvo) {
    this.costfactorvo = costfactorvo;
  }

  public void setDismny(UFDouble dismny) {
    this.dismny = dismny;
  }

  public void setFactorindex(int factorindex) {
    this.factorindex = factorindex;
  }

  public void setInvoicebid(String invoicebid) {
    this.invoicebid = invoicebid;
  }

  public void setPk_allotbillbid(String pkAllotbillbid) {
    this.pk_allotbillbid = pkAllotbillbid;
  }

  public void setPk_allotbillid(String pkAllotbillid) {
    this.pk_allotbillid = pkAllotbillid;
  }

  public void setPk_material(String pkMaterial) {
    this.pk_material = pkMaterial;
  }

  public void setPk_srcmaterial(String pkSrcmaterial) {
    this.pk_srcmaterial = pkSrcmaterial;
  }

  public void setPk_supplier(String pkSupplier) {
    this.pk_supplier = pkSupplier;
  }

  public void setStockbid(String stockbid) {
    this.stockbid = stockbid;
  }

  public void setVallotbilltype(String vallotbilltype) {
    this.vallotbilltype = vallotbilltype;
  }

}
