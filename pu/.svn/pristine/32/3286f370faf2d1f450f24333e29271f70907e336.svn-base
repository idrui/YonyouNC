/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-31 下午08:40:12
 */
package nc.ui.pu.m21.service;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderReplenishQuery;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>补货ModelService
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-31 下午08:40:12
 */
public class OrderReplenishModelService implements IAppModelService,
    IQueryService {

  @Override
  public void delete(Object object) throws Exception {
    return;
  }

  @Override
  public Object insert(Object object) throws Exception {
    return null;
  }

  @Override
  public Object[] queryByDataVisibilitySetting(LoginContext context)
      throws Exception {
    return null;
  }

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    IOrderReplenishQuery queryService =
        NCLocator.getInstance().lookup(IOrderReplenishQuery.class);
    return queryService.replenishQuery(queryScheme);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#update(java.lang.Object)
   */
  @Override
  public Object update(Object object) throws Exception {
    return null;
  }

}
