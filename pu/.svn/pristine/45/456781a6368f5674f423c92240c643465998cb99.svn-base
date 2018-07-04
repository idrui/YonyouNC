/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-26 上午07:17:36
 */
package nc.impl.pu.m422x.action.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 *            物资需求申请单收回时，对单据状态进行检查
 * @scene
 *      物资需求申请单收回
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-7-26 上午07:17:36
 * @author wuxla
 */
public class UnSendApproveValidateRule implements IRule<StoreReqAppVO> {

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
      // 根据公共需求2011.6.25
      // 审批中状态、审批人为空的单据可以收回
      if (!vo.getHVO().getFbillstatus().equals(
          POEnumBillStatus.APPROVING.value())
          || StringUtils.isNotBlank(vo.getHVO().getApprover())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004010_0","04004010-0010")/*@res "单据状态不正确，不能操作！"*/);
      }
    }
  }
}