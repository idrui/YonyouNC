package nc.bs.pu.mpp.strategy.orderaspraybill;

import nc.bs.pu.mpp.strategy.praybill.PrayBillBatchFetcher;
import nc.pubitf.uapbd.CurrencyRateUtil;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pl.tbb.PlBillBusiSysReg;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.tbb.TbbTempTableSqlBuilder;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-6-10 上午10:13:37
 * @author wuxla
 */

public class OrderCloseAsPrayBillBatchFetcher extends PrayBillBatchFetcher {
  private String exebilltype;

  public void setExebilltype(String exebilltype) {
    this.exebilltype = exebilltype;
  }

  private UFDouble toPrayBillCurrcyMny(IRowSet rowset, String currencytypeid,
      UFDouble value) {
    if (MathTool.compareTo(value, UFDouble.ZERO_DBL) == 0) {
      return UFDouble.ZERO_DBL;
    }
    UFDouble result = UFDouble.ZERO_DBL;
    CurrencyRateUtil util =
        CurrencyRateUtil.getInstanceByOrg(rowset.getString(0));
    try {
      // 折算汇率，现在为当前时间的汇率，需要确认
      result =
          MathTool.add(result, MathTool.nvl(util.getAmountByOpp(
              rowset.getString(1), currencytypeid, value, null,
              TimeUtils.getsrvBaseDate())));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return result;
  }

  @Override
  protected String getReadyQuerySql(NtbParamVO seed, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    tbbuilder.buildSql(this.getFieldMap(seed), params);
    StringBuilder select = new StringBuilder();
    if (PuBillBusiSysReg.NNUM.equals(type)) {
      select.append("select 0-sum(ob.nnum) ");
      tbbuilder.setStartIndex(1);
    }
    else if (PlBillBusiSysReg.NTAXMNY.equals(type)) {
      select
          .append("select b.pk_org,ob.corigcurrencyid,0,0-sum(isnull(ob.norigtaxmny,0)) ");
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
    where.append(" where h.dr = 0 and  b.dr = 0 and ob.dr=0");
    where.append(" and h.bislatest = '" + UFBoolean.TRUE.toString() + "'");
    where.append(" and h.fbillstatus = " + POEnumBillStatus.APPROVE.toInt());
    // where.append(" and b.browclose='" + UFBoolean.TRUE.toString() + "'");
    where.append(this.getWhereByExecBilltype());
    // 拼接where部分
    where.append(tbbuilder.getWherepart(true));
    StringBuilder group = new StringBuilder();
    group.append(" group by ");
    if (PlBillBusiSysReg.NTAXMNY.equals(type)) {
      group.append(" b.pk_org,ob.corigcurrencyid, ");
    }
    // 拼接group部分
    group.append(tbbuilder.getGrouppart(false));
    return select.append(from.toString()).append(where.toString())
        .append(group.toString()).toString();
  }

  @Override
  protected UFDouble[][] getReadyResult(IRowSet rowset, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    UFDouble[][] result = new UFDouble[params.length][4];
    while (rowset.next()) {
      for (int i = 0; i < params.length; i++) {
        if (null != params[i]) {
          if (PlBillBusiSysReg.NNUM.equals(type)) {
            if (tbbuilder.isMatch(rowset, params[i])) {
              UFDouble value = rowset.getUFDouble(0);
              result[i][0] = value;
              result[i][1] = value;
              result[i][2] = value;
              result[i][3] = value;
            }
          }
          else {
            if (tbbuilder.isMatch(rowset, params[i])) {
              UFDouble orderValue = rowset.getUFDouble(3);

              UFDouble orderLocalValue =
                  this.toPrayBillCurrcyMny(rowset, params[i].getPk_currency(),
                      orderValue);

              result[i][0] = orderLocalValue;
              result[i][1] = orderLocalValue;
              result[i][2] = orderLocalValue;
              result[i][3] = orderLocalValue;
            }
          }
        }
      }
    }
    return result;
  }

  protected String getWhereByExecBilltype() {
    if (POBillType.Order.getCode().equals(this.exebilltype)) {
      return " and ob.fisactive = " + EnumActive.CLOSE.value();
    }
    else if (POBillType.Arrive.getCode().equals(this.exebilltype)) {
      return " and ob.barriveclose='" + UFBoolean.TRUE.toString() + "'"
          + " and ob.fisactive <> " + EnumActive.REVISEHISTORY.value();
    }
    else if (POBillType.Invoice.getCode().equals(this.exebilltype)) {
      return " and ob.binvoiceclose='" + UFBoolean.TRUE.toString() + "'"
          + " and ob.fisactive <> " + EnumActive.REVISEHISTORY.value();
    }
    else if (ICBillType.PurchaseIn.getCode().equals(this.exebilltype)) {
      return " and ob.bstockclose='" + UFBoolean.TRUE.toString() + "'"
          + " and ob.fisactive <> " + EnumActive.REVISEHISTORY.value();
    }
    return "";
  }

}
