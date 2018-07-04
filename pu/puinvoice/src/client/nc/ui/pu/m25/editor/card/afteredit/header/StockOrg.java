/**
 * $�ļ�˵��$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-15 ����03:16:16
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����֯�༭����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-15 ����03:16:16
 */
public class StockOrg implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    // 1. �����֯�༭����ѯ��
    // 2. ��ձ���ֿ�
    CardEditorHelper invoice = new CardEditorHelper(event.getBillCardPanel());
    invoice.clearBodyValue(InvoiceItemVO.PK_STORDOC);
    // һЩ����ĺϲ����ù���
    List<IPURemoteCallCombinator> rccRuleLst =
        new ArrayList<IPURemoteCallCombinator>();
    // �ջ���
    boolean iscountrychg = this.setRececountry(event);
    if (iscountrychg) {
      // ע���ջ����仯ȡVAT˰��Ϣ�ϲ����ù���
      this.register_vattaxrule(invoice, rccRuleLst);
    }
    // �����ϲ�����
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
    // �����Ƶķ�Ʊ�����޸Ĺ���
    if (!InvoiceVOUtil.isSelfMade(invoice)) {
      return false;
    }
    String pk_storeorg =
        invoice.getHeadStringValue(InvoiceHeaderVO.PK_STOCKORG);
    String pk_country = null;
    // ����ջ������֯Ϊ�գ�Ӧ��ȡ���������֯
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
