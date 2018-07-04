package nc.vo.pu.report.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * �ɹ���Ʊ��Դ��������
 * 
 * @since 6.0
 * @version 2011-3-25 ����02:58:30
 * @author lizhengb
 */
public class PuInvoiceEnum extends NCMDEnum {

  /** ί�мӹ���� **/
  public static final PuInvoiceEnum IC_INBAND = MDEnum.valueOf(
      PuInvoiceEnum.class, "ic_inband");

  /** �ɹ���ⵥ **/
  public static final PuInvoiceEnum IC_PURCHASEIN = MDEnum.valueOf(
      PuInvoiceEnum.class, "ic_purchasein");

  /** ���Ļ��ܼ�¼ **/
  public static final PuInvoiceEnum IC_VMI = MDEnum.valueOf(
      PuInvoiceEnum.class, "ic_vmi");

  /** �ڳ��ݹ��� **/
  public static final PuInvoiceEnum PU_INITIALEST = MDEnum.valueOf(
      PuInvoiceEnum.class, "pu_initialest");

  /** �ɹ����� **/
  public static final PuInvoiceEnum PU_ORDER = MDEnum.valueOf(
      PuInvoiceEnum.class, "pu_order");

  /** ί�ⶩ�� **/
  public static final PuInvoiceEnum SC_ORDER = MDEnum.valueOf(
      PuInvoiceEnum.class, "sc_order");

  public PuInvoiceEnum(IEnumValue enumValue) {
    super(enumValue);
  }
}
