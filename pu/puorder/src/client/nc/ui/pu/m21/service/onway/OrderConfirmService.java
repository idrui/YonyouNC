/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-13 下午01:03:00
 */
package nc.ui.pu.m21.service.onway;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IConfirmQuery;
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
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-13 下午01:03:00
 */
public class OrderConfirmService implements IAppModelService, IQueryService {

  private Class<? extends IConfirmQuery> confirmQuery;

  private AbstractUIAppModel model;

  private Class<? extends IStatusMaintain> statusMaintain;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.model.IAppModelService#delete(java.lang.Object)
   */
  @Override
  public void delete(Object object) throws Exception {
    // 对方确认状态反确认
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
          .deleteForConfirm(newVOs);
    }
    return;

  }

  public Class<? extends IConfirmQuery> getConfirmQuery() {
    return this.confirmQuery;
  }

  public AbstractUIAppModel getModel() {
    return this.model;
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
    if (null != this.confirmQuery) {

      OrderVO[] orderVOs =
          NCLocator.getInstance().lookup(this.confirmQuery)
              .confirmQuery(queryScheme);

      // boolean isConfirmed = false;
      //
      // UFDate confirmDate = ClientContext.getInstance().getBusiDate();

      // if (whereSql.indexOf(OnwayStatusQryEnum.confirm.name() + " = 'N'") < 0)
      // {
      // isConfirmed = true;
      // }
      // this.setConfirmDate(orderVOs, isConfirmed, confirmDate);

      return orderVOs;
    }

    return null;
  }

  // @Override
  // public Object[] queryByWhereSql(String whereSql) throws Exception {
  //
  // if (null != this.confirmQuery) {
  //
  // OrderVO[] orderVOs =
  // NCLocator.getInstance().lookup(this.confirmQuery)
  // .confirmQuery(whereSql);
  //
  // boolean isConfirmed = false;
  //
  // UFDate confirmDate = ClientContext.getInstance().getBusiDate();
  //
  // if (whereSql.indexOf(OnwayStatusQryEnum.confirm.name() + " = 'N'") < 0) {
  // isConfirmed = true;
  // }
  //
  // this.setConfirmDate(orderVOs, isConfirmed, confirmDate);
  //
  // return orderVOs;
  // }
  //
  // return null;
  // }

  public void setConfirmQuery(Class<? extends IConfirmQuery> confirmQuery) {
    this.confirmQuery = confirmQuery;
  }

  public void setModel(AbstractUIAppModel model) {
    this.model = model;
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
    // 对方确认状态维护
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
          .updateForConfirm(newVOs);
    }
    return null;
  }

  /**
   * 方法功能描述：设置确认日期
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @param isConfirmed
   * @param confirmDate
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-26 下午07:20:53
   */
  // private void setConfirmDate(OrderVO[] orderVOs, boolean isConfirmed,
  // UFDate confirmDate) {
  //
  // if (!isConfirmed) {
  // for (OrderVO orderVO : orderVOs) {
  // OrderItemVO[] bvos = orderVO.getBVO();
  // for (OrderItemVO bvo : bvos) {
  // bvo.setDconfirmdate(confirmDate);
  // }
  // }
  // }
  // }

}
