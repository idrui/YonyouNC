package nc.vo.pu.m27.entity;

import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.scmpub.res.billtype.ICBillType;

public class VoiConsumeSettleVO extends StockSettleVO {

  private static final long serialVersionUID = 1L;

  @Override
  public UFBoolean getBaffectcost() {
    return (UFBoolean) this.getAttributeValue(VmiFIHeaderVO.BAFFECTCOST);
  }

  @Override
  public UFBoolean getBaffectpciacost() {
    // 这版消耗汇总不影响利润中心
    return UFBoolean.FALSE;
  }

  @Override
  public UFBoolean getBnormpur() {
    return (UFBoolean) this.getAttributeValue(VmiFIHeaderVO.BNORMPUR);
  }

  @Override
  public UFBoolean getBsettlefinish() {
    return (UFBoolean) this.getAttributeValue(VmiFIHeaderVO.BSETTLEFINISH);
  }

  @Override
  public String getCastunitid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CASTUNITID);
  }

  @Override
  public String getCbilltypecode() {
    return ICBillType.VmiSum.getCode();
  }

  @Override
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CCURRENCYID);
  }

  @Override
  public String getCfirstbid() {
    return null;
  }

  @Override
  public String getCfirstid() {
    return null;
  }

  @Override
  public String getCfirsttypecode() {
    return null;
  }

  @Override
  public String getCorigcurrencyid() {
    return null;
  }

  @Override
  public String getCproductorid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CPRODUCTORID);
  }

  @Override
  public String getCprojectid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CPROJECTID);
  }

  @Override
  public String getCsourcebid() {
    return null;
  }

  @Override
  public String getCsourceid() {
    return null;
  }

  @Override
  public String getCsourcetypecode() {
    return null;
  }

  @Override
  public String getCtrantypeid() {
    return (String) this.getAttributeValue(StockSettleVO.CTRANTYPEID);
  }

  @Override
  public String getCunitid() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.CUNITID);
  }

  /**
   * 入库日期
   * 
   * @return
   */
  @Override
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(VmiFIHeaderVO.DBILLDATE);
  }

  @Override
  public Integer getFdirtoaptype() {
    return (Integer) this.getAttributeValue(VmiFIHeaderVO.FTOAPFLAG);
  }

  @Override
  public Integer getFdirtoiatype() {
    return (Integer) this.getAttributeValue(VmiFIHeaderVO.FTOIAFLAG);
  }

  @Override
  public IDataViewMeta getMetaData() {
    // if (this.m_viewMeta == null) {
    // this.m_viewMeta = new DataViewMeta(VmiFIHeaderVO.class);
    // }
    // return this.m_viewMeta;
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        VoiConsumeSettleVOMeta.class);
  }

  @Override
  public UFDouble getNaccestcoststtlnum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCESTCOSTSTTLNUM);
  }

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
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCPREESTSTTLMNY);
  }

  @Override
  public UFDouble getNacctocostadjstmny() {
    return UFDouble.ZERO_DBL;
  }

  @Override
  public UFDouble getNaccumsettlenum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NACCUMSETTLENUM);
  }

  @Override
  public UFDouble getNaccurevcostnum() {
    return UFDouble.ZERO_DBL;
  }

  @Override
  public UFDouble getNcalcostmny() {
    // wuxla v61
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTCALCOSTMNY);
  }

  @Override
  public UFDouble getNcalcostprice() {
    // wuxla v61
    return null;
  }

  @Override
  public UFDouble getNestcalcostmny() {
    // wuxla V61
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTCALCOSTMNY);
  }

  @Override
  public UFDouble getNestcalcostprice() {
    // wuxla v61
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTCALCOSTPRICE);
  }

  @Override
  public UFDouble getNestmny() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTMNY);
  }

  @Override
  public UFDouble getNestnum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTNUM);
  }

  @Override
  public UFDouble getNestprice() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NESTPRICE);
  }

  @Override
  public UFDouble getNinassistnum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NINASSISTNUM);
  }

  @Override
  public UFDouble getNinnum() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NINNUM);
  }

  @Override
  public UFDouble getNmny() {
    return UFDouble.ZERO_DBL;
  }

  @Override
  public UFDouble getNnetprice() {
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NNETPRICE);
  }

  @Override
  public UFDouble getNorignetprice() {
    // wuxla v61
    return (UFDouble) this.getAttributeValue(VmiFIHeaderVO.NNETPRICE);
  }

  @Override
  public String getPk_batchcode() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_BATCHCODE);
  }

  @Override
  public String getPk_costregion() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_COSTREGION);
  }

  @Override
  public String getPk_dept() {
    return null;
  }

  @Override
  public String getPk_dept_v() {
    return null;
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
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_FINANCEORG);
  }

  @Override
  public String getPk_group() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_GROUP);
  }

  @Override
  public String getPk_material() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_MATERIAL);
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
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STOREORG);
  }

  @Override
  public String getPk_org_v() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STOREORG_V);
  }

  @Override
  public String getPk_psndoc() {
    return null;
  }

  @Override
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_SRCMATERIAL);
  }

  @Override
  public String getPk_stockps() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STOCKPS);
  }

  @Override
  public String getPk_stockps_b() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STOCKPS_B);
  }

  @Override
  public String getPk_supplier() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_SUPPLIER);
  }

  @Override
  public String getVbatchcode() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VBATCHCODE);
  }

  @Override
  public String getVbillcode() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VBILLCODE);
  }

  @Override
  public String getVchangerate() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VCHANGERATE);
  }

  /** 采购合同号 getter 方法 */
  @Override
  public String getVctcode() {
    return null;
  }

  @Override
  public String getVfirstcode() {
    return null;
  }

  @Override
  public String getVfirstrowno() {
    return null;
  }

  @Override
  public String getVfirsttrantype() {
    return null;
  }

  @Override
  public String getVfree1() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE1);
  }

  @Override
  public String getVfree10() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE10);
  }

  @Override
  public String getVfree2() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE2);
  }

  @Override
  public String getVfree3() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE3);
  }

  @Override
  public String getVfree4() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE4);
  }

  @Override
  public String getVfree5() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE5);
  }

  @Override
  public String getVfree6() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE6);
  }

  @Override
  public String getVfree7() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE7);
  }

  @Override
  public String getVfree8() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE8);
  }

  @Override
  public String getVfree9() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VFREE9);
  }

  @Override
  public String getVnotebody() {
    return null;
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
    return null;
  }

  @Override
  public String getVsourcerowno() {
    return null;
  }

  @Override
  public String getVsourcetrantype() {
    return null;
  }

  @Override
  public String getVtrantypecode() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.VTRANTYPECODE);
  }

  @Override
  public void setBaffectcost(UFBoolean flag) {
    this.setAttributeValue(VmiFIHeaderVO.BAFFECTCOST, flag);
  }

  @Override
  public void setBaffectpciacost(UFBoolean flag) {
  }

  /**
   * 入库日期
   * 
   * @param dbilldate
   */
  @Override
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(VmiFIHeaderVO.DBILLDATE, dbilldate);
  }

  @Override
  public void setNaccestcostwashmny(UFDouble value) {
    this.setAttributeValue(StockSettleVO.NACCESTCOSTWASHMNY, value);
  }

  @Override
  public void setNacctocostadjstmny(UFDouble value) {
    // 消耗汇总没有直接确认成本
  }

}
