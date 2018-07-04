package nc.ui.pu.m23.view;

import nc.ui.pu.m23.rule.ArriveUIScaleRule;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>检验到货单的页面视图
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-7-12 上午11:20:38
 */
public class CheckListView extends ShowUpableBillListView {

  private static final long serialVersionUID = 6938100099279118443L;

  private IBillListPanelValueSetter billListPanelValueSetter;

  @Override
  public IBillListPanelValueSetter getBillListPanelValueSetter() {
    return this.billListPanelValueSetter;
  }

  @Override
  public void initUI() {
    super.initUI();
    // 设置精度
    this.setScale();
  }

  @Override
  public void setBillListPanelValueSetter(
      IBillListPanelValueSetter billListPanelValueSetter) {
    super.setBillListPanelValueSetter(billListPanelValueSetter);
    this.billListPanelValueSetter = billListPanelValueSetter;
  }

  private void setScale() {
    new ArriveUIScaleRule(AppContext.getInstance().getPkGroup())
        .setSingleTableScale(this.getBillListPanel());
  }
}
