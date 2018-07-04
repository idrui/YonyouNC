package nc.pubitf.pu.m21.pub;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;

/**
 * ��������Ѱ���¼����ݷ�װ�ࡣ
 * 
 * @since 6.3
 * @version 2012-12-3 ����10:01:03
 * @author lixyp
 */
public class LastestPriceQueryVO implements Serializable {

  private static final long serialVersionUID = -170721450021841063L;

  /** ���� */
  private String currency = null;

  /** ��������˰���� */
  private UFDouble nnetprice = null;

  /** ����˰���� */
  private UFDouble norignetprice = null;

  /** ����˰���� */
  private UFDouble norigtaxnetprice = null;

  /** �����Һ�˰���� */
  private UFDouble ntaxnetprice = null;

  /** �ɹ���֯ */
  private String pk_org = null;

  /** ����OID */
  private String pk_srcmaterial = null;

  /** ��Ӧ�� */
  private String pk_supplier = null;

  /** ���� getter ���� */
  public String getCurrency() {
    return this.currency;
  }

  /** ��������˰���� getter ���� */
  public UFDouble getNnetprice() {
    return this.nnetprice;
  }

  /** ����˰���� getter ���� */
  public UFDouble getNorignetprice() {
    return this.norignetprice;
  }

  /** ����˰���� getter ���� */
  public UFDouble getNorigtaxnetprice() {
    return this.norigtaxnetprice;
  }

  /** �����Һ�˰���� getter ���� */
  public UFDouble getNtaxnetprice() {
    return this.ntaxnetprice;
  }

  /** �ɹ���֯ getter ���� */
  public String getPk_org() {
    return this.pk_org;
  }

  /** ����OID getter ���� */
  public String getPk_srcmaterial() {
    return this.pk_srcmaterial;
  }

  /** ��Ӧ�� getter ���� */
  public String getPk_supplier() {
    return this.pk_supplier;
  }

  /** ���� setter ���� */
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /** ��������˰���� setter ���� */
  public void setNnetprice(UFDouble nnetprice) {
    this.nnetprice = nnetprice;
  }

  /** ����˰���� setter ���� */
  public void setNorignetprice(UFDouble norignetprice) {
    this.norignetprice = norignetprice;
  }

  /** ����˰���� setter ���� */
  public void setNorigtaxnetprice(UFDouble norigtaxnetprice) {
    this.norigtaxnetprice = norigtaxnetprice;
  }

  /** �����Һ�˰���� setter ���� */
  public void setNtaxnetprice(UFDouble ntaxnetprice) {
    this.ntaxnetprice = ntaxnetprice;
  }

  /** �ɹ���֯ setter ���� */
  public void setPk_org(String pk_org) {
    this.pk_org = pk_org;
  }

  /** ����OID setter ���� */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.pk_srcmaterial = pk_srcmaterial;
  }

  /** ��Ӧ�� setter ���� */
  public void setPk_supplier(String pk_supplier) {
    this.pk_supplier = pk_supplier;
  }
}
