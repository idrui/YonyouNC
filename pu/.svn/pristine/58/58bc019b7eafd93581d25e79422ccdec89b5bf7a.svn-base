package nc.impl.pu.m23.approve.action;

import nc.bs.pu.m23.fa.rule.CanStoreNumRule;
import nc.bs.pu.m23.plugin.ArriveActionPlugInPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m23.approve.rule.ApproveAfterEventRule;
import nc.impl.pu.m23.approve.rule.ApproveBeforeEventRule;
import nc.impl.pu.m23.approve.rule.ChkCanApproveRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.ArriveATPUpdateRule;
import nc.vo.pu.pub.enumeration.PuBusiLogActionCode;
import nc.vo.pu.pub.enumeration.PuBusiLogPathCode;
import nc.vo.pu.pub.rule.busilog.WriteOperateLogRule;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单的审核对应的Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 上午11:14:26
 */
public class ArriveApproveAction {

  /**
   * 方法功能描述：到货单的审核对应的Action
   * <p>
   * <b>参数说明</b>
   * 
   * @param voArray
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 上午11:14:57
   */
  public ArriveVO[] approveArrive(ArriveVO[] voArray, AbstractCompiler2 script) {
    PfParameterUtil<ArriveVO> util =
        new PfParameterUtil<ArriveVO>(script == null ? null
            : script.getPfParameterVO(), voArray);
    ArriveVO[] originBills = util.getOrginBills();

    // 添加规则
    AroundProcesser<ArriveVO> processer =
        new AroundProcesser<ArriveVO>(
            ArriveActionPlugInPoint.ArriveApproveAction);
    this.addBeforeRule(processer);
    this.addAfterRule(processer);
    this.addATPRule(processer);
    // 可用量变更前操作
    new ArriveATPUpdateRule(true).process(voArray);
    processer.before(voArray);
    // 调用平台脚本进行审批
    if (null != script) {
      try {
        script.procFlowBacth(script.getPfParameterVO());
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }
    if (script == null || script.getPfParameterVO().m_preValueVos == null) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
              "04004040-0115")/* @res "到货单审批时，出现单据数组为空！" */;
      ExceptionUtils.wrappBusinessException(msg);
      return null;
    }
    BillUpdate<ArriveVO> update = new BillUpdate<ArriveVO>();
    ArriveVO[] returnVos = update.update(voArray, originBills);

    processer.after(returnVos);

    return returnVos;
  }

  private void addAfterRule(AroundProcesser<ArriveVO> processer) {
    // 写业务日志
    processer.addAfterRule(new WriteOperateLogRule<ArriveVO>(
        PuBusiLogPathCode.puarrivalApprovePath.getCode(),
        PuBusiLogActionCode.approve.getCode()));
    // 触发审批后事件
    processer.addAfterRule(new ApproveAfterEventRule());
  }

  private void addATPRule(AroundProcesser<ArriveVO> processer) {
    // 可用量变更后操作
    processer.addAfterRule(new ArriveATPUpdateRule(false));
  }

  private void addBeforeRule(AroundProcesser<ArriveVO> processer) {
    // 检查是否可审核
    processer.addBeforeRule(new ChkCanApproveRule());
    // 触发审批前事件
    // 之前是放在计算可入库数量后的，但如果是到货单，计算可入库数量可能会返回空，
    // 而且在计算可入库数量规则中不太好判断，因为有可能红蓝混合，为了不让它影响审批前事件触发，将此规则前提了一位
    processer.addBeforeRule(new ApproveBeforeEventRule());
    // 计算可入库数量
    processer.addBeforeRule(new CanStoreNumRule());

  }

}
