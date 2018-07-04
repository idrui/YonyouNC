/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-19 上午09:03:24
 */
package nc.vo.pu.m25.entity;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>用于发票询价的参数和返回值
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-19 上午09:03:24
 */
public class InvoicePriceQueryVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = -519948200847631288L;

  /** 记录表行位置 */
  private int itemIndex = -1;

  /** 记录对应的vo位置 */
  private Integer voIndex;

  public InvoicePriceQueryVO() {
    this.setVO(new InvoiceHeaderVO());
    this.setVO(new InvoiceItemVO());

  }

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CASTUNITID);
  }

  /** 币种 **/
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CORIGCURRENCYID);
  }

  /** 生产厂商 **/
  public String getCproductorid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CPRODUCTORID);
  }

  /** 项目 **/
  public String getCprojectid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CPROJECTID);
  }

  /** 质量等级 getter 方法 */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CQUALITYLEVELID);
  }

  /** 上层单据行主键 **/
  public String getCsourcebid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CSOURCEBID);
  }

  /** 上层单据主键 **/
  public String getCsourceid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CSOURCEID);
  }

  /** 上层单据类型 **/
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(InvoiceItemVO.CSOURCETYPECODE);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CUNITID);
  }

  /** 发票日期 **/
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.DBILLDATE);
  }

  /** 扣税类别 getter 方法 */
  public Integer getFtaxtypeflag() {
    return (Integer) this.getAttributeValue(InvoiceItemVO.FTAXTYPEFLAG);
  }

  /**
   * @return itemIndex
   */
  public int getItemIndex() {
    return this.itemIndex;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.pattern.model.entity.view.AbstractDataView#getMetaData()
   */
  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(InvoiceVO.class);
  }

  /** 不可抵扣税率 **/
  public UFDouble getNNoSubTaxRate() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NNOSUBTAXRATE);
  }

  /** 主原币无税净单价 **/
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NORIGPRICE);
  }

  /** 主原币净含税单价 **/
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NORIGTAXPRICE);
  }

  /** 主本币无税单价 **/
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NPRICE);
  }

  /** 主本币含税单价 **/
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NTAXPRICE);
  }

  /** 税率 getter 方法 */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NTAXRATE);
  }

  /** 采购发票主表_主键 **/
  public String getPk_invoice() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_INVOICE);
  }

  /** 发票子实体主键 **/
  public String getPk_invoice_b() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_INVOICE_B);
  }

  /** 物料(VID) **/
  public String getPk_material() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_MATERIAL);
  }

  /** 采购订单行主键 **/
  public String getPk_order_b() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_ORDER_B);
  }

  /** 财务组织 **/
  public String getPk_org() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_ORG);
  }

  /** 采购组织 **/
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_PURCHASEORG);
  }

  /** 物料(OID) **/
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_SRCMATERIAL);
  }

  /** 入库单行ID getter 方法 */
  public String getPk_stockps_b() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_STOCKPS_B);
  }

  /** 供应商 **/
  public String getPk_supplier() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.PK_SUPPLIER);
  }

  public Integer getVoIndex() {
    return this.voIndex;
  }

  /** 单位 setter 方法 */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(InvoiceItemVO.CASTUNITID, castunitid);
  }

  /** 币种 **/
  public void setCorigcurrencyid(String corigcurrencyid) {
    this.setAttributeValue(InvoiceHeaderVO.CORIGCURRENCYID, corigcurrencyid);
  }

  /** 生产厂商 **/
  public void setCproductorid(String cproductorid) {
    this.setAttributeValue(InvoiceItemVO.CPRODUCTORID, cproductorid);
  }

  /** 项目 **/
  public void setCprojectid(String cprojectid) {
    this.setAttributeValue(InvoiceItemVO.CPROJECTID, cprojectid);
  }

  /** 质量等级 setter 方法 */
  public void setCqualitylevelid(String cqualitylevelid) {
    this.setAttributeValue(InvoiceItemVO.CQUALITYLEVELID, cqualitylevelid);
  }

  /** 上层单据行主键 **/
  public void setCsourcebid(String csourcebid) {
    this.setAttributeValue(InvoiceItemVO.CSOURCEBID, csourcebid);
  }

  /** 上层单据主键 **/
  public void setCsourceid(String csourceid) {
    this.setAttributeValue(InvoiceItemVO.CSOURCEID, csourceid);
  }

  /** 上层单据类型 **/
  public void setCsourcetypecode(String csourcetypecode) {
    this.setAttributeValue(InvoiceItemVO.CSOURCETYPECODE, csourcetypecode);
  }

  /** 主单位 setter 方法 */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(InvoiceItemVO.CUNITID, cunitid);
  }

  /** 发票日期 **/
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(InvoiceHeaderVO.DBILLDATE, dbilldate);
  }

  /** 扣税类别 setter 方法 */
  public void setFtaxtypeflag(Integer ftaxtypeflag) {
    this.setAttributeValue(InvoiceItemVO.FTAXTYPEFLAG, ftaxtypeflag);
  }

  /**
   * @param itemIndex 询价VO在发票表体行的index
   */
  public void setItemIndex(int itemIndex) {
    this.itemIndex = itemIndex;
  }

  /** 不可抵扣税率 **/
  public void setNNoSubTaxRate(UFDouble nNoSubTaxRate) {
    this.setAttributeValue(InvoiceItemVO.NNOSUBTAXRATE, nNoSubTaxRate);
  }

  /** 主原币无税净单价 **/
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(InvoiceItemVO.NORIGPRICE, norigprice);
  }

  /** 主原币净含税单价 **/
  public void setNorigtaxprice(UFDouble norigtaxprice) {
    this.setAttributeValue(InvoiceItemVO.NORIGTAXPRICE, norigtaxprice);
  }

  /** 主本币无税单价 **/
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(InvoiceItemVO.NPRICE, nprice);
  }

  /** 主本币含税单价 **/
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(InvoiceItemVO.NTAXPRICE, ntaxprice);
  }

  /** 税率 setter 方法 */
  public void setNtaxrate(UFDouble ntaxrate) {
    this.setAttributeValue(InvoiceItemVO.NTAXRATE, ntaxrate);
  }

  /** 采购发票主表_主键 **/
  public void setPk_invoice(String pk_invoice) {
    this.setAttributeValue(InvoiceItemVO.PK_INVOICE, pk_invoice);
  }

  /** 发票子实体主键 **/
  public void setPk_invoice_b(String pk_invoice_b) {
    this.setAttributeValue(InvoiceItemVO.PK_INVOICE_B, pk_invoice_b);
  }

  /** 物料(VID) **/
  public void setPk_material(String pk_material) {
    this.setAttributeValue(InvoiceItemVO.PK_MATERIAL, pk_material);
  }

  /** 采购订单行主键 **/
  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(InvoiceItemVO.PK_ORDER_B, pk_order_b);
  }

  /** 财务组织 **/
  public void setPk_org(String pk_org) {
    this.setAttributeValue(InvoiceHeaderVO.PK_ORG, pk_org);
  }

  /** 采购组织 **/
  public void setPk_purchaseorg(String pk_purchaseorg) {
    this.setAttributeValue(InvoiceHeaderVO.PK_PURCHASEORG, pk_purchaseorg);
  }

  /** 物料(OID) **/
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(InvoiceItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** 入库单行ID setter 方法 */
  public void setPk_stockps_b(String pk_stockps_b) {
    this.setAttributeValue(InvoiceItemVO.PK_STOCKPS_B, pk_stockps_b);
  }

  /** 供应商 **/
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(InvoiceHeaderVO.PK_SUPPLIER, pk_supplier);
  }

  public void setVoIndex(Integer voIndex) {
    this.voIndex = voIndex;
  }
}
