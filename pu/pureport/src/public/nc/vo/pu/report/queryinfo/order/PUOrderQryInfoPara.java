package nc.vo.pu.report.queryinfo.order;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.view.order.OrderExecDetailViewMeta;
import nc.vo.pu.report.view.order.ReceivePlanRptViewMeta;

/**
 * �ɹ�������ѯ���ɱ����ѯ������
 * 
 * @since 6.0
 * @version 2011-3-10 ����02:18:42
 * @author wuxla
 */

public class PUOrderQryInfoPara extends PuQueryInfoPara {

  /** ���ϻ���������� **/
  public static final String BD_MARBASCLASS_CODE = "po_order_b.pk_material.pk_marbasclass";

  /** ���ϲɹ�������� **/
  public static final String BD_MARPUCLASS_CODE = "po_order_b.pk_material.materialstock.pk_marpuclass";

  /** ���ϱ��� **/
  public static final String BD_MATERIAL_V_CODE = "po_order_b.pk_srcmaterial.code";

  /** �������� **/
  public static final String BD_MATERIAL_V_NAME = "po_order_b.pk_srcmaterial.name";

  /** ��Ӧ�̱��� **/
  public static final String BD_SUPPLIER_CODE = "po_order_b.pk_supplier.code";

  /** ��Ӧ������ **/
  public static final String BD_SUPPLIER_NAME = "po_order_b.pk_supplier.name";

  /** ��Ӧ�̵������� **/
  public static final String BD_SUPPLIER_PK_AREACL = "po_order_b.pk_supplier.pk_areacl";
  
  /** ����ִ�����  */
  public static final String EXEC_COND = "exec_cond";

  /** ����ִ������߼�����  */
  public static final String EXEC_LOGICOND = "exec_logiccond";

  /** ����ִ�������ѯ����  */
  public static final String EXEC_WHERE = "exec_where";

  /** �ɹ�������ִ�л��ܲ���Ҫ��ʾ���ֶ�  */
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
    // ����Ĭ�ϲ���ʾ�Ŀ�����Ҫ���⴦��
    "this." + OrderExecDetailViewMeta.IC_CMATERIALVID + ".code",
    "this." + OrderExecDetailViewMeta.IC_CMATERIALVID + ".name",
    "this." + OrderExecDetailViewMeta.IC_CUNITID + ".name",
    OrderExecDetailViewMeta.INVOICE_VBILLCODE,
    OrderExecDetailViewMeta.INVOICE_DBILLDATE,
    "this." + OrderExecDetailViewMeta.INVOICE_CORIGCURRENCYID + ".name"
  };

  public static final String GATHERNOSHOWPARA = "gathernoshowpara";

  /**
   * ����ִ�����
   */
  public static final String PAYEXEC_COND = "payexec_cond";

  /**
   * ����ִ�������ѯ����
   */
  public static final String PAYEXEC_WHERE = "payexec_where";

  /**
   * �����ƻ���ѯ
   */
  public static final String RP_COND = "rp_cond";

  /**
   * �����ƻ��Ƿ��ջ����
   */
  public static final String RP_FINISHRECV = "bfinishrecv";

  /**
   * �����ƻ����ܲ���Ҫ��ʾ���ֶ�
   */
  public static final String[] RP_GATHERNOSHOW = new String[] {
    "this." + OrderReceivePlanVO.PK_RECVSTORDOC + ".name",
    ReceivePlanRptViewMeta.ARR_CROWNO, ReceivePlanRptViewMeta.ARR_DBILLDATE,
    ReceivePlanRptViewMeta.ARR_VBILLCODE, ReceivePlanRptViewMeta.IC_CROWNO,
    ReceivePlanRptViewMeta.IC_DBILLDATE, ReceivePlanRptViewMeta.IC_VBILLCODE
  };

  /**
   * �����ƻ���ѯ�߼�����
   */
  public static final String RP_GROUPCONDITION = "groupcondition";

  /**
   * �����ƻ���ѯ
   */
  public static final String RP_WHERE = "rp_where";

  /**
   * ����ִ�����
   * ͳ������
   */
  public static final String STATCONTENT = "statcontent";

  /**
   * ����ִ�����
   * ͳ�Ʒ�ʽ
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
