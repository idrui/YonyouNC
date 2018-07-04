/**
 * $文件说明$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 下午04:25:09
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              请购单直运和委外检查规则
 * @scene
 *        请购单新增、修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午10:02:34
 * @author yanxm5
 */
public class DirecttransitAndSctype implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    if (null == vos || vos.length == 0) {
      return;
    }
    for (PraybillVO vo : vos) {
      PraybillHeaderVO head = vo.getHVO();
      if (null != head.getBsctype() && head.getBsctype().booleanValue()
          && null != head.getBdirecttransit()
          && head.getBdirecttransit().booleanValue()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0027")/*
                                                                     * @res
                                                                     * "直运和委外互斥，不能同时选择"
                                                                     */);
      }
    }
  }
}
