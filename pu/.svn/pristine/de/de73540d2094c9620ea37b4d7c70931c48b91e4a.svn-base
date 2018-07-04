/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-26 下午08:03:17
 */
package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.ui.pu.m21.rule.EditableByUnit;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>换算率
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-26 下午08:03:17
 */
public class ChangeRate implements ICardBodyBeforeEditEventListener {

  private String itemkey = null;

  public ChangeRate(String itemKey) {
    this.itemkey = itemKey;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    BillModel billModel = panel.getBillModel(panel.getCurrentBodyTableCode());
    int row = event.getRow();
    int col = billModel.getBodyColByKey(this.itemkey);

    String material =
        (String) panel.getBodyValueAt(row, OrderItemVO.PK_MATERIAL);
    if (StringUtil.isEmptyWithTrim(material)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    new EditableByUnit(panel).setEditable(new int[] {
      row
    });
    // 如果当前编辑的单元格不能编辑，返回false，主要用于表体的卡片编辑功能中的编辑性控制。
    event.setReturnValue(billModel.isCellEditable(row, col));
  }
}
