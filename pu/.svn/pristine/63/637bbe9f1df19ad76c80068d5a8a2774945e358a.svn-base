package nc.impl.pu.m24.action;

import nc.bs.pu.m24.plugin.PricestlPluginPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m24.action.rule.SendAppoveVOValidateRule;
import nc.impl.pu.m24.action.rule.SendApproveFlowCheckRule;
import nc.impl.pu.m24.action.rule.SendApproveStatusChangeRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m24.entity.PricestlVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单的送审对应的Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-13 上午11:14:26
 */
public class PricestlSendAction {

  /**
   * 方法功能描述：价格结算单的送审对应的Action
   * <p>
   * <b>参数说明</b>
   * 
   * @param voArray
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-13 上午11:14:57
   */
  public PricestlVO[] sendapprove(PricestlVO[] vos, AbstractCompiler2 script) {
    PfParameterUtil<PricestlVO> util =
        new PfParameterUtil<PricestlVO>(script.getPfParameterVO(), vos);
    PricestlVO[] originBills = util.getOrginBills();
    PricestlVO[] clientBills = util.getClientFullInfoBill();

    AroundProcesser<PricestlVO> processor =
        new AroundProcesser<PricestlVO>(PricestlPluginPoint.SEND_APPROVE);
    // 添加规则
    this.addRule(processor);
    // 执行持久化前规则
    processor.before(clientBills);
    // 数据持久化
    PricestlVO[] returnVos =
        new BillUpdate<PricestlVO>().update(clientBills, originBills);
    // 执行持久化后规则
    processor.after(returnVos);

    return returnVos;
  }

  /**
   * 方法功能描述：更新前规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 下午01:23:06
   */
  private void addRule(AroundProcesser<PricestlVO> processer) {
    // 添加持久化前规则
    processer.addBeforeFinalRule(new SendApproveFlowCheckRule());
    // 单据状态检查规则
    processer.addBeforeFinalRule(new SendAppoveVOValidateRule());
    // 单据状态修改
    processer.addBeforeFinalRule(new SendApproveStatusChangeRule());

  }
}
