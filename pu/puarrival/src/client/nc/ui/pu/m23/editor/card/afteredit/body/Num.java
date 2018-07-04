package nc.ui.pu.m23.editor.card.afteredit.body;

import nc.ui.pu.m23.editor.card.utils.CalculateAstNum;
import nc.ui.pu.m23.editor.card.utils.CalculateMainNum;
import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.rule.ArriveNumCheck;
import nc.vo.pub.lang.UFDouble;

/**
 * 到货单数量的编辑后事件。
 * 
 * @since 6.0
 * @version 2012-8-11 下午02:55:06
 * @author lixyp
 */
public class Num implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent e) {
    this.relationCalculate(e);

    // 验证到货数量。
    CardEditorHelper card = new CardEditorHelper(e.getBillCardPanel());
    new ArriveNumCheck(card, (UFDouble) e.getOldValue()).check(e.getRow(),
        e.getKey());
  }

  /**
   * 赠品行编辑数量/主数量后联动计算赠品数量/主数量
   * 
   * @param e
   */
  private void dealPresentNum(BillCardPanel card, int currRow) {
    boolean bpresent =
        ArriveClientUtil.getBooleanCellValue(card, currRow,
            ArriveItemVO.BPRESENT);
    if (bpresent) {
      // 赠品数量 = 到货数量
      Object obj = card.getBodyValueAt(currRow, ArriveItemVO.NASTNUM);
      card.setBodyValueAt(obj, currRow, ArriveItemVO.NPRESENTASTNUM);

      // 赠品主数量 = 到货主数量
      obj = card.getBodyValueAt(currRow, ArriveItemVO.NNUM);
      card.setBodyValueAt(obj, currRow, ArriveItemVO.NPRESENTNUM);
    }
    else {
      // 清空赠品数量、赠品主数量
      ArriveClientUtil.clearBodyCellValues(card, currRow, new String[] {
        ArriveItemVO.NPRESENTASTNUM, ArriveItemVO.NPRESENTNUM
      });
    }
  }

  /**
   * 联动计算。
   * 
   * @param e
   */
  private void relationCalculate(CardBodyAfterEditEvent e) {
    int currRow = e.getRow();
    BillCardPanel card = e.getBillCardPanel();

    if (ArriveItemVO.NWASTNUM.equals(e.getKey())) {
      // 途耗主数量 如果修改的是主数量，则重新计算数量
      new CalculateAstNum(card, currRow).calculateAstNum();
    }
    else if (ArriveItemVO.NWASTASTNUM.equals(e.getKey())) {
      // 途耗数量 如果修改的是数量，则重新计算主数量
      new CalculateMainNum(card, currRow).calculateMainNum();
    }
    else if (ArriveItemVO.NPLANNUM.equals(e.getKey())) {
      // 应到主数量 如果修改的是主数量，则重新计算数量
      new CalculateAstNum(card, currRow).calculateAstNum();
    }
    else if (ArriveItemVO.NPLANASTNUM.equals(e.getKey())) {
      // 应到数量 如果修改的是数量，则重新计算主数量
      new CalculateMainNum(card, currRow).calculateMainNum();
    }
    else if (ArriveItemVO.NNUM.equals(e.getKey())) {
      this.dealPresentNum(card, currRow);
      // 到货主数量 如果修改的是主数量，则重新计算数量
      new CalculateAstNum(card, currRow).calculateAstNum();
    }
    else if (ArriveItemVO.NASTNUM.equals(e.getKey())) {
      this.dealPresentNum(card, currRow);
      // 到货数量 如果修改的是数量，则重新计算主数量
      new CalculateMainNum(card, currRow).calculateMainNum();
    }
  }
}
