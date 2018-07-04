/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-21 下午01:18:24
 */
package nc.ui.pu.m20.editor.card.afteredit.body;

import nc.ui.pu.m20.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.rule.ChangeRateUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-21 下午01:18:24
 */
public class Astunitid implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    // 设置换算率和数量

    BillCardPanel card = event.getBillCardPanel();
    int row = event.getRow();

    String castunitid =
        (String) card.getBodyValueAt(row, PraybillItemVO.CASTUNITID);
    if (StringUtil.isEmptyWithTrim(castunitid)) {
      card.setBodyValueAt(event.getOldValue(), row, PraybillItemVO.CASTUNITID);
      return;
    }

    new ChangeRateUtil().setChgRateAndFixedFlag(new CardEditorHelper(card),
        new int[] {
          row
        });
    // 编辑单位，换算率改变后，发起换算率联动计算
    new RelationCalculate().calculate(card, row, PraybillItemVO.VCHANGERATE);

  }

}
