/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-9 下午04:42:42
 */
package nc.ui.pu.m4t.editor.head.before;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>折本汇率：原币为空不让编辑，原币、本币相同不允许编辑
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-9 下午04:42:42
 */
public class ExchangeRate implements ICardHeadTailBeforeEditEventListener {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener#beforeEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent)
   */
  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel panel = e.getBillCardPanel();
    // 本币
    String ccurrencyid =
        (String) panel.getHeadItem(InitialEstHeaderVO.CCURRENCYID)
            .getValueObject();
    // 原币
    String corigcurrencyid =
        (String) panel.getHeadItem(InitialEstHeaderVO.CORIGCURRENCYID)
            .getValueObject();

    // 原币为空不让编辑
    // 原币、本币相同不允许编辑
    if (StringUtil.isEmptyWithTrim(corigcurrencyid)
        || corigcurrencyid.equals(ccurrencyid)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    e.setReturnValue(Boolean.TRUE);
  }

}
