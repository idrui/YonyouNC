package nc.ui.pu.m21.editor.card.afteredit.body;

import nc.itf.scmpub.reference.uap.bd.vat.VATBDService;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoByTaxcodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.ui.pu.m21.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.rule.vat.BillItemVatTaxInfoSetter;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDate;

/**
 * 税码编辑后处理
 * 
 * @since 6.0
 * @version 2012-2-20 下午02:31:00
 * @author tianft
 */
public class TaxCode implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    if (event.getValue() == null) {
      return;
    }
    // 查找税率
    this.setValue(new CardEditorHelper(event.getBillCardPanel()), new int[] {
      event.getRow()
    });
  }

  private void setValue(CardEditorHelper bill, int[] rows) {
    UFDate billdate = bill.getHeadUFDateValue(OrderHeaderVO.DBILLDATE);
    // 日期为空，则不能查询税信息
    if (null == billdate) {
      return;
    }
    VATInfoByTaxcodeQueryVO[] vatqvos =
        new VATInfoByTaxcodeQueryVO[rows.length];
    for (int i = 0; i < rows.length; i++) {
      String taxcode = bill.getBodyStringValue(rows[i], OrderItemVO.CTAXCODEID);
      vatqvos[i] = new VATInfoByTaxcodeQueryVO(taxcode, billdate);
    }
    VATInfoVO[] queryVATInfo = VATBDService.queryVATInfo(vatqvos);
    BillItemVatTaxInfoSetter setter = new BillItemVatTaxInfoSetter(bill);
    setter.setOrigtaxpriceKey(OrderItemVO.NQTORIGTAXPRICE);
    setter.setOrigpriceKey(OrderItemVO.NQTORIGPRICE);
    setter.setForceSet(true);
    String pk_org = bill.getHeadStringValue(OrderHeaderVO.PK_ORG);
    PricePriority pricePriority = PUSysParamUtil.getPO28(pk_org);
    RelationCalculate calc = new RelationCalculate();
    for (int i = 0; i < rows.length; i++) {
      VATInfoVO vatvo = queryVATInfo[i];
      if (null == vatvo) {
        continue;
      }
      String chgkey = setter.setVatTax(vatvo, rows[i], pricePriority);
      if (null == chgkey) {
        continue;
      }
      calc.calculate(bill.getEditor(), new int[] {
        rows[i]
      }, chgkey);
    }
  }
}
