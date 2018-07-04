package nc.ui.pu.m25.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算利润中心编辑前处理
 * </ul>
 * <p>
 * <p>
 * 
 * @since 6.0
 * @version 2014-9-15 下午2:58:36
 * @author luojw
 */

public class Apliabcenter implements ICardBodyBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    // 如果有来源，不允许编辑
    CardEditorHelper util = new CardEditorHelper(event.getBillCardPanel());
    String sourcetype =
        util.getBodyStringValue(event.getRow(), InvoiceItemVO.CSOURCETYPECODE);
    if (StringUtil.isEmptyWithTrim(sourcetype)) {
      event.setReturnValue(Boolean.TRUE);
      return;
    }
    event.setReturnValue(Boolean.FALSE);
  }

}
