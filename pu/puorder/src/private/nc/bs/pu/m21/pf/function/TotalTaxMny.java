/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-20 下午04:31:43
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
 * <li>订单整单本币价税合计
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-20 下午04:31:43
 */
public class TotalTaxMny {

  /**
   * 方法功能描述：返回VO整单价税合计
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-20 下午04:33:02
   */
  public UFDouble getTotalTaxMny(AggregatedValueObject vo)
      throws BusinessException {
    if (null == vo) {
      return UFDouble.ZERO_DBL;
    }

    OrderVO orderVO = (OrderVO) vo;
    OrderItemVO[] itemVOs = orderVO.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return UFDouble.ZERO_DBL;
    }

    UFDouble totalTaxMny = UFDouble.ZERO_DBL;
    OrderFunctionUtil ofutil = OrderFunctionUtil.getInstance();

    for (OrderItemVO itemVO : itemVOs) {
      if (ofutil.isDeleteOrRevise(itemVO)) {
        continue;
      }

      totalTaxMny = MathTool.add(totalTaxMny, itemVO.getNtaxmny());
    }

    return totalTaxMny;
  }
}
