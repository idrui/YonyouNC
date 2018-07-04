package nc.ui.pu.m25.editor.card.afteredit.header;

import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.ui.pu.m25.editor.utils.CurrencyUtil;
import nc.ui.pu.m25.editor.utils.ExchangeRateUtil;
import nc.ui.pu.m25.editor.utils.RelationCalculate;
import nc.ui.pu.m25.rule.InvoiceQueryPriceHandler;
import nc.ui.pu.m25.rule.InvoiceTaxEditHandler;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.handler.SupplierValueHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pu.pub.util.SupplierFreeCustInfoUtil;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25.rule.maintain.InvoiceSupplierVatCodeFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceVatTaxFillRule;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * <b>������Ҫ������¹��ܣ���Ӧ�� </b>
 * <ul>
 * <li>��ͷ��Ӧ�̱༭����
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-25 ����10:25:16
 */
public class Supplier implements ICardHeadTailAfterEditEventListener {
  /** һЩ����ĺϲ����ù��� */
  private List<IPURemoteCallCombinator> rccRuleLst =
      new ArrayList<IPURemoteCallCombinator>();

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    String newValue = ValueUtils.getString(event.getValue());
    // 1.��չ�Ӧ��ʱ
    if (StringUtil.isEmptyWithTrim(newValue)) {
      // ����
      // event.getBillCardPanel().setHeadItem(InvoiceHeaderVO.CORIGCURRENCYID,
      // null);
      // �ո���Э��
      event.getBillCardPanel().setHeadItem(InvoiceHeaderVO.PK_PAYTERM, null);
      event.getBillCardPanel().setHeadItem(InvoiceHeaderVO.PK_BANKACCBAS, null);
      event.getBillCardPanel().setHeadItem(InvoiceHeaderVO.VBANKACCOUNT, null);
      return;
    }
    // ====== ��Ӧ�̲��� ================
    // 1. ȡ��Ӧ�̱��ֲ��赽��ͷ������(ѯ��) ��
    // 2. �ı��ͷ���ʣ������������¼��� ��
    // 3. �绰����ʽʵ�֣���
    // 4. �����˻� ����ʽʵ�֣���
    // 5. ����Э�顢���λ�� ��
    // 6. �����ż�ҵ��Ա�� ��
    // 7. ɢ������ ��
    // 8.VAT��Ϣ���á�
    // 9.ѯ�ۡ�

    // ���÷�����
    this.setSendcountry(event);
    SupplierValueHandler handler =
        new SupplierValueHandler(event.getBillCardPanel());
    // ���ø��λ������Э�飬�ɹ����ţ��ɹ�Ա��
    handler.handleHeadDefaultValue();
    CardEditorHelper util = new CardEditorHelper(event.getBillCardPanel());
    String oldExchangeRate =
        util.getHeadStringValue(InvoiceHeaderVO.NEXCHANGERATE);
    String oldCurr = util.getHeadStringValue(InvoiceHeaderVO.CORIGCURRENCYID);
    // ���ñ�ͷ����
    Integer finvoicetype =
        (Integer) util.getHeadValue(InvoiceHeaderVO.FINVOICETYPE);
    if (finvoicetype != null && finvoicetype.intValue() != 1) {
      CurrencyUtil.setOrigcurrency(util);
    }
    String newCurr = util.getHeadStringValue(InvoiceHeaderVO.CORIGCURRENCYID);
    if (oldCurr == null || !oldCurr.equals(newCurr)) {
      int count = event.getBillCardPanel().getRowCount();
      int[] rows = new int[count];
      for (int i = 0; i < count; i++) {
        rows[i] = i;
        event.getBillCardPanel().setBodyValueAt(null, i, InvoiceItemVO.NPRICE);
      }
      RelationCalculate tool = new RelationCalculate();
      tool.calculate(event.getBillCardPanel(), rows, InvoiceItemVO.NPRICE);
    }

    // ���ñ�ͷ����
    ExchangeRateUtil.setExchangerate(util);
    // ���ʱ仯����
    ExchangeRateUtil.calculateBodyRows(util, oldExchangeRate);

    // ע�ᣭ����ѯ��,��Ա��������У�Զ�̵��úϲ���
    InvoiceQueryPriceHandler price =
        new InvoiceQueryPriceHandler(event.getBillCardPanel());
    price.prepareQueryPrice();

    // ע��-VATȡ˰����ϲ�����
    this.register_vattaxrule(util);

    // ע��-����ɢ���Ŀɱ༭�Լ������˺źϲ�����
    SupplierFreeCustInfoUtil scutil = new SupplierFreeCustInfoUtil();
    scutil.registerCombineRemoteCall(util);

    // ����ɢ���Ŀɱ༭�Լ������˺�
    scutil.setFreeCustBankAcc(util);
    // ִ��Զ�̵��õĺϲ�����
    this.doRmtCombCallRule();
    // ִ��ѯ��
    price.handleQueryPrice();

  }

  private void doRmtCombCallRule() {
    try {
      for (IPURemoteCallCombinator rccrule : this.rccRuleLst) {
        rccrule.process();
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    finally {
      // ������ɺ�������
      this.rccRuleLst.clear();
    }
  }

  private void register_vattaxrule(CardEditorHelper invoice) {
    // ��չ�Ӧ��VATע����
    invoice.setHeadValue(InvoiceHeaderVO.VVENDORVATCODE, null);

    IPURemoteCallCombinator vattaxrule =
        new InvoiceTaxEditHandler(invoice.getEditor(), null,
            new InvoiceSupplierVatCodeFillRule(new CardEditorHelper[] {
              invoice
            }), new InvoiceVatTaxFillRule(new CardEditorHelper[] {
              invoice
            }));
    vattaxrule.prepare();
    this.rccRuleLst.add(vattaxrule);
  }

  private boolean setSendcountry(CardHeadTailAfterEditEvent event) {
    CardEditorHelper invoice = new CardEditorHelper(event.getBillCardPanel());
    String pk_supplier = (String) event.getValue();
    // �����Ƶķ�Ʊ�����޸Ĺ���
    if (StringUtils.isBlank(pk_supplier) || !InvoiceVOUtil.isSelfMade(invoice)) {
      return false;
    }
    // �л��棬���ﲻ����Զ�̵���
    String pk_country = SupplierPubService.queryCountryBySupplier(new String[] {
      pk_supplier
    }).get(pk_supplier);
    String old_country =
        invoice.getHeadStringValue(InvoiceHeaderVO.CSENDCOUNTRYID);
    if (StringUtils.isBlank(pk_country) || pk_country.equals(old_country)) {
      return false;
    }
    invoice.setHeadValue(InvoiceHeaderVO.CSENDCOUNTRYID, pk_country);
    return true;
  }
}
