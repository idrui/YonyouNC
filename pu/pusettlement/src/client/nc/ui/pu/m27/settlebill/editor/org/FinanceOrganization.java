package nc.ui.pu.m27.settlebill.editor.org;

import nc.ui.pu.m27.match.util.CostfactorDynamicDisplayUtil;
import nc.ui.pu.m27.settlebill.editor.SettleBillScaleSetter;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������֯�ı༭���¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-7-3 ����03:40:52
 */
public class FinanceOrganization implements IOrgChangedEventListener {
  private OrgChangedEventHandler parentHandler;

  public FinanceOrganization(OrgChangedEventHandler orgChangedEventHandler) {
    this.parentHandler = orgChangedEventHandler;
  }

  @Override
  public void process(ShowUpableBillForm cardForm) {
    String pk_group = cardForm.getModel().getContext().getPk_group();
    String pk_org = cardForm.getModel().getContext().getPk_org();
    BillCardPanel panel = cardForm.getBillCardPanel();
    // ���ȴ���
    new SettleBillScaleSetter(pk_group).setCardScale(panel);
    // �ɱ�Ҫ�ض�̬��ʾ
    this.displayCostfactor(panel, this.parentHandler.getListView()
        .getBillListPanel(), this.parentHandler.getCostfactor(pk_org));
  }

  private void displayCostfactor(BillCardPanel panel,
      BillListPanel billListPanel, CostfactorVO[] cfVos) {
    BillScrollPane cardpane = panel.getBodyPanel();
    BillScrollPane listpane = billListPanel.getChildListPanel();
    // ����Ƭ
    CostfactorDynamicDisplayUtil.initCostfactorDisplay(cfVos, cardpane,
        SettleBillItemVO.NCOSTFACTOR1.substring(0, 11));
    // �����б�
    CostfactorDynamicDisplayUtil.initCostfactorDisplay(cfVos, listpane,
        SettleBillItemVO.NCOSTFACTOR1.substring(0, 11));
  }

}
