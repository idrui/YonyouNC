package nc.ui.pu.m23.editor.card.afteredit;

import java.util.Map;

import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;

/**
 * <p>
 * <b>��������ͷ�༭�¼��Ĵ����࣬������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����༭���¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-24 ����05:00:09
 */
public class CardHeadTailAfterEditEventHandler extends
    AbstractCardHeadTailAfterEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailAfterEditEventListener> listenerMap) {

    // ҵ��Ա
    // listenerMap.put(ArriveHeaderVO.PK_PUPSNDOC, new PUPersonDoc());
  }

}
