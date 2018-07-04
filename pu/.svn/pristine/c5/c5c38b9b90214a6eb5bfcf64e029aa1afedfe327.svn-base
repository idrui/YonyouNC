
package nc.vo.pu.m4t.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/**
 * 
 * @description
 *期初暂估单状态检查：必须是非审批态
 * @scene
 * 期初暂估单审批
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-9-8 上午09:11:00
 * @author wuxla
 */


public class ApproveStatusChkRule implements IRule<InitialEstVO> {

	/**
	 * 父类方法重写,用于期初暂估单状态检查，状态是自由才能进行审批
	 */
  @Override
  public void process(InitialEstVO[] vos) {
    for (InitialEstVO vo : vos) {
      // 状态是自由才能进行审批
      if (InitialEstStatus.APPROVED.value().equals(
          vo.getHeader().getFbillstatus())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0171")/*@res "单据状态不正确，不能审批！"*/);
      }
    }
  }

}