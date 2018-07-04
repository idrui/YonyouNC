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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���������Ӧ��VO�࣬���ڼ�¼����ʱ�ķ��÷�̯��ϸ
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhaoyha
 * @time 2009-7-16 ����10:56:31
 */
public class SettleFeeAllotDetailVO extends SuperVO {

  /** �Ƿ��ݹ��������ϵĵ�һ�ν��� **/
  public static final String BESTFIRSTSETTLE = "bestfirstsettle";

  /** dr **/
  public static final String DR = "dr";

  /** ��̯�����ݽ�� **/
  public static final String NALLOTBILLMNY = "nallotbillmny";

  /** ��̯���������� **/
  public static final String NALLOTBILLNUM = "nallotbillnum";

  /** ���÷�̯��� **/
  public static final String NALLOTMONEY = "nallotmoney";

  /** ��һ�ν���ĺ����ۼƷ��ý������ **/
  public static final String NTIMESAFTERFIRST = "ntimesafterfirst";

  /** ��̯�ĵ�����ID **/
  public static final String PK_ALLOTBILLBID = "pk_allotbillbid";

  /** ��̯�ĵ���ID **/
  public static final String PK_ALLOTBILLID = "pk_allotbillid";

  /** �������ϰ汾 **/
  public static final String PK_MATERIAL = "pk_material";

  /** ��Ӧ�ķ������ڵĽ�����ID **/
  public static final String PK_OPPOFEE_B = "pk_oppofee_b";

  /** ���÷�̯��ϸ **/
  public static final String PK_SETTLE_FEEALLOT = "pk_settle_feeallot";

  /** ���㵥ͷ **/
  public static final String PK_SETTLEBILL = "pk_settlebill";

  /** ���㵥��ϸ_���� **/
  public static final String PK_SETTLEBILL_B = "pk_settlebill_b";

  /** �������� **/
  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  /** ��Ӧ�� **/
  public static final String PK_SUPPLIER = "pk_supplier";

  /** ʱ��� **/
  public static final String TS = "ts";

  /** ���÷�̯���ݵĵ������� **/
  public static final String VALLOTBILLTYPE = "vallotbilltype";

  private static final long serialVersionUID = 1L;

  // ���÷�̯ʱ�������ĳɱ�Ҫ��VO
  private CostfactorViewVO costfactorvo;

  // ���ڼ�¼��̯���Ľ���Ӧ�ķ�Ʊ������
  private String pk_invoice_b;

  /** �Ƿ��ݹ��������ϵĵ�һ�ν��� **/
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

  /** ��̯�����ݽ�� **/
  public UFDouble getNallotbillmny() {
    return (UFDouble) this
        .getAttributeValue(SettleFeeAllotDetailVO.NALLOTBILLMNY);
  }

  /** ��̯���������� **/
  public UFDouble getNallotbillnum() {
    return (UFDouble) this
        .getAttributeValue(SettleFeeAllotDetailVO.NALLOTBILLNUM);
  }

  /** ���÷�̯��� **/
  public UFDouble getNallotmoney() {
    return (UFDouble) this
        .getAttributeValue(SettleFeeAllotDetailVO.NALLOTMONEY);
  }

  /** ��һ�ν���ĺ����ۼƷ��ý������ **/
  public Integer getNtimesafterfirst() {
    return (Integer) this
        .getAttributeValue(SettleFeeAllotDetailVO.NTIMESAFTERFIRST);
  }

  /** ��̯�ĵ�����ID **/
  public String getPk_allotbillbid() {
    return (String) this
        .getAttributeValue(SettleFeeAllotDetailVO.PK_ALLOTBILLBID);
  }

  /** ��̯�ĵ���ID **/
  public String getPk_allotbillid() {
    return (String) this
        .getAttributeValue(SettleFeeAllotDetailVO.PK_ALLOTBILLID);
  }

  public String getPk_invoice_b() {
    return this.pk_invoice_b;
  }

  /** �������ϰ汾 **/
  public String getPk_material() {
    return (String) this.getAttributeValue(SettleFeeAllotDetailVO.PK_MATERIAL);
  }

  /** ��Ӧ�ķ������ڵĽ�����ID **/
  public String getPk_oppofee_b() {
    return (String) this.getAttributeValue(SettleFeeAllotDetailVO.PK_OPPOFEE_B);
  }

