package nc.ui.pu.m20.billref.pu.m28;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m20.pu.m28.IQuery20For28;
import nc.ui.pu.m20.query.PraybillQueryDLGInitializer;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格审批单参照请购单的查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-8 下午01:48:33
 */
public class QueryServiceFor28 extends PuRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    this.checkQueryCond(queryScheme);
    IQuery20For28 queryService =
        NCLocator.getInstance().lookup(IQuery20For28.class);
    PraybillVO[] bills = queryService.queryPrayBills(queryScheme);
    return bills;
  }

  @Override
  protected String getRefOrgFieldCode() {
    return PraybillQueryDLGInitializer.PK_PRAYBILL_B_PK_PURCHASEORG;
  }

}
