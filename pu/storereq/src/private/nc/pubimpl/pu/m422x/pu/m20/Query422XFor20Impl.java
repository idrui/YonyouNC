package nc.pubimpl.pu.m422x.pu.m20;

import nc.bs.pu.m422x.query.QueryFor20BP;
import nc.pubitf.pu.m422x.pu.m20.IQuery422xFor20;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class Query422XFor20Impl implements IQuery422xFor20 {

  @Override
  public StoreReqAppVO[] queryStoreReqApps(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return new QueryFor20BP(queryScheme).queryStoreReqVOs();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
  
}
