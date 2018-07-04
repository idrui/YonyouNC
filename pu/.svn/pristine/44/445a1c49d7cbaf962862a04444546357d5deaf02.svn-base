package nc.ui.pu.m21.view;

import java.util.HashSet;
import java.util.Set;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单修订卡片编辑界面
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-15 上午10:02:58
 */
public class OrderReviseBillForm extends OrderBillForm {

  private static final long serialVersionUID = 9054268203741071834L;

  /**
   * 方法功能描述：设置修订订单的默认值。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-3-21 上午09:47:09
   */
  public static void setDefaultReviseItems(BillCardPanel bcp) {
    // 修订人
    bcp.setTailItem(OrderHeaderVO.CREVISEPSN, AppContext.getInstance()
        .getPkUser());
    // 修订日期
    bcp.setTailItem(OrderHeaderVO.TREVISIONTIME, AppContext.getInstance()
        .getBusiDate());
    // 版本+1
    bcp.setHeadItem(OrderHeaderVO.NVERSION, Integer.valueOf(((Integer) bcp
        .getHeadItem(OrderHeaderVO.NVERSION).getValueObject()).intValue() + 1));
  }

  /**
   * 方法功能描述：得到界面某行是否关闭
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-11 下午08:47:08
   */
  private boolean isRowActive(int row) {
    // 激活状态
    Integer fisactive =
        (Integer) this.getBillCardPanel().getBodyValueAt(row,
            OrderItemVO.FISACTIVE);
    return ObjectUtils.equals(fisactive, EnumActive.ACTIVE.value());
  }

  /**
   * 方法功能描述：某行是否有后续单据
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-11 下午08:54:33
   */
  private boolean isRowHaveAfterBill(int row) {
    // 判断累计发票数量 ，累计入库数量 ，累计到货数量，确定是否有后续单据
    // 在途
    if (MathTool.compareTo(
        (UFDouble) this.getBillCardPanel().getBodyValueAt(row,
            OrderItemVO.NACCUMARRVNUM), UFDouble.ZERO_DBL) > 0
        || MathTool
            .compareTo(
                (UFDouble) this.getBillCardPanel().getBodyValueAt(row,
                    OrderItemVO.NACCUMSTORENUM), UFDouble.ZERO_DBL) > 0
        || MathTool.compareTo((UFDouble) this.getBillCardPanel()
            .getBodyValueAt(row, OrderItemVO.NACCUMINVOICENUM),
            UFDouble.ZERO_DBL) > 0) {
      return true;
    }

    return false;
  }

  /**
   * 方法功能描述：关闭的行不允许修订；已有后续业务单据，则表头退货/库基于原订单补货不可以修改，
   * 表体批次号、需求部门、项目、收货仓库、收货地址、赠品、收货库存组织不可修改。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-24 下午02:34:28
   */
  private void setEditableWhenDownFlow() {
    BillItem[] headItems = this.getBillCardPanel().getHeadItems();
    for (BillItem headItem : headItems) {
      headItem.setEnabled(headItem.isM_bReviseFlag() && headItem.isEdit());
    }

    // 如果订单勾选退货的话，则不允许修改退货/库基于原订单补货信息。
    if ((Boolean) this.getBillCardPanel().getHeadItem(OrderHeaderVO.BRETURN)
        .getValueObject()) {
      this.getBillCardPanel().getHeadItem(OrderHeaderVO.BREFWHENRETURN)
          .setEnabled(false);
    }

    BillItem[] bodyItems = this.getBillCardPanel().getBodyItems();
    // 表体批次号、需求部门、项目、收货仓库、收货地址、赠品、收货库存组织
    String[] items =
        new String[] {
          OrderItemVO.PK_BATCHCODE, OrderItemVO.VBATCHCODE,
          OrderItemVO.PK_REQDEPT, OrderItemVO.CPROJECTID,
          OrderItemVO.CPROJECTTASKID, OrderItemVO.PK_RECVSTORDOC,
          OrderItemVO.PK_RECEIVEADDRESS, OrderItemVO.BLARGESS,
          OrderItemVO.PK_ARRVSTOORG, OrderItemVO.CASSCUSTID
        };
    Set<String> set = new HashSet<String>();
    for (String item : items) {
      set.add(item);
    }

    boolean first = true;
    for (int i = 0; i < this.getBillCardPanel().getRowCount(); ++i) {
      // 关闭的行，表体不允许修改
      if (!this.isRowActive(i)) {
        for (BillItem item : this.getBillCardPanel().getBodyItems()) {
          this.getBillCardPanel().setCellEditable(i, item.getKey(), false);
        }
      }
      else if (this.isRowHaveAfterBill(i)) { // 未关闭有后续单据
        // 表头退货/库基于原订单补货
        if (first) {
          this.getBillCardPanel().getHeadItem(OrderHeaderVO.BREFWHENRETURN)
              .setEnabled(false);
          first = false;
        }

        // 表体
        for (BillItem bodyItem : bodyItems) {
          if (set.contains(bodyItem.getKey())) {
            this.getBillCardPanel()
                .setCellEditable(i, bodyItem.getKey(), false);
          }
          else {
            this.getBillCardPanel().setCellEditable(i, bodyItem.getKey(),
                bodyItem.isM_bReviseFlag());
          }
        }
      }
      // 未关闭-无后续单据生成
      else {
        for (BillItem bodyItem : bodyItems) {
          this.getBillCardPanel().setCellEditable(i, bodyItem.getKey(),
              bodyItem.isM_bReviseFlag());
        }
      }
    }

  }

  @Override
  protected void onEdit() {
    super.onEdit();

    // 设置修订订单的默认值
    // OrderReviseBillForm.setDefaultReviseItems(this.getBillCardPanel());

    // 设置表头表体的可编辑性
    this.setEditableWhenDownFlow();
  }

}
