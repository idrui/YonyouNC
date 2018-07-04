package nc.vo.pu.m27.entity;

import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>采购结算时用于匹配界面(结算第二个界面)显示的VO，是其他结算VO的组合</b>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-17 下午01:16:59
 */
public class MatchMaterialVO extends SuperVO {
  /** 是否费用发票 */
  public static String BFEE = "bfee";

  /** 单据号 */
  public static String BILLCODE = "billcode";

  /** 是否来自发票 */
  public static String BINVOICE = "binvoice";

  /** 是否来自入库单 */
  public static String BSTOCK = "bstock";

  /** 本币币种 */
  public static final String CCURRENCYID = "ccurrencyid";

  /** 生产厂商 */
  public static String CPRODUCTORID = "cproductorid";

  /** 项目 */
  public static String CPROJECTID = "cprojectid";

  /**
   * 交易类型
   */
  public final static String CTRANTYPEID = "ctrantypeid";

  /** 计量单位 */
  public static String CUNITID = "cunitid";

  /** 累计暂估金额 */
  public static String NACCUMESTMNY = "naccumestmny";

  /** 累计暂估数量 */
  public static String NACCUMESTNUM = "naccumestnum";

  // add by liangchen1 进出口
  /** 调整货物金额 */
  public static String NADJUSTMNY = "nadjustmny";

  /** 未结算金额 */
  public static String NCANSETTLEMNY = "ncansettlemny";

  /** 未结算数量 */
  public static String NCANSETTLENUM = "ncansettlenum";

  /** 成本要素1-5 */
  public static String NCOSTFACTOR1 = "ncostfactor1";

  public static String NCOSTFACTOR2 = "ncostfactor2";

  public static String NCOSTFACTOR3 = "ncostfactor3";

  public static String NCOSTFACTOR4 = "ncostfactor4";

  public static String NCOSTFACTOR5 = "ncostfactor5";

  public static String NCOSTFACTOR6 = "ncostfactor6";

  public static String NCOSTFACTOR7 = "ncostfactor7";

  public static String NCOSTFACTOR8 = "ncostfactor8";

  /** 本次发票本币结算金额 */
  public static String NCURINVOICESETTLEMNY = "ncurinvoicesettlemny";

  /** 本次发票结算数量 */
  public static String NCURINVOICESETTLENUM = "ncurinvoicesettlenum";

  /** 本次结算金额 */
  public static String NCURSEETLEMNY = "ncurseetlemny";

  /** 本次入库结算数量 */
  public static String NCURSTOCKSETTLENUM = "ncurstocksettlenum";

  /** 折扣 */
  public static String NDISCOUNT = "ndiscount";

  /** 暂估金额 */
  public static final String NESTMNY = "nestmny";

  /** 暂估数量 */
  public static final String NESTNUM = "nestnum";

  /** 暂估单价 */
  public static final String NESTPRICE = "nestprice";

  /** 未暂估已结算金额 */
  public static String NNOESTSETTLEMNY = "nnoestsettlemny";

  /** 未暂估已结算数量noEstSettleNum */
  public static String NNOESTSETTLENUM = "nnoestsettlenum";

  /** 单价(本币无税单价) */
  public static String NPRICE = "nprice";

  /** 合理损耗数量 */
  public static String NREASONWASTENUM = "nreasonwastenum";

  /** 结算平均单价 */
  public static String NSETTLEAVGPRICE = "nsettleavgprice";

  /** 入库数量 */
  public static String NSTOCKINNUM = "nstockinnum";

  /** 单据子id */
  public static String PK_BILLBID = "pk_billbid";

  /** 单据id */
  public static String PK_BILLID = "pk_billid";

  /** 物料主键 */
  public static String PK_MATERIAL = "pk_material";

  /** 物料OID主键 */
  public static String PK_SRCMATERIAL = "pk_srcmaterial";

  /** 自定义项 */
  public static String VBDEF1 = "vbdef1";

  public static String VBDEF10 = "vbdef10";

  public static String VBDEF11 = "vbdef11";

  public static String VBDEF12 = "vbdef12";

  public static String VBDEF13 = "vbdef13";

