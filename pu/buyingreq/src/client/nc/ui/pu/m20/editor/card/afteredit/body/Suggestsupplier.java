/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-4 下午06:25:08
 */
package nc.ui.pu.m20.editor.card.afteredit.body;

import nc.ui.pu.m20.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.rule.SetEmployeeBySupplier;
import nc.vo.pu.m20.rule.SetPrice;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>建议供应商编辑后事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 下午06:25:08
 */
public class Suggestsupplier implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {

    // 根据参数PO29请购单价格处理规则设置主本币含税单价
    this.setPrice(event);

    // 如果采购员没有值，设置采购员未供应商专管业务员
    this.setEmployee(event);
  }

  private void setEmployee(CardBodyAfterEditEvent event) {
    CardEditorHelper kv = new CardEditorHelper(event.getBillCardPanel());
    int row = event.getRow();

    new SetEmployeeBySupplier().setEmployee(kv, new int[] {
      row
    });
  }

  private void setPrice(CardBodyAfterEditEvent event) {
    CardEditorHelper kv = new CardEditorHelper(event.getBillCardPanel());
    int row = event.getRow();

    new SetPrice().setPrice(kv, new int[] {
      row
    });
    // 进行询价，改变价格后，发起换算率联动计算，重新计算税价合计
    new RelationCalculate().calculate(event.getBillCardPanel(), event.getRow(),
        PraybillItemVO.NTAXPRICE);

  }

}
