package nc.pubimpl.pu.m25.ic.eurrpt;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubift.pu.m25.ic.eurrpt.IPUForICEURRpt;
import nc.vo.ic.icreport.param.mncommon.MNReportQueryParam;
import nc.vo.ic.icreport.param.mncommon.MNRptTempTableWrapperParam;
import nc.vo.ic.icreport.vo.mncommon.MNReportComBodyVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class PUForICEURRptImpl implements IPUForICEURRpt {

  // 报表的字段。这里三个静态字符串数组必须一一对应。
  private static final String[] MN_TABLE_FIELDS = new String[] {
    MNReportComBodyVO.CAREAID, MNReportComBodyVO.CCOUNTRYID,
    MNReportComBodyVO.CTRANSACTIONTYPE, MNReportComBodyVO.CMATERIALVID,
    MNReportComBodyVO.CMATERIALOID, MNReportComBodyVO.CORIGCURRENCYID,
    MNReportComBodyVO.NORIGTAXMNY, MNReportComBodyVO.NORIGINALNUM,
    MNReportComBodyVO.CSENDTYPEID, MNReportComBodyVO.CTRADEWORDID,
    MNReportComBodyVO.REMARK
  };

  // sql查询的别名。这里三个静态字符串数组必须一一对应。
  private static final String[] SQL_FIELD_ALIAS = new String[] {
    "cdestiareaid", "csendcountryid", "ctransactiontype", "pk_material",
    "pk_srcmaterial", "corigcurrencyid", "norigtaxmny", "nnum",
    "pk_transporttype", "ctradewordid", "remark"
  };

  // sql的取数。这里三个静态字符串数组必须一一对应。
  private static final String[] SQL_FIELD_QUERY =
      new String[] {
        "po_order_b.cdestiareaid",
        "po_invoice.csendcountryid",
        "case when po_order_b.blargess = 'Y' and po_invoice_b.nastnum > 0 then 3 when po_invoice_b.nastnum < 0 then 2 else 1 end",
        "po_invoice_b.pk_material", "po_invoice_b.pk_srcmaterial",
        "po_invoice.corigcurrencyid", "po_invoice_b.norigtaxmny",
        "po_invoice_b.nnum", "po_order.pk_transporttype",
        "po_invoice.ctradewordid", "'PO'"
      };

  @Override
  public MNRptTempTableWrapperParam queryArriveAndInvoice(
      MNReportQueryParam mnReportQueryParam) {
    // 插入临时表
    // mnRptTempTableWrapperParam.insertDataBySql(this.getSql(mnReportQueryParam),
    // this.getColumnAliasMapping());
    MNRptTempTableWrapperParam rptTableWrapper =
        new MNRptTempTableWrapperParam();
    rptTableWrapper.setSqlString(this.getSql(mnReportQueryParam));
    rptTableWrapper.setColumnMap(this.getColumnAliasMapping());
    return rptTableWrapper;
  }

  /**
   * 报表识别的字段和sql中别名的映射关系。
   * 
   * @return map key为报表识别的字段，value为sql中的别名。
   */
  private Map<String, String> getColumnAliasMapping() {
    Map<String, String> columnAliasMapping = new HashMap<String, String>();
    for (int i = 0; i < PUForICEURRptImpl.SQL_FIELD_ALIAS.length; i++) {
      columnAliasMapping.put(PUForICEURRptImpl.MN_TABLE_FIELDS[i],
          PUForICEURRptImpl.SQL_FIELD_ALIAS[i]);
    }

    return columnAliasMapping;
  }

  /**
   * 获取sql中的from片段。
   * 
   * @return sql中的from片段。
   */
  private String getFromSql() {
    SqlBuilder sqlBuilder = new SqlBuilder();
    for (int i = 0; i < PUForICEURRptImpl.SQL_FIELD_QUERY.length; i++) {
      sqlBuilder.append(PUForICEURRptImpl.SQL_FIELD_QUERY[i]);
      sqlBuilder.append(" as ");
      sqlBuilder.append(PUForICEURRptImpl.SQL_FIELD_ALIAS[i]);

      if (i != PUForICEURRptImpl.SQL_FIELD_QUERY.length - 1) {
        sqlBuilder.append(",");
      }
    }
    return sqlBuilder.toString();
  }

  /**
   * 查询sql。
   * 
   * @return sql语句
   */
  private String getSql(MNReportQueryParam mnReportQueryParam) {
    SqlBuilder sqlBuilder = new SqlBuilder();
    sqlBuilder.append("select ");
    sqlBuilder.append(this.getFromSql());
    sqlBuilder.append(" from po_invoice ");
    sqlBuilder
        .append("inner join po_invoice_b on po_invoice_b.pk_invoice = po_invoice.pk_invoice ");
    sqlBuilder
        .append("inner join po_order on po_order.pk_order = po_invoice_b.pk_order ");
    sqlBuilder
        .append("inner join po_order_b on po_order_b.pk_order_b = po_invoice_b.pk_order_b ");
    sqlBuilder
        .append("inner join org_stockorg on org_stockorg.pk_stockorg = po_order_b.pk_arrvstoorg ");
    sqlBuilder
        .append("inner join bd_countryzone on bd_countryzone.pk_country = po_invoice.csendcountryid");
    sqlBuilder.append(" where ");
    sqlBuilder.append(this.getWhereSqlByParam(mnReportQueryParam));
    return sqlBuilder.toString();
  }

  /**
   * 根据公司查找库存组织
   * 
   * @param pk_corp 公司ID
   * @return 库存组织数组
   */
  private String[] getStockOrgByCorp(String pk_corp) {
    SqlBuilder sqlBuilder = new SqlBuilder();
    sqlBuilder.append("select pk_stockorg ");
    sqlBuilder.append("from org_stockorg ");
    sqlBuilder
        .append("inner join org_orgs on org_stockorg.pk_financeorg = org_orgs.pk_org ");
    sqlBuilder.append(" where ");
    sqlBuilder.append("pk_corp ", pk_corp);

    DataAccessUtils tool = new DataAccessUtils();
    String[] ret =
        tool.query(sqlBuilder.toString()).toOneDimensionStringArray();
    if (ret.length == 0) {
      return new String[] {
        ""
      };
    }
    return ret;
  }

  /**
   * 获取sql中的where部分。
   * 
   * @param mnReportQueryParam 报表传入的查询参数。
   * @return sql中的where部分。
   */
  private String getWhereSqlByParam(MNReportQueryParam mnReportQueryParam) {
    SqlBuilder sqlBuilder = new SqlBuilder();
    sqlBuilder.append("po_invoice_b.dr = 0 and ");
    sqlBuilder.append("isnull(po_invoice_b.pk_order, '~') <> '~' ");

    // 收货国等于报告国，发货国不等于收货国，并且发货国必须是欧盟国家
    sqlBuilder.append(" and ");
    sqlBuilder.append(" isnull(po_invoice.crececountryid, '~')", " = ",
        mnReportQueryParam.getCreportcountryid());
    sqlBuilder.append(" and ");
    sqlBuilder
        .append("isnull(po_invoice.csendcountryid, '~') <> isnull(po_invoice.crececountryid, '~')");
    sqlBuilder.append(" and bd_countryzone.iseucountry = 'Y'");

    sqlBuilder.append(" and ");
    sqlBuilder
        .append("org_stockorg.pk_financeorg = po_order_b.pk_psfinanceorg");
    sqlBuilder.append(" and ");
    sqlBuilder.append(new IDExQueryBuilder(PUTempTable.tmp_po_25_10.name())
        .buildSQL("po_order_b.pk_arrvstoorg",
            this.getStockOrgByCorp(mnReportQueryParam.getCorpoid())));

    if (mnReportQueryParam.getDstartdate() != null) {
      sqlBuilder.append(" and po_invoice.dbilldate", ">=", mnReportQueryParam
          .getDstartdate().toString());
    }
    if (mnReportQueryParam.getDenddate() != null) {
      sqlBuilder.append(" and po_invoice.dbilldate", "<=", mnReportQueryParam
          .getDenddate().toString());
    }
    return sqlBuilder.toString();
  }
}
