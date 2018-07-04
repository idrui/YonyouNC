/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-20 下午08:26:27
 */
package nc.bs.pu.m21.pf.function;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderFunctionUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单整单无税本币金额
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-20 下午08:26:27
 */
public class TotalNoTaxmny {

  /**
   * 方法功能描述：根据订单VO返回订单整单无税本币金额.如果结果为零，则返回null
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-20 下午08:28:53
   */
  public UFDouble getNoTaxMny(AggregatedValueObject vo)
      throws BusinessException {
    if (null == vo) {
      return null;
    }

    OrderVO orderVO = (OrderVO) vo;
    OrderItemVO[] itemVOs = orderVO.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return null;
    }

    UFDouble totalNum = UFDouble.ZERO_DBL;
    OrderFunctionUtil ofutil = OrderFunctionUtil.getInstance();
    for (OrderItemVO itemVO : itemVOs) {
      if (ofutil.isDeleteOrRevise(itemVO)) {
        continue;
      }

      totalNum = MathTool.add(totalNum, itemVO.getNmny());
    }

    if (0 == MathTool.compareTo(UFDouble.ZERO_DBL, totalNum)) {
      return null;
    }

    return totalNum;
  }
}
