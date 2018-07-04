package nc.vo.pu.report.queryinfo.order;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.view.order.OrderExecDetailViewMeta;
import nc.vo.pu.report.view.order.ReceivePlanRptViewMeta;

/**
 * 采购订单查询自由报表查询参数类
 * 
 * @since 6.0
 * @version 2011-3-10 下午02:18:42
 * @author wuxla
 */

public class PUOrderQryInfoPara extends PuQueryInfoPara {

  /** 物料基本分类编码 **/
  public static final String BD_MARBASCLASS_CODE = "po_order_b.pk_material.pk_marbasclass";

  /** 物料采购分类编码 **/
  public static final String BD_MARPUCLASS_CODE = "po_order_b.pk_material.materialstock.pk_marpuclass";

  /** 物料编码 **/
  public static final String BD_MATERIAL_V_CODE = "po_order_b.pk_srcmaterial.code";

  /** 物料名称 **/
  public static final String BD_MATERIAL_V_NAME = "po_order_b.pk_srcmaterial.name";

  /** 供应商编码 **/
  public static final String BD_SUPPLIER_CODE = "po_order_b.pk_supplier.code";

  /** 供应商名称 **/
  public static final String BD_SUPPLIER_NAME = "po_order_b.pk_supplier.name";

  /** 供应商地区分类 **/
  public static final String BD_SUPPLIER_PK_AREACL = "po_order_b.pk_supplier.pk_areacl";
  
  /** 订单执行情况  */
  public static final String EXEC_COND = "exec_cond";

  /** 订单执行情况逻辑条件  */
  public static final String EXEC_LOGICOND = "exec_logiccond";

  /** 订单执行情况查询条件  */
  public static final String EXEC_WHERE = "exec_where";

  /** 采购订单行执行汇总不需要显示的字段  */
  public static final String[] GATHERNOSHOW = new String[] {
    OrderExecDetailViewMeta.OUTPUT_VBILLCODE,
    OrderExecDetailViewMeta.OUTPUT_DBILLDATE,
    OrderExecDetailViewMeta.LOAD_VBILLCODE,
    OrderExecDetailViewMeta.LOAD_DBILLDATE,
    OrderExecDetailViewMeta.CUSTOM_VBILLCODE,
    OrderExecDetailViewMeta.CUSTOM_DBILLDATE,
    OrderExecDetailViewMeta.OUTCUSTOM_VBILLCODE,
    OrderExecDetailViewMeta.OUTCUSTOM_DBILLDATE,
    OrderExecDetailViewMeta.ARRIVE_VBILLCODE,
    OrderExecDetailViewMeta.ARRIVE_DBILLDATE,
    "this." + OrderExecDetailViewMeta.CWAREHOUSEID + ".name",
    OrderExecDetailViewMeta.IC_VBILLCODE,
    OrderExecDetailViewMeta.IC_DBILLDATE,
    // 对于默认不显示的可能需要特殊处理
    "this." + OrderExecDetailViewMeta.IC_CMATERIALVID + ".code",
    "this." + OrderExecDetailViewMeta.IC_CMATERIALVID + ".name",
    "this." + OrderExecDetailViewMeta.IC_CUNITID + ".name",
    OrderExecDetailViewMeta.INVOICE_VBILLCODE,
    OrderExecDetailViewMeta.INVOICE_DBILLDATE,
    "this." + OrderExecDetailViewMeta.INVOICE_CORIGCURRENCYID + ".name"
  };

  public static final String GATHERNOSHOWPARA = "gathernoshowpara";

  /**
   * 付款执行情况
   */
  public static final String PAYEXEC_COND = "payexec_cond";

  /**
   * 付款执行情况查询条件
   */
  public static final String PAYEXEC_WHERE = "payexec_where";

  /**
   * 到货计划查询
   */
  public static final String RP_COND = "rp_cond";

  /**
   * 到货计划是否收货完成
   */
  public static final String RP_FINISHRECV = "bfinishrecv";

  /**
   * 到货计划汇总不需要显示的字段
   */
  public static final String[] RP_GATHERNOSHOW = new String[] {
    "this." + OrderReceivePlanVO.PK_RECVSTORDOC + ".name",
    ReceivePlanRptViewMeta.ARR_CROWNO, ReceivePlanRptViewMeta.ARR_DBILLDATE,
    ReceivePlanRptViewMeta.ARR_VBILLCODE, ReceivePlanRptViewMeta.IC_CROWNO,
    ReceivePlanRptViewMeta.IC_DBILLDATE, ReceivePlanRptViewMeta.IC_VBILLCODE
  };

  /**
   * 到货计划查询逻辑条件
   */
  public static final String RP_GROUPCONDITION = "groupcondition";

  /**
   * 到货计划查询
   */
  public static final String RP_WHERE = "rp_where";

  /**
   * 订单执行情况
   * 统计内容
   */
  public static final String STATCONTENT = "statcontent";

  /**
   * 订单执行情况
   * 统计方式
   */
  public static final String STATTYPE = "stattype";

  private static final long serialVersionUID = 3525376224660851271L;

  @Override
  public String[] getHideKeys() {
    return new String[] {
      OrderItemVO.PK_MATERIAL
    };
  }
}
