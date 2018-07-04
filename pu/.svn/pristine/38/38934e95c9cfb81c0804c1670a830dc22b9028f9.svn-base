/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-8 下午02:56:48
 */
package nc.ui.pu.m21.service;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单关闭查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-8 下午02:56:48
 */
public class OrderCloseService implements IQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    return NCLocator.getInstance().lookup(nc.itf.pu.m21.IOrderQuery.class)
        .closeQuery(queryScheme);
  }

}
