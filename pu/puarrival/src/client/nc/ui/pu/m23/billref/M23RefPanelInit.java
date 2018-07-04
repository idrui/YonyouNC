package nc.ui.pu.m23.billref;

import nc.ui.pu.m23.rule.ArriveUIScaleRule;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.billref.src.IRefPanelInit;

/**
 * ת�������ʼ������
 * 
 * @since 6.0
 * @version 2011-4-26 ����06:43:59
 * @author �����
 */

public class M23RefPanelInit implements IRefPanelInit {

  @Override
  public void refMasterPanelInit(BillListPanel masterPanel) {
    // ���ӱ��ȴ���
    new ArriveUIScaleRule(ClientContext.getInstance().getPk_group())
        .setListScale(masterPanel);
  }

  @Override
  public void refSinglePanelInit(BillListPanel singlePanel) {
    // �����ȴ���
    new ArriveUIScaleRule(ClientContext.getInstance().getPk_group())
        .setSingleTableScale(singlePanel);
  }

}
