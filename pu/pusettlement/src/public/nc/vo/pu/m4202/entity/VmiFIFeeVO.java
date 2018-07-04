/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-6 ����10:40:44
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���Ļ��ܲ�������ݹ�VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-6 ����10:40:44
 */
public class VmiFIFeeVO extends FeeEstVO {
  /**
   * 
   */
  private static final long serialVersionUID = -5019605095594608513L;

  /** ��Ӧ����־ getter ���� */
  @Override
  public UFBoolean getBtoap() {
    return (UFBoolean) this.getAttributeValue(FeeEstVO.BTOAP);
  }

  /** ���ɱ���־ getter ���� */
  @Override
  public UFBoolean getBtoia() {
    return (UFBoolean) this.getAttributeValue(FeeEstVO.BTOIA);
  }

  /** �ݹ����� getter ���� */
  @Override
  public UFDate getDestdate() {
    return (UFDate) this.getAttributeValue(FeeEstVO.DESTDATE);
  }

  /** dr getter ���� */
  @Override
  public Integer getDr() {
    return (Integer) this.getAttributeValue(FeeEstVO.DR);
  }

  @Override
  public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta(PUEntity.VMIFI_Fee);
  }

  /** �۱����� getter ���� */
  @Override
  public UFDouble getNestexchgrate() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTEXCHGRATE);
  }

  /** ������˰��� getter ���� */
  @Override
  public UFDouble getNestmny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTMNY);
  }

  /** ԭ����˰��� getter ���� */
  @Override
  public UFDouble getNestomny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTOMNY);
  }

  /** ԭ�Ҽ�˰�ϼ� getter ���� */
  @Override
  public UFDouble getNestototalmny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTOTOTALMNY);
  }

  /** ����˰�� getter ���� */
  @Override
  public UFDouble getNesttaxmny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTTAXMNY);
  }

  /** ˰�� getter ���� */
  @Override
  public UFDouble getNesttaxrate() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTTAXRATE);
  }

  /** ���Ҽ�˰�ϼ� getter ���� */
  @Override
  public UFDouble getNesttotalmny() {
    return (UFDouble) this.getAttributeValue(FeeEstVO.NESTTOTALMNY);
  }

  /** �ɱ�Ҫ�� getter ���� */
  @Override
  public String getPk_costfactor() {
    return (String) this.getAttributeValue(FeeEstVO.PK_COSTFACTOR);
  }

  /** ���� getter ���� */
  @Override
  public String getPk_estcurrency() {
    return (String) this.getAttributeValue(FeeEstVO.PK_ESTCURRENCY);
  }

  /** �ݹ��� getter ���� */
  @Override
  public String getPk_estpsn() {
    return (String) this.getAttributeValue(FeeEstVO.PK_ESTPSN);
  }

  /** ������ getter ���� */
  @Override
  public String getPk_feematerial() {
    return (String) this.getAttributeValue(FeeEstVO.PK_FEEMATERIAL);
  }

  /** ������֯ getter ���� */
  @Override
  public String getPk_financeorg() {
    return (String) this.getAttributeValue(FeeEstVO.PK_FINANCEORG);
  }

  /** �ݹ��������ϵ�һ�ν���Ľ��㵥 getter ���� */
  @Override
  public String getPk_firstsettle() {
    return (String) this.getAttributeValue(FeeEstVO.PK_FIRSTSETTLE);
  }

  /** �ݹ��������ϵ�һ�ν���Ľ��㵥�� getter ���� */
  @Override
  public String getPk_firstsettle_b() {
    return (String) this.getAttributeValue(FeeEstVO.PK_FIRSTSETTLE_B);
  }

  /** ���� getter ���� */
  @Override
  public String getPk_group() {
    return (String) this.getAttributeValue(FeeEstVO.PK_GROUP);
  }

  /** ���� getter ���� */
  @Override
  public String getPk_localcurrency() {
    return (String) this.getAttributeValue(FeeEstVO.PK_LOCALCURRENCY);
  }

  /** �ݹ��������� getter ���� */
  @Override
  public String getPk_srcfeematerial() {
    return (String) this.getAttributeValue(FeeEstVO.PK_SRCFEEMATERIAL);
  }

  /** ���Ļ��ܲ���ͷ getter ���� */
  @Override
  public String getPk_stockps() {
    return (String) this.getAttributeValue(FeeEstVO.PK_STOCKPS);
  }

  /** ���Ļ��ܷ����ݹ���ϸ getter ���� */
  @Override
  public String getPk_stockps_b() {
    return (String) this.getAttributeValue(FeeEstVO.PK_STOCKPS_B);
  }

  /** ���Ļ��ܷ����ݹ���ϸ getter ���� */
  @Override
  public String getPk_stockps_fee() {
    return (String) this.getAttributeValue(FeeEstVO.PK_STOCKPS_FEE);
  }

  /** ��Ӧ������ getter ���� */
  @Override
  public String getPk_supplier() {
    return (String) this.getAttributeValue(FeeEstVO.PK_SUPPLIER);
  }

  /** ts getter ���� */
  @Override
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(FeeEstVO.TS);
  }

  /** ��Ӧ����־ setter ���� */
  @Override
  public void setBtoap(UFBoolean btoap) {
    this.setAttributeValue(FeeEstVO.BTOAP, btoap);
  }

  /** ���ɱ���־ setter ���� */
  @Override
  public void setBtoia(UFBoolean btoia) {
    this.setAttributeValue(FeeEstVO.BTOIA, btoia);
  }

  /** �ݹ����� setter ���� */
  @Override
  public void setDestdate(UFDate destdate) {
    this.setAttributeValue(FeeEstVO.DESTDATE, destdate);
  }

  /** dr setter ���� */
  @Override
  public void setDr(Integer dr) {
    this.setAttributeValue(FeeEstVO.DR, dr);
  }

  /** �۱����� setter ���� */
  @Override
  public void setNestexchgrate(UFDouble nestexchgrate) {
    this.setAttributeValue(FeeEstVO.NESTEXCHGRATE, nestexchgrate);
  }

  /** ������˰��� setter ���� */
  @Override
  public void setNestmny(UFDouble nestmny) {
    this.setAttributeValue(FeeEstVO.NESTMNY, nestmny);
  }

  /** ԭ����˰��� setter ���� */
  @Override
  public void setNestomny(UFDouble nestomny) {
    this.setAttributeValue(FeeEstVO.NESTOMNY, nestomny);
  }

  /** ԭ�Ҽ�˰�ϼ� setter ���� */
  @Override
  public void setNestototalmny(UFDouble nestototalmny) {
    this.setAttributeValue(FeeEstVO.NESTOTOTALMNY, nestototalmny);
  }

  /** ����˰�� setter ���� */
  @Override
  public void setNesttaxmny(UFDouble nesttaxmny) {
    this.setAttributeValue(FeeEstVO.NESTTAXMNY, nesttaxmny);
  }

  /** ˰�� setter ���� */
  @Override
  public void setNesttaxrate(UFDouble nesttaxrate) {
    this.setAttributeValue(FeeEstVO.NESTTAXRATE, nesttaxrate);
  }

  /** ���Ҽ�˰�ϼ� setter ���� */
  @Override
  public void setNesttotalmny(UFDouble nesttotalmny) {
    this.setAttributeValue(FeeEstVO.NESTTOTALMNY, nesttotalmny);
  }

  /** �ɱ�Ҫ�� setter ���� */
  @Override
  public void setPk_costfactor(String pk_costfactor) {
    this.setAttributeValue(FeeEstVO.PK_COSTFACTOR, pk_costfactor);
  }

  /** ���� setter ���� */
  @Override
  public void setPk_estcurrency(String pk_estcurrency) {
    this.setAttributeValue(FeeEstVO.PK_ESTCURRENCY, pk_estcurrency);
  }

  /** �ݹ��� setter ���� */
  @Override
  public void setPk_estpsn(String pk_estpsn) {
    this.setAttributeValue(FeeEstVO.PK_ESTPSN, pk_estpsn);
  }

  /** ������ setter ���� */
  @Override
  public void setPk_feematerial(String pk_feematerial) {
    this.setAttributeValue(FeeEstVO.PK_FEEMATERIAL, pk_feematerial);
  }

  /** ������֯ setter ���� */
  @Override
  public void setPk_financeorg(String pk_financeorg) {
    this.setAttributeValue(FeeEstVO.PK_FINANCEORG, pk_financeorg);
  }

  /** �ݹ��������ϵ�һ�ν���Ľ��㵥 setter ���� */
  @Override
  public void setPk_firstsettle(String pk_firstsettle) {
    this.setAttributeValue(FeeEstVO.PK_FIRSTSETTLE, pk_firstsettle);
  }

  /** �ݹ��������ϵ�һ�ν���Ľ��㵥�� setter ���� */
  @Override
  public void setPk_firstsettle_b(String pk_firstsettle_b) {
    this.setAttributeValue(FeeEstVO.PK_FIRSTSETTLE_B, pk_firstsettle_b);
  }

  /** ���� setter ���� */
  @Override
  public void setPk_group(String pk_group) {
    this.setAttributeValue(FeeEstVO.PK_GROUP, pk_group);
  }

  /** ���� setter ���� */
  @Override
  public void setPk_localcurrency(String pk_localcurrency) {
    this.setAttributeValue(FeeEstVO.PK_LOCALCURRENCY, pk_localcurrency);
  }

  /** �ݹ��������� setter ���� */
  @Override
  public void setPk_srcfeematerial(String pk_srcfeematerial) {
    this.setAttributeValue(FeeEstVO.PK_SRCFEEMATERIAL, pk_srcfeematerial);
  }

  /** ���Ļ��ܲ���ͷ setter ���� */
  @Override
  public void setPk_stockps(String pk_stockps) {
    this.setAttributeValue(FeeEstVO.PK_STOCKPS, pk_stockps);
  }

  /** ���Ļ��ܷ����ݹ���ϸ setter ���� */
  @Override
  public void setPk_stockps_b(String pk_stockps_b) {
    this.setAttributeValue(FeeEstVO.PK_STOCKPS_B, pk_stockps_b);
  }

  /** ���Ļ��ܷ����ݹ���ϸ setter ���� */
  @Override
  public void setPk_stockps_fee(String pk_stockps_fee) {
    this.setAttributeValue(FeeEstVO.PK_STOCKPS_FEE, pk_stockps_fee);
  }

  /** ��Ӧ������ setter ���� */
  @Override
  public void setPk_supplier(String pk_supplier) {
    this.setAttributeValue(FeeEstVO.PK_SUPPLIER, pk_supplier);
  }

  /** ts setter ���� */
  @Override
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(FeeEstVO.TS, ts);
  }

}
