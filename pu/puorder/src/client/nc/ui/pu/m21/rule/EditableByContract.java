package nc.ui.pu.m21.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.scmpub.res.billtype.CTBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>跟合同相关的字段的可编辑性
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-4-22 下午01:07:04
 */
public class EditableByContract {
  private BillCardPanel panel;

  public EditableByContract(BillCardPanel panel) {
    this.panel = panel;
  }

  /**
   * 方法功能描述：设置可编辑性
   * 对于单价，按照合同的“单价控制方式”回写控制，不在订单上控制了
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          要设置可编辑性的行
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-4-22 下午01:07:23
   */
  public void setEditable(int[] rows) {
    int count = 0;// 关联合同行数
    for (int row : rows) {
      Object obj = this.panel.getBodyValueAt(row, OrderItemVO.CSOURCETYPECODE);
      if (obj != null && obj.equals(CTBillType.PurDaily.getCode())) {
        this.editableFromContract(row);
      }
      else {
        this.editableNotFromContract(row, count);
      }
    }
    if (count > 0) {
      this.panel.getHeadTailItem(OrderHeaderVO.CORIGCURRENCYID).setEnabled(
          false);
    }
    else {
      this.panel.getHeadTailItem(OrderHeaderVO.CORIGCURRENCYID)
          .setEnabled(true);
    }
  }

  private void editableFromContract(int row) {
    // 合同不可编辑
    this.panel.setCellEditable(row, OrderItemVO.CCONTRACTID, false);
    // 币种不可编辑
    this.panel.getHeadTailItem(OrderHeaderVO.CORIGCURRENCYID).setEnabled(false);
    // 根据合同控制物料的可编辑性
    this.materialEditable(row);
    // 设置单价的可编辑性
    // this.priceEditable(row);
  }

  private void editableNotFromContract(int row, int count) {
    Object obj = this.panel.getBodyValueAt(row, OrderItemVO.CCONTRACTROWID);
    // 如果关联过合同
    if (obj != null) {
      // 合同的编辑性根据单据模板的设置控制
      this.panel.setCellEditable(row, OrderItemVO.CCONTRACTID, this.panel
          .getBodyItem(OrderItemVO.CCONTRACTID).isEdit());
      count++;
      // 币种不可编辑
      // this.panel.getHeadTailItem(OrderHeaderVO.CORIGCURRENCYID).setEnabled(
      // false);
      // 设置单价的可编辑性
      // this.priceEditable(row);
    }
    else {
      // 合同的编辑性根据单据模板的设置控制
      this.panel.setCellEditable(row, OrderItemVO.CCONTRACTID, this.panel
          .getBodyItem(OrderItemVO.CCONTRACTID).isEdit());
      // 币种不可编辑
      // this.panel.getHeadTailItem(OrderHeaderVO.CORIGCURRENCYID)
      // .setEnabled(true);
      // 设置单价的可编辑性
      // this.priceEditable(row);
    }
  }

  private void materialEditable(int row) {
    // duy 根据合同控制物料的可编辑性
    // 编辑前控制
    this.panel.setCellEditable(row, OrderItemVO.PK_MATERIAL, this.panel
        .getBodyItem(OrderItemVO.PK_MATERIAL).isEdit());
  }

  // private void priceEditable(int row) {
  // // duy 设置单价的可编辑性
  // // 区分关联合同
  // this.panel.setCellEditable(row, OrderItemVO.NQTORIGTAXPRICE, this.panel
  // .getBodyItem(OrderItemVO.NQTORIGTAXPRICE).isEdit());
  // }
}
