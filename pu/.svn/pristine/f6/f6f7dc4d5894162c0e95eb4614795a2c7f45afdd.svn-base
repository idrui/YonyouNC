package nc.impl.pu.m23.approve.action;

import nc.bs.pu.m23.plugin.ArriveActionPlugInPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m23.approve.rule.ChkCanUnSendApproveRule;
import nc.impl.pu.m23.approve.rule.UnSendApproveAfterEventRule;
import nc.impl.pu.m23.approve.rule.UnSendApproveBeforeEventRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单的收回对应的Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 上午11:14:26
 */
public class ArriveUnSendApproveAction {

  /**
   * 方法功能描述：到货单的收回对应的Action
   * <p>
   * <b>参数说明</b>
   * 
   * @param voArray
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 上午11:14:57
   */
  public ArriveVO[] unSendArrive(ArriveVO[] voArray, AbstractCompiler2 script) {
    PfParameterUtil<ArriveVO> util =
        new PfParameterUtil<ArriveVO>(script == null ? null
            : script.getPfParameterVO(), voArray);
    ArriveVO[] originBills = util.getOrginBills();
    ArriveVO[] clientBills = util.getClientFullInfoBill();
    // 添加规则
    AroundProcesser<ArriveVO> processer =
        new AroundProcesser<ArriveVO>(
            ArriveActionPlugInPoint.ArriveUnSendAction);
    this.addBeforeRule(processer);
    this.addAfterRule(processer);
    processer.before(clientBills);

    // 调用平台脚本进行审批
    if (null != script) {
      try {
        script.procRecallFlow(script.getPfParameterVO());
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }
    if (script == null || script.getPfParameterVO().m_preValueVos == null) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
              "04004040-0117")/* @res "到货单收回时，出现单据数组为空！" */;
      ExceptionUtils.wrappBusinessException(msg);
      return null;
    }
    for (ArriveVO vo : clientBills) {
      vo.getParentVO().setStatus(VOStatus.UPDATED);
    }
    BillUpdate<ArriveVO> update = new BillUpdate<ArriveVO>();
    ArriveVO[] returnvos = update.update(clientBills, originBills);
    processer.after(returnvos);

    return returnvos;
  }

  private void addBeforeRule(AroundProcesser<ArriveVO> processer) {
    // 检查是否可收回
    processer.addBeforeRule(new ChkCanUnSendApproveRule());
    // 到货单收回前事件处理
    processer.addBeforeRule(new UnSendApproveBeforeEventRule());
  }

  private void addAfterRule(AroundProcesser<ArriveVO> processer) {
    // 到货单收回后事件处理
    processer.addAfterRule(new UnSendApproveAfterEventRule());

  }

}
