package nc.impl.pu.m24.action.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * @description
 *              价格结算单收回时，单据状态合法性检查
 * @scene
 *        价格结算单收回
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午4:32:16
 * @author luojw
 */
public class UnSendApproveVOValidateRule implements IRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] vos) {
    // 单据状态合法性检查
    this.statusCheck(vos);
  }

  private void statusCheck(PricestlVO[] vos) {
    for (PricestlVO vo : vos) {
      // 根据公共需求2011.6.25
      // 审批中状态、审批人为空的单据可以收回
      if (vo.getHVO().getFbillstatus().intValue() != POEnumBillStatus.APPROVING
          .toInt() || StringUtils.isNotBlank(vo.getHVO().getApprover())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004070_0", "04004070-0005")/*
                                                                     * @res
                                                                     * "单据状态不正确，不能收回！"
                                                                     */);
      }
    }
  }
}
