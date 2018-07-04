package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.ui.pu.m21.editor.card.afteredit.PriceQuoterUtil;
import nc.ui.pu.m21.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.m21.rule.EditableSetter;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.UnitAndChangeRate;
import nc.vo.pu.pub.enumeration.PriceParam;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>报价单位的编辑后事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-4-8 下午03:08:39
 */
public class QtUnit implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    int row = event.getRow();
    CardEditorHelper helper = new CardEditorHelper(event.getBillCardPanel());
    int[] rows = new int[] {
      row
    };

    // 带出换算率
    UnitAndChangeRate rate = new UnitAndChangeRate(helper);
    rate.setQtChangeRate(rows);

    // 数量、单价、金额的关联计算
    this.relationCalculate(event.getBillCardPanel(), rows);

    // mengjian 根据参数PO16自动询价条件自动询价
    this.setDefaultPrice(event, rows);

    // 控制跟计量单位相关的编辑性
    new EditableSetter(event.getBillCardPanel()).setEditableByUnit(rows);
  }

  private void relationCalculate(BillCardPanel panel, int[] rows) {
    RelationCalculate cal = new RelationCalculate();
    cal.calculate(panel, rows, OrderItemVO.VQTUNITRATE);
  }

  /**
   * 自动询价
   * mengjian
   * 
   * @param event
   * @param rows
   */
  @SuppressWarnings("restriction")
  private void setDefaultPrice(CardBodyAfterEditEvent event, int[] rows) {
    PriceQuoterUtil priceQuoterUtil = new PriceQuoterUtil();
    priceQuoterUtil.setDefaultPrice(event.getBillCardPanel(),
        PriceParam.Qtunit, rows);
  }

}
