package nc.pubimpl.pu.m21.ia.mi2;

import java.util.Map;

import nc.bs.pu.m21.query.ia.OrderQuerySOForIABP;
import nc.pubitf.pu.m21.ia.mi2.IOrderQueryForI2;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * @since 6.0
 * @version 2011-4-15 ÏÂÎç02:55:42
 * @author wuxla
 */

public class OrderQueryForI2Impl implements IOrderQueryForI2 {

  @Override
  public Map<String, String> queryDirectSOBidForIA(String[] bids)
      throws BusinessException {
    try {
      return new OrderQuerySOForIABP().queryDirectSOBidForIA(bids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public MapList<String, String> querySOBidAndCtransIdForIA(String[] bids)
      throws BusinessException {
    try {
      return new OrderQuerySOForIABP().querySOBidAndCtransIdForIA(bids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
