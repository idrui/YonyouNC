/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-9 下午12:33:19
 */
package nc.impl.pu.m20.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.wfengine.definition.WorkflowTypeEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单审批状态检查
 * <li>只有单据状态为 自由，并且 审批人为 空时 ，才能提交
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-26 上午11:30:45
 */
public class ApproveFlowCheckRule implements IRule<PraybillVO> {

  public ApproveFlowCheckRule() {
    // 缺省构造方法
  }

  @Override
  public void process(PraybillVO[] vos) {
    for (PraybillVO vo : vos) {
      PraybillHeaderVO header = vo.getHVO();
      if (!PfServiceScmUtil.isExistWorkflowDefinition(
          header.getVtrantypecode(), header.getPk_org(), header.getBillmaker(),
          WorkflowTypeEnum.Approveflow.getIntValue())) {
        ExceptionUtils.wrappBusinessException(header.getVbillcode()
            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0","04004020-0053")/*@res "单据没有可启动的审批流!"*/);
      }
    }
  }
}