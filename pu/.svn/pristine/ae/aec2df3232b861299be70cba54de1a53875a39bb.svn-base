package nc.ui.pu.m21.service;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderPayPlan;
import nc.itf.pu.m21.IOrderPayPlanQuery;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.IBatchAppModelService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.uif2.LoginContext;

/**
 * @since 6.0
 * @version 2011-1-4 ÉÏÎç09:02:42
 * @author wuxla
 */

public class OrderPayPlanModelService implements IBatchAppModelService,
    IQueryService {

  @Override
  public BatchOperateVO batchSave(BatchOperateVO batchVO) throws Exception {
    IOrderPayPlan service = NCLocator.getInstance().lookup(IOrderPayPlan.class);
    return service.batchSave(batchVO);
  }

  @Override
  public Object[] queryByDataVisibilitySetting(LoginContext context)
      throws Exception {
    return null;
  }

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    IOrderPayPlanQuery service =
        NCLocator.getInstance().lookup(IOrderPayPlanQuery.class);
    return service.queryByQueryScheme(queryScheme);
  }

}
