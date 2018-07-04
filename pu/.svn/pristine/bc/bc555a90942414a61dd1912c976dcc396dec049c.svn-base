/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-11 上午10:30:39
 */
package nc.ui.pu.m4t.rule;

import nc.ui.pu.m4t.editor.body.after.RelationCalculate;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>币种和汇率，以及可编辑性
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-11 上午10:30:39
 */
public class CurrencyRelated {
  private BillCardPanel panel;

  public CurrencyRelated(BillCardPanel panel) {
    this.panel = panel;
  }

  /**
   * 方法功能描述：计算
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-9-11 上午10:37:40
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
   * 方法功能描述：设置币种和汇率
   * <p>
   * <b>参数说明</b>
   * 
   * @param fromOrder
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-19 下午04:47:51
   */
  public void setCurrencyAndExchangeRate(boolean fromOrder) {
    new CurrencyAndExchangeRate(this.panel)
        .setCurrencyAndExchangerate(fromOrder);

    new EditableByCurrency(this.panel).setEditable();

    this.relationCalculate();
  }
}
