package nc.impl.pu.report.estdiffer.sqlbuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nc.pub.smart.context.SmartContext;
import nc.vo.pu.report.enumeration.PuEstStatGroupEnum;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.estdiffer.PuEstDifferQryInfoPara;
import nc.vo.pubapp.pattern.log.Log;

/**
 * 暂估差异报表查询SQL构造器。
 * 之前在PuEstDifferQryInfoPara参数中设置分组字段来起到分组的目的，这样会有一个问题，就是计算差异率的时候结果不正确。
 * 所以经过修改，分组也放在SQL中完成。由于存在隐藏列的问题，担心少查字段会造成语义模型的错误。
 * 所以采用按全字段分组，非分组字段用~代替的方案。
 * 
 * @since 6.3
 * @version 2012-12-6 下午01:33:58
 * @author lixyp
 */
public class EstDifferSqlBuilder {

  private StringBuilder builder = new StringBuilder();

  private List<String> groupSqlFields = new ArrayList<String>();

  /** 从外部传入的查询参数 */
  private PuEstDifferQryInfoPara infoPara = null;

  public EstDifferSqlBuilder(SmartContext context) {
    this.infoPara =
        (PuEstDifferQryInfoPara) context
            .getAttribute(PuQueryInfoPara.QUERY_PARA);
  }

  @Override
  public String toString() {
    this.buildSelectSql();
    this.builder.append(" from (");
    this.buildBaseSql();
    this.buildGroupSql();
    this.builder.append(") a "); // 此处需要加别名，否则SQL Server下会出错。
    this.buildWhereSql();
    Log.debug(" EstDifferReportImpl sql=" + this.builder.toString());
    return this.builder.toString();
  }

  /**
   * 构造基础查询。包含报表的几乎全部逻辑。
   * 当查询的字段不是分组字段的时候，用~代替。
   */
  private void buildBaseSql() {
    this.builder.append(" select ");

    List<String> groupFieldList = null;
    // 语义模型完成时，infoPara为空。
    if (this.infoPara == null) {
      groupFieldList = new ArrayList<String>();
    }
    else {
      String[] groupFields = this.infoPara.getGroupcondtion().split(",");
      groupFieldList = Arrays.asList(groupFields);
    }
    // 物料
    if (!groupFieldList.contains(PuEstStatGroupEnum.MAR.value())) {
      this.builder.append("'~' pk_material, '~' cunitid, ");
    }
    else {
      this.builder.append("pk_material, cunitid, ");
      this.groupSqlFields.add("pk_material");
      this.groupSqlFields.add("cunitid");
    }
    // 物料分类
    if (!groupFieldList.contains(PuEstStatGroupEnum.MARCLASS.value())) {
      this.builder.append("'~' pk_marbasclass, ");
    }
    else {
      this.builder.append("pk_marbasclass, ");
      this.groupSqlFields.add("pk_marbasclass");
    }
    // 采购部门
    if (!groupFieldList.contains(PuEstStatGroupEnum.PUR_DEPT.value())) {
      this.builder.append("'~' pk_dept_v, '~' pk_dept, ");
    }
    else {
      this.builder.append("pk_dept_v, pk_dept, ");
      this.groupSqlFields.add("pk_dept_v");
      this.groupSqlFields.add("pk_dept");
    }
    // 采购员
    if (!groupFieldList.contains(PuEstStatGroupEnum.PUR_PSN.value())) {
      this.builder.append("'~' pk_psndoc, ");
    }
    else {
      this.builder.append("pk_psndoc, ");
      this.groupSqlFields.add("pk_psndoc");
    }
    // 库存组织
    if (!groupFieldList.contains(PuEstStatGroupEnum.STORE_ORG.value())) {
      this.builder.append("'~' pk_storeorg, '~' pk_storeorg_v, ");
    }
    else {
      this.builder.append("pk_storeorg, pk_storeorg_v, ");
      this.groupSqlFields.add("pk_storeorg");
      this.groupSqlFields.add("pk_storeorg_v");
    }
    // 供应商
    if (!groupFieldList.contains(PuEstStatGroupEnum.SUPPLIER.value())) {
      this.builder.append("'~' pk_supplier, ");
    }
    else {
      this.builder.append("pk_supplier, ");
      this.groupSqlFields.add("pk_supplier");
    }
    this.groupSqlFields.add("ccurrencyid");
    this.builder.append(" ccurrencyid, ");
    this.builder.append(" sum(prensettlenum) prensettlenum, ");
    this.builder.append(" sum(prengoodsmoney) prengoodsmoney, ");
    this.builder.append(" sum(prenclashestmoney) prenclashestmoney, ");
    this.builder.append(" sum(currnsettlenum) currnsettlenum, ");
    this.builder.append(" sum(currngoodsmoney) currngoodsmoney, ");
    this.builder.append(" sum(currnclashestmoney) currnclashestmoney, ");
    // 本月前暂估本月结算差异率
    this.builder
        .append(" case when sum(isnull(prenclashestmoney,0))=0 then 0 else (sum(isnull(prengoodsmoney,0))-sum(isnull(prenclashestmoney,0)))/sum(prenclashestmoney)*100 end prendifferrate, ");
    // 本月暂估本月结算差异率
    this.builder
        .append(" case when sum(isnull(currnclashestmoney,0))=0 then 0 else (sum(isnull(currngoodsmoney,0))-sum(isnull(currnclashestmoney,0)))/sum(currnclashestmoney)*100 end currndifferrate ");
    this.builder.append(" from ((");
    this.builder.append(new PreMonthEstCurMonthSettle(this.infoPara)
        .getQuerySql());
    // 本月前暂估本月结算
    this.builder.append(" ) union all ( ");
    this.builder.append(new CurMonthEstCurMonthSettle(this.infoPara)
        .getQuerySql());
    // 本月暂估本月结算
    this.builder.append(" )) estdiffer ");
  }

