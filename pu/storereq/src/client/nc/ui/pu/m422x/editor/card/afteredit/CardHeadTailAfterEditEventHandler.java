/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 ����08:29:51
 */
package nc.ui.pu.m422x.editor.card.afteredit;

import java.util.Map;

import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;

import nc.ui.pu.m422x.editor.card.afteredit.header.Appdepth;
import nc.ui.pu.m422x.editor.card.afteredit.header.Apppsnh;
import nc.ui.pu.m422x.editor.card.afteredit.header.Dbilldate;
import nc.ui.pu.m422x.editor.card.afteredit.header.Freqtypeflag;
import nc.ui.pu.m422x.editor.card.afteredit.header.Project;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����������뵥��Ƭ��ͷ�༭���¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-16 ����08:29:51
 */
public class CardHeadTailAfterEditEventHandler extends
    AbstractCardHeadTailAfterEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailAfterEditEventListener> listenerMap) {
    // ���벿�ŵı༭���¼�
    listenerMap.put(StoreReqAppHeaderVO.PK_APPDEPTH_V, new Appdepth());
    // �������ڵı༭���¼�
    listenerMap.put(StoreReqAppHeaderVO.DBILLDATE, new Dbilldate());
    listenerMap.put(StoreReqAppHeaderVO.PK_APPPSNH, new Apppsnh());
    listenerMap.put(StoreReqAppHeaderVO.FREQTYPEFLAG, new Freqtypeflag());
    listenerMap.put(StoreReqAppHeaderVO.PK_PROJECT, new Project());
  }

}
