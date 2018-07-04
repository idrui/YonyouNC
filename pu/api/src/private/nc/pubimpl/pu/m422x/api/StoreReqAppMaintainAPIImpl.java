package nc.pubimpl.pu.m422x.api;

import nc.pubimpl.pu.m422x.api.action.StoreReqAppAPIDeleteAction;
import nc.pubimpl.pu.m422x.api.action.StoreReqAppAPIInsertAction;
import nc.pubitf.pu.m422x.api.IStoreReqAppMaintainAPI;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;

public class StoreReqAppMaintainAPIImpl implements IStoreReqAppMaintainAPI {

  @Override
  public StoreReqAppVO[] insertVO(StoreReqAppVO[] vos) 
      throws BusinessException {
    return new StoreReqAppAPIInsertAction().insert(vos);
  }

  @Override
  public StoreReqAppVO[] approveVO(StoreReqAppVO[] vos) 
      throws BusinessException {
    return null;
  }

  @Override
  public StoreReqAppVO[] unapproveVO(StoreReqAppVO[] vos) 
      throws BusinessException {
    return null;
  }

  @Override
  public void deleteVOByIDs(String[] ids) 
      throws BusinessException {
    new StoreReqAppAPIDeleteAction().delete(ids);
  }

  @Override
  public void deleteVOBySourceIDs(String[] sourceIDs) 
      throws BusinessException {
  }

}
