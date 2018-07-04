package nc.ui.pu.m23.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m23.editor.card.beforeedit.header.BackReason;
import nc.ui.pu.m23.editor.card.beforeedit.header.Dept;
import nc.ui.pu.m23.editor.card.beforeedit.header.NeverEditHeaderItem;
import nc.ui.pu.m23.editor.card.beforeedit.header.PuPerson;
import nc.ui.pu.m23.editor.card.beforeedit.header.ReceivePerson;
import nc.ui.pu.m23.editor.card.beforeedit.header.VtranTypeCode;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.vo.pu.m23.entity.ArriveHeaderVO;

/**
 * <p>
 * <b>��������ͷ�༭�¼��Ĵ����࣬������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����༭ǰ�¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-24 ����05:00:09
 */
public class CardHeadTailBeforeEditEventHandler extends
    AbstractCardHeadTailBeforeEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailBeforeEditEventListener> listenerMap) {

    // ���š���Ա���ջ��ˡ���������
    listenerMap.put(ArriveHeaderVO.PK_DEPT_V, new Dept());

    listenerMap.put(ArriveHeaderVO.PK_PUPSNDOC, new PuPerson());
    listenerMap.put(ArriveHeaderVO.PK_RECEIVEPSNDOC, new ReceivePerson());
    // listenerMap.put(ArriveHeaderVO.VTRANTYPECODE, new VtranTypeCode());
    listenerMap.put(ArriveHeaderVO.CTRANTYPEID, new VtranTypeCode());

    // ������༭�ֶ�
    NeverEditHeaderItem neverEditItem = new NeverEditHeaderItem();
    listenerMap.put(ArriveHeaderVO.PK_ARRIVEORDER, neverEditItem);
    listenerMap.put(ArriveHeaderVO.PK_PURCHASEORG, neverEditItem);
    listenerMap.put(ArriveHeaderVO.PK_SUPPLIER, neverEditItem);
    listenerMap.put(ArriveHeaderVO.PK_BUSITYPE, neverEditItem);
    listenerMap.put(ArriveHeaderVO.VBACKREASON, new BackReason());
  }
}
