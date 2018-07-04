package nc.ui.pu.m21.action;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.pu.m21.util.OrderResumeUtils;
import nc.ui.pu.m21.view.OrderBillForm;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.rule.NumAndOrigmnySum;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.lang.UFBoolean;

/**
 * 补货订单保存
 * 原来和采购订单维护使用一个OrderSaveAction
 * OrderSaveAction因为审批中修订改动比较大，所以再重新写一个
 * 
 * @since 6.0
 * @version 2011-12-22 下午04:08:48
 * @author wuxla
 */
public class OrderReplenishSaveAction extends SaveScriptAction {

  private static final long serialVersionUID = 3808744009597149103L;

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
  
  @Override
  protected void beforeDoAction() {
  	OrderBillForm billForm =	(OrderBillForm)this.editor;
  	billForm.getBillCardPanel().getBodyTabbedPane().setSelectedIndex(0);
  	super.beforeDoAction();
  }
}
