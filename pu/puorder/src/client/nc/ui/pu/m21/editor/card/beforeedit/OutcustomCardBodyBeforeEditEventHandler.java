/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-1 ����04:43:48
 */
package nc.ui.pu.m21.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m21.editor.card.beforeedit.body.SetOnwayFieldEnable;
import nc.ui.pu.m21.editor.card.beforeedit.body.SupplierFilter;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>װ�˱༭ǰ�¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-1 ����04:43:48
 */
public class OutcustomCardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {

    // װ�˵��ݺ�
    listenerMap.put(OrderOnwayItemVO.VBILLCODE, new SetOnwayFieldEnable(
        PoTransTypeVO.BOUTCUSTOMCODE));

    // װ������
    listenerMap.put(OrderOnwayItemVO.DBILLDATE, new SetOnwayFieldEnable(
        PoTransTypeVO.BOUTCUSTOMDATE));

    listenerMap.put(OrderOnwayItemVO.CCARRIER, new SupplierFilter());

  }

}
