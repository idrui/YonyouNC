/**
 * 
 */
package nc.itf.pu.m21;

import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����ά������
 * <li>����
 * <li>ɾ��
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2009-12-28 ����04:15:32
 */
public interface IOrderMaintain {

  /**
   * ��������������ɾ��������
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVos ������vo
   * @param ctxs
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-28 ����04:17:17
   */
  public void delete(OrderVO[] orderVos, OrderContext[] ctxs)
      throws BusinessException;

  /**
   * ����ɾ��
   * 
   * @param orderVos ������ȫvo
   * @param ctxs �����Ļ���
   * @throws BusinessException
   */
  public void deleteByFullVOs(OrderVO[] orderVos, OrderContext[] ctxs)
      throws BusinessException;

  /**
   * �����������������ᶩ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVos Ҫ����Ķ���VO���飨������vo��
   * @param freezeReason ����ԭ��
   * @return �����Ķ���VO���飨������vo��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-21 ����01:09:17
   */
  public OrderVO[] freezeOrder(OrderVO[] orderVos, String freezeReason)
      throws BusinessException;

  /**
   * ��������-����ȫvo
   * 
   * @param orderVos ����ȫvo
   * @param freezeReason
   * @return �����Ķ���ȫvo
   * @throws BusinessException
   */
  public OrderVO[] freezeOrderByFullVOs(OrderVO[] orderVos, String freezeReason)
      throws BusinessException;

  /**
   * ��������
   * 
   * @param orderClientVos ����ȫvo����
   * @param ctx �����Ļ���
   * @return ����ȫvo����
   * @throws BusinessException
   */
  public OrderVO[] insert(OrderVO[] orderClientVos, OrderContext ctx)
      throws BusinessException;

  /**
   * ά������
   * 
   * @param orderClientVos ǰ̨ȫvo
   * @return ������ȫvo
   * @throws BusinessException
   */
  public OrderVO[] save(OrderVO[] orderClientVos, OrderContext ctx)
      throws BusinessException;

  /**
   * ���������������ⶳ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVos Ҫ�ⶳ�Ķ���VO���飨������vo��
   * @return �ⶳ��Ķ���VO���飨������vo��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-21 ����01:09:46
   */
  public OrderVO[] unfreezeOrder(OrderVO[] orderVos) throws BusinessException;

  /**
   * �����ⶳ-����ȫvo
   * 
   * @param orderVos ����ȫvo
   * @return �ⶳ��Ķ���ȫvo
   * @throws BusinessException
   */
  public OrderVO[] unfreezeOrderByFullVOs(OrderVO[] orderVos)
      throws BusinessException;

  /**
   * �޸ı��棨���£�����
   * 
   * @param orderClientVos ����ȫvo����
   * @param ctx ������
   * @return ����ȫvo����
   * @throws BusinessException
   */
  public OrderVO[] update(OrderVO[] orderClientVos, OrderContext ctx)
      throws BusinessException;
}
