/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-24 下午03:34:00
 */
package nc.ui.pu.est.view;

import java.util.List;

import javax.swing.Action;

import nc.ui.pu.est.action.body.EstBodyDelLineAction;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.uif2app.actions.AbstractBodyTableExtendAction;
import nc.ui.pubapp.uif2app.actions.intf.ICardPanelDefaultActionProcessor;
import nc.ui.pubapp.uif2app.event.card.CardPanelEventTransformer;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.pub.util.ListUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>初始化卡片(表体),增加pubapp的框架配置等
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-24 下午03:34:00
 */
public class EstCardPanelInitializer {
  private BillCardPanel billCardPanel;

  private BillListPanel billListPanel;

  private BillManageModel model;

  /**
   * EstCardPanelInitializer 的构造子
   * 
   * @param billCardPanel
   * @param model
   */
  public EstCardPanelInitializer(BillCardPanel billCardPanel,
      BillListPanel billListPanel, BillManageModel model) {
    this.billCardPanel = billCardPanel;
    this.billListPanel = billListPanel;
    this.model = model;
  }

  /**
   * @return billCardPanel
   */
  public BillCardPanel getBillCardPanel() {
    return this.billCardPanel;
  }

  /**
   * @return model
   */
  public BillManageModel getModel() {
    return this.model;
  }

  public void initBodyLineActions(List<? extends Action> bodyActions) {
    if (ListUtil.isEmpty(bodyActions)) {
      return;
    }
    this.getBillCardPanel().addTabAction(IBillItem.BODY, bodyActions);
    for (int i = 0; i < bodyActions.size(); i++) {
      Action action = bodyActions.get(i);
      if (action instanceof AbstractBodyTableExtendAction) {
        action.setEnabled(false);
        ((AbstractBodyTableExtendAction) action).setCardPanel(this
            .getBillCardPanel());
        ((AbstractBodyTableExtendAction) action).setModel(this.getModel());
        if (action instanceof ICardPanelDefaultActionProcessor) {
          int type = ((ICardPanelDefaultActionProcessor) action).getType();
          this.getBillCardPanel().getBodyPanel()
              .replaceDefaultAction(type, action);

          if (action instanceof EstBodyDelLineAction) {
            ((EstBodyDelLineAction) action)
                .setBillListPanel(this.billListPanel);
          }

        }
        else {
          this.getBillCardPanel().getBodyPanel().addFixAction(action);
        }
      }
    }
  }

  public void initCardEventTransfer() {
    // 增加事件转发
    CardPanelEventTransformer eventTransformer =
        new CardPanelEventTransformer(this.getBillCardPanel(),
            (IAppModelEx) this.getModel());
    eventTransformer.setContext(this.getModel().getContext());
  }

}
