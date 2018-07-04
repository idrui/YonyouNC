/**
 * $文件说明$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-18 上午11:36:52
 */
package nc.bs.pu.m21.alert;

import nc.vo.pu.m21.enumeration.EnumBillStatus;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;

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
 * @author gaogr
 * @time 2010-10-18 上午11:36:52
 */
public class OrderAlertConst {
  // 审批通过及输出
  public static final String SQL_PO_AUDIT_OR_OUTPUT =
      "po_order.forderstatus IN (" + POEnumBillStatus.APPROVE.toInt() + ","
          + EnumBillStatus.EXPORT.toInt() + ")";

  // 到货计划表
  public static final String SQL_PO_BB1 = "(po_order_bb1.dr=0)";

  // 未完全到货
  public static final String SQL_PO_BB1_NOTFULLARRV =
      "(po_order_bb1.nnum>ISNULL(po_order_bb1.naccumarrvnum,0.0))";

  // 未完全入库
  public static final String SQL_PO_BB1_NOTFULLSTORE =
      "(po_order_bb1.nnum>ISNULL(po_order_bb1.naccumstorenum,0.0))";

  // 体
  public static final String SQL_PO_BODY = "(po_order_b.dr=0  )";

  // 激活
  public static final String SQL_PO_BODY_ACTIVE = "po_order_b.fisactive = 0 ";

  // 未完全到货
  public static final String SQL_PO_BODY_NOTFULLARRV =
      "(po_order_b.barriveclose= 'N')";

  // 未完全入库
  public static final String SQL_PO_BODY_NOTFULLSTORE =
      "(po_order_b.bstockclose='N')";

  // 无到货计划
  public static final String SQL_PO_BODY_RP_NO =
      "ISNULL(po_order_b.breceiveplan,'N')='N'";

  // 有到货计划
  public static final String SQL_PO_BODY_RP_YES = "po_order_b.breceiveplan='Y'";

  // 头的固定查询信息：最新版 + 版本号1 + DR
  public static final String SQL_PO_HEAD =
      "(po_order.dr=0 AND ISNULL(po_order.bislatest,'Y')='Y')";

  // 无到货环节
  public static final String SQL_PO_STATUS_ARRV_NO =
      "(ISNULL(po_potrantype.barrive,'N')='N')";

  // 有到货环节
  public static final String SQL_PO_STATUS_ARRV_YES =
      "(po_potrantype.barrive='Y')";

  // 必须有到货计划
  public static final String SQL_PO_STATUS_RP_NO =
      "(ISNULL(po_potrantype.breceiveplan,'N')='N')";

  // 有入库环节
  public static final String SQL_PO_STATUS_STORE_YES =
      "(po_potrantype.bstore='Y')";
}
