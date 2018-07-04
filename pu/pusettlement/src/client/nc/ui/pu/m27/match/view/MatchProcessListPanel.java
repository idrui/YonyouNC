package nc.ui.pu.m27.match.view;

import nc.ui.pu.m27.match.util.MatchScaleProcesser;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ƥ������ࡣ��Ӧƥ����̽���(����ڶ�������)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-11 ����04:54:34
 */
public class MatchProcessListPanel extends ShowUpableBillListView {
  private static final long serialVersionUID = -7074125421982487173L;

  @Override
  public void initUI() {
    super.initUI();
    this.getBillListPanel().setMultiSelect(false);
    this.getBillListPanel().setEnabled(true);
    // ��֧������
    this.getBillListPanel().getHeadTable().setSortEnabled(false);
    this.getBillListPanel().getBodyTable().setSortEnabled(false);
    // ������
    this.procScale(this.getBillListPanel());
  }

  private void procScale(BillListPanel blp) {
    new MatchScaleProcesser(blp).initScale();
  }

}
