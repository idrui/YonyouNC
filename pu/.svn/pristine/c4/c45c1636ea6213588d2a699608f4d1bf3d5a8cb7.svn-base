package nc.ui.pu.m25.editor.card.afteredit.header;

import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.m25.rule.InvoiceTaxEditHandler;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m25.rule.maintain.InvoiceVatTaxFillRule;

/**
 * 收货国编辑后处理器
 * 
 * @since 6.0
 * @version 2012-2-24 上午8:58:32
 * @author zhaoyha
 */
public class ReceCountry implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    if (null == event.getValue()) {
      return;
    }
    CardEditorHelper invoice = new CardEditorHelper(event.getBillCardPanel());
    // 一些处理的合并调用工具
    List<IPURemoteCallCombinator> rccRuleLst =
        new ArrayList<IPURemoteCallCombinator>();
    // 注册收货国变化取VAT税信息合并调用规则
    this.register_vattaxrule(invoice, rccRuleLst);
    // 批量合并处理
    for (IPURemoteCallCombinator rccrule : rccRuleLst) {
      rccrule.process();
    }
  }

  private void register_vattaxrule(CardEditorHelper invoice,
      List<IPURemoteCallCombinator> rccRuleLst) {
    IPURemoteCallCombinator vattaxrule =
        new InvoiceTaxEditHandler(invoice.getEditor(), null, null,
            new InvoiceVatTaxFillRule(new CardEditorHelper[] {
              invoice
            }));
    vattaxrule.prepare();
    rccRuleLst.add(vattaxrule);
  }

}
