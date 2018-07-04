package nc.pubimpl.pu.m21.pu.m23;

import java.util.Map;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21.pub.QueryCondManager;
import nc.vo.pu.m21.pub.QueryUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QueryConstants;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import nc.pubitf.pu.m21.pu.m23.IOrderQueryFor23;

import nc.bs.pu.m21.query.pu.OrderQueryFor23BP;
import nc.bs.pu.m21.query.pu.OrderQueryFor23BackBP;

import nc.ui.querytemplate.querytree.IQueryScheme;

public class OrderQueryFor23Impl implements IOrderQueryFor23 {

  @Override
  public OrderVO[] queryFor23(IQueryScheme queryScheme)
      throws BusinessException {
    String sql = this.getQuerySql(queryScheme);
    try {
      return new OrderQueryFor23BP().query(sql, UFBoolean.FALSE);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] queryFor23(String condition, UFBoolean isLazy)
      throws BusinessException {
    try {
      return new OrderQueryFor23BP().query(condition, isLazy);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] queryFor23QuickArrive(String condition)
      throws BusinessException {
    String sql = this.getSqlFor23QuickArrive(condition);
    try {
      return new OrderQueryFor23BP().query(sql, UFBoolean.FALSE);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] queryFor23Return(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      String sql = this.getBackSql(queryScheme);
      OrderQueryFor23BackBP bp = new OrderQueryFor23BackBP();
      return bp.query(sql, UFBoolean.FALSE);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
      return null;
    }
  }

  private String getBackSql(IQueryScheme queryScheme) {
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
    String trantypeTableAlias = "po_potrantype";
    SqlBuilder fromPart = new SqlBuilder();
    fromPart.append(" inner join po_potrantype po_potrantype on ");
    fromPart
        .append(mainTableAlias + ".ctrantypeid = po_potrantype.ctrantypeid");
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
    partWhr.append(manager.getPubCondForBack23(trantypeTableAlias));
    // 如果是普通订单，基于原订单退货补货为否，则累计到货数量 >累计入库数量+累计退货数量
    partWhr.append(" and (");
    partWhr.append("(");
    partWhr.append(mainTableAlias + "." + OrderHeaderVO.BREFWHENRETURN,
        UFBoolean.FALSE);
    partWhr.append(" and " + bTableAlias + ".nnum > 0 and coalesce(");
    partWhr.append(bTableAlias + ".naccumarrvnum,0) - coalesce(" + bTableAlias
        + ".nbackarrvnum,0) - coalesce(" + bTableAlias
        + ".naccumstorenum,0) > 0 ");
    // 如果是普通订单，基于原订单退货补货为是，则累计到货数量 >累计入库数量
    partWhr.append(") or (");
    partWhr.append(mainTableAlias + "." + OrderHeaderVO.BREFWHENRETURN,
        UFBoolean.TRUE);
    partWhr.append(" and " + bTableAlias + ".nnum > 0 and coalesce(");
    partWhr.append(bTableAlias + ".naccumarrvnum,0) - coalesce(" + bTableAlias
        + ".naccumstorenum,0) > 0 ");
    partWhr.append(") or (");
    // 如果是退货订单，则订单数量的绝对值 > 累计退库数量 + 累计退货数量
    partWhr.append(bTableAlias + ".nnum < 0 and ");
    partWhr.append(bTableAlias + ".nnum  + coalesce(" + bTableAlias
        + ".nbackarrvnum,0) + coalesce(" + bTableAlias
        + ".nbackstorenum,0) < 0 ");
    partWhr.append(")) ");

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

  private String getQuerySql(IQueryScheme queryScheme) {
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
    String trantypeTableAlias = "po_potrantype";
    QueryUtil util = QueryUtil.getInstance();

    SqlBuilder fromPart = new SqlBuilder();
    fromPart.append(" inner join po_potrantype po_potrantype on ");
    fromPart
        .append(mainTableAlias + ".ctrantypeid = po_potrantype.ctrantypeid");
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
    partWhr.append(manager.getPubCondFor23(bTableAlias, bbTableAlias,
        trantypeTableAlias));
    partWhr.append(" and "
        + manager.getPubCondFor45_23(mainTableAlias, bTableAlias, bbTableAlias,
            marTableAlias));
    partWhr.append(manager.getPubCondFor45_23Pull(bbTableAlias));
    partWhr.append(" and " + mainTableAlias + "." + OrderHeaderVO.BRETURN,
        UFBoolean.FALSE);

    partWhr.append(" and " + bTableAlias + "." + OrderItemVO.NNUM, ">", 0);

    ConditionVO dplanarrvdateCond = util.getDplanArrvDateCond(queryScheme);
    if (haveBB1) {
      partWhr.append(" and ");
      partWhr.append(bTableAlias + ".breceiveplan='Y' ");
      partWhr.append(" and ");
      partWhr.append(bb1TableAlias + ".nnum > coalesce(" + bb1TableAlias
          + ".naccumarrvnum,0) ");
      String arrvorgsql = util.getArrOrg(qrySchemeProcessor, bb1TableAlias);
      if (arrvorgsql != null) {
        partWhr.append(arrvorgsql);
      }
      if (dplanarrvdateCond != null) {
        dplanarrvdateCond.setFieldCode(bb1TableAlias + "."
            + OrderItemVO.DPLANARRVDATE);
        String plandate = dplanarrvdateCond.getWhereSQL(new ConditionVO[] {
          dplanarrvdateCond
        });
        partWhr.append(" and " + plandate);
      }
    }
    else {
      partWhr.append(" and (");
      partWhr.append(" (" + trantypeTableAlias + ".breceiveplan= 'N' and ");
      partWhr.append(bTableAlias + ".nnum > coalesce(" + bTableAlias
          + ".naccumarrvnum,0) ");
      String arrvorgsql = util.getArrOrg(qrySchemeProcessor, bTableAlias);
      if (arrvorgsql != null) {
        partWhr.append(arrvorgsql);
      }
      if (dplanarrvdateCond != null) {
        dplanarrvdateCond.setFieldCode(bTableAlias + "."
            + OrderItemVO.DPLANARRVDATE);
        String plandate = dplanarrvdateCond.getWhereSQL(new ConditionVO[] {
          dplanarrvdateCond
        });
        partWhr.append(" and " + plandate);
      }

      partWhr.append(" )or ");
      partWhr.append(" (" + trantypeTableAlias + ".breceiveplan= 'Y' and "
          + bTableAlias + ".breceiveplan = 'Y' and " + bb1TableAlias
          + ".nnum > coalesce(" + bb1TableAlias + ".naccumarrvnum,0) ");
      arrvorgsql = util.getArrOrg(qrySchemeProcessor, bb1TableAlias);
      if (arrvorgsql != null) {
        partWhr.append(arrvorgsql);
      }
      if (dplanarrvdateCond != null) {
        dplanarrvdateCond.setFieldCode(bb1TableAlias + "."
            + OrderItemVO.DPLANARRVDATE);
        String plandate = dplanarrvdateCond.getWhereSQL(new ConditionVO[] {
          dplanarrvdateCond
        });
        partWhr.append(" and " + plandate);
      }
      partWhr.append(" ) )");
    }

    // QueryUtil qutils = QueryUtil.getInstance();
    // qutils.appendReplaceCond(qrySchemeProcessor);
    // // 替换
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

    String finalSql = util.getReplacedCond(wholeSql.toString(), bTableAlias);
    if (dplanarrvdateCond != null) {
      finalSql = util.getReplacedCondByPlanDate(finalSql, bTableAlias);
    }
    return finalSql;
  }

  private String getSqlFor23QuickArrive(String condition) {
    SqlBuilder wholeSql = new SqlBuilder();
    wholeSql.append(" select po_order." + OrderHeaderVO.PK_ORDER + ",");
    wholeSql.append(" po_order_b." + OrderItemVO.PK_ORDER_B + ",");
    wholeSql.append(" po_order_bb1." + OrderReceivePlanVO.PK_ORDER_BB1 + ",");
    wholeSql.append(" po_order_bb." + StatusOnWayItemVO.PK_ORDER_BB);
    // 关联子表
    wholeSql.append(" from po_order left join po_order_b ");
    wholeSql.append(" on po_order.pk_order=po_order_b.pk_order");
    // 关联子子表
    wholeSql.append(" left join po_order_bb ");
    wholeSql.append(" on po_order_b.pk_order_b=po_order_bb.pk_order_b");
    // 关联子子表
    wholeSql.append(" left join po_order_bb1 ");
    wholeSql.append(" on po_order_b.pk_order_b=po_order_bb1.pk_order_b");
    // 关联交易类型
    wholeSql.append(" inner join po_potrantype ");
    wholeSql.append(" on po_order.vtrantypecode=po_potrantype.vtrantypecode");
    // 关联物料
    wholeSql.append(" inner join bd_material");
    wholeSql.append(" on po_order_b.pk_material=bd_material.pk_material");

    QueryCondManager manager = QueryCondManager.getInstance();
    wholeSql.append(" and ");
    wholeSql.append(manager.getPubCondFor23("po_order_b", "po_order_bb",
        "po_potrantype"));
    wholeSql.append(" and "
        + manager.getPubCondFor45_23("po_order", "po_order_b", "po_order_bb",
            "bd_material"));
    wholeSql.append(manager.getPubCondFor45_23Pull("po_order_bb"));
    wholeSql.append(" and po_order." + OrderHeaderVO.BRETURN, UFBoolean.FALSE);

    wholeSql.append(" and po_order_b." + OrderItemVO.NNUM, ">", 0);
    wholeSql.append(" and (");
    wholeSql.append(" (po_potrantype.breceiveplan= 'N' and ");
    wholeSql.append("po_order_b.nnum > coalesce(po_order_b.naccumarrvnum,0)) ");
    wholeSql.append(" or ");
    wholeSql
        .append(" (po_potrantype.breceiveplan= 'Y' and po_order_b.breceiveplan = 'Y' and po_order_bb1.nnum > coalesce(po_order_bb1.naccumarrvnum,0)) ");
    wholeSql.append(")");
    wholeSql.append(condition);
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
