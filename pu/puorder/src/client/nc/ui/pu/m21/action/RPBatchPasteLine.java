/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-22 下午04:36:17
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import nc.ui.pu.m21.service.OrderReceivePlanModel;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.batch.BatchPasteLineAction;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.pub.OrderReceivePlanUtils;

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
 * @time 2010-4-22 下午04:36:17
 */
public class RPBatchPasteLine extends BatchPasteLineAction {

  private static final long serialVersionUID = -6295135377129983622L;

  private BatchBillTable list;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);

    this.setCrowno();

    this.getList().getBillCardPanel().getBillModel()
        .loadLoadRelationItemValue();
    this.getList().getBillCardPanel().getBillModel().execLoadFormula();
  }

  /**
   * @return list
   */
  public BatchBillTable getList() {
    return this.list;
  }

  /**
   * @param list 要设置的 list
   */
  public void setList(BatchBillTable list) {
    this.list = list;
  }

  private Set<String> getArrayNotNull(BillCardPanel cardPanel, int iBeginRow,
      int iEndRow) {
    if (iBeginRow > iEndRow) {
      return null;
    }

    Set<String> set = new HashSet<String>();
    for (int i = iBeginRow; i <= iEndRow; ++i) {
      String oValue =
          (String) cardPanel.getBodyValueAt(i, OrderReceivePlanVO.VBILLCODE);
      if (!StringUtil.isEmptyWithTrim(oValue)) {
        set.add(oValue);
      }
    }

    return set;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-22 下午04:39:24
   */
  private void setCrowno() {
    BillCardPanel panel = this.getList().getBillCardPanel();
    int rowCount = panel.getRowCount();
    for (int i = 0; i < rowCount; ++i) {
      String pkOrderB =
          (String) panel.getBodyValueAt(i, OrderReceivePlanVO.PK_ORDER_B);
      String crowno =
          (String) panel.getBodyValueAt(i, OrderReceivePlanVO.CROWNOBB1);
      DefaultConstEnum value = new DefaultConstEnum(pkOrderB, crowno);
      panel.setBodyValueAt(value, i, OrderItemVO.CROWNO);
    }
  }

  @Override
  protected boolean beforePasteLine(Object obj) {
    boolean paste = super.beforePasteLine(obj);
    if (!paste) {
      return paste;
    }
    Set<String> tmpCodes = null;
    BillCardPanel panel = this.getList().getBillCardPanel();
    if (panel.getRowCount() >= 1) {
      tmpCodes = this.getArrayNotNull(panel, 0, panel.getRowCount() - 1);
    }
    // 到货计划号
    if (tmpCodes != null) {
      String[] bufCodes = tmpCodes.toArray(new String[tmpCodes.size()]);
      // 设置到货计划号
      String headBillCode =
          ((OrderReceivePlanModel) this.list.getModel()).getOrderVO().getHVO()
              .getVbillcode();
      String plancode =
          OrderReceivePlanUtils.getNextPlanCode(bufCodes, headBillCode);
      OrderReceivePlanVO rpVO = (OrderReceivePlanVO) obj;
      rpVO.setVbillcode(plancode);
    }

    return true;
  }

  @Override
  protected void doPasteLine(Object obj) {
    super.doPasteLine(obj);

    int index = this.getModel().getSelectedIndex();
    if (index == -1) {
      index = this.getModel().getRows().size();
    }

    OrderReceivePlanVO rpVO = (OrderReceivePlanVO) obj;
    this.getList()
        .getBillCardPanel()
        .setBodyValueAt(rpVO.getPk_order_b(), index,
            OrderReceivePlanVO.PK_ORDER_B);

  }
}
