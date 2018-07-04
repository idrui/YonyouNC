/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 下午09:14:39
 */
package nc.impl.pu.m422x;

import nc.impl.pu.m422x.action.StoreReqAppQueryAction;
import nc.itf.pu.m422x.IStoreReqAppQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

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
 * @author wuxla
 * @time 2010-7-19 下午09:14:39
 */
public class StoreReqAppQueryImpl implements IStoreReqAppQuery {

  @Override
  public StoreReqAppVO[] maintainQuery(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return new StoreReqAppQueryAction().maintainQuery(queryScheme);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
