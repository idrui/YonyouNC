package nc.ui.pu.m21.service;

import nc.bs.framework.common.NCLocator;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.uif2.LoginContext;

public class OrderModelService implements IAppModelService {

  @Override
  public void delete(Object object) throws Exception {
    // ֱ����Action�н���ɾ���������˴������д���
  }

  @Override
  public Object insert(Object object) throws Exception {
    // ֱ����Action�н��в���������˴������д���
    return null;
  }

  @Override
  public Object[] queryByDataVisibilitySetting(LoginContext context)
      throws Exception {
    return null;
  }

  // @Override
  // public Object[] queryByWhereSql(String whereSql) throws Exception {
  // nc.itf.pu.m21.IOrderQuery queryService =
  // NCLocator.getInstance().lookup(nc.itf.pu.m21.IOrderQuery.class);
  // return queryService.maintainQuery(whereSql, UFBoolean.FALSE);
  // }

  @Override
  public Object update(Object object) throws Exception {
    // ֱ����Action�н��и��²������˴������д���
    return null;
  }

}
