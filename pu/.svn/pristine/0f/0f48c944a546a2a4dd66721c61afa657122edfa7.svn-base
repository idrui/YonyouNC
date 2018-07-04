package nc.pubimpl.pu.m23.ec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m21.ec.OrderQueryForECUtil;
import nc.pubitf.pu.m23.ec.ArriveItemECVO;
import nc.pubitf.pu.m23.ec.ArriveMatViewECVO;
import nc.pubitf.pu.m23.ec.ArriveNumSummaryECVO;
import nc.pubitf.pu.m23.ec.ArriveOrderViewECVO;
import nc.pubitf.pu.m23.ec.ArrivePublishedQueryCondVO;
import nc.pubitf.pu.m23.ec.ArrivePublishedViewVO;
import nc.pubitf.pu.m23.ec.BackArriveMatViewECVO;
import nc.pubitf.pu.m23.ec.BackArriveOrderViewECVO;
import nc.pubitf.pu.m23.ec.BackArriveQueryCondVO;
import nc.pubitf.pu.m23.ec.IArriveQueryForEC;
import nc.pubitf.pu.m23.ec.SupplyDetailQueryCondVO;
import nc.ui.querytemplate.querytree.FromWhereSQL;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.pu.ec.NumSummaryQueryECVO;
import nc.vo.pu.ec.QueryForECUtil;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QueryConstants;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.util.MetaUtils;
import nc.vo.pubapp.util.VOSortUtils;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 为电子商务提供到货单查询
 * 
 * @since 6.0
 * @version 2011-5-7 上午11:09:58
 * @author wuxla
 */

public class ArriveQueryForECImpl implements IArriveQueryForEC {

