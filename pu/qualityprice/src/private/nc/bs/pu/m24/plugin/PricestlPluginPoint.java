package nc.bs.pu.m24.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥����չ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-8-30 ����05:32:17
 */
public enum PricestlPluginPoint implements IPluginPoint {
  /**
   * ���
   */
  APPROVE,

  /**
   * ɾ��
   */
  DELETE,

  /**
   * ��������
   */
  INSERT,

  /**
   * ����
   */
  SEND_APPROVE,

  /**
   * �ջ�
   */
  UN_SEND_APPROVE,

  /**
   * ȡ�����
   */
  UNAPPROVE,

  /**
   * ���±���
   */
  UPDATE;

  @Override
  public String getComponent() {
    return POBillType.PriceStl.getCode();
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
