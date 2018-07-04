package nc.vo.pu.m27.entity;

import nc.vo.pu.m25.settle.ICostfactorDiscount;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 此处插入类型说明。 创建日期：(2001-7-5)
 * 
 * @author：
 */
public abstract class StockSettleVO extends AbstractDataView implements
    ICostfactorDiscount {
  /** 是否普通采购 */
  public static final String BNORMPUR = "bnormpur";

  public static final String BSETTLEFINISH = "bsettlefinish";

  /** 单位 */
  public static final String CASTUNITID = "castunitid";

  public static final String CBILLTYPECODE = "cbilltypecode";

  /** 本币币种 */
  public static final String CCURRENCYID = "ccurrencyid";

  /** 源头单据分录标识 */
  public static final String CFIRSTBID = "cfirstbid";

  /** 源头单据标识 */
  public static final String CFIRSTID = "cfirstid";

  /** 源头单据类型 */
  public static final String CFIRSTTYPECODE = "cfirsttypecode";

  public static final String CORIGCURRENCYID = "corigcurrencyid";

  public static final String CPRODUCTORID = "cproductorid";

  public static final String CPROJECTID = "cprojectid";

  /** 来源单据分录标识 */
  public static final String CSOURCEBID = "csourcebid";

  /** 来源单据标识 */
  public static final String CSOURCEID = "csourceid";

  /** 来源单据类型 */
  public static final String CSOURCETYPECODE = "csourcetypecode";

  /**
   * 交易类型
   */
  public static final String CTRANTYPEID = "ctrantypeid";

  /** 主单位 */
  public static final String CUNITID = "cunitid";

  /** 入库日期 **/
  public static final String DBILLDATE = "dbilldate";

  /** 暂估应付的累计冲减数量 */
  public static final String NACCESTAPWASHNUM = "naccestapwashnum";

  /** 暂估部分累计结算数量 */
  public static final String NACCESTCOSTSTTLNUM = "naccestcoststtlnum";

  /** 累计暂估冲销金额 */
  public static final String NACCESTCOSTWASHMNY = "naccestcostwashmny";

  /** 累计货物结算金额 */
  public static final String NACCGOODSSETTLEMNY = "naccgoodssettlemny";

  /** 累计确认冲销金额 */
  public static final String NACCTOCOSTADJSTMNY = "nacctocostadjstmny";

  /** 累计结算数量 */
  public static final String NACCUMSETTLENUM = "naccumsettlenum";

  /** 结算平均价 */
  public final static String NAVGSETTLEPRICE = "navgsettleprice";

  /** 入库单上的计成本金额 */
  public static final String NCALCOSTMNY = "ncalcostmny";

  // wuxla v61 计成本单价
  /** 入库单确认成本单价 */
  public static final String NCALCOSTPRICE = "ncalcostprice";

  /** 未结算金额 */
  public static final String NCANSETTLEMNY = "ncansettlemny";

  /** 未结算数量 */
  public static final String NCANSETTLENUM = "ncansettlenum";

  /** 入库单上的成本金额 */
  public final static String NCOSTMNY = "ncostmny";

  /** 入库单上的成本单价 */
  public final static String NCOSTPRICE = "ncostprice";

  /** 本次结算数量 */
  public static final String NCURRENTSETTLENUM = "ncurrentsettlenum";

  /** 计成本金额 */
  public static final String NESTCALCOSTMNY = "nestcalcostmny";

  // wuxla v61 暂估记成本单价
  public static final String NESTCALCOSTPRICE = "nestcalcostprice";

  /** 暂估金额 */
  public static final String NESTMNY = "nestmny";

  /** 暂估数量 */
  public static final String NESTNUM = "nestnum";

  /** 暂估单价 */
  public static final String NESTPRICE = "nestprice";

  /**
   * 实入业务数量
   */
  public static final String NINASSISTNUM = "ninassistnum";

  /**
   * 实际入库数量
   */
  public static final String NINNUM = "ninnum";

  /** 本币无税金额：确认成本时的金额 */
  public static final String NMNY = "nmny";

  /** 主本币无税净价：确认成本时的单价 */
  public static final String NNETPRICE = "nnetprice";

  /** 主无税净价 */
  public static final String NORIGNETPRICE = "norignetprice";

  public static final String NORIGPRICE = "norigprice";

  /** 未暂估已结算金额、纯结算金额 */
  public final static String NPURESETTLEMNY = "npuresettlemny";

  /** 未暂估已结算数量、纯结算数量 */
  public final static String NPURESETTLENUM = "npuresettlenum";

  /** 剩余结算数量 */
  public static final String NRESIDUALSETTLENUM = "nresidualsettlenum";

  public static final String PK_BATCHCODE = "pk_batchcode";

  public static final String PK_COSTREGION = "pk_costregion";

  public static final String PK_DEPT = "pk_dept";

  public static final String PK_DEPT_V = "pk_dept_v";

  public static final String PK_FINANCEORG = "pk_financeorg";

  public static final String PK_GROUP = "pk_group";

  public static final String PK_MATERIAL = "pk_material";

  public static final String PK_ORDER = "pk_order";

  public static final String PK_ORDER_B = "pk_order_b";

  public static final String PK_ORG = "pk_org";

  public static final String PK_ORG_V = "pk_org_v";

  public static final String PK_PSNDOC = "pk_psndoc";

  /** 物料OID */
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  public static final String PK_STOCKPS = "pk_stockps";

  public static final String PK_STOCKPS_B = "pk_stockps_b";

  public static final String PK_SUPPLIER = "pk_supplier";

  public static final String VBATCHCODE = "vbatchcode";

  public static final String VBILLCODE = "vbillcode";

  public static final String VCHANGERATE = "vchangerate";

  /** 源头单据号 */
  public static final String VFIRSTCODE = "vfirstcode";

  /** 源头单据行号 */
  public static final String VFIRSTROWNO = "vfirstrowno";

  /** 源头交易类型 */
  public static final String VFIRSTTRANTYPE = "vfirsttrantype";

  public static final String VFREE1 = "vfree1";

  public static final String VFREE10 = "vfree10";

  public static final String VFREE2 = "vfree2";

  public static final String VFREE3 = "vfree3";

  public static final String VFREE4 = "vfree4";

  public static final String VFREE5 = "vfree5";

  public static final String VFREE6 = "vfree6";

  public static final String VFREE7 = "vfree7";

  public static final String VFREE8 = "vfree8";

  public static final String VFREE9 = "vfree9";

  public static final String VNOTEBODY = "vnotebody";

  public static final String VORDERCODE = "vordercode";

  public static final String VORDERTRANTYPECODE = "vordertrantypecode";

  /** 来源单据号 */
  public static final String VSOURCECODE = "vsourcecode";

  /** 来源单据行号 */
  public static final String VSOURCEROWNO = "vsourcerowno";

  /** 来源交易类型 */
  public static final String VSOURCETRANTYPE = "vsourcetrantype";

  public static final String VTRANTYPECODE = "vtrantypecode";

  private static final long serialVersionUID = 1L;

  // add by liangchen1 631进出口调整发票
  /** 调整货物 */
  private UFDouble m_nadjustmny = null;

  /** 本次分摊依据金额：费用结算时用 */
  private UFDouble m_nallotmoney = null;

  /** 本次分摊依据数量：费用结算时用 */
  private UFDouble m_nallotnum = null;

  /** 结算平均单价 */
  private UFDouble m_navgsettleprice = null;

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

  private UFDouble m_ncostfactor7 = null;

  private UFDouble m_ncostfactor8 = null;

  /** 累计本次结算数量 */
  private UFDouble m_ncurrentaccumsettlenum = null;

  /** 本次发票结算金额 (用于异物料和无发票结算) */
  private UFDouble m_ncurrentinvoicesettlemny = null;

  /** 本次结算数量 */
  private UFDouble m_ncurrentsettlenum = null;

  /** 本次总分摊金额：费用结算时用 */
  private UFDouble m_ncurrenttotalsettlemoney = null;

  /** 折扣 */
  private UFDouble m_ndiscount = null;

  /** 未暂估已结算金额、纯结算金额 */
  private UFDouble m_npuresettlemny = null;

  /** 未暂估已结算数量 */
  private UFDouble m_npuresettlenum = null;

  /** 本次剩余结算数量，实际结算时是以此数量为准进行排序，如从小到大 */
  private transient UFDouble m_nresidualsettlenum = null;

  protected transient DataViewMeta m_viewMeta = null;

  @Override
  public void addtoCurrenttotalsettlemoney(UFDouble dValue) {
    this.setNcurrenttotalsettlemoney(MathTool.add(
        this.getNcurrenttotalsettlemoney(), dValue));
  }

  /**
   * 父类方法重写,针对某些没有元数据的字段自己处理
   * 
   * @see nc.vo.pubapp.pattern.model.entity.view.AbstractDataView#getAttributeValue(java.lang.String)
   */
  @Override
  public Object getAttributeValue(String name) {
    // 必须的几个值
    if (StockSettleVO.NRESIDUALSETTLENUM.equals(name)) {
      return this.getNresidualsettlenum();
    }
    else if (StockSettleVO.NCANSETTLENUM.equals(name)) {
      return this.getNcansettlenum();
    }
    else if (StockSettleVO.NCANSETTLEMNY.equals(name)) {
      return this.getNcansettlemny();
    }
    else if (StockSettleVO.NCURRENTSETTLENUM.equals(name)) {
      return this.getNcurrentsettlenum();
    }
    return super.getAttributeValue(name);
  }

  /** 得到是否影响成本标志 **/
  public abstract UFBoolean getBaffectcost();

  /** 得到是否影响利润中心成本标志 **/
  public abstract UFBoolean getBaffectpciacost();

  /** 是普通采购，还是集采 */
  public abstract UFBoolean getBnormpur();

  public abstract UFBoolean getBsettlefinish();

  /**
   * 方法功能描述：获得业务单位
   * <p>
   * <b>参数说明</b>
   * 
   * @return 业务单位的PK
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-9-14 上午08:53:51
   */
  public abstract String getCastunitid();

  public abstract String getCbilltypecode();

  /** 本币币种 getter 方法 */
  public abstract String getCcurrencyid();

  /**
   * 方法功能描述：取得<b>源头单据行主键</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getCfirstbid();

  public abstract String getCfirstid();

  /**
   * 方法功能描述：取得<b>源头单据类型</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getCfirsttypecode();

  /**
   * 方法功能描述：取得<b>原币币种</b>属性。<br>
   * 用于自动结算规则的匹配（当用户勾选主无税单价相同时）
   * 
   * @param
   * @return String
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  public abstract String getCorigcurrencyid();

  /**
   * 方法功能描述：取得<b>生产厂商</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getCproductorid();

  /**
   * 方法功能描述：取得<b>项目</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getCprojectid();

  /**
   * 方法功能描述：取得<b>上层单据行主键</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getCsourcebid();

  /**
   * 方法功能描述：取得<b>上层单据主键</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getCsourceid();

  /**
   * 方法功能描述：取得<b>上层单据类型</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getCsourcetypecode();

  /**
   * 交易类型PK
   * 
   * @return
   */
  public abstract String getCtrantypeid();

  /**
   * 方法功能描述：取得<b>主计量单位</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getCunitid();

  /**
   * 入库日期
   * 
   * @return
   */
  public abstract UFDate getDbilldate();

  /**
   * 方法功能描述：设置<b>传应付</b>属性。
   * 
   * @param UFBoolean bconfirmap
   * @return
   * @author wangyf
   * @time 2009-6-30 13:38:31
   */
  public abstract Integer getFdirtoaptype();

  /**
   * 方法功能描述：设置<b>传应付</b>属性。
   * 
   * @param UFBoolean bconfirmap
   * @return
   * @author wangyf
   * @time 2009-6-30 13:38:31
   */
  public abstract Integer getFdirtoiatype();

  /** 暂估部分累计结算数量 getter 方法 */
  public abstract UFDouble getNaccestcoststtlnum();

  /**
   * 累计暂估冲销金额
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-6-10 上午11:19:02
   */
  public abstract UFDouble getNaccestcostwashmny();

  /**
   * 方法功能描述：累计结算金额
   * <p>
   * <b>参数说明</b>
   * 
   * @return 累计结算金额
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-9-16 下午04:07:53
   */
  public abstract UFDouble getNaccgoodssettlemny();

  /**
   * 方法功能描述： 暂估前累计结算金额（费用分摊时使用），没有暂估只有结算的单据返回累计结算金额
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-13 上午11:52:21
   */
  public abstract UFDouble getNaccpreeststtlmny();

  /**
   * 方法功能描述：累计调整确认应付原币价税合计。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-27 下午06:22:06
   */
  public UFDouble getNacctoapadjstotmny() {
    return UFDouble.ZERO_DBL;
  }

  /**
   * 累计确认成本冲销金额：
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-6-10 上午11:19:08
   */
  public abstract UFDouble getNacctocostadjstmny();

  /**
   * 方法功能描述：累计结算数量
   * <p>
   * <b>参数说明</b>
   * 
   * @return 累计结算数量
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-9-16 下午04:08:18
   */
  public abstract UFDouble getNaccumsettlenum();

  public abstract UFDouble getNaccurevcostnum();

  @Override
  public UFDouble getNadjustmny() {
    return this.m_nadjustmny;
  }

  @Override
  public UFDouble getNallotmoney() {
    return this.m_nallotmoney;
  }

  @Override
  public UFDouble getNallotnum() {
    return this.m_nallotnum;
  }

  public UFDouble getNavgsettleprice() {
    return this.m_navgsettleprice;
  }

  /**
   * 记成本金额
   * <p>
   * 使用场景：wuxla V61新增
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public abstract UFDouble getNcalcostmny();

  public abstract UFDouble getNcalcostprice();

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
    return this.m_ncostfactor7;
  }

  @Override
  public UFDouble getNcostfactor8() {
    return this.m_ncostfactor8;
  }

  public UFDouble getNcurrentaccumsettlenum() {
    return this.m_ncurrentaccumsettlenum;
  }

  public UFDouble getNcurrentinvoicesettlemny() {
    return this.m_ncurrentinvoicesettlemny;
  }

  public UFDouble getNcurrentsettlenum() {
    return this.m_ncurrentsettlenum;
  }

  public UFDouble getNcurrenttotalsettlemoney() {
    return this.m_ncurrenttotalsettlemoney;
  }

  @Override
  public UFDouble getNdiscount() {
    return this.m_ndiscount;
  }

  /**
   * 暂估记成本金额
   * <p>
   * 使用场景：wuxla V61新增
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public abstract UFDouble getNestcalcostmny();

  /**
   * 暂估记成本单价
   * <p>
   * 使用场景：
   * <ul>
   * <li>wuxla v61
   * </ul>
   * 
   * @return
   */
  public abstract UFDouble getNestcalcostprice();

  public abstract UFDouble getNestmny();

  /**
   * 暂估成本数量
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author wangyf
   * @time 2010-4-2 下午02:13:12
   */
  public abstract UFDouble getNestnum();

  public abstract UFDouble getNestprice();

  public abstract UFDouble getNinassistnum();

  public abstract UFDouble getNinnum();

  /**
   * 本币无税金额 <br>
   * 如果确认过成本，则为确认成本的金额
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-6-8 下午01:11:07
   */
  public abstract UFDouble getNmny();

  /**
   * 主本币无税净价：确认成本的单价<br>
   * 也用于发票入库单自动结算规则的匹配（当用户勾选主无税单价相同时）
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-6-8 下午01:10:37
   */
  public abstract UFDouble getNnetprice();

  /**
   * 入库单的主原币无税净价（如无外币，则返回主本币无税净价）<br>
   * 用于红蓝入库单自动结算规则的匹配（当用户勾选主无税净价相同时）
   * 
   * @return
   */
  public abstract UFDouble getNorignetprice();

  /**
   * 方法功能描述：入库单确认应付价税合计。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-27 下午06:47:12
   */
  public UFDouble getNorigtaxmny() {
    return UFDouble.ZERO_DBL;
  }

  /**
   * 方法功能描述：确认应付的原币价含税单价(为入库单价)。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-27 下午06:21:00
   */
  public UFDouble getNorigtaxnetprice() {
    return UFDouble.ZERO_DBL;
  }

  public UFDouble getNpuresettlemny() {
    return this.m_npuresettlemny;
  }

  public UFDouble getNpuresettlenum() {
    return this.m_npuresettlenum;
  }

  public UFDouble getNresidualsettlenum() {
    return this.m_nresidualsettlenum;
  }

  public abstract String getPk_batchcode();

  public abstract String getPk_costregion();

  /**
   * 方法功能描述：取得<b>部门</b>属性。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-27 下午09:41:46
   */
  public abstract String getPk_dept();

  public abstract String getPk_dept_v();

  /**
   * 方法功能描述：取得<b>直运销售订单行</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getPk_dtransalebid();

  /**
   * 方法功能描述：取得<b>直运销售订单</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getPk_dtransaleid();

  public abstract String getPk_financeorg();

  public abstract String getPk_group();

  public abstract String getPk_material();

  public abstract String getPk_order();

  public abstract String getPk_order_b();

  public abstract String getPk_org();

  public abstract String getPk_org_v();

  /**
   * 方法功能描述：取得<b>业务员</b>属性。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-27 下午09:41:46
   */
  public abstract String getPk_psndoc();

  @Override
  public abstract String getPk_srcmaterial();

  public abstract String getPk_stockps();

  /**
   * 方法功能描述：取得<b>质量等级</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  // public abstract String getCqualitylevelid();
  public abstract String getPk_stockps_b();

  public abstract String getPk_supplier();

  public abstract String getVbatchcode();

  public abstract String getVbillcode();

  public abstract String getVchangerate();

  /** 采购合同号 getter 方法 */
  public abstract String getVctcode();

  /**
   * 方法功能描述：取得<b>源头单据号</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getVfirstcode();

  public abstract String getVfirstrowno();

  /**
   * 方法功能描述：取得<b>源头交易类型</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getVfirsttrantype();

  public abstract String getVfree1();

  public abstract String getVfree10();

  public abstract String getVfree2();

  public abstract String getVfree3();

  public abstract String getVfree4();

  public abstract String getVfree5();

  public abstract String getVfree6();

  public abstract String getVfree7();

  public abstract String getVfree8();

  public abstract String getVfree9();

  public abstract String getVnotebody();

  public abstract String getVordercode();

  public abstract String getVordertrantypecode();

  /**
   * 方法功能描述：取得<b>上层单据号</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getVsourcecode();

  /**
   * 方法功能描述：取得<b>上层单据行号</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getVsourcerowno();

  /**
   * 方法功能描述：取得<b>上层交易类型</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public abstract String getVsourcetrantype();

  public abstract String getVtrantypecode();

  /** 设置是否影响成本标志 **/
  public abstract void setBaffectcost(UFBoolean flag);

  /** 设置是否影响利润中心成本标志 **/
  public abstract void setBaffectpciacost(UFBoolean flag);

  /**
   * 入库日期
   * 
   * @param dbilldate
   */
  public abstract void setDbilldate(UFDate dbilldate);

  /**
   * 累计暂估冲销金额
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wangyf
   * @time 2010-6-10 下午02:04:36
   */
  public abstract void setNaccestcostwashmny(UFDouble value);

  /**
   * 方法功能描述：累计调整确认应付原币价税合计。
   * <p>
   * <b>参数说明</b>
   * 
   * @param nacctoapadjstotmny <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-27 下午06:22:18
   */
  public void setNacctoapadjstotmny(UFDouble nacctoapadjstotmny) {
    if (nacctoapadjstotmny == null) {
      return;
      //
    }
  }

  /**
   * 累计确认成本冲销金额：
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-6-10 下午02:04:08
   */
  public abstract void setNacctocostadjstmny(UFDouble value);

  @Override
  public void setNadjustmny(UFDouble nadjustmny) {
    this.m_nadjustmny = nadjustmny;
  }

  /**
   * @param nallotmoney 要设置的 nallotmoney
   */
  public void setNallotmoney(UFDouble nallotmoney) {
    this.m_nallotmoney = nallotmoney;
  }

  /**
   * @param nallotnum 要设置的 nallotnum
   */
  public void setNallotnum(UFDouble nallotnum) {
    this.m_nallotnum = nallotnum;
  }

  public void setNavgsettleprice(UFDouble navgsettleprice) {
    this.m_navgsettleprice = navgsettleprice;
  }

  /**
   * 计成本可结算金额
   * 
   * @param ncansettlenmny
   */
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
    this.m_ncostfactor7 = ncostfactor7;
  }

  @Override
  public void setNcostfactor8(UFDouble ncostfactor8) {
    this.m_ncostfactor8 = ncostfactor8;
  }

  public void setNcurrentaccumsettlenum(UFDouble ncurrentaccumsettlenum) {
    this.m_ncurrentaccumsettlenum = ncurrentaccumsettlenum;
  }

  public void setNcurrentinvoicesettlemny(UFDouble ncurrentsettlemny) {
    this.m_ncurrentinvoicesettlemny = ncurrentsettlemny;
  }

  public void setNcurrentsettlenum(UFDouble ncurrentsettlenum) {
    this.m_ncurrentsettlenum = ncurrentsettlenum;
  }

  public void setNcurrenttotalsettlemoney(UFDouble ncurrentotalsettlemny) {
    this.m_ncurrenttotalsettlemoney = ncurrentotalsettlemny;
  }

  @Override
  public void setNdiscount(UFDouble ndiscount) {
    this.m_ndiscount = ndiscount;
  }

  /**
   * 方法功能描述：入库单确认应付价税合计。
   * <p>
   * <b>参数说明</b>
   * 
   * @param norigtaxmny <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-27 下午06:47:32
   */
  public void setNorigtaxmny(UFDouble norigtaxmny) {//
    if (norigtaxmny == null) {
      return;
    }
  }

  /**
   * 方法功能描述：确认应付的原币价含税单价。
   * <p>
   * <b>参数说明</b>
   * 
   * @param norigtaxnetprice <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-27 下午06:21:35
   */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {//
    if (norigtaxnetprice == null) {
      return;
    }
  }

  public void setNpuresettlemny(UFDouble npuresettlemny) {
    this.m_npuresettlemny = npuresettlemny;
  }

  public void setNpuresettlenum(UFDouble npuresettlenum) {
    this.m_npuresettlenum = npuresettlenum;
  }

  public void setNresidualsettlenum(UFDouble nresidualsettlenum) {
    this.m_nresidualsettlenum = nresidualsettlenum;
  }

}
