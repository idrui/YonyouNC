package nc.pubimpl.pu.m21.ic.reserve;

import nc.bs.pu.m21.writeback.ic.OrderWriteBackForReserveBP;
import nc.pubitf.pu.m21.ic.reserve.IOrderWriteBackForReserve;
import nc.pubitf.pu.m21.ic.reserve.IOrderWriteBackPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存预留回写采购订单服务的实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-6-17 下午07:44:08
 */
public class OrderWriteBackForReserveImpl implements IOrderWriteBackForReserve {

  @Override
  public void writeBackForReserve(IOrderWriteBackPara[] para)
      throws BusinessException {
    try {
      OrderWriteBackForReserveBP bp = new OrderWriteBackForReserveBP();
      bp.writeback(para);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