  public static String VBDEF14 = "vbdef14";

  public static String VBDEF15 = "vbdef15";

  public static String VBDEF16 = "vbdef16";

  public static String VBDEF17 = "vbdef17";

  public static String VBDEF18 = "vbdef18";

  public static String VBDEF19 = "vbdef19";

  public static String VBDEF2 = "vbdef2";

  public static String VBDEF20 = "vbdef20";

  public static String VBDEF3 = "vbdef3";

  public static String VBDEF4 = "vbdef4";

  public static String VBDEF5 = "vbdef5";

  public static String VBDEF6 = "vbdef6";

  public static String VBDEF7 = "vbdef7";

  public static String VBDEF8 = "vbdef8";

  public static String VBDEF9 = "vbdef9";

  /** 自由项 */
  public static String VFREE1 = "vfree1";

  /** 自由项10 */
  public static String VFREE10 = "vfree10";

  /** 自由项2 */
  public static String VFREE2 = "vfree2";

  /** 自由项3 */
  public static String VFREE3 = "vfree3";

  /** 自由项4 */
  public static String VFREE4 = "vfree4";

  /** 自由项5 */
  public static String VFREE5 = "vfree5";

  /** 自由项6 */
  public static String VFREE6 = "vfree6";

  /** 自由项7 */
  public static String VFREE7 = "vfree7";

  /** 自由项8 */
  public static String VFREE8 = "vfree8";

  /** 自由项9 */
  public static String VFREE9 = "vfree9";

  /** 批次号 */
  public static String VPRODUCENUM = "vproducenum";

  /** 单据类型，显示的是交易类型 */
  public static final String VTRANTYPECODE = InvoiceHeaderVO.VTRANTYPECODE;

  private static final long serialVersionUID = -9138716083211928980L;

  /** 是否费用发票 */
  private UFBoolean bfee;

  /** 单据号 */
  private String billcode;

  /** 是否来自发票 */
  private UFBoolean binvoice;

  /** 是否来自入库单 */
  private UFBoolean bstock;

  /** 本币币种 **/
  private String ccurrencyid;

  private String cproductorid;

  private String cprojectid;

  /** 交易类型PK */
  private String ctrantypeid;

  /** 计量单位 */
  private String cunitid;

  /** 用于组合matchMeterialVO的发票vo */
  private InvoiceSettleVO invoiceSettleVO;

  /** 累计暂估金额 */
  private UFDouble naccumestmny;

  /** 累计暂估数量 */
  private UFDouble naccumestnum;

  /** 折扣 */
  private UFDouble nadjustmny;

  /** 未结算金额 */
  private UFDouble ncansettlemny;

  /** 未结算数量 */
  private UFDouble ncansettlenum;

  /** 成本要素1-5 */
  private UFDouble ncostfactor1;

  private UFDouble ncostfactor2;

  private UFDouble ncostfactor3;

  private UFDouble ncostfactor4;

  private UFDouble ncostfactor5;

  private UFDouble ncostfactor6;

  private UFDouble ncostfactor7;

  private UFDouble ncostfactor8;

  /** 本次发票本币结算金额 */
  private UFDouble ncurinvoicesettlemny;

  /** 本次发票结算数量 */
  private UFDouble ncurinvoicesettlenum;

  /** 本次结算金额 */
  private UFDouble ncurseetlemny;

  /** 本次入库结算数量 */
  private UFDouble ncurstocksettlenum;

  /** 折扣 */
  private UFDouble ndiscount;

  /** 暂估金额 */
  private UFDouble nestmny;

  /** 暂估数量 */
  private UFDouble nestnum;

  /** 暂估单价 */
  private UFDouble nestprice;

  /** 未暂估已结算金额 */
  private UFDouble nnoestsettlemny;

  /** 未暂估已结算数量 noEstSettleNum */
  private UFDouble nnoestsettlenum;

  /** 单价(本币无税单价) */
  private UFDouble nprice;

  /** 合理损耗数量 */
  private UFDouble nreasonwastenum;

  /** 结算平均单价 */
  private UFDouble nsettleavgprice;

  /** 入库数量 */
  private UFDouble nstockinnum;

  /** 单据子id */
  private String pk_billbid;

