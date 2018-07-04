/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-12 ����11:14:43
 */
package nc.bs.pu.m25.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���Ʊ���ο����Ŀɲ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-12 ����11:14:43
 */
public enum InvoicePluginPoint implements IPluginPoint {
  /**
   * ����
   */
  APPROVE,
  /**
   * ȡ����Ӧ��
   */
  CANCEL_SEND_AP,
  /**
   * ɾ�����ⷢƱ
   */
  DEL_VIRTUAL,
  /**
   * ɾ��
   */
  DELETE,
  /**
   * ɾ��-IMPL
   */
  DELETE_IMPL,
  /**
   * ����
   */
  FREEZE,
  /**
   * �������ⷢƱ
   */
  GEN_VIRTUAL,
  /**
   * �������ⷢƱ_IT
   */
  GEN_VIRTUAL_IT,
  /**
   * �ɹ���ⵥ��ʽ����
   */
  M45_PUASHSAVE,
  /**
   * �����BP
   */
  SAVE_BP,
  /**
   * ��Ӧ��
   */
  SEND_AP,
  /**
   * ����(ƥ��)���Զ���Ӧ��
   */
  SEND_AP_AFTER_SETTLE,
  /**
   * ��Ӧ��-IMPL
   */
  SEND_AP_IMPL,
  /**
   * ǰ̨��������
   */
  UI_INSERT,
  /**
   * ǰ̨���±���
   */
  UI_UPDATE,
  /**
   * ����
   */
  UNAPPROVE,
  /**
   * �ⶳ
   */
  UNFREEZE, /**
   * �ջ�
   */
  UNSENDAPPROVE;

  @Override
  public String getComponent() {
    return POBillType.Invoice.getCode();
  }

  @Override
  public String getModule() {
    return NCModule.PO.getSystemCode();
  }

  @Override
  public String getPoint() {
    return this.name();
  }

}
