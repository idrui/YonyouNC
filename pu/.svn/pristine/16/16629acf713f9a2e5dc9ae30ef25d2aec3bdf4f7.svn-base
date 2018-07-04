package nc.ui.pu.pub.editor.card.listener;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.calculator.data.BillCardPanelDataSet;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>抽象监听类，用于处理编辑后的单价金额关系换算
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-1 上午10:48:34
 */
public abstract class AbstractRelationCalculateListener implements
    ICardBodyAfterEditEventListener {

  // 编辑改变后触发的事件
  private CardBodyAfterEditEvent afterEditEvent;

  @Override
  public void afterEdit(CardBodyAfterEditEvent e) {

    this.setAfterEditEvent(e);
    // 注册单价金额计算时用到的条件参数
    if (this.getCalculatorCondition(e.getBillCardPanel(), e.getRow()) == null) {
      return;
    }

    String itemKey = e.getKey();
    BillCardPanel card = e.getBillCardPanel();
    if (itemKey == null || card == null) {
      return;
    }

    this.calculate(card, e.getRow(), itemKey);
  }

  public void calculate(BillCardPanel panel, int rowNo, String itemKey) {
    // 创建数据集实例 初始化数据关系计算用的数据集
    IRelationForItems item = this.getIRelationForItem(itemKey);
    IDataSetForCal data = this.getBillCardPanelDataSet(panel, rowNo, item);
    Calculator tool = new Calculator(data, UIScaleUtils.getScaleUtils());

    // 两个参数 cond 为计算时的参数条件
    tool.calculate(this.getCalculatorCondition(panel, rowNo), itemKey);
  }

  public CardBodyAfterEditEvent getAfterEditEvent() {
    return this.afterEditEvent;
  }

  /**
   * 方法功能描述：子类可以复写此方法，进行BillCardPanelDataSet的处理
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   * @param rowNo
   * @param item
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-3-29 上午11:05:41
   */
  public BillCardPanelDataSet getBillCardPanelDataSet(BillCardPanel panel,
      int rowNo, IRelationForItems item) {
    return new BillCardPanelDataSet(panel, rowNo, item);
  }

  /**
   * 方法功能描述：用于注册单价金额计算时用到的条件参数
   * <p>
   * <b>参数说明</b>单价金额计算时用到的条件参数
   * 
   * @param cond
   *          <p>
   * @since 6.0
   * @author hanbin
   * @param panel
   * @param row
   * @time 2010-3-1 上午10:56:58
   */
  public abstract Condition getCalculatorCondition(BillCardPanel panel, int row);

  /**
   * 方法功能描述：子类可以复写此方法，进行字段名的映射
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemKey
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-3-29 上午11:05:09
   */
  public IRelationForItems getIRelationForItem(String itemKey) {
    if (itemKey != null) {
      return new RelationItemForCal();
    }
    return new RelationItemForCal();
  }

  public void setAfterEditEvent(CardBodyAfterEditEvent afterEditEvent) {
    this.afterEditEvent = afterEditEvent;
  }
}
