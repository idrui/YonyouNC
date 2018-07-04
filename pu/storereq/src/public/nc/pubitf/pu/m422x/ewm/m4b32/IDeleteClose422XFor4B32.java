package nc.pubitf.pu.m422x.ewm.m4b32;

import nc.vo.pub.BusinessException;

/**
 * �����������뵥�ṩ���ʲ�ά�޼ƻ���ɾ����رսӿ�
 * ����ά�޼ƻ������رջ��йر�ʱ���á�
 * 
 * @since 6.5
 * @version 2014-2-17 ����04:16:10
 * @author fanly3
 */
public interface IDeleteClose422XFor4B32 {
  /**
   * �رջ���ɾ��ά�޼ƻ��ж�Ӧ�����������������뵥
   * 
   * @param pk_repair_plan ά�޼ƻ�����pk
   * @param pk_repair_plan_inv �ƻ�������pk
   * @param isDelete �Ƿ�ɾ����־λ
   * @throws BusinessException
   */
  void closeStoreReqAppWhenCloseRepairPlan(String[] pk_repair_plan,
      String[] pk_repair_plan_inv, boolean isDelete) throws BusinessException;

  void DeleteStoreReqAppWhenDeleteRepairPlan(String[] pk_repair_plan,
      String[] pk_repair_plan_inv, boolean writeback) throws BusinessException;
}
