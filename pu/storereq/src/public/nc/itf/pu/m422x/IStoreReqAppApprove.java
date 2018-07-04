/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 ����08:57:43
 */
package nc.itf.pu.m422x;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����������뵥�����������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-19 ����08:57:43
 */
public interface IStoreReqAppApprove {
  /**
   * �������������������������뵥������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-19 ����08:59:54
   */
  public StoreReqAppVO[] approve(StoreReqAppVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * �������������������������뵥�����������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-19 ����09:00:04
   */
  public StoreReqAppVO[] sendapprove(StoreReqAppVO[] vos,
      AbstractCompiler2 script) throws BusinessException;

  /**
   * �������������������������뵥�����������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-19 ����08:59:59
   */
  public StoreReqAppVO[] unapprove(StoreReqAppVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * �������������������������뵥���ջز�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-19 ����09:00:04
   */
  public StoreReqAppVO[] unSendapprove(StoreReqAppVO[] vos,
      AbstractCompiler2 script) throws BusinessException;
}
