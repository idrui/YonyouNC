/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-29 下午04:39:49
 */
package nc.ui.pu.m4t.service;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m4t.IInitialEstMaintain;
import nc.itf.pu.m4t.IInitialEstQuery;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单为model提交的与后台交互服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-29 下午04:39:49
 */
public class InitialEstService implements IAppModelService, IQueryService {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#delete(java.lang.Object)
   */
  @Override
  public void delete(Object object) throws Exception {
    IInitialEstMaintain service =
        NCLocator.getInstance().lookup(IInitialEstMaintain.class);
    if (object.getClass().isArray()) {
      service.delete((InitialEstVO[]) object, null);
    }
    else {
      service.delete(new InitialEstVO[] {
        (InitialEstVO) object
      }, null);
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#insert(java.lang.Object)
   */
  @Override
  public Object insert(Object object) throws Exception {
    // 
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#queryByDataVisibilitySetting(nc.vo.uif2.LoginContext)
   */
  @Override
  public Object[] queryByDataVisibilitySetting(LoginContext context)
      throws Exception {
    // 
    return null;
  }

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    IInitialEstQuery service =
        NCLocator.getInstance().lookup(IInitialEstQuery.class);
    return service.query(queryScheme);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#update(java.lang.Object)
   */
  @Override
  public Object update(Object object) throws Exception {
    // 
    return null;
  }

}
