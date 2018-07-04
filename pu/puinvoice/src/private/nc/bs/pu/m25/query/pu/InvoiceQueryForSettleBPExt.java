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
 * Ϊ�����ṩ��ѯ��BP��չ
 * 
 * @since 6.0
 * @version 2011-2-28 ����10:15:58
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
    // �Ƿ��������÷�Ʊ���Ƿ��ѯ���÷�Ʊ֮���й�ϵ
    // �Ƿ���÷�Ʊ����Ϊ��ʱ����Ϊֻ����÷�Ʊ�������÷�Ʊ�����ܹ������÷�Ʊ����ʱ��ѯ�������÷�Ʊ������������
    // �Ƿ���÷�Ʊ����Ϊ��ʱ����Ϊ������÷�Ʊ��˵��ͬʱҲ���ܲ�������÷�Ʊ����ʱ��ѯ�������÷�Ʊ������������
    // ֻ���Ƿ���÷�Ʊ����Ϊȫ�����ա�δ���ã�ʱ����ѯ�������÷�Ʊ��������
    boolean bfee = null == qcfee || null == qcfee.getValues()[0];
    if (null == qc || !ValueUtils.getBoolean(qc.getValues()[0]) || !bfee) {
      return;
    }
    qc.setValues(new String[] {
      UFBoolean.FALSE.toString()
    });
    // �ȵ����й������÷�Ʊ�ķ��Ͻ���Ĳ�ѯSQL
    String rfqs = this.bp.getSQL(this.createRelaFeeQueryScheme(UIQs));
    // ����ѯ�������÷�Ʊ��SQL�Ͳ�ѯ�Ի����ѯSQL��select���ֱ���һ��
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
    // ���������÷�Ʊ�Ĳ�ѯsql�ӵ���ѯģ��SQL�ĺ��棬����������ѯ
    UISql.append(" union all ");
    UISql.append(addSql.toString());
  }

  /**
   * ����һ���������÷�Ʊ�Ĳ�ѯscheme
   * 
   * @param UIQs ǰ̨��ѯģ�������queryscheme
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
