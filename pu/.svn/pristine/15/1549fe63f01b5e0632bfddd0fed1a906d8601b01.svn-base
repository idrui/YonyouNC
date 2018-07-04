package nc.ui.pu.pub.parapanel;

import java.util.LinkedList;
import java.util.List;

import nc.ui.pubapp.panel.AbstractParaListToListPanel;
import nc.vo.pu.pub.enumeration.POParas;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pub.para.SysInitVO;

/**
 * <p>
 * <b>采购发票价格来源,本类主要完成以下功能：</b>
 * <ul>
 * <li>可选值为：（计划价delete）订单最新价、（订单最低价delete）参考成本、供应商价目表、订单价、入库单价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-4-10 下午05:56:36
 */
public class PO83ListToListPanel extends AbstractParaListToListPanel {
  private static final long serialVersionUID = 859317663154317848L;

  private ParaListItemInfo[] leftItems;

  private ParaListItemInfo[] rightItems;

  public PO83ListToListPanel(String pk_org) {
    // super(pk_org);
  }

  @Override
  public String getParamValueCode() {
    // 原来是PO83_v 现更改为PO83以适应uap新的变化
    return POParas.PO83.toString();
  }

  public List<String> getPO83PriceList() {
    // 可选值为：可选值为：计划价、订单最新价、订单最低价、参考成本、供应商有效价格、订单价、入库单价
    List<String> PO83PriceList = new LinkedList<String>();
    // PO83PriceList.add(PriceSource.PlanPrice.name());//2010-07-01需求去掉计划价
    PO83PriceList.add(PriceSource.OrderNewestPrice.name());
    // PO83PriceList.add(PriceSource.OrderMinPrice.name());//2010-07-01需求去掉订单最低价
    PO83PriceList.add(PriceSource.RefCostPrice.name());
    PO83PriceList.add(PriceSource.SupplierPrice.name());
    PO83PriceList.add(PriceSource.OrderPice.name());
    PO83PriceList.add(PriceSource.PurchaseInPrice.name());

    return PO83PriceList;
  }

  @Override
  public ParaListItemInfo[] initLeftItems(SysInitVO initVO) {
    if (this.leftItems == null) {
      this.buildInitData(initVO);
    }

    return this.leftItems;
  }

  @Override
  public ParaListItemInfo[] initRightItems(SysInitVO initVO) {
    if (this.rightItems == null) {
      this.buildInitData(initVO);
    }

    return this.rightItems;
  }

  private void buildInitData(SysInitVO initVO) {

    String[] initValues = initVO.getValue().split(",");

    this.rightItems = new ParaListItemInfo[initValues.length];
    List<String> po83PriceList = this.getPO83PriceList();
    for (int i = 0, len = initValues.length; i < len; i++) {

      this.rightItems[i] = new ParaListItemInfo();
      PriceSource priceEnum = PriceSource.valueOf(initValues[i]);
      this.rightItems[i].setText(priceEnum.code());
      this.rightItems[i].setValue(priceEnum.name());

      // 删除已被选中的值
      po83PriceList.remove(priceEnum.name());
    }

    this.leftItems = new ParaListItemInfo[po83PriceList.size()];
    for (int j = 0, len = this.leftItems.length; j < len; j++) {

      this.leftItems[j] = new ParaListItemInfo();
      PriceSource priceEnum = PriceSource.valueOf(po83PriceList.get(j));
      this.leftItems[j].setText(priceEnum.code());
      this.leftItems[j].setValue(priceEnum.name());
    }
  }
}
