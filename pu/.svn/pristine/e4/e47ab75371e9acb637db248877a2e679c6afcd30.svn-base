package nc.ui.pu.est.editor.relacalc;

import nc.ui.pu.est.view.EstEditor;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.calculator.data.BillModelDataSet;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.util.EstRelationCalcUtil;
import nc.vo.pu.est.util.FeeEstRelaItems;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>金额关系换算
 * </ul>
 * <p>
 */
public class FeeRelationCalculate implements
    IAppEventHandler<CardBodyAfterEditEvent> {

  private EstEditor editor = null;

  public EstEditor getEditor() {
    return this.editor;
  }

  @Override
  public void handleAppEvent(CardBodyAfterEditEvent e) {
    String itemKey = e.getKey();
    BillCardPanel panel = e.getBillCardPanel();
    int row = e.getRow();
    String pk_group = e.getContext().getPk_group();
    EstRelationCalcUtil util = new EstRelationCalcUtil(pk_group);
    IDataSetForCal dataset =
        new BillModelDataSet(panel.getBillModel(), row, new FeeEstRelaItems());
    util.calcDataSetForFee(dataset, itemKey);
    this.calTotalValue(e);
  }

  public void setEditor(EstEditor editor) {
    this.editor = editor;
  }

  /**
   * 计算合计相关
   * 
   * @param e
   */
  private void calTotalValue(CardBodyAfterEditEvent e) {
    CardPanelValueUtils cardUtil =
        new CardPanelValueUtils(e.getBillCardPanel());
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
    int curRow = this.getEditor().getModel().getSelectedRow();
    ListPanelValueUtils listUtil =
        new ListPanelValueUtils(this.getEditor().getBillListPanel());
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
}
