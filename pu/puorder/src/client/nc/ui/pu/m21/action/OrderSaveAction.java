/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-27 ����03:34:19
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.pu.m21.util.OrderResumeUtils;
import nc.ui.pu.uif2.PUBillManageModel;
import nc.ui.pu.uif2.PUUIState;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.uif2.UIState;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.rule.NumAndOrigmnySum;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������涯����ť
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-27 ����03:34:19
 */
public class OrderSaveAction extends SaveScriptAction {

  private static final long serialVersionUID = 7523477451846576372L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (PUUIState.EDIT_REVISE == this.getPUUIState()) {
      this.setActionName("REVISE");
    }
    else {
      this.setActionName("SAVEBASE");
    }
    super.doAction(e);

    if (UIState.NOT_EDIT == this.getModel().getUiState()) {
      // ����Ϊ��̬ͨ
      ((PUBillManageModel) this.getModel()).setPuUIState(PUUIState.NOT_EDIT);
    }
  }

  private PUUIState getPUUIState() {
    return ((PUBillManageModel) this.getModel()).getPuUIState();
  }

  @Override
  protected boolean isResume(IResumeException resumeInfo) {
    UFBoolean isResume =
        OrderResumeUtils.isResume(resumeInfo, this.getFlowContext());
    if (isResume == null) {
      return super.isResume(resumeInfo);
    }
    return isResume.booleanValue();
  }

  @Override
  protected Object[] processBefore(Object[] vos) {
    Object[] objs = super.processBefore(vos);
    for (Object obj : objs) {
      OrderVO vo = (OrderVO) obj;
      BillHelper<OrderVO> bill = new BillHelper<OrderVO>(vo);
      NumAndOrigmnySum sum = new NumAndOrigmnySum(bill);
      sum.setBlargessField(OrderItemVO.BLARGESS);
      sum.sum();
    }
    return objs;
  }

}
