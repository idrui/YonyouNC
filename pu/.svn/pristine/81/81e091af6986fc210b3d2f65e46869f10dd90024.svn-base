package nc.pubimpl.pu.tbb.strategy.orderbill;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.pubimpl.pu.tbb.strategy.AbstractGetDataBatchDMO;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.tbb.TbbTempTableSqlBuilder;
import nc.vo.tb.obj.NtbParamVO;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2011-4-2 上午09:36:36
 * @author yinfy
 */

public class OrderBillGetDataBatchDMO extends AbstractGetDataBatchDMO {
  private Map<String, String> allFieldMap = new HashMap<String, String>();

  public OrderBillGetDataBatchDMO() {
    // 币种
    this.allFieldMap.put(TbbTempTableSqlBuilder.CURRENCY, "b.corigcurrencyid");
    // 库存组织
    this.allFieldMap.put(DocConst.STOCKORG, "b.pk_arrvstoorg");
    // 采购组织
    this.allFieldMap.put(DocConst.PURCHASEORG, "b.pk_org");
    // 结算财务组织
    this.allFieldMap.put(DocConst.FINANCEORG, "b.pk_psfinanceorg");
    // 部门
    this.allFieldMap.put(DocConst.BDDEPT, "h." + OrderHeaderVO.PK_DEPT);
    // 业务员
    this.allFieldMap.put(DocConst.BDPSN, "h.cemployeeid");
    // 物料分类
    this.allFieldMap.put(DocConst.MATCLASS, "inv.pk_marbasclass");
    // 物料
    this.allFieldMap.put(DocConst.MATERIAL, "b.pk_material");
    // 物料OID
    this.allFieldMap.put(DocConst.MATERIALOID, "b.pk_srcmaterial");
    // 供应商分类
    this.allFieldMap.put(DocConst.BDVENDORCLASS, "vendor.pk_supplierclass");
    // 供应商
    this.allFieldMap.put(DocConst.BDVENDOR, "b.pk_supplier");
    // 地区分类
    this.allFieldMap.put(DocConst.BDARERCL, "vendor.pk_areacl");
    // 项目档案
    this.allFieldMap.put(DocConst.BDPROJECT, "b.cprojectid");
  }

  @Override
  protected String getExecQuerySql(NtbParamVO seed, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    tbbuilder.buildSql(this.getFieldMap(seed), params);
    // select部分
    StringBuilder select = this.getSelectPart(type, tbbuilder);
    // from部分
    String from = this.getFromPart(seed, tbbuilder);
    // where部分
    String where = this.getWherePart(seed, tbbuilder);
    // group部分
    String group = this.getGroupPart(tbbuilder);
    return select.append(from).append(where).append(group).toString();
  }

  private String getWherePart(NtbParamVO seed,
      TbbTempTableSqlBuilder tbbuilder) {
    SqlBuilder where = new SqlBuilder();
    where.append(" where ");
    where.append(" b.dr = 0 ");
    where.append(" and b." + OrderItemVO.FISACTIVE,
        new int[]{EnumActive.ACTIVE.toInt(), EnumActive.CLOSE.toInt()});
    if (!seed.isUnInure()) {
      where.append(" and ");
      if(UFIND.equals(seed.getMethodCode())){
        where.append(" h.forderstatus", APPROVE);
      }else if(PREFIND.equals(seed.getMethodCode())){
        where.append(" h.forderstatus", NOAPPROVE);
      }
    }
    // 拼接where部分
    where.append(tbbuilder.getWherepart(true));
    return where.toString();
  }

  private String getGroupPart(TbbTempTableSqlBuilder tbbuilder) {
    StringBuilder group = new StringBuilder();
    group.append(" group by ");
    // 拼接group部分
    group.append(tbbuilder.getGrouppart(false));
    return group.toString();
  }

