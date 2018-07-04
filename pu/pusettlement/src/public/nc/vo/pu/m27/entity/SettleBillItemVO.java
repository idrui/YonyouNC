package nc.vo.pu.m27.entity;

import nc.vo.pu.m25.settle.ICostfactorDiscount;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.pubapp.pattern.pub.MathTool;

public class SettleBillItemVO extends SuperVO implements ICostfactorDiscount {
  /** 是否冲销暂估标志 */
  public static final String BWASHEST = "bwashest";

  /** 行号 */
  public static final String CROWNO = "crowno";

  /** 主单位 */
  public static final String CUNITID = "cunitid";

  /** dr */
  public static final String DR = "dr";

  /** 行类型 */
  public static final String FROWTYPE = "frowtype";

  /** 开票日期 */
  public static final String INVOICEBILLDATE = "invoicebilldate";

  /** 调整货物金额 */
  public static final String NADJUSTMNY = "nadjustmny";

  /** 冲销暂估金额 */
  public static final String NCLASHESTMONEY = "nclashestmoney";

  /** 本币成本要素1 */
  public static final String NCOSTFACTOR1 = "ncostfactor1";

  /** 本币成本要素2 */
  public static final String NCOSTFACTOR2 = "ncostfactor2";

  /** 本币成本要素3 */
  public static final String NCOSTFACTOR3 = "ncostfactor3";

  /** 本币成本要素4 */
  public static final String NCOSTFACTOR4 = "ncostfactor4";

  /** 本币成本要素5 */
  public static final String NCOSTFACTOR5 = "ncostfactor5";

  /** 本币成本要素6 */
  public static final String NCOSTFACTOR6 = "ncostfactor6";

  /** 本币成本要素7 */
  public static final String NCOSTFACTOR7 = "ncostfactor7";

  /** 本币成本要素8 */
  public static final String NCOSTFACTOR8 = "ncostfactor8";

  /** 折扣 */
  public static final String NDISCOUNT = "ndiscount";

  /** 货物结算金额 */
  public static final String NGOODSMONEY = "ngoodsmoney";

  /** 货物结算单价 */
  public static final String NGOODSPRICE = "ngoodsprice";

  /** 金额 */
  public static final String NMONEY = "nmoney";

  /** 相应的确认应付原币价税合计 */
  public static final String NOPPOCONFMAPMNY = "noppoconfmapmny";

  /** 相应的确认成本 */
  public static final String NOPPOCONFMMONEY = "noppoconfmmoney";

  /** 单价 */
  public static final String NPRICE = "nprice";

  /** 合理损耗金额 */
  public static final String NREASONALWASTMNY = "nreasonalwastmny";

  /** 合理损耗数量 */
  public static final String NREASONALWASTNUM = "nreasonalwastnum";

  /** 合理损耗单价 */
  public static final String NREASONALWASTPRICE = "nreasonalwastprice";

  /** 结算数量 */
  public static final String NSETTLENUM = "nsettlenum";

  /** 收货库存组织 */
  public static final String PK_ARRSTOCKORG = "pk_arrstockorg";

  /** 收货库存组织版本 */
  public static final String PK_ARRSTOCKORG_V = "pk_arrstockorg_v";

  /** 成本域 */
  public static final String PK_COSTREGION = "pk_costregion";

  /** 采购部门原始信息 */
  public static final String PK_DEPT = "pk_dept";

  /** 采购部门 */
  public static final String PK_DEPT_V = "pk_dept_v";

  /** 相应的直运销售订单行 */
  public static final String PK_DTRANSALEBID = "pk_dtransalebid";

  /** 相应的直运销售订单 */
  public static final String PK_DTRANSALEID = "pk_dtransaleid";

  /** 其他入表头ID */
  public static final String PK_GENERALIN = "pk_generalin";

  /** 其他入表体ID */
  public static final String PK_GENERALIN_B = "pk_generalin_b";

  /** 所属集团 */
  public static final String PK_GROUP = "pk_group";

  /** 期初暂估表头ID */
  public static final String PK_INITIALEST = "pk_initialest";

  /** 期初暂估表体ID */
  public static final String PK_INITIALEST_B = "pk_initialest_b";

  /** 发票 */
  public static final String PK_INVOICE = "pk_invoice";

