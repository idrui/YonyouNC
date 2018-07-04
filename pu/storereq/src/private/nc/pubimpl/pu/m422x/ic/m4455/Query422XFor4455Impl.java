package nc.pubimpl.pu.m422x.ic.m4455;

import nc.bs.pu.m422x.query.QueryFor4455BP;
import nc.pubitf.pu.m422x.ic.m4455.IQuery422XFor4455;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 物资需求申请提供给出库申请单的转单查询实现类
 * 
 * @since 6.0
 * @version 2010-12-16 下午01:26:26
 * @author wuxla
 */

public class Query422XFor4455Impl implements IQuery422XFor4455 {

  @Override
  public StoreReqAppVO[] queryStoreReqAppsFor4455(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return new QueryFor4455BP(queryScheme).queryStoreReqVOs();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