  @Override
  public ArriveOrderViewECVO[] queryArriveByOrderBid(String[] bids)
      throws BusinessException {
    try {
      String sql = this.createOrderBidSql(bids);
      EfficientViewQuery<ArriveOrderViewECVO> query =
          new EfficientViewQuery<ArriveOrderViewECVO>(ArriveOrderViewECVO.class);
      return query.query(sql);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ArriveOrderViewECVO[] queryArriveByOrderHid(String[] hids)
      throws BusinessException {
    try {
      String sql = this.createOrderHidSql(hids);
      EfficientViewQuery<ArriveOrderViewECVO> query =
          new EfficientViewQuery<ArriveOrderViewECVO>(ArriveOrderViewECVO.class);
      return query.query(sql);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ArriveMatViewECVO[] queryArriveItemsByMat(NumSummaryQueryECVO queryECVO)
      throws BusinessException {
    try {
      String pk_org = queryECVO.getPk_org();
      String[] pk_materials = queryECVO.getPk_materials();
      String pk_supplier = queryECVO.getPk_supplier();
      UFDate beginDate = queryECVO.getBeginDate();
      UFDate endDate = queryECVO.getEndDate();
      QueryForECUtil.checkMatAndSupplierCond(pk_materials, pk_supplier,
          beginDate, endDate);
      String sql =
          this.createMatSql(pk_org, pk_materials, pk_supplier, beginDate,
              endDate);
      SqlBuilder sqlBuild = new SqlBuilder();
      sqlBuild.append(sql);
      sqlBuild.append(" and " + ArriveHeaderVO.BISBACK, UFBoolean.FALSE);
      EfficientViewQuery<ArriveMatViewECVO> query =
          new EfficientViewQuery<ArriveMatViewECVO>(ArriveMatViewECVO.class);
      ArriveMatViewECVO[] matVOs = query.query(sql);
      return matVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;

  }

  @Override
  public ArriveItemECVO[] queryArriveItemVOByHid(String hid)
      throws BusinessException {
    try {
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_23_06.name());
      String orderHidCond =
          builder.buildSQL(" and " + ArriveItemVO.PK_ARRIVEORDER, new String[] {
            hid
          });
      SqlBuilder sql = new SqlBuilder();
      sql.append(orderHidCond);
      EfficientViewQuery<ArriveItemECVO> query =
          new EfficientViewQuery<ArriveItemECVO>(ArriveItemECVO.class);
      return query.query(sql.toString());
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ArriveVO[] queryBackArriveByCond(BackArriveQueryCondVO condVo)
      throws BusinessException {
    try {
      String sql = this.getBackArriveBidQuerySqlByCond(condVo);
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql);
      String[] hids = rowset.toOneDimensionStringArray();
      if (ArrayUtils.isEmpty(hids)) {
        return null;
      }

      BillQuery<ArriveVO> query = new BillQuery<ArriveVO>(ArriveVO.class);
      return query.query(hids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ArriveVO[] queryBackArriveByHid(String[] hids)
      throws BusinessException {
    try {
      BillQuery<ArriveVO> query = new BillQuery<ArriveVO>(ArriveVO.class);
      return query.query(hids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public BackArriveOrderViewECVO[] queryBackArriveByOrderBid(String[] bids)
      throws BusinessException {
    try {
      String sql = this.createBackOrderBidSql(bids);
      EfficientViewQuery<BackArriveOrderViewECVO> query =
          new EfficientViewQuery<BackArriveOrderViewECVO>(
              BackArriveOrderViewECVO.class);
      BackArriveOrderViewECVO[] vos = query.query(sql);
      VOSortUtils.descSort(vos, new String[] {
        ArriveHeaderVO.DBILLDATE
      });
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public BackArriveOrderViewECVO[] queryBackArriveByOrderHid(String[] hids)
      throws BusinessException {
    try {
      String sql = this.createBackOrderHidSql(hids);
      EfficientViewQuery<BackArriveOrderViewECVO> query =
          new EfficientViewQuery<BackArriveOrderViewECVO>(
              BackArriveOrderViewECVO.class);
      BackArriveOrderViewECVO[] vos = query.query(sql);
      VOSortUtils.descSort(vos, new String[] {
        ArriveHeaderVO.DBILLDATE
      });
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public BackArriveMatViewECVO[] queryBackArriveItemsByMat(
      NumSummaryQueryECVO queryECVO) throws BusinessException {
    try {
      String pk_org = queryECVO.getPk_org();
      String[] pk_materials = queryECVO.getPk_materials();
      String pk_supplier = queryECVO.getPk_supplier();
      UFDate beginDate = queryECVO.getBeginDate();
      UFDate endDate = queryECVO.getEndDate();
      QueryForECUtil.checkMatAndSupplierCond(pk_materials, pk_supplier,
          beginDate, endDate);
      String sql =
          this.createMatSql(pk_org, pk_materials, pk_supplier, beginDate,
              endDate);
      SqlBuilder sqlBuild = new SqlBuilder();
      sqlBuild.append(sql);
      sqlBuild.append(" and " + ArriveHeaderVO.BISBACK, UFBoolean.TRUE);
      EfficientViewQuery<BackArriveMatViewECVO> query =
          new EfficientViewQuery<BackArriveMatViewECVO>(
              BackArriveMatViewECVO.class);
      BackArriveMatViewECVO[] matVOs = query.query(sqlBuild.toString());
      return matVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ArriveNumSummaryECVO[] queryNumSummaryByCond(
      SupplyDetailQueryCondVO condVo) throws BusinessException {
    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String hname = qrySchemeProcessor.getMainTableAlias();

    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(OrderQueryForECUtil.getSupplyDetailQueryWherePart(
        qrySchemeProcessor, condVo));
    wherePart.append(" and " + hname + "." + ArriveHeaderVO.FBILLSTATUS + " = "
        + POEnumBillStatus.APPROVE.toInt());

    String groupBy = "po_arriveorder_b.pk_srcmaterial";
    return this.queryNumSummary(qrySchemeProcessor, groupBy);
  }

  @Override
  public ArriveNumSummaryECVO[] queryNumSummaryByOrderBids(String[] bids)
      throws BusinessException {
    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);

    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_23_15.name());
    String where = builder.buildSQL(" and po_arriveorder_b.pk_order_b ", bids);
    qrySchemeProcessor.appendWhere(where);

    String groupBy = "po_arriveorder_b.pk_order_b";
    return this.queryNumSummary(qrySchemeProcessor, groupBy);
  }

  @Override
  public ArrivePublishedViewVO[] queryPublishedAndApprovedArrive(
      ArrivePublishedQueryCondVO condVO) throws BusinessException {
    try {
      String sql = this.createApprovedArrive(condVO);
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql);
      String[] hids = rowset.toOneDimensionStringArray();
      if (ArrayUtils.isEmpty(hids)) {
        return null;
      }
      ViewQuery<ArrivePublishedViewVO> query =
          new ViewQuery<ArrivePublishedViewVO>(ArrivePublishedViewVO.class);
      ArrivePublishedViewVO[] vos = query.query(hids);
      VOSortUtils.descSort(vos, new String[] {
        ArriveHeaderVO.DBILLDATE
      });
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private String createApprovedArrive(ArrivePublishedQueryCondVO condVO) {
    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String hname = qrySchemeProcessor.getMainTableAlias();

    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(OrderQueryForECUtil.getApprovedArriveWherePart(
        qrySchemeProcessor, condVO));
    qrySchemeProcessor.appendWhere(wherePart.toString());
    qrySchemeProcessor
        .appendWhere(" and " + hname + "." + ArriveHeaderVO.FBILLSTATUS + " = "
            + POEnumBillStatus.APPROVE.toInt());
    String fromwherePart = qrySchemeProcessor.getFinalFromWhere();
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct " + hname + "." + ArriveHeaderVO.PK_ARRIVEORDER);
    sql.append(fromwherePart);
    return sql.toString();
  }

  private String createBackOrderBidSql(String[] bids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_23_07.name());
    String orderHidCond =
        builder.buildSQL(" and " + ArriveItemVO.PK_ORDER_B, bids);
    SqlBuilder sql = new SqlBuilder();
    sql.append(orderHidCond);
    sql.append(" and " + ArriveHeaderVO.BISBACK, UFBoolean.TRUE);
    sql.append(" and " + ArriveHeaderVO.FBILLSTATUS,
        POEnumBillStatus.APPROVE.toInteger());
    return sql.toString();
  }

  private String createBackOrderHidSql(String[] hids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_23_07.name());
    String orderHidCond =
        builder.buildSQL(" and " + ArriveItemVO.PK_ORDER, hids);
    SqlBuilder sql = new SqlBuilder();
    sql.append(orderHidCond);
    sql.append(" and " + ArriveHeaderVO.BISBACK, UFBoolean.TRUE);
    return sql.toString();
  }

  private String createMatSql(String pk_org, String[] pk_materials,
      String pk_supplier, UFDate beginDate, UFDate endDate) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_23_08.name());
    String matHidCond =
        builder.buildSQL(" and " + ArriveItemVO.PK_MATERIAL, pk_materials);
    SqlBuilder sql = new SqlBuilder();
    sql.append(matHidCond);
    sql.append(" and po_arriveorder." + PUQueryConst.SUPPLIER, pk_supplier);
    if (pk_org != null) {
      sql.append(" and po_arriveorder." + ArriveHeaderVO.PK_PURCHASEORG, pk_org);
    }
    sql.append(" and po_arriveorder." + ArriveHeaderVO.FBILLSTATUS + " = "
        + POEnumBillStatus.APPROVE.toInt());
    sql.append(" and po_arriveorder." + ArriveHeaderVO.DBILLDATE, ">=",
        beginDate.toString());
    sql.append(" and po_arriveorder." + ArriveHeaderVO.DBILLDATE, "<=",
        endDate.toString());
    return sql.toString();
  }

  private String createOrderBidSql(String[] bids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_23_09.name());
    String orderHidCond =
        builder.buildSQL(" and " + ArriveItemVO.PK_ORDER_B, bids);
    SqlBuilder sql = new SqlBuilder();
    sql.append(orderHidCond);
    sql.append(" and " + ArriveHeaderVO.BISBACK, UFBoolean.FALSE);
    sql.append(" and po_arriveorder." + ArriveHeaderVO.FBILLSTATUS + " = 3 ");
    return sql.toString();
  }

  private String createOrderHidSql(String[] hids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_23_09.name());
    String orderHidCond =
        builder.buildSQL(" and " + ArriveItemVO.PK_ORDER, hids);
    SqlBuilder sql = new SqlBuilder();
    sql.append(orderHidCond);
    sql.append(" and " + ArriveHeaderVO.BISBACK, UFBoolean.FALSE);
    sql.append(" and po_arriveorder." + ArriveHeaderVO.FBILLSTATUS + " = ");
    sql.append(POEnumBillStatus.APPROVE);
    return sql.toString();
  }

  private IQueryScheme createQueryScheme() {
    SqlBuilder from = new SqlBuilder();
    from.append(PUEntity.M23_H_TABLE + " " + PUEntity.M23_H_TABLE);
    SqlBuilder where = new SqlBuilder();
    FromWhereSQLImpl fwsql =
        new FromWhereSQLImpl(from.toString(), where.toString());
    Map<String, String> attAlsMap = new HashMap<String, String>();
    attAlsMap.put(FromWhereSQL.DEFAULT_ATTRPATH, PUEntity.M23_H_TABLE);
    fwsql.setAttrpath_alias_map(attAlsMap);
    String arrivebeanid = null;
    try {
      arrivebeanid =
          MetaUtils.getBusinessEntityByBillType(POBillType.Arrive.getCode())
              .getID();
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    QueryScheme qs = new QueryScheme();
    Map<String, QueryCondition> qcMap = new HashMap<String, QueryCondition>();
    qs.putTableJoinFromWhereSQL(fwsql);
    qs.put(QueryConstants.BEAN_ID, arrivebeanid);
    qs.put(QueryConstants.QUERY_CONDITION, qcMap);
    return qs;
  }

  private String getBackArriveBidQuerySqlByCond(BackArriveQueryCondVO condVO) {
    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String hname = qrySchemeProcessor.getMainTableAlias();

    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(OrderQueryForECUtil.getBackArriveWherePart(
        qrySchemeProcessor, condVO));
    qrySchemeProcessor.appendWhere(wherePart.toString());
    qrySchemeProcessor.appendWhere(" and " + hname + "."
        + ArriveHeaderVO.BISBACK + " = 'Y'");
    String fromwherePart = qrySchemeProcessor.getFinalFromWhere();
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct " + hname + "." + ArriveHeaderVO.PK_ARRIVEORDER);
    sql.append(fromwherePart);
    return sql.toString();
  }

  /**
   * 目前有两个接口返回值类似，查询语句类似，故用相同的VO进行封装。
   * 差别仅在一种场景按订单BID分组，VO中订单BID有值，物料OID为空。
   * 另一种场景订单BID为空，物料OID有值。
   * 
   * @param qrySchemeProcessor 包含where和from的信息
   * @param groupBy 分组条件。目前可能的值有：
   *          1.po_arriveorder_b.pk_srcmaterial
   *          2.po_arriveorder_b.pk_order_b
   * @return 数据VO数组
   */
  private ArriveNumSummaryECVO[] queryNumSummary(
      QuerySchemeProcessor qrySchemeProcessor, String groupBy) {
    qrySchemeProcessor
        .appendFrom(" inner join po_arriveorder_b on po_arriveorder_b.pk_arriveorder=po_arriveorder.pk_arriveorder ");

    SqlBuilder sqlBuilder = new SqlBuilder();
    sqlBuilder.append("select ");
    sqlBuilder.append(groupBy);
    sqlBuilder
        .append(",isnull(sum(case when po_arriveorder.bisback = 'N' then nastnum else 0 end), 0) arrivenum,");
    sqlBuilder
        .append(" isnull(sum(case when po_arriveorder.bisback = 'Y' then nastnum else 0 end), 0) backnum");
    sqlBuilder.append(qrySchemeProcessor.getFinalFromWhere());
    sqlBuilder.append(" and po_arriveorder_b.dr = 0 ");
    sqlBuilder.append(" group by ");
    sqlBuilder.append(groupBy);

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sqlBuilder.toString());
    if (rowset.size() > 0) {
      List<ArriveNumSummaryECVO> vos = new ArrayList<ArriveNumSummaryECVO>();
      while (rowset.next()) {
        ArriveNumSummaryECVO vo = new ArriveNumSummaryECVO();
        if ("po_arriveorder_b.pk_order_b".equals(groupBy)) {
          vo.setPk_order_b(rowset.getString(0));
        }
        else if ("po_arriveorder_b.pk_srcmaterial".equals(groupBy)) {
          vo.setPk_srcmaterial(rowset.getString(0));
        }
        vo.setArrivenum(rowset.getUFDouble(1));
        vo.setBacknum(rowset.getUFDouble(2));
        vos.add(vo);
      }
      return vos.toArray(new ArriveNumSummaryECVO[vos.size()]);
    }
    return new ArriveNumSummaryECVO[0];
  }
}
