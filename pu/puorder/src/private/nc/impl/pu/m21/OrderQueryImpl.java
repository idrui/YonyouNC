/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 下午02:03:31
 */
package nc.impl.pu.m21;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.pu.m21.query.OrderCloseQueryBP;
import nc.bs.pu.m21.query.OrderQueryBP;
import nc.impl.pu.m21.action.OrderReviseQueryAction;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.pu.m21.IOrderQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.enumeration.EnumBillStatus;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.rule.PUBillLazyQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单查询操作实现
 * <li>维护查询
 * <li>修订查询
 * </ul>
 * 关闭查询
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2009-12-30 下午02:03:31
 */
public class OrderQueryImpl implements IOrderQuery {
  @Override
  public OrderCloseVO[] closeQuery(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(this.createWholeCloseSql(queryScheme));
      return new OrderCloseQueryBP().query(rowset.toOneDimensionStringArray());
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] maintainQuery(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return this.maintainLazyQuery(queryScheme);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IOrderQuery#queryOrderViewsByIds(java.lang.String[])
   */
  @Override
  public Map<String, OrderViewVO> queryOrderViewsByIds(String[] ids)
      throws BusinessException {
    if (ArrayUtils.isEmpty(ids)) {
      return null;
    }

    try {
      ViewQuery<OrderViewVO> query =
          new ViewQuery<OrderViewVO>(OrderViewVO.class);
      OrderViewVO[] views = query.query(ids);

      if (ArrayUtils.isEmpty(views)) {
        return null;
      }

      Map<String, OrderViewVO> map = new HashMap<String, OrderViewVO>();
      for (OrderViewVO view : views) {
        map.put(view.getPk_order_b(), view);
      }
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IOrderQuery#queryOrderVOsByIds(java.lang.String[])
   */
  @Override
  public OrderVO[] queryOrderVOsByIds(String[] ids, UFBoolean isLazy)
      throws BusinessException {
    if (ArrayUtils.isEmpty(ids)) {
      return null;
    }

    try {
      DataAccessUtils utils = new DataAccessUtils();
      // 执行sql，查询表头id
      IRowSet rowset = utils.query(this.createWholeSql(ids));
      return new OrderQueryBP().query(rowset.toOneDimensionStringArray(),
          isLazy);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public OrderVO[] reviseQuery(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return new OrderReviseQueryAction().query(queryScheme);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private void appendOrSql(SqlBuilder sql, String appSql) {
    if (sql.toString().equals("(")) {
      sql.append(appSql);
    }
    else {
      sql.append(" or ");
      sql.append(appSql);
    }
  }

  private void appOnwayArriveSql(QuerySchemeProcessor qrySchemeProcessor,
      SqlBuilder sql) {
    String btab =
        qrySchemeProcessor.getTableAliasOfAttribute(OrderItemVO.PK_ORDER_B
            + "." + OrderItemVO.PK_ORDER_B);
    String arriveSql = "abs(" + btab + "." + OrderItemVO.NACCUMARRVNUM + ")>0";
    this.appendOrSql(sql, arriveSql);
  }

  private void appOnwayAuditSql(QuerySchemeProcessor qrySchemeProcessor,
      SqlBuilder sql) {
    String htab = qrySchemeProcessor.getMainTableAlias();
    String approveSql =
        htab + "." + OrderHeaderVO.FORDERSTATUS + "="
            + POEnumBillStatus.APPROVE.toInteger();
    this.appendOrSql(sql, approveSql);
  }

  private void appOnwayStatusSql(QuerySchemeProcessor qrySchemeProcessor,
      QueryCondition cond, SqlBuilder sql) {
    String btab =
        qrySchemeProcessor.getTableAliasOfAttribute(OrderItemVO.PK_ORDER_B
            + "." + OrderItemVO.PK_ORDER_B);
    SqlBuilder inSql = new SqlBuilder();
    inSql.append("", cond.getValues());
    String onwaySql =
        "exists (select 1 from " + PUEntity.M21_BB_TABLE
            + " existsbb  where existsbb.dr=0 and existsbb."
            + OrderOnwayItemVO.PK_ORDER_B + "=" + btab + "."
            + OrderItemVO.PK_ORDER_B + " and existsbb."
            + OrderOnwayItemVO.FONWAYSTATUS + " " + inSql.toString()
            + " and existsbb.isoperated='Y')";
    this.appendOrSql(sql, onwaySql);
  }

  private void appOnwayStoreSql(QuerySchemeProcessor qrySchemeProcessor,
      SqlBuilder sql) {
    String btab =
        qrySchemeProcessor.getTableAliasOfAttribute(OrderItemVO.PK_ORDER_B
            + "." + OrderItemVO.PK_ORDER_B);
    String storeSql =
        "exists (select 1 from ic_purchasein_b icb where icb.dr=0"
            + " and icb." + MetaNameConst.CFIRSTBILLBID + "=" + btab + "."
            + OrderItemVO.PK_ORDER_B + ")";
    this.appendOrSql(sql, storeSql);
  }

  private String createWholeCloseSql(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    String itemTableAlias =
        qrySchemeProcessor.getTableAliasOfAttribute("pk_order_b.pk_org");

    String where = queryScheme.getTableJoinFromWhereSQL().getWhere();
    if (StringUtils.isNotBlank(where)) {
      qrySchemeProcessor.appendWhere(" and ");
    }
    QueryCondition browclose = qrySchemeProcessor.getQueryCondition("browclose");
    SqlBuilder sql = new SqlBuilder();
    sql.append(mainTableAlias + "." + OrderHeaderVO.DR, 0);
    sql.append(" and " + itemTableAlias + "." + OrderItemVO.DR, 0);
    sql.append(" and " + mainTableAlias + "." + OrderHeaderVO.BISLATEST,
        UFBoolean.TRUE);
    sql.append(" and " + mainTableAlias + "." + OrderHeaderVO.FORDERSTATUS,
        new Integer[] {
          (Integer) POEnumBillStatus.APPROVE.value(),
          (Integer) EnumBillStatus.EXPORT.value()
        });
    if (browclose != null) {
      // 如果行关闭为true，则四个关闭状态全部为true
      if ("Y".endsWith(browclose.getValues()[0])) {
        sql.append(" and " + itemTableAlias + "." + OrderItemVO.BARRIVECLOSE,
            UFBoolean.TRUE);
        sql.append(" and " + itemTableAlias + "." + OrderItemVO.BINVOICECLOSE,
            UFBoolean.TRUE);
        sql.append(" and " + itemTableAlias + "." + OrderItemVO.BPAYCLOSE,
            UFBoolean.TRUE);
        sql.append(" and " + itemTableAlias + "." + OrderItemVO.BSTOCKCLOSE,
            UFBoolean.TRUE);
      }
      else {
        sql.append(" and (" + itemTableAlias + "." + OrderItemVO.BARRIVECLOSE,
            UFBoolean.FALSE);
        sql.append(" or " + itemTableAlias + "." + OrderItemVO.BINVOICECLOSE,
            UFBoolean.FALSE);
        sql.append(" or " + itemTableAlias + "." + OrderItemVO.BPAYCLOSE,
            UFBoolean.FALSE);
        sql.append(" or " + itemTableAlias + "." + OrderItemVO.BSTOCKCLOSE,
            UFBoolean.FALSE);
        sql.append(")");
      }
    }

    qrySchemeProcessor.appendWhere(sql.toString());
    qrySchemeProcessor.appendCurrentGroup();
    qrySchemeProcessor.appendFuncPermissionOrgSql();

    sql = new SqlBuilder();
    sql.append("select " + itemTableAlias + ".pk_order_b ");
    sql.append(qrySchemeProcessor.getFinalFromWhere());

    sql.append(" order by " + mainTableAlias + "." + OrderHeaderVO.VBILLCODE);

    return sql.toString();
  }

  private String createWholeSql(String[] ids) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct po_order.pk_order from po_order where ");
    IDExQueryBuilder idbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_11.name());
    String idsql = idbuilder.buildSQL("po_order.pk_order", ids);
    sql.append(idsql);
    sql.append(" and po_order.dr = 0 ");
    sql.append(" and po_order.bislatest='Y' ");
    sql.append(" and po_order.pk_group='"
        + InvocationInfoProxy.getInstance().getGroupId() + "'");
    return sql.toString();
  }

  private String getOnwaystatusSql(QuerySchemeProcessor qrySchemeProcessor) {
    QueryCondition cond =
        qrySchemeProcessor.getQueryCondition(OrderOnwayItemVO.FONWAYSTATUS);
    if (null == cond || null == cond.getValues() || cond.getValues().length < 1) {
      return "";
    }
    SqlBuilder sql = new SqlBuilder();
    Set<String> splitValueSet =
        new HashSet<String>(Arrays.asList(cond.getValues()));
    sql.append("(");
    // 如果选择了审批
    if (splitValueSet.contains(OnwayStatus.STATUS_AUDIT.toInteger().toString())) {
      this.appOnwayAuditSql(qrySchemeProcessor, sql);
    }
    // 如果选择了到货
    if (splitValueSet
        .contains(OnwayStatus.STATUS_ARRIVE.toInteger().toString())) {
      this.appOnwayArriveSql(qrySchemeProcessor, sql);
    }
    // 如果选择了入库
    if (splitValueSet.contains(OnwayStatus.STATUS_STORE.toInteger().toString())) {
      this.appOnwayStoreSql(qrySchemeProcessor, sql);
    }
    splitValueSet.remove(OnwayStatus.STATUS_AUDIT.toInteger().toString());
    splitValueSet.remove(OnwayStatus.STATUS_STORE.toInteger().toString());
    splitValueSet.remove(OnwayStatus.STATUS_ARRIVE.toInteger().toString());
    // 如果选择了其它在在途状态
    if (!splitValueSet.isEmpty()) {
      this.appOnwayStatusSql(qrySchemeProcessor, cond, sql);
    }
    sql.append(")");
    return " and " + sql.toString();
  }

  private OrderVO[] maintainLazyQuery(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    // 临时拼上订单表头的EC扩展表
    // 平台在根据属性找别名时会拼扩展表，如果在这里拼扩展表，平台还会在拼一次，就重复了。
    // qrySchemeProcessor.getTableAliasOfAttribute(OrderHeaderVO.VREASON);
    SqlBuilder partWhr = new SqlBuilder();
    partWhr.append(" and ");
    partWhr.append(mainTableAlias);
    partWhr.append(".");
    partWhr.append(OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
    qrySchemeProcessor.appendCurrentGroup();
    qrySchemeProcessor.appendFuncPermissionOrgSql();
    qrySchemeProcessor.appendWhere(partWhr.toString());
    // 根据在途状态组织查询条件
    qrySchemeProcessor.appendWhere(this.getOnwaystatusSql(qrySchemeProcessor));
    return (OrderVO[]) new PUBillLazyQuery<OrderVO>(OrderVO.class,
        POBillType.Order).queryOrderByBillcode(queryScheme);
  }

}
