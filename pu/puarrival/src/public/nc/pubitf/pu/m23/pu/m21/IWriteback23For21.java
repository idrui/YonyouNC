package nc.pubitf.pu.m23.pu.m21;

import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ṩ���ɹ����������Ļ�д�ۼƲ��������ķ���ӿ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 ����04:14:51
 */
public interface IWriteback23For21 {

  /**
   * ���������������������ṩ���ɹ����������Ļ�д�ۼƲ��������ķ���ӿ�
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author hanbin
   * @time 2010-1-8 ����04:05:12
   */
  public void writebackNum(IWriteback23For21Para[] paraArray)
      throws BusinessException;
}
