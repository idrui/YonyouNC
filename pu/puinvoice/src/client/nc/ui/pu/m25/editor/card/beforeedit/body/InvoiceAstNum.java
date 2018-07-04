/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-23 上午11:37:27
 */
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
 * <li>表体数量编辑前处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-23 上午11:37:27
 */
public class InvoiceAstNum implements ICardBodyBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    // 没有单位，不让编辑单位数量
    CardEditorHelper util = new CardEditorHelper(event.getBillCardPanel());
    String casunitid =
        util.getBodyStringValue(event.getRow(), InvoiceItemVO.CASTUNITID);
    if (StringUtil.isEmptyWithTrim(casunitid)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }
    event.setReturnValue(Boolean.TRUE);
  }
}
