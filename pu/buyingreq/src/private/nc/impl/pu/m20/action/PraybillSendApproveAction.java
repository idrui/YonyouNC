package nc.impl.pu.m20.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.bs.scmpub.pf.PfParameterUtil;
import nc.impl.pu.m20.rule.ApproveFlowCheckRule;
import nc.impl.pu.m20.rule.PraybillStateChgRule;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.rule.pf.SendApproveStatusChkRule;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单送审操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-26 上午11:27:19
 */
public class PraybillSendApproveAction {
  /**
   * 方法功能描述： 送审。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param script
   * @return <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 上午11:01:49
   */
  public PraybillVO[] sendapprove(PraybillVO[] vos, AbstractCompiler2 script) {
    /*
     * change by wandl 
     * 解决审批流制单人下一个审批人勾选本人自动审批单据不能提交（提示：该单据已经被他人修改，请刷新界面，重做业务）
     * start
     */
  	//BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(vos); // 加锁
    PfParameterUtil<PraybillVO> util =
        new PfParameterUtil<PraybillVO>(script == null ? null
            : script.getPfParameterVO(), vos);
    PraybillVO[] sendVos = util.getClientFullInfoBill();
    PraybillVO[] orgVos = util.getOrginBills();
    /*
     * end
     */
    AroundProcesser<PraybillVO> processer =
        new AroundProcesser<PraybillVO>(null);
    this.addRule(processer);
    processer.before(sendVos);
    PraybillVO[] updatedVos =
        new BillUpdate<PraybillVO>().update(sendVos, orgVos);
    return updatedVos;
  }

  /**
   * 方法功能描述：增加规则。
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 上午11:01:34
   */
  private void addRule(AroundProcesser<PraybillVO> processer) {
    processer.addBeforeFinalRule(new ApproveFlowCheckRule());// 是否可以送审
    processer.addBeforeFinalRule(new SendApproveStatusChkRule<PraybillVO>());
    processer.addBeforeFinalRule(new PraybillStateChgRule()); // 单据自己维护送审状态
  }
}
