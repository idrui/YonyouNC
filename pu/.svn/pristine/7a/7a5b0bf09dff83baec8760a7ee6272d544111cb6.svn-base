package nc.ui.pu.m20.view;

import java.util.HashSet;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单修订卡片编辑界面
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-21 下午04:20:04
 */
public class PraybillRBillForm extends PraybillBillForm {

  private static final long serialVersionUID = 9054268203741071834L;

  /**
   * 方法功能描述：设置修订单据的默认值。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author GGR
   * @time 2010-6-21 下午04:20:19
   */
  public static void setDefaultReviseItems(BillCardPanel bcp) {
    // 修订人
    bcp.setTailItem(PraybillHeaderVO.CREVISEOPERID, AppContext.getInstance()
        .getPkUser());
    // 修订日期
    bcp.setTailItem(PraybillHeaderVO.TREVISIONTIME, AppContext.getInstance()
        .getBusiDate());
    // 版本+1
    int valueObject =
        ((Integer) bcp.getHeadItem(PraybillHeaderVO.NVERSION).getValueObject())
            .intValue();
    bcp.setHeadItem(PraybillHeaderVO.NVERSION, Integer.valueOf(valueObject + 1));
  }

  /**
   * 方法功能描述：得到界面某行是否关闭。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 下午02:47:57
   */
  private boolean isRowClose(int row) {
    // 关闭状态
    UFBoolean browclose =
        (UFBoolean) this.getBillCardPanel().getBodyValueAt(row,
            PraybillItemVO.BROWCLOSE);
    return null != browclose && browclose.booleanValue();
  }

  /**
   * 方法功能描述：某行是否有后续单据。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 下午02:49:51
   */
  private boolean isRowHaveAfterBill(int row) {

    // 判断累计订货数量 ，生成合同次数 ，生成价格审批单次数，生成询报价单次数，发布到电子商务来确定是否有后续单据
    if (MathTool.compareTo(
        (UFDouble) this.getBillCardPanel().getBodyValueAt(row,
            PraybillItemVO.NACCUMULATENUM), UFDouble.ZERO_DBL) > 0
        || null != this.getBillCardPanel().getBodyValueAt(row,
            PraybillItemVO.NGENCT)
        && ((Integer) this.getBillCardPanel().getBodyValueAt(row,
            PraybillItemVO.NGENCT)).intValue() > 0
        || null != this.getBillCardPanel().getBodyValueAt(row,
            PraybillItemVO.NPRICEAUDITBILL)
        && ((Integer) this.getBillCardPanel().getBodyValueAt(row,
            PraybillItemVO.NPRICEAUDITBILL)).intValue() > 0
        || null != this.getBillCardPanel().getBodyValueAt(row,
            PraybillItemVO.NQUOTEBILL)
        && ((Integer) this.getBillCardPanel().getBodyValueAt(row,
            PraybillItemVO.NQUOTEBILL)).intValue() > 0
        || null != this.getBillCardPanel().getBodyValueAt(row,
            PraybillItemVO.BPUBLISHTOEC)
        && ((UFBoolean) this.getBillCardPanel().getBodyValueAt(row,
            PraybillItemVO.BPUBLISHTOEC)).booleanValue()) {
      return true;
    }

    return false;
  }

  /**
   * 方法功能描述：单据号不能修改；关闭的行不允许修订；已有后续业务单据 表体不能修改。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author GGR
   * @time 2010-6-22 下午03:12:10
   */
  private void setEditableWhenDownFlow() {

    // 表体行号，物料，采购组织
    String[] items =
        new String[] {
          PraybillItemVO.CROWNO, PraybillItemVO.PK_MATERIAL,
          PraybillItemVO.PK_PURCHASEORG, PraybillItemVO.PK_REQDEPT,
          PraybillItemVO.CASTUNITID, PraybillItemVO.CASSCUSTID,
          PraybillItemVO.CORDERTRANTYPECODE
        };

    HashSet<String> heads = new HashSet<String>();
    heads.add(PraybillHeaderVO.CHPROJECTID);

    boolean isAllRowNotEdit = true;
    boolean hasDown = false;
    for (int i = 0; i < this.getBillCardPanel().getRowCount(); ++i) {
      // 关闭的行，表体不允许修改
      if (this.isRowClose(i)) {
        hasDown = true;
        for (BillItem item : this.getBillCardPanel().getBodyItems()) {
          this.getBillCardPanel().setCellEditable(i, item.getKey(), false);
        }
      }
      else if (this.isRowHaveAfterBill(i)) { // 未关闭有后续单据
        hasDown = true;
        // 表体
        for (String item : items) {
          this.getBillCardPanel().setCellEditable(i, item, false);
        }
      }
      else {
        isAllRowNotEdit = false;
      }

      this.getBillCardPanel().setCellEditable(i, PraybillItemVO.CASSCUSTID,
          false);
    }

    // 表体行都不能修改时，表头不能修改
    if (isAllRowNotEdit) {
      BillItem[] item = this.getBillCardPanel().getHeadShowItems();
      for (int i = 0; i < item.length; i++) {
        item[i].setEnabled(false);
      }
    }
    else {
      BillItem[] item = this.getBillCardPanel().getHeadShowItems();
      for (int i = 0; i < item.length; i++) {
        // 单据号,委外不能修改
        if (item[i].getKey().equals(PraybillHeaderVO.VBILLCODE)
            || item[i].getKey().equals(PraybillHeaderVO.BSCTYPE)) {
          item[i].setEnabled(false);
        }
        else {
          item[i].setEnabled(item[i].isM_bReviseFlag());

          if (hasDown && heads.contains(item[i].getKey())) {
            item[i].setEnabled(false);
          }
        }
      }
    }

  }

  @Override
  protected void onEdit() {
    super.onEdit();

    // 设置修订请购单的默认值
    PraybillRBillForm.setDefaultReviseItems(this.getBillCardPanel());

    // 设置表头表体的可编辑性
    this.setEditableWhenDownFlow();
  }
}