  /** 发票行 */
  public static final String PK_INVOICE_B = "pk_invoice_b";

  /** 发票的订单头 */
  public static final String PK_INVOICEORDER = "pk_invoiceorder";

  /** 发票的订单体 */
  public static final String PK_INVOICEORDER_B = "pk_invoiceorder_b";

  /** 物料版本 */
  public static final String PK_MATERIAL = "pk_material";

  /** 财务组织 */
  public static final String PK_ORG = "pk_org";

  /** 财务组织版本 */
  public static final String PK_ORG_V = "pk_org_v";

  /** 采购员 */
  public static final String PK_PSNDOC = "pk_psndoc";

  /** 采购入表头ID */
  public static final String PK_PURCHASEIN = "pk_purchasein";

  /** 采购入表体ID */
  public static final String PK_PURCHASEIN_B = "pk_purchasein_b";

  /** 对冲的发票 */
  public static final String PK_RUSHINVOICE = "pk_rushinvoice";

  /** 对冲的发票行 */
  public static final String PK_RUSHINVOICE_B = "pk_rushinvoice_b";

  /** 对冲的库存单据 */
  public static final String PK_RUSHSTOCK = "pk_rushstock";

  /** 对冲的库存单据行 */
  public static final String PK_RUSHSTOCK_B = "pk_rushstock_b";

  /** 费用分摊明细主键 */
  public static final String PK_SETTLE_FEEALLOT = "pk_settle_feeallot";

  /** 结算单明细 */
  public static final String PK_SETTLEBILL = "pk_settlebill";

  /** 结算明细 */
  public static final String PK_SETTLEBILL_B = "pk_settlebill_b";

  /** 物料 */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** 库存单据 */
  public static final String PK_STOCK = "pk_stock";

  /** 库存单据行 */
  public static final String PK_STOCK_B = "pk_stock_b";

  /** 库存单据的订单头 */
  public static final String PK_STOCKORDER = "pk_stockorder";

  /** 库存单据的订单体 */
  public static final String PK_STOCKORDER_B = "pk_stockorder_b";

  /** 仓库 */
  public static final String PK_STORDOC = "pk_stordoc";

  /** 委外入表头ID */
  public static final String PK_SUBCONTRACT = "pk_subcontract";

  /** 委外入表体ID */
  public static final String PK_SUBCONTRACT_B = "pk_subcontract_b";

  /** 供应商 */
  public static final String PK_SUPPLIER = "pk_supplier";

  /** 调拨入表头ID */
  public static final String PK_TRANSIN = "pk_transin";

  /** 调拨入表体ID */
  public static final String PK_TRANSIN_B = "pk_transin_b";

  /** 消耗汇总表头ID */
  public static final String PK_VOICONSUME = "pk_voiconsume";

  /** 消耗汇总表体ID */
  public static final String PK_VOICONSUME_B = "pk_voiconsume_b";

  /** 入库日期 */
  public static final String STOCKBILLDATE = "stockbilldate";

  /** ts */
  public static final String TS = "ts";

  /** 发票号 */
  public static final String VINVOICECODE = "vinvoicecode";

  /** 发票交易类型 */
  public static final String VINVOICETRANTYPE = "vinvoicetrantype";

  /** 库存单据类型 */
  public static final String VSTOCKBILLTYPE = "vstockbilltype";

  /** 库存单据号 */
  public static final String VSTOCKCODE = "vstockcode";

  /** 库存交易类型 */
  public static final String VSTOCKTRANTYPE = "vstocktrantype";
  
  /** 进口合同号 */
  public static final String VITCTCODE = "vitctcode";

  private static final long serialVersionUID = -1914800029666610204L;

  @Override
  public void addtoCurrenttotalsettlemoney(UFDouble dValue) {
    this.setNmoney(MathTool.add(this.getNmoney(), dValue));
  }

  /** 是否冲销暂估标志 getter 方法 */
  public UFBoolean getBwashest() {
    return (UFBoolean) this.getAttributeValue(SettleBillItemVO.BWASHEST);
  }

  /** 行号 getter 方法 */
  public String getCrowno() {
    return (String) this.getAttributeValue(SettleBillItemVO.CROWNO);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(SettleBillItemVO.CUNITID);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(SettleBillItemVO.DR);
  }

