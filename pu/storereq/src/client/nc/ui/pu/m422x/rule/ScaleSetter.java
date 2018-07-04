/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-27 下午04:07:45
 */
package nc.ui.pu.m422x.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.ui.pubapp.scale.TotalValueScaleProcessor;
import nc.vo.pu.m422x.rule.StoreReqScaleRule;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-27 下午04:07:45
 */
public class ScaleSetter extends StoreReqScaleRule {

  private String pk_group;

  public ScaleSetter(String pk_group) {
    this.pk_group = pk_group;
  }

  public void setCardScale(BillCardPanel panel) {
    this.setScale(new CardPaneScaleProcessor(this.pk_group, panel),
        new TotalValueScaleProcessor(panel));
  }

  public void setListScale(BillListPanel panel) {
    this.setScale(new ListPaneScaleProcessor(this.pk_group, panel),
        new TotalValueScaleProcessor(panel));
  }

  /**
   * 单表精度处理
   * 
   * @param panel
   */
  public void setSingleTableScale(BillListPanel panel) {
    this.setScaleForSingleTable(
        new ListPaneScaleProcessor(this.pk_group, panel),
        new TotalValueScaleProcessor(panel));
  }
}
