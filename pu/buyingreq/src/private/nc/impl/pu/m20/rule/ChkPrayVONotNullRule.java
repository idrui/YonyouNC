package nc.impl.pu.m20.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              传入的请购单VO 如果请购单、表头、表体其中任意一个为空则抛出业务异常
 * @scene
 *        计划订单推式保存请购单、生产订单推式保存请购单
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午1:42:44
 * @author yanxm5
 */
public class ChkPrayVONotNullRule implements IRule<PraybillVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    for (PraybillVO vo : vos) {
      if (null == vo) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0055")/*
                                                                     * @res
                                                                     * "传入的请购单数组存在空值"
                                                                     */);
        return;
      }
      // 多语
      if (null == vo.getHVO()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0056")/*
                                                                     * @res
                                                                     * "传入的请购单数组存在表头为空的单据，请检查"
                                                                     */);
      }
      if (null == vo.getBVO()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0057")/*
                                                                     * @res
                                                                     * "传入的请购单数组中有的表体为空，请检查"
                                                                     */);
      }
    }
  }
}
