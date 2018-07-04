/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-15 下午03:16:16
 */
package nc.ui.pu.m25.editor.card.afteredit.header;

import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.ui.pu.m25.rule.InvoiceTaxEditHandler;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25.rule.maintain.InvoiceVatTaxFillRule;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存组织编辑后处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-15 下午03:16:16
 */
public class StockOrg implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    // 1. 库存组织编辑后不再询价
    // 2. 清空表体仓库
    CardEditorHelper invoice = new CardEditorHelper(event.getBillCardPanel());
    invoice.clearBodyValue(InvoiceItemVO.PK_STORDOC);
    // 一些处理的合并调用工具
    List<IPURemoteCallCombinator> rccRuleLst =
        new ArrayList<IPURemoteCallCombinator>();
    // 收货国
    boolean iscountrychg = this.setRececountry(event);
    if (iscountrychg) {
      // 注册收货国变化取VAT税信息合并调用规则
      this.register_vattaxrule(invoice, rccRuleLst);
    }
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

  private boolean setRececountry(CardHeadTailAfterEditEvent event) {
    CardEditorHelper invoice = new CardEditorHelper(event.getBillCardPanel());
    // 非自制的发票不能修改国家
    if (!InvoiceVOUtil.isSelfMade(invoice)) {
      return false;
    }
    String pk_storeorg =
        invoice.getHeadStringValue(InvoiceHeaderVO.PK_STOCKORG);
    String pk_country = null;
    // 如果收货库存组织为空，应该取结算财务组织
    if (StringUtils.isBlank(pk_storeorg)) {
      String pk_org = invoice.getHeadStringValue(InvoiceHeaderVO.PK_ORG);
      pk_country = FinanceOrgPubService.queryCountryByFinanceOrg(new String[] {
        pk_org
      }).get(pk_org);
    }
    else {
      pk_country = StockOrgPubService.queryCountryByStockOrg(new String[] {
        pk_storeorg
      }).get(pk_storeorg);
    }
    String old_country =
        invoice.getHeadStringValue(InvoiceHeaderVO.CRECECOUNTRYID);
    if (StringUtils.isBlank(pk_country) || pk_country.equals(old_country)) {
      return false;
    }
    invoice.setHeadValue(InvoiceHeaderVO.CRECECOUNTRYID, pk_country);
    return true;
  }

}
