package nc.ui.pu.m23.view;

import nc.ui.pu.m23.rule.ArriveUIScaleRule;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���鵽������ҳ����ͼ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-7-12 ����11:20:38
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
    // ���þ���
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
