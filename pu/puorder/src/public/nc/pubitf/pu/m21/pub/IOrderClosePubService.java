/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-12 ����10:24:49
 */
package nc.pubitf.pu.m21.pub;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * �����ر�/�򿪲���,��������
 * 
 * @since 6.0
 * @version 2012-5-30 ����03:06:05
 * @author tianft
 */
public interface IOrderClosePubService {

  /**
   * �����ر�
   * 
   * @param vos ����ȫvo����
   * @return �����ر�̬�Ķ���ȫvo����
   * @throws BusinessException
   */
  public OrderVO[] arriveClose(OrderVO[] vos) throws BusinessException;

  /**
   * ������
   * 
   * @param vos ����ȫvo����
   * @return ������̬�Ķ���ȫvo����
   * @throws BusinessException
   */
  public OrderVO[] arriveOpen(OrderVO[] vos) throws BusinessException;

  /**
   * ���չر�
   * 
   * @param vos ����ȫvo����
   * @return ���չر�̬�Ķ���ȫvo����
   * @throws BusinessException
   */
  public OrderVO[] finalClose(OrderVO[] vos) throws BusinessException;

  /**
   * ���մ�
   * 
   * @param vos ����ȫvo����
   * @return ���մ�̬�Ķ���ȫvo����
   * @throws BusinessException
   */
  public OrderVO[] finalOpen(OrderVO[] vos) throws BusinessException;

  /**
   * ��Ʊ�ر�
   * 
   * @param vos ����ȫvo����
   * @return ��Ʊ�ر�̬�Ķ���ȫvo����
   * @throws BusinessException
   */
  public OrderVO[] invoiceClose(OrderVO[] vos) throws BusinessException;

  /**
   * ��Ʊ��
   * 
   * @param vos ����ȫvo����
   * @return ��Ʊ��̬�Ķ���ȫvo����
   * @throws BusinessException
   */
  public OrderVO[] invoiceOpen(OrderVO[] vos) throws BusinessException;

  /**
   * ����ر�
   * 
   * @param vos ����ȫvo����
   * @return ����ر�̬�Ķ���ȫvo����
   * @throws BusinessException
   */
  public OrderVO[] payClose(OrderVO[] vos) throws BusinessException;

  /**
   * �����
   * 
   * @param vos ����ȫvo����
   * @return �����̬�Ķ���ȫvo����
   * @throws BusinessException
   */
  public OrderVO[] payOpen(OrderVO[] vos) throws BusinessException;

  /**
   * �йر�
   * 
   * @param vos ����ȫvo����
   * @return �йر�̬�Ķ���ȫvo����
   * @throws BusinessException
   */
  public OrderVO[] rowClose(OrderVO[] vos) throws BusinessException;

  /**
   * �д�
   * 
   * @param vos ����ȫvo����
   * @return �д�̬�Ķ���ȫvo����
   * @throws BusinessException
   */
  public OrderVO[] rowOpen(OrderVO[] vos) throws BusinessException;

  /**
   * ���ر�
   * 
   * @param vos ����ȫvo����
   * @return ���ر�̬�Ķ���ȫvo����
   * @throws BusinessException
   */
  public OrderVO[] storeClose(OrderVO[] vos) throws BusinessException;

  /**
   * ����
   * 
   * @param vos ����ȫvo����
   * @return ����̬�Ķ���ȫvo����
   * @throws BusinessException
   */
  public OrderVO[] storeOpen(OrderVO[] vos) throws BusinessException;

}
