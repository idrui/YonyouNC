package nc.ui.pu.m20.billref.pu.m21;

import nc.bs.framework.common.NCLocator;
import nc.ui.pu.m20.query.PraybillQueryDLGInitializer;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单参照请购单的查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-27 下午09:00:33
 */
public class QueryServiceFor21 extends PuRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    this.checkQueryCond(queryScheme);
    nc.pubitf.pu.m20.pu.m21.IQuery20For21 queryService =
        NCLocator.getInstance().lookup(
            nc.pubitf.pu.m20.pu.m21.IQuery20For21.class);
    PraybillVO[] bills = queryService.queryPrayBills(queryScheme);
    return bills;
  }

  @Override
  protected String getRefOrgFieldCode() {
    return PraybillQueryDLGInitializer.PK_PRAYBILL_B_PK_PURCHASEORG;
  }

}
