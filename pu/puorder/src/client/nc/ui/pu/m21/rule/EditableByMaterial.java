package nc.ui.pu.m21.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ϱ༭���������ĵ�����Ŀ�༭��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-4-22 ����11:04:55
 */
public class EditableByMaterial {
  private BillCardPanel panel;

  public EditableByMaterial(BillCardPanel panel) {
    this.panel = panel;
  }

  /**
   * �����������������ÿɱ༭��
   * <p>
   * <b>����˵��</b>
   * 
   * @param rows Ҫ���ÿɱ༭�Ե���
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-4-22 ����01:07:32
   */
  public void setEditable(int[] rows) {
    for (int row : rows) {
      // ���ϵĿɱ༭����ģ�����
      this.panel.setCellEditable(row, OrderItemVO.PK_MATERIAL, this.panel
          .getBodyItem(OrderItemVO.PK_MATERIAL).isEdit());
      // ���ֵĿɱ༭����ģ�����
      this.panel.getHeadTailItem(OrderHeaderVO.CORIGCURRENCYID).setEnabled(
          this.panel.getHeadTailItem(OrderHeaderVO.CORIGCURRENCYID).isEdit());

      // �������������������ֶεĿɱ༭��
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
