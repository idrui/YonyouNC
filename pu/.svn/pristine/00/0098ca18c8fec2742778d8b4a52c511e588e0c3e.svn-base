/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-29 上午11:07:37
 */
package nc.impl.pu.m21;

import nc.bs.pu.m21.query.OrderQueryBP;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.pu.m21.IOutputQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>输出查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-6-29 上午11:07:37
 */

// 获取相应在途状态的采购订单在途数量
public class OutputQueryImpl implements IOutputQuery {

  @Override
  public OrderVO[] outputQuery(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      DataAccessUtils utils = new DataAccessUtils();

      IRowSet rowset = utils.query(this.createSql(queryScheme));

      OrderVO[] retVOs =
          new OrderQueryBP().query(rowset.toOneDimensionStringArray(),
              UFBoolean.FALSE);

      return retVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IOutputQuery#outputQuery(java.lang.String)
   */
  @Override
  public OrderVO[] outputQuery(String sqlWhere) throws BusinessException {
    if (sqlWhere == null) {
      return new OrderVO[0];
    }

    try {
      DataAccessUtils utils = new DataAccessUtils();

      IRowSet rowset = utils.query(sqlWhere);

      OrderVO[] retVOs =
          new OrderQueryBP().query(rowset.toOneDimensionStringArray(),
              UFBoolean.FALSE);

      return retVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private String createSql(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    String itemTableAlias =
        qrySchemeProcessor.getTableAliasOfAttribute("pk_order_b.pk_org");
    String bbTableAlias =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_order_b.pk_order_bb.pk_org");
    String where = queryScheme.getTableJoinFromWhereSQL().getWhere();
    if (StringUtils.isNotBlank(where)) {
      qrySchemeProcessor.appendWhere(" and ");
    }

    SqlBuilder sql = new SqlBuilder();
    sql.append(bbTableAlias + ".dr", 0);
    sql.append(" and " + bbTableAlias + "." + StatusOnWayItemVO.FONWAYSTATUS,
        (Integer) OnwayStatus.STATUS_OUTPUT.value());
    sql.append(" and " + mainTableAlias + "." + OrderHeaderVO.BISLATEST,
        UFBoolean.TRUE);
    sql.append(" and " + itemTableAlias + "." + OrderItemVO.FISACTIVE,
        (Integer) EnumActive.ACTIVE.value());

    QueryCondition outputCond = qrySchemeProcessor.getQueryCondition("output");
    Object[] values = outputCond.getValues();
    UFBoolean output = UFBoolean.valueOf((String) values[0]);
    sql.append(" and ");
    if (output.booleanValue()) {
      sql.append(bbTableAlias + "." + StatusOnWayItemVO.ISOPERATED,
          UFBoolean.TRUE);
    }
    else {
      sql.append(bbTableAlias + "." + StatusOnWayItemVO.ISOPERATED,
          UFBoolean.FALSE);
    }

    qrySchemeProcessor.appendWhere(sql.toString());
    qrySchemeProcessor.appendCurrentGroup();

    sql = new SqlBuilder();
    sql.append("select distinct " + mainTableAlias + ".pk_order ");
    sql.append(qrySchemeProcessor.getFinalFromWhere());
    return sql.toString();
  }

}
