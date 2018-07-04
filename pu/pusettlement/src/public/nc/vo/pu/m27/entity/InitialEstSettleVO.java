package nc.vo.pu.m27.entity;

import java.util.HashSet;
import java.util.Set;

import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pub.BeanHelper;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 期初暂估入库单结算所用VO
 * 
 * @since 6.0
 * @version 2011-1-28 下午10:23:22
 * @author zhaoyha
 */
public class InitialEstSettleVO extends StockSettleVO {

  /** 调用get方法取值 **/
  public static Set<String> beanAttrSet = new HashSet<String>();

  private static final long serialVersionUID = 8805840782341034792L;

  static {
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.NINNUM);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.NINASSISTNUM);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.CFIRSTBID);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.CFIRSTID);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.CFIRSTTYPECODE);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.VFIRSTCODE);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.VFIRSTROWNO);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.VFIRSTTRANTYPE);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.VORDERTRANTYPECODE);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.NACCESTAPWASHNUM);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.NACCESTCOSTWASHMNY);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.NACCUMSETTLENUM);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.VNOTEBODY);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.PK_ORG);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.PK_ORG_V);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.PK_FINANCEORG);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.PK_STOCKPS);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.PK_STOCKPS_B);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.PK_PSNDOC);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.NNETPRICE);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.NESTMNY);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.NESTNUM);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.NESTPRICE);
    InitialEstSettleVO.beanAttrSet.add(StockSettleVO.NACCESTCOSTSTTLNUM);
  }

  @Override
  public Object getAttributeValue(String name) {
    if (InitialEstSettleVO.beanAttrSet.contains(name)) {
      return BeanHelper.getProperty(this, name);
    }
    return super.getAttributeValue(name);
  }

  @Override
  public UFBoolean getBaffectcost() {
    // 与需求确认一定影响成本（zhounl,2011.2.18)
    // return UFBoolean.TRUE;
    // V61调整为看一看物料价值属性
    return (UFBoolean) this.getAttributeValue(InitialEstItemVO.BAFFECTCOST);
  }

  @Override
  public UFBoolean getBaffectpciacost() {
    return (UFBoolean) this.getAttributeValue(InitialEstItemVO.BAFFECTPCCOST);
  }

  @Override
  public UFBoolean getBnormpur() {
    return (UFBoolean) this.getAttributeValue(InitialEstHeaderVO.BNORMPUR);
  }

  @Override
  public UFBoolean getBsettlefinish() {
    return (UFBoolean) this.getAttributeValue(InitialEstItemVO.BSETTLEFINISH);
  }

  @Override
  public String getCastunitid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CASTUNITID);
  }

  @Override
  public String getCbilltypecode() {
    return POBillType.InitEstimate.getCode();
  }

  @Override
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.CCURRENCYID);
  }

  @Override
  public String getCfirstbid() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_ORDER_B);
  }

  @Override
  public String getCfirstid() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_ORDER);
  }

  @Override
  public String getCfirsttypecode() {
    return POBillType.Order.getCode();
  }

  @Override
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.CORIGCURRENCYID);
  }

  @Override
  public String getCproductorid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CPRODUCTORID);
  }

  @Override
  public String getCprojectid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CPROJECTID);
  }

  @Override
  public String getCsourcebid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CSOURCEBID);
  }

  @Override
  public String getCsourceid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CSOURCEID);
  }

  @Override
  public String getCsourcetypecode() {
    return (String) this.getAttributeValue(InitialEstItemVO.CSOURCETYPECODE);
  }

  @Override
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(StockSettleVO.CTRANTYPEID);
  }

  @Override
  public String getCunitid() {
    return (String) this.getAttributeValue(InitialEstItemVO.CUNITID);
  }

  @Override
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(InitialEstHeaderVO.DBILLDATE);
  }

  @Override
  public Integer getFdirtoaptype() {
    if (UFBoolean.TRUE.equals(this
        .getAttributeValue(InitialEstItemVO.BESTIMATEAP))) {
      return EnumToAPFlag.EstimateToAP.toInteger();
    }
    return EnumToAPFlag.NotToAP.toInteger();
  }

  @Override
  public Integer getFdirtoiatype() {
    return EnumToIAFlag.EstimateToIA.toInteger();
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        InitialEstSettleVOMeta.class);
    // if (null != this.m_viewMeta) {
    // return this.m_viewMeta;
    // }
    // Class<? extends ISuperVO> headClazz = InitialEstHeaderVO.class;
    // Class<? extends ISuperVO> itemClazz = InitialEstItemVO.class;
    // String[] headAttrs =
    // {
    // InitialEstHeaderVO.BNORMPUR, InitialEstHeaderVO.PK_GROUP,
    // InitialEstHeaderVO.PK_ORG, InitialEstHeaderVO.PK_ORG_V,
    // InitialEstHeaderVO.PK_INITIALEST, InitialEstHeaderVO.PK_STOCKORG,
    // InitialEstHeaderVO.PK_STOCKORG_V, InitialEstHeaderVO.VBILLCODE,
    // InitialEstHeaderVO.PK_DEPT, InitialEstHeaderVO.VTRANTYPECODE,
    // InitialEstHeaderVO.CTRANTYPEID, InitialEstHeaderVO.PK_DEPT_V,
    // InitialEstHeaderVO.PK_BUSITYPE, InitialEstHeaderVO.CCURRENCYID,
    // InitialEstHeaderVO.CORIGCURRENCYID, InitialEstHeaderVO.PK_COSTREGION,
    // InitialEstHeaderVO.PK_SUPPLIER, InitialEstHeaderVO.PK_STORDOC,
    // InitialEstHeaderVO.PK_BIZPSN, InitialEstHeaderVO.TS
    // };
    // String[] itemAttrs =
    // {
    // InitialEstItemVO.BSETTLEFINISH, InitialEstItemVO.BESTIMATEAP,
    // InitialEstItemVO.CSOURCEBID, InitialEstItemVO.CSOURCEID,
    // InitialEstItemVO.CSOURCETYPECODE, InitialEstItemVO.NNUM,
    // InitialEstItemVO.NASTNUM, InitialEstItemVO.NMNY,
    // InitialEstItemVO.NORIGTAXMNY, InitialEstItemVO.NPRICE,
    // InitialEstItemVO.NORIGTAXMNY, InitialEstItemVO.PK_MATERIAL,
    // InitialEstItemVO.CASTUNITID, InitialEstItemVO.CUNITID,
    // InitialEstItemVO.PK_SRCMATERIAL, InitialEstItemVO.PK_ORDER,
    // InitialEstItemVO.PK_ORDER_B, InitialEstItemVO.CORDERROWNO,
    // InitialEstItemVO.VORDERCODE, InitialEstItemVO.VORDERTRANTYPE,
    // InitialEstItemVO.PK_INITIALEST_B, InitialEstItemVO.VSOURCECODE,
    // InitialEstItemVO.VSOURCEROWNO, InitialEstItemVO.VSOURCETRANTYPE,
    // InitialEstItemVO.VFREE1, InitialEstItemVO.VFREE2,
    // InitialEstItemVO.VFREE3, InitialEstItemVO.VFREE4,
    // InitialEstItemVO.VFREE5, InitialEstItemVO.VFREE6,
    // InitialEstItemVO.VFREE7, InitialEstItemVO.VFREE8,
    // InitialEstItemVO.VFREE9, InitialEstItemVO.VFREE10,
    // InitialEstItemVO.VBMEMO, InitialEstItemVO.VBATCHCODE,
    // InitialEstItemVO.VCHANGERATE, InitialEstItemVO.PK_APFINANCEORG,
    // InitialEstItemVO.PK_APFINANCEORG_V, InitialEstItemVO.NACCSETTLENUM,
    // InitialEstItemVO.NACCWASHMNY, InitialEstItemVO.TS,
    // InitialEstItemVO.CPROJECTID, InitialEstItemVO.CPRODUCTORID,
    // InitialEstItemVO.NACCGOODSSETTLEMNY,
    // InitialEstItemVO.BAFFECTCOST
    // // wuxla V61
    // , InitialEstItemVO.NCALCOSTMNY, InitialEstItemVO.NORIGPRICE,
    // InitialEstItemVO.NESTCALCOSTPRICE
    // };
    // this.m_viewMeta = new DataViewMeta(itemClazz, itemAttrs);
    // this.m_viewMeta.add(headClazz, headAttrs);
    // this.m_viewMeta.addRelation(itemClazz, InitialEstItemVO.PK_INITIALEST,
    // headClazz, InitialEstItemVO.PK_INITIALEST);
    // return this.m_viewMeta;
  }

  /**
   * 期初即为累计结算数量（先暂估后结算）
   */
  @Override
  public UFDouble getNaccestcoststtlnum() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NACCSETTLENUM);
  }

  @Override
  public UFDouble getNaccestcostwashmny() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NACCWASHMNY);
  }

  @Override
  public UFDouble getNaccgoodssettlemny() {
    return (UFDouble) this.getAttributeValue(StockSettleVO.NACCGOODSSETTLEMNY);
  }

  @Override
  public UFDouble getNaccpreeststtlmny() {
    return UFDouble.ZERO_DBL;
  }

  @Override
  public UFDouble getNacctoapadjstotmny() {
    return UFDouble.ZERO_DBL;
  }

  @Override
  public UFDouble getNacctocostadjstmny() {
    return UFDouble.ZERO_DBL;
  }

  @Override
  public UFDouble getNaccumsettlenum() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NACCSETTLENUM);
  }

  @Override
  public UFDouble getNaccurevcostnum() {
    return UFDouble.ZERO_DBL;
  }

  @Override
  public UFDouble getNcalcostmny() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NCALCOSTMNY);
  }

  @Override
  public UFDouble getNcalcostprice() {
    // wuxla v61
    return null;
  }

  @Override
  public UFDouble getNestcalcostmny() {
    // wuxla v61
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NCALCOSTMNY);
  }

  @Override
  public UFDouble getNestcalcostprice() {
    // wuxla v61
    // wuxla v61
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NESTCALCOSTPRICE);
  }

  @Override
  public UFDouble getNestmny() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NMNY);
  }

  @Override
  public UFDouble getNestnum() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NNUM);
  }

  @Override
  public UFDouble getNestprice() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NPRICE);
  }

  @Override
  public UFDouble getNinassistnum() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NASTNUM);
  }

  @Override
  public UFDouble getNinnum() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NNUM);
  }

  @Override
  public UFDouble getNmny() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NMNY);
  }

  @Override
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NPRICE);
  }

  @Override
  public UFDouble getNorignetprice() {
    // wuxla v61
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NORIGPRICE);
  }

  @Override
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NORIGTAXMNY);
  }

  @Override
  public UFDouble getNorigtaxnetprice() {
    return (UFDouble) this.getAttributeValue(InitialEstItemVO.NORIGTAXPRICE);
  }

  @Override
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_BATCHCODE);
  }

  @Override
  public String getPk_costregion() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_COSTREGION);
  }

  @Override
  public String getPk_dept() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_DEPT);
  }

  @Override
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_DEPT_V);
  }

  @Override
  public String getPk_dtransalebid() {
    return null;
  }

  @Override
  public String getPk_dtransaleid() {
    return null;
  }

  @Override
  public String getPk_financeorg() {
    return (String) this.getVO(InitialEstHeaderVO.class).getAttributeValue(
        InitialEstHeaderVO.PK_ORG);
  }

  @Override
  public String getPk_group() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_GROUP);
  }

  @Override
  public String getPk_material() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_MATERIAL);
  }

  @Override
  public String getPk_order() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_ORDER);
  }

  @Override
  public String getPk_order_b() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_ORDER_B);
  }

  @Override
  public String getPk_org() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_STOCKORG);
  }

  @Override
  public String getPk_org_v() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_STOCKORG_V);
  }

  @Override
  public String getPk_psndoc() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_BIZPSN);
  }

  @Override
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_SRCMATERIAL);
  }

  @Override
  public String getPk_stockps() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_INITIALEST);
  }

  @Override
  public String getPk_stockps_b() {
    return (String) this.getAttributeValue(InitialEstItemVO.PK_INITIALEST_B);
  }

  @Override
  public String getPk_supplier() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.PK_SUPPLIER);
  }

  @Override
  public String getVbatchcode() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBATCHCODE);
  }

  @Override
  public String getVbillcode() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VBILLCODE);
  }

  @Override
  public String getVchangerate() {
    return (String) this.getAttributeValue(InitialEstItemVO.VCHANGERATE);
  }

  /** 采购合同号 getter 方法 */
  @Override
  public String getVctcode() {
    return null;
  }

  @Override
  public String getVfirstcode() {
    return (String) this.getAttributeValue(InitialEstItemVO.VORDERCODE);
  }

  @Override
  public String getVfirstrowno() {
    return (String) this.getAttributeValue(InitialEstItemVO.CORDERROWNO);
  }

  @Override
  public String getVfirsttrantype() {
    return (String) this.getAttributeValue(InitialEstItemVO.VORDERTRANTYPE);
  }

  @Override
  public String getVfree1() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE1);
  }

  @Override
  public String getVfree10() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE10);
  }

  @Override
  public String getVfree2() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE2);
  }

  @Override
  public String getVfree3() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE3);
  }

  @Override
  public String getVfree4() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE4);
  }

  @Override
  public String getVfree5() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE5);
  }

  @Override
  public String getVfree6() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE6);
  }

  @Override
  public String getVfree7() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE7);
  }

  @Override
  public String getVfree8() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE8);
  }

  @Override
  public String getVfree9() {
    return (String) this.getAttributeValue(InitialEstItemVO.VFREE9);
  }

  @Override
  public String getVnotebody() {
    return (String) this.getAttributeValue(InitialEstItemVO.VBMEMO);
  }

  @Override
  public String getVordercode() {
    return (String) this.getAttributeValue(InitialEstItemVO.VORDERCODE);
  }

  @Override
  public String getVordertrantypecode() {
    return (String) this.getAttributeValue(InitialEstItemVO.VORDERTRANTYPE);
  }

  @Override
  public String getVsourcecode() {
    return (String) this.getAttributeValue(InitialEstItemVO.VSOURCECODE);
  }

  @Override
  public String getVsourcerowno() {
    return (String) this.getAttributeValue(InitialEstItemVO.VSOURCEROWNO);
  }

  @Override
  public String getVsourcetrantype() {
    return (String) this.getAttributeValue(InitialEstItemVO.VSOURCETRANTYPE);
  }

  @Override
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(InitialEstHeaderVO.VTRANTYPECODE);
  }

  @Override
  public void setBaffectcost(UFBoolean flag) {
    // 一定影响成本
    // V61调整为看一看物料价值属性
    this.setAttributeValue(InitialEstItemVO.BAFFECTCOST, flag);
  }

  @Override
  public void setBaffectpciacost(UFBoolean flag) {
    this.setAttributeValue(InitialEstItemVO.BAFFECTPCCOST, flag);
  }

  @Override
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(InitialEstHeaderVO.DBILLDATE, dbilldate);
  }

  @Override
  public void setNaccestcostwashmny(UFDouble value) {
    this.setAttributeValue(InitialEstItemVO.NACCWASHMNY, value);
  }

  @Override
  public void setNacctocostadjstmny(UFDouble value) {
    // 期初没有直接确认成本和应付
  }
}
