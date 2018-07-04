package nc.vo.pu.m27.entity;

import nc.vo.pu.costfactor.entity.CostfactorViewVO;
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
 * <li>采购结算孙表对应的VO类，用于记录结算时的费用分摊明细
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhaoyha
 * @time 2009-7-16 上午10:56:31
 */
public class SettleFeeAllotDetailVO extends SuperVO {

  /** 是否暂估费用物料的第一次结算 **/
  public static final String BESTFIRSTSETTLE = "bestfirstsettle";

  /** dr **/
  public static final String DR = "dr";

  /** 分摊的依据金额 **/
  public static final String NALLOTBILLMNY = "nallotbillmny";

  /** 分摊的依据数量 **/
  public static final String NALLOTBILLNUM = "nallotbillnum";

  /** 费用分摊金额 **/
  public static final String NALLOTMONEY = "nallotmoney";

  /** 第一次结算的后续累计费用结算次数 **/
  public static final String NTIMESAFTERFIRST = "ntimesafterfirst";

  /** 分摊的单据行ID **/
  public static final String PK_ALLOTBILLBID = "pk_allotbillbid";

  /** 分摊的单据ID **/
  public static final String PK_ALLOTBILLID = "pk_allotbillid";

  /** 费用物料版本 **/
  public static final String PK_MATERIAL = "pk_material";

  /** 相应的费用所在的结算行ID **/
  public static final String PK_OPPOFEE_B = "pk_oppofee_b";

  /** 费用分摊明细 **/
  public static final String PK_SETTLE_FEEALLOT = "pk_settle_feeallot";

  /** 结算单头 **/
  public static final String PK_SETTLEBILL = "pk_settlebill";

  /** 结算单明细_主键 **/
  public static final String PK_SETTLEBILL_B = "pk_settlebill_b";

  /** 费用物料 **/
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** 供应商 **/
  public static final String PK_SUPPLIER = "pk_supplier";

  /** 时间戳 **/
  public static final String TS = "ts";

  /** 费用分摊依据的单据类型 **/
  public static final String VALLOTBILLTYPE = "vallotbilltype";

  private static final long serialVersionUID = 1L;

  // 费用分摊时所依赖的成本要素VO
  private CostfactorViewVO costfactorvo;

  // 用于记录分摊到的金额对应的发票行主键
  private String pk_invoice_b;

  /** 是否暂估费用物料的第一次结算 **/
  public UFBoolean getBestfirstsettle() {
    return (UFBoolean) this
        .getAttributeValue(SettleFeeAllotDetailVO.BESTFIRSTSETTLE);
  }

  public CostfactorViewVO getCostfactorvo() {
    return this.costfactorvo;
  }

