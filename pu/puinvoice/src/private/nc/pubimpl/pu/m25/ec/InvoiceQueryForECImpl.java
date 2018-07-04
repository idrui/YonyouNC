package nc.pubimpl.pu.m25.ec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubift.pu.m25.ec.IInvoiceQueryForEC;
import nc.pubift.pu.m25.ec.InvoiceItemECVO;
import nc.pubift.pu.m25.ec.InvoiceMatViewECVO;
import nc.pubift.pu.m25.ec.InvoiceNumSummaryECVO;
import nc.pubift.pu.m25.ec.InvoiceOrderViewECVO;
import nc.pubift.pu.m25.ec.InvoicePublishedQueryCondVO;
import nc.pubift.pu.m25.ec.InvoicePublishedViewVO;
import nc.pubift.pu.m25.ec.InvoiceSupplyDetailQueryCondVO;
import nc.pubitf.pu.m21.ec.OrderQueryForECUtil;
import nc.ui.querytemplate.querytree.FromWhereSQL;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.pu.ec.NumSummaryQueryECVO;
import nc.vo.pu.ec.QueryForECUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
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
 * @since 6.0
 * @version 2011-5-7 上午11:25:00
 * @author wuxla
 */

public class InvoiceQueryForECImpl implements IInvoiceQueryForEC {
  @Override
  public InvoiceOrderViewECVO[] queryInvoiceByOrderBid(String[] bids)
      throws BusinessException {
    try {
      String sql = this.createOrderBidSql(bids);
      EfficientViewQuery<InvoiceOrderViewECVO> query =
          new EfficientViewQuery<InvoiceOrderViewECVO>(
              InvoiceOrderViewECVO.class);
      InvoiceOrderViewECVO[] vos = query.query(sql);
      VOSortUtils.descSort(vos, new String[] {
        InvoiceHeaderVO.DBILLDATE
      });
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public InvoiceOrderViewECVO[] queryInvoiceByOrderHid(String[] hids)
      throws BusinessException {
    try {
      String sql = this.createOrderHidSql(hids);
      EfficientViewQuery<InvoiceOrderViewECVO> query =
          new EfficientViewQuery<InvoiceOrderViewECVO>(
              InvoiceOrderViewECVO.class);
      InvoiceOrderViewECVO[] vos = query.query(sql);
      VOSortUtils.descSort(vos, new String[] {
        InvoiceHeaderVO.DBILLDATE
      });
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public InvoiceMatViewECVO[] queryInvoiceItemsByMat(
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
      EfficientViewQuery<InvoiceMatViewECVO> query =
          new EfficientViewQuery<InvoiceMatViewECVO>(InvoiceMatViewECVO.class);
      InvoiceMatViewECVO[] matVOs = query.query(sql);
      VOSortUtils.descSort(matVOs, new String[] {
        InvoiceHeaderVO.DBILLDATE
      });
      return matVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public InvoiceItemECVO[] queryInvoiceItemVOByHid(String[] hids)
      throws BusinessException {
    try {
      String sql = this.createHidSql(hids);
      EfficientViewQuery<InvoiceItemECVO> query =
          new EfficientViewQuery<InvoiceItemECVO>(InvoiceItemECVO.class);
      InvoiceItemECVO[] invoiceItemVOs = query.query(sql);
      return invoiceItemVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public InvoiceNumSummaryECVO[] queryNumSummaryByCond(
      InvoiceSupplyDetailQueryCondVO condVo) throws BusinessException {
    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String hname = qrySchemeProcessor.getMainTableAlias();

    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(OrderQueryForECUtil.getSupplyDetailQueryWherePart(
        qrySchemeProcessor, condVo));
    wherePart.append(" and " + hname + "." + InvoiceHeaderVO.FBILLSTATUS
        + " = " + POEnumBillStatus.APPROVE.toInt());

    String groupBy = "po_invoice_b.pk_srcmaterial";
    return this.queryNumSummary(qrySchemeProcessor, groupBy);
  }

  @Override
  public InvoiceNumSummaryECVO[] queryNumSummaryByOrderBids(String[] bids)
      throws BusinessException {
    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);

    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_25_14.name());
    String where = builder.buildSQL(" and po_invoice_b.pk_order_b ", bids);
    qrySchemeProcessor.appendWhere(where);

    String groupBy = "po_invoice_b.pk_order_b";
    return this.queryNumSummary(qrySchemeProcessor, groupBy);
  }

  @Override
  public InvoicePublishedViewVO[] queryPublishedAndApprovedInvoice(
      InvoicePublishedQueryCondVO condVO) throws BusinessException {
    try {
      // 发票查询日期不是必输项，除供应商主键外都不是必输项
      QueryForECUtil.checkSupplierCond(condVO);
      // QueryForECUtil.checkDateCond(condVO);
      String sql = this.createApprovedInvoice(condVO);
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql);
      String[] hids = rowset.toOneDimensionStringArray();
      if (ArrayUtils.isEmpty(hids)) {
        return null;
      }
      ViewQuery<InvoicePublishedViewVO> query =
          new ViewQuery<InvoicePublishedViewVO>(InvoicePublishedViewVO.class);
      InvoicePublishedViewVO[] vos = query.query(hids);
      VOSortUtils.descSort(vos, new String[] {
        InvoiceHeaderVO.DBILLDATE
      });
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private String createApprovedInvoice(InvoicePublishedQueryCondVO condVO) {
    IQueryScheme queryScheme = this.createQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String hname = qrySchemeProcessor.getMainTableAlias();

    SqlBuilder wherePart = new SqlBuilder();
    wherePart.append(OrderQueryForECUtil.getApprovedInvoiceWherePart(
        qrySchemeProcessor, condVO));
    qrySchemeProcessor.appendWhere(wherePart.toString());
    qrySchemeProcessor.appendWhere(" and " + hname + "."
        + InvoiceHeaderVO.FBILLSTATUS + " = 3 ");
    String fromwherePart = qrySchemeProcessor.getFinalFromWhere();
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct " + hname + "." + InvoiceHeaderVO.PK_INVOICE);
    sql.append(fromwherePart);
    return sql.toString();
  }

  private String createHidSql(String[] hids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_25_07.name());
    String orderHidCond =
        builder.buildSQL(" and po_invoice." + InvoiceItemVO.PK_INVOICE, hids);
    SqlBuilder sql = new SqlBuilder();
    sql.append(orderHidCond);
    return sql.toString();
  }

  private String createMatSql(String pk_org, String[] pk_materials,
      String pk_supplier, UFDate beginDate, UFDate endDate) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_25_08.name());
    String matHidCond =
        builder.buildSQL(" and " + InvoiceItemVO.PK_MATERIAL, pk_materials);
    SqlBuilder sql = new SqlBuilder();
    sql.append(matHidCond);
    sql.append(" and po_invoice." + PUQueryConst.SUPPLIER, pk_supplier);
    if (pk_org != null) {
      sql.append(" and po_invoice." + InvoiceHeaderVO.PK_PURCHASEORG, pk_org);
    }
    sql.append(" and po_invoice." + InvoiceHeaderVO.FBILLSTATUS,
        POEnumBillStatus.APPROVE.toInt());
    sql.append(" and po_invoice." + InvoiceHeaderVO.DBILLDATE, ">=",
        beginDate.toString());
    sql.append(" and po_invoice." + InvoiceHeaderVO.DBILLDATE, "<=",
        endDate.toString());
    return sql.toString();
  }

  private String createOrderBidSql(String[] bids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_25_09.name());
    String orderHidCond =
        builder.buildSQL(" and " + InvoiceItemVO.PK_ORDER_B, bids);
    SqlBuilder sql = new SqlBuilder();
    sql.append(orderHidCond);
    sql.append(" and po_invoice." + InvoiceHeaderVO.FBILLSTATUS,
        POEnumBillStatus.APPROVE.toInt());
    sql.append(" and po_invoice." + InvoiceHeaderVO.BFROZEN, UFBoolean.FALSE);
    return sql.toString();
  }

  private String createOrderHidSql(String[] hids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_25_09.name());
    String orderHidCond =
        builder.buildSQL(" and " + InvoiceItemVO.PK_ORDER, hids);
    SqlBuilder sql = new SqlBuilder();
    sql.append(orderHidCond);
    return sql.toString();
  }

  private QueryScheme createQueryScheme() {
    SqlBuilder from = new SqlBuilder();
    from.append(PUEntity.M25_H_TAB + " " + PUEntity.M25_H_TAB);
    from.append(" inner join ");
    from.append(PUEntity.M25_B_TAB + " " + PUEntity.M25_B_TAB);
    from.append(" on po_invoice.pk_invoice = po_invoice_b.pk_invoice ");
    // from.append(" inner join po_order_ec po_order_ec on po_invoice_b.pk_order = po_order_ec.pk_order");
    // from.append(" inner join po_order po_order on po_order.pk_order = po_order_ec.pk_order");
    SqlBuilder where = new SqlBuilder();
    FromWhereSQLImpl fwsql =
        new FromWhereSQLImpl(from.toString(), where.toString());
    Map<String, String> attAlsMap = new HashMap<String, String>();
    attAlsMap.put(FromWhereSQL.DEFAULT_ATTRPATH, PUEntity.M25_H_TAB);
    fwsql.setAttrpath_alias_map(attAlsMap);
    String invoicebeanid = null;
    try {
      invoicebeanid =
          MetaUtils.getBusinessEntityByBillType(POBillType.Invoice.getCode())
              .getID();
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    QueryScheme qs = new QueryScheme();
    Map<String, QueryCondition> qcMap = new HashMap<String, QueryCondition>();
    qs.putTableJoinFromWhereSQL(fwsql);
    qs.put(QueryConstants.BEAN_ID, invoicebeanid);
    qs.put(QueryConstants.QUERY_CONDITION, qcMap);
    return qs;
  }

  /**
   * 目前有两个接口返回值类似，查询语句类似，故用相同的VO进行封装。
   * 差别仅在一种场景按订单BID分组，VO中订单BID有值，物料OID为空。
   * 另一种场景订单BID为空，物料OID有值。
   * 
   * @param qrySchemeProcessor 包含where和from的信息
   * @param groupBy 分组条件。目前可能的值有：
   *          1.po_invoice_b.pk_srcmaterial
   *          2.po_invoice_b.pk_order_b
   * @return 数据VO数组
   */
  private InvoiceNumSummaryECVO[] queryNumSummary(
      QuerySchemeProcessor qrySchemeProcessor, String groupBy) {
    qrySchemeProcessor
        .appendFrom(" inner join po_invoice_b on po_invoice_b.pk_invoice = po_invoice.pk_invoice ");

    SqlBuilder sqlBuilder = new SqlBuilder();
    sqlBuilder.append("select ");
    sqlBuilder.append(groupBy);
    sqlBuilder.append(",sum(po_invoice_b.nnum) nnum,");
    sqlBuilder.append(" sum(po_invoice_b.norigtaxmny) norigtaxmny");
    sqlBuilder.append(qrySchemeProcessor.getFinalFromWhere());
    sqlBuilder.append(" and po_invoice_b.dr = 0 ");
    sqlBuilder.append(" group by ");
    sqlBuilder.append(groupBy);

    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sqlBuilder.toString());
    if (rowset.size() > 0) {
      List<InvoiceNumSummaryECVO> vos = new ArrayList<InvoiceNumSummaryECVO>();
      while (rowset.next()) {
        InvoiceNumSummaryECVO vo = new InvoiceNumSummaryECVO();
        if ("po_invoice_b.pk_order_b".equals(groupBy)) {
          vo.setPk_order_b(rowset.getString(0));
        }
        else if ("po_invoice_b.pk_srcmaterial".equals(groupBy)) {
          vo.setPk_srcmaterial(rowset.getString(0));
        }
        vo.setNnum(rowset.getUFDouble(1));
        vo.setNorigtaxmny(rowset.getUFDouble(2));
        vos.add(vo);
      }
      return vos.toArray(new InvoiceNumSummaryECVO[vos.size()]);
    }
    return new InvoiceNumSummaryECVO[0];
  }
}
