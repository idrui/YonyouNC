package nc.impl.pu.costfactor.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

/**
 * @description
 *              成本要素定义删除、新增保存、修改保存
 * @scene
 *        成本要素定义的操作的二次开发插入点定义
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:30:04
 * @author yanxm5
 */
public enum ActionPlugInPoint implements IPluginPoint {
  /**
   * 成本要素定义删除
   */
  DeleteAction("nc.impl.pu.costfactor.action.DeleteAction"),

  /**
   * 成本要素定义新增保存
   */
  InsertAction("nc.impl.pu.costfactor.action.InsertAction"),

  /**
   * 成本要素定义修改保存
   */
  UpdateAction("nc.impl.pu.costfactor.action.UpdateAction");

  // 插入点
  private String point = null;

  private ActionPlugInPoint(String point) {
    this.point = point;
  }

  @Override
  public String getComponent() {
    return "costfactor";
  }

  @Override
  public String getModule() {
    return NCModule.PO.getSystemCode();
  }

  @Override
  public String getPoint() {
    return this.point;
  }
}
