package nc.bs.pu.mpp.strategy.praybill;

import java.util.HashMap;
import java.util.Map;

import nc.bs.pu.mpp.strategy.AbstractBatchFetcher;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pl.tbb.PlBillBusiSysReg;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.tbb.TbbTempTableSqlBuilder;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-4-2 下午01:47:54
 * @author yinfy
 */

public class PrayBillBatchFetcher extends AbstractBatchFetcher {
  private Map<String, String> allFieldMap = new HashMap<String, String>();

  public PrayBillBatchFetcher() {
    // 币种
    this.allFieldMap.put(TbbTempTableSqlBuilder.CURRENCY, "h.ccurrencyid");
    // 需求公司组织
    this.allFieldMap.put(DocConst.CORPSTOCKORG, "b.pk_org");
    // 采购组织
    this.allFieldMap.put(DocConst.PURCHASEORG, "b.pk_purchaseorg");
    // 库存组织
    this.allFieldMap.put(DocConst.STOCKORG, "b.pk_org");
    // 部门
    this.allFieldMap.put(DocConst.BDDEPT, "b.pk_reqdept");
    // 物料分类
    this.allFieldMap.put(DocConst.MATCLASS, "inv.pk_marbasclass");
    // 物料
    this.allFieldMap.put(DocConst.MATERIAL, "b.pk_material");
    // 物料OID
    this.allFieldMap.put(DocConst.MATERIALOID, "b.pk_srcmaterial");
    // 项目档案
    this.allFieldMap.put(DocConst.BDPROJECT, "b.cprojectid");
    // 单据审批日期
    this.allFieldMap.put(TbbTempTableSqlBuilder.DPERIOD, "h.taudittime");
  }

  @Override
  protected String getExecQuerySql(NtbParamVO seed, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    return null;
  }

  @Override
  protected Map<String, String> getFieldMap(NtbParamVO seed) {
    return this.allFieldMap;
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
    // where.append(" and isnull(b.naccumulatenum,0) = 0");
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
}
