package nc.bs.pu.mpp.strategy.orderaspraybill;

import nc.vo.bd.material.MaterialVO;
import nc.vo.pl.tbb.PlBillBusiSysReg;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.tbb.TbbTempTableSqlBuilder;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-4-2 下午03:58:26
 * @author yinfy
 */

public class InvoiceAsPrayBillBatchFetcher extends OrderAsPrayBillBatchFetcher {
  @Override
  protected String getExecQuerySql(NtbParamVO seed, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    tbbuilder.buildSql(this.getFieldMap(seed), params);
    StringBuilder select = new StringBuilder();
    if (PuBillBusiSysReg.NNUM.equals(type)) {
      select.append("select sum(ob.naccuminvoicenum) ");
      tbbuilder.setStartIndex(1);
    }
    else if (PlBillBusiSysReg.NTAXMNY.equals(type)) {
      select
          .append("select b.pk_org,ob.ccurrencyid,sum(coalesce(naccuminvoicemny,0)) ");
      tbbuilder.setStartIndex(3);
    }
    // 拼接select部分
    select.append(tbbuilder.getSelectpart(true));
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
    // where.append(" and b.browclose='" + UFBoolean.TRUE.toString() + "'");
    where.append(" and ob.binvoiceclose='" + UFBoolean.TRUE.toString() + "'");
    where.append(" and ob.fisactive <> " + EnumActive.REVISEHISTORY.value());
    // 拼接where部分
    where.append(tbbuilder.getWherepart(true));
    StringBuilder group = new StringBuilder();
    group.append(" group by ");
    if (PlBillBusiSysReg.NTAXMNY.equals(type)) {
      group.append(" b.pk_org,ob.ccurrencyid, ");
    }
    // 拼接group部分
    group.append(tbbuilder.getGrouppart(false));
    return select.append(from.toString()).append(where.toString())
        .append(group.toString()).toString();
  }
}
