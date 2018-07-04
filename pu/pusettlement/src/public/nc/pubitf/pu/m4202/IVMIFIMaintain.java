/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-9 ����01:21:32
 */
package nc.pubitf.pu.m4202;

import nc.vo.ic.m50.entity.VmiSumVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���Ļ��ܲ���(����)ά������
 * <li>Ϊ������Ļ�������/ȡ���������ṩ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-9 ����01:21:32
 */
public interface IVMIFIMaintain {
  /**
   * �����������������Ļ���ȡ������ʱɾ�����񸱱���
   * <p>
   * <b>����˵��</b>
   * 
   * @param hids ���Ļ��ܱ�ͷID����
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-9 ����01:24:52
   */
  public void cancelDupVMI(String[] hids) throws BusinessException;

  /**
   * �����������������Ļ�������ʱд���񸱱���
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos ���Ļ���VO����
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-9 ����01:24:46
   */
  public void duplicateVMI(VmiSumVO[] vos) throws BusinessException;
}
