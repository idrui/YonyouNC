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
 * <b>本类主要完成以下功能：供应商 </b>
 * <ul>
 * <li>表头供应商编辑后处理
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-25 上午10:25:16
 */
public class Supplier implements ICardHeadTailAfterEditEventListener {
  /** 一些处理的合并调用工具 */
  private List<IPURemoteCallCombinator> rccRuleLst =
      new ArrayList<IPURemoteCallCombinator>();

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    String newValue = ValueUtils.getString(event.getValue());
    // 1.清空供应商时
    if (StringUtil.isEmptyWithTrim(newValue)) {
      // 币种
      // event.getBillCardPanel().setHeadItem(InvoiceHeaderVO.CORIGCURRENCYID,
      // null);
      // 收付款协议
      event.getBillCardPanel().setHeadItem(InvoiceHeaderVO.PK_PAYTERM, null);
      event.getBillCardPanel().setHeadItem(InvoiceHeaderVO.PK_BANKACCBAS, null);
      event.getBillCardPanel().setHeadItem(InvoiceHeaderVO.VBANKACCOUNT, null);
      return;
    }
    // ====== 供应商不空 ================
    // 1. 取供应商币种并设到表头币种上(询价) √
    // 2. 改变表头汇率，触发表体重新计算 √
    // 3. 电话（公式实现）√
    // 4. 银行账户 （公式实现）√
    // 5. 付款协议、付款单位， √
    // 6. 带部门及业务员？ √
    // 7. 散户处理 √
    // 8.VAT信息设置√
    // 9.询价√

    // 设置发货国
    this.setSendcountry(event);
    SupplierValueHandler handler =
        new SupplierValueHandler(event.getBillCardPanel());
    // 设置付款单位，付款协议，采购部门，采购员等
    handler.handleHeadDefaultValue();
    CardEditorHelper util = new CardEditorHelper(event.getBillCardPanel());
    String oldExchangeRate =
        util.getHeadStringValue(InvoiceHeaderVO.NEXCHANGERATE);
    String oldCurr = util.getHeadStringValue(InvoiceHeaderVO.CORIGCURRENCYID);
    // 设置表头币种
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

    // 设置表头汇率
    ExchangeRateUtil.setExchangerate(util);
    // 汇率变化后处理
    ExchangeRateUtil.calculateBodyRows(util, oldExchangeRate);

    // 注册－触发询价,针对表体所有行（远程调用合并）
    InvoiceQueryPriceHandler price =
        new InvoiceQueryPriceHandler(event.getBillCardPanel());
    price.prepareQueryPrice();

    // 注册-VAT取税规则合并调用
    this.register_vattaxrule(util);

    // 注册-设置散户的可编辑性及银行账号合并调用
    SupplierFreeCustInfoUtil scutil = new SupplierFreeCustInfoUtil();
    scutil.registerCombineRemoteCall(util);

    // 设置散户的可编辑性及银行账号
    scutil.setFreeCustBankAcc(util);
    // 执行远程调用的合并规则
    this.doRmtCombCallRule();
    // 执行询价
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
      // 处理完成后必须清空
      this.rccRuleLst.clear();
    }
  }

  private void register_vattaxrule(CardEditorHelper invoice) {
    // 清空供应商VAT注册码
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
    // 非自制的发票不能修改国家
    if (StringUtils.isBlank(pk_supplier) || !InvoiceVOUtil.isSelfMade(invoice)) {
      return false;
    }
    // 有缓存，这里不会有远程调用
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
