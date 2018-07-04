package nc.ui.pu.m21.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物料编辑后所触发的单据项目编辑性
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-4-22 上午11:04:55
 */
public class EditableByMaterial {
  private BillCardPanel panel;

  public EditableByMaterial(BillCardPanel panel) {
    this.panel = panel;
  }

  /**
   * 方法功能描述：设置可编辑性
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows 要设置可编辑性的行
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-4-22 下午01:07:32
   */
  public void setEditable(int[] rows) {
    for (int row : rows) {
      // 物料的可编辑性受模板控制
      this.panel.setCellEditable(row, OrderItemVO.PK_MATERIAL, this.panel
          .getBodyItem(OrderItemVO.PK_MATERIAL).isEdit());
      // 币种的可编辑性受模板控制
      this.panel.getHeadTailItem(OrderHeaderVO.CORIGCURRENCYID).setEnabled(
          this.panel.getHeadTailItem(OrderHeaderVO.CORIGCURRENCYID).isEdit());

      // 根据物料来控制其它字段的可编辑性
      Object obj = this.panel.getBodyValueAt(row, OrderItemVO.PK_MATERIAL);
      if (obj == null) {
        this.editableWhenNoneMaterial(row);
      }
      else {
        this.editableWhenMaterial(row);
      }
    }
  }

  private void editableWhenMaterial(int row) {
    this.panel.setCellEditable(row, OrderItemVO.CCONTRACTID, this.panel
        .getBodyItem(OrderItemVO.CCONTRACTID).isEdit());
    this.panel.setCellEditable(row, OrderItemVO.NQTORIGTAXPRICE, this.panel
        .getBodyItem(OrderItemVO.NQTORIGTAXPRICE).isEdit());
    this.panel.setCellEditable(row, OrderItemVO.NQTORIGPRICE, this.panel
        .getBodyItem(OrderItemVO.NQTORIGPRICE).isEdit());
    this.panel.setCellEditable(row, OrderItemVO.NORIGTAXPRICE, this.panel
        .getBodyItem(OrderItemVO.NORIGTAXPRICE).isEdit());
    this.panel.setCellEditable(row, OrderItemVO.NORIGPRICE, this.panel
        .getBodyItem(OrderItemVO.NORIGPRICE).isEdit());
  }

  private void editableWhenNoneMaterial(int row) {
    this.panel.setCellEditable(row, OrderItemVO.CCONTRACTID, false);
    this.panel.setCellEditable(row, OrderItemVO.NQTORIGTAXPRICE, false);
    this.panel.setCellEditable(row, OrderItemVO.NQTORIGPRICE, false);
    this.panel.setCellEditable(row, OrderItemVO.NORIGTAXPRICE, false);
    this.panel.setCellEditable(row, OrderItemVO.NORIGPRICE, false);
  }
}
