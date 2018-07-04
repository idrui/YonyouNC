package nc.bs.pu.mpp.strategy.orderbill;

import nc.pubitf.uapbd.CurrencyRateUtil;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pl.tbb.PlBillBusiSysReg;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.tbb.TbbTempTableSqlBuilder;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-4-2 下午04:28:10
 * @author yinfy
 */

public class InvoiceBatchFetcher extends OrderBatchFetcher {
  private UFDouble toOriginCurrcyMny(IRowSet rowset, String currencytypeid) {
    UFDouble result = UFDouble.ZERO_DBL;

    CurrencyRateUtil util =
        CurrencyRateUtil.getInstanceByOrg(rowset.getString(0));
    try {
      // 折算汇率，现在为当前时间的汇率，需要确认
      result =
          MathTool.add(result, MathTool.nvl(util.getAmountByOpp(
              rowset.getString(1), currencytypeid, rowset.getUFDouble(2), null,
              TimeUtils.getsrvBaseDate())));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return result;
  }

  @Override
  protected String getExecQuerySql(NtbParamVO seed, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    tbbuilder.buildSql(this.getFieldMap(seed), params);
    StringBuilder select = new StringBuilder();
    if (PuBillBusiSysReg.NNUM.equals(type)) {
      select.append("select sum(b.naccuminvoicenum) ");
      tbbuilder.setStartIndex(1);
    }
    else if (PlBillBusiSysReg.NTAXMNY.equals(type)) {
      // 依次是全局本币，集团本币，组织本币,原币值
      select
          .append("select b.pk_psfinanceorg,b.ccurrencyid,sum(coalesce(naccuminvoicemny,0)) ");
      tbbuilder.setStartIndex(3);
    }
    // 拼接select部分
    select.append(tbbuilder.getSelectpart(true));
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
    StringBuilder group = new StringBuilder();
    group.append(" group by ");
    if (PlBillBusiSysReg.NTAXMNY.equals(type)) {
      group.append(" b.pk_psfinanceorg,b.ccurrencyid,");
    }
    // 拼接group部分
    group.append(tbbuilder.getGrouppart(false));
    return select.append(from.toString()).append(where.toString())
        .append(group.toString()).toString();
  }

  @Override
  protected UFDouble[][] getExecResult(IRowSet rowset, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    UFDouble[][] result = new UFDouble[params.length][4];
    while (rowset.next()) {
      for (int i = 0; i < params.length; i++) {
        if (null != params[i]) {
          if (tbbuilder.isMatch(rowset, params[i])) {
            if (PlBillBusiSysReg.NNUM.equals(type)) {
              result[i][0] = rowset.getUFDouble(0);
              result[i][1] = rowset.getUFDouble(0);
              result[i][2] = rowset.getUFDouble(0);
              result[i][3] = rowset.getUFDouble(0);
            }
            else {
              UFDouble mny =
                  this.toOriginCurrcyMny(rowset, params[i].getPk_currency());
              result[i][0] = UFDouble.ZERO_DBL;
              result[i][1] = UFDouble.ZERO_DBL;
              result[i][2] = MathTool.add(result[i][2], mny);
              result[i][3] = MathTool.add(result[i][3], mny);
            }
          }
        }
      }
    }
    return result;
  }
}
