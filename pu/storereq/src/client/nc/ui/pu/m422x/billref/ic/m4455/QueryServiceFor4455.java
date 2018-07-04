package nc.ui.pu.m422x.billref.ic.m4455;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m422x.ic.m4455.IQuery422XFor4455;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;

/**
 * @since 6.0
 * @version 2010-12-16 ÏÂÎç08:22:25
 * @author wuxla
 */

public class QueryServiceFor4455 extends PuRefQueryService {
  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    this.checkQueryCond(queryScheme);
    IQuery422XFor4455 queryService =
        NCLocator.getInstance().lookup(IQuery422XFor4455.class);
    StoreReqAppVO[] bills = queryService.queryStoreReqAppsFor4455(queryScheme);
    return bills;
  }

  @Override
  protected String getRefOrgFieldCode() {
    return StoreReqAppHeaderVO.PK_ORG;
  }

}
