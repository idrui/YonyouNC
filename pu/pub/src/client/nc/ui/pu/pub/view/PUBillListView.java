package nc.ui.pu.pub.view;

import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

/**
 * 采购列表视图
 * 
 * @since 6.36
 * @version 2015-4-30 上午11:21:45
 * @author luojw
 */
public class PUBillListView extends ShowUpableBillListView {
  private static final long serialVersionUID = 4008257755266220099L;

  @Override
  public void initUI() {
    super.initUI();
    // 设置合计行
    this.getBillListPanel().getParentListPanel().setTotalRowShow(true);
    this.getBillListPanel().getChildListPanel().setTotalRowShow(true);
  }
}