  /** 单据id */
  private String pk_billid;

  /** 物料主键 */
  private String pk_material;

  /** 进口明细单ID */
  private String pk_order;

  /** 进口明细行ID */
  private String pk_order_b;

  /** 物料OID主键 */
  private String pk_srcmaterial;

  /** 用于组合matchMeterialVO的入库vo */
  private StockSettleVO stockSettleVO;

  /** 自定义项 */
  private String vbdef1;

  private String vbdef10;

  private String vbdef11;

  private String vbdef12;

  private String vbdef13;

  private String vbdef14;

  private String vbdef15;

  private String vbdef16;

  private String vbdef17;

  private String vbdef18;

  private String vbdef19;

  private String vbdef2;

  private String vbdef20;

  private String vbdef3;

  private String vbdef4;

  private String vbdef5;

  private String vbdef6;

  private String vbdef7;

  private String vbdef8;

  private String vbdef9;

  /** 进口合同号 */
  private String vctcode;

  /** 自由项 */
  private String vfree1;

  /** 自由项10 */
  private String vfree10;

  /** 自由项2 */
  private String vfree2;

  /** 自由项3 */
  private String vfree3;

  /** 自由项4 */
  private String vfree4;

  /** 自由项5 */
  private String vfree5;

  /** 自由项6 */
  private String vfree6;

  /** 自由项7 */
  private String vfree7;

  /** 自由项8 */
  private String vfree8;

  /** 自由项9 */
  private String vfree9;

  /** 进口明细单号 */
  private String vordercode;

  /** 批次号 */
  private String vproducenum;

  /** 单据类型，显示的是交易类型 */
  private String vtrantypecode;

  /**
   * MatchMeterialVO 的构造子
   */
  public MatchMaterialVO() {
    // 默认构造器
  }

  public UFBoolean getBfee() {
    return this.bfee;
  }

  public String getBillcode() {
    return this.billcode;
  }

  public UFBoolean getBinvoice() {
    return this.binvoice;
  }

  public UFBoolean getBstock() {
    return this.bstock;
  }

  /** 本币币种 **/
  public String getCcurrencyid() {
    return this.ccurrencyid;
  }

  public String getCproductorid() {
    return this.cproductorid;
  }

  public String getCprojectid() {
    return this.cprojectid;
  }

  /**
   * 交易类型PK
   * 
   * @return
   */
  public String getCtrantypeid() {
    return this.ctrantypeid;
  }

  public String getCunitid() {
    return this.cunitid;
  }

  public InvoiceSettleVO getInvoiceSettleVO() {
    return this.invoiceSettleVO;
  }

  public UFDouble getNaccumestmny() {
    return this.naccumestmny;
  }

  public UFDouble getNaccumestnum() {
    return this.naccumestnum;
  }

  public UFDouble getNadjustmny() {
    return this.nadjustmny;
  }

  public UFDouble getNcansettlemny() {
    return this.ncansettlemny;
  }

  public UFDouble getNcansettlenum() {
    return this.ncansettlenum;
  }

  public UFDouble getNcostfactor1() {
    return this.ncostfactor1;
  }

  public UFDouble getNcostfactor2() {
    return this.ncostfactor2;
  }

  public UFDouble getNcostfactor3() {
    return this.ncostfactor3;
  }

  public UFDouble getNcostfactor4() {
    return this.ncostfactor4;
  }

  public UFDouble getNcostfactor5() {
    return this.ncostfactor5;
  }

  public UFDouble getNcostfactor6() {
    return this.ncostfactor6;
  }

  public UFDouble getNcostfactor7() {
    return this.ncostfactor7;
  }

  public UFDouble getNcostfactor8() {
    return this.ncostfactor8;
  }

  public UFDouble getNcurinvoicesettlemny() {
    return this.ncurinvoicesettlemny;
  }

  public UFDouble getNcurinvoicesettlenum() {
    return this.ncurinvoicesettlenum;
  }

  public UFDouble getNcurseetlemny() {
    return this.ncurseetlemny;
  }

  public UFDouble getNcurstocksettlenum() {
    return this.ncurstocksettlenum;
  }

