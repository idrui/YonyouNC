package nc.bs.pu.mpp.strategy.orderaspraybill;

import nc.bs.pu.mpp.strategy.praybill.PrayBillBatchFetcher;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pl.tbb.PlBillBusiSysReg;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.tbb.TbbTempTableSqlBuilder;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-6-10 下午02:01:05
 * @author wuxla
 */

public class OrderOpenAsPrayBillBatchFetcher extends PrayBillBatchFetcher {
  private String exebilltype;

  public void setExebilltype(String exebilltype) {
    this.exebilltype = exebilltype;
  }

  @Override
  protected String getReadyQuerySql(NtbParamVO seed, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    tbbuilder.buildSql(this.getFieldMap(seed), params);
    StringBuilder select = new StringBuilder();
    if (PuBillBusiSysReg.NNUM.equals(type)) {
      select.append("select sum(b.nnum) ");
      tbbuilder.setStartIndex(1);
    }
    else if (PlBillBusiSysReg.NTAXMNY.equals(type)) {
      // 依次是全局本币，集团本币，组织本币,原币值
      select.append("select 0,0,sum(b.ntaxmny),sum(b.ntaxmny) ");
      tbbuilder.setStartIndex(4);
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
    where.append(" where h.dr = 0 and  b.dr = 0 and ob.dr = 0 ");
    where.append(" and h.bislatest = '" + UFBoolean.TRUE.toString() + "'");
    where.append(" and h.fbillstatus = " + POEnumBillStatus.APPROVE.toInt());
    where.append(this.getWhereByExecBilltype());
    // where.append(" and isnull(b.browclose,'N') = '"
    // + UFBoolean.FALSE.toString() + "'");
    // 拼接where部分
    where.append(tbbuilder.getWherepart(true));
    StringBuilder group = new StringBuilder();
    group.append(" group by ");
    // 拼接group部分
    group.append(tbbuilder.getGrouppart(false));

    return select.append(from.toString()).append(where.toString())
        .append(group.toString()).toString();
  }

  protected String getWhereByExecBilltype() {
    if (POBillType.Order.getCode().equals(this.exebilltype)) {
      return " and ob.fisactive = " + EnumActive.ACTIVE.value();
    }
    else if (POBillType.Arrive.getCode().equals(this.exebilltype)) {
      return " and ob.barriveclose<>'" + UFBoolean.TRUE.toString() + "'"
          + " and ob.fisactive <> " + EnumActive.REVISEHISTORY.value();
    }
    else if (POBillType.Invoice.getCode().equals(this.exebilltype)) {
      return " and ob.binvoiceclose<>'" + UFBoolean.TRUE.toString() + "'"
          + " and ob.fisactive <> " + EnumActive.REVISEHISTORY.value();
    }
    else if (ICBillType.PurchaseIn.getCode().equals(this.exebilltype)) {
      return " and ob.bstockclose<>'" + UFBoolean.TRUE.toString() + "'"
          + " and ob.fisactive <> " + EnumActive.REVISEHISTORY.value();
    }
    return "";
  }

}
