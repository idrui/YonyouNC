package nc.bs.pu.m25.query.pu;

import java.util.HashMap;
import java.util.Map;

import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleQuery;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQuerySQLGenerator;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.enumeration.InvoiceQryFieldCode;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QueryConstants;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * 为结算提供查询的BP扩展
 * 
 * @since 6.0
 * @version 2011-2-28 下午10:15:58
 * @author zhaoyha
 */
public class InvoiceQueryForSettleBPExt {

  private InvoiceQueryForSettleBP bp;

  /**
   * @param bp
   */
  public InvoiceQueryForSettleBPExt(InvoiceQueryForSettleBP bp) {
    super();
    this.bp = bp;
  }

  public void appendRelaFeeQuerySQL(IQueryScheme UIQs, SqlBuilder UISql) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(UIQs);
    QueryCondition qc =
        qsp.getQueryCondition(InvoiceQryFieldCode.brelafeeinclude.code());
    QueryCondition qcfee =
        qsp.getQueryCondition(InvoiceQryFieldCode.bfeeinvoice.code());
    // 是否查关联费用发票与是否查询费用发票之间有关系
    // 是否费用发票条件为是时，因为只查费用发票，但费用发票本身不能关联费用发票，此时查询关联费用发票条件不起作用
    // 是否费用发票条件为否时，因为不查费用发票，说明同时也不能查关联费用发票，此时查询关联费用发票条件不起作用
    // 只有是否费用发票条件为全部（空、未设置）时，查询关联费用发票才起作用
    boolean bfee = null == qcfee || null == qcfee.getValues()[0];
    if (null == qc || !ValueUtils.getBoolean(qc.getValues()[0]) || !bfee) {
      return;
    }
    qc.setValues(new String[] {
      UFBoolean.FALSE.toString()
    });
    // 等到所有关联费用发票的符合结算的查询SQL
    String rfqs = this.bp.getSQL(this.createRelaFeeQueryScheme(UIQs));
    // 将查询关联费用发票的SQL和查询对话框查询SQL的select部分保持一致
    SqlBuilder addSql = new SqlBuilder();
    addSql.append("select a." + InvoiceItemVO.PK_INVOICE_B);
    addSql.append(",a." + InvoiceItemVO.FROWTYPE);
    addSql.append(",a." + InvoiceHeaderVO.PK_INVOICE);
    addSql.append(",a." + InvoiceHeaderVO.PK_PARENTINVOICE);
    addSql.append(",a." + InvoiceHeaderVO.FINVOICETYPE);
    addSql.append(" from (" + rfqs + ") a inner join ");
    addSql.append("(" + UISql.toString() + ") b on a."
        + InvoiceHeaderVO.PK_PARENTINVOICE);
    addSql.append("=b." + InvoiceHeaderVO.PK_INVOICE);
    // 将关联费用发票的查询sql接到查询模板SQL的后面，做成完整查询
    UISql.append(" union all ");
    UISql.append(addSql.toString());
  }

  /**
   * 构造一个关联费用发票的查询scheme
   * 
   * @param UIQs 前台查询模板过来的queryscheme
   * @return
   */
  private IQueryScheme createRelaFeeQueryScheme(IQueryScheme UIQs) {
    String beanid = (String) UIQs.get(QueryConstants.BEAN_ID);
    QueryScheme rfqs = new QueryScheme();
    rfqs.put(QueryConstants.BEAN_ID, beanid);
    SqlBuilder from = new SqlBuilder();
    from.append(PUEntity.M25_H_TAB + " " + PUEntity.M25_H_TAB);
    SqlBuilder where = new SqlBuilder();
    where.append("isnull(" + PUEntity.M25_H_TAB + "." + InvoiceHeaderVO.BFEE);
    where.append(",'" + UFBoolean.FALSE);
    where.append("')", UFBoolean.TRUE);
    Map<String, String> attMap = new HashMap<String, String>();
    attMap.put(IQuerySQLGenerator.DEFAULT_KEY, PUEntity.M25_H_TAB);
    FromWhereSQLImpl fwsql =
        new FromWhereSQLImpl(from.toString(), where.toString());
    fwsql.setAttrpath_alias_map(attMap);
    rfqs.putTableJoinFromWhereSQL(fwsql);
    rfqs.put(IInvoiceSettleQuery.QRY_TYPE_KEY,
        UIQs.get(IInvoiceSettleQuery.QRY_TYPE_KEY));
    rfqs.put(QueryConstants.QUERY_CONDITION,
        UIQs.get(QueryConstants.QUERY_CONDITION));
    return rfqs;
  }

}
