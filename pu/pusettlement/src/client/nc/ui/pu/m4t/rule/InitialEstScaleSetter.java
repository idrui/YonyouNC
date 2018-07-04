/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-8 ����07:30:23
 */
package nc.ui.pu.m4t.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.vo.pu.m4t.rule.InitialEstScaleRule;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ÿ�Ƭ����
 * <li>�����б���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-8 ����07:30:23
 */
public class InitialEstScaleSetter extends InitialEstScaleRule {
  private String pk_group;

  public InitialEstScaleSetter(String pk_group) {
    this.pk_group = pk_group;
  }

  /**
   * �����������������ÿ�Ƭ���澫��
   * <p>
   * <b>����˵��</b>
   * 
   * @param panel <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-8 ����07:35:16
   */
  public void setCardScale(BillCardPanel panel) {
    this.setScale(new CardPaneScaleProcessor(this.pk_group, panel), null);
  }

  /**
   * �������������������б���澫��
   * <p>
   * <b>����˵��</b>
   * 
   * @param panel <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-8 ����07:35:25
   */
  public void setListScale(BillListPanel panel) {
    this.setScale(new ListPaneScaleProcessor(this.pk_group, panel), null);
  }

  /**
   * ���������ã�ת����
   * 
   * @param panel
   */
  public void setSingleTableScale(BillListPanel panel) {
    this.setScaleForSingleTable(
        new ListPaneScaleProcessor(this.pk_group, panel), null);
  }

}
