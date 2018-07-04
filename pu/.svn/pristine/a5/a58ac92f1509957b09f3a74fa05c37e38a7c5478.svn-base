package nc.ui.pu.pub.editor.card.handler;

import org.apache.commons.lang.ArrayUtils;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;

/**
 * <p>
 * <b>到货单表头编辑事件的代理处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>处理编辑前事件
 * <li>处理编辑后事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-24 下午05:00:09
 */
public abstract class AbstractCardHeadTailAfterEditEventHandler
    extends
    AbstractCardEventHandler<ICardHeadTailAfterEditEventListener, CardHeadTailAfterEditEvent>
    implements IAppEventHandler<CardHeadTailAfterEditEvent> {

  public AbstractCardHeadTailAfterEditEventHandler() {
    super();
  }

  @Override
  public void handleAppEvent(CardHeadTailAfterEditEvent e) {

    this.setCardEditEvent(e);

    // 如果是第一次处理事件，注册事件监听类
    if (this.isFirstFlag()) {
      this.registerEventListener(this.getListenerMap());

      this.setFirstFlag(false);
    }

    // 没有配置,直接返回
    if (this.getEventListener(e) == null) {
      return;
    }

    /* 2015年12月10日 wangweir 事件响应前设置表体不合计,处理表体多行的合计计算效率问题 Begin*/
    setBodyCalculate(e, false);
    
    // 编辑后事件
    this.getEventListener(e).afterEdit(e);
    
    setBodyCalculate(e, true);
    /* 2015年12月10日 wangweir End*/
  }

  /**
   * 事件响应前设置表体合计状态
   * 
   * @param e CardHeadTailAfterEditEvent
   * @param isEnable true if enable body calculate
   */
  protected void setBodyCalculate(CardHeadTailAfterEditEvent e,
      boolean isEnable) {
    String[] tableCodes =
        e.getBillCardPanel().getBillData().getBodyTableCodes();

    if (ArrayUtils.isEmpty(tableCodes)) {
      return;
    }
    for (String tableCode : tableCodes) {
      e.getBillCardPanel().getBillModel(tableCode).setNeedCalculate(isEnable);
    }
  }

  private ICardHeadTailAfterEditEventListener getEventListener(
      CardHeadTailAfterEditEvent e) {
    String itemKey = e.getKey();

    return this.getListenerMap().get(itemKey);
  }
}
