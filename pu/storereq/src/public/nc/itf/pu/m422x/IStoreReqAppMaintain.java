/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 ����08:54:43
 */
package nc.itf.pu.m422x;

import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������������ά��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-19 ����08:54:43
 */
public interface IStoreReqAppMaintain {

  /**
   * ��������������ɾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-19 ����08:56:46
   */
  public void delete(StoreReqAppVO[] vos) throws BusinessException;

  /**
   * ������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-19 ����08:56:01
   */
  public StoreReqAppVO[] save(StoreReqAppVO[] vos) throws BusinessException;
}
