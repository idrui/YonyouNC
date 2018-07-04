/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-2 上午10:57:06
 */
package nc.impl.pu.m21;

import nc.itf.pu.m21.IOrderReplenishQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.rule.PUBillLazyQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>补货订单查询实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-2 上午10:57:06
 */
public class OrderReplenishQueryImpl implements IOrderReplenishQuery {

  @Override
  public OrderVO[] replenishQuery(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return this.lazyQuery(queryScheme);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  private OrderVO[] lazyQuery(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    // 临时拼上订单表头的EC扩展表
    qrySchemeProcessor.getTableAliasOfAttribute(OrderHeaderVO.VREASON);
    String where = queryScheme.getTableJoinFromWhereSQL().getWhere();
    if (StringUtils.isNotBlank(where)) {
      qrySchemeProcessor.appendWhere(" and ");
    }

    SqlBuilder sql = new SqlBuilder();
    sql.append(mainTableAlias + "." + OrderHeaderVO.DR, 0);
    sql.append(" and " + mainTableAlias + "." + OrderHeaderVO.BISLATEST,
        UFBoolean.TRUE);
    sql.append(" and " + mainTableAlias + "." + OrderHeaderVO.BISREPLENISH,
        UFBoolean.TRUE);

    qrySchemeProcessor.appendWhere(sql.toString());
    qrySchemeProcessor.appendCurrentGroup();

    return (OrderVO[]) new PUBillLazyQuery<OrderVO>(OrderVO.class,
        POBillType.Order).queryOrderByBillcode(queryScheme);
  }

}
