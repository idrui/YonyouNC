/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-28 ����09:52:44
 */
package nc.pubimpl.pu.m20.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

/**
 * @description
 *              ���ݶ��������Լƻ�������ʽ�����빺��\����������ʽ�����빺���Ȳ���
 * @scene
 *        �ƻ�������ʽ�����빺��\����������ʽ�����빺��
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����1:31:07
 * @author yanxm5
 */
public enum ActionPlugInPoint implements IPluginPoint {

  /**
   * ���۶�����ʽ�����빺��
   */
  PushSave20For30Action(
      nc.pubimpl.pu.m20.so.m30.action.PushSave20For30Action.class.getName()),

  /**
   * �ʲ�����������ʽ�����빺��
   */
  PushSave20For4A08Action(
      nc.pubimpl.pu.m20.aim.action.PushSave20For4A08Action.class.getName()),

  /**
   * �ƻ�������ʽ�����빺��
   */
  PushSave20ForA1Action(
      nc.pubimpl.pu.m20.mm.action.PushSave20For55B4Action.class.getName()),

  /**
   * ����������ʽ�����빺��
   */
  PushSave20ForA2Action(nc.pubimpl.pu.m20.mm.action.PushSave20ForA2Action.class
      .getName());

  // �����
  private String point = null;

  private ActionPlugInPoint(String point) {
    this.point = point;
  }

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.plugin.IPluginPoint#getComponent()
   */
  @Override
  public String getComponent() {
    return "m20";
  }

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.plugin.IPluginPoint#getModule()
   */
  @Override
  public String getModule() {
    return NCModule.PO.getSystemCode();
  }

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.plugin.IPluginPoint#getPoint()
   */
  @Override
  public String getPoint() {
    return this.point;
  }

}
