/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-20 下午04:39:07
 */
package nc.impl.pu.m422x.action;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.rule.PUBillLazyQuery;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-20 下午04:39:07
 */
public class StoreReqAppQueryAction {

  public StoreReqAppVO[] maintainQuery(IQueryScheme queryScheme) {
    return this.lazyQuery(queryScheme);
  }

  private StoreReqAppVO[] lazyQuery(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String where = queryScheme.getTableJoinFromWhereSQL().getWhere();
    if (StringUtils.isNotBlank(where)) {
      qrySchemeProcessor.appendWhere(" and ");
    }
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    qrySchemeProcessor.appendWhere(mainTableAlias + ".dr = 0 ");
    qrySchemeProcessor.appendCurrentGroup();
    qrySchemeProcessor.appendFuncPermissionOrgSql();
    return (StoreReqAppVO[]) new PUBillLazyQuery<StoreReqAppVO>(
        StoreReqAppVO.class, POBillType.MRBill)
        .queryOrderByBillcode(queryScheme);
  }
}
