/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 ����08:38:51
 */
package nc.ui.pu.m422x.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m422x.editor.card.beforeedit.header.AppDept;
import nc.ui.pu.m422x.editor.card.beforeedit.header.AppPsn;
import nc.ui.pu.m422x.editor.card.beforeedit.header.Trantypecode;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����������뵥��Ƭ�����ͷ�༭ǰ�¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-16 ����08:38:51
 */
public class CardHeadTailBeforeEditEventHandler extends
    AbstractCardHeadTailBeforeEditEventHandler {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailBeforeEditEventListener> listenerMap) {
    listenerMap.put(StoreReqAppHeaderVO.PK_APPDEPTH_V, new AppDept());
    listenerMap.put(StoreReqAppHeaderVO.PK_APPPSNH, new AppPsn());
    listenerMap.put(StoreReqAppHeaderVO.CTRANTYPEID, new Trantypecode());

  }

}
