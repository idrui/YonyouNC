/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-28 ����08:08:01
 */
package nc.itf.pu.m422x;

import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ر�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-28 ����08:08:01
 */
public interface IStoreReqAppClose {

  /**
   * �������������������ر�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-28 ����08:13:18
   */
  public StoreReqAppVO[] billClose(StoreReqAppVO[] vos)
      throws BusinessException;

  /**
   * ��������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-28 ����09:30:18
   */
  public StoreReqAppVO[] billOpen(StoreReqAppVO[] vos) throws BusinessException;

  /**
   * ���������������йر�/��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-28 ����08:13:56
   */
  public StoreReqAppVO[] rowClose(StoreReqAppVO[] vos) throws BusinessException;

  /**
   * ���������������д�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-28 ����09:30:22
   */
  public StoreReqAppVO[] rowOpen(StoreReqAppVO[] vos) throws BusinessException;
}
