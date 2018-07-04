package nc.ui.pu.m25.editor.card.afteredit.body;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.ui.pu.m25.editor.utils.RelationCalculate;
import nc.ui.pu.m25.editor.utils.TaxRateAndTypeUtil;
import nc.ui.pu.m25.rule.InvoiceQueryPriceHandler;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.rule.vat.BillItemVatTaxInfoSetter;
import nc.vo.pu.pub.rule.vat.TaxValue;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表体物料编辑后处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-26 上午10:38:21
 */
public class Material implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {

    RefMoreSelectedUtils utils =
        new RefMoreSelectedUtils(event.getBillCardPanel());
    event.getBillCardPanel().getBillModel().setNeedCalculate(false);
    // 处理多选情况
    int[] rows =
        utils.refMoreSelected(event.getRow(), InvoiceItemVO.PK_MATERIAL, true);
    // 清空物料
    if (rows.length == 1 && ObjectUtil.isEmptyWithTrim(event.getValue())) {
      event.getBillCardPanel().getBillModel().setNeedCalculate(true);
      return;

    }
    
    //清空批次号信息
    this.clearBatchCode(event.getBillCardPanel(), rows);
    
    // 物料编辑时（已经有物料的情况下，单位可能被该动过）
    this.calculateByNum(event.getBillCardPanel(), rows);

    // ---------远程调用合并-------------------------------------
    // 多选处理,询计划价，税率
    TaxRateAndTypeUtil util =
        new TaxRateAndTypeUtil(event.getBillCardPanel(), rows);
    util.prepare();
    // 询价远程调用合并
    InvoiceQueryPriceHandler price =
        new InvoiceQueryPriceHandler(event.getBillCardPanel(), rows);
    price.prepareQueryPrice();
    // VAT信息设置规则的远程调用
    TaxValue taxvaluerule =
        this.getVatValueRule(event.getBillCardPanel(), rows);

    // ---------远程调用合并执行真正的处理---------------------
    // VAT信息设置
    this.setVatVule(taxvaluerule, event.getBillCardPanel(), rows);
    // 计划价
    util.setPlanPrice();
    // 触发询价，劳务折扣类物料不参与询价！！
    price.handleQueryPrice();
    event.getBillCardPanel().getBillModel().setNeedCalculate(true);
  }

  /**
   * 编辑物料时重新设置单位，并用主数量联动
   * 
   * @param event
   * @param rows
   */
  private void calculateByNum(BillCardPanel panel, int[] rows) {
    if (ArrayUtils.isEmpty(rows)) {
      return;
    }
    ScaleUtils scale = new ScaleUtils(AppContext.getInstance().getPkGroup());
    for (int row : rows) {
      panel.setBodyValueAt(scale.adjustHslScale("1.00/1.00"), row,
          InvoiceItemVO.VCHANGERATE);
    }
    RelationCalculate calc = new RelationCalculate();
    calc.calculate(panel, rows, InvoiceItemVO.NNUM);
  }

  private TaxValue getVatValueRule(BillCardPanel bcp, int[] rows) {
    CardEditorHelper invoice = new CardEditorHelper(bcp);
    String sendcountry =
        invoice.getHeadStringValue(InvoiceHeaderVO.CSENDCOUNTRYID);
    String rececountry =
        invoice.getHeadStringValue(InvoiceHeaderVO.CRECECOUNTRYID);
    String taxcountry =
        invoice.getHeadStringValue(InvoiceHeaderVO.CTAXCOUNTRYID);
    UFDate billdate = invoice.getHeadUFDateValue(InvoiceHeaderVO.DBILLDATE);
    Integer fbsflag =
        (Integer) invoice.getHeadValue(InvoiceHeaderVO.FBUYSELLFLAG);
    String pk_supplier =
        invoice.getHeadStringValue(InvoiceHeaderVO.PK_SUPPLIER);
    UFBoolean triatrade =
        ValueUtils.getUFBoolean(invoice
            .getHeadValue(InvoiceHeaderVO.BTRIATRADEFLAG));
    String pk_org = invoice.getHeadStringValue(InvoiceHeaderVO.PK_ORG);
    VATInfoQueryVO[] vatqvos = new VATInfoQueryVO[rows.length];
    for (int i = 0; i < rows.length; i++) {
      if (StringUtils.isBlank(sendcountry) || StringUtils.isBlank(rececountry)
          || StringUtils.isBlank(taxcountry)
          || StringUtils.isBlank(pk_supplier) || null == billdate
          || null == fbsflag) {
        continue;
      }
      String pk_material =
          invoice.getBodyStringValue(rows[i], InvoiceItemVO.PK_MATERIAL);
      vatqvos[i] =
          new VATInfoQueryVO(taxcountry, BuySellFlagEnum.valueOf(fbsflag),
              triatrade, sendcountry, rececountry, pk_supplier, pk_material,
              billdate, pk_org);
    }
    TaxValue tv = new TaxValue(vatqvos);
    tv.prepare();
    return tv;
  }

  private void setVatVule(TaxValue tv, BillCardPanel bcp, int[] rows) {
    CardEditorHelper invoice = new CardEditorHelper(bcp);
    // VATValueSetProvider puorgbuysellGetter = new
    // VATValueSetProvider(invoice);
    // puorgbuysellGetter.setFbuysellflagPos(PosEnum.head);
    BillItemVatTaxInfoSetter setter = new BillItemVatTaxInfoSetter(invoice);
    RelationCalculate calc = new RelationCalculate();
    tv.process();
    VATInfoVO[] vatinfos = tv.getVatinfos();
    String pk_purchaseorg =
        invoice.getHeadStringValue(InvoiceHeaderVO.PK_PURCHASEORG);
    PricePriority pricePriority =
        PUSysParamUtil.getPO28For25(pk_purchaseorg) ? PricePriority.TAXPRICE_PRIOR_TO_PRICE
            : PricePriority.PRICE_PRIOR_TO_TAXPRICE;
    for (int i = 0; i < rows.length; i++) {
      VATInfoVO vatvo = vatinfos[i];
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

  private void clearBatchCode(BillCardPanel panel, int[] rows){
  	for (int i : rows) {
			panel.setBodyValueAt(null, i, InvoiceItemVO.VBATCHCODE);
			panel.setBodyValueAt(null, i, InvoiceItemVO.PK_BATCHCODE);
		}
  }
  
}
