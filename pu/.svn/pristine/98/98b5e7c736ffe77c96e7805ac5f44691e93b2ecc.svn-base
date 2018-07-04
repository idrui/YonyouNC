package nc.pubitf.pu.m422x.ewm.m4b32;

import nc.vo.pub.BusinessException;

/**
 * 物资需求申请单提供给资产维修计划的删除或关闭接口
 * 用于维修计划整单关闭或行关闭时调用。
 * 
 * @since 6.5
 * @version 2014-2-17 下午04:16:10
 * @author fanly3
 */
public interface IDeleteClose422XFor4B32 {
  /**
   * 关闭或是删除维修计划行对应的下游物资需求申请单
   * 
   * @param pk_repair_plan 维修计划主表pk
   * @param pk_repair_plan_inv 计划物料行pk
   * @param isDelete 是否删除标志位
   * @throws BusinessException
   */
  void closeStoreReqAppWhenCloseRepairPlan(String[] pk_repair_plan,
      String[] pk_repair_plan_inv, boolean isDelete) throws BusinessException;

  void DeleteStoreReqAppWhenDeleteRepairPlan(String[] pk_repair_plan,
      String[] pk_repair_plan_inv, boolean writeback) throws BusinessException;
}
