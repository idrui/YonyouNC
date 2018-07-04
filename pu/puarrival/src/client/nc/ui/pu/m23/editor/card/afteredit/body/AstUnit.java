package nc.ui.pu.m23.editor.card.afteredit.body;

import nc.ui.pu.m23.editor.card.utils.CalculateAstNum;
import nc.ui.pu.m23.editor.card.utils.ChangeRateUtil;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m23.entity.ArriveItemVO;

/**
 * <p>
 * <b>收货仓库编辑事件处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>编辑后：
 * <li>设置“是否固定换算率”、“换算率”
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 下午01:25:25
 */
public class AstUnit implements ICardBodyAfterEditEventListener {

  private AbstractRelationCalculateListener calculate;

  public AstUnit(AbstractRelationCalculateListener calculateListener) {
    this.calculate = calculateListener;
  }

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel card = event.getBillCardPanel();
    // 设置“是否固定换算率”、“换算率”
    ChangeRateUtil.setChgRateAndFixedFlag(card, event.getRow());

    // 业务单位修改后，换算率会变，需要修改
    this.calculate.calculate(card, event.getRow(), ArriveItemVO.VCHANGERATE);
    // 更新各种数量的主数量
    new CalculateAstNum(card, event.getRow()).calculateAstNum();
  }
}
