package nc.bs.pu.m25.upgrade.v61.vat;

import java.util.List;

import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule;
import nc.vo.pu.pub.util.IKeyValue;

public class InvoiceVatUpgradeRemoteRule extends InvoiceVatDefaultValueFillRule {

  public InvoiceVatUpgradeRemoteRule(IKeyValue[] bills,
      List<ICountrySetter> countrysetterList) {
    super(bills, countrysetterList);
  }

  @Override
  protected void setBillItemVatTaxInfo(VATInfoVO vo, IKeyValue bill, int row) {
    // Éý¼¶²¹³äË°Âë
    bill.setBodyValue(row, InvoiceItemVO.CTAXCODEID, vo.getCtaxcodeid());
  }

}
