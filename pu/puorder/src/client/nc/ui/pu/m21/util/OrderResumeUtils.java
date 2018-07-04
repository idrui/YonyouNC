package nc.ui.pu.m21.util;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.pub.common.context.PFlowContext;
import nc.vo.ct.exception.CtToleranceException;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.exception.AskMaxPriceException;
import nc.vo.pu.m21.exception.AskMaxStockException;
import nc.vo.pu.m21.exception.AskNotMeetCntCenPurRuleException;
import nc.vo.pu.m21.exception.AskNotMeetVrmCenPurRuleException;
import nc.vo.pu.m21.exception.AskNumException;
import nc.vo.pu.m21.exception.AskOverPresentStockException;
import nc.vo.pu.m21.exception.AskReviseToleException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pflow.PfUserObject;

/**
 * 订单交互异常提示工具类。这里处理订单所有交互异常的信息，这些交换异常可能来自多个action。<br>
 * 
 * @since 6.1 <br>
 *        此类逻辑从以下前台action重构过来：<br>
 *        nc.ui.pu.m21.action.OrderReplenishSaveAction<br>
 *        nc.ui.pu.m21.action.OrderReplenishSaveAction，<br>
 *        nc.ui.pu.m21.action.ReviseSaveAction<br>
 *        nc.ui.pu.m21.action.RPBatchSaveAction<br>
 * @version 2012-6-20 下午05:21:55
 * @author tianft
 */
public class OrderResumeUtils {

  /**
   * 是否已处理交互信息
   * 
   * @param resumeInfo
   * @param pflowContext
   * @return
   */
  public static UFBoolean isResume(IResumeException resumeInfo,
      PFlowContext pflowContext) {
    int answer =
        MessageDialog.showYesNoDlg(pflowContext.getParent(), null,
            ((Exception) resumeInfo).getMessage());

    if (UIDialog.ID_YES != answer) {
      return UFBoolean.FALSE;
    }

    OrderContext ctx = null;
    PfUserObject pfuo = pflowContext.getUserObj();
    if (null == pfuo) {
      ctx = new OrderContext();
      pfuo = new PfUserObject();
    }
    else {
      ctx = (OrderContext) pfuo.getUserObject();
    }

    if (resumeInfo instanceof AskMaxPriceException) {
      ctx.setMaxPriceConfirm(UFBoolean.TRUE);
    }
    // 合同
    else if (resumeInfo instanceof CtToleranceException) {
      ctx.setContractConfirm(UFBoolean.TRUE);
    }
    // 退货订单超现存量
    else if (resumeInfo instanceof AskOverPresentStockException) {
      ctx.setOverPresentStockConfirm(UFBoolean.TRUE);
    }
    else if (resumeInfo instanceof AskMaxStockException) {
      ctx.setMaxStockConfirm(UFBoolean.TRUE);
    }
    else if (resumeInfo instanceof AskNumException) {
      ctx.setPrayNumToleranceConfirm(UFBoolean.TRUE);
    }
    else if (resumeInfo instanceof AskReviseToleException) {
      ctx.setReviseToleranceConfirm(UFBoolean.TRUE);
    }
    // else if (resumeInfo instanceof AskNotMeetCenPurRuleException) {
    // ctx.setMeetcenpurruleconfirm(UFBoolean.TRUE);
    // }
    else if (resumeInfo instanceof AskNotMeetCntCenPurRuleException) {
      ctx.setMeetcntcenpurruleconfirm(UFBoolean.TRUE);
    }
    else if (resumeInfo instanceof AskNotMeetVrmCenPurRuleException) {
      ctx.setMeetvrmcenpurruleconfirm(UFBoolean.TRUE);
    }
    else {
      return null;// 意味着没处理
    }
    pfuo.setUserObject(ctx);
    pflowContext.setUserObj(pfuo);
    return UFBoolean.TRUE;
  }

}
