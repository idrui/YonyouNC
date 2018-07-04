package nc.pubimpl.pu.m422x.ewm.m4b32;

import nc.bs.pu.m422x.ewm.action.DeleteClose422XFor4B32Action;
import nc.pubitf.pu.m422x.ewm.m4b32.IDeleteClose422XFor4B32;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 物资需求申请单提供给资产维修计划的删除或关闭接口实现类
 * 该接口可能会被资产那边两次调用：
 * 场景一：第一次调用没有抛出StoreqDeleteException异常
 * 场景二： 第一次调用抛出了StoreqDeleteException异常，资产那边捕获该异常，弹出提示框。不管用户选择是还是否，都会再重新调用一次该接口
 * 该接口可能会出现性能问题，第一次，第二次都会重复去查寻数据库
 * 
 * @since 6.5
 * @version 2014-2-17 下午04:31:49
 * @author fanly3
 */
public class DeleteClose422XFor4B32Impl implements IDeleteClose422XFor4B32 {

  @Override
  public void closeStoreReqAppWhenCloseRepairPlan(String[] pk_repair_plan,
      String[] pk_repair_plan_inv, boolean isDelete) throws BusinessException {
    try {
      new DeleteClose422XFor4B32Action().deleteCloseStoreReqApp(pk_repair_plan,
          pk_repair_plan_inv, isDelete);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void DeleteStoreReqAppWhenDeleteRepairPlan(String[] pk_repair_plan,
      String[] pk_repair_plan_inv, boolean writeback) throws BusinessException {
    try {
      new DeleteClose422XFor4B32Action().deleteStoreReqApp(pk_repair_plan,
          pk_repair_plan_inv, writeback);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