  public UFDouble getNdiscount() {
    return this.ndiscount;
  }

  public UFDouble getNestmny() {
    return this.nestmny;
  }

  public UFDouble getNestnum() {
    return this.nestnum;
  }

  public UFDouble getNestprice() {
    return this.nestprice;
  }

  public UFDouble getNnoestsettlemny() {
    return this.nnoestsettlemny;
  }

  public UFDouble getNnoestsettlenum() {
    return this.nnoestsettlenum;
  }

  public UFDouble getNprice() {
    return this.nprice;
  }

  public UFDouble getNreasonwastenum() {
    return this.nreasonwastenum;
  }

  public UFDouble getNsettleavgprice() {
    return this.nsettleavgprice;
  }

  public UFDouble getNstockinnum() {
    return this.nstockinnum;
  }

  public String getPk_billbid() {
    return this.pk_billbid;
  }

  public String getPk_billid() {
    return this.pk_billid;
  }

  public String getPk_material() {
    return this.pk_material;
  }

  public String getPk_order() {
    return this.pk_order;
  }

  public String getPk_order_b() {
    return this.pk_order_b;
  }

  public String getPk_srcmaterial() {
    return this.pk_srcmaterial;
  }

  @Override
  public String getPrimaryKey() {
    if (UFBoolean.TRUE.equals(this.getBstock())
        && null != this.getStockSettleVO()) {
      return this.getStockSettleVO().getPrimaryKey();
    }
    else if (UFBoolean.TRUE.equals(this.getBinvoice())
        && null != this.getInvoiceSettleVO()) {
      return this.getInvoiceSettleVO().getPrimaryKey();
    }
    return this.pk_billbid;
  }

  public StockSettleVO getStockSettleVO() {
    return this.stockSettleVO;
  }

  public String getVbdef1() {
    return this.vbdef1;
  }

  public String getVbdef10() {
    return this.vbdef10;
  }

  public String getVbdef11() {
    return this.vbdef11;
  }

  public String getVbdef12() {
    return this.vbdef12;
  }

  public String getVbdef13() {
    return this.vbdef13;
  }

  public String getVbdef14() {
    return this.vbdef14;
  }

  public String getVbdef15() {
    return this.vbdef15;
  }

  public String getVbdef16() {
    return this.vbdef16;
  }

  public String getVbdef17() {
    return this.vbdef17;
  }

  public String getVbdef18() {
    return this.vbdef18;
  }

  public String getVbdef19() {
    return this.vbdef19;
  }

  public String getVbdef2() {
    return this.vbdef2;
  }

  public String getVbdef20() {
    return this.vbdef20;
  }

  public String getVbdef3() {
    return this.vbdef3;
  }

  public String getVbdef4() {
    return this.vbdef4;
  }

  public String getVbdef5() {
    return this.vbdef5;
  }

  public String getVbdef6() {
    return this.vbdef6;
  }

  public String getVbdef7() {
    return this.vbdef7;
  }

  public String getVbdef8() {
    return this.vbdef8;
  }

  public String getVbdef9() {
    return this.vbdef9;
  }

  public String getVctcode() {
    return this.vctcode;
  }

  public String getVfree1() {
    return this.vfree1;
  }

  public String getVfree10() {
    return this.vfree10;
  }

  public String getVfree2() {
    return this.vfree2;
  }

  public String getVfree3() {
    return this.vfree3;
  }

  public String getVfree4() {
    return this.vfree4;
  }

  public String getVfree5() {
    return this.vfree5;
  }

  public String getVfree6() {
    return this.vfree6;
  }

  public String getVfree7() {
    return this.vfree7;
  }

  public String getVfree8() {
    return this.vfree8;
  }

  public String getVfree9() {
    return this.vfree9;
  }

  public String getVordercode() {
    return this.vordercode;
  }

  public String getVproducenum() {
    return this.vproducenum;
  }

  public String getVtrantypecode() {
    return this.vtrantypecode;
  }

  public void setBfee(UFBoolean bfee) {
    this.bfee = bfee;
  }

  public void setBillcode(String billcode) {
    this.billcode = billcode;
  }

