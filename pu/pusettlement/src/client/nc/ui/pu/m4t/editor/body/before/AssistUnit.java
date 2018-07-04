/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-9 下午01:20:40
 */
package nc.ui.pu.m4t.editor.body.before;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4t.entity.InitialEstItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>单位
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-9 下午01:20:40
 */
public class AssistUnit implements ICardBodyBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    String pk_material =
        (String) panel.getBodyValueAt(row, InitialEstItemVO.PK_MATERIAL);

    if (StringUtil.isEmptyWithTrim(pk_material)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    this.filterMeasdoc(panel, row);

    event.setReturnValue(Boolean.TRUE);
  }

  private void filterMeasdoc(BillCardPanel panel, int row) {
    FilterMeasdocRefUtils filter =
        new FilterMeasdocRefUtils(
            panel.getBodyItem(InitialEstItemVO.CASTUNITID));
    filter.setMaterialPk((String) panel.getBodyValueAt(row,
        InitialEstItemVO.PK_MATERIAL));
  }

}
