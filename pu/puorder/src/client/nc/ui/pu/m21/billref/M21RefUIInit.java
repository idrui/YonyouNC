package nc.ui.pu.m21.billref;

import nc.ui.pu.m21.rule.OrderScaleSetter;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.billref.src.IRefPanelInit;

/**
 * 订单转单界面初始化类
 * 
 * @since 6.0
 * @version 2011-4-26 下午01:09:15
 * @author 田锋涛
 */

public class M21RefUIInit implements IRefPanelInit {

  @Override
  public void refMasterPanelInit(BillListPanel masterPanel) {
    // 主子表精度处理
    new OrderScaleSetter(ClientContext.getInstance().getPk_group())
        .setListScale(masterPanel);
  }

  @Override
  public void refSinglePanelInit(BillListPanel singlePanel) {
    // 单表精度处理
    new OrderScaleSetter(ClientContext.getInstance().getPk_group())
        .setSingleTableScale(singlePanel);
  }
}
