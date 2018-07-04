/**
 * $�ļ�˵��$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-15 ����03:16:16
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
 * @version 2011-6-9 ����02:54:52
 * @author �����
 */
public class GlobalExchangeRate implements
    ICardHeadTailBeforeEditEventListener, ICardBodyBeforeEditEventListener {
  /** ���ұ���(��λ��) */
  public static final String CCURRENCYID = "ccurrencyid";

  /** ���� */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    int gloableValue = PubSysParamUtil.getNC002IntValue();
    CardPanelValueUtils util = new CardPanelValueUtils(e.getBillCardPanel());
    String globlaCur = CurrencyInfo.getGlobeDefaultCurrtype();
    // ȫ�ֱ�λ��Ϊ��
    if (StringUtils.isEmpty(globlaCur)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    String origCur =
        util.getHeadTailStringValue(GlobalExchangeRate.CORIGCURRENCYID);
    String currency =
        util.getHeadTailStringValue(GlobalExchangeRate.CCURRENCYID);
    boolean editble = false;
    // ���ڱ��Ҽ���
    if (PubSysParamUtil.GLOBAL_CURRY_BASE == gloableValue) {
      editble = !globlaCur.equals(currency);
    }
    // ����ԭ�Ҽ���
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
    // ȫ�ֱ�λ��Ϊ��
    if (StringUtils.isEmpty(globlaCur)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    String origCur =
        util.getHeadTailStringValue(GlobalExchangeRate.CORIGCURRENCYID);
    String currency =
        util.getHeadTailStringValue(GlobalExchangeRate.CCURRENCYID);
    boolean editble = false;
    // ���ڱ��Ҽ���
    if (PubSysParamUtil.GLOBAL_CURRY_BASE == gloableValue) {
      editble = !globlaCur.equals(currency);
    }
    // ����ԭ�Ҽ���
    else if (PubSysParamUtil.GLOBAL_ORIG_BASE == gloableValue) {
      editble =
          StringUtils.isEmpty(origCur) ? false : !globlaCur.equals(origCur);
    }

    e.setReturnValue(Boolean.valueOf(editble));
  }
}
