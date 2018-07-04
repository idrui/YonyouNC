package nc.pubimpl.pu.m422x.ic.m4d;

import nc.bs.pu.m422x.query.QueryFor4dBP;
import nc.pubitf.pu.m422x.ic.m4d.IQuery422xFor4d;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������������ṩ�����ϳ��ⵥ��ת����ѯʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-15 ����11:40:25
 */
public class Query422xFor4dImpl implements IQuery422xFor4d {

  @Override
  public StoreReqAppVO[] queryStoreReqApps(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return new QueryFor4dBP(queryScheme).queryStoreReqVOs();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
