/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-21 ����01:48:28
 */
package nc.ui.pu.m21.action;

import nc.ui.pu.m21.rule.PasteLineRule;
import nc.ui.pubapp.uif2app.actions.BodyPasteLineAction;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ճ����ť
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-21 ����01:48:28
 */
public class OrderPastLineAction extends BodyPasteLineAction {

  private static final long serialVersionUID = -3592160438687432524L;

  public OrderPastLineAction() {
    this.initClearItems();
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.AbstractBodyPasteLineAction#doAction()
   */
  @Override
  public void doAction() {
    super.doAction();
    int lastPastedRow = this.lastPastedRow();
    int rowlength =
        this.getCardPanel().getBodyPanel().getTableModel().getPasteLineNumer();
    PasteLineRule rule =
        new PasteLineRule(this.getCardPanel(), this.getModel());
    rule.setDefaultValue(lastPastedRow, rowlength);
    rule.cntLink(lastPastedRow, rowlength);
  }

  /**
   * ����������������Ҫ��յ��ֶ�
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-9-21 ����03:48:01
   */
  private void initClearItems() {
    PasteLineRule rule = new PasteLineRule();
    this.setClearItems(rule.getClearItems());
  }

}
