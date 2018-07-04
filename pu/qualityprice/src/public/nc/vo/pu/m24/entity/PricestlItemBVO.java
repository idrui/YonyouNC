/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-13 下午04:53:37
 */
package nc.vo.pu.m24.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单子子表VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-13 下午04:53:37
 */
public class PricestlItemBVO extends SuperVO {

  /** 是否包含下限 */
  public static final String BINCLUDELOWER = "bincludelower";

  /** 是否包含上限 */
  public static final String BINCLUDEUPPER = "bincludeupper";

  /** 检验值是否加权平均 */
  public static final String BWEIGHTAVG = "bweightavg";

  /** 优质优价主体标准 */
  public static final String CQPBASESTANDID = "cqpbasestandid";

  /** 优质优价方案主表 */
  public static final String CQPSCHEMEID = "cqpschemeid";

  /** 优质优价标准子表 */
  public static final String CQPSTANDBID = "cqpstandbid";

  /** 优质优价标准主表 */
  public static final String CQPSTANDID = "cqpstandid";

  /** dr */
  public static final String DR = "dr";

  /** 累计方式 */
  public static final String FACCUMTYPE = "faccumtype";

  /** 调整类型 */
  public static final String FADJUSTTYPE = "fadjusttype";

  /** 标准值类型 */
  public static final String FVALUETYPE = "fvaluetype";

  /** 计算优先级 */
  public static final String IPRIORITY = "ipriority";

  /** 标准的计算结果 */
  public static final String NSTANDCALVALUE = "nstandcalvalue";

  /** 集团 */
  public static final String PK_GROUP = "pk_group";

  /** 采购组织 */
  public static final String PK_ORG = "pk_org";

  /** 采购组织版本 */
  public static final String PK_ORG_V = "pk_org_v";

  /** 价格结算单子子表 */
  public static final String PK_PRICESETTLE_BB = "pk_pricesettle_bb";

  /** 价格结算单子子表 */
  public static final String PO_PRICESETTLE = "po_pricesettle";

  /** ts */
  public static final String TS = "ts";

  /** 基准值下限 */
  public static final String VBASELOWLMT = "vbaselowlmt";

  /** 基准值上限 */
  public static final String VBASEUPLMT = "vbaseuplmt";

  /** 实际检验值 */
  public static final String VCHECKVALUE = "vcheckvalue";

  /** 标准公式编码 */
  public static final String VFRMLCODE = "vfrmlcode";

  /** 标准公式名称 */
  public static final String VFRMLNAME = "vfrmlname";

  /** 下限值 */
  public static final String VLOWER = "vlower";

  /** 计算先决条件编码 */
  public static final String VPRECONDCODE = "vprecondcode";

  /** 计算先决条件名称 */
  public static final String VPRECONDNAME = "vprecondname";

  /** 上限值 */
  public static final String VUPPER = "vupper";

  private static final long serialVersionUID = 2670869802710761424L;

  /** 是否包含下限 getter 方法 */
  public UFBoolean getBincludelower() {
    return (UFBoolean) this.getAttributeValue(PricestlItemBVO.BINCLUDELOWER);
  }

  /** 是否包含上限 getter 方法 */
  public UFBoolean getBincludeupper() {
    return (UFBoolean) this.getAttributeValue(PricestlItemBVO.BINCLUDEUPPER);
  }

  /** 检验值是否加权平均 getter 方法 */
  public UFBoolean getBweightavg() {
    return (UFBoolean) this.getAttributeValue(PricestlItemBVO.BWEIGHTAVG);
  }

  /** 优质优价主体标准 getter 方法 */
  public String getCqpbasestandid() {
    return (String) this.getAttributeValue(PricestlItemBVO.CQPBASESTANDID);
  }

  /** 优质优价方案主表 getter 方法 */
  public String getCqpschemeid() {
    return (String) this.getAttributeValue(PricestlItemBVO.CQPSCHEMEID);
  }

  /** 优质优价标准子表 getter 方法 */
  public String getCqpstandbid() {
    return (String) this.getAttributeValue(PricestlItemBVO.CQPSTANDBID);
  }

  /** 优质优价标准主表 getter 方法 */
  public String getCqpstandid() {
    return (String) this.getAttributeValue(PricestlItemBVO.CQPSTANDID);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(PricestlItemBVO.DR);
  }

