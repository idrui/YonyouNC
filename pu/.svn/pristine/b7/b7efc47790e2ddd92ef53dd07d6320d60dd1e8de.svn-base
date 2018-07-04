package nc.impl.pu.m21;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.pu.m21.IOrderPaymentQuery;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class OrderPaymentQueryImpl implements IOrderPaymentQuery {

  @Override
  public OrderPaymentVO[] queryOrderPaymentByIds(String[] ids)
      throws BusinessException {
    VOQuery<OrderPaymentVO> voQuery =
        new VOQuery<OrderPaymentVO>(OrderPaymentVO.class);
    return voQuery.query(ids);
  }

  @Override
  public MapList<String, OrderPaymentVO> queryOrderPaymentByOrderIds(
      String[] ids) throws BusinessException {
    MapList<String, OrderPaymentVO> retMap =
        new MapList<String, OrderPaymentVO>();
    try {
      VOQuery<OrderPaymentVO> query =
          new VOQuery<OrderPaymentVO>(OrderPaymentVO.class);
      SqlBuilder sql = new SqlBuilder();
      sql.append(" and ");
      if (ids.length == 1) {
        sql.append(" pk_order", ids[0]);
      }
      else {
        IDExQueryBuilder idqb = new IDExQueryBuilder("tmp_order_payment_001");
        sql.append(idqb.buildAnotherSQL(" pk_order", ids));
      }
      OrderPaymentVO[] vos = query.query(sql.toString(), null);
      for (OrderPaymentVO ctPaymentVO : vos) {
        retMap.put(ctPaymentVO.getPk_order(), ctPaymentVO);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return retMap;

  }

}
