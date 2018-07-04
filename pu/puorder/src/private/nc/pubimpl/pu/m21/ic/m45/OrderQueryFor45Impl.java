package nc.pubimpl.pu.m21.ic.m45;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21.pub.QueryCondManager;
import nc.vo.pu.m21.pub.QueryUtil;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QueryConstants;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.util.MetaUtils;
import nc.vo.scmpub.res.billtype.POBillType;

import nc.pubitf.pu.m21.ic.m45.IOrderQueryFor45;

import nc.bs.pu.m21.query.ic.OrderQueryFor45BackBP;
import nc.bs.pu.m21.query.ic.OrderQueryFor45PullBP;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;

import nc.ui.querytemplate.querytree.FromWhereSQL;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.querytree.QueryScheme;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单为采购入库单提供的查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-14 上午11:11:33
 */
public class OrderQueryFor45Impl implements IOrderQueryFor45 {

  @Override
  public OrderVO[] queryFor45(String[] orderHIDs) throws BusinessException {
    try {
      if (ArrayUtils.isEmpty(orderHIDs)) {
        return null;
      }
      String sql = this.getQuerySql(this.createQueryScheme(orderHIDs));
      return new OrderQueryFor45PullBP().query(sql, UFBoolean.FALSE);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] queryFor45Pull(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      String sql = this.getQuerySql(queryScheme);
      return new OrderQueryFor45PullBP().query(sql, UFBoolean.FALSE);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] queryFor45Push(IQueryScheme queryScheme)
      throws BusinessException {
    return null;
  }

  @Override
  public OrderVO[] queryFor45Return(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      String sql = this.getBackSql(queryScheme);
      OrderQueryFor45BackBP bp = new OrderQueryFor45BackBP();
      return bp.query(sql, UFBoolean.FALSE);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public Map<String, UFBoolean> queryReserveInfoFor45(String[] bids)
      throws BusinessException {
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    if (ArrayUtils.isEmpty(bids)) {
      return map;
    }
    try {
      VOQuery<OrderItemVO> query =
          new VOQuery<OrderItemVO>(OrderItemVO.class, new String[] {
            OrderItemVO.PK_ORDER_B, OrderItemVO.NSUPRSNUM
          });
      OrderItemVO[] itemVOs = query.query(bids);
      if (ArrayUtils.isEmpty(itemVOs)) {
        return map;
      }
      for (OrderItemVO itemVO : itemVOs) {
        if (MathTool.compareTo(itemVO.getNsuprsnum(), UFDouble.ZERO_DBL) > 0) {
          map.put(itemVO.getPk_order_b(), UFBoolean.TRUE);
        }
        else {
          map.put(itemVO.getPk_order_b(), UFBoolean.FALSE);
        }
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return map;
  }

  private QueryScheme createQueryScheme(String[] orderHIDs)
      throws BusinessException {
    SqlBuilder from = new SqlBuilder();
    from.append(PUEntity.M21_H_TABLE + " " + PUEntity.M21_H_TABLE);
    SqlBuilder where = new SqlBuilder();
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_44.name());
    where.append(builder.buildSQL(PUEntity.M21_H_TABLE + "."
        + OrderHeaderVO.PK_ORDER, orderHIDs));

    FromWhereSQLImpl fwsql =
        new FromWhereSQLImpl(from.toString(), where.toString());
    Map<String, String> attAlsMap = new HashMap<String, String>();
    attAlsMap.put(FromWhereSQL.DEFAULT_ATTRPATH, PUEntity.M21_H_TABLE);
    fwsql.setAttrpath_alias_map(attAlsMap);
    String beanid =
        MetaUtils.getBusinessEntityByBillType(POBillType.Order.getCode())
            .getID();
    QueryScheme qs = new QueryScheme();
    qs.putTableJoinFromWhereSQL(fwsql);
    qs.put(QueryConstants.BEAN_ID, beanid);
    qs.put(QueryConstants.QUERY_CONDITION,
        new HashMap<String, QueryCondition>());
    return qs;
  }

  private String getBackSql(IQueryScheme queryScheme) {
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
    // String trantypeTableAlias = "po_potrantype";
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
    // 退库不检查到货计划
    // 退库不检查订单在途状态
    // 如果是普通订单，基于原订单退货补货为N，则累计入库数量 > 累计退库数量
    partWhr.append(" and (");
    partWhr.append("(");
    partWhr.append(mainTableAlias + "." + OrderHeaderVO.BREFWHENRETURN,
        UFBoolean.FALSE);
    partWhr.append(" and " + bTableAlias + ".nnum > 0 and coalesce(");
    partWhr.append(bTableAlias + ".naccumstorenum,0) > coalesce(" + bTableAlias
        + ".nbackstorenum,0)");
    // 如果是普通订单，基于原订单退货补货为Y，则累计入库数量 > 0
    partWhr.append(") or (");
    partWhr.append(mainTableAlias + "." + OrderHeaderVO.BREFWHENRETURN,
        UFBoolean.TRUE);
    partWhr.append(" and " + bTableAlias + ".nnum > 0 and coalesce(");
    partWhr.append(bTableAlias + ".naccumstorenum,0) > 0");
    // 如果是退货订单，则订单数量的绝对值 > 累计退库数量 + 累计退货数量
    partWhr.append(") or (");
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
    String bbTableAlias =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_order_b.pk_order_bb.pk_org");
    String marTableAlias =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_order_b.pk_material.pk_org");
    String trantypeTableAlias = "po_potrantype";
    SqlBuilder fromPart = new SqlBuilder();
    fromPart.append(" inner join po_potrantype " + trantypeTableAlias + " on ");
    fromPart.append(mainTableAlias + ".ctrantypeid = " + trantypeTableAlias
        + ".ctrantypeid ");
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
    partWhr.append(manager.getPubCondFor45_23Pull(bbTableAlias));
    partWhr.append(" and "
        + manager.getPubCondFor45_4T(bTableAlias, bbTableAlias));

    partWhr.append(" and " + bTableAlias + "." + OrderItemVO.NNUM, ">", 0);

    QueryUtil util = QueryUtil.getInstance();
    if (haveBB1) {
      partWhr.append(" and ");
      partWhr.append(bTableAlias + ".breceiveplan='Y' ");
      partWhr.append(" and ");
      partWhr.append(bb1TableAlias + ".nnum > coalesce(" + bb1TableAlias
          + ".naccumstorenum,0) ");
      String arrvorgsql = util.getArrOrg(qrySchemeProcessor, bb1TableAlias);
      if (arrvorgsql != null) {
        partWhr.append(arrvorgsql);
      }
    }
    else {
      partWhr.append(" and (");
      partWhr.append(" ( " + trantypeTableAlias + ".breceiveplan= 'N' and "
          + bTableAlias + ".breceiveplan= 'N' and " + bTableAlias
          + ".nnum > coalesce(" + bTableAlias + ".naccumstorenum,0) ");
      String arrvorgsql = util.getArrOrg(qrySchemeProcessor, bTableAlias);
      if (arrvorgsql != null) {
        partWhr.append(arrvorgsql);
      }
      partWhr.append(" )or ");
      partWhr.append(" ( " + trantypeTableAlias + ".breceiveplan= 'Y' and "
          + bTableAlias + ".breceiveplan='Y' and " + bb1TableAlias
          + ".nnum > coalesce(" + bb1TableAlias + ".naccumstorenum,0) ");
      arrvorgsql = util.getArrOrg(qrySchemeProcessor, bb1TableAlias);
      if (arrvorgsql != null) {
        partWhr.append(arrvorgsql);
      }
      partWhr.append(" ) )");
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

    String finalSql = util.getReplacedCond(wholeSql.toString(), bTableAlias);
    return finalSql;
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
