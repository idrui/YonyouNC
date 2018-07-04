/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-5 上午08:21:37
 */
package nc.ui.pu.m422x.rule;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

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
 * @time 2010-8-5 上午08:21:37
 */
public class EditableByUnit {
  private BillCardPanel panel;

  public EditableByUnit(BillCardPanel panel) {
    this.panel = panel;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-13 下午02:37:42
   */
  public void setEditable(int[] rows) {
    for (int row : rows) {
      this.setEditable(row);
    }
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-8-13 下午02:37:11
   */
  public void setEditableAll() {
    int rowCount = this.panel.getRowCount();
    int[] rows = new int[rowCount];
    for (int i = 0; i < rowCount; i++) {
      rows[i] = i;
    }

    this.setEditable(rows);
  }

  private void setEditable(int row) {
    Object cunitid = this.panel.getBodyValueAt(row, StoreReqAppItemVO.CUNITID);
    if (cunitid == null) {
      return;
    }
    Object castunitid =
        this.panel.getBodyValueAt(row, StoreReqAppItemVO.CASTUNITID);
    Object material =
        this.panel.getBodyValueAt(row, StoreReqAppItemVO.PK_MATERIAL);
    // 主辅计量相同，不允许修改
    if (cunitid.equals(castunitid)) {
      this.panel.setCellEditable(row, StoreReqAppItemVO.VCHANGERATE, false);
    }
    // 主辅计量不同，根据是否固定换算率决定其编辑性
    else if (castunitid != null && material != null) {
      boolean fixedRate =
          MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(
              (String) material, (String) castunitid);
      this.panel
          .setCellEditable(row, StoreReqAppItemVO.VCHANGERATE, !fixedRate);
    }
    else {
      this.panel.setCellEditable(row, StoreReqAppItemVO.VCHANGERATE, true);
    }
  }
}
