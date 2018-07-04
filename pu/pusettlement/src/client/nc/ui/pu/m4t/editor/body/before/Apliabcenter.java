package nc.ui.pu.m4t.editor.body.before;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m4t.entity.InitialEstItemVO;

/**
 * �������ı༭�¼�
 * 
 * @since 6.5
 * @version 2014-4-9 ����03:58:53
 * @author mengjian
 */
public class Apliabcenter implements ICardBodyBeforeEditEventListener {

  @SuppressWarnings("restriction")
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    // ����Դ����ʱ�������Ĳ������޸�
    Object csourcebid =
        e.getBillCardPanel().getBodyValueAt(e.getRow(),
            InitialEstItemVO.CSOURCEBID);
    if (csourcebid != null) {
  	  e.setReturnValue(false);
    }
  }

}
