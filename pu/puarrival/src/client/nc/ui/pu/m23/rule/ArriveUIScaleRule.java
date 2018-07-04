package nc.ui.pu.m23.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.ui.pubapp.scale.TotalValueScaleProcessor;
import nc.vo.pu.m23.rule.ArriveScaleRule;

/**
 * ������ui�˾��ȴ���
 * 
 * @since 6.0
 * @version 2010-11-4 ����08:29:53
 * @author tianft
 */
public class ArriveUIScaleRule extends ArriveScaleRule {

  private String pk_group;

  public ArriveUIScaleRule(String pk_group) {
    this.pk_group = pk_group;
  }

  /**
   * ���ÿ�Ƭ���澫��
   * 
   * @param panel
   */
  public void setCardScale(BillCardPanel panel) {
    this.setScale(new CardPaneScaleProcessor(this.pk_group, panel),
        new TotalValueScaleProcessor(panel));
  }

  /**
   * �����б���澫��
   * 
   * @param panel
   */
  public void setListScale(BillListPanel panel) {
    this.setScale(new ListPaneScaleProcessor(this.pk_group, panel),
        new TotalValueScaleProcessor(panel));
  }

  /**
   * �����ȴ���
   * 
   * @param panel
   */
  public void setSingleTableScale(BillListPanel panel) {
    this.setScaleForSingleTable(
        new ListPaneScaleProcessor(this.pk_group, panel),
        new TotalValueScaleProcessor(panel));
  }
}
