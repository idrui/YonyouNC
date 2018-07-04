package nc.itf.pu.m23.approve;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>�����������������ӿڶ��壬������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����������
 * <li>������������
 * <li>������������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-11 ����01:38:53
 */
public interface IArriveApprove {

  /**
   * �������������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param voArray
   * @param script
   * @return ȫVO����
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-19 ����01:30:09
   */
  public ArriveVO[] approveArrive(ArriveVO[] voArray, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * ��������������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param voArray
   * @param script
   * @return ������VO����
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-19 ����01:31:03
   */
  public ArriveVO[] sendArrive(ArriveVO[] voArray, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * ��������������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param voArray
   * @param script
   * @return ȫVO����
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-19 ����01:30:47
   */
  public ArriveVO[] unApproveArrive(ArriveVO[] voArray, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * �����������������������ջ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param voArray
   * @param script
   * @return ������VO����
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-19 ����01:31:03
   */
  public ArriveVO[] unSendArrive(ArriveVO[] voArray, AbstractCompiler2 script)
      throws BusinessException;
}
