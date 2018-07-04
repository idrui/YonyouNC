package nc.ui.pu.pub.editor.card.listener;

import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>处理卡片上的表头表尾发生编辑改变后触发的事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-24 下午05:04:10
 */
public interface ICardHeadTailAfterEditEventListener extends ICardEventListener {

  /**
   * 方法功能描述：处理卡片上的表头表尾发生编辑改变后触发的事件
   * <p>
   * <b>参数说明</b>
   * 
   * @param event
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-2-25 上午09:24:24
   */
  public void afterEdit(CardHeadTailAfterEditEvent event);
}
