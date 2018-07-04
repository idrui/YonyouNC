/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-11 ����10:30:39
 */
package nc.ui.pu.m4t.rule;

import nc.ui.pu.m4t.editor.body.after.RelationCalculate;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ֺͻ��ʣ��Լ��ɱ༭��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-11 ����10:30:39
 */
public class CurrencyRelated {
  private BillCardPanel panel;

  public CurrencyRelated(BillCardPanel panel) {
    this.panel = panel;
  }

  /**
   * ������������������
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-9-11 ����10:37:40
   */
  public void relationCalculate() {
    int rowcount = this.panel.getRowCount();
    if (0 == rowcount) {
      return;
    }

    int rows[] = new int[rowcount];
    for (int i = 0; i < rowcount; ++i) {
      rows[i] = i;
    }
    RelationCalculate cal = new RelationCalculate();
    cal.calculate(this.panel, rows, InitialEstHeaderVO.NEXCHANGERATE);
  }

  /**
   * �����������������ñ��ֺͻ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param fromOrder
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-19 ����04:47:51
   */
  public void setCurrencyAndExchangeRate(boolean fromOrder) {
    new CurrencyAndExchangeRate(this.panel)
        .setCurrencyAndExchangerate(fromOrder);

    new EditableByCurrency(this.panel).setEditable();

    this.relationCalculate();
  }
}
