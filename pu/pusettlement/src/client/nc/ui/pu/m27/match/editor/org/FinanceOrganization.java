package nc.ui.pu.m27.match.editor.org;

import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

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
  private ShowUpableBillListView listView;

  public FinanceOrganization(ShowUpableBillListView listView) {
    this.listView = listView;
  }

  @Override
  public void process(ShowUpableBillForm cardForm) {
    MatchManageModel model = (MatchManageModel) this.listView.getModel();

    // ��ս�������
    model.initInvoice(null);
    model.initStock(null);

    // ���³�ʼ�����㻷��
    String pk_org = model.getContext().getPk_org();
    model.initSettleEnvironment(pk_org);
  }

}