  /**
   * 构造分组字段，分组字段包含全字段。
   * 由于非分组字段用~代替，所以结果上不会有问题。
   */
  private void buildGroupSql() {
    this.builder.append(" group by ");
    for (String groupSqlField : this.groupSqlFields) {
      this.builder.append(groupSqlField);
      this.builder.append(",");
    }
    this.builder.deleteCharAt(this.builder.length() - 1);
  }

  /**
   * 在基础查询的基础上再套一层，主要是为了拼接where条件，因为在where条件中无法使用分组。
   */
  private void buildSelectSql() {
    this.builder.append(" select pk_material,cunitid,pk_marbasclass,");
    this.builder.append("pk_dept_v,pk_dept,pk_psndoc,pk_storeorg, ");
    this.builder.append("pk_storeorg_v,pk_supplier,ccurrencyid,");
    this.builder.append("prensettlenum,prengoodsmoney,prenclashestmoney,");
    this.builder.append("currnsettlenum,currngoodsmoney,");
    this.builder.append("currnclashestmoney,prendifferrate,currndifferrate");
  }

  /**
   * 构造查询条件。只有当只查询超过标准值的数据时才起作用。
   */
  private void buildWhereSql() {
    if (this.infoPara != null && this.infoPara.isOnlyQueryOverDifferRate()) {
      this.builder.append(" where ");
      this.builder.append(" (prengoodsmoney - prenclashestmoney) * 100 > ");
      this.builder.append(this.infoPara.getDifferRateStdValue());
      this.builder.append(" * prenclashestmoney");
      this.builder.append(" or ");
      this.builder.append(" (currngoodsmoney - currnclashestmoney) * 100 > ");
      this.builder.append(this.infoPara.getDifferRateStdValue());
      this.builder.append(" * currnclashestmoney");
    }
  }
}
