/**
 * 
 */
package nc.ui.pu.m27.match.view;

import javax.swing.ListSelectionModel;

import nc.ui.pu.m27.match.util.DispResultScaleProcesser;
import nc.ui.pub.beans.UITable;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存单据、发票显示处理类，对应查询界面、匹配结果界面(结算第三个界面)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-11 下午04:50:24
 */
public class MatchResultListPanel extends ShowUpableBillListView {
  private static final long serialVersionUID = 1224926032125118626L;

  @Override
  public void initUI() {
    super.initUI();
    // 默认列表不可编辑
    this.getBillListPanel().setEnabled(false);
    // 设置表头（采购发票）支持多选
    UITable head = this.getBillListPanel().getHeadTable();
    head.setRowSelectionAllowed(true);
    head.setColumnSelectionAllowed(false);
    head.getSelectionModel().setSelectionMode(
        ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    // 设置表体（入库单）支持多选
    UITable body = this.getBillListPanel().getBodyTable();
    body.setRowSelectionAllowed(true);
    body.setColumnSelectionAllowed(false);
    body.getSelectionModel().setSelectionMode(
        ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    // 精度处理
    new DispResultScaleProcesser(this.getBillListPanel()).initScale();
  }
}
