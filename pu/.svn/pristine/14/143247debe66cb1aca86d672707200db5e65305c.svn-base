/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-13 上午11:27:31
 */
package nc.pubimpl.pu.m21.pu.m4t;

import java.util.Map;

import nc.bs.pu.m21.query.pu.OrderQueryFor4TBP;
import nc.pubitf.pu.m21.pu.m4t.IOrderQueryFor4T;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21.pub.QueryCondManager;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QueryConstants;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单参照采购订单查询服务实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-13 上午11:27:31
 */
public class OrderQueryFor4TImpl implements IOrderQueryFor4T {

  @Override
  public OrderVO[] queryFor4t(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      String sql = this.createSql(queryScheme);
      return new OrderQueryFor4TBP().queryFor4t(sql, UFBoolean.FALSE);
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
    String bTableAlias =
        qrySchemeProcessor.getTableAliasOfAttribute("pk_order_b.pk_org");
    // String bb1TableAlias =
    // qrySchemeProcessor
    // .getTableAliasOfAttribute("pk_order_b.pk_order_bb1.pk_org");
    String bbTableAlias =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_order_b.pk_order_bb.pk_org");
    String marTableAlias =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_order_b.pk_material.pk_org");

    SqlBuilder fromPart = new SqlBuilder();
    boolean haveBB1 = this.haveBB1(queryScheme);
    String bb1TableAlias = null;

    if (haveBB1) {
      bb1TableAlias =
          qrySchemeProcessor
              .getTableAliasOfAttribute("pk_order_b.pk_order_bb1.pk_org");
    }
    else {
      fromPart.append(" left join po_order_bb1 po_order_bb1 on ");
      fromPart.append(bTableAlias + ".pk_order_b = po_order_bb1.pk_order_b ");
      bb1TableAlias = "po_order_bb1";
    }
    qrySchemeProcessor.appendFrom(fromPart.toString());

    SqlBuilder partWhr = new SqlBuilder();
    QueryCondManager manager = QueryCondManager.getInstance();
    partWhr.append(" and ");
    partWhr.append(manager.getPubCondFor45_23(mainTableAlias, bTableAlias,
        bbTableAlias, marTableAlias));
    partWhr.append(" and ");
    partWhr.append(manager.getPubCondFor45_4T(bTableAlias, bbTableAlias));
    partWhr.append(" and ");
    partWhr.append(manager.getPubCondFor4T(bTableAlias));
    // 如果是普通订单，则主数量 > 累计入库数量
    partWhr.append(" and (");
    partWhr.append("(");
    partWhr.append(bTableAlias + ".nnum > 0 and ");
    partWhr.append(bTableAlias + ".nnum > coalesce( " + bTableAlias
        + ".naccumstorenum,0) ");
    // 如果是退货订单，则订单数量的绝对值 > 累计入库数量
    partWhr.append(") or (");
    partWhr.append(bTableAlias + ".nnum < 0 and ");
    partWhr.append(bTableAlias + ".nnum  < coalesce( " + bTableAlias
        + ".naccumstorenum,0) ");
    partWhr.append(")) ");

    // 替换
    // String replacedCond =
    // qutils.getReplacedCond(wholeFromPart + " where " + wherePart, poOrderB,
    // poOrderBB1);
    qrySchemeProcessor.appendWhere(partWhr.toString());

    SqlBuilder wholeSql = new SqlBuilder();
    wholeSql.append("select " + mainTableAlias + "." + OrderHeaderVO.PK_ORDER
        + ",");
    wholeSql.append(bTableAlias + "." + OrderItemVO.PK_ORDER_B + ",");
    wholeSql
        .append(bb1TableAlias + "." + OrderReceivePlanVO.PK_ORDER_BB1 + ",");
    wholeSql.append(bbTableAlias + "." + StatusOnWayItemVO.PK_ORDER_BB);
    wholeSql.append(qrySchemeProcessor.getFinalFromWhere());
    return wholeSql.toString();

  }

  @SuppressWarnings("unchecked")
  private boolean haveBB1(IQueryScheme queryScheme) {
    Map<String, QueryCondition> logicalConditionMap =
        (Map<String, QueryCondition>) queryScheme
            .get(QueryConstants.QUERY_CONDITION);

    for (String key : logicalConditionMap.keySet()) {
      if (key.indexOf("pk_order_bb1") > 0) {
        return true;
      }
    }

    return false;
  }

}
