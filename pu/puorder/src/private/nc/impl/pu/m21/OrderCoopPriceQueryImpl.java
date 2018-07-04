/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-27 下午03:05:09
 */
package nc.impl.pu.m21;

import nc.bs.pu.m21.query.OrderCoopPriceQueryBP;
import nc.itf.pu.m21.IOrderCoopPriceQuery;
import nc.vo.pu.m21.query.price.CoopPriceQueryParam;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单询协同售价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-10-27 下午03:05:09
 */
public class OrderCoopPriceQueryImpl implements IOrderCoopPriceQuery {

  @Override
  public CoopPriceQueryParam queryCoopPrice(CoopPriceQueryParam param)
      throws BusinessException {
    try {
      new OrderCoopPriceQueryBP().queryCoopPrice(param);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return param;
  }

}
