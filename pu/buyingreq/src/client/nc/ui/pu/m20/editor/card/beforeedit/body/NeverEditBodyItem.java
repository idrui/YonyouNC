package nc.ui.pu.m20.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ڿ���ĳЩ�ֶβ�����༭��Ҳ��Ϊ�˷�ֹ��Ӧ�ñ��༭���ֶΣ��û���ģ�������ÿ��Ա༭
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-28 ����03:11:59
 */
public class NeverEditBodyItem implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    e.setReturnValue(Boolean.FALSE);
  }
}
