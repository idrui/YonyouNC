package nc.vo.pu.m23.entity;

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
 * <li>到货单的子子表VO，用于存放质检结果数据
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 下午04:31:49
 */
public class ArriveBbVO extends SuperVO {

  /** 是否可入库 */
  public static final String BCANSTORE = "bcanstore";

  /** 是否改判 */
  public static final String BCHANGED = "bchanged";

  /** 是否合格 */
  public static final String BELIGIBLE = "beligible";

  /** 单位 */
  public static final String CASTUNITID = "castunitid";

  /** 主单位 */
  public static final String CUNITID = "cunitid";

  /** dr */
  public static final String DR = "dr";

  /** 累计入库数量 */
  public static final String NACCUMSTORENUM = "naccumstorenum";

  /** 数量 */
  public static final String NASTNUM = "nastnum";

  /** 主数量 */
  public static final String NNUM = "nnum";

  /** 到货单主键 */
  public static final String PK_ARRIVEORDER = "pk_arriveorder";

  /** 到货单质检明细 */
  public static final String PK_ARRIVEORDER_B = "pk_arriveorder_b";

  /** 到货单质检明细 */
  public static final String PK_ARRIVEORDER_BB = "pk_arriveorder_bb";

  /** 改判物料VID */
  public static final String PK_CHGMATERIAL = "pk_chgmaterial";

  /** 改判物料OID */
  public static final String PK_CHGSRCMATERIAL = "pk_chgsrcmaterial";

  /** 所属集团 */
  public static final String PK_GROUP = "pk_group";

  /** 入库批次号主键 */
  public static final String PK_INBATCHCODE = "pk_inbatchcode";

  /** 质检报告主键 */
  public static final String PK_QCREPORT = "pk_qcreport";

  /** ts */
  public static final String TS = "ts";

  /** 换算率 */
  public static final String VCHANGERATE = "vchangerate";

  /** 改判自由辅助属性1 */
  public static final String VCHGFREE1 = "vchgfree1";

  /** 改判自由辅助属性10 */
  public static final String VCHGFREE10 = "vchgfree10";

  /** 改判自由辅助属性2 */
  public static final String VCHGFREE2 = "vchgfree2";

  /** 改判自由辅助属性3 */
  public static final String VCHGFREE3 = "vchgfree3";

  /** 改判自由辅助属性4 */
  public static final String VCHGFREE4 = "vchgfree4";

  /** 改判自由辅助属性5 */
  public static final String VCHGFREE5 = "vchgfree5";

  /** 改判自由辅助属性6 */
  public static final String VCHGFREE6 = "vchgfree6";

  /** 改判自由辅助属性7 */
  public static final String VCHGFREE7 = "vchgfree7";

  /** 改判自由辅助属性8 */
  public static final String VCHGFREE8 = "vchgfree8";

  /** 改判自由辅助属性9 */
  public static final String VCHGFREE9 = "vchgfree9";

  /** 入库批次号编码 */
  public static final String VINBATCHCODE = "vinbatchcode";

  private static final long serialVersionUID = -4551838493191132669L;

  /** 是否可入库 getter 方法 */
  public UFBoolean getBcanstore() {
    return (UFBoolean) this.getAttributeValue(ArriveBbVO.BCANSTORE);
  }

  /** 是否改判 getter 方法 */
  public UFBoolean getBchanged() {
    return (UFBoolean) this.getAttributeValue(ArriveBbVO.BCHANGED);
  }

  /** 是否合格 getter 方法 */
  public UFBoolean getBeligible() {
    return (UFBoolean) this.getAttributeValue(ArriveBbVO.BELIGIBLE);
  }

  /** 单位 getter 方法 */
  public String getCastunitid() {
    return (String) this.getAttributeValue(ArriveBbVO.CASTUNITID);
  }

  /** 主单位 getter 方法 */
  public String getCunitid() {
    return (String) this.getAttributeValue(ArriveBbVO.CUNITID);
  }

