package nc.ui.pu.m25.editor.card.afteredit.header;

import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.m25.rule.InvoiceTaxEditHandler;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.rule.maintain.InvoiceOppTaxFlagFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceOrgVatCodeFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceVatTaxFillRule;

/**
 * 报税国编辑后处理器
 * 
 * @since 6.0
 * @version 2012-2-24 上午8:58:32
 * @author zhaoyha
 */
public class TaxCountry implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    if (null == event.getValue()) {
      return;
    }

    // 因为vat注册码的联动规则判断vat注册码不为空就不再联动了，所以在次需要先清空。
    event.getBillCardPanel().setHeadItem(InvoiceHeaderVO.VVATCODE, null);

    CardEditorHelper invoice = new CardEditorHelper(event.getBillCardPanel());
    // 一些处理的合并调用工具
    List<IPURemoteCallCombinator> rccRuleLst =
        new ArrayList<IPURemoteCallCombinator>();
    // 注册报税国变化取VAT税信息合并调用规则
    this.register_vattaxrule(invoice, rccRuleLst);
    // 批量合并处理
    for (IPURemoteCallCombinator rccrule : rccRuleLst) {
      rccrule.process();
    }
  }

  private void register_vattaxrule(CardEditorHelper invoice,
      List<IPURemoteCallCombinator> rccRuleLst) {
    CardEditorHelper[] invoices = new CardEditorHelper[] {
      invoice
    };
    IPURemoteCallCombinator vattaxrule =
        new InvoiceTaxEditHandler(invoice.getEditor(),
            new InvoiceOppTaxFlagFillRule(invoices), null,
            new InvoiceVatTaxFillRule(invoices));
    vattaxrule.prepare();

    IPURemoteCallCombinator orgvatrule =
        new InvoiceOrgVatCodeFillRule(invoices);
    orgvatrule.prepare();

    rccRuleLst.add(vattaxrule);
    rccRuleLst.add(orgvatrule);
  }

}
