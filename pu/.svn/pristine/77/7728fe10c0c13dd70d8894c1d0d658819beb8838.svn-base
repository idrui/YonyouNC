/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-3 上午10:28:05
 */
package nc.ui.pu.m20.editor.card.afteredit.body;

import nc.ui.pu.m20.editor.card.afteredit.body.pub.SetOrdertrantypeF;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.rule.SetEmployee;
import nc.vo.pu.m20.rule.SetPrice;

/**
 * <p>
 * <b>采购组织编辑事件处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-2-3 上午10:28:05
 */
public class Purchaseorg implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {

    // 清空建议供应商和采购员
    this.clear(event.getBillCardPanel(), event.getRow());
    CardEditorHelper kv = new CardEditorHelper(event.getBillCardPanel());
    // 设置采购员
    new SetEmployee().setEmployee(kv, new int[] {
      event.getRow()
    });
    // 设置订单类型默认值和参照
    this.setDefOrdertrantypecode(event, kv);
    // 根据参数PO29请购单价格处理规则设置主本币含税单价
    new SetPrice().setPrice(kv, new int[] {
      event.getRow()
    });
  }

  private void clear(BillCardPanel card, int row) {
    card.setBodyValueAt(null, row, PraybillItemVO.PK_EMPLOYEE);
    card.setBodyValueAt(null, row, PraybillItemVO.PK_SUGGESTSUPPLIER);
  }

  private void setDefOrdertrantypecode(CardBodyAfterEditEvent event,
      CardEditorHelper kv) {
    new SetOrdertrantypeF().setDefOrdertrantypecode(event.getBillCardPanel(),
        kv, new int[] {
          event.getRow()
        });
  }
}
