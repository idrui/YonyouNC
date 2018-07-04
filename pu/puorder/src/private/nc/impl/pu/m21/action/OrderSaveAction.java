/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-12-28 ����04:26:29
 */
package nc.impl.pu.m21.action;

import nc.bs.pu.m21.maintain.OrderSaveBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���涩��
 * <li>
 * <li>
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
 * @time 2009-12-28 ����04:26:29
 */
public class OrderSaveAction {

  /**
   * �ɹ�������̨����action
   * 
   * @param orderVos ǰ̨ȫvo
   * @param ctx ����������
   * @return ������ȫvo
   */
  public OrderVO[] save(OrderVO[] orderVos, OrderContext ctx) {
    BillTransferTool<OrderVO> tool = new BillTransferTool<OrderVO>(orderVos);
    OrderVO[] originVos = tool.getOriginBills();
    return new OrderSaveBP(ctx).save(orderVos, originVos);
  }

}
