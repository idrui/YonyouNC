/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-9 上午11:49:02
 */
package nc.ui.pu.m4t.rule;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m4t.entity.InitialEstItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据计量单位控制换算率的可编辑性
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-9 上午11:49:02
 */
public class EditableByUnit {
  private BillCardPanel panel;

  public EditableByUnit(BillCardPanel panel) {
    this.panel = panel;
  }

  /**
   * 方法功能描述：设置换算率的可编辑性
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          需要设置的行
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 下午01:17:22
   */
  public void setEditable(int[] rows) {
    for (int row : rows) {
      this.setEditable(row);
    }
  }

  /**
   * 方法功能描述：设置某行换算率的可编辑性
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 下午01:17:48
   */
  private void setEditable(int row) {
    Object cunitid = this.panel.getBodyValueAt(row, InitialEstItemVO.CUNITID);
    if (cunitid == null) {
      return;
    }
    Object castunitid =
        this.panel.getBodyValueAt(row, InitialEstItemVO.CASTUNITID);
    Object material =
        this.panel.getBodyValueAt(row, InitialEstItemVO.PK_MATERIAL);
    // 主辅计量相同，不允许修改
    if (cunitid.equals(castunitid)) {
      this.panel.setCellEditable(row, InitialEstItemVO.VCHANGERATE, false);
    }
    // 主辅计量不同，根据是否固定换算率决定其编辑性
    else if (castunitid != null && material != null) {
      boolean fixedRate =
          MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(
              (String) material, (String) castunitid);
      this.panel.setCellEditable(row, InitialEstItemVO.VCHANGERATE, !fixedRate);
    }
    else {
      this.panel.setCellEditable(row, InitialEstItemVO.VCHANGERATE, true);
    }
  }
}
