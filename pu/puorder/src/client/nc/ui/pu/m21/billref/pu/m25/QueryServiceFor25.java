package nc.ui.pu.m21.billref.pu.m25;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.pu.m25.IOrderQueryFor25;
import nc.ui.pu.m21.view.OrderQueryDLGInitializer;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���Ʊ���ղɹ������Ĳ�ѯ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-27 ����09:00:33
 */
public class QueryServiceFor25 extends PuRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    this.checkQueryCond(queryScheme);
    IOrderQueryFor25 queryService =
        NCLocator.getInstance().lookup(IOrderQueryFor25.class);
    return queryService.queryFor25(queryScheme);
  }

  @Override
  protected String getRefOrgFieldCode() {
    return OrderQueryDLGInitializer.PK_ORDER_B_PK_PSFINANCEORG;
  }

}
