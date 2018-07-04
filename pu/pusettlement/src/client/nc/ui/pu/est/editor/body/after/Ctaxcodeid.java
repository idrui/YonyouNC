package nc.ui.pu.est.editor.body.after;

import nc.itf.scmpub.reference.uap.bd.vat.VATBDService;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoByTaxcodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.calculator.data.BillModelDataSet;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.util.EstRelationCalcUtil;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;
import nc.vo.pu.pub.rule.vat.BillItemVatTaxInfoSetter;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;

import org.apache.commons.lang.ArrayUtils;

public class Ctaxcodeid implements ICardBodyAfterEditEventListener {
  private IRelationForItems relaitems;

  public Ctaxcodeid(IRelationForItems relaitems) {
    this.relaitems = relaitems;
  }

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel bcp = event.getBillCardPanel();
    int row = event.getRow();
    String ctaxcodeid = (String) bcp.getBodyValueAt(row, FeeEstVO.CTAXCODEID);
    UFDate destdate = (UFDate) bcp.getBodyValueAt(row, FeeEstVO.DESTDATE);
    if (null == ctaxcodeid || null == destdate) {
      return;
    }
    VATInfoByTaxcodeQueryVO[] queryvos = new VATInfoByTaxcodeQueryVO[] {
      new VATInfoByTaxcodeQueryVO(ctaxcodeid, destdate)
    };
    VATInfoVO[] vatinfos = VATBDService.queryVATInfo(queryvos);

    BillItemVatTaxInfoSetter setter =
        new BillItemVatTaxInfoSetter(new CardEditorHelper(bcp));
    setter.setForceSet(true);
    setter.setTaxrateKey(FeeEstVO.NESTTAXRATE);
    if (ArrayUtils.isEmpty(vatinfos) || vatinfos[0] == null) {
      setter.setVatTaxNull(row);
      return;
    }
    String changeKey =
        setter.setVatTax(vatinfos[0], row,
            PricePriority.TAXPRICE_PRIOR_TO_PRICE);
    // 2012-06-27 tianft 费用界面没有单价，根据需求wangyf确认已原币价税合计计算（购销类型寻到进口时）
    if (PuAttrNameEnum.nastorigtaxprice.name().equals(changeKey)
        || PuAttrNameEnum.nastorigprice.name().equals(changeKey)) {
      changeKey = FeeEstVO.NESTOTOTALMNY;
    }
    IDataSetForCal dataset =
        new BillModelDataSet(bcp.getBillModel(), row, this.relaitems);
    String pk_group = dataset.getPk_group();
    // 计算工具
    EstRelationCalcUtil calcUtil = new EstRelationCalcUtil(pk_group);
    calcUtil.calcDataSetForFee(dataset, changeKey);
  }
}
