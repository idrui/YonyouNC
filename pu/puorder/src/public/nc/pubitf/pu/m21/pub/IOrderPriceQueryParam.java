package nc.pubitf.pu.m21.pub;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ѯ����ʹ�õĽӿ����Ͳ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-21 ����03:00:48
 */
public interface IOrderPriceQueryParam {

  /**
   * ��������������//��ý��������֯��
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.1
   * @author yangtian
   * @time 2011-1-11 ����03:14:53
   */
  public String getFinanceOrg();

  /**
   * ��������������//�������OID��
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 ����03:14:42
   */
  public String getMaterial();

  /**
   * ��������������//�������λԭ�Ҳ���˰���ۡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 ����03:15:16
   */
  public UFDouble getOrigPrice();

  /**
   * ��������������//�������λԭ�Һ�˰���ۡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 ����03:15:13
   */
  public UFDouble getOrigTaxPrice();

  /**
   * ��������������//�������λ���Ҳ���˰���ۡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 ����03:15:10
   */
  public UFDouble getPrice();

  /**
   * ��������������//�������λ���Һ�˰���ۡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 ����03:15:06
   */
  public UFDouble getTaxPrice();

  /**
   * ��������������//���ý��������֯��
   * <p>
   * <b>����˵��</b>
   * 
   * @param financeOrg
   *          <p>
   * @since 6.1
   * @author yangtian
   * @time 2011-1-11 ����03:14:57
   */
  public void setFinanceOrg(String financeOrg);

  /**
   * ��������������//��������λԭ�Ҳ���˰���ۡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param origNetPrice
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 ����03:15:03
   */
  public void setOrigPrice(UFDouble origNetPrice);

  /**
   * ��������������//��������λԭ�Һ�˰���ۡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param origTaxNetPrice
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 ����03:15:00
   */
  public void setOrigTaxPrice(UFDouble origTaxNetPrice);

  /**
   * ��������������//��������λ���Ҳ���˰���ۡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param netPrice
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 ����03:14:57
   */
  public void setPrice(UFDouble netPrice);

  /**
   * ��������������//��������λ���Һ�˰���ۡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param taxNetPrice
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-21 ����03:14:53
   */
  public void setTaxPrice(UFDouble taxNetPrice);
}
