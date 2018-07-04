/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 上午10:48:17
 */
package nc.impl.pu.m4t.action;

import nc.bs.pu.est.plugin.InitialEstPluginPoint;
import nc.bs.pu.m4t.maintain.rule.maintain.InitialEstNormalPurFlagRule;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.rule.ApproveStatusChkRule;
import nc.vo.pu.m4t.rule.InitialEstAffectCostFlagRule;
import nc.vo.pu.m4t.rule.InitialEstAffectPCCostFlagRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>审批动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-26 上午10:48:17
 */
public class InitialEstApproveAction {
  public InitialEstVO[] approve(InitialEstVO[] vos, AbstractCompiler2 script) {
    if (ArrayUtils.isEmpty(vos) || null == script) {
      return null;
    }

    PfParameterUtil<InitialEstVO> util =
        new PfParameterUtil<InitialEstVO>(script.getPfParameterVO(), vos);
    InitialEstVO[] originBills = util.getOrginBills();
    InitialEstVO[] clientBills = util.getClientFullInfoBill();

    AroundProcesser<InitialEstVO> processer =
        new AroundProcesser<InitialEstVO>(InitialEstPluginPoint.APPROVE);
    this.addRule(processer);

    processer.before(clientBills);

    try {
      script.procFlowBacth(script.getPfParameterVO());
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
    // 可审批状态检查
    processer.addBeforeFinalRule(new ApproveStatusChkRule());
    // 是否是集采标志设置
    processer.addBeforeFinalRule(new InitialEstNormalPurFlagRule());
    // 影响成本设置
    processer.addBeforeFinalRule(new InitialEstAffectCostFlagRule());
    // 影响利润中心成本设置
    processer.addBeforeFinalRule(new InitialEstAffectPCCostFlagRule());

  }
}
