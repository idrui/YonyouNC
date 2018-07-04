package nc.ui.pu.m21.view;

import org.apache.commons.lang.StringUtils;

import nc.ui.pu.pub.util.SupplierFreeCustInfoUtil;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;

public class OrderOutputShowView extends ShowUpableBillListView {
  private static final long serialVersionUID = -3364118774550958333L;
  
  private Boolean[] isload = new Boolean[]{Boolean.FALSE};

  @Override
  public void initUI() {
    super.initUI();
    // 初始化银行账户参照
    SupplierFreeCustInfoUtil custBankUtil = new SupplierFreeCustInfoUtil();
    custBankUtil.setBankaccbasname(OrderHeaderVO.PK_BANKDOC);
    custBankUtil.initCustBankRefPanel(this.getBillListPanel(),
        UFBoolean.FALSE.booleanValue());
    // 多选属性，需要在添加RowStateChangeEventListener之前设置
    this.getBillListPanel().setMultiSelect(true);
    this.getBillListPanel().setChildMultiSelect(false);
  }
  
  @Override
  public void setModel(BillManageModel model) {
    super.setModel(model);
  }
  
  @Override
  protected void synchronizeDataFromModel() {
    Object[] datas = this.getModel().getData().toArray();

    if (datas == null || datas.length == 0) { // 如果没有数据，则清除
      this.billListPanel.getHeadBillModel().clearBodyData();
      this.billListPanel.getBodyBillModel().clearBodyData();
    } else {
      this.getBillListPanelValueSetter().setHeaderDatas(this.billListPanel, datas);
      String freecustid =
            ((OrderHeaderVO) ((OrderVO) datas[0]).getParentVO()).getPk_freecust();
      boolean isFreeCust = StringUtils.isEmpty(freecustid) ? false : true;
      SupplierFreeCustInfoUtil custBankUtil = new SupplierFreeCustInfoUtil();
      custBankUtil.setBankaccbasname(OrderHeaderVO.PK_BANKDOC);
      custBankUtil.initCustBankValue(this.getBillListPanel(), isFreeCust, this.isload);
      
      this.getBillListPanelValueSetter().setBodyData(this.billListPanel,
          this.getModel().getSelectedData());

      setHeadTableHighLightByModelSelection();

      setCheckBoxMultiUnstate();
    }
  }

}