  /** 累计方式 getter 方法 */
  public Integer getFaccumtype() {
    return (Integer) this.getAttributeValue(PricestlItemBVO.FACCUMTYPE);
  }

  /** 调整类型 getter 方法 */
  public Integer getFadjusttype() {
    return (Integer) this.getAttributeValue(PricestlItemBVO.FADJUSTTYPE);
  }

  /** 标准值类型 getter 方法 */
  public Integer getFvaluetype() {
    return (Integer) this.getAttributeValue(PricestlItemBVO.FVALUETYPE);
  }

  /** 计算优先级 getter 方法 */
  public Integer getIpriority() {
    return (Integer) this.getAttributeValue(PricestlItemBVO.IPRIORITY);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M24_BB);
  }

  /** 标准的计算结果 getter 方法 */
  public UFDouble getNstandcalvalue() {
    return (UFDouble) this.getAttributeValue(PricestlItemBVO.NSTANDCALVALUE);
  }

  /** 集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(PricestlItemBVO.PK_GROUP);
  }

  /** 采购组织 getter 方法 */
  public String getPk_org() {
    return (String) this.getAttributeValue(PricestlItemBVO.PK_ORG);
  }

  /** 采购组织版本 getter 方法 */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(PricestlItemBVO.PK_ORG_V);
  }

  /** 价格结算单子子表 getter 方法 */
  public String getPk_pricesettle_bb() {
    return (String) this.getAttributeValue(PricestlItemBVO.PK_PRICESETTLE_BB);
  }

  /** 价格结算单子子表 getter 方法 */
  public String getPo_pricesettle() {
    return (String) this.getAttributeValue(PricestlItemBVO.PO_PRICESETTLE);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(PricestlItemBVO.TS);
  }

  /** 基准值下限 getter 方法 */
  public String getVbaselowlmt() {
    return (String) this.getAttributeValue(PricestlItemBVO.VBASELOWLMT);
  }

  /** 基准值上限 getter 方法 */
  public String getVbaseuplmt() {
    return (String) this.getAttributeValue(PricestlItemBVO.VBASEUPLMT);
  }

  /** 实际检验值 getter 方法 */
  public String getVcheckvalue() {
    return (String) this.getAttributeValue(PricestlItemBVO.VCHECKVALUE);
  }

  /** 标准公式编码 getter 方法 */
  public String getVfrmlcode() {
    return (String) this.getAttributeValue(PricestlItemBVO.VFRMLCODE);
  }

  /** 标准公式名称 getter 方法 */
  public String getVfrmlname() {
    return (String) this.getAttributeValue(PricestlItemBVO.VFRMLNAME);
  }

  /** 下限值 getter 方法 */
  public String getVlower() {
    return (String) this.getAttributeValue(PricestlItemBVO.VLOWER);
  }

  /** 计算先决条件编码 getter 方法 */
  public String getVprecondcode() {
    return (String) this.getAttributeValue(PricestlItemBVO.VPRECONDCODE);
  }

  /** 计算先决条件名称 getter 方法 */
  public String getVprecondname() {
    return (String) this.getAttributeValue(PricestlItemBVO.VPRECONDNAME);
  }

  /** 上限值 getter 方法 */
  public String getVupper() {
    return (String) this.getAttributeValue(PricestlItemBVO.VUPPER);
  }

  /** 是否包含下限 setter 方法 */
  public void setBincludelower(UFBoolean bincludelower) {
    this.setAttributeValue(PricestlItemBVO.BINCLUDELOWER, bincludelower);
  }

  /** 是否包含上限 setter 方法 */
  public void setBincludeupper(UFBoolean bincludeupper) {
    this.setAttributeValue(PricestlItemBVO.BINCLUDEUPPER, bincludeupper);
  }

  /** 检验值是否加权平均 setter 方法 */
  public void setBweightavg(UFBoolean bweightavg) {
    this.setAttributeValue(PricestlItemBVO.BWEIGHTAVG, bweightavg);
  }

  /** 优质优价主体标准 setter 方法 */
  public void setCqpbasestandid(String cqpbasestandid) {
    this.setAttributeValue(PricestlItemBVO.CQPBASESTANDID, cqpbasestandid);
  }

  /** 优质优价方案主表 setter 方法 */
  public void setCqpschemeid(String cqpschemeid) {
    this.setAttributeValue(PricestlItemBVO.CQPSCHEMEID, cqpschemeid);
  }

  /** 优质优价标准子表 setter 方法 */
  public void setCqpstandbid(String cqpstandbid) {
    this.setAttributeValue(PricestlItemBVO.CQPSTANDBID, cqpstandbid);
  }

  /** 优质优价标准主表 setter 方法 */
  public void setCqpstandid(String cqpstandid) {
    this.setAttributeValue(PricestlItemBVO.CQPSTANDID, cqpstandid);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(PricestlItemBVO.DR, dr);
  }

  /** 累计方式 setter 方法 */
  public void setFaccumtype(Integer faccumtype) {
    this.setAttributeValue(PricestlItemBVO.FACCUMTYPE, faccumtype);
  }

  /** 调整类型 setter 方法 */
  public void setFadjusttype(Integer fadjusttype) {
    this.setAttributeValue(PricestlItemBVO.FADJUSTTYPE, fadjusttype);
  }

  /** 标准值类型 setter 方法 */
  public void setFvaluetype(Integer fvaluetype) {
    this.setAttributeValue(PricestlItemBVO.FVALUETYPE, fvaluetype);
  }

  /** 计算优先级 setter 方法 */
  public void setIpriority(Integer ipriority) {
    this.setAttributeValue(PricestlItemBVO.IPRIORITY, ipriority);
  }

  /** 标准的计算结果 setter 方法 */
  public void setNstandcalvalue(UFDouble nstandcalvalue) {
    this.setAttributeValue(PricestlItemBVO.NSTANDCALVALUE, nstandcalvalue);
  }

  /** 集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(PricestlItemBVO.PK_GROUP, pk_group);
  }

  /** 采购组织 setter 方法 */
  public void setPk_org(String pk_org) {
    this.setAttributeValue(PricestlItemBVO.PK_ORG, pk_org);
  }

  /** 采购组织版本 setter 方法 */
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(PricestlItemBVO.PK_ORG_V, pk_org_v);
  }

  /** 价格结算单子子表 setter 方法 */
  public void setPk_pricesettle_bb(String pk_pricesettle_bb) {
    this.setAttributeValue(PricestlItemBVO.PK_PRICESETTLE_BB, pk_pricesettle_bb);
  }

  /** 价格结算单子子表 setter 方法 */
  public void setPo_pricesettle(String po_pricesettle) {
    this.setAttributeValue(PricestlItemBVO.PO_PRICESETTLE, po_pricesettle);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(PricestlItemBVO.TS, ts);
  }

  /** 基准值下限 setter 方法 */
  public void setVbaselowlmt(String vbaselowlmt) {
    this.setAttributeValue(PricestlItemBVO.VBASELOWLMT, vbaselowlmt);
  }

  /** 基准值上限 setter 方法 */
  public void setVbaseuplmt(String vbaseuplmt) {
    this.setAttributeValue(PricestlItemBVO.VBASEUPLMT, vbaseuplmt);
  }

  /** 实际检验值 setter 方法 */
  public void setVcheckvalue(String vcheckvalue) {
    this.setAttributeValue(PricestlItemBVO.VCHECKVALUE, vcheckvalue);
  }

  /** 标准公式编码 setter 方法 */
  public void setVfrmlcode(String vfrmlcode) {
    this.setAttributeValue(PricestlItemBVO.VFRMLCODE, vfrmlcode);
  }

  /** 标准公式名称 setter 方法 */
  public void setVfrmlname(String vfrmlname) {
    this.setAttributeValue(PricestlItemBVO.VFRMLNAME, vfrmlname);
  }

  /** 下限值 setter 方法 */
  public void setVlower(String vlower) {
    this.setAttributeValue(PricestlItemBVO.VLOWER, vlower);
  }

  /** 计算先决条件编码 setter 方法 */
  public void setVprecondcode(String vprecondcode) {
    this.setAttributeValue(PricestlItemBVO.VPRECONDCODE, vprecondcode);
  }

  /** 计算先决条件名称 setter 方法 */
  public void setVprecondname(String vprecondname) {
    this.setAttributeValue(PricestlItemBVO.VPRECONDNAME, vprecondname);
  }

  /** 上限值 setter 方法 */
  public void setVupper(String vupper) {
    this.setAttributeValue(PricestlItemBVO.VUPPER, vupper);
  }
}
