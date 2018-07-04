package nc.bs.pu.costfactor.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

/**
 * 成本要素定义业务核心处理逻辑的二次开发插入点定义
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 下午02:25:42
 */
public enum BPPlugInPoint implements IPluginPoint {
  /**
   * 成本要素定义删除BP
   */
  DeleteBP("nc.bs.pu.costfactor.maintain.DeleteBP"),

  /**
   * 成本要素定义新增保存BP
   */
  InsertBP("nc.bs.pu.costfactor.maintain.InsertBP"),

  /**
   * 成本要素定义修改保存BP
   */
  UpdateBP("nc.bs.pu.costfactor.maintain.UpdateBP");

  // 插入点
  private String point = null;

  private BPPlugInPoint(String point) {
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
