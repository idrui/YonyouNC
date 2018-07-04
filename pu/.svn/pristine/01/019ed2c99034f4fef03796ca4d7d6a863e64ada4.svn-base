package nc.ui.pu.est.action.body;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.actions.BodyDelLineAction;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 删除按钮，主要是为了更新货物行的费用总计。
 * 
 * @since 6.0
 * @version 2013-5-24 上午09:23:10
 * @author wuxla
 */
public class EstBodyDelLineAction extends BodyDelLineAction {
  private BillListPanel billListPanel;

  @Override
  public void doAction() {
    super.doAction();
    if (this.getBillListPanel() == null) {
      return;
    }
    CardPanelValueUtils cardUtil = new CardPanelValueUtils(this.getCardPanel());
    int rows = cardUtil.getRowCount();
    UFDouble feeMny = UFDouble.ZERO_DBL;
    UFDouble feeTaxMny = UFDouble.ZERO_DBL;
    for (int i = 0; i < rows; i++) {
      feeMny =
          MathTool.add(feeMny,
              cardUtil.getBodyUFDoubleValue(i, FeeEstVO.NESTMNY));
      feeTaxMny =
          MathTool.add(feeTaxMny,
              cardUtil.getBodyUFDoubleValue(i, FeeEstVO.NESTTOTALMNY));
    }
    int curRow = ((BillManageModel) this.getModel()).getSelectedRow();
    ListPanelValueUtils listUtil =
        new ListPanelValueUtils(this.getBillListPanel());
    UFDouble estMny =
        listUtil.getHeadUFDoubleValueAt(curRow, GoodsEstVO.NESTMNY);
    UFDouble estTotalMny =
        listUtil.getHeadUFDoubleValueAt(curRow, GoodsEstVO.NESTTOTALMNY);
    listUtil.setHeadValueAt(feeMny, curRow, GoodsEstVO.NFEEMNY);
    listUtil.setHeadValueAt(feeTaxMny, curRow, GoodsEstVO.NFEETAXMNY);
    listUtil.setHeadValueAt(MathTool.add(estMny, feeMny), curRow,
        GoodsEstVO.NTOTALMNY);
    listUtil.setHeadValueAt(MathTool.add(estTotalMny, feeTaxMny), curRow,
        GoodsEstVO.NTOTALTAXMNY);
  }

  public BillListPanel getBillListPanel() {
    return this.billListPanel;
  }

  public void setBillListPanel(BillListPanel billListPanel) {
    this.billListPanel = billListPanel;
  }
}
