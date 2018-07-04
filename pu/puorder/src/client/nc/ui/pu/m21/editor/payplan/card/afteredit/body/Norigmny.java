package nc.ui.pu.m21.editor.payplan.card.afteredit.body;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.rule.PayPlanRateCalcByMny;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

/**
 * @since 6.0
 * @version 2011-1-23 下午02:19:35
 * @author wuxla
 */

public class Norigmny implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    UFDouble norigmny =
        (UFDouble) panel.getBodyValueAt(row, AbstractPayPlanVO.NORIGMNY);
    UFDouble oldNorigmny = (UFDouble) event.getOldValue();

    if (null == norigmny) {
      panel.setBodyValueAt(oldNorigmny, row, AbstractPayPlanVO.NORIGMNY);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0074")/*
                                                                   * @res
                                                                   * "金额不能为空"
                                                                   */);
    }

    if (MathTool.compareTo(norigmny, UFDouble.ZERO_DBL) <= 0) {
      panel.setBodyValueAt(oldNorigmny, row, AbstractPayPlanVO.NORIGMNY);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0075")/*
                                                                   * @res
                                                                   * "金额必须大于0"
                                                                   */);
    }

    UFDouble naccumpayapporgmny =
        (UFDouble) panel.getBodyValueAt(row,
            AbstractPayPlanVO.NACCUMPAYAPPORGMNY);
    if (MathTool.compareTo(norigmny, naccumpayapporgmny) < 0) {
      panel.setBodyValueAt(oldNorigmny, row, AbstractPayPlanVO.NORIGMNY);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0368")/*
                                                                   * @res
                                                                   * "付款计划的金额不可小于累计付款申请金额"
                                                                   */);
    }
    UFDouble naccumpayorgmny =
        (UFDouble) panel.getBodyValueAt(row, AbstractPayPlanVO.NACCUMPAYORGMNY);
    if (MathTool.compareTo(norigmny, naccumpayorgmny) < 0) {
      panel.setBodyValueAt(oldNorigmny, row, AbstractPayPlanVO.NORIGMNY);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0369")/*
                                                                   * @res
                                                                   * "付款计划的金额不可小于累计付款金额"
                                                                   */);
    }

    CardEditorHelper card = new CardEditorHelper(panel);
    PayPlanRateCalcByMny rule = new PayPlanRateCalcByMny(card);
    rule.calcMnyByRate(row, norigmny);
  }

}
