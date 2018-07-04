/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-13 上午08:25:03
 */
package nc.bs.pu.m21.pf.function;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>业务检查函数：MIN(订单表体主数量)
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-13 上午08:25:03
 */
public class MinNum {

  /**
   * 方法功能描述：订单行主数量，订单行主数量=MIN每行订单主数量
   * <p>
   * <b>参数说明</b>
   *
   * @param vo
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-13 上午09:37:26
   */
  public UFDouble getMinNum(AggregatedValueObject vo) throws BusinessException {
    if (null == vo) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0135")/*@res "检查函数传入参数错误!"*/);
    }

    OrderVO orderVO = (OrderVO) vo;
    OrderItemVO[] itemVOs = orderVO.getBVO();

    if (ArrayUtils.isEmpty(itemVOs)) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0135")/*@res "检查函数传入参数错误!"*/);
    }

    UFDouble min = itemVOs[0].getNnum();
    for (int i = 1; i < itemVOs.length; ++i) {
      if ((null == itemVOs[i]) || (null == itemVOs[i].getNnum())) {
        continue;
      }

      if (null == min) {
        min = itemVOs[i].getNnum();
      }
      else if (MathTool.compareTo(min, itemVOs[i].getNnum()) > 0) {
        min = itemVOs[i].getNnum();
      }
    }

    return min;
  }

}