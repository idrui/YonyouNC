package nc.impl.pu.m21.action.rule.approve;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.wfengine.definition.WorkflowTypeEnum;

/**
 * @description
 *              采购订单审批流检查（当前操作员是否有可用的审批流）
 * @scene
 *        采购订单送审
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午10:54:42
 * @author luojw
 */
public class SendApproveFlowCheckRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      OrderHeaderVO header = vo.getHVO();
      if (!PfServiceScmUtil.isExistWorkflowDefinition(
          header.getVtrantypecode(), header.getPk_org(), header.getBillmaker(),
          WorkflowTypeEnum.Approveflow.getIntValue())) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0275", null, new String[] {
              header.getVbillcode()
            })/* {0}单据没有可启动的审批流! */);
      }
    }
  }

}
