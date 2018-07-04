/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-27 下午04:00:05
 */
package nc.ui.pu.position.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.position.IPosition;
import nc.ui.pubapp.uif2app.model.IQueryService;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-27 下午04:00:05
 */
public class PositionModelService implements IAppModelService, IQueryService {
  private LoginContext loginContext = null;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#delete(java.lang.Object)
   */
  @Override
  public void delete(Object object) throws Exception {
    if (object.getClass().isArray()) {
      this.service().delete((PositionVO[]) object);
    }
    else {
      this.service().delete(new PositionVO[] {
        (PositionVO) object
      });
    }

  }

  public LoginContext getContext() {
    return this.loginContext;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelservice()#insert(java.lang.Object)
   */
  @Override
  public Object insert(Object object) throws Exception {
    if (object.getClass().isArray()) {
      return this.service().insert((PositionVO[]) object)[0];
    }

    return this.service().insert(new PositionVO[] {
      (PositionVO) object
    })[0];

  }

  /**
   * 父类方法重写
   * 
   * @see
   *      nc.ui.uif2.model.IAppModelservice()#queryByDataVisibilitySetting(nc.vo
   *      .
   *      uif2.LoginContext)
   */
  @Override
  public Object[] queryByDataVisibilitySetting(LoginContext context)
      throws Exception {
    return this.service().queryPositions(this.getContext().getNodeCode(), null);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.model.IQueryService#queryByWhereSql(java.lang.String)
   */
  @Override
  public Object[] queryByWhereSql(String sql) throws Exception {

    return this.service().queryPositions(this.getContext().getNodeCode(), sql);
  }

  public void setContext(LoginContext loginContext) {
    this.loginContext = loginContext;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#update(java.lang.Object)
   */
  @Override
  public Object update(Object object) throws Exception {
    if (object.getClass().isArray()) {
      return this.service().update((PositionVO[]) object)[0];
    }

    return this.service().update(new PositionVO[] {
      (PositionVO) object
    })[0];

  }

  private IPosition service() {
    return NCLocator.getInstance().lookup(IPosition.class);
  }

}
