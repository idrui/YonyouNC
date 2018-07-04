package nc.pubimpl.pu.m21.ic.m4c;

import java.util.Map;

import nc.bs.pu.m21.query.ic.OrderQueryFor4CBP;
import nc.pubitf.pu.m21.ic.m4c.IOrderQueryFor4C;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 销售出库单查询实现类
 * 
 * @since 6.0
 * @version 2011-4-27 下午01:13:27
 * @author wuxla
 */

public class OrderQueryFor4CImpl implements IOrderQueryFor4C {

  @Override
  public Map<String, OrderItemVO> queryCoopOrderVO(String[] sobids)
      throws BusinessException {
    if (ArrayUtils.isEmpty(sobids)) {
      return null;
    }
    try {
      return new OrderQueryFor4CBP().queryCoopOrderVO(sobids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
