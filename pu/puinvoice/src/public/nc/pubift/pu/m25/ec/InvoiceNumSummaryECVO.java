package nc.pubift.pu.m25.ec;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;

public class InvoiceNumSummaryECVO implements Serializable {

  private static final long serialVersionUID = -7445438982545325199L;

  /** ��Ʊ������ */
  private UFDouble nnum = null;

  /** ��Ʊ��˰�ϼƣ�ԭ�Һ�˰�� */
  private UFDouble norigtaxmny = null;

  /** �ɹ���������PK */
  private String pk_order_b = null;

  /** ����OID */
  private String pk_srcmaterial = null;

  /** ��Ʊ������ getter ���� */
  public UFDouble getNnum() {
    return this.nnum;
  }

  /** ��Ʊ��˰�ϼ� getter ���� */
  public UFDouble getNorigtaxmny() {
    return this.norigtaxmny;
  }

  /** �ɹ���������PK getter ���� */
  public String getPk_order_b() {
    return this.pk_order_b;
  }

  /** ����OID getter ���� */
  public String getPk_srcmaterial() {
    return this.pk_srcmaterial;
  }

  /** ��Ʊ������ setter ���� */
  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }

  /** ��Ʊ��˰�ϼ� setter ���� */
  public void setNorigtaxmny(UFDouble norigtaxmny) {
    this.norigtaxmny = norigtaxmny;
  }

  /** �ɹ���������PK setter ���� */
  public void setPk_order_b(String pk_order_b) {
    this.pk_order_b = pk_order_b;
  }

  /** ����OID setter */
  public void setPk_srcmaterial(String pk_srcmaterial) {
    this.pk_srcmaterial = pk_srcmaterial;
  }
}
