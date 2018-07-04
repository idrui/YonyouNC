/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-10 下午03:03:18
 */
package nc.ui.pu.m20.view;

import javax.swing.ListSelectionModel;

import nc.ui.pu.pub.view.PUBillListView;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单列表界面
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-10 下午03:03:18
 */
public class BillListView extends PUBillListView {

  private static final long serialVersionUID = -1663245037545639583L;

  @Override
  public void initUI() {
    super.initUI();

    this.getBillListPanel().getBodyTable()
        .setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
  }
}
