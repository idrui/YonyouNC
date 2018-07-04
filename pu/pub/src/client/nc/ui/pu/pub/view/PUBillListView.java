package nc.ui.pu.pub.view;

import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

/**
 * �ɹ��б���ͼ
 * 
 * @since 6.36
 * @version 2015-4-30 ����11:21:45
 * @author luojw
 */
public class PUBillListView extends ShowUpableBillListView {
  private static final long serialVersionUID = 4008257755266220099L;

  @Override
  public void initUI() {
    super.initUI();
    // ���úϼ���
    this.getBillListPanel().getParentListPanel().setTotalRowShow(true);
    this.getBillListPanel().getChildListPanel().setTotalRowShow(true);
  }
}
