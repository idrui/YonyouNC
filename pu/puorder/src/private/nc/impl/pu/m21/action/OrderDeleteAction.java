/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2009-12-30 ����10:41:47
 */
package nc.impl.pu.m21.action;

import nc.bs.pu.m21.maintain.OrderDeleteBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����ɾ������
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
 * @time 2009-12-30 ����10:41:47
 */
public class OrderDeleteAction {

  /**
   * ��������������ɾ��ָ���Ķ�����
   * <p>
   * <b>examples:</b>
   * <p>
   * ʹ��ʾ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVos ȫvo
   * @param ctx
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2009-12-30 ����11:01:59
   */
  public void delete(OrderVO[] orderVos, OrderContext ctx) {
    BillTransferTool<OrderVO> tool = new BillTransferTool<OrderVO>(orderVos);
    OrderVO[] orgOrderVos = tool.getOriginBills();
    // ����BP
    new OrderDeleteBP(ctx).delete(orgOrderVos);
  }

}
