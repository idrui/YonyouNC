package nc.ui.pu.pub.editor.card.listener;

import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>处理卡片上的表体发生编辑改变后触发的事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-2-1 下午07:47:38
 */
public interface ICardBodyAfterEditEventListener extends ICardEventListener {

  /**
   * 方法功能描述：处理卡片上的表体发生编辑改变后触发的事件
   * <p>
   * <b>参数说明</b>
   * 
   * @param event
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-2-25 上午09:23:40
   */
  public void afterEdit(CardBodyAfterEditEvent event);
}
