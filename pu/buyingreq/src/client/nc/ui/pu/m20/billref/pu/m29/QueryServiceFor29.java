package nc.ui.pu.m20.billref.pu.m29;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m20.pu.m29.IQuery20For29;
import nc.ui.pu.m20.query.PraybillQueryDLGInitializer;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ѯ���۵������빺���Ĳ�ѯ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-8 ����01:55:43
 */
public class QueryServiceFor29 extends PuRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    this.checkQueryCond(queryScheme);
    IQuery20For29 queryService =
        NCLocator.getInstance().lookup(IQuery20For29.class);
    PraybillVO[] bills = queryService.queryPrayBills(queryScheme);
    return bills;
  }

  @Override
  protected String getRefOrgFieldCode() {
    return PraybillQueryDLGInitializer.PK_PRAYBILL_B_PK_PURCHASEORG;
  }

}
