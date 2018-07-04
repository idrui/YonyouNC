/**
 * 
 */
package nc.ui.pu.m25.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>折本汇率编辑前处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-29 下午02:52:18
 */
public class ExchangeRate implements ICardHeadTailBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    // 1.原币为空不让编辑
    // 2.原币、本币相同不允许编辑
    CardEditorHelper helper = new CardEditorHelper(e.getBillCardPanel());
    // 本币
    String ccurrencyid =
        (String) helper.getHeadValue(InvoiceHeaderVO.CCURRENCYID);
    // 原币
    String corigcurrencyid =
        (String) helper.getHeadValue(InvoiceHeaderVO.CORIGCURRENCYID);

    if (StringUtil.isEmptyWithTrim(corigcurrencyid)
        || corigcurrencyid.equals(ccurrencyid)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    e.setReturnValue(Boolean.TRUE);

  }

}
