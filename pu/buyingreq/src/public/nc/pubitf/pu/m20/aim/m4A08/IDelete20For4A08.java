package nc.pubitf.pu.m20.aim.m4A08;

import nc.vo.pub.BusinessException;

/**
 * �ʲ���������ȡ������ʱɾ�������빺��
 * 
 * @since 6.5
 * @version 2014-2-14 ����10:38:32
 * @author fanly3
 */
public interface IDelete20For4A08 {
  /**
   * �ʲ���������ȡ������ʱɾ�������빺��
   * 
   * @param pk_table �ʲ�������������pks
   * @param pk_table_bs �ʲ����������ӱ�pks
   * @throws BusinessException
   */
  void deletePrayBills(String[] pk_table, String[] pk_table_bs)
      throws BusinessException;
}
