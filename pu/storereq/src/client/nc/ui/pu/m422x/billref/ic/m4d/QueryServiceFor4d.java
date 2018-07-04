package nc.ui.pu.m422x.billref.ic.m4d;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m422x.ic.m4d.IQuery422xFor4d;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请提供给材料出库单的查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-15 上午11:26:48
 */
public class QueryServiceFor4d extends PuRefQueryService {
  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    this.checkQueryCond(queryScheme);
    IQuery422xFor4d queryService =
        NCLocator.getInstance().lookup(IQuery422xFor4d.class);
    StoreReqAppVO[] bills = queryService.queryStoreReqApps(queryScheme);
    return bills;
  }

  @Override
  protected String getRefOrgFieldCode() {
    return StoreReqAppHeaderVO.PK_ORG;
  }
}
