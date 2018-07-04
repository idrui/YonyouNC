package nc.ui.pu.m25.rule;

import java.util.List;
import java.util.Map;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.m25.editor.utils.RelationCalculate;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m25.rule.maintain.InvoiceOppTaxFlagFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceSupplierVatCodeFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceVatTaxFillRule;
import nc.vo.pu.pub.rule.vat.BuysellflagSetter;
import nc.vo.pu.pub.rule.vat.TriatradeflagSetter;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;

/**
 * 编辑后处理器，采购发票VAT税码、税率等公共设置规则
 * 
 * @since 6.0
 * @version 2012-2-23 下午4:01:47
 * @author zhaoyha
 */
public class InvoiceTaxEditHandler implements IPURemoteCallCombinator {

  private final BillCardPanel bcp;

  private List<Integer> changebills = null;

  private InvoiceOppTaxFlagFillRule opptaxSetter;

  private InvoiceSupplierVatCodeFillRule suppVatcodeSetter;

  private InvoiceVatTaxFillRule vatTaxSetter;

  public InvoiceTaxEditHandler(BillCardPanel bcp,
      InvoiceOppTaxFlagFillRule opptaxSetter,
      InvoiceSupplierVatCodeFillRule suppVatcodeSetter,
      InvoiceVatTaxFillRule vatTaxSetter) {
    this.bcp = bcp;
    this.opptaxSetter = opptaxSetter;
    this.suppVatcodeSetter = suppVatcodeSetter;
    this.vatTaxSetter = vatTaxSetter;
  }

  @Override
  public void prepare() {
    this.setBuysellflag();
    this.setTriatrade();
    this.register_opptax();
    this.register_suppvatcode();
    this.register_vattax();
  }

  @Override
  public void process() {
    this.proc_opptax();
    this.proc_suppvatcode();
    this.proc_vattax();
  }

  private void proc_opptax() {
    if (null != this.opptaxSetter) {
      this.opptaxSetter.process();
    }
  }

  private void proc_suppvatcode() {
    if (null != this.suppVatcodeSetter) {
      this.suppVatcodeSetter.process();
    }
  }

  private void proc_vattax() {
    if (null == this.vatTaxSetter) {
      return;
    }
    this.vatTaxSetter.setBuysellChangeBills(this.changebills);
    this.vatTaxSetter.process();
    MapList<String, Integer> chgNameIndexMap =
        this.vatTaxSetter.getChgNameIndexMap();
    // 联动计算
    RelationCalculate calc = new RelationCalculate();
    for (Map.Entry<String, List<Integer>> entry : chgNameIndexMap.entrySet()) {
      String chgkey = entry.getKey();
      List<Integer> rows = entry.getValue();
      Integer[] arows = rows.toArray(new Integer[rows.size()]);
      calc.calculate(this.bcp, ArrayUtils.toPrimitive(arows), chgkey);
    }
  }

  private void register_opptax() {
    if (null != this.opptaxSetter) {
      this.opptaxSetter.prepare();
    }
  }

  private void register_suppvatcode() {
    if (null != this.suppVatcodeSetter) {
      this.suppVatcodeSetter.prepare();
    }
  }

  private void register_vattax() {
    if (null != this.vatTaxSetter) {
      this.vatTaxSetter.prepare();
    }
  }

  private void setBuysellflag() {
    this.changebills =
        new BuysellflagSetter().setHeadBuysellFlag(new CardEditorHelper[] {
          new CardEditorHelper(this.bcp)
        });
  }

  private void setTriatrade() {
    new TriatradeflagSetter().setHeadTriatradeflag(new CardEditorHelper[] {
      new CardEditorHelper(this.bcp)
    });
  }

}
