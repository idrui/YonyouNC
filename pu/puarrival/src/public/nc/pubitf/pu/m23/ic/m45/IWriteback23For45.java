package nc.pubitf.pu.m23.ic.m45;

import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ṩ�����Ĳɹ���ⵥ�Ļ�д�ۼ���������ķ���ӿ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 ����04:14:51
 */
public interface IWriteback23For45 {

  /**
   * ���������������������ṩ�����Ĳɹ���ⵥ�Ļ�д�ۼ���������ķ���ӿ�
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author hanbin
   * @time 2010-1-8 ����04:05:12
   */
  public void writebackNum(IWriteback23For45Para[] paraArray)
      throws BusinessException;
}
