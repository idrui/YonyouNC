/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-28 上午09:52:44
 */
package nc.impl.pu.m20.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

/**
 * @description
 *              根据动作插入点对请购单进行审批、删除、保存、修改、弃审等操作
 * @scene
 *        请购单审批、删除、保存、修改、弃审
 * @param 无
 * @since 6.0
 * @version
 * @author linsf 2010-1-28 上午09:52:44
 */
public enum ActionPlugInPoint implements IPluginPoint {
  /**
   * 请购单审批
   */
  ApproveAction(nc.impl.pu.m20.action.PraybillApproveAction.class.getName()),

  /**
   * 请购单删除
   */
  DeleteAction(nc.impl.pu.m20.action.PraybillDeleteAction.class.getName()),

  /**
   * 请购单新增保存
   */
  InsertAction(nc.impl.pu.m20.action.PraybillInsertAction.class.getName()),

  /**
   * 请购单弃审
   */
  UnApproveAction(nc.impl.pu.m20.action.PraybillUnApproveAction.class.getName()),

  /**
   * 请购单修改保存
   */
  UpdateAction(nc.impl.pu.m20.action.PraybillUpdateAction.class.getName());

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
