/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-18 下午02:45:41
 */
package nc.ui.pu.est.editor.body.after;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.calculator.data.BillModelDataSet;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.rule.FeeEstVatValue;
import nc.vo.pu.est.util.EstCurrencyUtil;
import nc.vo.pu.est.util.EstRelationCalcUtil;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>供应商编辑后处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-18 下午02:45:41
 */
public class Supplier implements ICardBodyAfterEditEventListener {
  private IRelationForItems relaitems;

  /**
   * Supplier 的构造子
   * 
   * @param relaitems
   *          联动计算的字段集
   */
  public Supplier(IRelationForItems relaitems) {
    this.relaitems = relaitems;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener#afterEdit(nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent)
   */
  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {

    String newValue = (String) event.getValue();
    BillCardPanel bcp = event.getBillCardPanel();
    int row = event.getRow();
    // 1.清空供应商时
    if (StringUtil.isEmptyWithTrim(newValue)) {
      // 币种清空
      // bcp.setBodyValueAt(null, row, pk_currency_name);
      return;
    }
    IDataSetForCal dataset =
        new BillModelDataSet(bcp.getBillModel(), row, this.relaitems);
    String pk_fiorg = dataset.getPk_org();
    // 计算工具
    String pk_group = dataset.getPk_group();
    EstRelationCalcUtil calcUtil = new EstRelationCalcUtil(pk_group);
    this.setVat(bcp, dataset, row, pk_fiorg, calcUtil);
    // 默认币种
    String pk_currtype =
        SupplierPubService.getDefaultCurrtype(newValue, pk_fiorg);
    if (StringUtil.isEmptyWithTrim(pk_currtype)) {
      return;
    }
    String pk_currency_name = this.relaitems.getCorigcurrencyidKey();
    bcp.setBodyValueAt(pk_currtype, row, pk_currency_name);

    EstCurrencyUtil.feeCurrencyChange(dataset, calcUtil,
        PricePriority.TAXPRICE_PRIOR_TO_PRICE);// 需求确认， 默认使用含税优先

  }

  private void setVat(BillCardPanel bcp, IDataSetForCal dataset, int row,
      String pk_fiorg, EstRelationCalcUtil calcUtil) {
    FeeEstVatValue vat = new FeeEstVatValue();
    IKeyValue keyValue = new CardEditorHelper(bcp);
    Map<Integer, String> map =
        vat.setSendCountryAndVatValue(keyValue, pk_fiorg, new int[] {
          row
        });
    if (null == map) {
      return;
    }
    String changeAttr = map.get(Integer.valueOf(row));
    if (null == changeAttr) {
      return;
    }

    calcUtil.calcDataSetForFee(dataset, changeAttr);
  }

}
