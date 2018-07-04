/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-23 上午08:31:08
 */
package nc.ui.pu.m21.editor.card.afteredit;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.uif2.AppEvent;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货计划数量计算。没有金额计算。
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-23 上午08:31:08
 */
public class RPRelationCalculate implements IAppEventHandler<AppEvent> {

  private static class OrderBillCardPanelDataSet extends BillCardPanelDataSet {
    public OrderBillCardPanelDataSet(BillCardPanel cardPanel, int row,
        IRelationForItems item) {
      super(cardPanel, row, item);
    }

    @Override
    public String getCastunitid() {
      String value = this.getString(OrderReceivePlanVO.CASTUNITID);
      return value;
    }

    @Override
    public String getCqtunitid() {
      String value = this.getString(OrderReceivePlanVO.CQTUNITID);
      return value;
    }

    @Override
    public String getCunitid() {
      String value = this.getString(OrderReceivePlanVO.CUNITID);
      return value;
    }

    private String getString(String key) {
      Object value = this.getBillCardPanel().getBodyValueAt(this.getRow(), key);
      String str = ValueUtils.getString(value);
      return str;
    }

  }

  public void calculate(BillCardPanel panel, int[] rows, String itemKey) {
    IRelationForItems item = new RelationItemForCal();
    ScaleUtils scale = UIScaleUtils.getScaleUtils();
    for (int row : rows) {
      // 创建数据集实例 初始化数据关系计算用的数据集
      IDataSetForCal data = new OrderBillCardPanelDataSet(panel, row, item);
      Calculator tool = new Calculator(data, scale);//
      // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
      Condition cond = new Condition();// 创建参数实例
      // String material =
      // (String) panel.getBodyValueAt(row, OrderItemVO.PK_MATERIAL);
      // String cunitid =
      // (String) panel.getBodyValueAt(row, OrderReceivePlanVO.CUNITID);
      // String castunitid =
      // (String) panel.getBodyValueAt(row, OrderReceivePlanVO.CASTUNITID);
      // String cqtunitid =
      // (String) panel.getBodyValueAt(row, OrderReceivePlanVO.CQTUNITID);
      // 设置是否固定单位换算率
      cond.setIsFixNchangerate(true);
      // 是否固定报价单位换算率
      cond.setIsFixNqtunitrate(true);
      // 两个参数 cond 为计算时的参数条件
      tool.calculate(cond, itemKey);
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.event.IAppEventHandler#handleAppEvent(nc.ui.uif2.AppEvent)
   */
  @Override
  public void handleAppEvent(AppEvent e) {
    if (e instanceof CardBodyAfterEditEvent) {
      CardBodyAfterEditEvent event = (CardBodyAfterEditEvent) e;
      String itemKey = event.getKey();
      BillCardPanel panel = event.getBillCardPanel();
      if (itemKey == null || panel == null) {
        return;
      }

      int[] rows = new int[1];
      rows[0] = event.getRow();

      this.calculate(panel, rows, itemKey);
    }
  }

  private boolean isFixUnitRate(String material, String cunitid,
      String castunitid) {
    boolean flag = true;
    if (material == null || cunitid == null || castunitid == null) {
      return flag;
    }
    flag =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material,
            cunitid, castunitid);
    return flag;
  }
}