  /** dr getter 方法 */
  public Integer getDr() {
    return (Integer) this.getAttributeValue(ArriveBbVO.DR);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.M23_BB);
  }

  /** 累计入库数量 getter 方法 */
  public UFDouble getNaccumstorenum() {
    return (UFDouble) this.getAttributeValue(ArriveBbVO.NACCUMSTORENUM);
  }

  /** 数量 getter 方法 */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(ArriveBbVO.NASTNUM);
  }

  /** 主数量 getter 方法 */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(ArriveBbVO.NNUM);
  }

  /** 到货单主键 getter 方法 */
  public String getPk_arriveorder() {
    return (String) this.getAttributeValue(ArriveBbVO.PK_ARRIVEORDER);
  }

  /** 到货单质检明细 getter 方法 */
  public String getPk_arriveorder_b() {
    return (String) this.getAttributeValue(ArriveBbVO.PK_ARRIVEORDER_B);
  }

  /** 到货单质检明细 getter 方法 */
  public String getPk_arriveorder_bb() {
    return (String) this.getAttributeValue(ArriveBbVO.PK_ARRIVEORDER_BB);
  }

  /** 改判物料VID getter 方法 */
  public String getPk_chgmaterial() {
    return (String) this.getAttributeValue(ArriveBbVO.PK_CHGMATERIAL);
  }

  /** 改判物料OID getter 方法 */
  public String getPk_chgsrcmaterial() {
    return (String) this.getAttributeValue(ArriveBbVO.PK_CHGSRCMATERIAL);
  }

  /** 所属集团 getter 方法 */
  public String getPk_group() {
    return (String) this.getAttributeValue(ArriveBbVO.PK_GROUP);
  }

  /** 入库批次号主键 getter 方法 */
  public String getPk_inbatchcode() {
    return (String) this.getAttributeValue(ArriveBbVO.PK_INBATCHCODE);
  }

  /** 质检报告主键 getter 方法 */
  public String getPk_qcreport() {
    return (String) this.getAttributeValue(ArriveBbVO.PK_QCREPORT);
  }

  /** ts getter 方法 */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(ArriveBbVO.TS);
  }

  /** 换算率 getter 方法 */
  public String getVchangerate() {
    return (String) this.getAttributeValue(ArriveBbVO.VCHANGERATE);
  }

  /** 改判自由辅助属性1 getter 方法 */
  public String getVchgfree1() {
    return (String) this.getAttributeValue(ArriveBbVO.VCHGFREE1);
  }

  /** 改判自由辅助属性10 getter 方法 */
  public String getVchgfree10() {
    return (String) this.getAttributeValue(ArriveBbVO.VCHGFREE10);
  }

  /** 改判自由辅助属性2 getter 方法 */
  public String getVchgfree2() {
    return (String) this.getAttributeValue(ArriveBbVO.VCHGFREE2);
  }

  /** 改判自由辅助属性3 getter 方法 */
  public String getVchgfree3() {
    return (String) this.getAttributeValue(ArriveBbVO.VCHGFREE3);
  }

  /** 改判自由辅助属性4 getter 方法 */
  public String getVchgfree4() {
    return (String) this.getAttributeValue(ArriveBbVO.VCHGFREE4);
  }

  /** 改判自由辅助属性5 getter 方法 */
  public String getVchgfree5() {
    return (String) this.getAttributeValue(ArriveBbVO.VCHGFREE5);
  }

  /** 改判自由辅助属性6 getter 方法 */
  public String getVchgfree6() {
    return (String) this.getAttributeValue(ArriveBbVO.VCHGFREE6);
  }

  /** 改判自由辅助属性7 getter 方法 */
  public String getVchgfree7() {
    return (String) this.getAttributeValue(ArriveBbVO.VCHGFREE7);
  }

  /** 改判自由辅助属性8 getter 方法 */
  public String getVchgfree8() {
    return (String) this.getAttributeValue(ArriveBbVO.VCHGFREE8);
  }

  /** 改判自由辅助属性9 getter 方法 */
  public String getVchgfree9() {
    return (String) this.getAttributeValue(ArriveBbVO.VCHGFREE9);
  }

  /** 入库批次号编码 getter 方法 */
  public String getVinbatchcode() {
    return (String) this.getAttributeValue(ArriveBbVO.VINBATCHCODE);
  }

  /** 是否可入库 setter 方法 */
  public void setBcanstore(UFBoolean bcanstore) {
    this.setAttributeValue(ArriveBbVO.BCANSTORE, bcanstore);
  }

  /** 是否改判 setter 方法 */
  public void setBchanged(UFBoolean bchanged) {
    this.setAttributeValue(ArriveBbVO.BCHANGED, bchanged);
  }

  /** 是否合格 setter 方法 */
  public void setBeligible(UFBoolean beligible) {
    this.setAttributeValue(ArriveBbVO.BELIGIBLE, beligible);
  }

  /** 单位 setter 方法 */
  public void setCastunitid(String castunitid) {
    this.setAttributeValue(ArriveBbVO.CASTUNITID, castunitid);
  }

  /** 主单位 setter 方法 */
  public void setCunitid(String cunitid) {
    this.setAttributeValue(ArriveBbVO.CUNITID, cunitid);
  }

  /** dr setter 方法 */
  public void setDr(Integer dr) {
    this.setAttributeValue(ArriveBbVO.DR, dr);
  }

  /** 累计入库数量 setter 方法 */
  public void setNaccumstorenum(UFDouble naccumstorenum) {
    this.setAttributeValue(ArriveBbVO.NACCUMSTORENUM, naccumstorenum);
  }

  /** 数量 setter 方法 */
  public void setNastnum(UFDouble nastnum) {
    this.setAttributeValue(ArriveBbVO.NASTNUM, nastnum);
  }

  /** 主数量 setter 方法 */
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(ArriveBbVO.NNUM, nnum);
  }

  /** 到货单主键 setter 方法 */
  public void setPk_arriveorder(String pk_arriveorder) {
    this.setAttributeValue(ArriveBbVO.PK_ARRIVEORDER, pk_arriveorder);
  }

  /** 到货单质检明细 setter 方法 */
  public void setPk_arriveorder_b(String pk_arriveorder_b) {
    this.setAttributeValue(ArriveBbVO.PK_ARRIVEORDER_B, pk_arriveorder_b);
  }

  /** 到货单质检明细 setter 方法 */
  public void setPk_arriveorder_bb(String pk_arriveorder_bb) {
    this.setAttributeValue(ArriveBbVO.PK_ARRIVEORDER_BB, pk_arriveorder_bb);
  }

  /** 改判物料VID setter 方法 */
  public void setPk_chgmaterial(String pk_chgmaterial) {
    this.setAttributeValue(ArriveBbVO.PK_CHGMATERIAL, pk_chgmaterial);
  }

  /** 改判物料OID setter 方法 */
  public void setPk_chgsrcmaterial(String pk_chgsrcmaterial) {
    this.setAttributeValue(ArriveBbVO.PK_CHGSRCMATERIAL, pk_chgsrcmaterial);
  }

  /** 所属集团 setter 方法 */
  public void setPk_group(String pk_group) {
    this.setAttributeValue(ArriveBbVO.PK_GROUP, pk_group);
  }

  /** 入库批次号主键 setter 方法 */
  public void setPk_inbatchcode(String pk_inbatchcode) {
    this.setAttributeValue(ArriveBbVO.PK_INBATCHCODE, pk_inbatchcode);
  }

  /** 质检报告主键 setter 方法 */
  public void setPk_qcreport(String pk_qcreport) {
    this.setAttributeValue(ArriveBbVO.PK_QCREPORT, pk_qcreport);
  }

  /** ts setter 方法 */
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(ArriveBbVO.TS, ts);
  }

  /** 换算率 setter 方法 */
  public void setVchangerate(String vchangerate) {
    this.setAttributeValue(ArriveBbVO.VCHANGERATE, vchangerate);
  }

  /** 改判自由辅助属性1 setter 方法 */
  public void setVchgfree1(String vchgfree1) {
    this.setAttributeValue(ArriveBbVO.VCHGFREE1, vchgfree1);
  }

  /** 改判自由辅助属性10 setter 方法 */
  public void setVchgfree10(String vchgfree10) {
    this.setAttributeValue(ArriveBbVO.VCHGFREE10, vchgfree10);
  }

  /** 改判自由辅助属性2 setter 方法 */
  public void setVchgfree2(String vchgfree2) {
    this.setAttributeValue(ArriveBbVO.VCHGFREE2, vchgfree2);
  }

  /** 改判自由辅助属性3 setter 方法 */
  public void setVchgfree3(String vchgfree3) {
    this.setAttributeValue(ArriveBbVO.VCHGFREE3, vchgfree3);
  }

  /** 改判自由辅助属性4 setter 方法 */
  public void setVchgfree4(String vchgfree4) {
    this.setAttributeValue(ArriveBbVO.VCHGFREE4, vchgfree4);
  }

  /** 改判自由辅助属性5 setter 方法 */
  public void setVchgfree5(String vchgfree5) {
    this.setAttributeValue(ArriveBbVO.VCHGFREE5, vchgfree5);
  }

  /** 改判自由辅助属性6 setter 方法 */
  public void setVchgfree6(String vchgfree6) {
    this.setAttributeValue(ArriveBbVO.VCHGFREE6, vchgfree6);
  }

  /** 改判自由辅助属性7 setter 方法 */
  public void setVchgfree7(String vchgfree7) {
    this.setAttributeValue(ArriveBbVO.VCHGFREE7, vchgfree7);
  }

  /** 改判自由辅助属性8 setter 方法 */
  public void setVchgfree8(String vchgfree8) {
    this.setAttributeValue(ArriveBbVO.VCHGFREE8, vchgfree8);
  }

  /** 改判自由辅助属性9 setter 方法 */
  public void setVchgfree9(String vchgfree9) {
    this.setAttributeValue(ArriveBbVO.VCHGFREE9, vchgfree9);
  }

  /** 入库批次号编码 setter 方法 */
  public void setVinbatchcode(String vinbatchcode) {
    this.setAttributeValue(ArriveBbVO.VINBATCHCODE, vinbatchcode);
  }
}
