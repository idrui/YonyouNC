package nc.ui.pu.m23.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m23.maintain.IArriveMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.uif2.LoginContext;

/**
 * @since 6.0
 * @version 2011-1-13 ÏÂÎç01:11:34
 * @author yinfy
 */

public class ArriveCheckModelService implements IAppModelService, IQueryService {

  @Override
  public void delete(Object object) throws Exception {
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
    ArriveViewVO[] views =
        NCLocator.getInstance().lookup(IArriveMaintain.class).queryCheckArrive(
            queryScheme);
    return views;
  }

  @Override
  public Object update(Object object) throws Exception {
    return null;
  }

}
