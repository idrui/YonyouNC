package nc.bs.pu.m23.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @description
 *              ��������BP���ο�������㶨��
 * @scene
 * @param ��
 * @since 6.3
 * @version 2010-1-13 ����10:26:37
 * @author hanbin
 */
public enum ArriveBPPlugInPoint implements IPluginPoint {

  /**
   * ɾ��
   */
  ArriveDeleteBP,

  /**
   * ����
   */
  ArriveInsertBP,
  
  /**
   * API����
   */
  ArriveInsertAPIBP,

  /**
   * �޸�
   */
  ArriveUpdateBP,

  /**
   * �����ʲ���Ƭ
   */
  CreateFACardBP,

  /**
   * ɾ���ʲ���Ƭ
   */
  DeleteFACardBP,

  /**
   * ɾ��ת�̵�
   */
  DeleteTransAsset,

  /**
   * ��ת��ֱ��
   */
  MaterialAssign,
  /**
   * ����ת�̵�
   */
  TransAsset,

  /**
   * �ɹ������������д
   */
  Writeback23For21BP,

  /**
   * �ɹ���ⵥ��д
   */
  Writeback23For45BP,

  /**
   * ί����ⵥ��д
   */
  Writeback23For47BP;

  @Override
  public String getComponent() {
    return POBillType.Arrive.getCode();
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
