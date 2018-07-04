package nc.ui.pu.m4t.billref;

import nc.ui.pu.m4t.rule.InitialEstScaleSetter;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.billref.src.IRefPanelInit;

/**
 * @since 6.0
 * @version 2011-4-26 下午06:21:00
 * @author 田锋涛
 */

public class M4TRefUIInit implements IRefPanelInit {

  @Override
  public void refMasterPanelInit(BillListPanel masterPanel) {
    // 主子表精度处理
    new InitialEstScaleSetter(ClientContext.getInstance().getPk_group())
        .setListScale(masterPanel);
  }

  @Override
  public void refSinglePanelInit(BillListPanel singlePanel) {
    // 单表精度处理
    new InitialEstScaleSetter(ClientContext.getInstance().getPk_group())
        .setSingleTableScale(singlePanel);
  }

}
