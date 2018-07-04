/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-19 上午10:26:00
 */
package nc.ui.pu.m4t.billref.pu.m25;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m4t.pu.m25.IInitialEstInvoiceQuery;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票参照期初暂估单的查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-19 上午10:26:00
 */
public class QueryServiceFor25 extends PuRefQueryService {

  @Override
  public Object[] queryByQueryScheme(IQueryScheme queryScheme) throws Exception {
    this.checkQueryCond(queryScheme);
    IInitialEstInvoiceQuery service =
        NCLocator.getInstance().lookup(IInitialEstInvoiceQuery.class);
    return service.query(queryScheme);
  }

  @Override
  protected String getRefOrgFieldCode() {
    return InitialEstHeaderVO.PK_ORG;
  }

}
