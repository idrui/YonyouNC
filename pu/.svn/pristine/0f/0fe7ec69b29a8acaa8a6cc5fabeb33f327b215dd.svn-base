/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-28 上午09:52:44
 */
package nc.pubimpl.pu.m20.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

/**
 * @description
 *              根据动作插入点对计划订单推式保存请购单\生产订单推式保存请购单等操作
 * @scene
 *        计划订单推式保存请购单\生产订单推式保存请购单
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午1:31:07
 * @author yanxm5
 */
public enum ActionPlugInPoint implements IPluginPoint {

  /**
   * 销售订单推式保存请购单
   */
  PushSave20For30Action(
      nc.pubimpl.pu.m20.so.m30.action.PushSave20For30Action.class.getName()),

  /**
   * 资产配置申请推式保存请购单
   */
  PushSave20For4A08Action(
      nc.pubimpl.pu.m20.aim.action.PushSave20For4A08Action.class.getName()),

  /**
   * 计划订单推式保存请购单
   */
  PushSave20ForA1Action(
      nc.pubimpl.pu.m20.mm.action.PushSave20For55B4Action.class.getName()),

  /**
   * 生产订单推式保存请购单
   */
  PushSave20ForA2Action(nc.pubimpl.pu.m20.mm.action.PushSave20ForA2Action.class
      .getName());

  // 插入点
  private String point = null;

  private ActionPlugInPoint(String point) {
    this.point = point;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.plugin.IPluginPoint#getComponent()
   */
  @Override
  public String getComponent() {
    return "m20";
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.plugin.IPluginPoint#getModule()
   */
  @Override
  public String getModule() {
    return NCModule.PO.getSystemCode();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.plugin.IPluginPoint#getPoint()
   */
  @Override
  public String getPoint() {
    return this.point;
  }

}
