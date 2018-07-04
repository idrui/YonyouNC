package nc.ui.pu.m27.match.editor.org;

import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>财务组织的编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-7-3 下午03:40:52
 */
public class FinanceOrganization implements IOrgChangedEventListener {
  private ShowUpableBillListView listView;

  public FinanceOrganization(ShowUpableBillListView listView) {
    this.listView = listView;
  }

  @Override
  public void process(ShowUpableBillForm cardForm) {
    MatchManageModel model = (MatchManageModel) this.listView.getModel();

    // 清空界面数据
    model.initInvoice(null);
    model.initStock(null);

    // 重新初始化结算环境
    String pk_org = model.getContext().getPk_org();
    model.initSettleEnvironment(pk_org);
  }

}
