package nc.ui.pu.pub.editor.card.handler;

import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;

/**
 * <p>
 * <b>到货单表体编辑事件的代理处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>卡片表体编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-24 下午05:00:09
 */
public abstract class AbstractCardBodyAfterEditEventHandler
    extends
    AbstractCardEventHandler<ICardBodyAfterEditEventListener, CardBodyAfterEditEvent>
    implements IAppEventHandler<CardBodyAfterEditEvent> {

  /**
   * 用于单价金额计算
   */
  private AbstractRelationCalculateListener calculateListener;

  /**
   * 卡片上的表体发生编辑改变后触发的事件
   */
  private CardBodyAfterEditEvent cardBodyAfterEditEvent;

  public AbstractCardBodyAfterEditEventHandler() {
    super();
  }

  /**
   * 方法功能描述：注册单价金额关系换算的监听类。
   * <p>
   * <b>参数说明</b>
   * 
   * @param calculateListener
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-3-1 上午10:53:12
   */
  public abstract AbstractRelationCalculateListener getCalculateListener();

  public CardBodyAfterEditEvent getCardBodyAfterEditEvent() {
    return this.cardBodyAfterEditEvent;
  }

  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {

    this.setCardBodyAfterEditEvent(e);

    // 如果是第一次处理事件，注册事件监听类、注册单价金额关系换算的监听类。
    if (this.isFirstFlag()) {
      this.registerEventListener(this.getListenerMap());

      this.calculateListener = this.getCalculateListener();

      this.setFirstFlag(false);
    }
    if (PuAttrNameEnum.pk_material.name().equals(e.getKey())
        || PuAttrNameEnum.cqtunitid.name().equals(e.getKey())
        || PuAttrNameEnum.castunitid.name().equals(e.getKey())) {
      // 编辑后重新设置数量精度
      this.resetNumValue(e.getBillCardPanel(), e.getRow());
    }
    if (this.getEventListener(e) != null) {
      this.getEventListener(e).afterEdit(e);
    }

    // 单价金额计算
    if (this.calculateListener != null) {
      this.calculateListener.afterEdit(e);
    }
  }

  public void setCalculateListener(
      AbstractRelationCalculateListener calculateListener) {
    this.calculateListener = calculateListener;
  }

  public void setCardBodyAfterEditEvent(
      CardBodyAfterEditEvent cardBodyAfterEditEvent) {
    this.cardBodyAfterEditEvent = cardBodyAfterEditEvent;
  }

  private ICardBodyAfterEditEventListener getEventListener(
      CardBodyAfterEditEvent e) {
    String itemKey = e.getKey();

    return this.getListenerMap().get(itemKey);
  }

  /**
   * 重新设值（处理数量精度）
   * 
   * @param panel
   * @param row
   */
  private void resetNumValue(BillCardPanel panel, int row) {
    // 数量字段
    String[] keys =
        new String[] {
          PuAttrNameEnum.nastnum.name(), PuAttrNameEnum.nnum.name(),
          PuAttrNameEnum.nqtunitnum.name()
        };
    for (String key : keys) {
      Object value = panel.getBodyValueAt(row, key);
      if (value != null) {
        panel.setBodyValueAt(value, row, key);
      }
    }
  }

}
