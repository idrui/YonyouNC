package nc.bs.pu.m4t.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 *期初暂估单删除状态检查：只有自由状态的才能删除
 * @scene
 * 期初暂估单删除
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-9-8 上午10:39:13
 * @author wuxla
 */
public class InitialEstDelStatusChkRule implements IRule<InitialEstVO> {

  /**
   * 父类方法重写,用于检查期初暂估单的状态是否为自由态
   */
  @Override
  public void process(InitialEstVO[] vos) {
    for (InitialEstVO vo : vos) {
      if (InitialEstStatus.APPROVED.value().equals(
          vo.getHeader().getFbillstatus())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0088")/*@res "单据状态不正确，不能删除！"*/);
      }
    }
  }

}