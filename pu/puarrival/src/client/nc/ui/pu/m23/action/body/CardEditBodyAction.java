package nc.ui.pu.m23.action.body;

import nc.ui.pu.m23.refmodel.MaterialRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.actions.BodyLineEditAction;
import nc.vo.pu.m23.entity.ArriveItemVO;

public class CardEditBodyAction extends BodyLineEditAction {

  private static final long serialVersionUID = 7312489604641918260L;

  public CardEditBodyAction() {
    super();
  }

  @Override
  public void doAction() {
    int row = super.getCardPanel().getBillTable().getSelectedRow();
    // 如果没有选中表体行，直接表体点“卡片编辑”按钮，默认应该取表体第一行
    if (row == -1) {
      row = 0;
    }
    UIRefPane materialRefPane =
        (UIRefPane) super.getCardPanel().getBodyItem(ArriveItemVO.PK_MATERIAL)
            .getComponent();
    MaterialRefModel materialRefModel =
        (MaterialRefModel) materialRefPane.getRefModel();
    materialRefModel.initialize(this.getCardPanel(), row);
    super.doAction();
  }
}
