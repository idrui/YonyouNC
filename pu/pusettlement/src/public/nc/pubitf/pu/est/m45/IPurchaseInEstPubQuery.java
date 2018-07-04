/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 ����01:07:00
 */
package nc.pubitf.pu.est.m45;

import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ѯ�ɹ���ⵥ���ݹ���Ϣ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-16 ����01:07:00
 */
public interface IPurchaseInEstPubQuery {
  /**
   * ����������������ѯ�ɹ���ⵥ���ݹ���Ϣ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param bids ����ID����
   * @return �������ݶ���������Ҫ����������(����δ�ݹ���������)
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-16 ����01:08:32
   */
  public PurchaseInEstVO[] query(String[] bids) throws BusinessException;
}
