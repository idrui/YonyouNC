package nc.bs.pu.m21.query.pu;

import java.util.HashMap;
import java.util.Map;

import nc.bs.scmpub.util.SCMDataAccessUtils;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.jdbc.framework.util.DBConsts;
import nc.ui.querytemplate.querytree.FromWhereSQL;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QueryConstants;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.util.MetaUtils;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.util.StringUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为出口合同提供的查询服务BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.0
 * @author zhangyhh
 * @time 2013-10-11 下午03:48:50
 */
public class OrderQueryForETBP {

  /**
   * 拣配查询
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  public OrderVO[] queryForet(IQueryScheme queryScheme)
      throws BusinessException {
    String sql = this.createSql(queryScheme);
    //DataAccessUtils util = new DataAccessUtils();
  	/*
  	 * change by wandl
  	 * 转单界面限制查询数据量10000行，超过10000行会报错提示缩小查询范围！
  	 */
  	SCMDataAccessUtils util = new SCMDataAccessUtils(10000);
    String[] bids = util.query(sql).toOneDimensionStringArray();
    if (ArrayUtils.isEmpty(bids)) {
      return null;
    }
    // 查询视图VO
    ViewQuery<OrderViewVO> billQuery =
        new ViewQuery<OrderViewVO>(OrderViewVO.class);
    OrderViewVO[] views = billQuery.query(bids);
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }

    OrderVO[] vos = OrderViewVO.getOrderVO(views);

    // AroundProcesser<OrderVO> processer =
    // new AroundProcesser<OrderVO>(OrderPluginPoint.Query_ET);
    // this.addRule(processer);
    // processer.after(vos);
    return vos;
  }

  /**
   * 自动拣配查询
   * 
   * @param org
   * @param material
   * @param warehouse
   * @param batchcode
   * @param bid
   * @return OrderVO[]
   * @throws BusinessException
   */
  public OrderVO[] queryForet(String org, String material, String warehouse,
      String batchcode, String bid) throws BusinessException {

    String sql = this.createSql(org, material, warehouse, batchcode, bid);
    DataAccessUtils util = new DataAccessUtils();
    String[] bids = util.query(sql).toOneDimensionStringArray();
    if (ArrayUtils.isEmpty(bids)) {
      return null;
    }
    // 查询视图VO
    ViewQuery<OrderViewVO> billQuery =
        new ViewQuery<OrderViewVO>(OrderViewVO.class);
    OrderViewVO[] views = billQuery.query(bids);
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }

    OrderVO[] vos = OrderViewVO.getOrderVO(views);

    // AroundProcesser<OrderVO> processer =
    // new AroundProcesser<OrderVO>(OrderPluginPoint.Query_ET);
    // this.addRule(processer);
    // processer.after(vos);
    return vos;
  }

  // private void addRule(AroundProcesser<OrderVO> processer) {
  // if (null == processer) {
  // return;
  // }
  // }

  /**
   * 拣配自动查询条件
   * 
   * @param org
   * @param material
   * @param warehouse
   * @param batchcode
   * @param bid
   * @return sql
   */
  private String condition(String org, String material, String warehouse,
      String batchcode, String bid, String itemTableAlias) {

    SqlBuilder whereSql = new SqlBuilder();
    whereSql.append(" and " + itemTableAlias + "." + OrderItemVO.PK_ARRVSTOORG,
        org);
    if (!StringUtil.isEmptyTrimSpace(warehouse)) {
      whereSql.append(" and (" + itemTableAlias + "."
          + OrderItemVO.PK_RECVSTORDOC, warehouse);
      whereSql.append(" or " + itemTableAlias + "."
          + OrderItemVO.PK_RECVSTORDOC, DBConsts.NULL_WAVE);
      whereSql.append(") ");
    }
    if (!StringUtil.isEmptyTrimSpace(batchcode)) {
      whereSql.append(" and (" + itemTableAlias + "." + OrderItemVO.VBATCHCODE,
          batchcode);
      whereSql.append(" or " + itemTableAlias + "." + OrderItemVO.VBATCHCODE,
          DBConsts.NULL_WAVE);
      whereSql.append(") ");
    }
    whereSql.append(" and " + itemTableAlias + "." + OrderItemVO.PK_MATERIAL,
        material);
    whereSql
        .append(" and " + itemTableAlias + "." + OrderItemVO.CFIRSTBID, bid);

    return whereSql.toString();
  }

  /**
   * 构建查询方案
   * 
   * @return QueryScheme
   */
  private QueryScheme createQueryScheme() {
    SqlBuilder from = new SqlBuilder();
    from.append(PUEntity.M21_H_TABLE + " " + PUEntity.M21_H_TABLE);

    FromWhereSQLImpl fwsql = new FromWhereSQLImpl(from.toString(), "");
    Map<String, String> attAlsMap = new HashMap<String, String>();
    attAlsMap.put(FromWhereSQL.DEFAULT_ATTRPATH, PUEntity.M21_H_TABLE);
    fwsql.setAttrpath_alias_map(attAlsMap);
    String orderbeanid = null;
    try {
      orderbeanid =
          MetaUtils.getBusinessEntityByBillType(POBillType.Order.getCode())
              .getID();
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    QueryScheme qs = new QueryScheme();
    Map<String, QueryCondition> qcMap = new HashMap<String, QueryCondition>();
    qs.putTableJoinFromWhereSQL(fwsql);
    qs.put(QueryConstants.BEAN_ID, orderbeanid);
    qs.put(QueryConstants.QUERY_CONDITION, qcMap);
    return qs;
  }

  /**
   * 拣配查询方案sql
   * 
   * @param queryScheme
   * @return sql
   */
  private String createSql(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String itemTableAlias =
        qrySchemeProcessor.getTableAliasOfAttribute("pk_order_b.pk_org");
    String[] ids = (String[]) queryScheme.get(OrderItemVO.PK_ORDER_B);
    if (ids != null && ids.length > 0) {
      // 有id传入时，只需查询指定id的采购订单，用于加载已拣配的订单行。么贵敬
      SqlBuilder builder = new SqlBuilder();
      builder.append(OrderItemVO.PK_ORDER_B, ids);
      String idsql = " and " + builder.toString();
      qrySchemeProcessor.appendWhere(idsql);
    }
    else {

      // where
      SqlBuilder wheresql = new SqlBuilder();

      wheresql.append(this.whereSql(queryScheme));
      if (queryScheme.get(OrderItemVO.PK_ARRVSTOORG) != null) {

        wheresql.append(" and " + itemTableAlias + "."
            + OrderItemVO.PK_ARRVSTOORG,
            queryScheme.get(OrderItemVO.PK_ARRVSTOORG).toString());
      }
      if (queryScheme.get(OrderItemVO.PK_MATERIAL) != null) {

        wheresql.append(" and " + itemTableAlias + "."
            + OrderItemVO.PK_MATERIAL, queryScheme.get(OrderItemVO.PK_MATERIAL)
            .toString());
      }
      if (queryScheme.get(OrderItemVO.VBATCHCODE) != null) {

        wheresql.append(" and (" + itemTableAlias + "."
            + OrderItemVO.VBATCHCODE, queryScheme.get(OrderItemVO.VBATCHCODE)
            .toString());
        wheresql.append(" or " + itemTableAlias + "." + OrderItemVO.VBATCHCODE,
            DBConsts.NULL_WAVE);
        wheresql.append(") ");

      }
      if (queryScheme.get(OrderItemVO.PK_RECVSTORDOC) != null) {

        wheresql.append(" and (" + itemTableAlias + "."
            + OrderItemVO.PK_RECVSTORDOC,
            queryScheme.get(OrderItemVO.PK_RECVSTORDOC).toString());
        wheresql.append(" or " + itemTableAlias + "."
            + OrderItemVO.PK_RECVSTORDOC, DBConsts.NULL_WAVE);
        wheresql.append(") ");
      }

      qrySchemeProcessor.appendWhere(wheresql.toString());
    }

    SqlBuilder wholesql = new SqlBuilder();
    wholesql.append("select ");
    wholesql.append(itemTableAlias + "." + OrderItemVO.PK_ORDER_B + " ");
    wholesql.append(qrySchemeProcessor.getFinalFromWhere());

    return wholesql.toString();
  }

  private String createSql(String org, String material, String warehouse,
      String batchcode, String bid) {

    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);

    String itemTableAlias =
        qrySchemeProcessor.getTableAliasOfAttribute("pk_order_b.pk_org");

    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(this.whereSql(queryScheme));
    wherePart.append(this.condition(org, material, warehouse, batchcode, bid,
        itemTableAlias));

    qrySchemeProcessor.appendWhere(wherePart.toString());

    SqlBuilder wholesql = new SqlBuilder();
    wholesql.append("select ");
    wholesql.append(itemTableAlias + "." + OrderItemVO.PK_ORDER_B + " ");
    wholesql.append(qrySchemeProcessor.getFinalFromWhere());

    return wholesql.toString();
  }

  /**
   * 固定查询条件
   * 
   * @return sql
   */
  private String whereSql(IQueryScheme queryScheme) {

    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    String itemTableAlias =
        qrySchemeProcessor.getTableAliasOfAttribute("pk_order_b.pk_org");
    SqlBuilder whereSql = new SqlBuilder();
    whereSql.append(
        " and " + mainTableAlias + "." + OrderHeaderVO.FORDERSTATUS,
        (Integer) POEnumBillStatus.APPROVE.value());
    whereSql.append(" and " + mainTableAlias + "." + OrderHeaderVO.DR, 0);
    whereSql.append(" and " + mainTableAlias + "." + OrderHeaderVO.BISLATEST,
        UFBoolean.TRUE);
    whereSql.append(" and " + mainTableAlias + "." + OrderHeaderVO.BFROZEN,
        UFBoolean.FALSE);
    whereSql.append(" and " + mainTableAlias + "." + OrderHeaderVO.CTRANTYPEID
        + " in (");
    whereSql.append(" select ctrantypeid from po_potrantype ");
    whereSql.append(" where barrive", UFBoolean.FALSE);
    whereSql.append(" and breceiveplan", UFBoolean.FALSE);
    whereSql.append(" and bstore", UFBoolean.TRUE);

    whereSql.append(") and " + itemTableAlias + "." + OrderItemVO.DR, 0);
    whereSql.append(" and " + itemTableAlias + "." + OrderItemVO.BSTOCKCLOSE,
        UFBoolean.FALSE);
    whereSql.append(" and (" + itemTableAlias + "." + OrderItemVO.NNUM
        + " - ISNULL(" + itemTableAlias + "." + OrderItemVO.NACCUMPICKUPNUM
        + ",0)) > 0");

    return whereSql.toString();
  }

}
