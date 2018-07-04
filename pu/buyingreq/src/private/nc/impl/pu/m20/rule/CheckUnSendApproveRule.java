package nc.impl.pu.m20.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * @description
 *              请购单是否可收回检查
 * @scene
 *        请购单收回
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午10:41:32
 * @author yanxm5
 */
public class CheckUnSendApproveRule implements IRule<PraybillVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    // 单据状态合法性检查
    this.billStatusCheck(vos);
  }

  private void billStatusCheck(PraybillVO[] vos) {
    for (PraybillVO vo : vos) {
      // 根据公共需求2011.6.25
      // 审批中状态、审批人为空的单据可以收回
      if (vo.getHVO().getFbillstatus().intValue() != POEnumBillStatus.APPROVING
          .toInt() || StringUtils.isNotBlank(vo.getHVO().getApprover())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0054")/*
                                                                     * @res
                                                                     * "单据状态不正确，不能收回！"
                                                                     */);
      }
    }
  }
}
