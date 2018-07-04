package nc.ui.pu.pub.parapanel;

import java.util.LinkedList;
import java.util.List;

import nc.ui.pubapp.panel.AbstractParaListToListPanel;
import nc.vo.pu.pub.enumeration.POParas;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pub.para.SysInitVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>可选值为：供应商价目表、参考成本、计划价、订单最新价、订单最低价、请购单单价、无默认
 * <li>默认值为：供应商价目表->订单最新价->请购单单价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-4-10 下午05:56:36
 */
public class PO06ListToListPanel extends AbstractParaListToListPanel {
  private static final long serialVersionUID = 859317663154317848L;

  private ParaListItemInfo[] leftItems;

  private ParaListItemInfo[] rightItems;

  public PO06ListToListPanel(String pk_org) {
    // super(pk_org);
  }

  @Override
  public String getParamValueCode() {
    // 原来是PO06_v 现更改为PO06以适应uap新的变化
    return POParas.PO06.toString();
  }

  public List<String> getPO06PriceList() {
    // 可选值为：供应商价目表、参考成本、计划价、订单最新价、订单最低价、请购单单价、无默认。
    List<String> PO06PriceList = new LinkedList<String>();

    PO06PriceList.add(PriceSource.SupplierPrice.name());
    PO06PriceList.add(PriceSource.RefCostPrice.name());
    PO06PriceList.add(PriceSource.PlanPrice.name());
    PO06PriceList.add(PriceSource.OrderNewestPrice.name());
    PO06PriceList.add(PriceSource.OrderMinPrice.name());
    PO06PriceList.add(PriceSource.PrayPrice.name());

    return PO06PriceList;
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
    List<String> list = this.getPO06PriceList();
    for (int i = 0, len = initValues.length; i < len; i++) {

      this.rightItems[i] = new ParaListItemInfo();
      PriceSource priceEnum = PriceSource.valueOf(initValues[i]);
      this.rightItems[i].setText(priceEnum.code());
      this.rightItems[i].setValue(priceEnum.name());

      // 删除已被选中的值
      list.remove(priceEnum.name());
    }

    this.leftItems = new ParaListItemInfo[list.size()];
    for (int j = 0, len = this.leftItems.length; j < len; j++) {

      this.leftItems[j] = new ParaListItemInfo();
      PriceSource priceEnum = PriceSource.valueOf(list.get(j));
      this.leftItems[j].setText(priceEnum.code());
      this.leftItems[j].setValue(priceEnum.name());
    }
  }
}
