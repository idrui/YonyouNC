package nc.pubimpl.pu.m21.dm.m4804;

import java.util.HashMap;
import java.util.Map;

import nc.bs.pu.m21.query.dm.OrderQueryFor4804BP;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.pu.m21.dm.m4804.IOrderQueryFor4804;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QueryConstants;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2010-12-13 下午05:55:05
 * @author wuxla
 */

public class OrderQueryFor4804Impl implements IOrderQueryFor4804 {

  @Override
  public OrderViewVO[] queryFor4804(IQueryScheme queryScheme)
      throws BusinessException {
    Object busiObj = queryScheme.get("cbiztypeid");
    if (null == busiObj) {
      return null;
    }
    String sql = this.createSql(queryScheme, busiObj);
    try {
      return new OrderQueryFor4804BP().queryFor4804(sql, UFBoolean.FALSE);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
      return null;
    }
  }

  @Override
  public Map<String, String> queryPlanCodeByBBidsFor4804(String[] bbids)
      throws BusinessException {
    if (ArrayUtils.isEmpty(bbids)) {
      return null;
    }

    try {
      VOQuery<OrderReceivePlanVO> query =
          new VOQuery<OrderReceivePlanVO>(OrderReceivePlanVO.class,
              new String[] {
                OrderReceivePlanVO.PK_ORDER_BB1, OrderReceivePlanVO.VBILLCODE
              });
      OrderReceivePlanVO[] vos = query.query(bbids);
      if (ArrayUtils.isEmpty(vos)) {
        return null;
      }

      Map<String, String> map = new HashMap<String, String>();
      for (OrderReceivePlanVO vo : vos) {
        map.put(vo.getPk_order_bb1(), vo.getVbillcode());
      }
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private String createSql(IQueryScheme queryScheme, Object busiObj) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    String bTableAlias =
        qrySchemeProcessor.getTableAliasOfAttribute("pk_order_b.pk_org");
    String bbTableAlias =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_order_b.pk_order_bb.pk_org");
    String marTableAlias =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_order_b.pk_material.pk_org");

    SqlBuilder fromPart = new SqlBuilder();
    fromPart.append(" inner join po_potrantype po_potrantype on ");
    fromPart.append(mainTableAlias
        + ".ctrantypeid = po_potrantype.ctrantypeid ");// id 关联确定唯一性
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
    String[] busis = (String[]) busiObj;
    partWhr.append(" and " + mainTableAlias + "." + OrderHeaderVO.PK_BUSITYPE,
        busis);
    partWhr.append(" and " + mainTableAlias + "." + OrderHeaderVO.BISLATEST,
        UFBoolean.TRUE);
    partWhr.append(" and " + mainTableAlias + "." + OrderHeaderVO.FORDERSTATUS,
        POEnumBillStatus.APPROVE.toInt());
    partWhr.append(" and " + mainTableAlias + "." + OrderHeaderVO.BFROZEN,
        UFBoolean.FALSE);
    partWhr.append(" and " + bTableAlias + "." + OrderItemVO.FISACTIVE, 0);
    partWhr.append(" and " + marTableAlias + "." + MaterialVO.FEE,
        UFBoolean.FALSE);
    partWhr.append(" and " + marTableAlias + "." + MaterialVO.DISCOUNTFLAG,
        UFBoolean.FALSE);
    partWhr.append(" and " + bTableAlias + "." + OrderItemVO.NNUM, ">", 0);
    partWhr.append(" and " + bbTableAlias + "." + StatusOnWayItemVO.NONWAYNUM,
        ">", 0);

    partWhr.append(" and ( ");
    partWhr.append(" po_potrantype.breceiveplan='N' ");
    partWhr.append(" or po_potrantype.breceiveplan='Y' and " + bTableAlias
        + ".breceiveplan='Y' ");
    partWhr.append(" ) ");

    if (haveBB1) {
      partWhr.append(" and ");
      partWhr.append(bTableAlias + ".breceiveplan='Y' ");
      partWhr.append(" and ");
      partWhr.append(bb1TableAlias + ".nnum > coalesce(" + bb1TableAlias
          + ".naccumdevnum,0) ");
    }
    else {
      partWhr.append(" and ");
      partWhr.append(bTableAlias + ".btransclosed = 'N' ");
      partWhr.append(" and (");
      partWhr.append("( ");
      partWhr.append("po_potrantype.breceiveplan= 'N' and ");
      partWhr.append(bTableAlias + ".nnum > coalesce(" + bTableAlias
          + ".naccumdevnum,0) and " + bTableAlias + ".btransclosed = 'N'  ");
      partWhr.append(" )");
      partWhr.append(" or ");
      partWhr.append("( ");
      partWhr.append("po_potrantype.breceiveplan= 'Y' and ");
      partWhr.append(bTableAlias + ".breceiveplan = 'Y' and " + bb1TableAlias
          + ".nnum > coalesce(" + bb1TableAlias + ".naccumdevnum,0) and "
          + bb1TableAlias + ".btransclosed = 'N' ) ");
      partWhr.append(")");
    }
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
