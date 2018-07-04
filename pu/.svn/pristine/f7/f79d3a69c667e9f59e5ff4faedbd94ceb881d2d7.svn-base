package nc.impl.pu.report.supplierstatcomprate;

import nc.itf.pu.report.supplierstatcomprate.ISupplierStatOrderCompRate;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.exception.SmartException;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.report.queryinfo.supplierstatcomprate.SupplierStatCompRateQryInfoPara;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.StringUtils;

/**
 * 采购报表—按供应商统计订单完成率
 * 数据加工
 * SQL脚本代码如下:
 * SELECT
 * pk_marbasclass,
 * pk_supplier,
 * ordernum,
 * orderrownum,
 * shouldorder,
 * shouldorderrownum,
 * realordernum,
 * realorderrownum,
 * rowcomprate,
 * cast(realordernum as float) ordernum ordercomprate
 * FROM
 * (
 * SELECT
 * pk_marbasclass pk_marbasclass,
 * pk_supplier pk_supplier,
 * COUNT(DISTINCT pk_order) ordernum,
 * MAX(orderrownum) orderrownum,
 * COUNT(DISTINCT pk_shouldorder) shouldorder,
 * MAX(shouldorderrownum) shouldorderrownum,
 * COUNT(DISTINCT realordernum) realordernum,
 * MAX(realorderrownum) realorderrownum,
 * CASE
 * WHEN MAX(orderrownum) = 0
 * THEN 0
 * ELSE cast(MAX(realorderrownum) as float) / MAX(orderrownum)
 * END rowcomprate
 * FROM
 * (
 * SELECT
 * bd_material.pk_marbasclass,
 * po_order_b.pk_supplier,
 * po_order_b.pk_order,
 * COUNT(*) over(partition BY bd_material.pk_marbasclass,
 * po_order_b.pk_supplier)
 * orderrownum,
 * CASE
 * WHEN (SUM(
 * CASE
 * WHEN po_order_b.dplanarrvdate <= '2010-02-01 23:59:59'
 * THEN 0
 * ELSE 1
 * END) over(partition BY bd_material.pk_marbasclass,
 * po_order_b.pk_supplier, po_order_b.pk_order)) = 0
 * THEN po_order_b.pk_order
 * ELSE NULL
 * END pk_shouldorder,
 * COUNT(
 * CASE
 * WHEN po_order_b.dplanarrvdate <= '2010-02-01 23:59:59'
 * THEN po_order_b.pk_order
 * ELSE NULL
 * END) over(partition BY bd_material.pk_marbasclass, po_order_b.pk_supplier)
 * shouldorderrownum,
 * CASE
 * WHEN (SUM(
 * CASE
 * WHEN po_order_b.bstockclose = 'Y'
 * THEN 0
 * ELSE 1
 * END) over(partition BY bd_material.pk_marbasclass,
 * po_order_b.pk_supplier, po_order_b.pk_order)) = 0
 * THEN po_order_b.pk_order
 * ELSE NULL
 * END realordernum,
 * COUNT(
 * CASE
 * WHEN po_order_b.bstockclose = 'Y'
 * THEN po_order_b.pk_order
 * ELSE NULL
 * END) over(partition BY bd_material.pk_marbasclass, po_order_b.pk_supplier)
 * realorderrownum
 * FROM
 * po_order po_order
 * INNER JOIN
 * po_order_b po_order_b
 * ON
 * po_order.pk_order = po_order_b.pk_order
 * INNER JOIN
 * bd_material bd_material
 * ON
 * po_order_b.pk_material = bd_material.pk_material
 * WHERE
 * po_order.dr = 0
 * AND po_order_b.dr = 0
 * AND po_order.forderstatus = 3
 * AND po_order_b.fisactive <> 3) innerresult
 * GROUP BY
 * pk_marbasclass,
 * pk_supplier) outerresult
 * 
 * @since 6.0
 * @version 2012-8-21 下午10:25:18
 * @author fanly3
 */
