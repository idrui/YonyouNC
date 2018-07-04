/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-22 上午08:45:32
 */
package nc.impl.pu.m422x.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m422x.action.rule.approve.SendApproveFlowCheckRule;
import nc.impl.pu.m422x.action.rule.approve.SendApproveStatusChangeRule;
import nc.impl.pu.m422x.action.rule.approve.SendApproveValidateRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m422x.entity.StoreReqAppVO;

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
 * @time 2010-7-22 上午08:45:32
 */
public class StoreReqAppSendApproveAction {

  public StoreReqAppVO[] sendapprove(StoreReqAppVO[] vos,
      AbstractCompiler2 script) {
    PfParameterUtil<StoreReqAppVO> util =
        new PfParameterUtil<StoreReqAppVO>(script.getPfParameterVO(), vos);
    StoreReqAppVO[] originBills = util.getOrginBills();
    StoreReqAppVO[] clientBills = util.getClientFullInfoBill();

    AroundProcesser<StoreReqAppVO> processer =
        new AroundProcesser<StoreReqAppVO>(null);
    this.addRule(processer);
    processer.before(clientBills);
    StoreReqAppVO[] returnVos =
        new BillUpdate<StoreReqAppVO>().update(clientBills, originBills);
    return returnVos;
  }

  private void addRule(AroundProcesser<StoreReqAppVO> processer) {
    processer.addBeforeRule(new SendApproveFlowCheckRule());
    processer.addBeforeRule(new SendApproveValidateRule());
    processer.addBeforeRule(new SendApproveStatusChangeRule());
  }
}
