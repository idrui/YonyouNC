package nc.ui.pu.m21.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.ui.pubapp.scale.TotalValueScaleProcessor;
import nc.vo.pu.m21.rule.OrderScaleRule;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置卡片精度
 * <li>设置列表精度
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-5-17 下午03:29:50
 */
public class OrderScaleSetter extends OrderScaleRule {
  private String pk_group;

  public OrderScaleSetter(String pk_group) {
    this.pk_group = pk_group;
  }

  /**
   * 方法功能描述：设置卡片界面精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel 卡片
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-5-17 下午03:35:01
   */
  public void setCardScale(BillCardPanel panel) {
    this.setScale(this.getCardPaneScaleProcessor(panel),
        new TotalValueScaleProcessor(panel));
  }

  /**
   * 方法功能描述：设置列表界面精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel 列表
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-5-17 下午03:35:17
   */
  public void setListScale(BillListPanel panel) {
    this.setScale(this.getListPaneScaleProcessor(panel),
        new TotalValueScaleProcessor(panel));
  }

  /**
   * 单表精度处理
   * 
   * @param panel
   */
  public void setSingleTableScale(BillListPanel panel) {
    this.setScaleForSingleTable(this.getListPaneScaleProcessor(panel),
        new TotalValueScaleProcessor(panel));
  }

  private CardPaneScaleProcessor getCardPaneScaleProcessor(BillCardPanel panel) {
    return new CardPaneScaleProcessor(this.pk_group, panel);
  }

  private ListPaneScaleProcessor getListPaneScaleProcessor(BillListPanel panel) {
    return new ListPaneScaleProcessor(this.pk_group, panel);
  }

}
