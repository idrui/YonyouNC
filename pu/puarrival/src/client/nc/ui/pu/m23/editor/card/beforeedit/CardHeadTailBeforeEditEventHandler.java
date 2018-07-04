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
 * <b>到货单表头编辑事件的处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>处理编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-24 下午05:00:09
 */
public class CardHeadTailBeforeEditEventHandler extends
    AbstractCardHeadTailBeforeEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailBeforeEditEventListener> listenerMap) {

    // 部门、人员、收货人、到货类型
    listenerMap.put(ArriveHeaderVO.PK_DEPT_V, new Dept());

    listenerMap.put(ArriveHeaderVO.PK_PUPSNDOC, new PuPerson());
    listenerMap.put(ArriveHeaderVO.PK_RECEIVEPSNDOC, new ReceivePerson());
    // listenerMap.put(ArriveHeaderVO.VTRANTYPECODE, new VtranTypeCode());
    listenerMap.put(ArriveHeaderVO.CTRANTYPEID, new VtranTypeCode());

    // 不允许编辑字段
    NeverEditHeaderItem neverEditItem = new NeverEditHeaderItem();
    listenerMap.put(ArriveHeaderVO.PK_ARRIVEORDER, neverEditItem);
    listenerMap.put(ArriveHeaderVO.PK_PURCHASEORG, neverEditItem);
    listenerMap.put(ArriveHeaderVO.PK_SUPPLIER, neverEditItem);
    listenerMap.put(ArriveHeaderVO.PK_BUSITYPE, neverEditItem);
    listenerMap.put(ArriveHeaderVO.VBACKREASON, new BackReason());
  }
}
