package nc.impl.pu.m24.action;

import nc.bs.pu.m24.plugin.PricestlPluginPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m24.action.rule.ApproveVOValidateRule;
import nc.impl.pu.m24.action.rule.PriceChkRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单的审核对应的Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-13 上午11:14:26
 */
public class PricestlApproveAction {

  /**
   * 方法功能描述：价格结算单的审核对应的Action
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-7-30 上午11:14:57
   */
  public PricestlVO[] approve(PricestlVO[] vos, AbstractCompiler2 script) {
    PfParameterUtil<PricestlVO> util =
        new PfParameterUtil<PricestlVO>(script == null ? null
            : script.getPfParameterVO(), vos);
    PricestlVO[] originBills = util.getOrginBills();
    PricestlVO[] clientBills = util.getClientFullInfoBill();
    AroundProcesser<PricestlVO> processer =
        new AroundProcesser<PricestlVO>(PricestlPluginPoint.APPROVE);
    this.addBeforeRule(processer);

    // 前规则
    processer.before(clientBills);

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
    BillUpdate<PricestlVO> update = new BillUpdate<PricestlVO>();
    PricestlVO[] returnVos = update.update(clientBills, originBills);

    // 后规则
    processer.after(returnVos);
    return returnVos;
  }

  private void addBeforeRule(AroundProcesser<PricestlVO> processer) {
    processer.addBeforeRule(new ApproveVOValidateRule()); // 审核VO状态检查
    processer.addBeforeRule(new PriceChkRule()); // 审核时价格检查

  }

}
