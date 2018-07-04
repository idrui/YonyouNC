package nc.vo.pu.report.enumeration;

public enum StoreReqSummaryQryFieldCode {

  /** 申请部门 */
  appdept("pk_storereq_b.pk_appdept"),
  /** 申请人 */
  appsn("pk_storereq_b.pk_apppsn"),
  /** 收货地点 */
  cdevaddrid("pk_storereq_b.cdevaddrid"),
  /** 收货地区 */
  cdevareaid("pk_storereq_b.cdevareaid"),
  /** 项目 */
  cprojectid("pk_storereq_b.cprojectid"),
  /** 申请日期 */
  dbilldate("dbilldate"),
  /** 需求日期 */
  dreqdate("pk_storereq_b.dreqdate"),
  /** 单据状态 */
  fbillstatus("fbillstatus"),
  /** 需求类型 */
  freqtypeflag("freqtypeflag"),
  /** 物料基本分类编码 */
  materialclasscode("pk_storereq_b.pk_srcmaterial.pk_marbasclass.code"),
  /** 物料编码 */
  materialcode("pk_storereq_b.pk_srcmaterial.code"),
  /** 物料名称 */
  materialname("pk_storereq_b.pk_srcmaterial.name"),
  /** 库存组织 */
  org("pk_org"),
  /** 收货地址 */
  receiveaddress("pk_storereq_b.pk_receiveaddress"),
  /** 需求仓库 */
  reqstordoc("pk_storereq_b.pk_reqstordoc"),
  /** 汇总方式 */
  summarytype("summarytype"),
  /** 申请单号 */
  vbillcode("vbillcode");

  private String fieldCode = null;

  private StoreReqSummaryQryFieldCode(String fieldCode) {
    this.fieldCode = fieldCode;
  }

  public String getFieldCode() {
    return this.fieldCode;
  }
}
