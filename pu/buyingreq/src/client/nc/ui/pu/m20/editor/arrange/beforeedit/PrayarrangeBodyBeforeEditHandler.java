/**
 * 
 */
package nc.ui.pu.m20.editor.arrange.beforeedit;

import java.util.Map;

import nc.ui.pu.m20.editor.arrange.beforeedit.body.Employee;
import nc.ui.pu.m20.editor.arrange.beforeedit.body.PurchaseOrg;
import nc.ui.pu.m20.editor.arrange.beforeedit.body.SuggestSupplier;
import nc.ui.pu.m23.editor.card.beforeedit.body.NeverEditBodyItem;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version
 * @since
 * @author luojw
 * @time 2014-7-17 ����8:50:21
 */
public class PrayarrangeBodyBeforeEditHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  /*
   * ���෽����д
   * @see
   * nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener
   * (java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    NeverEditBodyItem item = new NeverEditBodyItem();
    // ��ӱ�ͷ�ֶ�
    item.addNeverEditItem(PraybillHeaderVO.class, listenerMap);
    // ��ӱ����ֶ�
    item.addNeverEditItem(PraybillItemVO.class, listenerMap);

    // �ɹ�Ա
    listenerMap.put(PraybillItemVO.PK_EMPLOYEE, new Employee());
    // �ɹ���֯
    listenerMap.put(PraybillItemVO.PK_PURCHASEORG_V, new PurchaseOrg());
    // ���鹩Ӧ��
    listenerMap.put(PraybillItemVO.PK_SUGGESTSUPPLIER, new SuggestSupplier());
  }

}