  public void setBinvoice(UFBoolean isFromInvoice) {
    this.binvoice = isFromInvoice;
  }

  public void setBstock(UFBoolean isFromStock) {
    this.bstock = isFromStock;
  }

  /** 本币币种 **/
  public void setCcurrencyid(String ccurrencyid) {
    this.ccurrencyid = ccurrencyid;
  }

  public void setCproductorid(String cproductorid) {
    this.cproductorid = cproductorid;
  }

  public void setCprojectid(String cprojectid) {
    this.cprojectid = cprojectid;
  }

  public void setCtrantypeid(String ctrantypeid) {
    this.ctrantypeid = ctrantypeid;
  }

  public void setCunitid(String cunitid) {
    this.cunitid = cunitid;
  }

  public void setInvoiceSettleVO(InvoiceSettleVO invoiceSettleVO) {
    this.invoiceSettleVO = invoiceSettleVO;
  }

  public void setNaccumestmny(UFDouble naccumsettlemny) {
    this.naccumestmny = naccumsettlemny;
  }

  public void setNaccumestnum(UFDouble naccumestnum) {
    this.naccumestnum = naccumestnum;
  }

  public void setNadjustmny(UFDouble nadjustmny) {
    this.nadjustmny = nadjustmny;
  }

  public void setNcansettlemny(UFDouble ncansettlemny) {
    this.ncansettlemny = ncansettlemny;
  }

  public void setNcansettlenum(UFDouble ncansettlenum) {
    this.ncansettlenum = ncansettlenum;
  }

  public void setNcostfactor1(UFDouble ncostfactor1) {
    this.ncostfactor1 = ncostfactor1;
  }

  public void setNcostfactor2(UFDouble ncostfactor2) {
    this.ncostfactor2 = ncostfactor2;
  }

  public void setNcostfactor3(UFDouble ccostfactor3) {
    this.ncostfactor3 = ccostfactor3;
  }

  public void setNcostfactor4(UFDouble ccostfactor4) {
    this.ncostfactor4 = ccostfactor4;
  }

  public void setNcostfactor5(UFDouble ccostfactor5) {
    this.ncostfactor5 = ccostfactor5;
  }

  public void setNcostfactor6(UFDouble ncostfactor6) {
    this.ncostfactor6 = ncostfactor6;
  }

  public void setNcostfactor7(UFDouble ncostfactor7) {
    this.ncostfactor7 = ncostfactor7;
  }

  public void setNcostfactor8(UFDouble ncostfactor8) {
    this.ncostfactor8 = ncostfactor8;
  }

  public void setNcurinvoicesettlemny(UFDouble ncurinvoicesettlemny) {
    this.ncurinvoicesettlemny = ncurinvoicesettlemny;
  }

  public void setNcurinvoicesettlenum(UFDouble ncurinvoicesettlenum) {
    this.ncurinvoicesettlenum = ncurinvoicesettlenum;
  }

  public void setNcurseetlemny(UFDouble ncurseetlemny) {
    this.ncurseetlemny = ncurseetlemny;
  }

  public void setNcurstocksettlenum(UFDouble ncurtocksettlenum) {
    this.ncurstocksettlenum = ncurtocksettlenum;
  }

  public void setNdiscount(UFDouble ndiscount) {
    this.ndiscount = ndiscount;
  }

  public void setNestmny(UFDouble nestmny) {
    this.nestmny = nestmny;
  }

  public void setNestnum(UFDouble nestnum) {
    this.nestnum = nestnum;
  }

  public void setNestprice(UFDouble nestprice) {
    this.nestprice = nestprice;
  }

  public void setNnoestsettlemny(UFDouble nnoestsettlemny) {
    this.nnoestsettlemny = nnoestsettlemny;
  }

  public void setNnoestsettlenum(UFDouble nnoestsettlenum) {
    this.nnoestsettlenum = nnoestsettlenum;
  }

  public void setNprice(UFDouble nprice) {
    this.nprice = nprice;
  }

  public void setNreasonwastenum(UFDouble nreasonwastenum) {
    this.nreasonwastenum = nreasonwastenum;
  }

  public void setNsettleavgprice(UFDouble nsettleavgprice) {
    this.nsettleavgprice = nsettleavgprice;
  }

