package nc.bs.pu.m21.pf.function;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 订单整单毛利率
 * 
 * @since 6.0
 * @version 2011-4-12 下午04:54:51
 * @author wuxla
 */

public class GrossProfitRate {
  /**
   * 订单整单毛利率
   * 
   * @param aggVO 订单VO
   * @return 订单整单毛利率
   * @throws BusinessException
   */
  public UFDouble getGrossProfitRate(AggregatedValueObject aggVO)
      throws BusinessException {
    if (null == aggVO) {
      return null;
    }
    UFDouble[] values = GrossProfitUtil.getGrossProfit((OrderVO) aggVO);
    if (null == values) {
      return null;
    }
    if (0 == MathTool.compareTo(UFDouble.ZERO_DBL, values[1])) {
      return UFDouble.ZERO_DBL;
    }
    if (0 == values[0].compareTo(values[1])) {
      return UFDouble.ZERO_DBL;
    }
    return values[0].div(values[1]);
  }
}
