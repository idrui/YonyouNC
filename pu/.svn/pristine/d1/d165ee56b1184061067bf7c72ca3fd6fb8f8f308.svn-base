package nc.pubimpl.pu.m21.pub;

import nc.impl.pu.m21.action.OrderArriveCloseAction;
import nc.impl.pu.m21.action.OrderFinalCloseAction;
import nc.impl.pu.m21.action.OrderInvoiceCloseAction;
import nc.impl.pu.m21.action.OrderPayCloseAction;
import nc.impl.pu.m21.action.OrderRowCloseAction;
import nc.impl.pu.m21.action.OrderStoreCloseAction;
import nc.pubitf.pu.m21.pub.IOrderClosePubService;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderVO;

public class OrderClosePubServiceImpl implements IOrderClosePubService {

  @Override
  public OrderVO[] arriveClose(OrderVO[] vos) {
    OrderCloseVO[] closevos = OrderVO.getCloseVO(vos);
    OrderCloseVO[] returnVos = new OrderArriveCloseAction().close(closevos);
    return OrderCloseVO.getOrderVO(returnVos);
  }

  @Override
  public OrderVO[] arriveOpen(OrderVO[] vos) {
    OrderCloseVO[] closevos = OrderVO.getCloseVO(vos);
    OrderCloseVO[] returnVos = new OrderArriveCloseAction().open(closevos);
    return OrderCloseVO.getOrderVO(returnVos);

  }

  @Override
  public OrderVO[] finalClose(OrderVO[] vos) {
    OrderCloseVO[] closevos = OrderVO.getCloseVO(vos);
    OrderCloseVO[] returnVos = new OrderFinalCloseAction().close(closevos);
    return OrderCloseVO.getOrderVO(returnVos);
  }

  @Override
  public OrderVO[] finalOpen(OrderVO[] vos) {
    OrderCloseVO[] closevos = OrderVO.getCloseVO(vos);
    OrderCloseVO[] returnVos = new OrderFinalCloseAction().open(closevos);
    return OrderCloseVO.getOrderVO(returnVos);
  }

  @Override
  public OrderVO[] invoiceClose(OrderVO[] vos) {
    OrderCloseVO[] closevos = OrderVO.getCloseVO(vos);
    OrderCloseVO[] returnVos = new OrderInvoiceCloseAction().close(closevos);
    return OrderCloseVO.getOrderVO(returnVos);
  }

  @Override
  public OrderVO[] invoiceOpen(OrderVO[] vos) {
    OrderCloseVO[] closevos = OrderVO.getCloseVO(vos);
    OrderCloseVO[] returnVos = new OrderInvoiceCloseAction().open(closevos);
    return OrderCloseVO.getOrderVO(returnVos);
  }

  @Override
  public OrderVO[] payClose(OrderVO[] vos) {
    OrderCloseVO[] closevos = OrderVO.getCloseVO(vos);
    OrderCloseVO[] returnVos = new OrderPayCloseAction().close(closevos);
    return OrderCloseVO.getOrderVO(returnVos);
  }

  @Override
  public OrderVO[] payOpen(OrderVO[] vos) {
    OrderCloseVO[] closevos = OrderVO.getCloseVO(vos);
    OrderCloseVO[] returnVos = new OrderPayCloseAction().open(closevos);
    return OrderCloseVO.getOrderVO(returnVos);
  }

  @Override
  public OrderVO[] rowClose(OrderVO[] vos) {
    OrderCloseVO[] closevos = OrderVO.getCloseVO(vos);
    OrderCloseVO[] returnVos = new OrderRowCloseAction().close(closevos);
    return OrderCloseVO.getOrderVO(returnVos);
  }

  @Override
  public OrderVO[] rowOpen(OrderVO[] vos) {
    OrderCloseVO[] closevos = OrderVO.getCloseVO(vos);
    OrderCloseVO[] returnVos = new OrderRowCloseAction().open(closevos);
    return OrderCloseVO.getOrderVO(returnVos);
  }

  @Override
  public OrderVO[] storeClose(OrderVO[] vos) {
    OrderCloseVO[] closevos = OrderVO.getCloseVO(vos);
    OrderCloseVO[] returnVos = new OrderStoreCloseAction().close(closevos);
    return OrderCloseVO.getOrderVO(returnVos);
  }

  @Override
  public OrderVO[] storeOpen(OrderVO[] vos) {
    OrderCloseVO[] closevos = OrderVO.getCloseVO(vos);
    OrderCloseVO[] returnVos = new OrderStoreCloseAction().open(closevos);
    return OrderCloseVO.getOrderVO(returnVos);
  }

}
