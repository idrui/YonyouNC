/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-13 上午10:17:54
 */
package nc.ui.pu.m21.action.orderclose;

import java.awt.event.ActionEvent;

import nc.ui.pu.m21.view.OrderCloseListView;
import nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>关闭菜单刷新
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-13 上午10:17:54
 */
public class CloseRefreshAction extends DefaultRefreshAction {

  /**
   * 
   */
  private static final long serialVersionUID = 1950269305545360192L;

  private OrderCloseListView list;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);

    if (this.list != null) {
      this.list.getBillListPanel().getHeadBillModel().execLoadFormula();
      this.list.getBillListPanel().getHeadBillModel()
          .loadLoadRelationItemValue();
    }

  }

  public OrderCloseListView getList() {
    return this.list;
  }

  public void setList(OrderCloseListView list) {
    this.list = list;
  }
}
