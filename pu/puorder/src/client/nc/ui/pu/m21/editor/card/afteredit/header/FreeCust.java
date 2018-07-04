/**
 * 
 */
package nc.ui.pu.m21.editor.card.afteredit.header;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pu.pub.util.SupplierFreeCustInfoUtil;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;

/**
 * <b>������Ҫ������¹��ܣ�ɢ��</b>
 * <ul>
 * <li></li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-25 ����11:02:34
 */
public class FreeCust implements ICardHeadTailAfterEditEventListener {

  /*
   * (non-Javadoc)
   * @see
   * nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener#afterEdit
   * (nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent)
   */
  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    CardEditorHelper util = new CardEditorHelper(event.getBillCardPanel());
    SupplierFreeCustInfoUtil custBankUtil = new SupplierFreeCustInfoUtil();
    custBankUtil.setBankaccbasname(OrderHeaderVO.PK_BANKDOC);
    custBankUtil.setSuppliername(OrderHeaderVO.PK_INVCSUPLLIER);
    custBankUtil.afterFreeCust(util);
  }

}
