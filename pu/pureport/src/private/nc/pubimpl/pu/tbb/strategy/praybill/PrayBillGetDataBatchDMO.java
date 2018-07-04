package nc.pubimpl.pu.tbb.strategy.praybill;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.pubimpl.pu.tbb.strategy.AbstractGetDataBatchDMO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.tbb.TbbTempTableSqlBuilder;
import nc.vo.tb.obj.NtbParamVO;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2011-3-29 上午09:23:56
 * @author yinfy
 */

public class PrayBillGetDataBatchDMO extends AbstractGetDataBatchDMO {
  private Map<String, String> allFieldMap = new HashMap<String, String>();

  public PrayBillGetDataBatchDMO() {
    this.allFieldMap.put(DocConst.STOCKORG, "h.pk_org");
    this.allFieldMap.put(DocConst.BDDEPT, "b.pk_reqdept");
    this.allFieldMap.put(DocConst.BDPROJECT, "b.cprojectid");
    this.allFieldMap.put(DocConst.BDPSN, "h." + PraybillHeaderVO.PK_PLANPSN);
    this.allFieldMap.put(DocConst.MATCLASS, "inv.pk_marbasclass");
    this.allFieldMap.put(DocConst.MATERIAL, "b.pk_material");
    this.allFieldMap.put(DocConst.MATERIALOID, "b.pk_srcmaterial");
  }

  @Override
  protected String getExecQuerySql(NtbParamVO seed, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    tbbuilder.buildSql(this.getFieldMap(seed), params);
    // select部分
    StringBuilder select = this.getSelectPart(type, tbbuilder);
    // from部分
    StringBuilder from = this.getFromPart(seed, tbbuilder);
    // where部分
    String where = this.getWherePart(seed, tbbuilder);
    // group部分
    StringBuilder group = this.getGroupPart(tbbuilder);
    return select.append(from.toString()).append(where.toString())
        .append(group.toString()).toString();
  }

  private StringBuilder getGroupPart(TbbTempTableSqlBuilder tbbuilder) {
    StringBuilder group = new StringBuilder();
    group.append(" group by ");
    // 拼接group部分
    group.append(tbbuilder.getGrouppart(false));
    return group;
  }

  private String getWherePart(NtbParamVO seed,
      TbbTempTableSqlBuilder tbbuilder) {
    SqlBuilder where = new SqlBuilder();
    where.append(" where ");
    where.append(" h.dr = 0 ");
    where.append(" and ");
    where.append(" b.dr = 0 ");
    where.append(" and h.bislatest = '" + UFBoolean.TRUE.toString() + "'");
    if (!seed.isUnInure()) {
      where.append(" and ");
      if(UFIND.equals(seed.getMethodCode())){
        where.append(" h.fbillstatus", APPROVE);
      }else if(PREFIND.equals(seed.getMethodCode())){
        where.append(" h.fbillstatus", NOAPPROVE);
      }
    }
    // 拼接where部分
    where.append(tbbuilder.getWherepart(true));
    return where.toString();
  }

  private StringBuilder getFromPart(NtbParamVO seed,
      TbbTempTableSqlBuilder tbbuilder) {
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
    return from;
  }

  private StringBuilder getSelectPart(String type,
      TbbTempTableSqlBuilder tbbuilder) {
    StringBuilder select = new StringBuilder();
    if (PuBillBusiSysReg.NNUM.equals(type)) {
      select.append("select sum(b.nnum) ");
      tbbuilder.setStartIndex(1);
    }
    // 拼接select部分
    select.append(tbbuilder.getSelectpart(true));
    return select;
  }

  @Override
  protected Map<String, String> getFieldMap(NtbParamVO seed) {
    Map<String, String> result = new HashMap<String, String>();
    // 日期区间映射
    if (!StringUtils.isEmpty(seed.getDateType())) {
      if (PuBillBusiSysReg.DBILLDATE.equals(seed.getDateType())) {
        result.put(TbbTempTableSqlBuilder.DPERIOD, "h.dbilldate");
      }
      else if (PuBillBusiSysReg.DAUDITDATT.equals(seed.getDateType())) {
        result.put(TbbTempTableSqlBuilder.DPERIOD, "h.taudittime");
      }
    }
    if (ArrayUtils.isEmpty(seed.getBusiAttrs())) {
      return result;
    }
    for (int i = 0; i < seed.getBusiAttrs().length; i++) {
      result.put(seed.getBusiAttrs()[i],
          this.allFieldMap.get(seed.getBusiAttrs()[i]));
    }
    return result;
  }

  /**
   * 判断 参数中是否包括其他档案，如果包括true
   * 
   * @param param
   * @return
   */
  @Override
  protected boolean haveOtherDoc(NtbParamVO param) {
    String[] docs =
        new String[] {
          DocConst.STOCKORG, DocConst.BDDEPT, DocConst.BDPSN,
          DocConst.MATCLASS, DocConst.MATERIAL, DocConst.MATERIALOID,
          DocConst.BDPROJECT
        };

    Set<String> set = new HashSet<String>();
    for (String doc : docs) {
      set.add(doc);
    }

    String[] busiAttrs = param.getBusiAttrs();
    for (String busiAttr : busiAttrs) {
      if (!set.contains(busiAttr)) {
        return true;
      }
    }
    return false;
  }
}
