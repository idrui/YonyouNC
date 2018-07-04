/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-6 上午10:40:44
 */
package nc.vo.pu.m4202.entity;

import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总财务费用暂估VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-6 上午10:40:44
 */
public class VmiFIFeeVO extends FeeEstVO {
  /**
   * 
   */
  private static final long serialVersionUID = -5019605095594608513L;

  /** 传应付标志 getter 方法 */
  @Override
  public UFBoolean getBtoap() {
    return (UFBoolean) this.getAttributeValue(FeeEstVO.BTOAP);
  }

  /** 传成本标志 getter 方法 */
  @Override
  public UFBoolean getBtoia() {
    return (UFBoolean) this.getAttributeValue(FeeEstVO.BTOIA);
  }

  /** 暂估日期 getter 方法 */
  @Override
  public UFDate getDestdate() {
    return (UFDate) this.getAttributeValue(FeeEstVO.DESTDATE);
  }

  /** dr getter 方法 */
  @Override
  public Integer getDr() {
    return (Integer) this.getAttributeValue(FeeEstVO.DR);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.VMIFI_Fee);
  }

  /** 折本汇率 getter 方法 */
  @Override
  public UFDouble getNestexchgrate() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTEXCHGRATE);
  }

  /** 本币无税金额 getter 方法 */
  @Override
  public UFDouble getNestmny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTMNY);
  }

  /** 原币无税金额 getter 方法 */
  @Override
  public UFDouble getNestomny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTOMNY);
  }

  /** 原币价税合计 getter 方法 */
  @Override
  public UFDouble getNestototalmny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTOTOTALMNY);
  }

  /** 本币税额 getter 方法 */
  @Override
  public UFDouble getNesttaxmny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTTAXMNY);
  }

  /** 税率 getter 方法 */
  @Override
  public UFDouble getNesttaxrate() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTTAXRATE);
  }

  /** 本币价税合计 getter 方法 */
  @Override
  public UFDouble getNesttotalmny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTTOTALMNY);
  }

  /** 成本要素 getter 方法 */
  @Override
  public String getPk_costfactor() {
    return (String) this.getAttributeValue(FeeEstVO.PK_COSTFACTOR);
  }

  /** 币种 getter 方法 */
  @Override
  public String getPk_estcurrency() {
    return (String) this.getAttributeValue(FeeEstVO.PK_ESTCURRENCY);
  }

  /** 暂估人 getter 方法 */
  @Override
  public String getPk_estpsn() {
    return (String) this.getAttributeValue(FeeEstVO.PK_ESTPSN);
  }

  /** 费用项 getter 方法 */
  @Override
  public String getPk_feematerial() {
    return (String) this.getAttributeValue(FeeEstVO.PK_FEEMATERIAL);
  }

  /** 财务组织 getter 方法 */
  @Override
  public String getPk_financeorg() {
    return (String) this.getAttributeValue(FeeEstVO.PK_FINANCEORG);
  }

  /** 暂估费用物料第一次结算的结算单 getter 方法 */
  @Override
  public String getPk_firstsettle() {
    return (String) this.getAttributeValue(FeeEstVO.PK_FIRSTSETTLE);
  }

  /** 暂估费用物料第一次结算的结算单行 getter 方法 */
  @Override
  public String getPk_firstsettle_b() {
    return (String) this.getAttributeValue(FeeEstVO.PK_FIRSTSETTLE_B);
  }

  /** 集团 getter 方法 */
  @Override
  public String getPk_group() {
    return (String) this.getAttributeValue(FeeEstVO.PK_GROUP);
  }

  /** 本币 getter 方法 */
  @Override
  public String getPk_localcurrency() {
    return (String) this.getAttributeValue(FeeEstVO.PK_LOCALCURRENCY);
  }

  /** 暂估费用物料 getter 方法 */
  @Override
  public String getPk_srcfeematerial() {
    return (String) this.getAttributeValue(FeeEstVO.PK_SRCFEEMATERIAL);
  }

  /** 消耗汇总财务头 getter 方法 */
  @Override
  public String getPk_stockps() {
    return (String) this.getAttributeValue(FeeEstVO.PK_STOCKPS);
  }

  /** 消耗汇总费用暂估明细 getter 方法 */
  @Override
  public String getPk_stockps_b() {
    return (String) this.getAttributeValue(FeeEstVO.PK_STOCKPS_B);
  }

  /** 消耗汇总费用暂估明细 getter 方法 */
  @Override
  public String getPk_stockps_fee() {
    return (String) this.getAttributeValue(FeeEstVO.PK_STOCKPS_FEE);
  }

  /** 供应商名称 getter 方法 */
  @Override
  public String getPk_supplier() {
    return (String) this.getAttributeValue(FeeEstVO.PK_SUPPLIER);
  }

  /** ts getter 方法 */
  @Override
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(FeeEstVO.TS);
  }

  /** 传应付标志 setter 方法 */
  @Override
  public void setBtoap(UFBoolean btoap) {
    this.setAttributeValue(FeeEstVO.BTOAP, btoap);
  }

  /** 传成本标志 setter 方法 */
  @Override
  public void setBtoia(UFBoolean btoia) {
    this.setAttributeValue(FeeEstVO.BTOIA, btoia);
  }

  /** 暂估日期 setter 方法 */
  @Override
  public void setDestdate(UFDate destdate) {
    this.setAttributeValue(FeeEstVO.DESTDATE, destdate);
  }

  /** dr setter 方法 */
  @Override
  public void setDr(Integer dr) {
    this.setAttributeValue(FeeEstVO.DR, dr);
  }

  /** 折本汇率 setter 方法 */
  @Override
  public void setNestexchgrate(UFDouble nestexchgrate) {
    this.setAttributeValue(FeeEstVO.NESTEXCHGRATE, nestexchgrate);
  }

  /** 本币无税金额 setter 方法 */
  @Override
  public void setNestmny(UFDouble nestmny) {
    this.setAttributeValue(FeeEstVO.NESTMNY, nestmny);
  }

  /** 原币无税金额 setter 方法 */
  @Override
  public void setNestomny(UFDouble nestomny) {
    this.setAttributeValue(FeeEstVO.NESTOMNY, nestomny);
  }

  /** 原币价税合计 setter 方法 */
  @Override
  public void setNestototalmny(UFDouble nestototalmny) {
    this.setAttributeValue(FeeEstVO.NESTOTOTALMNY, nestototalmny);
  }

  /** 本币税额 setter 方法 */
  @Override
  public void setNesttaxmny(UFDouble nesttaxmny) {
    this.setAttributeValue(FeeEstVO.NESTTAXMNY, nesttaxmny);
  }

  /** 税率 setter 方法 */
  @Override
  public void setNesttaxrate(UFDouble nesttaxrate) {
    this.setAttributeValue(FeeEstVO.NESTTAXRATE, nesttaxrate);
  }

  /** 本币价税合计 setter 方法 */
  @Override
  public void setNesttotalmny(UFDouble nesttotalmny) {
    this.setAttributeValue(FeeEstVO.NESTTOTALMNY, nesttotalmny);
  }

  /** 成本要素 setter 方法 */
  @Override
  public void setPk_costfactor(String pk_costfactor) {
    this.setAttributeValue(FeeEstVO.PK_COSTFACTOR, pk_costfactor);
  }

  /** 币种 setter 方法 */
  @Override
  public void setPk_estcurrency(String pk_estcurrency) {
    this.setAttributeValue(FeeEstVO.PK_ESTCURRENCY, pk_estcurrency);
  }

  /** 暂估人 setter 方法 */
  @Override
  public void setPk_estpsn(String pk_estpsn) {
    this.setAttributeValue(FeeEstVO.PK_ESTPSN, pk_estpsn);
  }

  /** 费用项 setter 方法 */
  @Override
  public void setPk_feematerial(String pk_feematerial) {
    this.setAttributeValue(FeeEstVO.PK_FEEMATERIAL, pk_feematerial);
  }

  /** 财务组织 setter 方法 */
  @Override
  public void setPk_financeorg(String pk_financeorg) {
    this.setAttributeValue(FeeEstVO.PK_FINANCEORG, pk_financeorg);
  }

  /** 暂估费用物料第一次结算的结算单 setter 方法 */
  @Override
  public void setPk_firstsettle(String pk_firstsettle) {
    this.setAttributeValue(FeeEstVO.PK_FIRSTSETTLE, pk_firstsettle);
  }

  /** 暂估费用物料第一次结算的结算单行 setter 方法 */
  @Override
  public void setPk_firstsettle_b(String pk_firstsettle_b) {
    this.setAttributeValue(FeeEstVO.PK_FIRSTSETTLE_B, pk_firstsettle_b);
  }

  /** 集团 setter 方法 */
  @Override
  public void setPk_group(String pk_group) {
    this.setAttributeValue(FeeEstVO.PK_GROUP, pk_group);
  }

  /** 本币 setter 方法 */
  @Override
  public void setPk_localcurrency(String pk_localcurrency) {
    this.setAttributeValue(FeeEstVO.PK_LOCALCURRENCY, pk_localcurrency);
  }

  /** 暂估费用物料 setter 方法 */
  @Override
  public void setPk_srcfeematerial(String pk_srcfeematerial) {
    this.setAttributeValue(FeeEstVO.PK_SRCFEEMATERIAL, pk_srcfeematerial);
  }

  /** 消耗汇总财务头 setter 方法 */
  @Override
  public void setPk_stockps(String pk_stockps) {
    this.setAttributeValue(FeeEstVO.PK_STOCKPS, pk_stockps);
  }

  /** 消耗汇总费用暂估明细 setter 方法 */
  @Override
  public void setPk_stockps_b(String pk_stockps_b) {
    this.setAttributeValue(FeeEstVO.PK_STOCKPS_B, pk_stockps_b);
  }

  /** 消耗汇总费用暂估明细 setter 方法 */
  @Override
  public void setPk_stockps_fee(String pk_stockps_fee) {
    this.setAttributeValue(FeeEstVO.PK_STOCKPS_FEE, pk_stockps_fee);
  }

  /** 供应商名称 setter 方法 */
  @Override
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(FeeEstVO.PK_SUPPLIER, pk_supplier);
  }

  /** ts setter 方法 */
  @Override
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(FeeEstVO.TS, ts);
  }

}
