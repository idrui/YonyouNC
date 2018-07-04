package nc.pubimpl.pu.m422x.api;

import nc.bs.scmpub.query.SCMBillQuery;
import nc.bs.scmpub.query.SCMViewQuery;
import nc.pubitf.pu.m422x.api.IStoreReqAppQueryAPI;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class StoreReqAppQueryAPIImpl implements IStoreReqAppQueryAPI {

  @Override
  public StoreReqAppVO[] queryVOByScheme(IQueryScheme queryScheme) 
      throws BusinessException {
    try {
      SCMBillQuery<StoreReqAppVO> queryTool = 
          new SCMBillQuery<StoreReqAppVO>(StoreReqAppVO.class);
      return queryTool.queryVOByScheme(queryScheme);
    }
    catch (Exception e){
      ExceptionUtils.marsh(e);
      return null;
    } 
  }

  @Override
  public StoreReqAppVO[] queryVOByScheme(IQueryScheme queryScheme, String[] queryFields) 
      throws BusinessException {
    try {
      SCMBillQuery<StoreReqAppVO> queryTool = 
          new SCMBillQuery<StoreReqAppVO>(StoreReqAppVO.class);
      return queryTool.queryVOByScheme(queryScheme, queryFields);
    }
    catch (Exception e){
      ExceptionUtils.marsh(e);
      return null;
    }
  }

  @Override
  public StoreReqAppViewVO[] queryViewVOByScheme(IQueryScheme queryScheme) 
      throws BusinessException {
    try {
      SCMViewQuery<StoreReqAppViewVO> queryTool = 
          new SCMViewQuery<StoreReqAppViewVO>(StoreReqAppViewVO.class);
      return queryTool.queryViewVOByScheme(queryScheme);
    }
    catch (Exception e){
      ExceptionUtils.marsh(e);
      return null;
    }
  }

  @Override
  public StoreReqAppViewVO[] queryViewVOByScheme(IQueryScheme queryScheme,
      String[] queryFields) throws BusinessException {
    try {
      SCMViewQuery<StoreReqAppViewVO> queryTool = 
          new SCMViewQuery<StoreReqAppViewVO>(StoreReqAppViewVO.class);
      return queryTool.queryViewVOByScheme(queryScheme, queryFields);
    }
    catch (Exception e){
      ExceptionUtils.marsh(e);
      return null;
    }
  }

  @Override
  public StoreReqAppVO[] queryVOByIDs(String[] ids) 
      throws BusinessException {
    try {
      SCMBillQuery<StoreReqAppVO> queryTool = 
          new SCMBillQuery<StoreReqAppVO>(StoreReqAppVO.class);
      return queryTool.queryVOByIDs(ids);
    }
    catch (Exception e){
      ExceptionUtils.marsh(e);
      return null;
    } 
  }

  @Override
  public StoreReqAppVO[] queryVOByIDs(String[] ids, String[] queryFields) 
      throws BusinessException {
    try {
      SCMBillQuery<StoreReqAppVO> queryTool = 
          new SCMBillQuery<StoreReqAppVO>(StoreReqAppVO.class);
      return queryTool.queryVOByIDs(ids, queryFields);
    }
    catch (Exception e){
      ExceptionUtils.marsh(e);
      return null;
    } 
  }

  @Override
  public StoreReqAppViewVO[] queryViewVOByBIDs(String[] bids) 
      throws BusinessException {
    try {
      SCMViewQuery<StoreReqAppViewVO> queryTool = 
          new SCMViewQuery<StoreReqAppViewVO>(StoreReqAppViewVO.class);
      return queryTool.queryViewVOByBIDs(bids);
    }
    catch (Exception e){
      ExceptionUtils.marsh(e);
      return null;
    }
  }

  @Override
  public StoreReqAppViewVO[] queryViewVOByBIDs(String[] bids, String[] queryFields) 
      throws BusinessException {
    try {
      SCMViewQuery<StoreReqAppViewVO> queryTool = 
          new SCMViewQuery<StoreReqAppViewVO>(StoreReqAppViewVO.class);
      return queryTool.queryViewVOByBIDs(bids, queryFields);
    }
    catch (Exception e){
      ExceptionUtils.marsh(e);
      return null;
    }
  }

  @Override
  public StoreReqAppViewVO[] queryViewVOBySourceBIDs(String[] sourceBIDs) 
      throws BusinessException {
    try {
      SCMViewQuery<StoreReqAppViewVO> queryTool = 
          new SCMViewQuery<StoreReqAppViewVO>(StoreReqAppViewVO.class);
      return queryTool.queryViewVOBySourceBIDs(StoreReqAppItemVO.CSOURCEBID, sourceBIDs, null);
    }
    catch (Exception e){
      ExceptionUtils.marsh(e);
      return null;
    }
  }
}
