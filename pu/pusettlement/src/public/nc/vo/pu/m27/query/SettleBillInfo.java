package nc.vo.pu.m27.query;

import java.io.Serializable;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����ϵ��Ϣ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-7-13 ����03:11:05
 */
public class SettleBillInfo implements Serializable {
  private static final long serialVersionUID = 5789568357045139533L;

  private UFBoolean bwashest;

  private UFDouble noppconfirmapmny;

  private UFDouble nsettlenum;

  private String pk_invoice;

  private String pk_invoice_b;

  private String pk_rushinvoice;

  private String pk_rushinvoice_b;

  private String pk_rushstock;

  private String pk_rushstock_b;

  private String pk_settlebill_b;

  private String pk_stock;

  private String pk_stock_b;

  private String pk_stockorder;

  private String pk_stockorder_b;

  private String vstockbilltype;

  public UFBoolean getBwashest() {
    return this.bwashest;
  }

  /**
   * ��������������������ϸ��Ӧ��ȷ��Ӧ��Ӧ��ԭ�Ҽ�˰�ϼơ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-27 ����07:56:21
   */
  public UFDouble getNoppconfirmapmny() {
    return this.noppconfirmapmny;
  }

  /**
   * ������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @return ����
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-13 ����04:22:34
   */
  public UFDouble getNsettlenum() {
    return this.nsettlenum;
  }

  /**
   * ����������������Ʊ��ID
   * <p>
   * <b>����˵��</b>
   * 
   * @return ��Ʊ��ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-15 ����04:52:32
   */
  public String getPk_invoice() {
    return this.pk_invoice;
  }

  /**
   * ����������������Ʊ����ID
   * <p>
   * <b>����˵��</b>
   * 
   * @return ��Ʊ����ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-15 ����04:52:49
   */
  public String getPk_invoice_b() {
    return this.pk_invoice_b;
  }

  /**
   * ���������������Գ巢Ʊ��ID
   * <p>
   * <b>����˵��</b>
   * 
   * @return �Գ巢Ʊ��ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-15 ����05:00:29
   */
  public String getPk_rushinvoice() {
    return this.pk_rushinvoice;
  }

  /**
   * ���������������Գ�ķ�Ʊ��ID<br>
   * 1���������ⵥ��Ʊƥ��Ľ����У����ֵΪ��<br>
   * 2������Ƿ��÷�Ʊ�����ֵΪ��<br>
   * <p>
   * <b>����˵��</b>
   * 
   * @return �Գ�ķ�Ʊ��ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-13 ����04:21:06
   */
  public String getPk_rushinvoice_b() {
    return this.pk_rushinvoice_b;
  }

  /**
   * ���������������Գ���ⵥ��ID
   * <p>
   * <b>����˵��</b>
   * 
   * @return �Գ���ⵥ��ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-27 ����11:01:17
   */
  public String getPk_rushstock() {
    return this.pk_rushstock;
  }

  /**
   * ���������������Գ���ⵥ����ϸID
   * <p>
   * <b>����˵��</b>
   * 
   * @return �Գ���ⵥ����ϸID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-27 ����11:01:44
   */
  public String getPk_rushstock_b() {
    return this.pk_rushstock_b;
  }

  /**
   * �����������������㵥��ϸID
   * <p>
   * <b>����˵��</b>
   * 
   * @return ���㵥��ϸID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-21 ����04:17:21
   */
  public String getPk_settlebill_b() {
    return this.pk_settlebill_b;
  }

  /**
   * ����������������Ӧ��ⵥ�ĵ���ID<br>
   * ����Ǻ�����Ʊ�Գ壬���ֵΪ��<br>
   * <p>
   * <b>����˵��</b>
   * 
   * @return ��Ӧ��ⵥ�ĵ���ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-15 ����04:51:31
   */
  public String getPk_stock() {
    return this.pk_stock;
  }

  /**
   * ����������������Ӧ��ⵥ�ĵ�����ID<br>
   * ����Ǻ�����Ʊ�Գ壬���ֵΪ��<br>
   * <p>
   * <b>����˵��</b>
   * 
   * @return ��Ӧ��ⵥ�ĵ�����ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-13 ����04:18:34
   */
  public String getPk_stock_b() {
    return this.pk_stock_b;
  }

  /**
   * @return pk_stockorder
   */
  public String getPk_stockorder() {
    return this.pk_stockorder;
  }

  /**
   * ����������������Ӧ��ⵥ��Դͷ�ɹ������У�Ҫ����Ӧ�����Ա�Ӧ�����͸��������
   * <p>
   * <b>����˵��</b>
   * 
   * @return ��Ӧ��ⵥ��Դͷ�ɹ�������
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-13 ����04:19:05
   */
  public String getPk_stockorder_b() {
    return this.pk_stockorder_b;
  }

  /**
   * ����������������Ӧ��ⵥ�ĵ������ͣ�����Ǻ�����Ʊ�Գ壬���ֵΪ�գ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return ��Ӧ��ⵥ�ĵ�������
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-13 ����04:17:56
   */
  public String getVstockbilltype() {
    return this.vstockbilltype;
  }

  public void setBwashest(UFBoolean bwashest) {
    this.bwashest = bwashest;
  }

  /**
   * ��������������������ϸ��Ӧ��ȷ��Ӧ��Ӧ��ԭ�Ҽ�˰�ϼơ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param noppconfirmapmny = �������� * ��ⵥȷ��Ӧ��ԭ�Ҽ�˰�ϼ�(��β���)
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-28 ����08:59:14
   */
  public void setNoppconfirmapmny(UFDouble noppconfirmapmny) {
    this.noppconfirmapmny = noppconfirmapmny;
  }

  public void setNsettlenum(UFDouble nsettlenum) {
    this.nsettlenum = nsettlenum;
  }

  public void setPk_invoice(String pkInvoice) {
    this.pk_invoice = pkInvoice;
  }

  public void setPk_invoice_b(String pkInvoiceB) {
    this.pk_invoice_b = pkInvoiceB;
  }

  public void setPk_rushinvoice(String pkRushinvoice) {
    this.pk_rushinvoice = pkRushinvoice;
  }

  public void setPk_rushinvoice_b(String pkRushinvoiceB) {
    this.pk_rushinvoice_b = pkRushinvoiceB;
  }

  public void setPk_rushstock(String pkRushstock) {
    this.pk_rushstock = pkRushstock;
  }

  public void setPk_rushstock_b(String pkRushstockB) {
    this.pk_rushstock_b = pkRushstockB;
  }

  public void setPk_settlebill_b(String pkSettlebillB) {
    this.pk_settlebill_b = pkSettlebillB;
  }

  public void setPk_stock(String pkStock) {
    this.pk_stock = pkStock;
  }

  public void setPk_stock_b(String pkStockB) {
    this.pk_stock_b = pkStockB;
  }

  /**
   * @param pk_stockorder Ҫ���õ� pk_stockorder
   */
  public void setPk_stockorder(String pk_stockorder) {
    this.pk_stockorder = pk_stockorder;
  }

  public void setPk_stockorder_b(String pkStockorderB) {
    this.pk_stockorder_b = pkStockorderB;
  }

  public void setVstockbilltype(String vstockbilltype) {
    this.vstockbilltype = vstockbilltype;
  }

}
