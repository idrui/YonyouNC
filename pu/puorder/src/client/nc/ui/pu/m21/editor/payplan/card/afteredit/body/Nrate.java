package nc.ui.pu.m21.editor.payplan.card.afteredit.body;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.rule.PayPlanMnyCalcByRate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

/**
 * 比率
 *
 * @since 6.0
 * @version 2011-1-23 下午12:38:23
 * @author wuxla
 */

public class Nrate implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    UFDouble oldNrate = (UFDouble) event.getOldValue();
    UFDouble nrate = (UFDouble) event.getValue();
    if (null == nrate) {
      panel.setBodyValueAt(oldNrate, row, AbstractPayPlanVO.NRATE);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0076")/*@res "比率不能为空"*/);
    }

    if (MathTool.compareTo(nrate, UFDouble.ZERO_DBL) < 0) {
      panel.setBodyValueAt(oldNrate, row, AbstractPayPlanVO.NRATE);
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0077")/*@res "比率必须大于等于0"*/);
    }

    // 可以超订单付款，所以只管计算
    CardEditorHelper card = new CardEditorHelper(panel);
    PayPlanMnyCalcByRate rule = new PayPlanMnyCalcByRate(card);
    rule.calcMnyByRate(row, nrate);
  }

}