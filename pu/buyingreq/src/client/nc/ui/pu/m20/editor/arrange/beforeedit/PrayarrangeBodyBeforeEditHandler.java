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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version
 * @since
 * @author luojw
 * @time 2014-7-17 上午8:50:21
 */
public class PrayarrangeBodyBeforeEditHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  /*
   * 父类方法重写
   * @see
   * nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener
   * (java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    NeverEditBodyItem item = new NeverEditBodyItem();
    // 添加表头字段
    item.addNeverEditItem(PraybillHeaderVO.class, listenerMap);
    // 添加表体字段
    item.addNeverEditItem(PraybillItemVO.class, listenerMap);

    // 采购员
    listenerMap.put(PraybillItemVO.PK_EMPLOYEE, new Employee());
    // 采购组织
    listenerMap.put(PraybillItemVO.PK_PURCHASEORG_V, new PurchaseOrg());
    // 建议供应商
    listenerMap.put(PraybillItemVO.PK_SUGGESTSUPPLIER, new SuggestSupplier());
  }

}
