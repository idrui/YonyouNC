package nc.bs.pu.m21.pf.function;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * 订单整单毛利额
 * 
 * @since 6.0
 * @version 2011-4-12 下午03:23:26
 * @author wuxla
 */

public class GrossProfit {
  /**
   * 订单整单毛利额
   * 
   * @param aggVO 订单VO
   * @return 订单整单毛利额
   * @throws BusinessException
   */
  public UFDouble getGrossProfit(AggregatedValueObject aggVO)
      throws BusinessException {
    if (null == aggVO) {
      return null;
    }
    UFDouble[] values = GrossProfitUtil.getGrossProfit((OrderVO) aggVO);
    if (values != null) {
      return values[0];
    }
    return null;
  }
}
