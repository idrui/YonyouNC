package nc.pubitf.pu.m20.am.m4b36;

import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺��Ϊ�ʲ�ά�޹����ṩ�Ĳ�ѯ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-26 ����07:22:29
 */
public interface IQuery20For4b36 {
  /**
   * �������������������ʲ�ά�޹�����ͷID��ѯ�����빺����
   * <p>
   * <b>����˵��</b>
   * 
   * @param billids
   *          �ʲ�ά�޹�����ͷid����
   * @return �빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-5-21 ����03:54:18
   */
  PraybillVO[] queryBillBySource(String[] billids) throws BusinessException;

  /**
   * �������������������ʲ�ά�޹�������ID�����ѯ�����빺����
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemids
   *          �ʲ�ά�޹�������id����
   * @return �빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-5-21 ����03:54:18
   */
  PraybillVO[] queryBillBySourceBID(String[] itemids) throws BusinessException;

}
