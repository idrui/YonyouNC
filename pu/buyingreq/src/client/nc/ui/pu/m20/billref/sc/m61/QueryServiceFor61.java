package nc.ui.pu.m20.billref.sc.m61;

import nc.bs.framework.common.NCLocator;
import nc.ui.pu.m20.query.PraybillQueryDLGInitializer;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ί�ⶩ�������빺���Ĳ�ѯ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-20 ����09:00:33
 */
public class QueryServiceFor61 extends PuRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    this.checkQueryCond(queryScheme);
    nc.pubitf.pu.m20.sc.m61.IQuery20For61 queryService =
        NCLocator.getInstance().lookup(
            nc.pubitf.pu.m20.sc.m61.IQuery20For61.class);
    PraybillVO[] bills = queryService.queryPrayBills(queryScheme);
    return bills;
  }

  @Override
  protected String getRefOrgFieldCode() {
    return PraybillQueryDLGInitializer.PK_PRAYBILL_B_PK_PURCHASEORG;
  }

}
