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
 *              �۸���㵥
 * @scene
 *        �۸���㵥����
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����4:29:22
 * @author luojw
 */
public class SendApproveFlowCheckRule implements IRule<PricestlVO> {
  public SendApproveFlowCheckRule() {
    // ȱʡ���췽��
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
                "4004070_0", "04004070-0003")/* @res "����û�п�������������!" */);
      }
    }
  }

}
