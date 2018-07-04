package nc.vo.pu.m27.entity;

import nc.pubitf.uapbd.CurrencyRateUtilHelper;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m4a.entity.GeneralInBodyVO;
import nc.vo.ic.m4a.entity.GeneralInHeadVO;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * <p>
 * <b>其他入库单结算使用,说明：</b>
 * <ul>
 * <li>没有业务流程字段
 * <li>没有原币等数据字段
 * <li>没有采购订单字段
 * <li>没有暂估字段、没有累计结算字段
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-10 下午06:34:06
 */
public class GeneralInSettleVO extends StockSettleVO {

  private static final long serialVersionUID = -6591611487734054138L;

  private GeneralInBodyVO bodyvo;

  private String ccurrencyid;

  private String costregionid;

  private GeneralInHeadVO headvo;

  private String pk_financeorg;

  @Override
  public Object getAttributeValue(String name) {
    if (StockSettleVO.PK_ORG.equals(name)) {
      // 库存组织
      return this.getPk_org();
    }
    else if (StockSettleVO.PK_ORG_V.equals(name)) {
      return this.getPk_org_v();
    }
    else if (StockSettleVO.PK_STOCKPS.equals(name)) {
      // 入库单主键
      return this.getPk_stockps();
    }
    else if (StockSettleVO.VBILLCODE.equals(name)) {
      return this.getVbillcode();
    }
    else if (StockSettleVO.PK_DEPT.equals(name)) {
      // 部门
      return this.getPk_dept();
    }
    else if (StockSettleVO.PK_DEPT_V.equals(name)) {
      // 部门VID
      return this.getPk_dept_v();
    }
    else if (StockSettleVO.PK_STOCKPS_B.equals(name)) {
      // 入库单行主键
      return this.getPk_stockps_b();
    }
    else if (StockSettleVO.CFIRSTID.equals(name)) {
      return this.getCfirstid();
    }
    else if (StockSettleVO.CFIRSTBID.equals(name)) {
      return this.getCfirstbid();
    }
    else if (StockSettleVO.CFIRSTTYPECODE.equals(name)) {
      return this.getCfirsttypecode();
    }
    else if (StockSettleVO.VFIRSTTRANTYPE.equals(name)) {
      return this.getVfirsttrantype();
    }
    else if (StockSettleVO.VFIRSTCODE.equals(name)) {
      return this.getVfirstcode();
    }
    else if (StockSettleVO.VFIRSTROWNO.equals(name)) {
      return this.getVfirstrowno();
    }
    else if (StockSettleVO.CSOURCEID.equals(name)) {
      return this.getCsourceid();
    }
    else if (StockSettleVO.CSOURCEBID.equals(name)) {
      return this.getCsourcebid();
    }
    else if (StockSettleVO.CSOURCETYPECODE.equals(name)) {
      return this.getCsourcetypecode();
    }
    else if (StockSettleVO.VSOURCETRANTYPE.equals(name)) {
      return this.getVsourcetrantype();
    }
    else if (StockSettleVO.VSOURCECODE.equals(name)) {
      return this.getVsourcecode();
    }
    else if (StockSettleVO.VSOURCEROWNO.equals(name)) {
      return this.getVsourcerowno();
    }
    else if (StockSettleVO.NINNUM.equals(name)) {
      // 入库数量
      return this.getNinnum();
    }
    else if (StockSettleVO.NINASSISTNUM.equals(name)) {
      // 入库业务数量
      return this.getNinassistnum();
    }
    else if (StockSettleVO.NMNY.equals(name)) {
      // 金额
      return this.getNmny();
    }
    else if (StockSettleVO.PK_SRCMATERIAL.equals(name)) {
      // 物料OID
      return this.getPk_srcmaterial();
    }
    else if (StockSettleVO.PK_MATERIAL.equals(name)) {
      // 物料VID
      return this.getPk_material();
    }
    else if ("cunitid".equals(name)) {
      return this.getCunitid();
    }
    else if (StockSettleVO.VFREE1.equals(name)) {
      return this.getVfree1();
    }
    else if (StockSettleVO.VFREE2.equals(name)) {
      return this.getVfree2();
    }
    else if (StockSettleVO.VFREE3.equals(name)) {
      return this.getVfree3();
    }
    else if (StockSettleVO.VFREE4.equals(name)) {
      return this.getVfree4();
    }
    else if (StockSettleVO.VFREE5.equals(name)) {
      return this.getVfree5();
    }
    else if (StockSettleVO.VFREE6.equals(name)) {
      return this.getVfree6();
    }
    else if (StockSettleVO.VFREE7.equals(name)) {
      return this.getVfree7();
    }
    else if (StockSettleVO.VFREE8.equals(name)) {
      return this.getVfree8();
    }
    else if (StockSettleVO.VFREE9.equals(name)) {
      return this.getVfree9();
    }
    else if (StockSettleVO.VFREE10.equals(name)) {
      return this.getVfree10();
    }
    else if (StockSettleVO.VNOTEBODY.equals(name)) {
      // 表体备注
      return this.getVnotebody();
    }
    else if (StockSettleVO.VBATCHCODE.equals(name)) {
      return this.getVbatchcode();
    }
    else if (StockSettleVO.VCHANGERATE.equals(name)) {
      return this.getVchangerate();
    }
    else if (StockSettleVO.CASTUNITID.equals(name)) {
      return this.getCastunitid();
    }
    else if (StockSettleVO.PK_SUPPLIER.equals(name)) {
      return this.getPk_supplier();
    }

    else if (StockSettleVO.PK_PSNDOC.equals(name)) {
      return this.getPk_psndoc();
    }
    return super.getAttributeValue(name);
  }

