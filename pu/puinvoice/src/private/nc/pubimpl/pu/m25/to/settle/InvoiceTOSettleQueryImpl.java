package nc.pubimpl.pu.m25.to.settle;

import nc.bs.pu.m25.query.pu.InvoiceQueryForSettleBP;
import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleQuery;
import nc.pubitf.pu.m25.pu.settle.InvoiceQueryResultFor27;
import nc.pubitf.pu.m25.to.settle.IInvoiceTOSettleQuery;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.enumeration.InvoiceQryFieldCode;
import nc.vo.pu.m25.enumeration.InvoiceRowType;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;

/**
 * 为内部交易费用结算，查询采购费用发票的服务实现
 * 
 * @since 6.0
 * @version 2011-1-24 下午08:26:58
 * @author zhyhang
 */
public class InvoiceTOSettleQueryImpl implements IInvoiceTOSettleQuery {

  @Override
  public FeeDiscountSettleVO[] query(IQueryScheme qs) throws BusinessException {
    try {
      // 设置一些内部交易结算
      this.setTOSettleInfo(qs);
      InvoiceQueryResultFor27 invoices =
          new InvoiceQueryForSettleBP().queryByWhereSql(qs);
      return (FeeDiscountSettleVO[]) ArrayUtils.addAll(invoices
          .getDiscountInvoices(), invoices.getFeeInvoices());
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private void setTOSettleInfo(IQueryScheme qs) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(qs);
    String halias = qsp.getMainTableAlias();
    String balias =
        qsp.getTableAliasOfAttribute(InvoiceQryFieldCode.invoicebid.code());
    FromWhereSQLImpl fromWhereSQL =
        (FromWhereSQLImpl) qs.getTableJoinFromWhereSQL();

    String where = fromWhereSQL.getWhere();
    SqlBuilder whr = new SqlBuilder();
    whr.append(where);
    whr.append(" and ");
    // 拼上结算财务组织
    whr.append(halias);
    whr.append(".");
    whr.append(InvoiceHeaderVO.PK_ORG, (String) qs
        .get(IInvoiceTOSettleQuery.QS_SETTLE_FIORG_KEY));
    whr.append(" and ");
    // 只查询费用折扣发票
    whr.append(balias);
    whr.append(".");
    whr.append(InvoiceItemVO.FROWTYPE, new int[] {
      InvoiceRowType.DISCOUNT_ROW, InvoiceRowType.FEE_ROW
    });
    fromWhereSQL.setWhere(whr.toString());
    // 查询采购发票（非消耗汇总）
    qs.put(IInvoiceSettleQuery.QRY_TYPE_KEY, IInvoiceSettleQuery.QRY_TYPE_PO);
  }
}
