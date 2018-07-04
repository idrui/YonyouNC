/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-26 上午11:51:12
 */
package nc.ui.pu.m21.action.status;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.scmpub.util.FromWhereParseUtil;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-26 上午11:51:12
 */
public class StatusActionTools {

  /**
   * 方法功能描述：拼接自定义条件
   * <p>
   * <b>参数说明</b>
   * 
   * @param sqlWhere
   * @param sqlsb
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-21 下午02:01:07
   */
  public static void combinCond(String sqlWhere, int status, StringBuilder sqlsb) {

    int index = sqlWhere.indexOf(" WHERE ");
    String cond = sqlWhere.substring(index);
    sqlsb.append(cond);
    sqlsb.append(" and po_order_bb.dr = 0");
    sqlsb.append(" and po_order_bb.fonwaystatus = ");
    sqlsb.append(status);

  }

  /**
   * 方法功能描述：拼接过滤关闭行，是否已操作等条件
   * <p>
   * <b>参数说明</b>
   * 
   * @param hTable
   * @param bTable
   * @param sqlsb
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-26 上午09:12:46
   */
  public static void combinOperated(String hTable, String bTable,
      String bbTable, boolean isopr, StringBuilder sqlsb) {

    // 过滤掉关闭行和非最新版本
    sqlsb.append(" and ");
    sqlsb.append(hTable);
    sqlsb.append(".");
    sqlsb.append(OrderHeaderVO.BISLATEST);
    sqlsb.append(" = 'Y'");

    sqlsb.append(" and ");
    sqlsb.append(bTable);
    sqlsb.append(".");
    sqlsb.append(OrderItemVO.FISACTIVE);
    sqlsb.append(" = ");
    sqlsb.append(ValueUtils.getInt(EnumActive.ACTIVE.value()));

    if (!isopr) {
      sqlsb.append(" and ");
      sqlsb.append(bbTable);
      sqlsb.append(".isoperated = 'N' ");
    }
    else {
      sqlsb.append(" and ");
      sqlsb.append(bbTable);
      sqlsb.append(".isoperated = 'Y' ");
    }
  }

  /**
   * 方法功能描述：如果是查询未输出，过滤所有停用项。
   * <p>
   * <b>参数说明</b>
   * 
   * @param hTable
   *          表头别名
   * @param sqlsb
   *          sql
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-21 下午01:58:22
   */
  public static void filtratUnable(String hTable, StringBuilder sqlsb) {

    sqlsb.append(" inner join bd_supplier bd_supplier on ");
    sqlsb.append(hTable);
    sqlsb
        .append(".pk_supplier = bd_supplier.pk_supplier and bd_supplier.dr = 0 ");
    sqlsb.append(" inner join org_dept org_dept on ");
    sqlsb.append(hTable);
    sqlsb.append(".pk_dept = org_dept.pk_dept and org_dept.dr = 0 ");

  }

  /**
   * 方法功能描述：判断是否已操作
   * <p>
   * <b>参数说明</b>
   * 
   * @param dlg
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-26 下午01:22:09
   */
  // public static UFBoolean getIsOperated(SCMQueryConditionDlg dlg,
  // String statusName) {
  // ConditionVO[] condVOs = dlg.getSelectedConds();
  // for (ConditionVO condVO : condVOs) {
  // if (statusName.equals(condVO.getFieldCode())) {
  // return UFBoolean.valueOf(condVO.getValue());
  // }
  // }
  // return UFBoolean.FALSE;
  // }

  /**
   * 方法功能描述： 取得组织
   * <p>
   * <b>参数说明</b>
   * 
   * @param sqlWhere
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-26 上午11:25:27
   */
  // public static String getpkorg(SCMQueryConditionDlg dlg) {
  // ConditionVO[] generalConds = dlg.getGeneralCondtionVOs();
  // for (ConditionVO generalCond : generalConds) {
  // if (OrderHeaderVO.PK_ORG.equals(generalCond.getFieldCode())) {
  // return generalCond.getValue();
  // }
  // }
  // return "";
  // }

  /**
   * 方法功能描述：拼接sql
   * <p>
   * <b>参数说明</b>
   * 
   * @param sqlWhere
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-26 下午01:17:55
   */
  public static String getSql(String sqlWhere, UFBoolean isOperated, int status) {
    FromWhereParseUtil parseUtil = new FromWhereParseUtil(sqlWhere);
    String hTable = parseUtil.getTableAlias(PUEntity.M21_H_TABLE);
    String bTable = parseUtil.getTableAlias(PUEntity.M21_B_TABLE);
    String bbTable = parseUtil.getTableAlias(PUEntity.M21_BB_TABLE);

    if (StringUtils.isEmpty(bTable)) {
      bTable = "po_order_b";
    }

    if (StringUtils.isEmpty(bbTable)) {
      bbTable = "po_order_bb";
    }

    StringBuilder sqlsb = new StringBuilder();
    // 拼接关联表
    StatusActionTools.conTable(hTable, bTable, bbTable, sqlsb);

    // 如果是查询未输出，过滤所有停用项。
    // if (!isOperated.booleanValue()) {
    // StatusActionTools.filtratUnable(hTable, sqlsb);
    // }

    // 拼接自定义条件
    StatusActionTools.combinCond(sqlWhere, status, sqlsb);

    StatusActionTools.combinOperated(hTable, bTable, bbTable,
        isOperated.booleanValue(), sqlsb);

    sqlsb.append(" order by pk_order_b");
    return sqlsb.toString();
  }

  /**
   * 方法功能描述：表关联
   * <p>
   * <b>参数说明</b>
   * 
   * @param hTable
   *          表头别名
   * @param bTable
   *          表体别名
   * @param sqlsb
   *          sql
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-21 下午01:56:35
   */
  private static void conTable(String hTable, String bTable, String bbTable,
      StringBuilder sqlsb) {
    // 关联子表与子子表
    sqlsb
        .append("select distinct pk_order, pk_order_b, pk_order_bb from po_order ");
    sqlsb.append(hTable);
    sqlsb.append(" inner join po_order_b ");
    sqlsb.append(bTable);
    sqlsb.append(" on ");
    sqlsb.append(hTable);
    sqlsb.append(".pk_order = ");
    sqlsb.append(bTable);
    sqlsb.append(".pk_order inner join po_order_bb ");
    sqlsb.append(bbTable);
    sqlsb.append(" on ");
    sqlsb.append(bbTable);
    sqlsb.append(".pk_order_b = ");
    sqlsb.append(bTable);
    sqlsb.append(".pk_order_b");
  }
}
