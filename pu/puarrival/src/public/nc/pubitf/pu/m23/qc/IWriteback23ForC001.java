package nc.pubitf.pu.m23.qc;

import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ṩ�����쵥�Ļ�д����ӿ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 ����04:14:51
 */
public interface IWriteback23ForC001 {
  /**
   * ����������������д���������ۼƱ������������ڱ��쵥��ʽ���ɡ�ɾ��������ʱ
   * <p>
   * <b>����˵��</b>
   * 
   * @param �������ӱ�id����
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-8 ����04:34:40
   */
  public void writebackNum(Writeback23ForC001Para[] paras)
      throws BusinessException;
}
