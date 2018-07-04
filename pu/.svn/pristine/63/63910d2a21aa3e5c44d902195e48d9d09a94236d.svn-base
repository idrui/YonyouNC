package nc.ui.pu.m422x.billref.pu.m20;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m422x.pu.m20.IQuery422xFor20;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;

public class QueryServiceFor20 extends PuRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    this.checkQueryCond(queryScheme);
    IQuery422xFor20 queryService =
        NCLocator.getInstance().lookup(IQuery422xFor20.class);
    StoreReqAppVO[] bills = queryService.queryStoreReqApps(queryScheme);
    return bills;
  }

  @Override
  protected String getRefOrgFieldCode() {
    return StoreReqAppHeaderVO.PK_ORG;
  }
}