public class SupplierStatOrderCompRateImpl implements
    ISupplierStatOrderCompRate {

  @Override
  public String getOrderCompRateSql(SmartContext context) throws SmartException {
    // 取得查询条件对话框中的where条件
    String where =
        (String) context
            .getAttribute(SupplierStatCompRateQryInfoPara.QUERY_WHERE);
    // 获得截止日期
    String deallineDateStr =
        (String) context
            .getAttribute(SupplierStatCompRateQryInfoPara.DEADLINEDATE);
    // 得到最终SQL
    String finalSql = this.getFinalSql(where, deallineDateStr);
    return finalSql;
  }

  /**
   * 查询条件对话框中选择了物理基本分类编码时,拼接bd_marbasclass的inner join语句
   * 
   * @param sql 内查询SqlBuilder对象
   * @param where 查询条件对话框中的where条件
   */
  private void appendMarBasClass(SqlBuilder sql, String where) {
    if (StringUtils.isBlank(where)) {
      return;
    }
    boolean hasMarBasClass = false;
    if (where.contains("bd_marbasclass")) {
      hasMarBasClass = true;
    }
    if (hasMarBasClass) {
      sql.append(" inner join ");
      sql.append(" bd_marbasclass bd_marbasclass");
      sql.append(" on");
      sql.append(" bd_marbasclass.pk_marbasclass = bd_material.pk_marbasclass");
    }
  }

  /**
   * 查询条件对话框中选择了供应商编码或供应商名称时,拼接bd_supplier的inner join语句
   * 
   * @param sql 内查询SqlBuilder对象
   * @param where 查询条件对话框中的where条件
   */
  private void appendSupplier(SqlBuilder sql, String where) {
    if (StringUtils.isBlank(where)) {
      return;
    }
    boolean hasSupplier = false;
    if (where.contains("bd_supplier")) {
      hasSupplier = true;
    }
    if (hasSupplier) {
      sql.append(" inner join ");
      sql.append(" bd_supplier bd_supplier");
      sql.append(" on");
      sql.append(" bd_supplier.pk_supplier = po_order_b.pk_supplier");
    }
  }

  /**
   * 需要对where条件进行非空校验,在语义模型点击下一步时,它的值就为null
   * 
   * @param sql 内查询SqlBuilder对象
   * @param where 查询条件对话框中的where条件
   */
  private void appendWhereSql(SqlBuilder sql, String where) {
    if (StringUtils.isBlank(where)) {
      return;
    }
    sql.append(" and ");
    sql.append(where);
  }

  /**
   * 构建内查询语句
   * 
   * @param where 查询条件对话框中的where条件
   * @param deallineDateStr 截止日期
   * @return 内查询SQL
   */
  private String buildSubQuery(String where, String deallineDateStr) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("          SELECT  ");
    sql.append("              pk_marbasclass                 pk_marbasclass,  ");
    sql.append("              pk_supplier                    pk_supplier, ");
    sql.append("              COUNT(DISTINCT pk_order)       ordernum,  ");
    sql.append("              MAX(orderrownum)               orderrownum, ");
    sql.append("              COUNT(DISTINCT pk_shouldorder) shouldorder, ");
    sql.append("              MAX(shouldorderrownum)         shouldorderrownum, ");
    sql.append("              COUNT(DISTINCT realordernum)   realordernum,  ");
    sql.append("              MAX(realorderrownum)           realorderrownum, ");
    sql.append("              CASE  ");
    sql.append("                  WHEN MAX(orderrownum) = 0 ");
    sql.append("                  THEN 0  ");
    sql.append("                  ELSE cast(MAX(realorderrownum) as float) / MAX(orderrownum)  ");
    sql.append("              END rowcomprate ");
    sql.append("          FROM  ");
    sql.append("              ( ");
    sql.append("                  SELECT  ");
    sql.append("                      bd_material.pk_marbasclass, ");
    sql.append("                      po_order_b.pk_supplier, ");
    sql.append("                      po_order_b.pk_order,  ");
    sql.append("                      COUNT(*) over(partition BY bd_material.pk_marbasclass, po_order_b.pk_supplier)  ");
    sql.append("                      orderrownum,  ");
    sql.append("                      CASE  ");
    sql.append("                          WHEN (SUM(  ");
    sql.append("                                      CASE  ");
    sql.append("                                          WHEN po_order_b.dplanarrvdate <= '"
        + deallineDateStr + "'  ");
    sql.append("                                          THEN 0  ");
    sql.append("                                          ELSE 1  ");
    sql.append("                                      END) over(partition BY bd_material.pk_marbasclass,  ");
    sql.append("                                  po_order_b.pk_supplier, po_order_b.pk_order)) = 0 ");
    sql.append("                          THEN po_order_b.pk_order  ");
    sql.append("                          ELSE NULL ");
    sql.append("                      END pk_shouldorder, ");
    sql.append("                      COUNT(  ");
    sql.append("                          CASE  ");
    sql.append("                              WHEN po_order_b.dplanarrvdate <= '"
        + deallineDateStr + "'  ");
    sql.append("                              THEN po_order_b.pk_order  ");
    sql.append("                              ELSE NULL ");
    sql.append("                          END) over(partition BY bd_material.pk_marbasclass, po_order_b.pk_supplier)  ");
    sql.append("                      shouldorderrownum,  ");
    sql.append("                      CASE  ");
    sql.append("                          WHEN (SUM(  ");
    sql.append("                                      CASE  ");
    sql.append("                                          WHEN po_order_b.bstockclose = 'Y' ");
    sql.append("                                          THEN 0  ");
    sql.append("                                          ELSE 1  ");
    sql.append("                                      END) over(partition BY bd_material.pk_marbasclass,  ");
    sql.append("                                  po_order_b.pk_supplier, po_order_b.pk_order)) = 0 ");
    sql.append("                          THEN po_order_b.pk_order  ");
    sql.append("                          ELSE NULL ");
    sql.append("                      END realordernum, ");
    sql.append("                      COUNT(  ");
    sql.append("                          CASE  ");
    sql.append("                              WHEN po_order_b.bstockclose = 'Y' ");
    sql.append("                              THEN po_order_b.pk_order  ");
    sql.append("                              ELSE NULL ");
    sql.append("                          END) over(partition BY bd_material.pk_marbasclass, po_order_b.pk_supplier)  ");
    sql.append("                      realorderrownum ");
    sql.append("                  FROM  ");
    sql.append("                      po_order po_order ");
    sql.append("                  INNER JOIN  ");
    sql.append("                      po_order_b po_order_b ");
    sql.append("                  ON  ");
    sql.append("                      po_order.pk_order = po_order_b.pk_order ");
    sql.append("                  INNER JOIN  ");
    sql.append("                      bd_material bd_material ");
    sql.append("                  ON  ");
    sql.append("                      po_order_b.pk_material = bd_material.pk_material  ");
    // 查询模板上选择了供应商相关的条件,添加供应商表的inner join
    this.appendSupplier(sql, where);
    // 查询模板上选择了物料基本分类编码,添加物料基本分类表的inner join
    this.appendMarBasClass(sql, where);
    sql.append("                  WHERE ");
    sql.append("                      po_order.dr = 0 ");
    sql.append("                  AND po_order_b.dr = 0 ");
    sql.append("                  AND po_order.forderstatus = "
        + POEnumBillStatus.APPROVE.toIntValue());
    sql.append("                  AND po_order_b.fisactive <> "
        + EnumActive.REVISEHISTORY.toIntValue());
    // 添加查询模板上选择了的where条件
    this.appendWhereSql(sql, where);
    sql.append(") innerresult  ");

    return sql.toString();
  }

  /**
   * 返回最终SQL
   * 
   * @param where 查询条件对话框中的where条件
   * @param deallineDateStr 截止日期
   * @return 最终SQL
   */
  private String getFinalSql(String where, String deallineDateStr) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("  SELECT  ");
    sql.append("      pk_marbasclass, ");
    sql.append("      pk_supplier,  ");
    sql.append("      ordernum, ");
    sql.append("      orderrownum,  ");
    sql.append("      shouldorder,  ");
    sql.append("      shouldorderrownum,  ");
    sql.append("      realordernum, ");
    sql.append("      realorderrownum,  ");
    sql.append("      rowcomprate,  ");
    sql.append("      cast(realordernum as float) / ordernum ordercomprate ");
    sql.append("  FROM  ");
    sql.append("      ( ");
    sql.append(this.buildSubQuery(where, deallineDateStr));
    sql.append("          GROUP BY  ");
    sql.append("              pk_marbasclass, ");
    sql.append("              pk_supplier) outerresult  ");
    return sql.toString();
  }

}
