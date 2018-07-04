/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-2 ����11:25:54
 */
package nc.ui.pu.m25.editor.card.afteredit.body;

import nc.ui.pu.m25.editor.utils.UnitAndChangeRateUtil;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����λ�ı�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-2 ����11:25:54
 */
public class InvoiceAstUnit implements ICardBodyAfterEditEventListener {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent)
   */
  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    CardEditorHelper helper = new CardEditorHelper(event.getBillCardPanel());
    String astUnit =
        helper.getBodyStringValue(event.getRow(), InvoiceItemVO.CASTUNITID);
    if (StringUtil.isEmptyWithTrim(astUnit)) {
      return;
    }
    UnitAndChangeRateUtil.setChangeRate(helper, new int[] {
      event.getRow()
    });
  }

}
