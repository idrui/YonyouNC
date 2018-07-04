package nc.ui.pu.m21.action;

import nc.ui.pu.m21.rule.PasteLineRule;
import nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>粘帖至行尾按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-21 下午03:57:11
 */
public class OrderPasteToTailAction extends BodyPasteToTailAction {

  private static final long serialVersionUID = 1787768567561156176L;

  public OrderPasteToTailAction() {
    this.initClearItems();
  }

  /**
   * 父类方法重写
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
   * 方法功能描述：需要清空的字段
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-9-21 下午03:48:01
   */
  private void initClearItems() {
    PasteLineRule rule = new PasteLineRule();
    this.setClearItems(rule.getClearItems());
  }
}
