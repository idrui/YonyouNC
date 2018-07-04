package nc.ui.pu.m422x.billref;

import nc.ui.pu.m422x.rule.ScaleSetter;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.billref.src.IRefPanelInit;
import nc.vo.pubapp.AppContext;

/**
 * @since 6.0
 * @version 2011-4-26 ����06:54:09
 * @author �����
 */

public class M422xRefPanelInit implements IRefPanelInit {

  @Override
  public void refMasterPanelInit(BillListPanel masterPanel) {
    // ���ӱ��ȴ���
    new ScaleSetter(AppContext.getInstance().getPkGroup())
        .setListScale(masterPanel);
  }

  @Override
  public void refSinglePanelInit(BillListPanel singlePanel) {
    // �����ȴ���
    new ScaleSetter(AppContext.getInstance().getPkGroup())
        .setSingleTableScale(singlePanel);
  }

}
