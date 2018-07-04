package nc.ui.pu.m23.model;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m23.maintain.IArriveMaintain;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uif2.LoginContext;

public class ArriveModelService implements IAppModelService {

  @Override
  public void delete(Object object) {
    ArriveVO bill = (ArriveVO) object;
    ArriveVO[] bills = new ArriveVO[] {
      bill
    };
    IArriveMaintain service =
        NCLocator.getInstance().lookup(IArriveMaintain.class);
    try {
      service.deleteArrive(bills, null);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  @Override
  public Object insert(Object object) {
    ArriveVO bill = (ArriveVO) object;
    ArriveVO[] ret = null;
    IArriveMaintain service =
        NCLocator.getInstance().lookup(IArriveMaintain.class);
    try {
      ret = service.saveBase(new ArriveVO[] {
        bill
      }, null);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return ret;
  }

  @Override
  public Object[] queryByDataVisibilitySetting(LoginContext context) {
    return null;
  }

  /*
   * public Object[] queryByWhereSql(String whereSql) {
   * ArriveVO[] rets = null;
   * IArriveMaintain service =
   * NCLocator.getInstance().lookup(IArriveMaintain.class);
   * try {
   * rets = service.queryArrive(whereSql);
   * }
   * catch (BusinessException e) {
   * ExceptionUtils.wrappException(e);
   * }
   * return rets;
   * }
   */

  @Override
  public Object update(Object object) {
    return null;
  }

}
