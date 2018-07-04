package nc.vo.pu.report.scale;

public interface PUPubMetaNameConst {
  // 采购订单执行查询
  /** 到货主数量 */
  public static final String ARRIVE_NNUM = "arrive_nnum";

  public static final String ARRIVEBACKNNUM = "arrivebacknnum";

  public static final String ARRIVEMNY = "arrivenmny";

  public static final String ARRIVENNUM = "arrivennum";

  /** 辅单位 */
  public static final String CASTUNITID = "castunitid";

  /** 本币币种 */
  public static final String CCURRENCYID = "ccurrencyid";

  /** 物料编码 */
  public static final String CMATERIALOID = "cmaterialoid";

  /** 物料版本 */
  public static final String CMATERIALVID = "cmaterialvid";

  /** 币种 */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** 客户 */
  public static final String CASSCUSTID = "casscustid";
  
  /** 生产厂商 */
  public static final String CPRODUCTORID = "cproductorid";  

  /** 项目 */
  public static final String CPROJECTID = "cprojectid";
  
  /** 特征码 */
  public static final String CFFILEID = "cffileid";
  
  /** 行号 */
  public static final String CROWNO = "crowno";

  /** 主单位 */
  public static final String CUNITID = "cunitid";

  public static final String CURRNCLASHESTMONEY = "currnclashestmoney";

  public static final String CURRNGOODSMONEY = "currngoodsmoney";

  /** 暂估月统计 */
  public static final String CURRNSETTLENUM = "currnsettlenum";

  public static final String CURRUNESTMNY = "currunestmny";

  public static final String CURRUNESTNUM = "currunestnum";

  /** 供应商 */
  public static final String CVENDORID = "cvendorid";

  /** 采购订单执行查询-辅单位 */
  public static final String IC_CUNITID = "ic_cunitid";

  public static final String IC_NNUM = "ic_nnum";

  public static final String IC_NORIGMNY = "ic_norigmny";

  public static final String IC_NORIGPRICE = "ic_norigprice";// ic_norigprice
                                                             // 入库单价

  // 采购发票明细查询
  public static final String INBANDNTAXPRICE = "inbandntaxprice";// inbandntaxprice
                                                                 // 入库单价

  /** 发票主数量 */
  public static final String INVOICE_NNUM = "invoice_nnum";

  public static final String INVOICEMNY = "invoicenmny";

  public static final String INVOICENNUM = "invoicennum";

  public static final String INVOICETAX = "invoicetax";

  // 采购订单退货明细查询
  public static final String NACCUMREPLNUM = "naccumreplnum";// NACCUMREPLNUM
                                                             // 补货主数量

  public static final String NASTNUM = "nastnum";

  public static final String NESTOTAXPRICE = "nestotaxprice";// nestotaxprice
                                                             // 主含税单价

  /** 入库主数量 */
  public static final String NINNUM = "ninnum";

  // 发票应付余额
  public static final String NINVOICEBALANCE = "ninvoicebalance";

  // 发票本币金额
  public static final String NINVOICEMNY = "ninvoicemny";

  public static final String NNUM = "nnum";

  // 采购待开票数量查询
  public static final String NOINVOICENUM = "noinvoicenum";// noinvoicenum 未开票数量

  // 订单应付余额
  public static final String NORDERBALANCE = "norderbalance";

  // 订单本币金额
  public static final String NORDERMNY = "nordermny";

  // 订单付款金额
  public static final String NPAYMNY = "npaymny";

  public static final String NPRICE = "nprice";// NPRICE 订单主本币无税单价

  /** 税率 */
  public static final String NTAXRATE = "ntaxrate";

  // 未核销付款金额
  public static final String NUNVERIFYMNY = "nunverifymny";

  public static final String ORDER_NMNY = "order_nmny"; // NMNY1 本币无税金额

  // 请购单执行明细查询
  /** 订货主数量 */
  public static final String ORDER_NNUM = "order_nnum";

  public static final String ORDERMNY = "ordernmny";

  // 综合日报
  public static final String ORDERNNUM = "ordernnum";

  public static final String ORDERTAX = "orderntax";

  /** 批次号主键 */
  public static final String PK_BATCHCODE = "pk_batchcode";

