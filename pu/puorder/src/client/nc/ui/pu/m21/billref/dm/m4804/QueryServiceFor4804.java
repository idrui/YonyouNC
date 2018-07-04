package nc.ui.pu.m21.billref.dm.m4804;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.dm.m4804.IOrderQueryFor4804;
import nc.ui.pu.m21.view.OrderQueryDLGInitializer;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 运输单参照采购订单的查询服务
 * 
 * @since 6.0
 * @version 2010-12-13 下午06:20:19
 * @author wuxla
 */

public class QueryServiceFor4804 extends PuRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    this.checkQueryCond(queryScheme);
    IOrderQueryFor4804 service =
        NCLocator.getInstance().lookup(IOrderQueryFor4804.class);
    return service.queryFor4804(queryScheme);
  }

  @Override
  protected String getRefOrgFieldCode() {
    return OrderQueryDLGInitializer.PK_ORDER_B_PK_FLOWSTOCKORG;
  }
}
