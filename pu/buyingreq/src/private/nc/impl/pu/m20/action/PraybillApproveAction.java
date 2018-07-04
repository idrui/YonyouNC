/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-28 上午10:04:37
 */
package nc.impl.pu.m20.action;

import nc.bs.pu.m20.maintain.rule.ATPAfterUpdateRule;
import nc.bs.pu.m20.maintain.rule.ATPBeforeUpdateRule;
import nc.bs.pu.m20.maintain.rule.approve.ApproveBudgetCtrlRule;
import nc.bs.pu.m20.maintain.rule.approve.ApproveUpdateBodyStsRule;
import nc.bs.pu.m20.maintain.rule.approve.CheckApproveRule;
import nc.bs.pu.m20.maintain.rule.approve.SendMsgToUserRule;
import nc.bs.pu.m20.plugin.PraybillPluginPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.PuBusiLogActionCode;
import nc.vo.pu.pub.enumeration.PuBusiLogPathCode;
import nc.vo.pu.pub.rule.busilog.WriteOperateLogRule;
import nc.vo.pu.pub.rule.pf.ApprovedVOFilterRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单审批Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-28 上午10:04:37
 */
public class PraybillApproveAction {

  public PraybillVO[] approve(PraybillVO[] vos, AbstractCompiler2 script) {
    PfParameterUtil<PraybillVO> util =
        new PfParameterUtil<PraybillVO>(script == null ? null
            : script.getPfParameterVO(), vos);
    PraybillVO[] originBills = util.getOrginBills();
    AroundProcesser<PraybillVO> processer =
        new AroundProcesser<PraybillVO>(PraybillPluginPoint.APPROVE);
    this.addBeforeRule(processer);
    this.addAfterRule(processer);

    // 前规则
    processer.before(vos);

    // 调用平台脚本进行审批
    if (null != script) {
      try {
        script.procFlowBacth(script.getPfParameterVO());
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }

    // 把VO持久化到数据库中
    BillUpdate<PraybillVO> update = new BillUpdate<PraybillVO>();
    PraybillVO[] returnVos = update.update(vos, originBills);

    // 后规则
    processer.after(returnVos);
    return returnVos;
  }

  private void addAfterRule(AroundProcesser<PraybillVO> processer) {

    // 写业务日志
    processer.addAfterRule(new WriteOperateLogRule<PraybillVO>(
        PuBusiLogPathCode.prayBillApprovePath.getCode(),
        PuBusiLogActionCode.approve.getCode()));
    // 审批后可用量处理
    processer.addAfterRule(new ATPAfterUpdateRule());
    // 审批的后规则，只过滤到所有已经审批过的VO，后续规则注意判断空
    processer.addAfterRule(new ApprovedVOFilterRule<PraybillVO>());
    // 给业务员对应的操作员发消息
    processer.addAfterRule(new SendMsgToUserRule());
    // 回写采购计划
    processer.addAfterRule(new ApproveBudgetCtrlRule());
    


   

  }

  private void addBeforeRule(AroundProcesser<PraybillVO> processer) {
    // 审批前检查
    processer.addBeforeRule(new CheckApproveRule());
    // 审批前可用量处理
    processer.addBeforeRule(new ATPBeforeUpdateRule());
    //	 审时更改表体计划状态为‘计划生效’add by zjf
    processer.addBeforeRule( new ApproveUpdateBodyStsRule());

  }

}
