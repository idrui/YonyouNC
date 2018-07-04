/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-12-28 ����04:24:33
 */
package nc.impl.pu.m21;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m21.maintain.OrderFreezeBP;
import nc.bs.pu.m21.maintain.OrderUnfreezeBP;
import nc.impl.pu.m21.action.OrderDeleteAction;
import nc.impl.pu.m21.action.OrderSaveAction;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.pu.m21.IOrderMaintain;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2009-12-28 ����04:24:33
 */
public class OrderMaintainImpl implements IOrderMaintain {

  @Override
  public void delete(OrderVO[] orderVos, OrderContext[] ctxs)
      throws BusinessException {
    try {
      BillTransferTool<OrderVO> tool = new BillTransferTool<OrderVO>(orderVos);
      OrderVO[] clientVos = tool.getClientFullInfoBill();
      // ҵ����־aop����
      // nc.itf.pu.m21.IOrderMaintain.deleteByFullVOs(OrderVO[], OrderContext[])
      NCLocator.getInstance().lookup(IOrderMaintain.class)
          .deleteByFullVOs(clientVos, ctxs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

  @Override
  public void deleteByFullVOs(OrderVO[] orderVos, OrderContext[] ctxs)
      throws BusinessException {
    OrderContext ctx = ArrayUtils.isEmpty(ctxs) ? new OrderContext() : ctxs[0];
    try {
      new OrderDeleteAction().delete(orderVos, ctx);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

  @Override
  public OrderVO[] freezeOrder(OrderVO[] orderVos, String freezeReason)
      throws BusinessException {
    try {

      BillTransferTool<OrderVO> tool = new BillTransferTool<OrderVO>(orderVos);
      OrderVO[] clientVos = tool.getClientFullInfoBill();
      // ҵ����־aop����
      // nc.impl.pu.m21.OrderMaintainImpl.freezeOrderByFullVOs(OrderVO[],
      // String)
      OrderVO[] returnVos =
          NCLocator.getInstance().lookup(IOrderMaintain.class)
              .freezeOrderByFullVOs(clientVos, freezeReason);
      return tool.getBillForToClient(returnVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] freezeOrderByFullVOs(OrderVO[] orderVos, String freezeReason)
      throws BusinessException {
    try {
      return new OrderFreezeBP().freeze(orderVos, freezeReason);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] insert(OrderVO[] orderClientVos, OrderContext ctx)
      throws BusinessException {
    return new OrderSaveAction().save(orderClientVos, ctx);
  }

  @Override
  public OrderVO[] save(OrderVO[] orderClientVos, OrderContext ctx)
      throws BusinessException {
    try {
      OrderContext context = ctx;
      if (ctx == null) {
        context = new OrderContext();
      }
      // ��������
      if (orderClientVos[0].getHVO().getStatus() == VOStatus.NEW) {
        return NCLocator.getInstance().lookup(IOrderMaintain.class)
            .insert(orderClientVos, context);
      }
      // �޸ı���
      return NCLocator.getInstance().lookup(IOrderMaintain.class)
          .update(orderClientVos, context);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] unfreezeOrder(OrderVO[] orderVos) throws BusinessException {
    try {
      BillTransferTool<OrderVO> tool = new BillTransferTool<OrderVO>(orderVos);
      OrderVO[] clientVos = tool.getClientFullInfoBill();
      // ҵ����־aop����nc.impl.pu.m21.OrderMaintainImpl.unfreezeOrderByFullVOs(OrderVO[])
      OrderVO[] returnVos =
          NCLocator.getInstance().lookup(IOrderMaintain.class)
              .unfreezeOrderByFullVOs(clientVos);
      return tool.getBillForToClient(returnVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] unfreezeOrderByFullVOs(OrderVO[] orderVos)
      throws BusinessException {
    try {
      return new OrderUnfreezeBP().unfreeze(orderVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public OrderVO[] update(OrderVO[] orderClientVos, OrderContext ctx)
      throws BusinessException {
    return new OrderSaveAction().save(orderClientVos, ctx);
  }

}
