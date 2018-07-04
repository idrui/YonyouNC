package nc.impl.pu.m23.approve.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.wfengine.definition.WorkflowTypeEnum;

/**
 * 
 * @description <ul>
 *              <li>检查是否可以送审
 *              <li>如果单据已经送审，不能送审
 *              <li>如果单据已经审核完成，不能送审
 *              <li>如果单据无审批流，不能送审
 *              </ul>
 * @scene
 * 到货单送审
 * @param 无
 * 
 * @since 6.3
 * @version 2010-1-19 上午09:34:01
 * @author hanbin
 */
public class ChkCanSendRule implements IRule<ArriveVO> {

  public ChkCanSendRule() {
    return;
  }

  @Override
  public void process(ArriveVO[] voArray) {
    for (ArriveVO aggVO : voArray) {
      this.chkCanSave(aggVO);
    }
  }

  /*
   * 检查是否可以送审
   */
  private void chkCanSave(ArriveVO aggVO) {
    String userid = AppContext.getInstance().getPkUser();
    ArriveHeaderVO header = aggVO.getHVO();
    if(!header.getBillmaker().equals(userid)){
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
      .getNCLangRes().getStrByID("4004000_0", "04004000-0158")/*
                                                               * @res
                                                               * "当前用户不是制单人，不允许提交！"
                                                               */);
    }
    Integer fbillstatus = aggVO.getHVO().getFbillstatus();
    if (POEnumBillStatus.APPROVING.value().equals(fbillstatus)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0119")/*@res "到货单已送审，不允许送审！"*/);
    }

    if (POEnumBillStatus.APPROVE.value().equals(fbillstatus)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0120")/*@res "到货单已审核，不允许送审！"*/);
    }

    // 没有配置审批流，不允许送审
    String trancode = header.getVtrantypecode();
    String org = header.getPk_org();
    if (!PfServiceScmUtil.isExistWorkflowDefinition(trancode, org,
        header.getBillmaker(), WorkflowTypeEnum.Approveflow.getIntValue())) {
      ExceptionUtils.wrappBusinessException(header.getVbillcode()
          + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0121")/*@res "单据没有可启动的审批流!"*/);
    }
  }
}