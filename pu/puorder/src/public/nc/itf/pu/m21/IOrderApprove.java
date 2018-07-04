/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 ����03:35:52
 */
package nc.itf.pu.m21;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ����������������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-13 ����03:35:52
 */
public interface IOrderApprove {

  /**
   * ������������������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-13 ����03:37:31
   */
  public OrderVO[] approve(OrderVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * �����������������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-13 ����03:38:58
   */
  public OrderVO[] sendapprove(OrderVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * �����������������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-13 ����03:38:13
   */
  public OrderVO[] unapprove(OrderVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * ���������������������ջز�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-13 ����03:38:58
   */
  public OrderVO[] unSendapprove(OrderVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

}
