package nc.ui.pu.m21.service;

import nc.bs.framework.common.NCLocator;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单删除的服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author Administrator
 * @time 2010-4-11 下午09:06:43
 */
public class OrderDeleteService<E extends OrderVO> implements
    ISingleBillService<E> {

  @Override
  public OrderVO operateBill(OrderVO bill) throws Exception {
    // 执行远程调用，进行删除
    nc.itf.pu.m21.IOrderMaintain maintainService =
        NCLocator.getInstance().lookup(nc.itf.pu.m21.IOrderMaintain.class);
    maintainService.delete(new OrderVO[] {
      bill
    }, null);
    return bill;
  }

}
