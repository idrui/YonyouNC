/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-8 下午07:30:23
 */
package nc.ui.pu.m4t.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.vo.pu.m4t.rule.InitialEstScaleRule;

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
 * @author wuxla
 * @time 2010-9-8 下午07:30:23
 */
public class InitialEstScaleSetter extends InitialEstScaleRule {
  private String pk_group;

  public InitialEstScaleSetter(String pk_group) {
    this.pk_group = pk_group;
  }

  /**
   * 方法功能描述：设置卡片界面精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-8 下午07:35:16
   */
  public void setCardScale(BillCardPanel panel) {
    this.setScale(new CardPaneScaleProcessor(this.pk_group, panel), null);
  }

  /**
   * 方法功能描述：设置列表界面精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-8 下午07:35:25
   */
  public void setListScale(BillListPanel panel) {
    this.setScale(new ListPaneScaleProcessor(this.pk_group, panel), null);
  }

  /**
   * 单表精度设置（转单）
   * 
   * @param panel
   */
  public void setSingleTableScale(BillListPanel panel) {
    this.setScaleForSingleTable(
        new ListPaneScaleProcessor(this.pk_group, panel), null);
  }

}
