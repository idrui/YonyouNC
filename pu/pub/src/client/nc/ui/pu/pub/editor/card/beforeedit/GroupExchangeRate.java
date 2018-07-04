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
 * @version 2011-6-9 下午02:55:07
 * @author 田锋涛
 */
public class GroupExchangeRate implements ICardHeadTailBeforeEditEventListener,
    ICardBodyBeforeEditEventListener {
  /** 本币币种(本位币) */
  public static final String CCURRENCYID = "ccurrencyid";

  /** 币种 */
  public static final String CORIGCURRENCYID = "corigcurrencyid";

  /** 所属集团 */
  public static final String PK_GROUP = "pk_group";

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    CardPanelValueUtils util = new CardPanelValueUtils(e.getBillCardPanel());
    String pk_group = util.getHeadTailStringValue(GroupExchangeRate.PK_GROUP);
    int groupValue = PubSysParamUtil.getNC001IntValue(pk_group);
    String groupCur = CurrencyInfo.getDefaultCurrtypeByOrgID(pk_group);
    // 集团本位币为空
    if (StringUtils.isEmpty(groupCur)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    String origCur =
        util.getHeadTailStringValue(GroupExchangeRate.CORIGCURRENCYID);
    String currency =
        util.getHeadTailStringValue(GroupExchangeRate.CCURRENCYID);
    boolean editble = false;
    // 基于本币计算
    if (PubSysParamUtil.GROUP_CURRY_BASE == groupValue) {
      editble = !groupCur.equals(currency);
    }
    // 基于原币计算
    else if (PubSysParamUtil.GROUP_ORIG_BASE == groupValue) {
      editble =
          StringUtils.isEmpty(origCur) ? false : !groupCur.equals(origCur);
    }

    e.setReturnValue(Boolean.valueOf(editble));
  }

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    CardPanelValueUtils util = new CardPanelValueUtils(e.getBillCardPanel());
    String pk_group = util.getHeadTailStringValue(GroupExchangeRate.PK_GROUP);
    int groupValue = PubSysParamUtil.getNC001IntValue(pk_group);
    String groupCur = CurrencyInfo.getDefaultCurrtypeByOrgID(pk_group);
    // 集团本位币为空
    if (StringUtils.isEmpty(groupCur)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    String origCur =
        util.getHeadTailStringValue(GroupExchangeRate.CORIGCURRENCYID);
    String currency =
        util.getHeadTailStringValue(GroupExchangeRate.CCURRENCYID);
    boolean editble = false;
    // 基于本币计算
    if (PubSysParamUtil.GROUP_CURRY_BASE == groupValue) {
      editble = !groupCur.equals(currency);
    }
    // 基于原币计算
    else if (PubSysParamUtil.GROUP_ORIG_BASE == groupValue) {
      editble =
          StringUtils.isEmpty(origCur) ? false : !groupCur.equals(origCur);
    }

    e.setReturnValue(Boolean.valueOf(editble));
  }
}
