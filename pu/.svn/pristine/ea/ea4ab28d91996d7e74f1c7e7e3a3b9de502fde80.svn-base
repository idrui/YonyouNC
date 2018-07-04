package nc.vo.pu.m27.entity;

import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

public class PurchaseInSettleVO extends StockSettleVO {

  public static final String BAFFECTCOST = PurchaseinFIItemVO.BAFFECTCOST;

  public static final String FDIRTOAPTYPE = PurchaseinFIItemVO.FTOAPFLAG;

  /** 源头单据标识 */
  public static final String FDIRTOIATYPE = PurchaseinFIItemVO.FTOIAFLAG;

  public static final String IESTIMATEAPTIMES = "iestimateaptimes";// 入库结算信息表头_主键

  /** 相应的确认应付原币含税单价　 **/
  public static final String NACCTOAPADJSTOTMNY =
      PurchaseinFIItemVO.NACCTOAPADJSTOTMNY;

  // 保证编译通过临时处理
  public static final String NACCUREVCOSTNUM = "naccurevcostnum";

  public static final String NDIRTOCOSTNUM = PurchaseinFIItemVO.NINNUM;

  /** 入库单确认应付原币含税单价 **/
  public static final String NORIGTAXNETPRICE =
      PurchaseinFIItemVO.NORIGTAXNETPRICE;

  public static final String PK_APFINANCEORG =
      PurchaseinFIItemVO.PK_APFINANCEORG;

  public static final String PK_APFINANCEORG_V =
      PurchaseinFIItemVO.PK_APFINANCEORG_V;

  public static final String PK_DTRANSALEBID =
      PurchaseinFIItemVO.PK_DTRANSALEBID;

  public static final String PK_DTRANSALEID = PurchaseinFIItemVO.PK_DTRANSALEID;

  private static final long serialVersionUID = 9204872287324028190L;

  @Override
  public Object getAttributeValue(String name) {

    // 注意：只有和stocksettlevo不一样的NAME才需写IF，否则容易死循环
    // if (StockSettleVO.NESTNUM.equals(name)) {
    // return getNestnum();
    // }
    return super.getAttributeValue(name);

  }

  /** 影响成本标志 **/
  @Override
  public UFBoolean getBaffectcost() {
    return (UFBoolean) this.getAttributeValue(PurchaseinFIItemVO.BAFFECTCOST);
  }

  @Override
  public UFBoolean getBaffectpciacost() {
    return (UFBoolean) this.getAttributeValue(PurchaseinFIItemVO.BAFFECTPCCOST);
  }

  @Override
  public UFBoolean getBnormpur() {
    return (UFBoolean) this.getAttributeValue(PurchaseinFIHeaderVO.BNORMPUR);
  }

  @Override
  public UFBoolean getBsettlefinish() {
    return (UFBoolean) this.getAttributeValue(StockSettleVO.BSETTLEFINISH);
  }

  @Override
  public String getCastunitid() {
    return (String) this.getAttributeValue(StockSettleVO.CASTUNITID);
  }

  @Override
  public String getCbilltypecode() {
    return (String) this.getAttributeValue(StockSettleVO.CBILLTYPECODE);
  }

