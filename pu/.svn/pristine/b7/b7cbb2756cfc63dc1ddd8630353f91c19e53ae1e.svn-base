package nc.impl.pu.m23.approve.action;

import nc.bs.pu.m23.plugin.ArriveActionPlugInPoint;
import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m23.approve.rule.ChkCanSendRule;
import nc.impl.pu.m23.approve.rule.SendApproveAfterEventRule;
import nc.impl.pu.m23.approve.rule.SendApproveBeforeEventRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.VOStatus;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单的送审对应的Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 上午11:14:26
 */
public class ArriveSendAction {

  /**
   * 方法功能描述：到货单的送审对应的Action
   * <p>
   * <b>参数说明</b>
   * 
   * @param voArray
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 上午11:14:57
   */
  public ArriveVO[] sendArrive(ArriveVO[] voArray, AbstractCompiler2 script) {
    PfParameterUtil<ArriveVO> util =
        new PfParameterUtil<ArriveVO>(script == null ? null
            : script.getPfParameterVO(), voArray);
    ArriveVO[] originBills = util.getOrginBills();
    ArriveVO[] clientBills = util.getClientFullInfoBill();

    // 添加规则
    AroundProcesser<ArriveVO> processer =
        new AroundProcesser<ArriveVO>(ArriveActionPlugInPoint.ArriveSendAction);
    this.addBeforeRule(processer);
    this.addAfterRule(processer);
    processer.before(clientBills);

    // 单据自己维护送审状态
    for (ArriveVO vo : clientBills) {
      vo.getHVO().setFbillstatus(POEnumBillStatus.APPROVING.toInteger());
      vo.getHVO().setStatus(VOStatus.UPDATED);
    }
    ArriveVO[] updatedVos =
        new BillUpdate<ArriveVO>().update(clientBills, originBills);
    processer.after(updatedVos);
    return updatedVos;
  }

  private void addBeforeRule(AroundProcesser<ArriveVO> processer) {

    // 检查是否可送审
    processer.addBeforeRule(new ChkCanSendRule());
    // 到货单送审前事件
    processer.addBeforeRule(new SendApproveBeforeEventRule());
  }

  private void addAfterRule(AroundProcesser<ArriveVO> processer) {
    // 到货单送审后事件
    processer.addAfterRule(new SendApproveAfterEventRule());

  }
}
