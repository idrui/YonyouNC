/**
 * $�ļ�˵��$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-8 ����02:56:48
 */
package nc.ui.pu.m21.service;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.uif2app.query2.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ������رղ�ѯ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-8 ����02:56:48
 */
public class OrderCloseService implements IQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    return NCLocator.getInstance().lookup(nc.itf.pu.m21.IOrderQuery.class)
        .closeQuery(queryScheme);
  }

}
