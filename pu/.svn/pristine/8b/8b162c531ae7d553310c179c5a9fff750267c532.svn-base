package nc.ui.pu.m27.settlebill.editor;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.vo.pu.m27.rule.SettleBillScaleRule;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算单精度设置器
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-10-20 下午03:53:30
 */
public class SettleBillScaleSetter extends SettleBillScaleRule {
  private String pk_group;

  public SettleBillScaleSetter(String pk_group) {
    this.pk_group = pk_group;
  }

  /**
   * 方法功能描述：卡片界面精度设置
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   *          卡片面板
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-10-20 下午03:58:16
   */
  public void setCardScale(BillCardPanel panel) {
    this.setBodyScale(new CardPaneScaleProcessor(this.pk_group, panel));
  }

  /**
   * 方法功能描述：设置列表精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   *          列表面板
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-10-20 下午03:54:28
   */
  public void setListScale(BillListPanel panel) {
    this.setBodyScale(new ListPaneScaleProcessor(this.pk_group, panel));
  }
}
