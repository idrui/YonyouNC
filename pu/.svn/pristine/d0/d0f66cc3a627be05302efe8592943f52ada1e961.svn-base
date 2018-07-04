package nc.pubimpl.pu.m21.et;

import nc.bs.pu.m21.query.pu.OrderQueryForETBP;
import nc.pubitf.pu.m21.et.IOrderQueryForET;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 拣配查询实现类
 * @since 6.31
 * @version 2013-7-31 下午03:22:09
 * @author zhangyhh
 */
public class OrderQueryForETImpl implements IOrderQueryForET {

  @Override
  public OrderVO[] queryForet(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return new OrderQueryForETBP().queryForet(queryScheme);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] queryForet(String org, String material, String warehouse,
      String batchcode, String bid) throws BusinessException {
    try {
      return new OrderQueryForETBP().queryForet(org, material, warehouse,
          batchcode, bid);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
