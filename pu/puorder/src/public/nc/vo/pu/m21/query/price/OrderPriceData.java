package nc.vo.pu.m21.query.price;

import java.io.Serializable;

import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/**
 * �����۸���Ϣ�ĳ��ض���
 * <p>
 * 
 * @version 2010-3-21 ����10:53:39
 * @since 6.0
 * @author duy
 */
public class OrderPriceData implements Serializable {

  private static final long serialVersionUID = 8763539796308656258L;

  // Ҫ��ѯ�ĵ�λ�������������Ǳ��ۣ��ɽӿ�Լ����
  private String cunit;

  // ԭ�ұ���
  private String currencyId;

  // ����OID
  private String material;

  // ԭ�Ҳ���˰���ۣ��������Ǳ��ۣ��ɽӿ�Լ����
  private UFDouble origPrice;

  // ԭ�Һ�˰���ۣ��������Ǳ��ۣ��ɽӿ�Լ����
  private UFDouble origTaxPrice;

  /**
   * ���������֯
   */
  private String pk_financeorg;

  /** �ɹ���֯ */
  private String pk_purchaseOrg = null;

  /** ��Ӧ�� */
  private String pk_supplier = null;

  // ���Ҳ���˰���ۣ��������Ǳ��ۣ��ɽӿ�Լ����
  private UFDouble price;

  // ���Һ�˰���ۣ��������Ǳ��ۣ��ɽӿ�Լ����
  private UFDouble taxPrice;

  private UFDateTime ts;

  public OrderPriceData(final String pk_purchaseOrg, final String pk_supplier,
      final String currencyId, final String material, final UFDouble origPrice,
      final UFDouble origTaxPrice, final UFDouble price,
      final UFDouble taxPrice, final String pk_financeorg, final String cunit) {
    this.pk_purchaseOrg = pk_purchaseOrg;
    this.pk_supplier = pk_supplier;
    this.price = price;
    this.taxPrice = taxPrice;
    this.origPrice = origPrice;
    this.origTaxPrice = origTaxPrice;
    this.material = material;
    this.currencyId = currencyId;
    this.pk_financeorg = pk_financeorg;
    this.cunit = cunit;
  }

  /**
   * Ҫ��ѯ�ĵ�λ�������������Ǳ��ۣ��ɽӿ�Լ����
   * 
   * @return
   */
  public final String getCunit() {
    return this.cunit;
  }

  /**
   * ��ñ���
   * 
   * @return ����
   * @since 6.0
   */
  public final String getCurrencyId() {
    return this.currencyId;
  }

  /**
   * �����������������ϵ�OID������ѯ�������¼�ʱ��ֵ��
   * 
   * @return ���ϵ�OID
   * @since 6.0
   */
  public final String getMaterial() {
    return this.material;

  }

  /**
   * ��������������ԭ�Ҳ���˰����
   * 
   * @return ԭ�Ҳ���˰����
   * @since 6.0
   */
  public final UFDouble getOrigPrice() {
    return this.origPrice;
  }

  /**
   * ��������������ԭ�Һ�˰����
   * 
   * @return ԭ�Һ�˰����
   * @since 6.0
   */
  public final UFDouble getOrigTaxPrice() {
    return this.origTaxPrice;
  }

  /**
   * �����ϵĽ��������֯
   * 
   * @return
   */
  public final String getPk_financeorg() {
    return this.pk_financeorg;
  }

  /** �ɹ���֯ getter ���� */
  public final String getPk_purchaseOrg() {
    return this.pk_purchaseOrg;
  }

  /** ��Ӧ�� getter ���� */
  public final String getPk_supplier() {
    return this.pk_supplier;
  }

  /**
   * ��ñ��Ҳ���˰����
   * 
   * @return ���Ҳ���˰����
   */
  public final UFDouble getPrice() {
    return this.price;
  }

  /**
   * ��ñ��Һ�˰����
   * 
   * @return ���Һ�˰����
   */
  public final UFDouble getTaxPrice() {
    return this.taxPrice;
  }

  /**
   * ���ts�ֶ�
   * 
   * @return ���Һ�˰����
   */
  public final UFDateTime getTs() {
    return this.ts;
  }

  public final void setCunit(final String cunit) {
    this.cunit = cunit;
  }

  /**
   * �趨���������֯
   */
  public final void setPk_financeorg(final String pk_financeorg) {
    this.pk_financeorg = pk_financeorg;
  }

  /** �ɹ���֯ setter ���� */
  public final void setPk_purchaseOrg(String pk_purchaseOrg) {
    this.pk_purchaseOrg = pk_purchaseOrg;
  }

  /** ��Ӧ�� setter ���� */
  public final void setPk_supplier(String pk_supplier) {
    this.pk_supplier = pk_supplier;
  }

  public final void setTs(final UFDateTime ts) {
    this.ts = ts;
  }
}
