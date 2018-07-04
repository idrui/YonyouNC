package nc.ui.pu.pub.parapanel;

import java.util.LinkedList;
import java.util.List;

import nc.ui.pubapp.panel.AbstractParaListToListPanel;
import nc.vo.pu.pub.enumeration.POParas;
import nc.vo.pu.pub.enumeration.PriceParam;
import nc.vo.pub.para.SysInitVO;

/**
 * PO16采购订单自动询价条件
 * 供应商,物料,币种,单据日期,报价单位,结算财务组织,生产厂商,质量等级,收货地区,运输方式
 * 默认值 供应商、物料
 * Supplier,Material,Currency,BillDate,Qtunit,Psfinanceorg,Productor,
 * Qualitylevel,ReceiveArea,Vehicletype
 * 
 * @since 6.5
 * @version 2014-1-14 下午02:22:19
 * @author mengjian
 */
public class PO16ListToListPanel extends AbstractParaListToListPanel {
  private static final long serialVersionUID = 859317663154317848L;

  private ParaListItemInfo[] leftItems;

  private ParaListItemInfo[] rightItems;

  public PO16ListToListPanel(String pk_org) {
    // super(pk_org);
  }

  @Override
  public String getParamValueCode() {
    return POParas.PO16.toString();
  }

  public List<String> getPO16PriceList() {
    // 可选值为：供应商、物料、币种、单据日期、报价单位、结算财务组织、生产厂商、质量等级、收货地区、运输方式。
    List<String> PO16PriceList = new LinkedList<String>();

    PO16PriceList.add(PriceParam.Supplier.name());
    PO16PriceList.add(PriceParam.Material.name());
    PO16PriceList.add(PriceParam.Currency.name());
    PO16PriceList.add(PriceParam.BillDate.name());
    PO16PriceList.add(PriceParam.Qtunit.name());
    PO16PriceList.add(PriceParam.Psfinanceorg.name());
    PO16PriceList.add(PriceParam.Productor.name());
    PO16PriceList.add(PriceParam.Qualitylevel.name());
    PO16PriceList.add(PriceParam.ReceiveArea.name());
    PO16PriceList.add(PriceParam.Vehicletype.name());

    return PO16PriceList;
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
    List<String> list = this.getPO16PriceList();
    for (int i = 0, len = initValues.length; i < len; i++) {

      this.rightItems[i] = new ParaListItemInfo();
      PriceParam priceEnum = PriceParam.valueOf(initValues[i]);
      this.rightItems[i].setText(priceEnum.code());
      this.rightItems[i].setValue(priceEnum.name());

      // 删除已被选中的值
      list.remove(priceEnum.name());
    }

    this.leftItems = new ParaListItemInfo[list.size()];
    for (int j = 0, len = this.leftItems.length; j < len; j++) {

      this.leftItems[j] = new ParaListItemInfo();
      PriceParam priceEnum = PriceParam.valueOf(list.get(j));
      this.leftItems[j].setText(priceEnum.code());
      this.leftItems[j].setValue(priceEnum.name());
    }
  }
}
