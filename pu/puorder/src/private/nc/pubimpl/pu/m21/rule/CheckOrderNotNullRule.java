/**
 * $文件说明$
 *
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-19 上午09:50:21
 */
package nc.pubimpl.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>参数正确性检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-19 上午09:50:21
 */
public class CheckOrderNotNullRule implements IRule<OrderVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0176")/*
                                                                   * @res
                                                                   * "传入的参数为空"
                                                                   */);
    }

    for (OrderVO vo : vos) {
      if (null == vo) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0177")/*
                                                                     * @res
                                                                     * "传入的采购订单数组存在空值"
                                                                     */);
      }

      if (vo != null
          && (null == vo.getHVO() || ArrayUtils.isEmpty(vo.getBVO()))) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0178")/*
                                                                     * @res
                                                                     * "传入的采购订单数组存在不完整的单据，请检查"
                                                                     */);
      }
    }
  }
}
