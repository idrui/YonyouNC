package nc.ui.pu.m20.billref.it.m5801;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m20.it.m5801.IQuery20For5801;
import nc.ui.pu.m20.query.PraybillQueryDLGInitializer;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * 进口合同参照请购单的查询服务
 * 
 * @since 6.3.1
 * @version 2013-7-3上午11:12:25
 * @author fanly3
 */
public class QueryServiceFor5801 extends PuRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    this.checkQueryCond(queryScheme);
    IQuery20For5801 queryService =
        NCLocator.getInstance().lookup(IQuery20For5801.class);
    PraybillVO[] bills = queryService.queryPrayBills(queryScheme);
    return bills;
  }

  @Override
  protected String getRefOrgFieldCode() {
    return PraybillQueryDLGInitializer.PK_PRAYBILL_B_PK_PURCHASEORG;
  }
}
