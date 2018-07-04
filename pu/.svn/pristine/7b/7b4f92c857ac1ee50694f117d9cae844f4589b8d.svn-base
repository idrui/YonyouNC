package nc.pubimpl.pu.tbb.strategy.invoicebill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.entity.InvoiceViewVO;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.tbb.TbbSplitParamvoUtil;
import nc.vo.scmpub.tbb.TbbTempTableSqlBuilder;
import nc.vo.tb.obj.NtbParamVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-6-20 上午10:37:35
 * @author wuxla
 */

public class InvoiceBillLinkQueryDMO extends InvoiceBillGetDataBatchDMO {

  public InvoiceVO[] linkQuery25ForTbb(NtbParamVO[] params) {
    TbbSplitParamvoUtil util = new TbbSplitParamvoUtil();
    Map<String, NtbParamVO[]> map = util.split(params);
    List<InvoiceVO> list = new ArrayList<InvoiceVO>();
    for (Map.Entry<String, NtbParamVO[]> ite : map.entrySet()) {
      NtbParamVO seed = this.getSeed(ite.getValue());
      if (seed == null) {
        continue;
      }
      TbbTempTableSqlBuilder tbbuilder = new TbbTempTableSqlBuilder();
      String sql = this.getExecQuerySql(seed, params, null, tbbuilder);
      IRowSet rowset = new DataAccessUtils().query(sql);
      String[] bids = rowset.toOneDimensionStringArray();
      if (ArrayUtils.isEmpty(bids)) {
        continue;
      }
      ViewQuery<InvoiceViewVO> query =
          new ViewQuery<InvoiceViewVO>(InvoiceViewVO.class);
      InvoiceViewVO[] views = query.query(bids);
      InvoiceVO[] vos = InvoiceViewVO.getInvoiceVO(views);
      list.addAll(Arrays.asList(vos));
    }

    return list.toArray(new InvoiceVO[list.size()]);
  }

  @Override
  protected String getExecQuerySql(NtbParamVO seed, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    tbbuilder.buildSql(this.getFieldMap(seed), params);
    StringBuilder select = new StringBuilder();
    select.append("select distinct b.pk_invoice_b ");
    // tbbuilder.setStartIndex(1);
    // // 拼接select部分
    // select.append(tbbuilder.getSelectpart(true));
    StringBuilder from = new StringBuilder();
    from.append(" from po_invoice_b b inner join po_invoice h on ");
    from.append(" b.pk_invoice = h.pk_invoice ");

    // 包含物料分类
    if (this.isIncludeField(seed, DocConst.MATCLASS)) {
      from.append(" left join " + MaterialVO.getDefaultTableName()
          + " inv on  ");
      from.append(" b.pk_material=inv.pk_material ");
    }
    // 包含供应商分类或者供应商地区分类
    if (this.isIncludeField(seed, DocConst.BDVENDORCLASS)
        || this.isIncludeField(seed, DocConst.BDARERCL)) {
      from.append(" left join " + new SupplierVO().getTableName()
          + " vendor on  ");
      from.append(" b.pk_supplier = vendor.pk_supplier ");
    }
    // 拼接from部分
    from.append(tbbuilder.getFrompart());
    SqlBuilder where = new SqlBuilder();
    where.append(" where ");
    where.append(" h.dr = 0 ");
    where.append(" and ");
    where.append(" b.dr = 0 ");
    if (!seed.isUnInure()) {
      where.append(" and ");
      if("UFIND".equals(params[0].getMethodCode())){
        where.append(" h.fbillstatus", APPROVE);
      }else if("PREFIND".equals(params[0].getMethodCode())){
        where.append(" h.fbillstatus", NOAPPROVE);
      }
    }
    // 拼接where部分
    where.append(tbbuilder.getWherepart(true));
    return select.append(from.toString()).append(where.toString()).toString();
  }
}
