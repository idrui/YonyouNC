/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-2 ����02:11:25
 */
package nc.itf.pu.m20;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-2 ����02:11:25
 */
public interface IPraybillApprove {

  /**
   * ���������������빺��������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @param script
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author linsf
   * @return ��������빺��
   * @time 2010-2-2 ����02:11:57
   */
  PraybillVO[] approve(PraybillVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * ���������������빺������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @param script
   * @return ������빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-26 ����11:23:51
   */
  PraybillVO[] sendapprove(PraybillVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * ���������������빺������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @param script
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author linsf
   * @return �������빺��
   * @time 2010-2-2 ����02:13:31
   */
  PraybillVO[] unApprove(PraybillVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * ���������������빺���ջء�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @param script
   * @return �ջص��빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-26 ����11:23:51
   */
  PraybillVO[] unSendapprove(PraybillVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

}
