package nc.bs.pu.costfactor.plugin;

import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.vo.pubapp.res.NCModule;

/**
 * �ɱ�Ҫ�ض���ҵ����Ĵ����߼��Ķ��ο�������㶨��
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 ����02:25:42
 */
public enum BPPlugInPoint implements IPluginPoint {
  /**
   * �ɱ�Ҫ�ض���ɾ��BP
   */
  DeleteBP("nc.bs.pu.costfactor.maintain.DeleteBP"),

  /**
   * �ɱ�Ҫ�ض�����������BP
   */
  InsertBP("nc.bs.pu.costfactor.maintain.InsertBP"),

  /**
   * �ɱ�Ҫ�ض����޸ı���BP
   */
  UpdateBP("nc.bs.pu.costfactor.maintain.UpdateBP");

  // �����
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
