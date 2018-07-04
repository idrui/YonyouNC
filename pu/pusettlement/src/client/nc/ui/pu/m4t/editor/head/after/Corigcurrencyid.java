/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-9 ����09:47:11
 */
package nc.ui.pu.m4t.editor.head.after;

import nc.ui.pu.m4t.rule.CurrencyRelated;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-9 ����09:47:11
 */
public class Corigcurrencyid implements ICardHeadTailAfterEditEventListener {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent)
   */
  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    Object value = event.getValue();
    if (ObjectUtil.isEmptyWithTrim(value)) {
      event.getBillCardPanel().setHeadItem(InitialEstHeaderVO.CORIGCURRENCYID,
          event.getOldValue());
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0048")/*
                                                                   * @res
                                                                   * "ԭ�ұ��ֲ���Ϊ�գ���¼��ԭ�ұ���"
                                                                   */);
    }

    CurrencyRelated rule = new CurrencyRelated(event.getBillCardPanel());
    rule.setCurrencyAndExchangeRate(false);
  }
}
