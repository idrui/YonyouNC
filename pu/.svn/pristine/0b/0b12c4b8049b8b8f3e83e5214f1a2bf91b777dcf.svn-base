/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-15 下午03:16:16
 */
package nc.ui.pu.pub.editor.card.beforeedit;

import nc.itf.scmpub.reference.uap.bd.currency.CurrencyInfo;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.vo.pu.pub.util.PubSysParamUtil;

import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2011-6-9 下午02:54:52
 * @author 田锋涛
 */
public class GlobalExchangeRate implements
    ICardHeadTailBeforeEditEventListener, ICardBodyBeforeEditEventListener {
  /** 本币币种(本位币) */
  public static final String CCURRENCYID = "ccurrencyid";

  /** 币种 */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    int gloableValue = PubSysParamUtil.getNC002IntValue();
    CardPanelValueUtils util = new CardPanelValueUtils(e.getBillCardPanel());
    String globlaCur = CurrencyInfo.getGlobeDefaultCurrtype();
    // 全局本位币为空
    if (StringUtils.isEmpty(globlaCur)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    String origCur =
        util.getHeadTailStringValue(GlobalExchangeRate.CORIGCURRENCYID);
    String currency =
        util.getHeadTailStringValue(GlobalExchangeRate.CCURRENCYID);
    boolean editble = false;
    // 基于本币计算
    if (PubSysParamUtil.GLOBAL_CURRY_BASE == gloableValue) {
      editble = !globlaCur.equals(currency);
    }
    // 基于原币计算
    else if (PubSysParamUtil.GLOBAL_ORIG_BASE == gloableValue) {
      editble =
          StringUtils.isEmpty(origCur) ? false : !globlaCur.equals(origCur);
    }

    e.setReturnValue(Boolean.valueOf(editble));
  }

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    int gloableValue = PubSysParamUtil.getNC002IntValue();
    CardPanelValueUtils util = new CardPanelValueUtils(e.getBillCardPanel());
    String globlaCur = CurrencyInfo.getGlobeDefaultCurrtype();
    // 全局本位币为空
    if (StringUtils.isEmpty(globlaCur)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    String origCur =
        util.getHeadTailStringValue(GlobalExchangeRate.CORIGCURRENCYID);
    String currency =
        util.getHeadTailStringValue(GlobalExchangeRate.CCURRENCYID);
    boolean editble = false;
    // 基于本币计算
    if (PubSysParamUtil.GLOBAL_CURRY_BASE == gloableValue) {
      editble = !globlaCur.equals(currency);
    }
    // 基于原币计算
    else if (PubSysParamUtil.GLOBAL_ORIG_BASE == gloableValue) {
      editble =
          StringUtils.isEmpty(origCur) ? false : !globlaCur.equals(origCur);
    }

    e.setReturnValue(Boolean.valueOf(editble));
  }
}
