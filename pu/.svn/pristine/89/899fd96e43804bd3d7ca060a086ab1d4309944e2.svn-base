package nc.ui.pu.m21.action;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.pu.m21.util.OrderResumeUtils;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.PlanArriveDateRule;
import nc.vo.pu.pub.rule.ItemNotNullCheckRule;
import nc.vo.pu.pub.rule.NumAndOrigmnySum;
import nc.vo.pu.pub.rule.RowNoCheckRule;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单修订保存按钮动作类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-16 下午02:36:56
 */
public class ReviseSaveAction extends SaveScriptAction {

  private static final long serialVersionUID = -2531146636955636096L;

  @Override
  public void doBeforAction() {
    super.doBeforAction();
    OrderVO vo = (OrderVO) this.editor.getValue();

    // 表体非空检查
    ItemNotNullCheckRule itemCheck = new ItemNotNullCheckRule();
    itemCheck.checkItem(new OrderVO[] {
      vo
    });

    // 行号
    RowNoCheckRule rowRule = new RowNoCheckRule();
    rowRule.checkRowNo(new OrderVO[] {
      vo
    }, OrderItemVO.CROWNO);

    // 计划到货日期不能早于订单日期
    PlanArriveDateRule rule = new PlanArriveDateRule();
    rule.checkPlanArriveDate(new OrderVO[] {
      vo
    });
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
