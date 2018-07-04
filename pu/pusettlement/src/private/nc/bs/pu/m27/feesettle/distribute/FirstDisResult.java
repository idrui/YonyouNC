package nc.bs.pu.m27.feesettle.distribute;

import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>记录入库单费用之间的分摊明细(第一次分摊)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-7 下午07:34:58
 */
public class FirstDisResult {
  // TRUE:费用分摊,FALSE:折扣分摊
  private boolean bfeedistribute;

  // 费用分摊时所依赖的成本要素VO
  private CostfactorViewVO costfactorvo;

  // 分摊金额
  private UFDouble dismny;

  // 成本要素索引号
  private int factorindex;

  // 费用发票的行主键
  private String invoicebid;

  // 费用或者劳务物料OID
  private String pk_material;

  // 费用或者劳务物料VID
  private String pk_srcmaterial;

  // 供应商
  private String pk_supplier;

  // 入库单的行主键
  private String stockbid;

  // 入库单的单据类型
  private String stockbilltype;

  // 入库单的主键
  private String stockid;

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

  public String getStockbilltype() {
    return this.stockbilltype;
  }

  public String getStockid() {
    return this.stockid;
  }

  public boolean isBfeedistribute() {
    return this.bfeedistribute;
  }

  public void setBfeedistribute(boolean bfeedistribute) {
    this.bfeedistribute = bfeedistribute;
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

  public void setStockbilltype(String stockbilltype) {
    this.stockbilltype = stockbilltype;
  }

  public void setStockid(String stockid) {
    this.stockid = stockid;
  }
}
