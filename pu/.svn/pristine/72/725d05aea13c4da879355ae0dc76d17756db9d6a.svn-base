/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-24 下午04:03:29
 */
package nc.ui.pu.m25.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表体主本币无税金额编辑前处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-24 下午04:03:29
 */
public class OrigMoney implements ICardBodyBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    // 表体行没有物料不允许编辑
    Object objMaterialPK =
        event.getBillCardPanel().getBodyValueAt(event.getRow(),
            InvoiceItemVO.PK_MATERIAL);
    boolean isEditable =
        ((objMaterialPK != null) && (objMaterialPK.toString().trim().length() > 0));
    event.getBillCardPanel().setCellEditable(event.getRow(),
        InvoiceItemVO.NORIGMNY, isEditable);
  }

}
