package nc.pubimpl.pu.m422x.ewm.m4b32;

import nc.bs.pu.m422x.query.QueryFor4b32BP;
import nc.pubitf.pu.m422x.ewm.m4b32.IQuery422XFor4b32;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class Query422XFor4b32Impl implements IQuery422XFor4b32 {

  @Override
  public StoreReqAppVO[] queryStoreReqAppsFor4b32(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return new QueryFor4b32BP(queryScheme).queryStoreReqVOs();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
