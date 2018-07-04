/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-9 ����04:42:42
 */
package nc.ui.pu.m4t.editor.head.before;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۱����ʣ�ԭ��Ϊ�ղ��ñ༭��ԭ�ҡ�������ͬ������༭
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-9 ����04:42:42
 */
public class ExchangeRate implements ICardHeadTailBeforeEditEventListener {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel panel = e.getBillCardPanel();
    // ����
    String ccurrencyid =
        (String) panel.getHeadItem(InitialEstHeaderVO.CCURRENCYID)
            .getValueObject();
    // ԭ��
    String corigcurrencyid =
        (String) panel.getHeadItem(InitialEstHeaderVO.CORIGCURRENCYID)
            .getValueObject();

    // ԭ��Ϊ�ղ��ñ༭
    // ԭ�ҡ�������ͬ������༭
    if (StringUtil.isEmptyWithTrim(corigcurrencyid)
        || corigcurrencyid.equals(ccurrencyid)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    e.setReturnValue(Boolean.TRUE);
  }

}