  /** dr **/
  public Integer getDr() {
    return (Integer) this.getAttributeValue(SettleFeeAllotDetailVO.DR);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.SettleBill_FeeDetail);
  }

  /** 分摊的依据金额 **/
  public UFDouble getNallotbillmny() {
    return (UFDouble) this
        .getAttributeValue(SettleFeeAllotDetailVO.NALLOTBILLMNY);
  }

  /** 分摊的依据数量 **/
  public UFDouble getNallotbillnum() {
    return (UFDouble) this
        .getAttributeValue(SettleFeeAllotDetailVO.NALLOTBILLNUM);
  }

  /** 费用分摊金额 **/
  public UFDouble getNallotmoney() {
    return (UFDouble) this
        .getAttributeValue(SettleFeeAllotDetailVO.NALLOTMONEY);
  }

  /** 第一次结算的后续累计费用结算次数 **/
  public Integer getNtimesafterfirst() {
    return (Integer) this
        .getAttributeValue(SettleFeeAllotDetailVO.NTIMESAFTERFIRST);
  }

  /** 分摊的单据行ID **/
  public String getPk_allotbillbid() {
    return (String) this
        .getAttributeValue(SettleFeeAllotDetailVO.PK_ALLOTBILLBID);
  }

  /** 分摊的单据ID **/
  public String getPk_allotbillid() {
    return (String) this
        .getAttributeValue(SettleFeeAllotDetailVO.PK_ALLOTBILLID);
  }

  public String getPk_invoice_b() {
    return this.pk_invoice_b;
  }

  /** 费用物料版本 **/
  public String getPk_material() {
    return (String) this.getAttributeValue(SettleFeeAllotDetailVO.PK_MATERIAL);
  }

  /** 相应的费用所在的结算行ID **/
  public String getPk_oppofee_b() {
    return (String) this.getAttributeValue(SettleFeeAllotDetailVO.PK_OPPOFEE_B);
  }

  /** 费用分摊明细 **/
  public String getPk_settle_feeallot() {
    return (String) this
        .getAttributeValue(SettleFeeAllotDetailVO.PK_SETTLE_FEEALLOT);
  }

  /** 结算单头 **/
  public String getPk_settlebill() {
    return (String) this
        .getAttributeValue(SettleFeeAllotDetailVO.PK_SETTLEBILL);
  }

  /** 结算单明细_主键 **/
  public String getPk_settlebill_b() {
    return (String) this
        .getAttributeValue(SettleFeeAllotDetailVO.PK_SETTLEBILL_B);
  }

  /** 费用物料 **/
  public String getPk_srcmaterial() {
    return (String) this
        .getAttributeValue(SettleFeeAllotDetailVO.PK_SRCMATERIAL);
  }

  /** 供应商 **/
  public String getPk_supplier() {
    return (String) this.getAttributeValue(SettleFeeAllotDetailVO.PK_SUPPLIER);
  }

  /** 时间戳 **/
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SettleFeeAllotDetailVO.TS);
  }

  /** 费用分摊依据的单据类型 **/
  public String getVallotbilltype() {
    return (String) this
        .getAttributeValue(SettleFeeAllotDetailVO.VALLOTBILLTYPE);
  }

  /** 是否暂估费用物料的第一次结算 **/
  public void setBestfirstsettle(UFBoolean bestfirstsettle) {
    this.setAttributeValue(SettleFeeAllotDetailVO.BESTFIRSTSETTLE,
        bestfirstsettle);
  }

  public void setCostfactorvo(CostfactorViewVO costfactorvo) {
    this.costfactorvo = costfactorvo;
  }

  /** dr **/
  public void setDr(Integer dr) {
    this.setAttributeValue(SettleFeeAllotDetailVO.DR, dr);
  }

  /** 分摊的依据金额* */
  public void setNallotbillmny(UFDouble nallotbillmny) {
    this.setAttributeValue(SettleFeeAllotDetailVO.NALLOTBILLMNY, nallotbillmny);
  }

  /** 分摊的依据数量* */
  public void setNallotbillnum(UFDouble nallotbillnum) {
    this.setAttributeValue(SettleFeeAllotDetailVO.NALLOTBILLNUM, nallotbillnum);
  }

  /** 费用分摊金额 **/
  public void setNallotmoney(UFDouble nallotmoney) {
    this.setAttributeValue(SettleFeeAllotDetailVO.NALLOTMONEY, nallotmoney);
  }

  /** 第一次结算的后续累计费用结算次数 **/
  public void setNtimesafterfirst(Integer ntimesafterfirst) {
    this.setAttributeValue(SettleFeeAllotDetailVO.NTIMESAFTERFIRST,
        ntimesafterfirst);
  }

  /** 分摊的单据行ID **/
  public void setPk_allotbillbid(String pk_allotbillbid) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_ALLOTBILLBID,
        pk_allotbillbid);
  }

  /** 分摊的单据ID **/
  public void setPk_allotbillid(String pk_allotbillid) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_ALLOTBILLID,
        pk_allotbillid);
  }

  public void setPk_invoice_b(String pkInvoiceB) {
    this.pk_invoice_b = pkInvoiceB;
  }

  /** 费用物料版本 **/
  public void setPk_material(String pk_material) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_MATERIAL, pk_material);
  }

  /** 相应的费用所在的结算行ID **/
  public void setPk_oppofee_b(String pk_oppofee_b) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_OPPOFEE_B, pk_oppofee_b);
  }

  /** 费用分摊明细 **/
  public void setPk_settle_feeallot(String pk_settle_feeallot) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_SETTLE_FEEALLOT,
        pk_settle_feeallot);
  }

  /** 结算单头 **/
  public void setPk_settlebill(String pk_settlebill) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_SETTLEBILL, pk_settlebill);
  }

  /** 结算单明细_主键 **/
  public void setPk_settlebill_b(String pk_settlebill_b) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_SETTLEBILL_B,
        pk_settlebill_b);
  }

  /** 费用物料 **/
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_SRCMATERIAL,
        pk_srcmaterial);
  }

  /** 供应商 **/
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_SUPPLIER, pk_supplier);
  }

  /** 时间戳 **/
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SettleFeeAllotDetailVO.TS, ts);
  }

  /** 费用分摊依据的单据类型 **/
  public void setVallotbilltype(String vallotbilltype) {
    this.setAttributeValue(SettleFeeAllotDetailVO.VALLOTBILLTYPE,
        vallotbilltype);
  }
}