  /** ���÷�̯��ϸ **/
  public String getPk_settle_feeallot() {
    return (String) this
        .getAttributeValue(SettleFeeAllotDetailVO.PK_SETTLE_FEEALLOT);
  }

  /** ���㵥ͷ **/
  public String getPk_settlebill() {
    return (String) this
        .getAttributeValue(SettleFeeAllotDetailVO.PK_SETTLEBILL);
  }

  /** ���㵥��ϸ_���� **/
  public String getPk_settlebill_b() {
    return (String) this
        .getAttributeValue(SettleFeeAllotDetailVO.PK_SETTLEBILL_B);
  }

  /** �������� **/
  public String getPk_srcmaterial() {
    return (String) this
        .getAttributeValue(SettleFeeAllotDetailVO.PK_SRCMATERIAL);
  }

  /** ��Ӧ�� **/
  public String getPk_supplier() {
    return (String) this.getAttributeValue(SettleFeeAllotDetailVO.PK_SUPPLIER);
  }

  /** ʱ��� **/
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(SettleFeeAllotDetailVO.TS);
  }

  /** ���÷�̯���ݵĵ������� **/
  public String getVallotbilltype() {
    return (String) this
        .getAttributeValue(SettleFeeAllotDetailVO.VALLOTBILLTYPE);
  }

  /** �Ƿ��ݹ��������ϵĵ�һ�ν��� **/
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

  /** ��̯�����ݽ��* */
  public void setNallotbillmny(UFDouble nallotbillmny) {
    this.setAttributeValue(SettleFeeAllotDetailVO.NALLOTBILLMNY, nallotbillmny);
  }

  /** ��̯����������* */
  public void setNallotbillnum(UFDouble nallotbillnum) {
    this.setAttributeValue(SettleFeeAllotDetailVO.NALLOTBILLNUM, nallotbillnum);
  }

  /** ���÷�̯��� **/
  public void setNallotmoney(UFDouble nallotmoney) {
    this.setAttributeValue(SettleFeeAllotDetailVO.NALLOTMONEY, nallotmoney);
  }

  /** ��һ�ν���ĺ����ۼƷ��ý������ **/
  public void setNtimesafterfirst(Integer ntimesafterfirst) {
    this.setAttributeValue(SettleFeeAllotDetailVO.NTIMESAFTERFIRST,
        ntimesafterfirst);
  }

  /** ��̯�ĵ�����ID **/
  public void setPk_allotbillbid(String pk_allotbillbid) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_ALLOTBILLBID,
        pk_allotbillbid);
  }

  /** ��̯�ĵ���ID **/
  public void setPk_allotbillid(String pk_allotbillid) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_ALLOTBILLID,
        pk_allotbillid);
  }

  public void setPk_invoice_b(String pkInvoiceB) {
    this.pk_invoice_b = pkInvoiceB;
  }

  /** �������ϰ汾 **/
  public void setPk_material(String pk_material) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_MATERIAL, pk_material);
  }

  /** ��Ӧ�ķ������ڵĽ�����ID **/
  public void setPk_oppofee_b(String pk_oppofee_b) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_OPPOFEE_B, pk_oppofee_b);
  }

  /** ���÷�̯��ϸ **/
  public void setPk_settle_feeallot(String pk_settle_feeallot) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_SETTLE_FEEALLOT,
        pk_settle_feeallot);
  }

  /** ���㵥ͷ **/
  public void setPk_settlebill(String pk_settlebill) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_SETTLEBILL, pk_settlebill);
  }

  /** ���㵥��ϸ_���� **/
  public void setPk_settlebill_b(String pk_settlebill_b) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_SETTLEBILL_B,
        pk_settlebill_b);
  }

  /** �������� **/
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_SRCMATERIAL,
        pk_srcmaterial);
  }

  /** ��Ӧ�� **/
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(SettleFeeAllotDetailVO.PK_SUPPLIER, pk_supplier);
  }

  /** ʱ��� **/
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(SettleFeeAllotDetailVO.TS, ts);
  }

  /** ���÷�̯���ݵĵ������� **/
  public void setVallotbilltype(String vallotbilltype) {
    this.setAttributeValue(SettleFeeAllotDetailVO.VALLOTBILLTYPE,
        vallotbilltype);
  }
}
