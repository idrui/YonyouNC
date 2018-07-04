/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-19 ����10:57:29
 */
package nc.itf.pu.m25;

import nc.vo.pu.m25.entity.InvoicePriceQueryVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���ƱĬ��ѯ�۲������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-19 ����10:57:29
 */
public interface IDefaultPriceQuery {
  /**
   * �����ż۵�ѯ��
   * 
   * @param para
   * @return
   * @throws BusinessException
   */
  public InvoicePriceQueryVO[] queryHqHpPrice(InvoicePriceQueryVO[] para)
      throws BusinessException;

  /**
   * ѯ�ƻ���
   * 
   * @param para
   * @return
   * @throws BusinessException
   */
  public InvoicePriceQueryVO[] queryPlanPrice(InvoicePriceQueryVO[] para)
      throws BusinessException;

  /**
   * ֻ���ݼ۸���Դѯ��
   * 
   * @param para
   * @return
   * @throws BusinessException
   */
  public InvoicePriceQueryVO[] queryPriceBySysPara(InvoicePriceQueryVO[] para)
      throws BusinessException;

  /**
   * ���������ż۵�ѯ�ۣ�ͬʱҲ����ݼ۸���Դѯ�ۣ������ż۵ļ۸�����ǰ��
   * 
   * @param para
   * @param hqhp �Ƿ������ż�
   * @return
   * @throws BusinessException
   */
  public InvoicePriceQueryVO[] queryPriceWithHqHp(InvoicePriceQueryVO[] para)
      throws BusinessException;
}
