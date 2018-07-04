
package nc.bs.pu.m4t.maintain.rule.maintain;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 状态检查:审批通过的状态不能修改
 * @scene
 * 期初暂估单保存
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-31 上午10:07:36
 * @author guoyk
 */

public class InitialEstBillStatusChkRule implements IRule<InitialEstVO> {

  /**
   * 父类方法重写
   *
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(InitialEstVO[] vos) {
    for (InitialEstVO vo : vos) {
      if (InitialEstStatus.APPROVED.value().equals(
          vo.getHeader().getFbillstatus())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0089")/*@res "单据已经审核，不能修改"*/);
      }
    }
  }
}