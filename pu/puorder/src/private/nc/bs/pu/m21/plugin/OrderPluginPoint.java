package nc.bs.pu.m21.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ�������չ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-30 ����04:41:31
 */
public enum OrderPluginPoint implements IPluginPoint {
  /**
   * ����
   */
  APPROVE,

  /**
   * �����ر�
   */
  ARRIVE_CLOSE,

  /**
   * ������
   */
  ARRIVE_OPEN,

  /**
   * ���Զ���
   */
  AUTO_ROW_OPEN,

  /**
   * �Զ����ر�
   */
  AUTO_STORE_CLOSE,

  /**
   * �˻���ת����ѯ
   */
  BACK23,

  /**
   * �˿ⵥת����ѯ
   */
  BACK45,

  /**
   * ɾ��
   */
  DELETE,

  /**
   * ���չر�
   */
  FINAL_CLOSE,

  /**
   * ���մ�
   */
  FINAL_OPEN,

  /**
   * ����
   */
  FREEZE,

  /**
   * ��������
   */
  INSERT,

  /**
   * ��Ʊ�ر�
   */
  INVOICE_CLOSE,

  /**
   * ��Ʊ��
   */
  INVOICE_OPEN,

  /**
   * ����ر�
   */
  PAY_CLOSE,

  /**
   * �����
   */
  PAY_OPEN,

  /**
   * ����ƻ�ȡ����������
   */
  PAYPLAN_CANCELPAYREQ,

  /**
   * ����ƻ�ɾ��
   */
  PAYPLAN_DELETE_BP,

  /**
   * ����ƻ�����
   */
  PAYPLAN_INSERT_BP,

  /**
   * ����ƻ�����������
   */
  PAYPLAN_PAYREQ,

  /**
   * ����ƻ���ѯ
   */
  PAYPLAN_QUERY,

  /**
   * ����ƻ��޸�
   */
  PAYPLAN_UPDATE,

  /**
   * ����ƻ��޸�BP
   */
  PAYPLAN_UPDATE_BP,

  /**
   * ������ת����ѯ
   */
  PULL23,

  /**
   * �ɹ���Ʊת����ѯ
   */
  PULL25,

  /**
   * ��ⵥת����ѯ
   */
  PULL45,

  /**
   * ���䵥
   */
  PULL4804,

  /**
   * �ڳ��ݹ���ת����ѯ
   */
  PULL4T,

  /**
   * ���۳��ⵥ
   */
  PUSH4C,

  /**
   * Эͬ���۶���
   */
  PUSHCOOP30,

  /**
   * ������ϸ�������ѯ����
   */
  Query_ET,

  /**
   * �ɹ���ⵥ��д�����ƻ�
   */
  RECEIVE_PLAN_WRITEBACK_45,

  /**
   * ���䵥��д�����ƻ�
   */
  RECEIVE_PLAN_WRITEBACK_4804,

  /**
   * �޶�
   */
  REVISE,

  /**
   * �йر�
   */
  ROW_CLOSE,

  /**
   * �д�
   */
  ROW_OPEN,

  /**
   * ����
   */
  SEND_APPROVE,

  /**
   * ���ر�
   */
  STORE_CLOSE,

  /**
   * ����
   */
  STORE_OPEN,

  /**
   * ȡ�����
   */
  UNAPPROVE,

  /**
   * �ⶳ
   */
  UNFREEZE,

  /**
   * �ջ�
   */
  UNSAVE,

  /**
   * �޸ı���
   */
  UPDATE,
  /**
   * �������뵥��д
   */
  WRITEBACK_36DA,

  /**
   * �ɹ���ⵥ��д����
   */
  WRITEBACK_45,

  /**
   * ���䵥��д����
   */
  WRITEBACK_4804,
  /**
   * Эͬ��д
   */
  WRITEBACK_COOP30,

  /**
   * ������ϸ����д����
   */
  WRITEBACK_ET,

  /**
   * ������д
   */
  WRITEBACK_F1,
  /**
   * �����д
   */
  WRITEBACK_F3;

  @Override
  public String getComponent() {
    return POBillType.Order.getCode();
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
