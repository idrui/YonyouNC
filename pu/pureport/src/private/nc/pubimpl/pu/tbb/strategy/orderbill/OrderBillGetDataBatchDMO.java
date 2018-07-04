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
 * @version 2011-4-2 ����09:36:36
 * @author yinfy
 */

public class OrderBillGetDataBatchDMO extends AbstractGetDataBatchDMO {
  private Map<String, String> allFieldMap = new HashMap<String, String>();

  public OrderBillGetDataBatchDMO() {
    // ����
    this.allFieldMap.put(TbbTempTableSqlBuilder.CURRENCY, "b.corigcurrencyid");
    // �����֯
    this.allFieldMap.put(DocConst.STOCKORG, "b.pk_arrvstoorg");
    // �ɹ���֯
    this.allFieldMap.put(DocConst.PURCHASEORG, "b.pk_org");
    // ���������֯
    this.allFieldMap.put(DocConst.FINANCEORG, "b.pk_psfinanceorg");
    // ����
    this.allFieldMap.put(DocConst.BDDEPT, "h." + OrderHeaderVO.PK_DEPT);
    // ҵ��Ա
    this.allFieldMap.put(DocConst.BDPSN, "h.cemployeeid");
    // ���Ϸ���
    this.allFieldMap.put(DocConst.MATCLASS, "inv.pk_marbasclass");
    // ����
    this.allFieldMap.put(DocConst.MATERIAL, "b.pk_material");
    // ����OID
    this.allFieldMap.put(DocConst.MATERIALOID, "b.pk_srcmaterial");
    // ��Ӧ�̷���
    this.allFieldMap.put(DocConst.BDVENDORCLASS, "vendor.pk_supplierclass");
    // ��Ӧ��
    this.allFieldMap.put(DocConst.BDVENDOR, "b.pk_supplier");
    // ��������
    this.allFieldMap.put(DocConst.BDARERCL, "vendor.pk_areacl");
    // ��Ŀ����
    this.allFieldMap.put(DocConst.BDPROJECT, "b.cprojectid");
  }

  @Override
  protected String getExecQuerySql(NtbParamVO seed, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    tbbuilder.buildSql(this.getFieldMap(seed), params);
    // select����
    StringBuilder select = this.getSelectPart(type, tbbuilder);
    // from����
    String from = this.getFromPart(seed, tbbuilder);
    // where����
    String where = this.getWherePart(seed, tbbuilder);
    // group����
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
    // ƴ��where����
    where.append(tbbuilder.getWherepart(true));
    return where.toString();
  }

  private String getGroupPart(TbbTempTableSqlBuilder tbbuilder) {
    StringBuilder group = new StringBuilder();
    group.append(" group by ");
    // ƴ��group����
    group.append(tbbuilder.getGrouppart(false));
    return group.toString();
  }

  private String getFromPart(NtbParamVO seed,
      TbbTempTableSqlBuilder tbbuilder) {
    StringBuilder from = new StringBuilder();
    from.append(" from po_order_b b ");
    // ���������������ϵ�ר���������Ϊ���·�ʽ
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
    // �������Ϸ���
    if (this.isIncludeField(seed, DocConst.MATCLASS)) {
      from.append(" left outer join " + MaterialVO.getDefaultTableName()
          + " inv on  ");
      from.append(" b.pk_material=inv.pk_material ");
    }
    // ������Ӧ�̷�����߹�Ӧ�̵�������
    if (this.isIncludeField(seed, DocConst.BDVENDORCLASS)
        || this.isIncludeField(seed, DocConst.BDARERCL)) {
      from.append(" left join " + new SupplierVO().getTableName()
          + " vendor on  ");
      from.append(" b.pk_supplier = vendor.pk_supplier ");
    }
    // ƴ��from����
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
      // ������ȫ�ֱ��ң����ű��ң���֯����,ԭ��ֵ
      select
          .append("select sum(b.nglobalmny),sum(b.ngroupmny),sum(b.nmny),sum(b.norigmny) ");
      tbbuilder.setStartIndex(4);
    }
    else if (PuBillBusiSysReg.NTAXMNY.equals(type)) {
      // ������ȫ�ֱ��ң����ű��ң���֯����,ԭ��ֵ
      select
          .append("select sum(b.nglobaltaxmny),sum(b.ngrouptaxmny),sum(b.ntaxmny),sum(b.norigtaxmny) ");
      tbbuilder.setStartIndex(4);
    }
    // ƴ��select����
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
    // ��������ӳ��
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
