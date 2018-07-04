package nc.vo.pu.m24.entity;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥�ṩ�۸�ӿ��� Ϊ��Ʊ���ݹ��ṩ�����������ż�ȡ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-8-13 ����01:42:47
 */
public class PricParaVO implements Serializable {

  private static final long serialVersionUID = 4169034276231312609L;

  /**
   * ��ⵥ��ID
   */
  private String cgeneralbid = null;

  /**
   * �۸���㵥������˰����
   */
  private UFDouble nprice = null;

  /**
   * �۸���㵥���Һ�˰����
   */
  private UFDouble ntaxprice = null;

  /**
   * ��ȡ��ⵥ��ID
   */
  public String getCgeneralbid() {
    return this.cgeneralbid;
  }

  /**
   * ��ȡ�۸���㵥������˰����
   */
  public UFDouble getNprice() {
    return this.nprice;
  }

  /**
   * ��ȡ�۸���㵥���Һ�˰����
   */
  public UFDouble getNtaxprice() {
    return this.ntaxprice;
  }

  /**
   * ������ⵥ��ID
   */
  public void setCgeneralbid(String newVal) {
    this.cgeneralbid = newVal;
  }

  /**
   * ���ü۸���㵥������˰����
   */
  public void setNprice(UFDouble newVal) {
    this.nprice = newVal;
  }

  /**
   * ���ü۸���㵥���Һ�˰����
   */
  public void setNtaxprice(UFDouble newVal) {
    this.ntaxprice = newVal;
  }
}