  public void setNstockinnum(UFDouble nstockinnum) {
    this.nstockinnum = nstockinnum;
  }

  public void setPk_billbid(String pk_billid) {
    this.pk_billbid = pk_billid;
  }

  public void setPk_billid(String pk_billid) {
    this.pk_billid = pk_billid;
  }

  public void setPk_material(String pkMaterial) {
    this.pk_material = pkMaterial;
  }

  public void setPk_order(String pk_order) {
    this.pk_order = pk_order;
  }

  public void setPk_order_b(String pk_order_b) {
    this.pk_order_b = pk_order_b;
  }

  public void setPk_srcmaterial(String pkSrcmaterial) {
    this.pk_srcmaterial = pkSrcmaterial;
  }

  public void setStockSettleVO(StockSettleVO stockSettleVO) {
    this.stockSettleVO = stockSettleVO;
  }

  public void setVbdef1(String vbdef1) {
    this.vbdef1 = vbdef1;
  }

  public void setVbdef10(String vbdef10) {
    this.vbdef10 = vbdef10;
  }

  public void setVbdef11(String vbdef11) {
    this.vbdef11 = vbdef11;
  }

  public void setVbdef12(String vbdef12) {
    this.vbdef12 = vbdef12;
  }

  public void setVbdef13(String vbdef13) {
    this.vbdef13 = vbdef13;
  }

  public void setVbdef14(String vbdef14) {
    this.vbdef14 = vbdef14;
  }

  public void setVbdef15(String vbdef15) {
    this.vbdef15 = vbdef15;
  }

  public void setVbdef16(String vbdef16) {
    this.vbdef16 = vbdef16;
  }

  public void setVbdef17(String vbdef17) {
    this.vbdef17 = vbdef17;
  }

  public void setVbdef18(String vbdef18) {
    this.vbdef18 = vbdef18;
  }

  public void setVbdef19(String vbdef19) {
    this.vbdef19 = vbdef19;
  }

  public void setVbdef2(String vbdef2) {
    this.vbdef2 = vbdef2;
  }

  public void setVbdef20(String vbdef20) {
    this.vbdef20 = vbdef20;
  }

  public void setVbdef3(String vbdef3) {
    this.vbdef3 = vbdef3;
  }

  public void setVbdef4(String vbdef4) {
    this.vbdef4 = vbdef4;
  }

  public void setVbdef5(String vbdef5) {
    this.vbdef5 = vbdef5;
  }

  public void setVbdef6(String vbdef6) {
    this.vbdef6 = vbdef6;
  }

  public void setVbdef7(String vbdef7) {
    this.vbdef7 = vbdef7;
  }

  public void setVbdef8(String vbdef8) {
    this.vbdef8 = vbdef8;
  }

  public void setVbdef9(String vbdef9) {
    this.vbdef9 = vbdef9;
  }

  public void setVctcode(String vctcode) {
    this.vctcode = vctcode;
  }

  public void setVfree1(String vfree1) {
    this.vfree1 = vfree1;
  }

  public void setVfree10(String vfree10) {
    this.vfree10 = vfree10;
  }

  public void setVfree2(String vfree2) {
    this.vfree2 = vfree2;
  }

  public void setVfree3(String vfree3) {
    this.vfree3 = vfree3;
  }

  public void setVfree4(String vfree4) {
    this.vfree4 = vfree4;
  }

  public void setVfree5(String vfree5) {
    this.vfree5 = vfree5;
  }

  public void setVfree6(String vfree6) {
    this.vfree6 = vfree6;
  }

  public void setVfree7(String vfree7) {
    this.vfree7 = vfree7;
  }

  public void setVfree8(String vfree8) {
    this.vfree8 = vfree8;
  }

  public void setVfree9(String vfree9) {
    this.vfree9 = vfree9;
  }

  public void setVordercode(String vordercode) {
    this.vordercode = vordercode;
  }

  public void setVproducenum(String vproducenum) {
    this.vproducenum = vproducenum;
  }

  public void setVtrantypecode(String vtrantypecode) {
    this.vtrantypecode = vtrantypecode;
  }

}
