/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-11 ����09:06:09
 */
package nc.ui.pu.m21.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m21.editor.card.beforeedit.body.AssistUnit;
import nc.ui.pu.m21.editor.card.beforeedit.body.BatchCode;
import nc.ui.pu.m21.editor.card.beforeedit.body.Blargess;
import nc.ui.pu.m21.editor.card.beforeedit.body.ChangeRate;
import nc.ui.pu.m21.editor.card.beforeedit.body.Ctaxcodeid;
import nc.ui.pu.m21.editor.card.beforeedit.body.DiscountRate;
import nc.ui.pu.m21.editor.card.beforeedit.body.Material;
import nc.ui.pu.m21.editor.card.beforeedit.body.ReceiveWarehouse;
import nc.ui.pu.m21.editor.card.beforeedit.body.RequestDepartment;
import nc.ui.pu.pub.editor.card.beforeedit.Casscustid;
import nc.ui.pu.pub.editor.card.beforeedit.GlobalExchangeRate;
import nc.ui.pu.pub.editor.card.beforeedit.GroupExchangeRate;
import nc.ui.pu.pub.editor.card.beforeedit.ProjectTaskId;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ������޶���Ƭ�������༭ǰ�¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-11 ����09:06:09
 */
public class ReviseCardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    // �ջ��ֿ�ı༭ǰ�¼�
    listenerMap.put(OrderItemVO.PK_RECVSTORDOC, new ReceiveWarehouse());
    listenerMap.put(OrderItemVO.PK_REQDEPT_V, new RequestDepartment());
    listenerMap.put(OrderItemVO.BLARGESS, new Blargess());
    // ����
    listenerMap.put(OrderItemVO.PK_MATERIAL, new Material());
    // ҵ��λ�ı༭ǰ�¼�
    listenerMap.put(OrderItemVO.CASTUNITID, new AssistUnit());
    // ���۵�λ�ı༭ǰ�¼�����ҵ��λ��ͬ��
    listenerMap.put(OrderItemVO.CQTUNITID, new AssistUnit());
    // ������
    listenerMap.put(OrderItemVO.VCHANGERATE, new ChangeRate(
        OrderItemVO.VCHANGERATE));
    // ���۵�λ������
    listenerMap.put(OrderItemVO.VQTUNITRATE, new ChangeRate(
        OrderItemVO.VQTUNITRATE));
    // ���κ�
    listenerMap.put(OrderItemVO.VBATCHCODE, new BatchCode());
    listenerMap.put(OrderItemVO.NGLOBALEXCHGRATE, new GlobalExchangeRate());// ȫ�ֱ�λ�һ���
    listenerMap.put(OrderItemVO.NGROUPEXCHGRATE, new GroupExchangeRate());// ���ű�λ�һ���
    // ��Ŀ����
    listenerMap.put(PraybillItemVO.CPROJECTTASKID, new ProjectTaskId());
    // �ͻ�
    listenerMap.put(OrderItemVO.CASSCUSTID, new Casscustid());
    // ˰��
    listenerMap.put(OrderItemVO.CTAXCODEID, new Ctaxcodeid());
    // �ۿ�
    listenerMap.put(OrderItemVO.NITEMDISCOUNTRATE, new DiscountRate());
  }
}
