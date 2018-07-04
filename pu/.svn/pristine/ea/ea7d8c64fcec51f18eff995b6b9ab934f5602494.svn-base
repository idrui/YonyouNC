package nc.bs.pu.mpp.strategy.praybill;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.scmpub.tbb.TbbTempTableSqlBuilder;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-6-23 上午09:54:59
 * @author wuxla
 */

public class PrayBillBatchLinkQuery extends PrayBillBatchFetcher {

  public String[] readyLinkQuery20ForTbb(NtbParamVO vo) {
    TbbTempTableSqlBuilder tbbuilder = new TbbTempTableSqlBuilder();
    String sql = this.getReadyQuerySql(vo, new NtbParamVO[] {
      vo
    }, null, tbbuilder);
    IRowSet rowset = new DataAccessUtils().query(sql);
    String[] bids = rowset.toOneDimensionStringArray();
    return bids;
  }

  @Override
  protected String getReadyQuerySql(NtbParamVO seed, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    tbbuilder.buildSql(this.getFieldMap(seed), params);
    StringBuilder select = new StringBuilder();
    select.append("select distinct b.pk_praybill_b ");
    // 拼接select部分
    StringBuilder from = new StringBuilder();
    from.append(" from po_praybill_b b inner join po_praybill h on ");
    from.append(" b.pk_praybill = h.pk_praybill ");

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
    where.append(" and h.fbillstatus = " + POEnumBillStatus.APPROVE.toInt());
    where.append(" and h.bsctype = 'N' ");
    // 拼接where部分
    where.append(tbbuilder.getWherepart(true));

    return select.append(from.toString()).append(where.toString()).toString();
  }
}
