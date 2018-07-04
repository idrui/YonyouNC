/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-28 上午10:06:02
 */
package nc.impl.pu.m20.action;

import nc.bs.pu.m20.maintain.rule.unapprove.UnApproveBudgetCtrlRule;
import nc.bs.pu.m20.maintain.rule.unapprove.UnApproveCgfanRule;
import nc.bs.pu.m20.maintain.rule.unapprove.UnApproveChkRule;
import nc.bs.pu.m20.plugin.PraybillPluginPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.pu.reference.ic.ATPServices;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.PuBusiLogActionCode;
import nc.vo.pu.pub.enumeration.PuBusiLogPathCode;
import nc.vo.pu.pub.rule.ApproverPermissionRule;
import nc.vo.pu.pub.rule.busilog.WriteOperateLogRule;
import nc.vo.pu.pub.rule.pf.UnApprovedVOFilterRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

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
 * @author linsf
 * @time 2010-1-28 上午10:06:02
 */
public class PraybillUnApproveAction {

  public PraybillVO[] unApprove(PraybillVO[] vos, AbstractCompiler2 script) {
    PfParameterUtil<PraybillVO> util =
        new PfParameterUtil<PraybillVO>(script == null ? null
            : script.getPfParameterVO(), vos);
    PraybillVO[] originBills = util.getOrginBills();

    CompareAroundProcesser<PraybillVO> processer =
        new CompareAroundProcesser<PraybillVO>(PraybillPluginPoint.UNAPPROVE);
    // 可用量更新前处理
    ATPServices.modifyATPBefore(POBillType.PrayBill.getCode(), vos);

    this.addBeforeRule(processer);
    this.addAfterRule(processer, originBills);
    processer.before(vos, originBills);
    // 调用平台脚本进行弃审
    if (null != script) {
      try {
        script.procUnApproveFlow(script.getPfParameterVO());
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }
    // 把VO持久化到数据库中
    BillUpdate<PraybillVO> update = new BillUpdate<PraybillVO>();
    PraybillVO[] returnVos = update.update(vos, originBills);
    // 可用量
    ATPServices.modifyATPAfter(POBillType.PrayBill.getCode(), vos);

    processer.after(returnVos, originBills);

    return returnVos;
  }

  private void addAfterRule(CompareAroundProcesser<PraybillVO> processer,
      PraybillVO[] origVos) {
    // 写业务日志
    processer.addAfterRule(new WriteOperateLogRule<PraybillVO>(
        PuBusiLogPathCode.prayBillApprovePath.getCode(),
        PuBusiLogActionCode.unapprove.getCode()));
    // 过滤出原来是审批过的，当前操作后取消审批的VO，只有这样的才算真正的取消审批（有审批流时会有这种情况）
    processer.addAfterRule(new UnApprovedVOFilterRule<PraybillVO>(origVos));
    // 弃审回写采购计划
    processer.addAfterRule(new UnApproveBudgetCtrlRule());
  }

  private void addBeforeRule(CompareAroundProcesser<PraybillVO> processer) {
    // 弃审检查
    processer.addBeforeRule(new UnApproveChkRule());
    
    //弃审下游生产采购方案抛错校验 add by zjf
    processer.addBeforeRule(new UnApproveCgfanRule());
   
    processer.addBeforeRule(new ApproverPermissionRule<PraybillVO>(
        POBillType.PrayBill.getCode()));
  }

}
