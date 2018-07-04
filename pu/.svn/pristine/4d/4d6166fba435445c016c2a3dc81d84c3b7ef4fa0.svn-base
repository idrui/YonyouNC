package nc.impl.pu.m24.action.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m24.entity.PricestlHeaderVO;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.wfengine.definition.WorkflowTypeEnum;

/**
 * @description
 *              价格结算单
 * @scene
 *        价格结算单送审
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:29:22
 * @author luojw
 */
public class SendApproveFlowCheckRule implements IRule<PricestlVO> {
  public SendApproveFlowCheckRule() {
    // 缺省构造方法
  }

  @Override
  public void process(PricestlVO[] vos) {
    for (PricestlVO vo : vos) {
      PricestlHeaderVO header = vo.getHVO();
      if (!PfServiceScmUtil.isExistWorkflowDefinition(
          POBillType.PriceStl.getCode(), header.getPk_org(),
          header.getBillmaker(), WorkflowTypeEnum.Approveflow.getIntValue())) {
        ExceptionUtils.wrappBusinessException(header.getVbillcode()
            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                "4004070_0", "04004070-0003")/* @res "单据没有可启动的审批流!" */);
      }
    }
  }

}