  /** 集团 */
  public static final String PK_GROUP = "pk_group";

  public static final String PK_MATERIAL = "pk_material";

  /** 采购订单退货明细查询--主单位 */
  public static final String PK_MEASDOC = "pk_measdoc";

  /** 组织 */
  public static final String PK_ORG = "pk_org";

  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  // 请购单执行明细查询
  /** 请购单主数量 */
  public static final String PRAYBILL_NNUM = "nnum";

  public static final String PRENCLASHESTMONEY = "prenclashestmoney";

  public static final String PRENGOODSMONEY = "prengoodsmoney";

  public static final String PRENSETTLENUM = "prensettlenum";

  public static final String PREUNESTMNY = "preunestmny";

  public static final String PREUNESTNUM = "preunestnum";

  public static final String PURINBACKNNUM = "purbacknnum";

  public static final String PURINMNY = "purnmny";

  public static final String PURINNNUM = "purnnum";

  public static final String RETURNENUM = "returnenum";

  public static final String SETTLEMNY = "settlenmny";

  public static final String SETTLENNUM = "settlennum";

  public static final String TENDUNESTNUM = "tendunestnum";

  public static final String TESTNUM = "testnum";

  /**
   * 带this的物料编码
   */
  public static final String THISSRCMATERIALCODE = "this.pk_srcmaterial.code";

  /**
   * 带this的物料名称
   */
  public static final String THISSRCMATERIALNAME = "this.pk_srcmaterial.name";

  /**
   * 带this的供应商编码
   */
  public static final String THISSUPPLIERCODE = "this.pk_supplier.code";

  /**
   * 带this的供应商名称
   */
  public static final String THISSUPPLIERNAME = "this.pk_supplier.name";

  public static final String TNCLASHESTMONEY = "tnclashestmoney";

  public static final String TNESTMNY = "tnestmny";

  // 供应商暂估明细查询
  public static final String TNESTNUM = "tnestnum";// tnestnum 暂估数量

  public static final String TNGOODSMONEY = "tngoodsmoney";

  public static final String TNSETTLENUM = "tnsettlenum";

  // 供应商暂估余额表/供应商暂估余额查询
  public static final String TUNESTNUM = "tunestnum";

  /** 批次号 */
  public static final String VBATCHCODE = "vbatchcode";
  
  /**可开票金额 */
  public static final String NCANINVOICEMNY = "ncaninvoicemny";

  /** 换算率 */
  public static final String VCHANGERATE = "vchangerate";

  /** 自由辅助属性1 */
  public static final String VFREE1 = "vfree1";

  /** 自由辅助属性10 */
  public static final String VFREE10 = "vfree10";

  /** 自由辅助属性2 */
  public static final String VFREE2 = "vfree2";

  /** 自由辅助属性3 */
  public static final String VFREE3 = "vfree3";

  /** 自由辅助属性4 */
  public static final String VFREE4 = "vfree4";

  /** 自由辅助属性5 */
  public static final String VFREE5 = "vfree5";

  /** 自由辅助属性6 */
  public static final String VFREE6 = "vfree6";

  /** 自由辅助属性7 */
  public static final String VFREE7 = "vfree7";

  /** 自由辅助属性8 */
  public static final String VFREE8 = "vfree8";

  /** 自由辅助属性9 */
  public static final String VFREE9 = "vfree9";

  /** 自由辅助属性 */
  public static final String[] VFREEITEMS = new String[] {
    PUPubMetaNameConst.VFREE1, PUPubMetaNameConst.VFREE2,
    PUPubMetaNameConst.VFREE3, PUPubMetaNameConst.VFREE4,
    PUPubMetaNameConst.VFREE5, PUPubMetaNameConst.VFREE6,
    PUPubMetaNameConst.VFREE7, PUPubMetaNameConst.VFREE8,
    PUPubMetaNameConst.VFREE9, PUPubMetaNameConst.VFREE10
  };

  /** 自由辅助属性的集合字段定义 */
  public static final String[] VFREES = new String[] {
    "vfree1", "vfree2", "vfree3", "vfree4", "vfree5", "vfree6", "vfree7",
    "vfree8", "vfree9", "vfree10"
  };
}
