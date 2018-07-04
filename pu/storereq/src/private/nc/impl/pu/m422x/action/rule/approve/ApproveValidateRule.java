/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-26 上午07:01:07
 */
package nc.impl.pu.m422x.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.enumeration.EnumBillStatus;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 *            物资需求申请单审批时，判断单据状态是否正确
 * @scene
 *      物资需求申请单审批
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-7-26 上午07:01:07
 * @author wuxla
 */
public class ApproveValidateRule implements IRule<StoreReqAppVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(StoreReqAppVO[] vos) {
    this.statusCheck(vos);
  }

  private void statusCheck(StoreReqAppVO[] vos) {
    for (StoreReqAppVO vo : vos) {
      // 状态是自由或审批中才能进行审批
      if (POEnumBillStatus.APPROVE.toInt() == vo.getHVO().getFbillstatus()
          .intValue()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004010_0", "04004010-0009")/*
                                                                     * @res
                                                                     * "单据已经审批，不能再审批！"
                                                                     */);
      }
      if (EnumBillStatus.CLOSE.toInt() == vo.getHVO().getFbillstatus()
          .intValue()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0158")/*
                                                                     * @res
                                                                     * "单据已最终关闭，不能操作！\n"
                                                                     */);
      }
    }
  }
}