  /** 行类型 getter 方法 */
  public Integer getFrowtype() {
    return (Integer) this.getAttributeValue(SettleBillItemVO.FROWTYPE);
  }

  /** 开票日期 **/
  public UFDate getInvoicebilldate() {
    return (UFDate) this.getAttributeValue(SettleBillItemVO.INVOICEBILLDATE);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.SettleBill_B);
    return meta;
  }

  /** 调整货物金额 getter 方法 */
  @Override
  public UFDouble getNadjustmny() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NADJUSTMNY);
  }

  @Override
  public UFDouble getNallotmoney() {
    return null;
  }

  @Override
  public UFDouble getNallotnum() {
    return null;
  }

  /**
   * 冲销暂估金额 getter 方法<br>
   * 零代表有暂估回冲,只不过回冲金额为零（取精度时可能出现的情况），而空则无暂估回冲<br>
   * 用于判断是暂估前结算，还是暂估后结算
   */
  public UFDouble getNclashestmoney() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCLASHESTMONEY);
  }

  /** 本币成本要素1 getter 方法 */
  @Override
  public UFDouble getNcostfactor1() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCOSTFACTOR1);
  }

  /** 本币成本要素2 getter 方法 */
  @Override
  public UFDouble getNcostfactor2() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCOSTFACTOR2);
  }

  /** 本币成本要素3 getter 方法 */
  @Override
  public UFDouble getNcostfactor3() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCOSTFACTOR3);
  }

  /** 本币成本要素4 getter 方法 */
  @Override
  public UFDouble getNcostfactor4() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCOSTFACTOR4);
  }

  /** 本币成本要素5 getter 方法 */
  @Override
  public UFDouble getNcostfactor5() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCOSTFACTOR5);
  }

  /** 本币成本要素6 getter 方法 */
  @Override
  public UFDouble getNcostfactor6() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCOSTFACTOR6);
  }

  /** 本币成本要素7 getter 方法 */
  @Override
  public UFDouble getNcostfactor7() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCOSTFACTOR7);
  }

  /** 本币成本要素8 getter 方法 */
  @Override
  public UFDouble getNcostfactor8() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NCOSTFACTOR8);
  }

  /** 折扣 getter 方法 */
  @Override
  public UFDouble getNdiscount() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NDISCOUNT);
  }

  /** 货物结算金额 getter 方法 */
  public UFDouble getNgoodsmoney() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NGOODSMONEY);
  }

  /** 货物结算单价 getter 方法 */
  public UFDouble getNgoodsprice() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NGOODSPRICE);
  }

  /** 金额 getter 方法 */
  public UFDouble getNmoney() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NMONEY);
  }

  /** 相应的确认应付原币价税合计 getter 方法 */
  public UFDouble getNoppoconfmapmny() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NOPPOCONFMAPMNY);
  }

  /** 相应的确认成本 getter 方法 */
  public UFDouble getNoppoconfmmoney() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NOPPOCONFMMONEY);
  }

  /** 单价 getter 方法 */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NPRICE);
  }

  /** 合理损耗金额 getter 方法 */
  public UFDouble getNreasonalwastmny() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NREASONALWASTMNY);
  }

  /** 合理损耗数量 getter 方法 */
  public UFDouble getNreasonalwastnum() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NREASONALWASTNUM);
  }

  /** 合理损耗单价 getter 方法 */
  public UFDouble getNreasonalwastprice() {
    return (UFDouble) this
        .getAttributeValue(SettleBillItemVO.NREASONALWASTPRICE);
  }

  /** 结算数量 getter 方法 */
  public UFDouble getNsettlenum() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NSETTLENUM);
  }

  /** 收货库存组织 getter 方法 */
  public String getPk_arrstockorg() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_ARRSTOCKORG);
  }

  /** 收货库存组织版本 getter 方法 */
  public String getPk_arrstockorg_v() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_ARRSTOCKORG_V);
  }

  /** 成本域 getter 方法 */
  public String getPk_costregion() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_COSTREGION);
  }

  /** 采购部门原始信息 getter 方法 */
  public String getPk_dept() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_DEPT);
  }

  /** 采购部门 getter 方法 */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_DEPT_V);
  }

  /** 相应的直运销售订单行 getter 方法 */
  public String getPk_dtransalebid() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_DTRANSALEBID);
  }

  /** 相应的直运销售订单 getter 方法 */
  public String getPk_dtransaleid() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_DTRANSALEID);
  }

  /** 其他入表头ID getter 方法 */
  public String getPk_generalin() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_GENERALIN);
  }

  /** 其他入表体ID getter 方法 */
  public String getPk_generalin_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_GENERALIN_B);
  }

  /** 所属集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_GROUP);
  }

  /** 期初暂估表头ID getter 方法 */
  public String getPk_initialest() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_INITIALEST);
  }

  /** 期初暂估表体ID getter 方法 */
  public String getPk_initialest_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_INITIALEST_B);
  }

  /** 发票 getter 方法 */
  public String getPk_invoice() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_INVOICE);
  }

  /** 发票行 getter 方法 */
  public String getPk_invoice_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_INVOICE_B);
  }

  /** 发票的订单头 getter 方法 */
  public String getPk_invoiceorder() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_INVOICEORDER);
  }

  /** 发票的订单体 getter 方法 */
  public String getPk_invoiceorder_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_INVOICEORDER_B);
  }

  /** 物料版本 getter 方法 */
  public String getPk_material() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_MATERIAL);
  }

  /** 财务组织 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_ORG);
  }

  /** 财务组织版本 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_ORG_V);
  }

  /** 采购员 getter 方法 */
  public String getPk_psndoc() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_PSNDOC);
  }

  /** 采购入表头ID getter 方法 */
  public String getPk_purchasein() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_PURCHASEIN);
  }

  /** 采购入表体ID getter 方法 */
  public String getPk_purchasein_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_PURCHASEIN_B);
  }

  /** 对冲的发票 getter 方法 */
  public String getPk_rushinvoice() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_RUSHINVOICE);
  }

  /** 对冲的发票行 getter 方法 */
  public String getPk_rushinvoice_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_RUSHINVOICE_B);
  }

  /** 对冲的库存单据 getter 方法 */
  public String getPk_rushstock() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_RUSHSTOCK);
  }

  /** 对冲的库存单据行 getter 方法 */
  public String getPk_rushstock_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_RUSHSTOCK_B);
  }

  /** 费用分摊明细主键 getter 方法 */
  public String getPk_settle_feeallot() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_SETTLE_FEEALLOT);
  }

  /** 结算单明细 getter 方法 */
  public String getPk_settlebill() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_SETTLEBILL);
  }

  /** 结算明细 getter 方法 */
  public String getPk_settlebill_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_SETTLEBILL_B);
  }

  /** 物料 getter 方法 */
  @Override
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_SRCMATERIAL);
  }

  /** 库存单据 getter 方法 */
  public String getPk_stock() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_STOCK);
  }

  /** 库存单据行 getter 方法 */
  public String getPk_stock_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_STOCK_B);
  }

  /** 库存单据的订单头 getter 方法 */
  public String getPk_stockorder() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_STOCKORDER);
  }

  /** 库存单据的订单体 getter 方法 */
  public String getPk_stockorder_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_STOCKORDER_B);
  }

  /** 仓库 getter 方法 */
  public String getPk_stordoc() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_STORDOC);
  }

  /** 委外入表头ID getter 方法 */
  public String getPk_subcontract() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_SUBCONTRACT);
  }

  /** 委外入表体ID getter 方法 */
  public String getPk_subcontract_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_SUBCONTRACT_B);
  }

  /** 供应商 getter 方法 */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_SUPPLIER);
  }

  /** 调拨入表头ID getter 方法 */
  public String getPk_transin() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_TRANSIN);
  }

  /** 调拨入表体ID getter 方法 */
  public String getPk_transin_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_TRANSIN_B);
  }

  /** 消耗汇总表头ID getter 方法 */
  public String getPk_voiconsume() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_VOICONSUME);
  }

  /** 消耗汇总表体ID getter 方法 */
  public String getPk_voiconsume_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_VOICONSUME_B);
  }

  /** 入库日期 **/
  public UFDate getStockbilldate() {
    return (UFDate) this.getAttributeValue(SettleBillItemVO.STOCKBILLDATE);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SettleBillItemVO.TS);
  }

  /** 发票号 getter 方法 */
  public String getVinvoicecode() {
    return (String) this.getAttributeValue(SettleBillItemVO.VINVOICECODE);
  }

  /** 发票交易类型 getter 方法 */
  public String getVinvoicetrantype() {
    return (String) this.getAttributeValue(SettleBillItemVO.VINVOICETRANTYPE);
  }

  /** 库存单据类型 getter 方法 */
  public String getVstockbilltype() {
    return (String) this.getAttributeValue(SettleBillItemVO.VSTOCKBILLTYPE);
  }

  /** 库存单据号 getter 方法 */
  public String getVstockcode() {
    return (String) this.getAttributeValue(SettleBillItemVO.VSTOCKCODE);
  }

  /** 库存交易类型 getter 方法 */
  public String getVstocktrantype() {
    return (String) this.getAttributeValue(SettleBillItemVO.VSTOCKTRANTYPE);
  }
  
  /** 进口合同号 getter 方法 */
  public String getVitctcode() {
	    return (String) this.getAttributeValue(SettleBillItemVO.VITCTCODE);
	  }
  
  

  /** 是否冲销暂估标志 setter 方法 */
  public void setBwashest(UFBoolean bwashest) {
    this.setAttributeValue(SettleBillItemVO.BWASHEST, bwashest);
  }

  /** 行号 setter 方法 */
  public void setCrowno(String crowno) {
    this.setAttributeValue(SettleBillItemVO.CROWNO, crowno);
  }

  /** 主单位 setter 方法 */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(SettleBillItemVO.CUNITID, cunitid);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(SettleBillItemVO.DR, dr);
  }

  /** 行类型 setter 方法 */
  public void setFrowtype(Integer frowtype) {
    this.setAttributeValue(SettleBillItemVO.FROWTYPE, frowtype);
  }

  /** 开票日期 **/
  public void setInvoicebilldate(UFDate invoicebilldate) {
    this.setAttributeValue(SettleBillItemVO.INVOICEBILLDATE, invoicebilldate);
  }

  /** 调整货物金额 setter 方法 */
  @Override
  public void setNadjustmny(UFDouble nadjustmny) {
    this.setAttributeValue(SettleBillItemVO.NADJUSTMNY, nadjustmny);
  }

  /**
   * 冲销暂估金额 setter 方法 <br>
   * 零代表有暂估回冲,只不过回冲金额为零（取精度时可能出现的情况），而空则无暂估回冲<br>
   * 用于判断是暂估前结算，还是暂估后结算
   */
  public void setNclashestmoney(UFDouble nclashestmoney) {
    this.setAttributeValue(SettleBillItemVO.NCLASHESTMONEY, nclashestmoney);
  }

  /** 本币成本要素1 setter 方法 */
  @Override
  public void setNcostfactor1(UFDouble ncostfactor1) {
    this.setAttributeValue(SettleBillItemVO.NCOSTFACTOR1, ncostfactor1);
  }

  /** 本币成本要素2 setter 方法 */
  @Override
  public void setNcostfactor2(UFDouble ncostfactor2) {
    this.setAttributeValue(SettleBillItemVO.NCOSTFACTOR2, ncostfactor2);
  }

  /** 本币成本要素3 setter 方法 */
  @Override
  public void setNcostfactor3(UFDouble ncostfactor3) {
    this.setAttributeValue(SettleBillItemVO.NCOSTFACTOR3, ncostfactor3);
  }

  /** 本币成本要素4 setter 方法 */
  @Override
  public void setNcostfactor4(UFDouble ncostfactor4) {
    this.setAttributeValue(SettleBillItemVO.NCOSTFACTOR4, ncostfactor4);
  }

  /** 本币成本要素5 setter 方法 */
  @Override
  public void setNcostfactor5(UFDouble ncostfactor5) {
    this.setAttributeValue(SettleBillItemVO.NCOSTFACTOR5, ncostfactor5);
  }

  /** 本币成本要素6 setter 方法 */
  @Override
  public void setNcostfactor6(UFDouble ncostfactor6) {
    this.setAttributeValue(SettleBillItemVO.NCOSTFACTOR6, ncostfactor6);
  }

  /** 本币成本要素7 setter 方法 */
  @Override
  public void setNcostfactor7(UFDouble ncostfactor7) {
    this.setAttributeValue(SettleBillItemVO.NCOSTFACTOR7, ncostfactor7);
  }

  /** 本币成本要素8 setter 方法 */
  @Override
  public void setNcostfactor8(UFDouble ncostfactor8) {
    this.setAttributeValue(SettleBillItemVO.NCOSTFACTOR8, ncostfactor8);
  }

  /** 折扣 setter 方法 */
  @Override
  public void setNdiscount(UFDouble ndiscount) {
    this.setAttributeValue(SettleBillItemVO.NDISCOUNT, ndiscount);
  }

  /** 货物结算金额 setter 方法 */
  public void setNgoodsmoney(UFDouble ngoodsmoney) {
    this.setAttributeValue(SettleBillItemVO.NGOODSMONEY, ngoodsmoney);
  }

  /** 货物结算单价 setter 方法 */
  public void setNgoodsprice(UFDouble ngoodsprice) {
    this.setAttributeValue(SettleBillItemVO.NGOODSPRICE, ngoodsprice);
  }

  /** 金额 setter 方法 */
  public void setNmoney(UFDouble nmoney) {
    this.setAttributeValue(SettleBillItemVO.NMONEY, nmoney);
  }

  /** 相应的确认应付原币价税合计 setter 方法 */
  public void setNoppoconfmapmny(UFDouble noppoconfmapmny) {
    this.setAttributeValue(SettleBillItemVO.NOPPOCONFMAPMNY, noppoconfmapmny);
  }

  /** 相应的确认成本 setter 方法 */
  public void setNoppoconfmmoney(UFDouble noppoconfmmoney) {
    this.setAttributeValue(SettleBillItemVO.NOPPOCONFMMONEY, noppoconfmmoney);
  }

  /** 单价 setter 方法 */
  public void setNprice(UFDouble nprice) {
    this.setAttributeValue(SettleBillItemVO.NPRICE, nprice);
  }

  /** 合理损耗金额 setter 方法 */
  public void setNreasonalwastmny(UFDouble nreasonalwastmny) {
    this.setAttributeValue(SettleBillItemVO.NREASONALWASTMNY, nreasonalwastmny);
  }

  /** 合理损耗数量 setter 方法 */
  public void setNreasonalwastnum(UFDouble nreasonalwastnum) {
    this.setAttributeValue(SettleBillItemVO.NREASONALWASTNUM, nreasonalwastnum);
  }

  /** 合理损耗单价 setter 方法 */
  public void setNreasonalwastprice(UFDouble nreasonalwastprice) {
    this.setAttributeValue(SettleBillItemVO.NREASONALWASTPRICE,
        nreasonalwastprice);
  }

  /** 结算数量 setter 方法 */
  public void setNsettlenum(UFDouble nsettlenum) {
    this.setAttributeValue(SettleBillItemVO.NSETTLENUM, nsettlenum);
  }

  /** 收货库存组织 setter 方法 */
  public void setPk_arrstockorg(String pk_arrstockorg) {
    this.setAttributeValue(SettleBillItemVO.PK_ARRSTOCKORG, pk_arrstockorg);
  }

  /** 收货库存组织版本 setter 方法 */
  public void setPk_arrstockorg_v(String pk_arrstockorg_v) {
    this.setAttributeValue(SettleBillItemVO.PK_ARRSTOCKORG_V, pk_arrstockorg_v);
  }

  /** 成本域 setter 方法 */
  public void setPk_costregion(String pk_costregion) {
    this.setAttributeValue(SettleBillItemVO.PK_COSTREGION, pk_costregion);
  }

  /** 采购部门原始信息 setter 方法 */
  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(SettleBillItemVO.PK_DEPT, pk_dept);
  }

  /** 采购部门 setter 方法 */
  public void setPk_dept_v(String pk_dept_v) {
    this.setAttributeValue(SettleBillItemVO.PK_DEPT_V, pk_dept_v);
  }

  /** 相应的直运销售订单行 setter 方法 */
  public void setPk_dtransalebid(String pk_dtransalebid) {
    this.setAttributeValue(SettleBillItemVO.PK_DTRANSALEBID, pk_dtransalebid);
  }

  /** 相应的直运销售订单 setter 方法 */
  public void setPk_dtransaleid(String pk_dtransaleid) {
    this.setAttributeValue(SettleBillItemVO.PK_DTRANSALEID, pk_dtransaleid);
  }

  /** 其他入表头ID setter 方法 */
  public void setPk_generalin(String pk_generalin) {
    this.setAttributeValue(SettleBillItemVO.PK_GENERALIN, pk_generalin);
  }

  /** 其他入表体ID setter 方法 */
  public void setPk_generalin_b(String pk_generalin_b) {
    this.setAttributeValue(SettleBillItemVO.PK_GENERALIN_B, pk_generalin_b);
  }

  /** 所属集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SettleBillItemVO.PK_GROUP, pk_group);
  }

  /** 期初暂估表头ID setter 方法 */
  public void setPk_initialest(String pk_initialest) {
    this.setAttributeValue(SettleBillItemVO.PK_INITIALEST, pk_initialest);
  }

  /** 期初暂估表体ID setter 方法 */
  public void setPk_initialest_b(String pk_initialest_b) {
    this.setAttributeValue(SettleBillItemVO.PK_INITIALEST_B, pk_initialest_b);
  }

  /** 发票 setter 方法 */
  public void setPk_invoice(String pk_invoice) {
    this.setAttributeValue(SettleBillItemVO.PK_INVOICE, pk_invoice);
  }

  /** 发票行 setter 方法 */
  public void setPk_invoice_b(String pk_invoice_b) {
    this.setAttributeValue(SettleBillItemVO.PK_INVOICE_B, pk_invoice_b);
  }

  /** 发票的订单头 setter 方法 */
  public void setPk_invoiceorder(String pk_invoiceorder) {
    this.setAttributeValue(SettleBillItemVO.PK_INVOICEORDER, pk_invoiceorder);
  }

  /** 发票的订单体 setter 方法 */
  public void setPk_invoiceorder_b(String pk_invoiceorder_b) {
    this.setAttributeValue(SettleBillItemVO.PK_INVOICEORDER_B,
        pk_invoiceorder_b);
  }

  /** 物料版本 setter 方法 */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(SettleBillItemVO.PK_MATERIAL, pk_material);
  }

  /** 财务组织 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SettleBillItemVO.PK_ORG, pk_org);
  }

  /** 财务组织版本 setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SettleBillItemVO.PK_ORG_V, pk_org_v);
  }

  /** 采购员 setter 方法 */
  public void setPk_psndoc(String pk_psndoc) {
    this.setAttributeValue(SettleBillItemVO.PK_PSNDOC, pk_psndoc);
  }

  /** 采购入表头ID setter 方法 */
  public void setPk_purchasein(String pk_purchasein) {
    this.setAttributeValue(SettleBillItemVO.PK_PURCHASEIN, pk_purchasein);
  }

  /** 采购入表体ID setter 方法 */
  public void setPk_purchasein_b(String pk_purchasein_b) {
    this.setAttributeValue(SettleBillItemVO.PK_PURCHASEIN_B, pk_purchasein_b);
  }

  /** 对冲的发票 setter 方法 */
  public void setPk_rushinvoice(String pk_rushinvoice) {
    this.setAttributeValue(SettleBillItemVO.PK_RUSHINVOICE, pk_rushinvoice);
  }

  /** 对冲的发票行 setter 方法 */
  public void setPk_rushinvoice_b(String pk_rushinvoice_b) {
    this.setAttributeValue(SettleBillItemVO.PK_RUSHINVOICE_B, pk_rushinvoice_b);
  }

  /** 对冲的库存单据 setter 方法 */
  public void setPk_rushstock(String pk_rushstock) {
    this.setAttributeValue(SettleBillItemVO.PK_RUSHSTOCK, pk_rushstock);
  }

  /** 对冲的库存单据行 setter 方法 */
  public void setPk_rushstock_b(String pk_rushstock_b) {
    this.setAttributeValue(SettleBillItemVO.PK_RUSHSTOCK_B, pk_rushstock_b);
  }

  /** 费用分摊明细主键 setter 方法 */
  public void setPk_settle_feeallot(String pk_settle_feeallot) {
    this.setAttributeValue(SettleBillItemVO.PK_SETTLE_FEEALLOT,
        pk_settle_feeallot);
  }

  /** 结算单明细 setter 方法 */
  public void setPk_settlebill(String pk_settlebill) {
    this.setAttributeValue(SettleBillItemVO.PK_SETTLEBILL, pk_settlebill);
  }

  /** 结算明细 setter 方法 */
  public void setPk_settlebill_b(String pk_settlebill_b) {
    this.setAttributeValue(SettleBillItemVO.PK_SETTLEBILL_B, pk_settlebill_b);
  }

  /** 物料 setter 方法 */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(SettleBillItemVO.PK_SRCMATERIAL, pk_srcmaterial);
  }

  /** 库存单据 setter 方法 */
  public void setPk_stock(String pk_stock) {
    this.setAttributeValue(SettleBillItemVO.PK_STOCK, pk_stock);
  }

  /** 库存单据行 setter 方法 */
  public void setPk_stock_b(String pk_stock_b) {
    this.setAttributeValue(SettleBillItemVO.PK_STOCK_B, pk_stock_b);
  }

  /** 库存单据的订单头 setter 方法 */
  public void setPk_stockorder(String pk_stockorder) {
    this.setAttributeValue(SettleBillItemVO.PK_STOCKORDER, pk_stockorder);
  }

  /** 库存单据的订单体 setter 方法 */
  public void setPk_stockorder_b(String pk_stockorder_b) {
    this.setAttributeValue(SettleBillItemVO.PK_STOCKORDER_B, pk_stockorder_b);
  }

  /** 仓库 setter 方法 */
  public void setPk_stordoc(String pk_stordoc) {
    this.setAttributeValue(SettleBillItemVO.PK_STORDOC, pk_stordoc);
  }

  /** 委外入表头ID setter 方法 */
  public void setPk_subcontract(String pk_subcontract) {
    this.setAttributeValue(SettleBillItemVO.PK_SUBCONTRACT, pk_subcontract);
  }

  /** 委外入表体ID setter 方法 */
  public void setPk_subcontract_b(String pk_subcontract_b) {
    this.setAttributeValue(SettleBillItemVO.PK_SUBCONTRACT_B, pk_subcontract_b);
  }

  /** 供应商 setter 方法 */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(SettleBillItemVO.PK_SUPPLIER, pk_supplier);
  }

  /** 调拨入表头ID setter 方法 */
  public void setPk_transin(String pk_transin) {
    this.setAttributeValue(SettleBillItemVO.PK_TRANSIN, pk_transin);
  }

  /** 调拨入表体ID setter 方法 */
  public void setPk_transin_b(String pk_transin_b) {
    this.setAttributeValue(SettleBillItemVO.PK_TRANSIN_B, pk_transin_b);
  }

  /** 消耗汇总表头ID setter 方法 */
  public void setPk_voiconsume(String pk_voiconsume) {
    this.setAttributeValue(SettleBillItemVO.PK_VOICONSUME, pk_voiconsume);
  }

  /** 消耗汇总表体ID setter 方法 */
  public void setPk_voiconsume_b(String pk_voiconsume_b) {
    this.setAttributeValue(SettleBillItemVO.PK_VOICONSUME_B, pk_voiconsume_b);
  }

  /** 入库日期 **/
  public void setStockbilldate(UFDate stockbilldate) {
    this.setAttributeValue(SettleBillItemVO.STOCKBILLDATE, stockbilldate);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SettleBillItemVO.TS, ts);
  }

  /** 发票号 setter 方法 */
  public void setVinvoicecode(String vinvoicecode) {
    this.setAttributeValue(SettleBillItemVO.VINVOICECODE, vinvoicecode);
  }

  /** 发票交易类型 setter 方法 */
  public void setVinvoicetrantype(String vinvoicetrantype) {
    this.setAttributeValue(SettleBillItemVO.VINVOICETRANTYPE, vinvoicetrantype);
  }

  /** 库存单据类型 setter 方法 */
  public void setVstockbilltype(String vstockbilltype) {
    this.setAttributeValue(SettleBillItemVO.VSTOCKBILLTYPE, vstockbilltype);
  }

  /** 库存单据号 setter 方法 */
  public void setVstockcode(String vstockcode) {
    this.setAttributeValue(SettleBillItemVO.VSTOCKCODE, vstockcode);
  }

  /** 库存交易类型 setter 方法 */
  public void setVstocktrantype(String vstocktrantype) {
    this.setAttributeValue(SettleBillItemVO.VSTOCKTRANTYPE, vstocktrantype);
  }
  
  /** 进口合同号 setter 方法 */
  public void setVitctcode(String vitctcode) {
    this.setAttributeValue(SettleBillItemVO.VITCTCODE, vitctcode);
  }
  

}
