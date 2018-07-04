package nc.ui.pu.m27.match.view;

import nc.ui.pu.m27.match.util.MatchScaleProcesser;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>匹配过程类。对应匹配过程界面(结算第二个界面)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-11 下午04:54:34
 */
public class MatchProcessListPanel extends ShowUpableBillListView {
  private static final long serialVersionUID = -7074125421982487173L;

  @Override
  public void initUI() {
    super.initUI();
    this.getBillListPanel().setMultiSelect(false);
    this.getBillListPanel().setEnabled(true);
    // 不支持排序
    this.getBillListPanel().getHeadTable().setSortEnabled(false);
    this.getBillListPanel().getBodyTable().setSortEnabled(false);
    // 处理精度
    this.procScale(this.getBillListPanel());
  }

  private void procScale(BillListPanel blp) {
    new MatchScaleProcesser(blp).initScale();
  }

}
