package nc.vo.pu.m25.settle;

import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.pub.MathTool;

public class InvoiceSettleVO extends AbstractDataView implements
    ICostfactorDiscount {

  public static final String BAPFLAG = InvoiceHeaderVO.BAPFLAG;

  public static final String BFEE = InvoiceHeaderVO.BFEE;

  /** 本币币种 **/
  public static final String CCURRENCYID = InvoiceHeaderVO.CCURRENCYID;
  
  /** 发票日期 **/
  public static final String DBILLDATE = InvoiceHeaderVO.DBILLDATE;
  /**
   * 发票日期
   * 
   * @param dbilldate
   */
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(InvoiceSettleVO.DBILLDATE, dbilldate);
  }
  /**
   * 发票日期
   * 
   * @return
   */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(InvoiceSettleVO.DBILLDATE);
  }

  /** 源头单据分录标识 */
  public static final String CFIRSTBID = InvoiceItemVO.CFIRSTBID;

  /** 源头单据标识 */
  public static final String CFIRSTID = InvoiceItemVO.CFIRSTID;

  /** 源头单据类型 */
  public static final String CFIRSTTYPECODE = InvoiceItemVO.CFIRSTTYPECODE;

  /** 原币币种 **/
  public static final String CORIGCURRENCYID = InvoiceHeaderVO.CORIGCURRENCYID;

  public static final String CPRODUCTORID = InvoiceItemVO.CPRODUCTORID;

  public static final String CPROJECTID = InvoiceItemVO.CPROJECTID;

  /** 行号 **/
  public static final String CROWNO = InvoiceItemVO.CROWNO;

  /** 来源单据分录标识 */
  public static final String CSOURCEBID = InvoiceItemVO.CSOURCEBID;

  /** 来源单据标识 */
  public static final String CSOURCEID = InvoiceItemVO.CSOURCEID;

  /** 来源单据类型 */
  public static final String CSOURCETYPECODE = InvoiceItemVO.CSOURCETYPECODE;

  /**
   * 交易类型
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  public static final String CUNITID = InvoiceItemVO.CUNITID;

  /** 发票归属 **/
  public static final String FINVOICETYPE = "finvoicetype";

  // 发票行类型，对应枚举类InvoiceRowType(0:货物行，1：折扣行，2:劳务行，3：零数量行)
  public static final String FROWTYPE = InvoiceItemVO.FROWTYPE;

  public static final String NACCUMSETTMNY = InvoiceItemVO.NACCUMSETTMNY;

  public static final String NACCUMSETTNUM = InvoiceItemVO.NACCUMSETTNUM;

  /** 可结算金额 */
  public static final String NCANSETTLEMNY = "ncansettlemny";

  /** 可结算数量 */
  public static final String NCANSETTLENUM = "ncansettlenum";

  /** 本次发票结算金额 */
  public static final String NCURRENTINVOICESETTLEMNY =
      "ncurrentinvoicesettlemny";

  /** 本次结算数量 */
  public static final String NCURRENTSETTLENUM = "ncurrentsettlenum";

  public static final String NMNY = InvoiceItemVO.NMNY;

  public static final String NNUM = InvoiceItemVO.NNUM;

  /** 主原币无税净单价 **/
  public static final String NORIGPRICE = InvoiceItemVO.NORIGPRICE;

  public static final String NPRICE = InvoiceItemVO.NPRICE;

  public static final String NREASONWASTENUM = InvoiceItemVO.NREASONWASTENUM;

  /** 剩余结算数量 */
  public static final String NRESIDUALSETTLENUM = "nresidualsettlenum";

  public static final String PK_BIZPSN = InvoiceHeaderVO.PK_BIZPSN;

  public static final String PK_DEPT = InvoiceHeaderVO.PK_DEPT;

  public static final String PK_DEPT_V = InvoiceHeaderVO.PK_DEPT_V;

  public static final String PK_GROUP = InvoiceHeaderVO.PK_GROUP;

  public static final String PK_INVOICE = InvoiceHeaderVO.PK_INVOICE;

  public static final String PK_INVOICE_B = InvoiceItemVO.PK_INVOICE_B;

  public static final String PK_MATERIAL = InvoiceItemVO.PK_MATERIAL;

  public static final String PK_ORDER = InvoiceItemVO.PK_ORDER;

  public static final String PK_ORDER_B = InvoiceItemVO.PK_ORDER_B;

  public static final String PK_ORG = InvoiceHeaderVO.PK_ORG;

  public static final String PK_ORG_V = InvoiceHeaderVO.PK_ORG_V;

  public static final String PK_PARENTINVOICE =
      InvoiceHeaderVO.PK_PARENTINVOICE;

  public static final String PK_SRCMATERIAL = InvoiceItemVO.PK_SRCMATERIAL;

  /** 库存组织 **/
  public static final String PK_STOCKORG = InvoiceHeaderVO.PK_STOCKORG;

  /** 库存组织版本信息 **/
  public static final String PK_STOCKORG_V = InvoiceHeaderVO.PK_STOCKORG_V;

  public static final String PK_SUPPLIER = InvoiceHeaderVO.PK_SUPPLIER;

  public static final String VBATCHCODE = InvoiceItemVO.VBATCHCODE;

  public static final String VBILLCODE = InvoiceHeaderVO.VBILLCODE;

  /** 源头单据号 */
  public static final String VFIRSTCODE = InvoiceItemVO.VFIRSTCODE;

  /** 源头单据行号 */
  public static final String VFIRSTROWNO = InvoiceItemVO.VFIRSTROWNO;

  /** 源头交易类型 */
  public static final String VFIRSTTRANTYPE = InvoiceItemVO.VFIRSTTRANTYPE;

  public static final String VFREE1 = InvoiceItemVO.VFREE1;

  public static final String VFREE10 = InvoiceItemVO.VFREE10;

  public static final String VFREE2 = InvoiceItemVO.VFREE2;

  public static final String VFREE3 = InvoiceItemVO.VFREE3;

  public static final String VFREE4 = InvoiceItemVO.VFREE4;

  public static final String VFREE5 = InvoiceItemVO.VFREE5;

  public static final String VFREE6 = InvoiceItemVO.VFREE6;

  public static final String VFREE7 = InvoiceItemVO.VFREE7;

  public static final String VFREE8 = InvoiceItemVO.VFREE8;

  public static final String VFREE9 = InvoiceItemVO.VFREE9;

  /** 表体备注 */
  public static final String VMEMOB = InvoiceItemVO.VMEMOB;

  /** 来源单据号 */
  public static final String VSOURCECODE = InvoiceItemVO.VSOURCECODE;

  /** 来源单据行号 */
  public static final String VSOURCEROWNO = InvoiceItemVO.VSOURCEROWNO;

  /** 来源交易类型 */
  public static final String VSOURCETRANTYPE = InvoiceItemVO.VSOURCETRANTYPE;

  // public static final String CQUALITYLEVELID = InvoiceItemVO.CQUALITYLEVELID;

  public static final String VTRANTYPECODE = InvoiceHeaderVO.VTRANTYPECODE;

  private static final long serialVersionUID = -7340683516996512866L;

  private UFDouble m_costfactor7 = null;

  private UFDouble m_costfactor8 = null;

  // add by liangchen1 631进出口调整分摊
  /** 进口调整货物 */
  private UFDouble m_nadjustmny = null;

  /** 未结算金额 */
  private UFDouble m_ncansettlemny = null;

  /** 未结算数量 */
  private UFDouble m_ncansettlenum = null;

  /** 成本要素1-8 */
  private UFDouble m_ncostfactor1 = null;

  private UFDouble m_ncostfactor2 = null;

  private UFDouble m_ncostfactor3 = null;

  private UFDouble m_ncostfactor4 = null;

  private UFDouble m_ncostfactor5 = null;

  private UFDouble m_ncostfactor6 = null;

  /** 累计本次发合理损耗累计结算数量 */
  private UFDouble m_ncurrentaccreasonwastenum = null;

  /** 累计本次发票结算金额 */
  private UFDouble m_ncurrentaccuminvoicesettlemny = null;

  /** 累计本次结算数量 */
  private UFDouble m_ncurrentaccumsettlenum = null;

  /** 累计本次总结算金额 */
  private UFDouble m_ncurrentaccumtotalsettlemny = null;

  /** 本次发票结算金额 */
  private UFDouble m_ncurrentinvoicesettlemny = null;

  /** 本次总结算金额=本次发票结算金额+折扣+成本要素1-8 */
  private UFDouble m_ncurrentotalsettlemny = null;

  /** 本次结算数量 */
  private UFDouble m_ncurrentsettlenum = null;

  /** 折扣 */
  private UFDouble m_ndiscount = null;

  /** 本次剩余结算数量，实际结算时是以此数量为准进行排序，如从小到大 */
  private transient UFDouble m_nresidualsettlenum = null;

  /** 成本域 */
  private String m_pk_costregion = null;

  // private transient DataViewMeta m_viewMeta = null;

  @Override
  public void addtoCurrenttotalsettlemoney(UFDouble dValue) {
    this.setNcurrentotalsettlemny(MathTool.add(this.getNcurrentotalsettlemny(),
        dValue));
  }

  /**
   * 父类方法重写,针对某些没有元数据的字段自己处理
   * 
   * @see nc.vo.pubapp.pattern.model.entity.view.AbstractDataView#getAttributeValue(java.lang.String)
   */
  @Override
  public Object getAttributeValue(String name) {
    if (InvoiceSettleVO.NCANSETTLENUM.equals(name)) {// 未结算数量
      return this.getNcansettlenum();
    }
    else if (InvoiceSettleVO.NCANSETTLEMNY.equals(name)) {// 未结算金额
      return this.getNcansettlemny();
    }
    else if (InvoiceSettleVO.NCURRENTSETTLENUM.equals(name)) {// 本次结算数量
      return this.getNcurrentsettlenum();
    }
    else if (InvoiceSettleVO.NRESIDUALSETTLENUM.equals(name)) {// 剩余结算数量
      return this.getNresidualsettlenum();
    }
    else if (InvoiceSettleVO.NCURRENTINVOICESETTLEMNY.equals(name)) {
      return this.getNcurrentinvoicesettlemny();// 本次发票结算金额
    }

    return super.getAttributeValue(name);
  }

  /**
   * 方法功能描述：取得<b>已传应付标志</b>属性。
   * 
   * @param
   * @return UFBoolean
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public UFBoolean getBapflag() {
    return (UFBoolean) this.getAttributeValue(InvoiceSettleVO.BAPFLAG);
  }

  /**
   * 方法功能描述：取得<b>是否费用发票</b>属性。
   * 
   * @param
   * @return UFBoolean
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public UFBoolean getBfee() {
    return (UFBoolean) this.getAttributeValue(InvoiceSettleVO.BFEE);
  }

  /** 虚拟发票标志 getter 方法 */
  public UFBoolean getBvirtual() {
    return (UFBoolean) this.getAttributeValue(InvoiceHeaderVO.BVIRTUAL);
  }

  /**
   * 方法功能描述：取得<b>本币币种</b>属性。
   * 
   * @param
   * @return String
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CCURRENCYID);
  }

  /**
   * 方法功能描述：取得<b>源头单据行主键</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCfirstbid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CFIRSTBID);
  }

  /**
   * 方法功能描述：取得<b>源头单据主键</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCfirstid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CFIRSTID);
  }

  /**
   * 方法功能描述：取得<b>源头单据类型</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCfirsttypecode() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CFIRSTTYPECODE);
  }

  /**
   * 方法功能描述：取得<b>原币币种</b>属性。
   * 
   * @param
   * @return String
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CORIGCURRENCYID);
  }

  /**
   * 方法功能描述：取得<b>生产厂商</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCproductorid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CPRODUCTORID);
  }

  /**
   * 方法功能描述：取得<b>项目</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCprojectid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CPROJECTID);
  }

  /**
   * 方法功能描述：取得<b>行号</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCrowno() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CROWNO);
  }

  /**
   * 方法功能描述：取得<b>上层单据行主键</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCsourcebid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CSOURCEBID);
  }

  /**
   * 方法功能描述：取得<b>上层单据主键</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCsourceid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CSOURCEID);
  }

  /**
   * 方法功能描述：取得<b>上层单据类型</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CSOURCETYPECODE);
  }

  /**
   * 交易类型PK
   * 
   * @return
   */
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CTRANTYPEID);
  }

  /**
   * 方法功能描述：取得<b>主计量单位</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getCunitid() {
    return (String) this.getAttributeValue(InvoiceSettleVO.CUNITID);
  }

  /** 获取发票归属 getter 方法 */
  public Integer getFinvoicetype() {
    return (Integer) this.getAttributeValue(InvoiceHeaderVO.FINVOICETYPE);
  }

  /** 行类型 getter 方法 */
  public Integer getFrowtype() {
    return (Integer) this.getAttributeValue(InvoiceSettleVO.FROWTYPE);
  }

  @Override
  public IDataViewMeta getMetaData() {
    // if (this.m_viewMeta == null) {
    // Class<? extends ISuperVO> headClazz = InvoiceHeaderVO.class;
    // Class<? extends ISuperVO> itemClazz = InvoiceItemVO.class;
    // // 视图的主vo为子表vo
    // this.m_viewMeta =
    // new DataViewMeta(itemClazz, new String[] {
    // InvoiceItemVO.VFREE1, InvoiceItemVO.VFREE10, InvoiceItemVO.VFREE2,
    // InvoiceItemVO.VFREE3, InvoiceItemVO.VFREE4, InvoiceItemVO.VFREE5,
    // InvoiceItemVO.VFREE6, InvoiceItemVO.VFREE7, InvoiceItemVO.VFREE8,
    // InvoiceItemVO.VFREE9, InvoiceItemVO.VBATCHCODE,
    // InvoiceItemVO.CROWNO, InvoiceItemVO.PK_COSTSUBJ,
    // InvoiceItemVO.CSOURCEBID, InvoiceItemVO.CSOURCETYPECODE,
    // InvoiceItemVO.VSOURCECODE, InvoiceItemVO.VSOURCEROWNO,
    // InvoiceItemVO.VSOURCETRANTYPE, InvoiceItemVO.CSOURCEID,
    // InvoiceItemVO.VFIRSTROWNO, InvoiceItemVO.VFIRSTTRANTYPE,
    // InvoiceItemVO.CFIRSTID, InvoiceItemVO.CFIRSTBID,
    // InvoiceItemVO.CFIRSTTYPECODE, InvoiceItemVO.VFIRSTCODE,
    // InvoiceItemVO.NACCUMSETTMNY, InvoiceItemVO.NACCUMSETTNUM,
    // InvoiceItemVO.VCHANGERATE, InvoiceItemVO.NNUM, InvoiceItemVO.NMNY,
    // InvoiceItemVO.NPRICE, InvoiceItemVO.NREASONWASTENUM,
    // InvoiceItemVO.PK_INVOICE_B, InvoiceItemVO.PK_MATERIAL,
    // InvoiceItemVO.PK_SRCMATERIAL, InvoiceItemVO.CUNITID,
    // InvoiceItemVO.PK_ORDER, InvoiceItemVO.PK_ORDER_B,
    // InvoiceItemVO.CPRODUCTORID, InvoiceItemVO.CPROJECTID,
    // InvoiceItemVO.PK_STORDOC, InvoiceItemVO.VMEMOB,
    // InvoiceItemVO.NORIGPRICE, InvoiceItemVO.FROWTYPE, InvoiceItemVO.TS,
    // InvoiceItemVO.NCALCOSTMNY
    // });
    //
    // // 视图vo还要包含主表vo
    // this.m_viewMeta.add(headClazz, new String[] {
    // InvoiceHeaderVO.PK_BIZPSN, InvoiceHeaderVO.BAPFLAG,
    // InvoiceHeaderVO.BVIRTUAL, InvoiceHeaderVO.DBILLDATE,
    // InvoiceHeaderVO.PK_GROUP, InvoiceHeaderVO.PK_DEPT,
    // InvoiceHeaderVO.PK_DEPT_V, InvoiceHeaderVO.BFEE,
    // InvoiceHeaderVO.PK_ORG, InvoiceHeaderVO.PK_ORG_V,
    // InvoiceHeaderVO.PK_INVOICE, InvoiceHeaderVO.PK_PARENTINVOICE,
    // InvoiceHeaderVO.PK_PURCHASEORG, InvoiceHeaderVO.PK_STOCKORG,
    // InvoiceHeaderVO.PK_STOCKORG_V, InvoiceHeaderVO.VBILLCODE,
    // InvoiceHeaderVO.VTRANTYPECODE, InvoiceHeaderVO.CTRANTYPEID,
    // InvoiceHeaderVO.CCURRENCYID, InvoiceHeaderVO.CORIGCURRENCYID,
    // InvoiceHeaderVO.PK_SUPPLIER, InvoiceHeaderVO.TS
    //
    // });
    // // 设置关联字段
    //
    // this.m_viewMeta.addRelation(itemClazz, InvoiceItemVO.PK_INVOICE,
    // headClazz, InvoiceHeaderVO.PK_INVOICE);
    //
    // this.m_viewMeta.setAttributeAliasName(InvoiceHeaderVO.class,
    // InvoiceHeaderVO.TS, "hts");
    // }
    // return this.m_viewMeta;

    return DataViewMetaFactory.getInstance().getDataViewMeta(
        InvoiceSettleVOMeta.class);
  }

  /**
   * 方法功能描述：取得<b>累计本币结算金额</b>属性。
   * 
   * @param
   * @return UFDouble
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public UFDouble getNaccumsettmny() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleVO.NACCUMSETTMNY);
  }

  /**
   * 方法功能描述：取得<b>累计结算数量</b>属性。
   * 
   * @param
   * @return UFDouble
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public UFDouble getNaccumsettnum() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleVO.NACCUMSETTNUM);
  }

  @Override
  public UFDouble getNadjustmny() {
    return this.m_nadjustmny;
  }

  @Override
  public UFDouble getNallotmoney() {
    return this.getNcurrentinvoicesettlemny();
  }

  @Override
  public UFDouble getNallotnum() {
    return this.getNcurrentsettlenum();
  }

  /** 计成本金额 getter 方法 */
  public UFDouble getNcalcostmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NCALCOSTMNY);
  }

  public UFDouble getNcansettlemny() {
    return this.m_ncansettlemny;
  }

  public UFDouble getNcansettlenum() {
    return this.m_ncansettlenum;
  }

  @Override
  public UFDouble getNcostfactor1() {
    return this.m_ncostfactor1;
  }

  @Override
  public UFDouble getNcostfactor2() {
    return this.m_ncostfactor2;
  }

  @Override
  public UFDouble getNcostfactor3() {
    return this.m_ncostfactor3;
  }

  @Override
  public UFDouble getNcostfactor4() {
    return this.m_ncostfactor4;
  }

  @Override
  public UFDouble getNcostfactor5() {
    return this.m_ncostfactor5;
  }

  @Override
  public UFDouble getNcostfactor6() {
    return this.m_ncostfactor6;
  }

  @Override
  public UFDouble getNcostfactor7() {
    return this.m_costfactor7;
  }

  @Override
  public UFDouble getNcostfactor8() {
    return this.m_costfactor8;
  }

  /**
   * 本次累计合理损耗结算数量
   * 
   * @return
   */
  public UFDouble getNcurrentaccreasonwastenum() {
    return this.m_ncurrentaccreasonwastenum;
  }

  public UFDouble getNcurrentaccuminvoicesettlemny() {
    return this.m_ncurrentaccuminvoicesettlemny;
  }

  public UFDouble getNcurrentaccumsettlenum() {
    return this.m_ncurrentaccumsettlenum;
  }

  public UFDouble getNcurrentaccumtotalsettlemny() {
    return this.m_ncurrentaccumtotalsettlemny;
  }

  public UFDouble getNcurrentinvoicesettlemny() {
    return this.m_ncurrentinvoicesettlemny;
  }

  public UFDouble getNcurrentotalsettlemny() {
    return this.m_ncurrentotalsettlemny;
  }

  public UFDouble getNcurrentsettlenum() {
    return this.m_ncurrentsettlenum;
  }

  @Override
  public UFDouble getNdiscount() {
    return this.m_ndiscount;
  }

  /**
   * 方法功能描述：取得<b>本币无税金额</b>属性。
   * 
   * @param
   * @return UFDouble
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleVO.NMNY);
  }

  /**
   * 方法功能描述：取得<b>发票数量</b>属性。
   * 
   * @param
   * @return UFDouble
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleVO.NNUM);
  }

  /**
   * 方法功能描述：取得<b>主原币无税净单价</b>属性。
   * 
   * @param
   * @return UFDouble
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public UFDouble getNorigprice() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleVO.NORIGPRICE);
  }

  /**
   * 方法功能描述：取得<b>本币无税单价</b>属性。
   * 
   * @param
   * @return UFDouble
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleVO.NPRICE);
  }

  /**
   * 方法功能描述：取得<b>合理损耗数量</b>属性。
   * 
   * @param
   * @return UFDouble
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public UFDouble getNreasonwastenum() {
    return (UFDouble) this.getAttributeValue(InvoiceSettleVO.NREASONWASTENUM);
  }

  public UFDouble getNresidualsettlenum() {
    return this.m_nresidualsettlenum;
  }

  /**
   * 方法功能描述：取得<b>业务员</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public String getPk_bizpsn() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_BIZPSN);
  }

  public String getPk_costregion() {
    return this.m_pk_costregion;
  }

  /**
   * 方法功能描述：取得<b>使用部门</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getPk_dept() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_DEPT);
  }

  public String getPk_dept_v() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_DEPT_V);
  }

  /**
   * 方法功能描述：取得<b>所属集团</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public String getPk_group() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_GROUP);
  }

  /**
   * 方法功能描述：取得<b>采购发票主表_主键</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getPk_invoice() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_INVOICE);
  }

  /**
   * 方法功能描述：取得<b>发票子实体主键</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getPk_invoice_b() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_INVOICE_B);
  }

  /**
   * 方法功能描述：取得<b>物料</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getPk_material() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_MATERIAL);
  }

  /**
   * 方法功能描述：取得<b>采购订单主键</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getPk_order() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_ORDER);
  }

  /**
   * 方法功能描述：取得<b>采购订单行主键</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_ORDER_B);
  }

  /**
   * 方法功能描述：取得<b>主组织-财务组织</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getPk_org() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_ORG);
    // return (String)
    // getVO(InvoiceHeaderVO.class).getAttributeValue(PK_FINANCEORG);
  }

  public String getPk_org_v() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_ORG_V);
    // return (String)
    // getVO(InvoiceHeaderVO.class).getAttributeValue(PK_FINANCEORG);
  }

  /**
   * 方法功能描述：取得<b>费用发票对应货物发票</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public String getPk_parentinvoice() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_PARENTINVOICE);
  }

  @Override
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_SRCMATERIAL);
  }

  /**
   * 方法功能描述：取得<b>库存组织</b>属性。
   * 
   * @param
   * @return String
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public String getPk_stockorg() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_STOCKORG);
  }

  /**
   * 方法功能描述：取得<b>库存组织版本</b>属性。
   * 
   * @param
   * @return String
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public String getPk_stockorg_v() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_STOCKORG_V);
  }

  /** 仓库 getter 方法 */
  public String getPk_stordoc() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_STORDOC);
  }

  /**
   * 方法功能描述：取得<b>供应商</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public String getPk_supplier() {
    return (String) this.getAttributeValue(InvoiceSettleVO.PK_SUPPLIER);
  }

  /**
   * 方法功能描述：取得<b>发票号</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public String getVbillcode() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VBILLCODE);
  }

  /** 合同号 getter 方法 */
  public String getVctcode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VCTCODE);
  }

  /**
   * 方法功能描述：取得<b>源头单据号</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfirstcode() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFIRSTCODE);
  }

  /**
   * 方法功能描述：取得<b>质量等级</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  // public String getCqualitylevelid() {
  // return (String) this.getAttributeValue(CQUALITYLEVELID);
  // }
  /**
   * 方法功能描述：取得<b>源头单据行号</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFIRSTROWNO);
  }

  /**
   * 方法功能描述：取得<b>源头交易类型</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFIRSTTRANTYPE);
  }

  /**
   * 方法功能描述：取得<b>自由项1</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree1() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE1);
  }

  /**
   * 方法功能描述：设置<b>质量等级</b>属性。
   * 
   * @param String vfree1
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  // public void setCqualitylevelid(String value) {
  // this.setAttributeValue(CQUALITYLEVELID, value);
  // }
  /**
   * 方法功能描述：取得<b>自由项10</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree10() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE10);
  }

  /**
   * 方法功能描述：取得<b>自由项2</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree2() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE2);
  }

  /**
   * 方法功能描述：取得<b>自由项3</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree3() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE3);
  }

  /**
   * 方法功能描述：取得<b>自由项4</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree4() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE4);
  }

  /**
   * 方法功能描述：取得<b>自由项5</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree5() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE5);
  }

  /**
   * 方法功能描述：取得<b>自由项6</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree6() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE6);
  }

  /**
   * 方法功能描述：取得<b>自由项7</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree7() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE7);
  }

  /**
   * 方法功能描述：取得<b>自由项8</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree8() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE8);
  }

  /**
   * 方法功能描述：取得<b>自由项9</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVfree9() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VFREE9);
  }

  /**
   * 方法功能描述：取得<b>行备注</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVmemo() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VMEMOB);
  }

  /** 订单号 getter 方法 */
  public String getVordercode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VORDERCODE);
  }

  /**
   * 方法功能描述：取得<b>批次号</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVproducenum() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VBATCHCODE);
  }

  /**
   * 方法功能描述：取得<b>上层单据号</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVsourcecode() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VSOURCECODE);
  }

  /**
   * 方法功能描述：取得<b>上层单据行号</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VSOURCEROWNO);
  }

  /**
   * 方法功能描述：取得<b>上层交易类型</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public String getVsourcetrantype() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VSOURCETRANTYPE);
  }

  /**
   * 方法功能描述：取得<b>交易类型</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(InvoiceSettleVO.VTRANTYPECODE);
  }

  @Override
  public void setAttributeValue(String name, Object value) {
    if (InvoiceSettleVO.NCURRENTINVOICESETTLEMNY.equals(name)) {
      // 本将发票结算金额，费用发票会从模板上取值
      this.setNcurrentinvoicesettlemny((UFDouble) value);
    }
    super.setAttributeValue(name, value);
  }

  /**
   * 方法功能描述：设置<b>已传应付标志</b>属性。
   * 
   * @param UFBoolean bapflag
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public void setBapflag(UFBoolean bapflag) {
    this.setAttributeValue(InvoiceSettleVO.BAPFLAG, bapflag);
  }

  /**
   * 方法功能描述：设置<b>是否费用发票</b>属性。
   * 
   * @param UFBoolean bchargeinvoice
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public void setBfee(UFBoolean bchargeinvoice) {
    this.setAttributeValue(InvoiceSettleVO.BFEE, bchargeinvoice);
  }

  /** 虚拟发票标志 setter 方法 */
  public void setBvirtual(UFBoolean bvirtual) {
    this.setAttributeValue(InvoiceHeaderVO.BVIRTUAL, bvirtual);
  }

  /**
   * 方法功能描述：设置<b>原币币种</b>属性。
   * 
   * @param String pk_currtype
   * @return
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public void setCcurrencyid(String pk_currtype) {
    this.setAttributeValue(InvoiceSettleVO.CCURRENCYID, pk_currtype);
  }

  /**
   * 方法功能描述：设置<b>源头单据行主键</b>属性。
   * 
   * @param String csourcebillrowid
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCfirstbid(String cfirstbid) {
    this.setAttributeValue(InvoiceSettleVO.CFIRSTBID, cfirstbid);
  }

  /**
   * 方法功能描述：设置<b>源头单据主键</b>属性。
   * 
   * @param String csourcebillid
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCfirstid(String cfirstid) {
    this.setAttributeValue(InvoiceSettleVO.CFIRSTID, cfirstid);
  }

  /**
   * 方法功能描述：设置<b>源头单据类型</b>属性。
   * 
   * @param String csourcebilltype
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCfirsttypecode(String cfirsttypecode) {
    this.setAttributeValue(InvoiceSettleVO.CFIRSTTYPECODE, cfirsttypecode);
  }

  /**
   * 方法功能描述：设置<b>原币币种</b>属性。
   * 
   * @param String pk_currtype
   * @return
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public void setCorigcurrencyid(String pk_currtype) {
    this.setAttributeValue(InvoiceSettleVO.CORIGCURRENCYID, pk_currtype);
  }

  /**
   * 方法功能描述：设置<b>生产厂商</b>属性。
   * 
   * @param String vfree1
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCproductorid(String value) {
    this.setAttributeValue(InvoiceSettleVO.CPRODUCTORID, value);
  }

  /**
   * 方法功能描述：设置<b>项目</b>属性。
   * 
   * @param String vfree1
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCprojectid(String value) {
    this.setAttributeValue(InvoiceSettleVO.CPROJECTID, value);
  }

  /**
   * 方法功能描述：设置<b>行号</b>属性。
   * 
   * @param String vrowno
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCrowno(String vrowno) {
    this.setAttributeValue(InvoiceSettleVO.CROWNO, vrowno);
  }

  /**
   * 方法功能描述：设置<b>上层单据行主键</b>属性。
   * 
   * @param String cupsourcebillrowid
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCsourcebid(String csourcebid) {
    this.setAttributeValue(InvoiceSettleVO.CSOURCEBID, csourcebid);
  }

  /**
   * 方法功能描述：设置<b>上层单据主键</b>属性。
   * 
   * @param String cupsourcebillid
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCsourceid(String csourceid) {
    this.setAttributeValue(InvoiceSettleVO.CSOURCEID, csourceid);
  }

  /**
   * 方法功能描述：设置<b>上层单据类型</b>属性。
   * 
   * @param String cupsourcebilltype
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCsourcetypecode(String csourcetypecode) {
    this.setAttributeValue(InvoiceSettleVO.CSOURCETYPECODE, csourcetypecode);
  }

  /**
   * 方法功能描述：设置<b>主计量单位</b>属性。
   * 
   * @param String pk_measdoc
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setCunitid(String pk_measdoc) {
    this.setAttributeValue(InvoiceSettleVO.CUNITID, pk_measdoc);
  }

  /** 设置发票归属 setter 方法 */
  public void setFinvoicetype(Integer finvoicetype) {
    this.setAttributeValue(InvoiceHeaderVO.FINVOICETYPE, finvoicetype);
  }

  /**
   * 方法功能描述：设置<b>累计本币结算金额</b>属性。
   * 
   * @param UFDouble naccumsettmny
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setNaccumsettmny(UFDouble naccumsettmny) {
    this.setAttributeValue(InvoiceSettleVO.NACCUMSETTMNY, naccumsettmny);
  }

  /**
   * 方法功能描述：设置<b>累计结算数量</b>属性。
   * 
   * @param UFDouble naccumsettnum
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setNaccumsettnum(UFDouble naccumsettnum) {
    this.setAttributeValue(InvoiceSettleVO.NACCUMSETTNUM, naccumsettnum);
  }

  @Override
  public void setNadjustmny(UFDouble nadjustmny) {
    this.m_nadjustmny = nadjustmny;
  }

  public void setNcansettlemny(UFDouble ncansettlenmny) {
    this.m_ncansettlemny = ncansettlenmny;
  }

  public void setNcansettlenum(UFDouble ncansettlenum) {
    this.m_ncansettlenum = ncansettlenum;
  }

  @Override
  public void setNcostfactor1(UFDouble ncostfactor1) {
    this.m_ncostfactor1 = ncostfactor1;
  }

  @Override
  public void setNcostfactor2(UFDouble ncostfactor2) {
    this.m_ncostfactor2 = ncostfactor2;
  }

  @Override
  public void setNcostfactor3(UFDouble ncostfactor3) {
    this.m_ncostfactor3 = ncostfactor3;
  }

  @Override
  public void setNcostfactor4(UFDouble ncostfactor4) {
    this.m_ncostfactor4 = ncostfactor4;
  }

  @Override
  public void setNcostfactor5(UFDouble ncostfactor5) {
    this.m_ncostfactor5 = ncostfactor5;
  }

  @Override
  public void setNcostfactor6(UFDouble ncostfactor6) {
    this.m_ncostfactor6 = ncostfactor6;
  }

  @Override
  public void setNcostfactor7(UFDouble ncostfactor7) {
    this.m_costfactor7 = ncostfactor7;
  }

  @Override
  public void setNcostfactor8(UFDouble ncostfactor8) {
    this.m_costfactor8 = ncostfactor8;
  }

  /**
   * 本次累计合理损耗结算数量
   * 
   * @param ncurrentaccreasonwastenum
   */
  public void setNcurrentaccreasonwastenum(UFDouble ncurrentaccreasonwastenum) {
    this.m_ncurrentaccreasonwastenum = ncurrentaccreasonwastenum;
  }

  public void setNcurrentaccuminvoicesettlemny(
      UFDouble ncurrentaccuminvoicesettlemny) {
    this.m_ncurrentaccuminvoicesettlemny = ncurrentaccuminvoicesettlemny;
  }

  public void setNcurrentaccumsettlenum(UFDouble ncurrentaccumsettlenum) {
    this.m_ncurrentaccumsettlenum = ncurrentaccumsettlenum;
  }

  public void setNcurrentaccumtotalsettlemny(
      UFDouble ncurrentaccumtotalsettlemny) {
    this.m_ncurrentaccumtotalsettlemny = ncurrentaccumtotalsettlemny;
  }

  public void setNcurrentinvoicesettlemny(UFDouble ncurrentsettlemny) {
    this.m_ncurrentinvoicesettlemny = ncurrentsettlemny;
  }

  public void setNcurrentotalsettlemny(UFDouble ncurrentotalsettlemny) {
    this.m_ncurrentotalsettlemny = ncurrentotalsettlemny;
  }

  public void setNcurrentsettlenum(UFDouble ncurrentsettlenum) {
    this.m_ncurrentsettlenum = ncurrentsettlenum;
  }

  @Override
  public void setNdiscount(UFDouble ndiscount) {
    this.m_ndiscount = ndiscount;
  }

  /**
   * 方法功能描述：设置<b>本币无税金额</b>属性。
   * 
   * @param UFDouble nmoney
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setNmny(UFDouble nmoney) {
    this.setAttributeValue(InvoiceSettleVO.NMNY, nmoney);
  }

  /**
   * 方法功能描述：设置<b>发票数量</b>属性。
   * 
   * @param UFDouble ninvoicenum
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setNnum(UFDouble ninvoicenum) {
    this.setAttributeValue(InvoiceSettleVO.NNUM, ninvoicenum);
  }

  /**
   * 方法功能描述：设置<b>主原币无税净单价</b>属性。
   * 
   * @param UFDouble ninvoicenum
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setNorigprice(UFDouble norigprice) {
    this.setAttributeValue(InvoiceSettleVO.NORIGPRICE, norigprice);
  }

  /**
   * 方法功能描述：设置<b>本币无税单价</b>属性。
   * 
   * @param UFDouble nnetprice
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setNprice(UFDouble nnetprice) {
    this.setAttributeValue(InvoiceSettleVO.NPRICE, nnetprice);
  }

  /**
   * 方法功能描述：设置<b>合理损耗数量</b>属性。
   * 
   * @param UFDouble nreasonwastenum
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setNreasonwastenum(UFDouble nreasonwastenum) {
    this.setAttributeValue(InvoiceSettleVO.NREASONWASTENUM, nreasonwastenum);
  }

  public void setNresidualsettlenum(UFDouble nresidualsettlenum) {
    this.m_nresidualsettlenum = nresidualsettlenum;
  }

  /**
   * 方法功能描述：设置<b>业务员</b>属性。
   * 
   * @param String cbizpsn
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public void setPk_bizpsn(String cbizpsn) {
    this.setAttributeValue(InvoiceSettleVO.PK_BIZPSN, cbizpsn);
  }

  public void setPk_costregion(String pk_costregion) {
    this.m_pk_costregion = pk_costregion;
  }

  /**
   * 方法功能描述：设置<b>使用部门</b>属性。
   * 
   * @param String pk_dept
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(InvoiceSettleVO.PK_DEPT, pk_dept);
  }

  /**
   * 方法功能描述：设置<b>所属集团</b>属性。
   * 
   * @param String pk_group
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(InvoiceSettleVO.PK_GROUP, pk_group);
  }

  /**
   * 方法功能描述：设置<b>采购发票主表_主键</b>属性。
   * 
   * @param String pk_invoice
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setPk_invoice(String pk_invoice) {
    this.setAttributeValue(InvoiceSettleVO.PK_INVOICE, pk_invoice);
  }

  /**
   * 方法功能描述：设置<b>发票子实体主键</b>属性。
   * 
   * @param String pk_invoice_b
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setPk_invoice_b(String pk_invoice_b) {
    this.setAttributeValue(InvoiceSettleVO.PK_INVOICE_B, pk_invoice_b);
  }

  /**
   * 方法功能描述：设置<b>物料</b>属性。
   * 
   * @param String pk_material
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(InvoiceSettleVO.PK_MATERIAL, pk_material);
  }

  /**
   * 方法功能描述：设置<b>采购订单主键</b>属性。
   * 
   * @param String pk_order
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setPk_order(String pk_order) {
    this.setAttributeValue(InvoiceSettleVO.PK_ORDER, pk_order);
  }

  /**
   * 方法功能描述：设置<b>采购订单行主键</b>属性。
   * 
   * @param String pk_order_b
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(InvoiceSettleVO.PK_ORDER_B, pk_order_b);
  }

  /**
   * 方法功能描述：设置<b>主组织-财务组织</b>属性。
   * 
   * @param String pk_financeorg
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(InvoiceSettleVO.PK_ORG, pk_org);
  }

  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(InvoiceSettleVO.PK_ORG_V, pk_org_v);
  }

  /**
   * 方法功能描述：设置<b>费用发票对应货物发票</b>属性。
   * 
   * @param String pk_parentinvoice
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public void setPk_parentinvoice(String pk_parentinvoice) {
    this.setAttributeValue(InvoiceSettleVO.PK_PARENTINVOICE, pk_parentinvoice);
  }

  public void setPk_srcmaterial(String pk_material) {
    this.setAttributeValue(InvoiceSettleVO.PK_SRCMATERIAL, pk_material);
  }

  /**
   * 方法功能描述：设置<b>库存组织</b>属性。
   * 
   * @param String pk_currtype
   * @return
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public void setPk_stockorg(String pk_stock) {
    this.setAttributeValue(InvoiceSettleVO.PK_STOCKORG, pk_stock);
  }

  /**
   * 方法功能描述：设置<b>库存组织</b>属性。
   * 
   * @param String pk_currtype
   * @return
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public void setPk_stockorg_v(String pk_stock_v) {
    this.setAttributeValue(InvoiceSettleVO.PK_STOCKORG_V, pk_stock_v);
  }

  /** 仓库 setter 方法 */
  public void setPk_stordoc(String pk_stordoc) {
    this.setAttributeValue(InvoiceItemVO.PK_STORDOC, pk_stordoc);
  }

  /**
   * 方法功能描述：设置<b>供应商</b>属性。
   * 
   * @param String pk_supplier
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(InvoiceSettleVO.PK_SUPPLIER, pk_supplier);
  }

  /**
   * 方法功能描述：设置<b>发票号</b>属性。
   * 
   * @param String vinvoicecode
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public void setVbillcode(String vinvoicecode) {
    this.setAttributeValue(InvoiceSettleVO.VBILLCODE, vinvoicecode);
  }

  /**
   * 方法功能描述：设置<b>源头单据号</b>属性。
   * 
   * @param String vsourcebillcode
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfirstcode(String vfirstcode) {
    this.setAttributeValue(InvoiceSettleVO.VFIRSTCODE, vfirstcode);
  }

  /**
   * 方法功能描述：设置<b>源头单据行号</b>属性。
   * 
   * @param String vsourcebillrowno
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfirstrowno(String vsourcebillrowno) {
    this.setAttributeValue(InvoiceSettleVO.VFIRSTROWNO, vsourcebillrowno);
  }

  /**
   * 方法功能描述：设置<b>源头交易类型</b>属性。
   * 
   * @param String vsourcetrantype
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfirsttrantype(String vfirsttrantype) {
    this.setAttributeValue(InvoiceSettleVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  /**
   * 方法功能描述：设置<b>自由项1</b>属性。
   * 
   * @param String vfree1
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree1(String vfree1) {
    this.setAttributeValue(InvoiceSettleVO.VFREE1, vfree1);
  }

  /**
   * 方法功能描述：设置<b>自由项10</b>属性。
   * 
   * @param String vfree10
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree10(String vfree10) {
    this.setAttributeValue(InvoiceSettleVO.VFREE10, vfree10);
  }

  /**
   * 方法功能描述：设置<b>自由项2</b>属性。
   * 
   * @param String vfree2
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree2(String vfree2) {
    this.setAttributeValue(InvoiceSettleVO.VFREE2, vfree2);
  }

  /**
   * 方法功能描述：设置<b>自由项3</b>属性。
   * 
   * @param String vfree3
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree3(String vfree3) {
    this.setAttributeValue(InvoiceSettleVO.VFREE3, vfree3);
  }

  /**
   * 方法功能描述：设置<b>自由项4</b>属性。
   * 
   * @param String vfree4
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree4(String vfree4) {
    this.setAttributeValue(InvoiceSettleVO.VFREE4, vfree4);
  }

  /**
   * 方法功能描述：设置<b>自由项5</b>属性。
   * 
   * @param String vfree5
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree5(String vfree5) {
    this.setAttributeValue(InvoiceSettleVO.VFREE5, vfree5);
  }

  /**
   * 方法功能描述：设置<b>自由项6</b>属性。
   * 
   * @param String vfree6
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree6(String vfree6) {
    this.setAttributeValue(InvoiceSettleVO.VFREE6, vfree6);
  }

  /**
   * 方法功能描述：设置<b>自由项7</b>属性。
   * 
   * @param String vfree7
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree7(String vfree7) {
    this.setAttributeValue(InvoiceSettleVO.VFREE7, vfree7);
  }

  /**
   * 方法功能描述：设置<b>自由项8</b>属性。
   * 
   * @param String vfree8
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree8(String vfree8) {
    this.setAttributeValue(InvoiceSettleVO.VFREE8, vfree8);
  }

  /**
   * 方法功能描述：设置<b>自由项9</b>属性。
   * 
   * @param String vfree9
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVfree9(String vfree9) {
    this.setAttributeValue(InvoiceSettleVO.VFREE9, vfree9);
  }

  /**
   * 方法功能描述：设置<b>行备注型</b>属性。
   * 
   * @param String vupsourcetrantype
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVmemo(String vbodymemo) {
    this.setAttributeValue(InvoiceSettleVO.VMEMOB, vbodymemo);
  }

  /**
   * 方法功能描述：设置<b>批次号</b>属性。
   * 
   * @param String vproducenum
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVproducenum(String vproducenum) {
    this.setAttributeValue(InvoiceSettleVO.VBATCHCODE, vproducenum);
  }

  /**
   * 方法功能描述：设置<b>上层单据号</b>属性。
   * 
   * @param String vupsourcebillcode
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVsourcecode(String vsourcecode) {
    this.setAttributeValue(InvoiceSettleVO.VSOURCECODE, vsourcecode);
  }

  /**
   * 方法功能描述：设置<b>上层单据行号</b>属性。
   * 
   * @param String vupsourcebillrowno
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVsourcerowno(String vsourcerowno) {
    this.setAttributeValue(InvoiceSettleVO.VSOURCEROWNO, vsourcerowno);
  }

  /**
   * 方法功能描述：设置<b>上层交易类型</b>属性。
   * 
   * @param String vupsourcetrantype
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setVsourcetrantype(String vsourcetrantype) {
    this.setAttributeValue(InvoiceSettleVO.VSOURCETRANTYPE, vsourcetrantype);
  }

  /**
   * 方法功能描述：设置<b>交易类型</b>属性。
   * 
   * @param String vtrantypecode
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:51:09
   */
  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(InvoiceSettleVO.VTRANTYPECODE, vtrantypecode);
  }
}