  @Override
  public UFBoolean getBaffectcost() {
    // 查出费用结算的其它入库单一定是影响成本的（采购需求）
    return UFBoolean.TRUE;
  }

  @Override
  public UFBoolean getBaffectpciacost() {
    return UFBoolean.TRUE;
  }

  @Override
  public UFBoolean getBnormpur() {
    return UFBoolean.TRUE;
  }

  public GeneralInBodyVO getBodyvo() {
    return this.bodyvo;
  }

  @Override
  public UFBoolean getBsettlefinish() {
    return UFBoolean.FALSE;
  }

  @Override
  public String getCastunitid() {
    return this.bodyvo.getCastunitid();
  }

  @Override
  public String getCbilltypecode() {
    return ICBillType.GeneralIn.getCode();
  }

  @Override
  public String getCcurrencyid() {
    if (null == this.ccurrencyid) {
      this.ccurrencyid =
          CurrencyRateUtilHelper.getInstance().getLocalCurrtypeByOrgID(
              this.pk_financeorg);
    }
    return this.ccurrencyid;
  }

  @Override
  public String getCfirstbid() {
    return this.bodyvo.getCfirstbillbid();
  }

  @Override
  public String getCfirstid() {
    return this.bodyvo.getCfirstbillhid();
  }

  @Override
  public String getCfirsttypecode() {
    return this.bodyvo.getCfirsttype();
  }

  @Override
  public String getCorigcurrencyid() {
    // 其他入库单没有原币相关字段
    return null;
  }

  public String getCostregion() {
    return this.costregionid;
  }

  @Override
  public String getCproductorid() {
    return this.bodyvo.getCproductorid();
  }

  @Override
  public String getCprojectid() {
    return this.bodyvo.getCprojectid();
  }

  @Override
  public String getCsourcebid() {
    return this.bodyvo.getCsourcebillbid();
  }

  @Override
  public String getCsourceid() {
    return this.bodyvo.getCsourcebillhid();
  }

  @Override
  public String getCsourcetypecode() {
    return this.bodyvo.getCsourcetype();
  }

  @Override
  public String getCtrantypeid() {
    return this.headvo.getCtrantypeid();
  }

  @Override
  public String getCunitid() {
    return this.bodyvo.getCunitid();
  }

