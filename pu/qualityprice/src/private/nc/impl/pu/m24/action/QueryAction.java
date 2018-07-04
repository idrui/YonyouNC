/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-13 下午07:44:10
 */
package nc.impl.pu.m24.action;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.rule.PUBillLazyQuery;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-13 下午07:44:10
 */
public class QueryAction {
  /**
   * 查询价格结算单
   * 
   * @param queryScheme
   * @return
   */
  public PricestlVO[] query(IQueryScheme queryScheme) {
    return this.lazyQuery(queryScheme);
  }

  private PricestlVO[] lazyQuery(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    String where = queryScheme.getTableJoinFromWhereSQL().getWhere();
    if (StringUtils.isNotBlank(where)) {
      qrySchemeProcessor.appendWhere(" and ");
    }
    qrySchemeProcessor.appendWhere(mainTableAlias + ".dr = 0 ");
    qrySchemeProcessor.appendCurrentGroup();
    qrySchemeProcessor.appendFuncPermissionOrgSql();
    return (PricestlVO[]) new PUBillLazyQuery<PricestlVO>(PricestlVO.class,
        POBillType.PriceStl).queryOrderByBillcode(queryScheme);
  }
}
