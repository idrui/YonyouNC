package nc.ui.pu.m23.billref;

import nc.ui.pu.m23.rule.ArriveUIScaleRule;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.billref.src.IRefPanelInit;

/**
 * 转单界面初始化处理
 * 
 * @since 6.0
 * @version 2011-4-26 下午06:43:59
 * @author 田锋涛
 */

public class M23RefPanelInit implements IRefPanelInit {

  @Override
  public void refMasterPanelInit(BillListPanel masterPanel) {
    // 主子表精度处理
    new ArriveUIScaleRule(ClientContext.getInstance().getPk_group())
        .setListScale(masterPanel);
  }

  @Override
  public void refSinglePanelInit(BillListPanel singlePanel) {
    // 单表精度处理
    new ArriveUIScaleRule(ClientContext.getInstance().getPk_group())
        .setSingleTableScale(singlePanel);
  }

}
