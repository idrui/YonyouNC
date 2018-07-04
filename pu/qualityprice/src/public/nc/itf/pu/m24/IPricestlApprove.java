package nc.itf.pu.m24;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥��������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-7-30 ����11:47:25
 */
public interface IPricestlApprove {

  /**
   * ���������������۸���㵥������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-7-30 ����11:47:58
   */
  PricestlVO[] approve(PricestlVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * ���������������۸���㵥����
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-7-30 ����11:49:12
   */
  PricestlVO[] sendapprove(PricestlVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * ���������������۸���㵥����
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-7-30 ����11:48:58
   */
  PricestlVO[] unApprove(PricestlVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * ���������������۸���㵥�ջء�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-7-30 ����11:49:12
   */
  PricestlVO[] unSendapprove(PricestlVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

}
