package nc.ui.pu.m25.editor.card.afteredit.body;

import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.bd.vat.VATBDService;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoByTaxcodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.ui.pu.m25.editor.utils.RelationCalculate;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.rule.vat.BillItemVatTaxInfoSetter;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 税码编辑后处理
 * 
 * @since 6.0
 * @version 2012-2-24 下午3:12:26
 * @author zhaoyha
 */
public class Taxcode implements ICardBodyAfterEditEventListener {
  /** 一些处理的合并调用工具 */
  private List<IPURemoteCallCombinator> rccRuleLst =
      new ArrayList<IPURemoteCallCombinator>();

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    CardEditorHelper invoice = new CardEditorHelper(event.getBillCardPanel());
    // 执行远程调用的合并规则
    this.doRmtCombCallRule();
    // 税码编辑后设置VAT税率等信息，暂时不支持合并调用
    this.setvatvalue(invoice, new int[] {
      event.getRow()
    });
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

  private void setvatvalue(CardEditorHelper invoice, int[] rows) {
    UFDate billdate = invoice.getHeadUFDateValue(InvoiceHeaderVO.DBILLDATE);
    // 日期为空，则不能查询税信息
    if (null == billdate) {
      return;
    }

    VATInfoByTaxcodeQueryVO[] vatqvos =
        new VATInfoByTaxcodeQueryVO[rows.length];
    for (int i = 0; i < rows.length; i++) {
      String taxcode =
          invoice.getBodyStringValue(rows[i], InvoiceItemVO.CTAXCODEID);
      vatqvos[i] = new VATInfoByTaxcodeQueryVO(taxcode, billdate);
    }
    VATInfoVO[] queryVATInfo = VATBDService.queryVATInfo(vatqvos);
    // VATValueSetProvider puorgbuysellGetter = new
    // VATValueSetProvider(invoice);
    // puorgbuysellGetter.setFbuysellflagPos(PosEnum.head);
    BillItemVatTaxInfoSetter setter = new BillItemVatTaxInfoSetter(invoice);
    setter.setForceSet(true);
    RelationCalculate calc = new RelationCalculate();
    String pk_purchaseorg =
        invoice.getHeadStringValue(InvoiceHeaderVO.PK_PURCHASEORG);
    PricePriority pricePriority =
        PUSysParamUtil.getPO28For25(pk_purchaseorg) ? PricePriority.TAXPRICE_PRIOR_TO_PRICE
            : PricePriority.PRICE_PRIOR_TO_TAXPRICE;
    for (int i = 0; i < rows.length; i++) {
      VATInfoVO vatvo = queryVATInfo[i];
      if (null == vatvo) {
        continue;
      }
      String chgkey = setter.setVatTax(vatvo, rows[i], pricePriority);
      if (null == chgkey) {
        continue;
      }
      calc.calculate(invoice.getEditor(), new int[] {
        rows[i]
      }, chgkey);
    }
  }

}
