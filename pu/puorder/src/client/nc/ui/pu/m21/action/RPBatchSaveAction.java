/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-15 上午09:00:47
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.pu.m21.service.OrderReceivePlanModel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.batch.BatchSaveAction;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.exception.AskNumException;
import nc.vo.pu.m21.rule.RPDPlanarrvdateCheck;
import nc.vo.pu.m21.rule.RPNaccumrpnumCheck;
import nc.vo.pu.m21.rule.RPRowNumCheck;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

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
 * @time 2010-4-15 上午09:00:47
 */
public class RPBatchSaveAction extends BatchSaveAction {

  private static final long serialVersionUID = -4605807453257160371L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.doBeforeAction();

    this.saveRP(e);

    this.setCrowno();
    this.getEditor().getBillCardPanel().getBillModel()
        .loadLoadRelationItemValue();
    this.getEditor().getBillCardPanel().getBillModel().execLoadFormula();
  }

  /**
   * 方法功能描述：校验
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-6-1 上午11:11:32
   */
  private void doBeforeAction() {
    OrderReceivePlanModel model = (OrderReceivePlanModel) this.getModel();
    List<Object> list = model.getRows();

    if (0 == list.size()) {
      return;
    }

    OrderReceivePlanVO[] rpVOs = new OrderReceivePlanVO[list.size()];
    rpVOs = list.toArray(rpVOs);
    OrderVO orderVO = model.getOrderVO();

    // 到货数量不能大于数量
    RPRowNumCheck numCheck = new RPRowNumCheck();
    numCheck.numCheck(rpVOs);

    // 计划到货日期不早于订单日期
    RPDPlanarrvdateCheck dPlanarrvdateCheck = new RPDPlanarrvdateCheck();
    dPlanarrvdateCheck.checkDPlanarrvdate(rpVOs, orderVO);

    // 累计到货计划数量不能小于订单数量
    RPNaccumrpnumCheck naccumrpnumCheck = new RPNaccumrpnumCheck();
    naccumrpnumCheck.checkNaccumrpnum(rpVOs, orderVO);
  }

  private boolean isResume(IResumeException resumeInfo) {
    int answer =
        MessageDialog.showYesNoDlg(
            this.getModel().getContext().getEntranceUI(), null,
            ((Exception) resumeInfo).getMessage());
    if (UIDialog.ID_YES != answer) {
      return false;
    }
    if (resumeInfo instanceof AskNumException) {
      OrderReceivePlanModel model = (OrderReceivePlanModel) this.getModel();
      model.setConfirm(UFBoolean.TRUE);
    }
    return true;
  }

  private void saveRP(ActionEvent event) throws Exception {
    boolean isContinue = true;
    while (isContinue) {
      try {
        super.doAction(event);
        isContinue = false;
        OrderReceivePlanModel model = (OrderReceivePlanModel) this.getModel();
        model.setConfirm(UFBoolean.FALSE);
      }
      catch (Exception e) {
        Throwable ex = ExceptionUtils.unmarsh(e);
        if (ex instanceof IResumeException) {
          if (this.isResume((IResumeException) ex)) {
            isContinue = true;
          }
          else {
            isContinue = false;
          }
        }
        else {
          OrderReceivePlanModel model = (OrderReceivePlanModel) this.getModel();
          model.setConfirm(UFBoolean.FALSE);
          throw e;
        }
      }
    }
  }

  /**
   * 方法功能描述：设置行号
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 下午01:43:09
   */
  private void setCrowno() {
    BillCardPanel panel = this.getEditor().getBillCardPanel();
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

}
