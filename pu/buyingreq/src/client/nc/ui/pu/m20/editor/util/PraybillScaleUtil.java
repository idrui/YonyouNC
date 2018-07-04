package nc.ui.pu.m20.editor.util;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.ui.pubapp.scale.TotalValueScaleProcessor;
import nc.ui.uif2.editor.BillForm;
import nc.vo.pu.m20.rule.PrayBillScaleRule;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单精度设置
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-21 上午08:51:20
 */
public class PraybillScaleUtil extends PrayBillScaleRule {

  public void orgChgScale(BillForm billform) {
    String pk_group = billform.getModel().getContext().getPk_group();
    BillCardPanel bcp = billform.getBillCardPanel();
    this.setScale(new CardPaneScaleProcessor(pk_group, bcp),
        new TotalValueScaleProcessor(bcp));

  }

  public void setListScale(BillListPanel blp, String pk_group) {
    this.setScale(new ListPaneScaleProcessor(pk_group, blp),
        new TotalValueScaleProcessor(blp));

  }

  public void setSingleTableScale(BillListPanel blp, String pk_group) {

    this.setScaleForSingleTable(new ListPaneScaleProcessor(pk_group, blp),
        new TotalValueScaleProcessor(blp));
  }

}
