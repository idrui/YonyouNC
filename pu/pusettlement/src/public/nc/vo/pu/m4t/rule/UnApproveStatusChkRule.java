package nc.vo.pu.m4t.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 *期初暂估单状态检查：状态必须是审批态
 * @scene
 * 期初暂估单取消审批
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-9-8 上午09:25:43
 * @author wuxla
 */
 
public class UnApproveStatusChkRule implements IRule<InitialEstVO> {

  /**
   * 父类方法重写,状态是自由态的单据不能取消审批
   */
  @Override
  public void process(InitialEstVO[] vos) {
    for (InitialEstVO vo : vos) {
      // 状态是自由态的单据不能取消审批
      if (InitialEstStatus.FEE.value().equals(vo.getHeader().getFbillstatus())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0193")/*@res "单据状态不正确，不能操作！"*/);
      }
    }
  }
}