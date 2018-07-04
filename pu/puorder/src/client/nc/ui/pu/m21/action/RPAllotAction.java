/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-17 上午08:49:23
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import nc.ui.pu.m21.service.OrderReceivePlanModel;
import nc.ui.pu.m21.view.ReceivePlanAllotUI;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BatchBillTableModel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanAllotVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderReceivePlanUtils;
import nc.vo.pu.m21.rule.RPRelationCalculate;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>分货
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-17 上午08:49:23
 */
public class RPAllotAction extends NCAction {

  private static final long serialVersionUID = -2553758111594692665L;

  private BatchBillTable list;

  private BatchBillTableModel model;

  public RPAllotAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_ALLOTGOODS);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    BillCardPanel panel = this.getList().getBillCardPanel();
    int selectRow = panel.getBillTable().getSelectedRow();
    if (selectRow < 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0066")/*
                                                                   * @res
                                                                   * "请选中需分配的行"
                                                                   */);
    }

    OrderReceivePlanVO rpVO =
        (OrderReceivePlanVO) panel.getBillModel().getBodyValueRowVO(selectRow,
            OrderReceivePlanVO.class.getName());
    if (null == rpVO || StringUtil.isEmptyWithTrim(rpVO.getPk_order_b())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0067")/*
                                                                   * @res
                                                                   * "无到货计划行或没有录入明细，不能进行分货"
                                                                   */);
    }

    panel.getBillData().dataNotNullValidateOfBody(
        OrderReceivePlanVO.PK_ORDER_BB1, new CircularlyAccessibleValueObject[] {
          rpVO
        });

    OrderVO orderVO = ((OrderReceivePlanModel) this.model).getOrderVO();
    BillIndex index = new BillIndex(new OrderVO[] {
      orderVO
    });
    IVOMeta meta = orderVO.getMetaData().getVOMeta(OrderItemVO.class);
    OrderItemVO itemVO = (OrderItemVO) index.get(meta, rpVO.getPk_order_b());

    ReceivePlanAllotUI pnlAllot = new ReceivePlanAllotUI(itemVO, rpVO);
    pnlAllot.display(this.list);

    OrderReceivePlanAllotVO[] allotVOs = pnlAllot.getVoResultRPAllot();
    if (!ArrayUtils.isEmpty(allotVOs)) {
      OrderReceivePlanVO[] rPAllotVOs = this.getRPVOsByAllotVOs(rpVO, allotVOs);
      int len = rPAllotVOs.length;
      int[] row = this.getRowsAfterMultiSelect(selectRow, len);
      this.getModel().delLine(selectRow);
      this.getModel().insertLines(selectRow, rPAllotVOs);
      this.execLoadValue(row, itemVO);

      int rowcount = panel.getRowCount();
      for (int i = 0; i < rowcount; ++i) {
        OrderReceivePlanVO tempVO = (OrderReceivePlanVO) this.model.getRow(i);
        panel.getBillModel().setBodyRowVO(tempVO, i);
        if (tempVO.getPk_order_bb1() != null) {
          String pkOrderB = tempVO.getPk_order_b();
          // OrderItemVO orderItemVO = map.get(pkOrderB);
          OrderItemVO orderItemVO = (OrderItemVO) index.get(meta, pkOrderB);
          DefaultConstEnum value =
              new DefaultConstEnum(pkOrderB, orderItemVO.getCrowno());
          panel.setBodyValueAt(value, i, OrderItemVO.CROWNO);
          panel.setBodyValueAt(tempVO.getVbillcode(), i,
              OrderReceivePlanVO.VBILLCODE);
        }
      }

      panel.getBillModel().loadLoadRelationItemValue();
      panel.getBillModel().execLoadFormula();
    }
  }

  /**
   * @return list
   */
  public BatchBillTable getList() {
    return this.list;
  }

  /**
   * @return model
   */
  public BatchBillTableModel getModel() {
    return this.model;
  }

  /**
   * 方法功能描述：分货后的到货计划VO数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param voRP
   * @param voaAllot
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 下午01:35:57
   */
  public OrderReceivePlanVO[] getRPVOsByAllotVOs(OrderReceivePlanVO voRP,
      OrderReceivePlanAllotVO[] voaAllot) {

    if (voaAllot == null || voaAllot.length == 0) {
      return new OrderReceivePlanVO[] {
        voRP
      };
    }

    int iLen = voaAllot.length;
    OrderReceivePlanVO[] voaRP = new OrderReceivePlanVO[iLen];
    RPRelationCalculate cal = new RPRelationCalculate();
    for (int i = 0; i < iLen; i++) {
      voaRP[i] = (OrderReceivePlanVO) voRP.clone();
      // 到货计划号
      voaRP[i].setVbillcode(null);
      // 库存组织、仓库
      if (!voaAllot[i].getPk_arrvstoorg().equals(voaRP[i].getPk_arrvstoorg())) {
        voaRP[i].setPk_recvstordoc(null);
        voaRP[i].setPk_arrvstoorg(voaAllot[i].getPk_arrvstoorg());
        voaRP[i].setPk_arrvstoorg_v(voaAllot[i].getPk_arrvstoorg_v());
      }
      // 数量
      voaRP[i].setNnum(voaAllot[i].getNallotnum());
      // TS置空
      voaRP[i].setPk_order_bb1(null);
      voaRP[i].setTs(null);
      cal.calculate(voaRP[i], OrderReceivePlanVO.NNUM);
    }

    return voaRP;
  }

  /**
   * @param list
   *          要设置的 list
   */
  public void setList(BatchBillTable list) {
    this.list = list;
  }

  /**
   * @param model
   *          要设置的 model
   */
  public void setModel(BatchBillTableModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private void execLoadValue(int[] iaRow, OrderItemVO itemVO) {
    BillCardPanel panel = this.getList().getBillCardPanel();
    for (int i = 0; i < iaRow.length; ++i) {
      int iRow = iaRow[i];
      if (null == itemVO) {
        continue;
      }

      String pk_order_b = itemVO.getPk_order_b();
      panel.setBodyValueAt(pk_order_b, iRow, OrderReceivePlanVO.PK_ORDER_B);
      DefaultConstEnum value =
          new DefaultConstEnum(pk_order_b, itemVO.getCrowno());
      // 行号
      panel.setBodyValueAt(value, iRow, OrderItemVO.CROWNO);

      // 到货计划号
      this.setPlanCode(iRow);
    }
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param iBeginRow
   * @param iEndRow
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 下午01:44:14
   */
  private String[] getArrayNotNull(int iBeginRow, int iEndRow) {
    if (iBeginRow > iEndRow) {
      return null;
    }

    BillCardPanel panel = this.getList().getBillCardPanel();
    Set<String> set = new HashSet<String>();
    for (int i = iBeginRow; i <= iEndRow; ++i) {
      String oValue =
          (String) panel.getBodyValueAt(i, OrderReceivePlanVO.VBILLCODE);
      if (!StringUtil.isEmptyWithTrim(oValue)) {
        set.add(oValue);
      }
    }

    int iSize = set.size();
    if (iSize > 0) {
      return set.toArray(new String[0]);
    }

    return null;
  }

  /**
   * 方法功能描述：选择的行
   * <p>
   * <b>参数说明</b>
   * 
   * @param iCurRow
   * @param iSelectedLen
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 下午01:36:14
   */
  private int[] getRowsAfterMultiSelect(int iCurRow, int iSelectedLen) {

    int SelectedLen = iSelectedLen <= 1 ? 1 : iSelectedLen;
    // 增的行
    int[] iaRow = new int[SelectedLen];
    for (int i = 0; i < SelectedLen; i++) {
      iaRow[i] = iCurRow + i;
    }

    return iaRow;
  }

  /**
   * 方法功能描述：设置到货计划号
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   * @param iRow
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 下午01:44:10
   */
  private void setPlanCode(int iRow) {
    BillCardPanel panel = this.getList().getBillCardPanel();
    // 所有行
    int iTotalRow = panel.getRowCount();
    if (iTotalRow == 0) {
      return;
    }

    // 当前行有到货计划号时不再重新取
    String sCode =
        (String) panel.getBodyValueAt(iRow, OrderReceivePlanVO.VBILLCODE);
    if (!StringUtil.isEmptyWithTrim(sCode)) {
      return;
    }

    String[] saPlanCode = this.getArrayNotNull(0, iTotalRow - 1);
    // 设置到货计划号
    String headBillCode =
        ((OrderReceivePlanModel) this.model).getOrderVO().getHVO()
            .getVbillcode();
    String planCode =
        OrderReceivePlanUtils.getNextPlanCode(saPlanCode, headBillCode);
    OrderReceivePlanVO rpVO = (OrderReceivePlanVO) this.getModel().getRow(iRow);
    rpVO.setVbillcode(planCode);
    panel.setBodyValueAt(planCode, iRow, OrderReceivePlanVO.VBILLCODE);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    return this.getModel().getUiState() == UIState.EDIT
        && this.getModel().getSelectedIndex() != -1
        && this.getModel().getRows().size() > 0;
  }
}
