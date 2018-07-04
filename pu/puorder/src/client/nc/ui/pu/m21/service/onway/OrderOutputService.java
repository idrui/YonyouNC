/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-1 上午11:43:07
 */
package nc.ui.pu.m21.service.onway;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOutputQuery;
import nc.itf.pu.m21.IStatusMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单输出查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-1 上午11:43:07
 */
public class OrderOutputService implements IAppModelService, IQueryService {

  private AbstractUIAppModel model = null;

  private Class<? extends IOutputQuery> outputQuery;

  private Class<? extends IStatusMaintain> statusMaintain;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#delete(java.lang.Object)
   */
  @Override
  public void delete(Object object) throws Exception {
    // 输出状态维护
    if (object == null) {
      return;
    }
    OrderVO[] newVOs = null;
    if (object instanceof OrderVO) {
      newVOs = new OrderVO[] {
        (OrderVO) object
      };
    }
    else if (object instanceof OrderVO[]) {
      newVOs = (OrderVO[]) object;
    }
    if (null != this.statusMaintain) {
      NCLocator.getInstance().lookup(this.statusMaintain)
          .deleteForOutput(newVOs);
    }
    return;
  }

  public AbstractUIAppModel getModel() {
    return this.model;
  }

  public Class<? extends IOutputQuery> getOutputQuery() {
    return this.outputQuery;
  }

  public Class<? extends IStatusMaintain> getStatusMaintain() {
    return this.statusMaintain;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#insert(java.lang.Object)
   */
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
    if (null != this.outputQuery) {

      return NCLocator.getInstance().lookup(this.outputQuery)
          .outputQuery(queryScheme);
    }

    return null;
  }

  // @Override
  // public Object[] queryByWhereSql(String whereSql) throws Exception {
  // if (null != this.outputQuery) {
  //
  // return NCLocator.getInstance().lookup(this.outputQuery)
  // .outputQuery(whereSql);
  // }
  //
  // return null;
  // }

  public void setModel(AbstractUIAppModel model) {
    this.model = model;
  }

  public void setOutputQuery(Class<? extends IOutputQuery> outputQuery) {
    this.outputQuery = outputQuery;
  }

  public void setStatusMaintain(Class<? extends IStatusMaintain> statusMaintain) {
    this.statusMaintain = statusMaintain;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#update(java.lang.Object)
   */
  @Override
  public Object update(Object object) throws Exception {
    // 输出状态维护
    if (object == null) {
      return null;
    }
    OrderVO[] newVOs = null;
    if (object instanceof OrderVO) {
      newVOs = new OrderVO[] {
        (OrderVO) object
      };
    }
    else if (object instanceof OrderVO[]) {
      newVOs = (OrderVO[]) object;
    }
    if (null != this.statusMaintain) {
      return NCLocator.getInstance().lookup(this.statusMaintain)
          .updateForOutput(newVOs);
    }
    return null;
  }
}
