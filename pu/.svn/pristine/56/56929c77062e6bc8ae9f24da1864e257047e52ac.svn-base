package nc.ui.pu.m4t.view;

import nc.ui.pu.m4t.rule.InitialEstScaleSetter;
import nc.ui.pu.pub.view.PUBillListView;
import nc.vo.pubapp.AppContext;

/**
 * 期初暂估单列表视图
 * 
 * @since 6.3
 * @version 2013-3-14 下午02:09:00
 * @author fanly3
 */
public class InitialEstBillListView extends PUBillListView {
  private static final long serialVersionUID = -7379838315181410159L;

  @Override
  public void initUI() {
    super.initUI();
    new InitialEstScaleSetter(AppContext.getInstance().getPkGroup())
        .setListScale(this.getBillListPanel());
  }
}