  /**
   * 入库日期
   * 
   * @return
   */
  @Override
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(MetaNameConst.DBIZDATE);
  }

  @Override
  public Integer getFdirtoaptype() {
    return null;
  }

  @Override
  public Integer getFdirtoiatype() {
    return EnumToIAFlag.EstimateToIA.toInteger();
  }

  public GeneralInHeadVO getHeadvo() {
    return this.headvo;
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        GeneralInSettleVOMeta.class);
    // nc.vo.ic.m4a.entity.GeneralInHeadVO
    // nc.vo.ic.m4a.entity.GeneralInBodyVO
    // if (this.m_viewMeta == null) {
    // Class<? extends ISuperVO> headClazz = GeneralInHeadVO.class;
    // Class<? extends ISuperVO> itemClazz = GeneralInBodyVO.class;
    // List<String> headKeys = new ArrayList<String>();
    // headKeys.add(ICPubMetaNameConst.PK_GROUP);
    // headKeys.add(ICPubMetaNameConst.PK_ORG);
    // headKeys.add(ICPubMetaNameConst.PK_ORG_V);
    // headKeys.add(MetaNameConst.CGENERALHID);
    // headKeys.add(ICPubMetaNameConst.CWAREHOUSEID);
    // headKeys.add(ICPubMetaNameConst.VBILLCODE);
    // headKeys.add(ICPubMetaNameConst.CDPTID);
    // headKeys.add(ICPubMetaNameConst.CDPTVID);
    // headKeys.add(ICPubMetaNameConst.CBIZID);
    // headKeys.add(ICPubMetaNameConst.CTRANTYPEID);
    // headKeys.add(ICPubMetaNameConst.VTRANTYPECODE);
    // headKeys.add(ICPubMetaNameConst.TS);
    //
    // List<String> bodyKeys = new ArrayList<String>();
    // bodyKeys.add(MetaNameConst.CGENERALBID);
    // bodyKeys.add(MetaNameConst.CFIRSTBILLHID);
    // bodyKeys.add(MetaNameConst.CFIRSTBILLBID);
    // bodyKeys.add(MetaNameConst.CFIRSTTYPE);
    // bodyKeys.add(MetaNameConst.CFIRSTTRANSTYPE);
    // bodyKeys.add(MetaNameConst.VFIRSTBILLCODE);
    // bodyKeys.add(MetaNameConst.VFIRSTROWNO);
    // bodyKeys.add(MetaNameConst.CSOURCEBILLHID);
    // bodyKeys.add(MetaNameConst.CSOURCEBILLBID);
    // bodyKeys.add(MetaNameConst.CSOURCETYPE);
    // bodyKeys.add(MetaNameConst.CSOURCETRANSTYPE);
    // bodyKeys.add(MetaNameConst.VSOURCEBILLCODE);
    // bodyKeys.add(MetaNameConst.VSOURCEROWNO);
    // bodyKeys.add(ICPubMetaNameConst.NASSISTNUM);
    // bodyKeys.add(ICPubMetaNameConst.NNUM);
    // bodyKeys.add(ICPubMetaNameConst.NCOSTMNY);
    // bodyKeys.add(ICPubMetaNameConst.NCOSTPRICE);
    // bodyKeys.add(ICPubMetaNameConst.CMATERIALOID);
    // bodyKeys.add(ICPubMetaNameConst.CMATERIALVID);
    // bodyKeys.add(ICPubMetaNameConst.CASTUNITID);
    // bodyKeys.add(ICPubMetaNameConst.CUNITID);
    // bodyKeys.add(ICPubMetaNameConst.VFREE1);
    // bodyKeys.add(ICPubMetaNameConst.VFREE2);
    // bodyKeys.add(ICPubMetaNameConst.VFREE3);
    // bodyKeys.add(ICPubMetaNameConst.VFREE4);
    // bodyKeys.add(ICPubMetaNameConst.VFREE5);
    // bodyKeys.add(ICPubMetaNameConst.VFREE6);
    // bodyKeys.add(ICPubMetaNameConst.VFREE7);
    // bodyKeys.add(ICPubMetaNameConst.VFREE8);
    // bodyKeys.add(ICPubMetaNameConst.VFREE9);
    // bodyKeys.add(ICPubMetaNameConst.VFREE10);
    // bodyKeys.add(ICPubMetaNameConst.VNOTEBODY);
    // bodyKeys.add(ICPubMetaNameConst.VBATCHCODE);
    // bodyKeys.add(ICPubMetaNameConst.VCHANGERATE);
    // bodyKeys.add(ICPubMetaNameConst.TS);
    // bodyKeys.add(ICPubMetaNameConst.CPROJECTID);
    // bodyKeys.add(ICPubMetaNameConst.CPRODUCTORID);
    // bodyKeys.add(ICPubMetaNameConst.CVENDORID);
    //
    // this.m_viewMeta =
    // new DataViewMeta(itemClazz, bodyKeys.toArray(new String[0]));
    // this.m_viewMeta.add(headClazz, headKeys.toArray(new String[0]));
    // // 设置关联字段
    // this.m_viewMeta.addRelation(itemClazz, MetaNameConst.CGENERALHID,
    // headClazz, MetaNameConst.CGENERALHID);
    // this.m_viewMeta.setAttributeAliasName(headClazz, ICPubMetaNameConst.TS,
    // "hts");
    // this.m_viewMeta.setAttributeAliasName(itemClazz, ICPubMetaNameConst.TS,
    // "bts");
    // }
    //
    // return this.m_viewMeta;
  }

  @Override
  public UFDouble getNaccestcoststtlnum() {
    return null;
  }

  @Override
  public UFDouble getNaccestcostwashmny() {
    return null;
  }

  @Override
  public UFDouble getNaccgoodssettlemny() {
    return null;
  }

  @Override
  public UFDouble getNaccpreeststtlmny() {
    return UFDouble.ZERO_DBL;
  }

  @Override
  public UFDouble getNacctocostadjstmny() {
    return null;
  }

  @Override
  public UFDouble getNaccumsettlenum() {
    return null;
  }

  @Override
  public UFDouble getNaccurevcostnum() {
    return null;
  }

  @Override
  public UFDouble getNcalcostmny() {
    // wuxla v61
    return this.bodyvo.getNcostmny();
  }

  @Override
  public UFDouble getNcalcostprice() {
    // wuxla v61
    return this.bodyvo.getNcostprice();
  }

  @Override
  public UFDouble getNestcalcostmny() {
    // wuxla V61
    return this.bodyvo.getNcostmny();
  }

  @Override
  public UFDouble getNestcalcostprice() {
    // wuxla v61
    return this.bodyvo.getNcostprice();
  }

  @Override
  public UFDouble getNestmny() {
    return this.bodyvo.getNcostmny();
  }

  @Override
  public UFDouble getNestnum() {
    return this.bodyvo.getNnum();
  }

  @Override
  public UFDouble getNestprice() {
    return this.bodyvo.getNcostprice();
  }

  @Override
  public UFDouble getNinassistnum() {
    return this.bodyvo.getNassistnum();
  }

  @Override
  public UFDouble getNinnum() {
    return this.bodyvo.getNnum();
  }

  @Override
  public UFDouble getNmny() {
    return this.bodyvo.getNcostmny();
  }

  @Override
  public UFDouble getNnetprice() {
    return this.bodyvo.getNcostprice();
  }

  @Override
  public UFDouble getNorignetprice() {
    // wuxla v61
    return this.bodyvo.getNcostprice();
  }

  @Override
  public String getPk_batchcode() {
    return this.bodyvo.getPk_batchcode();
  }

  @Override
  public String getPk_costregion() {
    return this.getCostregion();
  }

  @Override
  public String getPk_dept() {
    return this.headvo.getCdptid();
  }

  @Override
  public String getPk_dept_v() {
    return this.headvo.getCdptvid();
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
    return this.pk_financeorg;
  }

  @Override
  public String getPk_group() {
    return this.headvo.getPk_group();
  }

  @Override
  public String getPk_material() {
    return this.bodyvo.getCmaterialvid();
  }

  @Override
  public String getPk_order() {
    return null;
  }

  @Override
  public String getPk_order_b() {
    return null;
  }

  @Override
  public String getPk_org() {
    return this.headvo.getPk_org();
  }

  @Override
  public String getPk_org_v() {
    return this.headvo.getPk_org_v();
  }

  @Override
  public String getPk_psndoc() {
    return this.headvo.getCbizid();
  }

  @Override
  public String getPk_srcmaterial() {
    return this.bodyvo.getCmaterialoid();
  }

  @Override
  public String getPk_stockps() {
    return this.headvo.getCgeneralhid();
  }

  @Override
  public String getPk_stockps_b() {
    return this.bodyvo.getCgeneralbid();
  }

  @Override
  public String getPk_supplier() {
    return this.bodyvo.getCvendorid();
  }

  @Override
  public String getVbatchcode() {
    return this.bodyvo.getVbatchcode();
  }

  @Override
  public String getVbillcode() {
    return this.headvo.getVbillcode();
  }

  @Override
  public String getVchangerate() {
    return this.bodyvo.getVchangerate();
  }

  /** 采购合同号 getter 方法 */
  @Override
  public String getVctcode() {
    return null;
  }

  @Override
  public String getVfirstcode() {
    return this.bodyvo.getVfirstbillcode();
  }

  @Override
  public String getVfirstrowno() {
    return this.bodyvo.getVfirstrowno();
  }

  @Override
  public String getVfirsttrantype() {
    return this.bodyvo.getCfirsttranstype();
  }

  @Override
  public String getVfree1() {
    return this.bodyvo.getVfree1();
  }

  @Override
  public String getVfree10() {
    return this.bodyvo.getVfree10();
  }

  @Override
  public String getVfree2() {
    return this.bodyvo.getVfree2();
  }

  @Override
  public String getVfree3() {
    return this.bodyvo.getVfree3();
  }

  @Override
  public String getVfree4() {
    return this.bodyvo.getVfree4();
  }

  @Override
  public String getVfree5() {
    return this.bodyvo.getVfree5();
  }

  @Override
  public String getVfree6() {
    return this.bodyvo.getVfree6();
  }

  @Override
  public String getVfree7() {
    return this.bodyvo.getVfree7();
  }

  @Override
  public String getVfree8() {
    return this.bodyvo.getVfree8();
  }

  @Override
  public String getVfree9() {
    return this.bodyvo.getVfree9();
  }

  @Override
  public String getVnotebody() {
    return this.bodyvo.getVnotebody();
  }

  @Override
  public String getVordercode() {
    return null;
  }

  @Override
  public String getVordertrantypecode() {
    return null;
  }

  @Override
  public String getVsourcecode() {
    return this.bodyvo.getVsourcebillcode();
  }

  @Override
  public String getVsourcerowno() {
    return this.bodyvo.getVsourcerowno();
  }

  @Override
  public String getVsourcetrantype() {
    return this.bodyvo.getCsourcetranstype();
  }

  @Override
  public String getVtrantypecode() {
    return this.headvo.getVtrantypecode();
  }

  @Override
  public void setBaffectcost(UFBoolean flag) {
    // 查出费用结算的其它入库单一定是影响成本的（采购需求）
  }

  @Override
  public void setBaffectpciacost(UFBoolean flag) {
  }

  public void setBodyvo(GeneralInBodyVO bodyvo) {
    this.bodyvo = bodyvo;
  }

  public void setCostregion(String costregionid) {
    this.costregionid = costregionid;
  }

  /**
   * 入库日期
   * 
   * @param dbilldate
   */
  @Override
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(MetaNameConst.DBIZDATE, dbilldate);
  }

  public void setHeadvo(GeneralInHeadVO headvo) {
    this.headvo = headvo;
  }

  @Override
  public void setNaccestcostwashmny(UFDouble value) {
    //
  }

  @Override
  public void setNacctocostadjstmny(UFDouble value) {
    //
  }

  public void setPk_financeorg(String pk_financeorg) {
    this.pk_financeorg = pk_financeorg;
  }

  @Override
  public void setVO(ISuperVO vo) {
    if (vo instanceof GeneralInHeadVO) {
      this.headvo = (GeneralInHeadVO) vo;
    }
    else if (vo instanceof GeneralInBodyVO) {
      this.bodyvo = (GeneralInBodyVO) vo;
    }
    super.setVO(vo);
  }
}
