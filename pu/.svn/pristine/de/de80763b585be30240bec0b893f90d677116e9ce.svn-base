package nc.bs.pu.mpp.strategy.orderaspraybill;

import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.bd.material.MaterialVO;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m45.entity.PurchaseInViewVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.scmpub.tbb.TbbTempTableSqlBuilder;
import nc.vo.tb.obj.NtbParamVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-7-1 下午04:47:29
 * @author wuxla
 */

public class PurchaseinAsPrayBillBatchLinkQuery extends
    OrderAsPrayBillBatchFetcher {

  public PurchaseInViewVO[] execLinkQuery45(NtbParamVO vo) {
    TbbTempTableSqlBuilder tbbuilder = new TbbTempTableSqlBuilder();
    String sql = this.getExecQuerySql(vo, new NtbParamVO[] {
      vo
    }, null, tbbuilder);
    IRowSet rowset = new DataAccessUtils().query(sql);
    String[] bids = rowset.toOneDimensionStringArray();
    if (ArrayUtils.isEmpty(bids)) {
      return null;
    }
    String bidsql = this.createBidSql(bids);
    EfficientViewQuery<PurchaseInViewVO> query =
        new EfficientViewQuery<PurchaseInViewVO>(PurchaseInViewVO.class);
    PurchaseInViewVO[] views = query.query(bidsql);
    return views;
  }

  private String createBidSql(String[] bids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_rpt_12.name());
    String hidCond =
        builder.buildSQL(" and " + MetaNameConst.CFIRSTBILLBID, bids);
    return hidCond.toString();
  }

  @Override
  protected String getExecQuerySql(NtbParamVO seed, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    tbbuilder.buildSql(this.getFieldMap(seed), params);
    StringBuilder select = new StringBuilder();
    select.append("select ob.pk_order_b ");
    // 拼接select部分
    StringBuilder from = new StringBuilder();
    from.append(" from po_praybill_b b inner join po_praybill h on ");
    from.append(" b.pk_praybill = h.pk_praybill ");
    from.append(" inner join po_order_b ob on ob.csourcebid=b.pk_praybill_b ");
    // 包含物料分类
    if (this.isIncludeField(seed, DocConst.MATCLASS)) {
      from.append(" left outer join " + MaterialVO.getDefaultTableName()
          + " inv on  ");
      from.append(" b.pk_material=inv.pk_material ");
    }
    // 拼接from部分
    from.append(tbbuilder.getFrompart());
    StringBuilder where = new StringBuilder();
    where.append(" where h.dr = 0 and  b.dr = 0 and ob.dr=0");
    where.append(" and h.bislatest = '" + UFBoolean.TRUE.toString() + "'");
    // where.append(" and h.fbillstatus = " + POEnumBillStatus.APPROVE.toInt());
    where.append(" and ob.bstockclose='" + UFBoolean.TRUE.toString() + "'");
    where.append(" and ob.fisactive <> " + EnumActive.REVISEHISTORY.value());
    // 拼接where部分
    where.append(tbbuilder.getWherepart(true));
    // 拼接group部分
    return select.append(from.toString()).append(where.toString()).toString();
  }
}