  @Override
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(StockSettleVO.CCURRENCYID);
  }

  /**
   * 方法功能描述：取得<b>源头单据行主键</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  @Override
  public String getCfirstbid() {
    return (String) this.getAttributeValue(StockSettleVO.CFIRSTBID);
  }

  @Override
  public String getCfirstid() {
    return (String) this.getAttributeValue(StockSettleVO.CFIRSTID);
  }

  /**
   * 方法功能描述：取得<b>源头单据类型</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  @Override
  public String getCfirsttypecode() {
    return (String) this.getAttributeValue(StockSettleVO.CFIRSTTYPECODE);
  }

  /**
   * 方法功能描述：取得<b>原币币种</b>属性。
   * 
   * @param
   * @return String
   * @author wangyf
   * @time 2009-6-30 15:20:05
   */
  @Override
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(StockSettleVO.CORIGCURRENCYID);
  }

  /**
   * 方法功能描述：取得<b>生产厂商</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  @Override
  public String getCproductorid() {
    return (String) this.getAttributeValue(StockSettleVO.CPRODUCTORID);
  }

  /**
   * 方法功能描述：取得<b>项目</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  @Override
  public String getCprojectid() {
    return (String) this.getAttributeValue(StockSettleVO.CPROJECTID);
  }

  /**
   * 方法功能描述：取得<b>上层单据行主键</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  @Override
  public String getCsourcebid() {
    return (String) this.getAttributeValue(StockSettleVO.CSOURCEBID);
  }

  /**
   * 方法功能描述：取得<b>上层单据主键</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  @Override
  public String getCsourceid() {
    return (String) this.getAttributeValue(StockSettleVO.CSOURCEID);
  }

  /**
   * 方法功能描述：取得<b>上层单据类型</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  @Override
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(StockSettleVO.CSOURCETYPECODE);
  }

  @Override
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(StockSettleVO.CTRANTYPEID);
  }

  /**
   * 方法功能描述：取得<b>主计量单位</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  @Override
  public String getCunitid() {
    return (String) this.getAttributeValue(StockSettleVO.CUNITID);
  }

  /**
   * 入库日期
   * 
   * @return
   */
  @Override
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(PurchaseinFIItemVO.DBIZDATE);
  }

  /**
   * 方法功能描述：设置<b>传应付</b>属性。
   * 
   * @param UFBoolean bconfirmap
   * @return
   * @author wangyf
   * @time 2009-6-30 13:38:31
   */
  @Override
  public Integer getFdirtoaptype() {
    return (Integer) this.getAttributeValue(PurchaseInSettleVO.FDIRTOAPTYPE);
  }

  /**
   * 方法功能描述：设置<b>传应付</b>属性。
   * 
   * @param UFBoolean bconfirmap
   * @return
   * @author wangyf
   * @time 2009-6-30 13:38:31
   */
  @Override
  public Integer getFdirtoiatype() {
    return (Integer) this.getAttributeValue(PurchaseInSettleVO.FDIRTOIATYPE);
  }

  @Override
  public IDataViewMeta getMetaData() {
    // if (this.m_viewMeta == null) {
    // Class<? extends ISuperVO> headClazz = PurchaseinFIHeaderVO.class;
    // Class<? extends ISuperVO> itemClazz = PurchaseinFIItemVO.class;
    // // 视图的主vo为子表vo
    // this.m_viewMeta =
    // new DataViewMeta(itemClazz, new String[] {
    // PurchaseinFIItemVO.BSETTLEFINISH, PurchaseinFIItemVO.CFIRSTBID,
    // PurchaseinFIItemVO.CFIRSTID, PurchaseinFIItemVO.CFIRSTTYPECODE,
    // PurchaseinFIItemVO.PK_STOCKPS, PurchaseinFIItemVO.CSOURCEBID,
    // PurchaseinFIItemVO.CSOURCEID, PurchaseinFIItemVO.CSOURCETYPECODE,
    // PurchaseinFIItemVO.FTOIAFLAG, PurchaseinFIItemVO.FTOAPFLAG,
    // PurchaseinFIItemVO.BAFFECTCOST, PurchaseinFIItemVO.NACCUMSETTLENUM,
    // PurchaseinFIItemVO.NINNUM, PurchaseinFIItemVO.NINASSISTNUM,
    // PurchaseinFIItemVO.NESTNUM, PurchaseinFIItemVO.NESTMNY,
    // PurchaseinFIItemVO.NESTPRICE,
    // PurchaseinFIItemVO.NACCESTCOSTSTTLNUM,
    // PurchaseinFIItemVO.NACCESTCOSTWASHMNY,
    // PurchaseinFIItemVO.NACCTOCOSTADJSTMNY, PurchaseinFIItemVO.NMNY,
    // PurchaseinFIItemVO.NORIGTAXNETPRICE,
    // PurchaseinFIItemVO.NACCTOAPADJSTOTMNY,
    //
    // PurchaseinFIItemVO.NORIGTAXMNY, PurchaseinFIItemVO.NNETPRICE,
    // PurchaseinFIItemVO.PK_COSTREGION,
    // PurchaseinFIItemVO.CORIGCURRENCYID, PurchaseinFIItemVO.CCURRENCYID,
    // PurchaseinFIItemVO.PK_GROUP, PurchaseinFIItemVO.PK_MATERIAL,
    // PurchaseinFIItemVO.CASTUNITID, PurchaseinFIItemVO.CUNITID,
    // PurchaseinFIItemVO.PK_SRCMATERIAL, PurchaseinFIItemVO.PK_ORDER,
    // PurchaseinFIItemVO.PK_ORDER_B, PurchaseinFIItemVO.PK_STOCKPS_B,
    // PurchaseinFIItemVO.VSOURCECODE, PurchaseinFIItemVO.VSOURCEROWNO,
    // PurchaseinFIItemVO.VSOURCETRANTYPE, PurchaseinFIItemVO.VFIRSTROWNO,
    // PurchaseinFIItemVO.VFIRSTTRANTYPE, PurchaseinFIItemVO.VFIRSTCODE,
    // PurchaseinFIItemVO.PK_SUPPLIER, PurchaseinFIItemVO.VFREE1,
    // PurchaseinFIItemVO.VFREE2, PurchaseinFIItemVO.VFREE3,
    // PurchaseinFIItemVO.VFREE4, PurchaseinFIItemVO.VFREE5,
    // PurchaseinFIItemVO.VFREE6, PurchaseinFIItemVO.VFREE7,
    // PurchaseinFIItemVO.VFREE8, PurchaseinFIItemVO.VFREE9,
    // PurchaseinFIItemVO.VFREE10, PurchaseinFIItemVO.VNOTEBODY,
    // PurchaseinFIItemVO.VBATCHCODE, PurchaseinFIItemVO.VCHANGERATE,
    // PurchaseinFIItemVO.NCOSTMNY,
    // PurchaseinFIItemVO.NCOSTPRICE,
    // PurchaseinFIItemVO.PK_FINANCEORG,
    // PurchaseinFIItemVO.PK_FINANCEORG_V,
    // PurchaseinFIItemVO.PK_APFINANCEORG,
    // PurchaseinFIItemVO.PK_APFINANCEORG_V,
    // PurchaseinFIItemVO.TS,
    // PurchaseinFIItemVO.NACCPREESTSTTLMNY,
    // PurchaseinFIItemVO.CPROJECTID,
    // PurchaseinFIItemVO.CPRODUCTORID,
    // PurchaseinFIItemVO.NACCGOODSSETTLEMNY
    // // wuxla v61
    // , PurchaseinFIItemVO.NESTCALCOSTMNY,
    // PurchaseinFIItemVO.NCALCOSTMNY,
    // PurchaseinFIItemVO.NESTCALCOSTPRICE,
    // PurchaseinFIItemVO.NCALCOSTPRICE, PurchaseinFIItemVO.NORIGNETPRICE
    // });
    // // 视图vo还要包含主表vo
    // this.m_viewMeta.add(headClazz, new String[] {
    // PurchaseinFIHeaderVO.BNORMPUR, PurchaseinFIHeaderVO.PK_BUSITYPE,
    // PurchaseinFIHeaderVO.PK_GROUP, PurchaseinFIHeaderVO.PK_ORG,
    // PurchaseinFIHeaderVO.PK_ORG_V, PurchaseinFIHeaderVO.PK_STOCKPS,
    // PurchaseinFIHeaderVO.PK_STORDOC, PurchaseinFIHeaderVO.VBILLCODE,
    // PurchaseinFIHeaderVO.CBILLTYPECODE, PurchaseinFIHeaderVO.VTRANTYPECODE,
    // PurchaseinFIHeaderVO.CTRANTYPEID, PurchaseinFIHeaderVO.PK_DEPT,
    // PurchaseinFIHeaderVO.PK_DEPT_V, PurchaseinFIHeaderVO.PK_PSNDOC,
    // PurchaseinFIHeaderVO.TS
    //
    // });
    // // 设置关联字段
    //
    // this.m_viewMeta.addRelation(itemClazz, PurchaseinFIItemVO.PK_STOCKPS,
    // headClazz, PurchaseinFIHeaderVO.PK_STOCKPS);
    // }
    // return this.m_viewMeta;
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        PurchaseInSettleVOMeta.class);

  }

  @Override
  public UFDouble getNaccestcoststtlnum() {
    return (UFDouble) this.getAttributeValue(StockSettleVO.NACCESTCOSTSTTLNUM);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.m27.entity.StockSettleVO#getNaccestcostwashmny()
   */
  @Override
  public UFDouble getNaccestcostwashmny() {
    return (UFDouble) this.getAttributeValue(StockSettleVO.NACCESTCOSTWASHMNY);
  }

  @Override
  public UFDouble getNaccgoodssettlemny() {
    return (UFDouble) this.getAttributeValue(StockSettleVO.NACCGOODSSETTLEMNY);
  }

  @Override
  public UFDouble getNaccpreeststtlmny() {
    return (UFDouble) this
        .getAttributeValue(PurchaseinFIItemVO.NACCPREESTSTTLMNY);
  }

  @Override
  public UFDouble getNacctoapadjstotmny() {
    return (UFDouble) this
        .getAttributeValue(PurchaseInSettleVO.NACCTOAPADJSTOTMNY);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.m27.entity.StockSettleVO#getNacctocostadjstmny()
   */
  @Override
  public UFDouble getNacctocostadjstmny() {
    return (UFDouble) this.getAttributeValue(StockSettleVO.NACCTOCOSTADJSTMNY);
  }

  @Override
  public UFDouble getNaccumsettlenum() {
    return (UFDouble) this.getAttributeValue(StockSettleVO.NACCUMSETTLENUM);
  }

  @Override
  public UFDouble getNaccurevcostnum() {
    return (UFDouble) this
        .getAttributeValue(PurchaseInSettleVO.NACCUREVCOSTNUM);
  }

  @Override
  public UFDouble getNcalcostmny() {
    return (UFDouble) this.getAttributeValue(StockSettleVO.NCALCOSTMNY);
  }

  @Override
  public UFDouble getNcalcostprice() {
    // wuxla v61
    return (UFDouble) this.getAttributeValue(StockSettleVO.NCALCOSTPRICE);
  }

  @Override
  public UFDouble getNestcalcostmny() {
    return (UFDouble) this.getAttributeValue(StockSettleVO.NESTCALCOSTMNY);
  }

  @Override
  public UFDouble getNestcalcostprice() {
    // wuxla v61
    return (UFDouble) this.getAttributeValue(StockSettleVO.NESTCALCOSTPRICE);
  }

  @Override
  public UFDouble getNestmny() {
    return (UFDouble) this.getAttributeValue(StockSettleVO.NESTMNY);
  }

  @Override
  public UFDouble getNestnum() {
    return (UFDouble) this.getAttributeValue(StockSettleVO.NESTNUM);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.m27.entity.StockSettleVO#getNestprice()
   */
  @Override
  public UFDouble getNestprice() {
    return (UFDouble) this.getAttributeValue(StockSettleVO.NESTPRICE);
  }

  @Override
  public UFDouble getNinassistnum() {
    return (UFDouble) this.getAttributeValue(StockSettleVO.NINASSISTNUM);
  }

  @Override
  public UFDouble getNinnum() {
    return (UFDouble) this.getAttributeValue(StockSettleVO.NINNUM);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.m27.entity.StockSettleVO#getNmny()
   */
  @Override
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(StockSettleVO.NMNY);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.m27.entity.StockSettleVO#getNnetprice()
   */
  @Override
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(StockSettleVO.NNETPRICE);
  }

  @Override
  public UFDouble getNorignetprice() {
    // wuxla v61
    return (UFDouble) this.getAttributeValue(StockSettleVO.NORIGNETPRICE);
  }

  @Override
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(PurchaseinFIItemVO.NORIGTAXMNY);
  }

  @Override
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this
        .getAttributeValue(PurchaseInSettleVO.NORIGTAXNETPRICE);
  }

  public String getPk_apfinanceorg() {
    return (String) this.getAttributeValue(PurchaseInSettleVO.PK_APFINANCEORG);
  }

  public String getPk_apfinanceorg_v() {
    return (String) this
        .getAttributeValue(PurchaseInSettleVO.PK_APFINANCEORG_V);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.m27.entity.StockSettleVO#getPk_batchcode()
   */
  @Override
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(StockSettleVO.PK_BATCHCODE);
  }

  @Override
  public String getPk_costregion() {
    return (String) this.getAttributeValue(StockSettleVO.PK_COSTREGION);
  }

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
  @Override
  public String getPk_dept() {
    return (String) this.getAttributeValue(StockSettleVO.PK_DEPT);
  }

  @Override
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(StockSettleVO.PK_DEPT_V);
  }

  /**
   * 方法功能描述：取得<b>直运销售订单行</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  @Override
  public String getPk_dtransalebid() {
    return (String) this.getAttributeValue(PurchaseInSettleVO.PK_DTRANSALEBID);
  }

  /**
   * 方法功能描述：取得<b>直运销售订单</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  @Override
  public String getPk_dtransaleid() {
    return (String) this.getAttributeValue(PurchaseInSettleVO.PK_DTRANSALEID);
  }

  @Override
  public String getPk_financeorg() {
    return (String) this.getAttributeValue(StockSettleVO.PK_FINANCEORG);
  }

  @Override
  public String getPk_group() {
    return (String) this.getAttributeValue(StockSettleVO.PK_GROUP);
  }

  @Override
  public String getPk_material() {
    return (String) this.getAttributeValue(StockSettleVO.PK_MATERIAL);
  }

  @Override
  public String getPk_order() {
    return (String) this.getAttributeValue(StockSettleVO.PK_ORDER);
  }

  @Override
  public String getPk_order_b() {
    return (String) this.getAttributeValue(StockSettleVO.PK_ORDER_B);
  }

  @Override
  public String getPk_org() {
    return (String) this.getAttributeValue(StockSettleVO.PK_ORG);
  }

  @Override
  public String getPk_org_v() {
    return (String) this.getAttributeValue(StockSettleVO.PK_ORG_V);
  }

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
  @Override
  public String getPk_psndoc() {
    return (String) this.getAttributeValue(StockSettleVO.PK_PSNDOC);
  }

  @Override
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(StockSettleVO.PK_SRCMATERIAL);
  }

  @Override
  public String getPk_stockps() {
    return (String) this.getAttributeValue(StockSettleVO.PK_STOCKPS);
  }

  @Override
  public String getPk_stockps_b() {
    return (String) this.getAttributeValue(StockSettleVO.PK_STOCKPS_B);
  }

  @Override
  public String getPk_supplier() {
    return (String) this.getAttributeValue(StockSettleVO.PK_SUPPLIER);
  }

  @Override
  public String getVbatchcode() {
    return (String) this.getAttributeValue(StockSettleVO.VBATCHCODE);
  }

  @Override
  public String getVbillcode() {
    return (String) this.getAttributeValue(StockSettleVO.VBILLCODE);
  }

  @Override
  public String getVchangerate() {
    return (String) this.getAttributeValue(StockSettleVO.VCHANGERATE);
  }

  /** 采购合同号 getter 方法 */
  @Override
  public String getVctcode() {
    return (String) this.getAttributeValue(PurchaseinFIItemVO.VCTCODE);
  }

  /**
   * 方法功能描述：取得<b>源头单据号</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  @Override
  public String getVfirstcode() {
    return (String) this.getAttributeValue(StockSettleVO.VFIRSTCODE);
  }

  @Override
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(StockSettleVO.VFIRSTROWNO);
  }

  /**
   * 方法功能描述：取得<b>源头交易类型</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  @Override
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(StockSettleVO.VFIRSTTRANTYPE);
  }

  @Override
  public String getVfree1() {
    return (String) this.getAttributeValue(StockSettleVO.VFREE1);
  }

  @Override
  public String getVfree10() {
    return (String) this.getAttributeValue(StockSettleVO.VFREE10);
  }

  @Override
  public String getVfree2() {
    return (String) this.getAttributeValue(StockSettleVO.VFREE2);
  }

  @Override
  public String getVfree3() {
    return (String) this.getAttributeValue(StockSettleVO.VFREE3);
  }

  @Override
  public String getVfree4() {
    return (String) this.getAttributeValue(StockSettleVO.VFREE4);
  }

  @Override
  public String getVfree5() {
    return (String) this.getAttributeValue(StockSettleVO.VFREE5);
  }

  @Override
  public String getVfree6() {
    return (String) this.getAttributeValue(StockSettleVO.VFREE6);
  }

  @Override
  public String getVfree7() {
    return (String) this.getAttributeValue(StockSettleVO.VFREE7);
  }

  @Override
  public String getVfree8() {
    return (String) this.getAttributeValue(StockSettleVO.VFREE8);
  }

  @Override
  public String getVfree9() {
    return (String) this.getAttributeValue(StockSettleVO.VFREE9);
  }

  @Override
  public String getVnotebody() {
    return (String) this.getAttributeValue(StockSettleVO.VNOTEBODY);
  }

  @Override
  public String getVordercode() {
    return (String) this.getAttributeValue(StockSettleVO.VORDERCODE);
  }

  @Override
  public String getVordertrantypecode() {
    return (String) this.getAttributeValue(StockSettleVO.VORDERTRANTYPECODE);
  }

  /**
   * 方法功能描述：取得<b>上层单据号</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  @Override
  public String getVsourcecode() {
    return (String) this.getAttributeValue(StockSettleVO.VSOURCECODE);
  }

  /**
   * 方法功能描述：取得<b>上层单据行号</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  @Override
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(StockSettleVO.VSOURCEROWNO);
  }

  /**
   * 方法功能描述：取得<b>上层交易类型</b>属性。
   * 
   * @param
   * @return String
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  @Override
  public String getVsourcetrantype() {
    return (String) this.getAttributeValue(StockSettleVO.VSOURCETRANTYPE);
  }

  @Override
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(StockSettleVO.VTRANTYPECODE);
  }

  /** 影响成本标志 **/
  @Override
  public void setBaffectcost(UFBoolean baffectcost) {
    this.setAttributeValue(PurchaseinFIItemVO.BAFFECTCOST, baffectcost);
  }

  @Override
  public void setBaffectpciacost(UFBoolean baffectpccost) {
    this.setAttributeValue(PurchaseinFIItemVO.BAFFECTPCCOST, baffectpccost);
  }

  public void setBsettlefinish(UFBoolean bsettlefinish) {
    this.setAttributeValue(StockSettleVO.BSETTLEFINISH, bsettlefinish);
  }

  public void setCbilltypecode(String vbilltypecode) {
    this.setAttributeValue(StockSettleVO.CBILLTYPECODE, vbilltypecode);
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
    this.setAttributeValue(StockSettleVO.CFIRSTBID, cfirstbid);
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
    this.setAttributeValue(StockSettleVO.CFIRSTID, cfirstid);
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
    this.setAttributeValue(StockSettleVO.CFIRSTTYPECODE, cfirsttypecode);
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
    this.setAttributeValue(StockSettleVO.CORIGCURRENCYID, pk_currtype);
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
    this.setAttributeValue(StockSettleVO.CPRODUCTORID, value);
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
    this.setAttributeValue(StockSettleVO.CPROJECTID, value);
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
    this.setAttributeValue(StockSettleVO.CSOURCEBID, csourcebid);
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
    this.setAttributeValue(StockSettleVO.CSOURCEID, csourceid);
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
    this.setAttributeValue(StockSettleVO.CSOURCETYPECODE, csourcetypecode);
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
    this.setAttributeValue(StockSettleVO.CUNITID, pk_measdoc);
  }

  /**
   * 入库日期
   * 
   * @param dbilldate
   */
  @Override
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(PurchaseinFIItemVO.DBIZDATE, dbilldate);
  }

  /**
   * 方法功能描述：设置<b>传成本</b>属性。
   * 
   * @param UFBoolean bconfirmap
   * @return
   * @author wangyf
   * @time 2009-6-30 13:38:31
   */
  public void setFdirecttoaptype(Integer bap) {
    this.setAttributeValue(PurchaseInSettleVO.FDIRTOAPTYPE, bap);
  }

  /**
   * 方法功能描述：设置<b>传成本</b>属性。
   * 
   * @param UFBoolean bconfirmap
   * @return
   * @author wangyf
   * @time 2009-6-30 13:38:31
   */
  public void setFdirecttoiatype(Integer bap) {
    this.setAttributeValue(PurchaseInSettleVO.FDIRTOIATYPE, bap);
  }

  public void setIestimateaptimes(Integer iestimateaptimes) {
    this.setAttributeValue(PurchaseInSettleVO.IESTIMATEAPTIMES,
        iestimateaptimes);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.m27.entity.StockSettleVO#setNaccumestclashmny()
   */
  @Override
  public void setNaccestcostwashmny(UFDouble value) {
    this.setAttributeValue(StockSettleVO.NACCESTCOSTWASHMNY, value);
  }

  public void setNaccgoodssettlemny(UFDouble naccumsettlemny) {
    this.setAttributeValue(StockSettleVO.NACCGOODSSETTLEMNY, naccumsettlemny);
  }

  @Override
  public void setNacctoapadjstotmny(UFDouble nacctoapadjstotmny) {
    this.setAttributeValue(PurchaseInSettleVO.NACCTOAPADJSTOTMNY,
        nacctoapadjstotmny);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.m27.entity.StockSettleVO#setNaccumconfmclashmny()
   */
  @Override
  public void setNacctocostadjstmny(UFDouble value) {
    this.setAttributeValue(StockSettleVO.NACCTOCOSTADJSTMNY, value);

  }

  public void setNaccumsettlenum(UFDouble naccumsettlenum) {
    this.setAttributeValue(StockSettleVO.NACCUMSETTLENUM, naccumsettlenum);
  }

  public void setNaccurevcostnum(String naccurevcostnum) {
    this.setAttributeValue(PurchaseInSettleVO.NACCUREVCOSTNUM, naccurevcostnum);
  }

  public void setNinnum(UFDouble ninnum) {
    this.setAttributeValue(StockSettleVO.NINNUM, ninnum);
  }

  public void setNorigprice(String nprice) {
    this.setAttributeValue(StockSettleVO.NORIGPRICE, nprice);
  }

  @Override
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.setAttributeValue(PurchaseinFIItemVO.NORIGTAXMNY, norigtaxmny);
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
  @Override
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.setAttributeValue(PurchaseInSettleVO.NORIGTAXNETPRICE,
        norigtaxnetprice);
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
  public void setPk_costregion(String pk_costregion) {
    this.setAttributeValue(StockSettleVO.PK_COSTREGION, pk_costregion);
  }

  /**
   * 方法功能描述：设置<b>部门</b>属性。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_dept <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-27 下午09:41:17
   */
  public void setPk_dept(String pk_dept) {
    this.setAttributeValue(StockSettleVO.PK_DEPT, pk_dept);
  }

  /**
   * 方法功能描述：设置<b>直运销售订单行</b>属性。
   * 
   * @param String vfree1
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setpk_dtransalebid(String value) {
    this.setAttributeValue(PurchaseInSettleVO.PK_DTRANSALEBID, value);
  }

  /**
   * 方法功能描述：设置<b>直运销售订单ID</b>属性。
   * 
   * @param String vfree1
   * @return
   * @author zhaoyha
   * @time 2009-6-30 17:47:56
   */
  public void setpk_dtransaleid(String value) {
    this.setAttributeValue(PurchaseInSettleVO.PK_DTRANSALEID, value);
  }

  public void setPk_financeorg(String pk_financeorg) {
    this.setAttributeValue(StockSettleVO.PK_FINANCEORG, pk_financeorg);
  }

  public void setPk_group(String pk_group) {
    this.setAttributeValue(StockSettleVO.PK_GROUP, pk_group);
  }

  public void setPk_material(String pk_material) {
    this.setAttributeValue(StockSettleVO.PK_MATERIAL, pk_material);
  }

  public void setPk_order(String pk_order) {
    this.setAttributeValue(StockSettleVO.PK_ORDER, pk_order);
  }

  public void setPk_order_b(String pk_order_b) {
    this.setAttributeValue(StockSettleVO.PK_ORDER_B, pk_order_b);
  }

  public void setPk_org(String pk_stockorg) {
    this.setAttributeValue(StockSettleVO.PK_ORG, pk_stockorg);
  }

  public void setPk_org_v(String pk_stockorg_v) {
    this.setAttributeValue(StockSettleVO.PK_ORG_V, pk_stockorg_v);
  }

  /**
   * 方法功能描述：设置<b>业务员</b>属性。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_dept <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-27 下午09:41:17
   */
  public void setPk_psndoc(String pk_psndoc) {
    this.setAttributeValue(StockSettleVO.PK_PSNDOC, pk_psndoc);
  }

  public void setPk_srcmaterial(String pk_material) {
    this.setAttributeValue(StockSettleVO.PK_SRCMATERIAL, pk_material);
  }

  public void setPk_stockps(String pk_stockps) {
    this.setAttributeValue(StockSettleVO.PK_STOCKPS, pk_stockps);
  }

  public void setPk_stockps_b(String pk_stockps_b) {
    this.setAttributeValue(StockSettleVO.PK_STOCKPS_B, pk_stockps_b);
  }

  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(StockSettleVO.PK_SUPPLIER, pk_supplier);
  }

  public void setVbatchcode(String vbatchcode) {
    this.setAttributeValue(StockSettleVO.VBATCHCODE, vbatchcode);
  }

  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(StockSettleVO.VBILLCODE, vbillcode);
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
    this.setAttributeValue(StockSettleVO.VFIRSTCODE, vfirstcode);
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
    this.setAttributeValue(StockSettleVO.VFIRSTROWNO, vsourcebillrowno);
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
    this.setAttributeValue(StockSettleVO.VFIRSTTRANTYPE, vfirsttrantype);
  }

  public void setVfree1(String vfree1) {
    this.setAttributeValue(StockSettleVO.VFREE1, vfree1);
  }

  public void setVfree10(String vfree10) {
    this.setAttributeValue(StockSettleVO.VFREE10, vfree10);
  }

  public void setVfree2(String vfree2) {
    this.setAttributeValue(StockSettleVO.VFREE2, vfree2);
  }

  public void setVfree3(String vfree3) {
    this.setAttributeValue(StockSettleVO.VFREE3, vfree3);
  }

  public void setVfree4(String vfree4) {
    this.setAttributeValue(StockSettleVO.VFREE4, vfree4);
  }

  public void setVfree5(String vfree5) {
    this.setAttributeValue(StockSettleVO.VFREE5, vfree5);
  }

  public void setVfree6(String vfree6) {
    this.setAttributeValue(StockSettleVO.VFREE6, vfree6);
  }

  public void setVfree7(String vfree7) {
    this.setAttributeValue(StockSettleVO.VFREE7, vfree7);
  }

  public void setVfree8(String vfree8) {
    this.setAttributeValue(StockSettleVO.VFREE8, vfree8);
  }

  public void setVfree9(String vfree9) {
    this.setAttributeValue(StockSettleVO.VFREE9, vfree9);
  }

  public void setVnotebody(String vnotebody) {
    this.setAttributeValue(StockSettleVO.VNOTEBODY, vnotebody);
  }

  public void setVordercode(String vordercode) {
    this.setAttributeValue(StockSettleVO.VORDERCODE, vordercode);
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
    this.setAttributeValue(StockSettleVO.VSOURCECODE, vsourcecode);
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
    this.setAttributeValue(StockSettleVO.VSOURCEROWNO, vsourcerowno);
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
    this.setAttributeValue(StockSettleVO.VSOURCETRANTYPE, vsourcetrantype);
  }

  public void setVtrantypecode(String vtrantypecode) {
    this.setAttributeValue(StockSettleVO.VTRANTYPECODE, vtrantypecode);
  }

}
