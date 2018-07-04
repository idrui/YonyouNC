package nc.pubimpl.pu.m422x.ewm.m4b32;

import nc.bs.pu.m422x.ewm.action.DeleteClose422XFor4B32Action;
import nc.pubitf.pu.m422x.ewm.m4b32.IDeleteClose422XFor4B32;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * �����������뵥�ṩ���ʲ�ά�޼ƻ���ɾ����رսӿ�ʵ����
 * �ýӿڿ��ܻᱻ�ʲ��Ǳ����ε��ã�
 * ����һ����һ�ε���û���׳�StoreqDeleteException�쳣
 * �������� ��һ�ε����׳���StoreqDeleteException�쳣���ʲ��Ǳ߲�����쳣��������ʾ�򡣲����û�ѡ���ǻ��Ƿ񣬶��������µ���һ�θýӿ�
 * �ýӿڿ��ܻ�����������⣬��һ�Σ��ڶ��ζ����ظ�ȥ��Ѱ���ݿ�
 * 
 * @since 6.5
 * @version 2014-2-17 ����04:31:49
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
