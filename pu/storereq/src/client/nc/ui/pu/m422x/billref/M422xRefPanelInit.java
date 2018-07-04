package nc.ui.pu.m422x.billref;

import nc.ui.pu.m422x.rule.ScaleSetter;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.src.IRefPanelInit;
import nc.vo.pubapp.AppContext;

/**
 * @since 6.0
 * @version 2011-4-26 下午06:54:09
 * @author 田锋涛
 */

public class M422xRefPanelInit implements IRefPanelInit {

  @Override
  public void refMasterPanelInit(BillListPanel masterPanel) {
    // 主子表精度处理
    new ScaleSetter(AppContext.getInstance().getPkGroup())
        .setListScale(masterPanel);
  }

  @Override
  public void refSinglePanelInit(BillListPanel singlePanel) {
    // 单表精度处理
    new ScaleSetter(AppContext.getInstance().getPkGroup())
        .setSingleTableScale(singlePanel);
  }

}
