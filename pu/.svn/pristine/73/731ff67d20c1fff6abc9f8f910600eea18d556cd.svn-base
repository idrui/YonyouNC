/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-22 上午08:45:21
 */
package nc.impl.pu.m422x.action;

import nc.bs.pu.m422x.plugin.StoreReqAppPluginPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m422x.action.rule.approve.FollowupBillChkRule;
import nc.impl.pu.m422x.action.rule.approve.UnApproveValidateRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.rule.ApproverPermissionRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>弃审
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-22 上午08:45:21
 */
public class StoreReqAppUnApproveAction {

  public void addRule(AroundProcesser<StoreReqAppVO> processer) {
    // 状态检查
    processer.addBeforeRule(new UnApproveValidateRule());
    // 后续单据
    processer.addBeforeRule(new FollowupBillChkRule());
    processer.addBeforeRule(new ApproverPermissionRule<StoreReqAppVO>(
        POBillType.MRBill.getCode()));
  }

  public StoreReqAppVO[] unapprove(StoreReqAppVO[] vos, AbstractCompiler2 script) {
    if (ArrayUtils.isEmpty(vos) || null == script) {
      return null;
    }

    PfParameterUtil<StoreReqAppVO> util =
        new PfParameterUtil<StoreReqAppVO>(script.getPfParameterVO(), vos);
    StoreReqAppVO[] originBills = util.getOrginBills();
    StoreReqAppVO[] clientBills = util.getClientFullInfoBill();

    AroundProcesser<StoreReqAppVO> processer =
        new AroundProcesser<StoreReqAppVO>(StoreReqAppPluginPoint.UNAPPROVE);
    this.addRule(processer);

    processer.before(clientBills);
    // 调用平台脚本进行弃审
    try {
      script.procUnApproveFlow(script.getPfParameterVO());
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }

    BillUpdate<StoreReqAppVO> update = new BillUpdate<StoreReqAppVO>();
    StoreReqAppVO[] returnVos = update.update(clientBills, originBills);

    processer.after(returnVos);
    return returnVos;
  }
}