  private String getFromPart(NtbParamVO seed,
      TbbTempTableSqlBuilder tbbuilder) {
    StringBuilder from = new StringBuilder();
    from.append(" from po_order_b b ");
    // 根据刘建波、金煜等专家意见，改为如下方式
    boolean isJoinH = false;
    if (this.isIncludeField(seed, DocConst.BDPSN)
        || this.isIncludeField(seed, DocConst.BDDEPT) || !seed.isUnInure()
        || PuBillBusiSysReg.DAUDITDATT.equals(seed.getDateType())) {
      isJoinH = true;
    }

    if (isJoinH) {
      from.append(" inner join po_order h on ");
      from.append(" b.pk_order = h.pk_order ");
    }
    // 包含物料分类
    if (this.isIncludeField(seed, DocConst.MATCLASS)) {
      from.append(" left outer join " + MaterialVO.getDefaultTableName()
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
    return from.toString();
  }

  private StringBuilder getSelectPart(String type,
      TbbTempTableSqlBuilder tbbuilder) {
    StringBuilder select = new StringBuilder();
    if (PuBillBusiSysReg.NNUM.equals(type)) {
      select.append("select sum(b.nnum) ");
      tbbuilder.setStartIndex(1);
    }
    else if (PuBillBusiSysReg.NMNY.equals(type)) {
      // 依次是全局本币，集团本币，组织本币,原币值
      select
          .append("select sum(b.nglobalmny),sum(b.ngroupmny),sum(b.nmny),sum(b.norigmny) ");
      tbbuilder.setStartIndex(4);
    }
    else if (PuBillBusiSysReg.NTAXMNY.equals(type)) {
      // 依次是全局本币，集团本币，组织本币,原币值
      select
          .append("select sum(b.nglobaltaxmny),sum(b.ngrouptaxmny),sum(b.ntaxmny),sum(b.norigtaxmny) ");
      tbbuilder.setStartIndex(4);
    }
    // 拼接select部分
    select.append(tbbuilder.getSelectpart(true));
    return select;
  }

  @Override
  protected UFDouble[][] getExecResult(IRowSet rowset, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    UFDouble[][] result = new UFDouble[params.length][4];

    while (rowset.next()) {
      for (int i = 0; i < params.length; i++) {
        if (null != params[i]) {
          if (tbbuilder.isMatch(rowset, params[i])) {
            if (PuBillBusiSysReg.NNUM.equals(type)) {
              result[i][0] = rowset.getUFDouble(0);
              result[i][1] = rowset.getUFDouble(0);
              result[i][2] = rowset.getUFDouble(0);
              result[i][3] = rowset.getUFDouble(0);
            }
            else {
              result[i][0] = rowset.getUFDouble(0);
              result[i][1] = rowset.getUFDouble(1);
              result[i][2] = rowset.getUFDouble(2);
              result[i][3] = rowset.getUFDouble(3);
            }
          }
        }
      }
    }
    return result;
  }

  @Override
  protected Map<String, String> getFieldMap(NtbParamVO seed) {
    Map<String, String> result = new HashMap<String, String>();
    // 日期区间映射
    if (!StringUtils.isEmpty(seed.getDateType())) {
      if (PuBillBusiSysReg.DBILLDATE.equals(seed.getDateType())) {
        result.put(TbbTempTableSqlBuilder.DPERIOD, "b.dbilldate");
      }
      else if (PuBillBusiSysReg.DAUDITDATT.equals(seed.getDateType())) {
        result.put(TbbTempTableSqlBuilder.DPERIOD, "h.taudittime");
      }
    }
    if (seed.getCurr_type() == 3) {
      result.put(TbbTempTableSqlBuilder.CURRENCY, "b.corigcurrencyid");
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

  @Override
  protected boolean haveOtherDoc(NtbParamVO param) {
    String[] docs =
        new String[] {
          DocConst.PURCHASEORG, DocConst.FINANCEORG, DocConst.STOCKORG,
          DocConst.BDDEPT, DocConst.BDPSN, DocConst.MATCLASS,
          DocConst.MATERIAL, DocConst.MATERIALOID, DocConst.BDVENDORCLASS,
          DocConst.BDVENDOR, DocConst.BDARERCL, DocConst.BDPROJECT
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
