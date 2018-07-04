package nc.ui.pu.m27.settlebill.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m27.ISettleBillQuery;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * 结算单维护查询的MODEL服务
 * 
 * @since 6.0
 * @version 2011-1-13 下午04:49:23
 * @author zhaoyha
 */

public class SettleBillModelService implements IQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    return NCLocator.getInstance().lookup(ISettleBillQuery.class).query(
        queryScheme);
  }

}
