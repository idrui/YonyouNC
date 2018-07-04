package nc.vo.pu.m27.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.scmpub.util.selffun.BillMapInfoVO;

public class SettleBillHeaderVO extends SuperVO {
  /** 制单人 */
  public static final String BILLMAKER = "billmaker";

  /** 是否已传成本 **/
  public static final String BTOIA = "btoia";

  /** 是否虚拟发票的结算 **/
  public static final String BVIRTUALSETTLE = "bvirtualsettle";

  /** 本币币种 */
  public static final String CCURRENCYID = "ccurrencyid";

  /** 创建时间 **/
  public static final String CREATIONTIME = "creationtime";

  /** 创建人 **/
  public static final String CREATOR = "creator";

  /** 结算日期 **/
  public static final String DBILLDATE = "dbilldate";

  /** 制单日期 */
  public static final String DMAKEDATE = "dmakedate";

  /** dr **/
  public static final String DR = "dr";

  /** 结算单据类型 **/
  public static final String FSETTLETYPE = "fsettletype";

  /** 打印次数 **/
  public static final String IPRINTCOUNT = "iprintcount";

  /** 所属集团 **/
  public static final String PK_GROUP = "pk_group";

  /** 财务组织 **/
  public static final String PK_ORG = "pk_org";

  /** 财务组织版本 **/
  public static final String PK_ORG_V = "pk_org_v";

  /** 结算单 **/
  public static final String PK_SETTLEBILL = "pk_settlebill";

  /** 库存单据 */
  public static final String STOCKBILL = "stockbill";

  /** 时间戳 **/
  public static final String TS = "ts";

  /** 结算单号 **/
  public static final String VBILLCODE = "vbillcode";

  private static final long serialVersionUID = -4691704696643217113L;

  /** 制单人 **/
  public String getBillmaker() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.BILLMAKER);
  }

  /** 是否已传成本 **/
  public UFBoolean getBtoia() {
    return (UFBoolean) this.getAttributeValue(SettleBillHeaderVO.BTOIA);
  }

  /** 是否虚拟发票的结算 **/
  public UFBoolean getBvirtualsettle() {
    return (UFBoolean) this
        .getAttributeValue(SettleBillHeaderVO.BVIRTUALSETTLE);
  }

  /** 本币币种 getter 方法 */
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.CCURRENCYID);
  }

  /** 创建时间 **/
  public UFDateTime getCreationtime() {
    return (UFDateTime) this.getAttributeValue(SettleBillHeaderVO.CREATIONTIME);
  }

  /** 创建人 **/
  public String getCreator() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.CREATOR);
  }

  /** 结算日期 **/
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SettleBillHeaderVO.DBILLDATE);
  }

  /** 制单日期 **/
  public UFDate getDmakedate() {
    return (UFDate) this.getAttributeValue(SettleBillHeaderVO.DMAKEDATE);
  }

  /** dr **/
  public Integer getDr() {
    return (Integer) this.getAttributeValue(SettleBillHeaderVO.DR);
  }

  /** 结算单据类型 **/
  public Integer getFsettletype() {
    return (Integer) this.getAttributeValue(SettleBillHeaderVO.FSETTLETYPE);
  }

  /** 打印次数 **/
  public Integer getIprintcount() {
    return (Integer) this.getAttributeValue(SettleBillHeaderVO.IPRINTCOUNT);
  }

  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta(PUEntity.SettleBill_H);
    return meta;
  }

  /** 所属集团 **/
  public String getPk_group() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.PK_GROUP);
  }

  /** 财务组织 **/
  public String getPk_org() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.PK_ORG);
  }

  /** 财务组织版本 **/
  public String getPk_org_v() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.PK_ORG_V);
  }

  /** 结算单 **/
  public String getPk_settlebill() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.PK_SETTLEBILL);
  }

  /**
   * 库存单据
   * <p>
   * 使用场景：计算属性，只用于传存货时VO对照处理，不用于其他用途。
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public BillMapInfoVO getStockbill() {
    return (BillMapInfoVO) this.getAttributeValue(SettleBillHeaderVO.STOCKBILL);
  }

  /** 时间戳 **/
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SettleBillHeaderVO.TS);
  }

  /** 结算单号 **/
  public String getVbillcode() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.VBILLCODE);
  }

  /** 制单人 **/
  public void setBillmaker(String operator) {
    this.setAttributeValue(SettleBillHeaderVO.BILLMAKER, operator);
  }

  /** 是否已传成本 **/
  public void setBtoia(UFBoolean btoia) {
    this.setAttributeValue(SettleBillHeaderVO.BTOIA, btoia);
  }

  /** 是否虚拟发票的结算 **/
  public void setBvirtualsettle(UFBoolean bvirtualsettle) {
    this.setAttributeValue(SettleBillHeaderVO.BVIRTUALSETTLE, bvirtualsettle);
  }

  /** 本币币种 setter 方法 */
  public void setCcurrencyid(String ccurrencyid) {
    this.setAttributeValue(SettleBillHeaderVO.CCURRENCYID, ccurrencyid);
  }

  /** 创建时间 **/
  public void setCreationtime(UFDateTime creationtime) {
    this.setAttributeValue(SettleBillHeaderVO.CREATIONTIME, creationtime);
  }

  /** 创建人 **/
  public void setCreator(String creator) {
    this.setAttributeValue(SettleBillHeaderVO.CREATOR, creator);
  }

  /** 结算日期 **/
  public void setDbilldate(UFDate dbilldate) {
    this.setAttributeValue(SettleBillHeaderVO.DBILLDATE, dbilldate);
  }

  /** 制单日期 **/
  public void setDmakedate(UFDate dmakedate) {
    this.setAttributeValue(SettleBillHeaderVO.DMAKEDATE, dmakedate);
  }

  /** dr **/
  public void setDr(Integer dr) {
    this.setAttributeValue(SettleBillHeaderVO.DR, dr);
  }

  /** 结算单据类型 **/
  public void setFsettletype(Integer fsettletype) {
    this.setAttributeValue(SettleBillHeaderVO.FSETTLETYPE, fsettletype);
  }

  /** 打印次数 **/
  public void setIprintcount(Integer iprintcount) {
    this.setAttributeValue(SettleBillHeaderVO.IPRINTCOUNT, iprintcount);
  }

  /** 所属集团 **/
  public void setPk_group(String pk_group) {
    this.setAttributeValue(SettleBillHeaderVO.PK_GROUP, pk_group);
  }

  /** 财务组织 **/
  public void setPk_org(String pk_org) {
    this.setAttributeValue(SettleBillHeaderVO.PK_ORG, pk_org);
  }

  /** 财务组织版本 **/
  public void setPk_org_v(String pk_org_v) {
    this.setAttributeValue(SettleBillHeaderVO.PK_ORG_V, pk_org_v);
  }

  /** 结算单 **/
  public void setPk_settlebill(String pk_settlebill) {
    this.setAttributeValue(SettleBillHeaderVO.PK_SETTLEBILL, pk_settlebill);
  }

  /**
   * 库存单据
   * <p>
   * 使用场景：只用于传存货时VO交换处理，不用于其他用途
   * <ul>
   * <li>
   * </ul>
   * 
   * @param stockbill
   */
  public void setStockbill(BillMapInfoVO stockbill) {
    this.setAttributeValue(SettleBillHeaderVO.STOCKBILL, stockbill);
  }

  /** 时间戳 **/
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SettleBillHeaderVO.TS, ts);
  }

  /** 结算单号 **/
  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(SettleBillHeaderVO.VBILLCODE, vbillcode);
  }
}
