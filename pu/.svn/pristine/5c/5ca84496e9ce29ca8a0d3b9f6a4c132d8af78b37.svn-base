/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-9 上午09:47:11
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>币种
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-9 上午09:47:11
 */
public class Corigcurrencyid implements ICardHeadTailAfterEditEventListener {

  /**
   * 父类方法重写
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
                                                                   * "原币币种不能为空，请录入原币币种"
                                                                   */);
    }

    CurrencyRelated rule = new CurrencyRelated(event.getBillCardPanel());
    rule.setCurrencyAndExchangeRate(false);
  }
}
