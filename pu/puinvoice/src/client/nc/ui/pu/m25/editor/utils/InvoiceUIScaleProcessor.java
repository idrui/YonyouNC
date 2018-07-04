/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-30 上午09:33:53
 */
package nc.ui.pu.m25.editor.utils;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.ui.pubapp.scale.TotalValueScaleProcessor;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pu.m25.pub.InvoiceScaleProcessor;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票精度设置
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-30 上午09:33:53
 */
public class InvoiceUIScaleProcessor extends InvoiceScaleProcessor {

  public void orgChgScale(ShowUpableBillForm billform) {
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
