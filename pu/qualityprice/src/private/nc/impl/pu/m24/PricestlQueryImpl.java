/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-13 ����07:43:17
 */
package nc.impl.pu.m24;

import nc.impl.pu.m24.action.QueryAction;
import nc.itf.pu.m24.IPricestlQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥��ѯʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-13 ����07:43:17
 */
public class PricestlQueryImpl implements IPricestlQuery {

  @Override
  public PricestlVO[] queryPrayBills(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return new QueryAction().query(queryScheme);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
