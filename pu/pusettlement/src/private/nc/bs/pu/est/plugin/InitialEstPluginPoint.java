package nc.bs.pu.est.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ڳ��ݹ������ο����Ŀɲ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-12 ����11:14:43
 */
public enum InitialEstPluginPoint implements IPluginPoint {
  /** ���� **/
  APPROVE,
  /** ɾ�� **/
  DELETE,
  /** ɾ��-ACTION **/
  DELETE_ACTION,
  /** �ݹ�Ӧ����BP�� **/
  EST_AP,
  /** �����BP **/
  SAVE_BP,
  /** ǰ̨�������� **/
  UI_INSERT,
  /** ǰ̨���±��� **/
  UI_UPDATE,
  /** ���� **/
  UNAPPROVE;

  @Override
  public String getComponent() {
    return POBillType.InitEstimate.getCode();
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
