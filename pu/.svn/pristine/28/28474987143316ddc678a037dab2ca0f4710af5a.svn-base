package nc.bs.pu.mpp.strategy.orderbill;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.pubitf.pu.m25.pu.pub.IQueryInvoice;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.tbb.TbbTempTableSqlBuilder;
import nc.vo.tb.obj.NtbParamVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-6-23 下午01:40:37
 * @author wuxla
 */

public class InvoiceBatchLinkQuery extends InvoiceBatchFetcher {
  public InvoiceVO[] execLinkQuery25(NtbParamVO vo) {
    TbbTempTableSqlBuilder tbbuilder = new TbbTempTableSqlBuilder();
    String sql = this.getExecQuerySql(vo, new NtbParamVO[] {
      vo
    }, null, tbbuilder);
    IRowSet rowset = new DataAccessUtils().query(sql);
    String[] bids = rowset.toOneDimensionStringArray();
    if (ArrayUtils.isEmpty(bids)) {
      return null;
    }
    IQueryInvoice service = NCLocator.getInstance().lookup(IQueryInvoice.class);
    try {
      return service.queryInvoiceByOrderBid(bids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  @Override
  protected String getExecQuerySql(NtbParamVO seed, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    tbbuilder.buildSql(this.getFieldMap(seed), params);
    StringBuilder select = new StringBuilder();
    select.append("select b.pk_order_b ");
    StringBuilder from = new StringBuilder();
    from.append(" from po_order_b b inner join po_order h on ");
    from.append(" b.pk_order = h.pk_order ");
    // 包含物料分类
    if (this.isIncludeField(seed, DocConst.MATCLASS)) {
      from.append(" left outer join " + MaterialVO.getDefaultTableName()
          + " inv on  ");
      from.append(" b.pk_material=inv.pk_material ");
    }
    // 拼接from部分
    from.append(tbbuilder.getFrompart());
    StringBuilder where = new StringBuilder();
    where.append(" where h.dr = 0 and  b.dr = 0 ");
    where.append(" and h.bislatest = '" + UFBoolean.TRUE.toString() + "'");
    where.append(" and h.forderstatus = " + POEnumBillStatus.APPROVE.toInt());
    where.append(" and b.binvoiceclose='" + UFBoolean.TRUE.toString() + "'");
    // 拼接where部分
    where.append(tbbuilder.getWherepart(true));
    return select.append(from.toString()).append(where.toString()).toString();
  }

}
