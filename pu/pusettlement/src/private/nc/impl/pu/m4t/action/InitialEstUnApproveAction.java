/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 上午10:49:24
 */
package nc.impl.pu.m4t.action;

import nc.bs.pu.est.plugin.InitialEstPluginPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m4t.action.rule.unapprove.CancelSendAPRule;
import nc.impl.pu.m4t.action.rule.unapprove.FollowupProcChkRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.rule.UnApproveStatusChkRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>弃审动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-26 上午10:49:24
 */
public class InitialEstUnApproveAction {
  public InitialEstVO[] unapprove(InitialEstVO[] vos, AbstractCompiler2 script) {
    if (ArrayUtils.isEmpty(vos) || null == script) {
      return null;
    }

    PfParameterUtil<InitialEstVO> util =
        new PfParameterUtil<InitialEstVO>(script.getPfParameterVO(), vos);
    InitialEstVO[] originBills = util.getOrginBills();
    InitialEstVO[] clientBills = util.getClientFullInfoBill();

    AroundProcesser<InitialEstVO> processer =
        new AroundProcesser<InitialEstVO>(InitialEstPluginPoint.UNAPPROVE);
    this.addRule(processer);

    processer.before(clientBills);
    // 调用平台脚本进行弃审
    try {
      script.procUnApproveFlow(script.getPfParameterVO());
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }

    BillUpdate<InitialEstVO> update = new BillUpdate<InitialEstVO>();
    InitialEstVO[] returnVos = update.update(clientBills, originBills);

    processer.after(returnVos);
    return returnVos;
  }

  private void addRule(AroundProcesser<InitialEstVO> processer) {
    processer.addBeforeRule(new UnApproveStatusChkRule());
    processer.addBeforeRule(new FollowupProcChkRule());
    // 需要更新表体的标记，所以放在前规则了
    processer.addBeforeRule(new CancelSendAPRule());
  }
}
