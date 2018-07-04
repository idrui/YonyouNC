package nc.ui.pu.m21.rule;

import nc.ui.pub.bill.BillCardPanel;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置订单的字段可编辑性
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-5-18 下午01:30:38
 */
public class EditableSetter {
  private BillCardPanel panel;

  public EditableSetter(BillCardPanel panel) {
    this.panel = panel;
  }

  /**
   * 方法功能描述：重新设置所有行的可编辑性
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows 要设置可编辑性的行
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-4-22 下午01:07:23
   */
  public void setEditableAll() {
    int rowCount = this.panel.getRowCount();
    int[] rows = new int[rowCount];
    for (int i = 0; i < rowCount; i++) {
      rows[i] = i;
    }

    // 控制跟物料相关的编辑性
    this.setEditableByMaterial(rows);

    // 控制跟合同相关的编辑性
    this.setEditableByContract(rows);

    // 控制跟计量单位相关的编辑性
    this.setEditableByUnit(rows);

    // 控制跟币种相关的可编辑性
    this.setEditableByCurrency(rows);
  }

  /**
   * 方法功能描述：控制跟合同相关的编辑性
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows 要设置的行
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-5-18 下午01:36:23
   */
  public void setEditableByContract(int[] rows) {
    new EditableByContract(this.panel).setEditable(rows);
  }

  /**
   * 方法功能描述：控制跟币种相关的可编辑性
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows 要设置的行
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-5-18 下午01:35:45
   */
  public void setEditableByCurrency(int[] rows) {
    new EditableByCurrency(this.panel).setEditable(rows);
  }

  /**
   * 方法功能描述：控制跟物料相关的可编辑性
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows 要设置的行
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-5-18 下午01:33:47
   */
  public void setEditableByMaterial(int[] rows) {
    new EditableByMaterial(this.panel).setEditable(rows);
  }

  /**
   * 方法功能描述：控制跟计量单位相关的编辑性
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows 要设置的行
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-5-18 下午01:36:02
   */
  public void setEditableByUnit(int[] rows) {
    new EditableByUnit(this.panel).setEditable(rows);
  }

}
