package nc.pubimpl.pu.m422x.ic.m4d;

import nc.bs.pu.m422x.query.QueryFor4dBP;
import nc.pubitf.pu.m422x.ic.m4d.IQuery422xFor4d;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请提供给材料出库单的转单查询实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-15 上午11:40:25
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
