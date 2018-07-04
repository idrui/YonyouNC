/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-11 下午04:41:37
 */
package nc.ui.pu.m21.billref.pu.m4t;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.pu.m4t.IOrderQueryFor4T;
import nc.ui.pu.m21.view.OrderQueryDLGInitializer;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单参照采购订单的查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-11 下午04:41:37
 */
public class QueryServiceFor4t extends PuRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    this.checkQueryCond(queryScheme);
    IOrderQueryFor4T queryService =
        NCLocator.getInstance().lookup(IOrderQueryFor4T.class);
    return queryService.queryFor4t(queryScheme);
  }

  @Override
  protected String getRefOrgFieldCode() {
    return OrderQueryDLGInitializer.PK_ORDER_B_PK_PSFINANCEORG;
  }
}
