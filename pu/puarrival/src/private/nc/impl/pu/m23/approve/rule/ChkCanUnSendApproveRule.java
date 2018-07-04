package nc.impl.pu.m23.approve.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 * 检查是否可以收回
 * @scene
 * 到货单收回
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-5-13 上午11:51:38
 * @author hanbin
 */

public class ChkCanUnSendApproveRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] voArray) {
    for (ArriveVO aggVO : voArray) {
      // 检查是否可收回
      this.chkCanApprove(aggVO);
    }
  }

  private void chkCanApprove(ArriveVO aggVO) {
    // 根据公共需求2011.6.25
    // 审批中状态、审批人为空的单据可以收回
    if (!POEnumBillStatus.APPROVING.value().equals(
        aggVO.getHVO().getFbillstatus())
        || StringUtils.isNotBlank(aggVO.getHVO().getApprover())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0129")/*@res "到货单不是审批中状态，不允许收回！"*/);
    }
  }

}