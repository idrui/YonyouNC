/**
 * $文件说明$
 *
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-25 下午06:45:59
 */
package nc.bs.pu.m20.maintain.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              请购单是否可审批检查
 * @scene
 *        请购单审批
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午8:50:35
 * @author yanxm5
 */
public class CheckApproveRule implements IRule<PraybillVO> {

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
      // 状态是自由或审批中才能进行审批
      if (vo.getHVO().getFbillstatus().intValue() != POEnumBillStatus.FREE
          .toInt()
          && vo.getHVO().getFbillstatus().intValue() != POEnumBillStatus.COMMIT
              .toInt()
          && vo.getHVO().getFbillstatus().intValue() != POEnumBillStatus.APPROVING
              .toInt()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0024")/*
                                                                     * @res
                                                                     * "单据状态不正确，不能审批！"
                                                                     */);
      }
    }
  }
}
