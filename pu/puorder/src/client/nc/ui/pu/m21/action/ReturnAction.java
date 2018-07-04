/**
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-6 下午02:05:38
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>构造修订记录的"返回"按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-6 下午02:05:38
 */
public class ReturnAction extends NCAction {
  private static final long serialVersionUID = -5667491871111125098L;

  private ShowUpableBillListView listView;

  private NCAction refreshAction;

  /**
   * InvoiceAction 的构造子
   */
  public ReturnAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_MATCHBACK);
//    this.setBtnName("返回");
//    this.setCode("btnMatchBack");
//    this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {

    this.getRefreshAction().doAction(e);

    this.getListView().showMeUp();
  }

  public ShowUpableBillListView getListView() {
    return this.listView;
  }

  public NCAction getRefreshAction() {
    return this.refreshAction;
  }

  public void setListView(ShowUpableBillListView listView) {
    this.listView = listView;
  }

  public void setRefreshAction(NCAction refreshAction) {
    this.refreshAction = refreshAction;
  }
}
