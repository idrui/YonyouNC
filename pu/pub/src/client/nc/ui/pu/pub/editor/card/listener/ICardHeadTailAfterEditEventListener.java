package nc.ui.pu.pub.editor.card.listener;

import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����Ƭ�ϵı�ͷ��β�����༭�ı�󴥷����¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-24 ����05:04:10
 */
public interface ICardHeadTailAfterEditEventListener extends ICardEventListener {

  /**
   * ������������������Ƭ�ϵı�ͷ��β�����༭�ı�󴥷����¼�
   * <p>
   * <b>����˵��</b>
   * 
   * @param event
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-2-25 ����09:24:24
   */
  public void afterEdit(CardHeadTailAfterEditEvent event);
}
