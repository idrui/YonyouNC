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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������࣬���ڴ���༭��ĵ��۽���ϵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-1 ����10:48:34
 */
public abstract class AbstractRelationCalculateListener implements
    ICardBodyAfterEditEventListener {

  // �༭�ı�󴥷����¼�
  private CardBodyAfterEditEvent afterEditEvent;

  @Override
  public void afterEdit(CardBodyAfterEditEvent e) {

    this.setAfterEditEvent(e);
    // ע�ᵥ�۽�����ʱ�õ�����������
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
    // �������ݼ�ʵ�� ��ʼ�����ݹ�ϵ�����õ����ݼ�
    IRelationForItems item = this.getIRelationForItem(itemKey);
    IDataSetForCal data = this.getBillCardPanelDataSet(panel, rowNo, item);
    Calculator tool = new Calculator(data, UIScaleUtils.getScaleUtils());

    // �������� cond Ϊ����ʱ�Ĳ�������
    tool.calculate(this.getCalculatorCondition(panel, rowNo), itemKey);
  }

  public CardBodyAfterEditEvent getAfterEditEvent() {
    return this.afterEditEvent;
  }

  /**
   * ��������������������Ը�д�˷���������BillCardPanelDataSet�Ĵ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param panel
   * @param rowNo
   * @param item
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-3-29 ����11:05:41
   */
  public BillCardPanelDataSet getBillCardPanelDataSet(BillCardPanel panel,
      int rowNo, IRelationForItems item) {
    return new BillCardPanelDataSet(panel, rowNo, item);
  }

  /**
   * ������������������ע�ᵥ�۽�����ʱ�õ�����������
   * <p>
   * <b>����˵��</b>���۽�����ʱ�õ�����������
   * 
   * @param cond
   *          <p>
   * @since 6.0
   * @author hanbin
   * @param panel
   * @param row
   * @time 2010-3-1 ����10:56:58
   */
  public abstract Condition getCalculatorCondition(BillCardPanel panel, int row);

  /**
   * ��������������������Ը�д�˷����������ֶ�����ӳ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemKey
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-3-29 ����11:05:09
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
