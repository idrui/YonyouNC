package nc.ui.pu.m21.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m21.editor.card.beforeedit.body.Arrvstoorg;
import nc.ui.pu.m21.editor.card.beforeedit.body.AssistUnit;
import nc.ui.pu.m21.editor.card.beforeedit.body.BatchCode;
import nc.ui.pu.m21.editor.card.beforeedit.body.Blargess;
import nc.ui.pu.m21.editor.card.beforeedit.body.CalTaxMny;
import nc.ui.pu.m21.editor.card.beforeedit.body.Ccontractid;
import nc.ui.pu.m21.editor.card.beforeedit.body.Cffileid;
import nc.ui.pu.m21.editor.card.beforeedit.body.ChangeRate;
import nc.ui.pu.m21.editor.card.beforeedit.body.Cqpbaseschemeid;
import nc.ui.pu.m21.editor.card.beforeedit.body.Ctaxcodeid;
import nc.ui.pu.m21.editor.card.beforeedit.body.DestArea;
import nc.ui.pu.m21.editor.card.beforeedit.body.DiscountRate;
import nc.ui.pu.m21.editor.card.beforeedit.body.Effectaddmonth;
import nc.ui.pu.m21.editor.card.beforeedit.body.Effectmonth;
import nc.ui.pu.m21.editor.card.beforeedit.body.Flowstockorg;
import nc.ui.pu.m21.editor.card.beforeedit.body.Material;
import nc.ui.pu.m21.editor.card.beforeedit.body.Nexchangerate;
import nc.ui.pu.m21.editor.card.beforeedit.body.OrigArea;
import nc.ui.pu.m21.editor.card.beforeedit.body.Psfinanceorg;
import nc.ui.pu.m21.editor.card.beforeedit.body.ReceiveAddress;
import nc.ui.pu.m21.editor.card.beforeedit.body.ReceiveWarehouse;
import nc.ui.pu.m21.editor.card.beforeedit.body.Reqstoorg;
import nc.ui.pu.m21.editor.card.beforeedit.body.RequestDepartment;
import nc.ui.pu.m21.editor.card.beforeedit.body.RequestWarehouse;
import nc.ui.pu.m21.editor.card.beforeedit.body.Venddevaddr;
import nc.ui.pu.pub.editor.card.beforeedit.Casscustid;
import nc.ui.pu.pub.editor.card.beforeedit.GlobalExchangeRate;
import nc.ui.pu.pub.editor.card.beforeedit.GroupExchangeRate;
import nc.ui.pu.pub.editor.card.beforeedit.ProjectTaskId;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ�������Ƭ�������༭ǰ�¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-2-25 ����09:18:01
 */
public class CardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    // ҵ��λ�ı༭ǰ�¼�
    listenerMap.put(OrderItemVO.CASTUNITID, new AssistUnit());
    // ���۵�λ�ı༭ǰ�¼�����ҵ��λ��ͬ��
    listenerMap.put(OrderItemVO.CQTUNITID, new AssistUnit());
    // ����ֿ�ı༭ǰ�¼�
    listenerMap.put(OrderItemVO.PK_REQSTORDOC, new RequestWarehouse());
    // �ջ��ֿ�ı༭ǰ�¼�
    listenerMap.put(OrderItemVO.PK_RECVSTORDOC, new ReceiveWarehouse());
    // �����ŵı༭ǰ�¼�
    listenerMap.put(OrderItemVO.PK_REQDEPT_V, new RequestDepartment());
    // ��ͬ
    listenerMap.put(OrderItemVO.CCONTRACTID, new Ccontractid());
    // ����
    listenerMap.put(OrderItemVO.PK_MATERIAL, new Material());
    // ��������֯
    listenerMap.put(OrderItemVO.PK_REQSTOORG_V, new Reqstoorg());
    // �ջ������֯
    listenerMap.put(OrderItemVO.PK_ARRVSTOORG_V, new Arrvstoorg());
    // ���������֯
    listenerMap.put(OrderItemVO.PK_PSFINANCEORG_V, new Psfinanceorg());
    // ������
    listenerMap.put(OrderItemVO.VCHANGERATE, new ChangeRate(
        OrderItemVO.VCHANGERATE));
    // ���۵�λ������
    listenerMap.put(OrderItemVO.VQTUNITRATE, new ChangeRate(
        OrderItemVO.VQTUNITRATE));
    // ���κ�
    listenerMap.put(OrderItemVO.VBATCHCODE, new BatchCode());
    listenerMap.put(OrderItemVO.CQPBASESCHEMEID, new Cqpbaseschemeid());
    listenerMap.put(OrderItemVO.PK_FLOWSTOCKORG_V, new Flowstockorg());
    listenerMap.put(OrderItemVO.NGLOBALEXCHGRATE, new GlobalExchangeRate());// ȫ�ֱ�λ�һ���
    listenerMap.put(OrderItemVO.NGROUPEXCHGRATE, new GroupExchangeRate());// ���ű�λ�һ���
    // �Ƿ���Ʒ
    listenerMap.put(OrderItemVO.BLARGESS, new Blargess());
    // ��Ŀ����
    listenerMap.put(OrderItemVO.CPROJECTTASKID, new ProjectTaskId());
    // �ͻ�
    listenerMap.put(OrderItemVO.CASSCUSTID, new Casscustid());
    // ��˰���
    listenerMap.put(OrderItemVO.NCALTAXMNY, new CalTaxMny());
    // ˰��
    listenerMap.put(OrderItemVO.CTAXCODEID, new Ctaxcodeid());
    // Ŀ�ĵ���
    listenerMap.put(OrderItemVO.CDESTIAREAID, new DestArea());
    // ԭ������
    listenerMap.put(OrderItemVO.CORIGAREAID, new OrigArea());
    // �ۿ�
    listenerMap.put(OrderItemVO.NITEMDISCOUNTRATE, new DiscountRate());
    // ��Ӧ�̷�����ַ
    listenerMap.put(OrderItemVO.VVENDDEVADDR, new Venddevaddr());
    // �ջ���ַ
    listenerMap.put(OrderItemVO.PK_RECEIVEADDRESS, new ReceiveAddress());
    // �۱�����
    listenerMap.put(OrderItemVO.NEXCHANGERATE, new Nexchangerate());

    // ����Э��ҳǩ��Ч��
    listenerMap.put(OrderPaymentVO.EFFECTMONTH, new Effectmonth());
    // ����Э��ҳǩ������
    listenerMap.put(OrderPaymentVO.EFFECTADDMONTH, new Effectaddmonth());

//    // ������༭�ֶ�
//    NeverEditBodyItem neverEditItem = new NeverEditBodyItem();
    // ������
    listenerMap.put(OrderItemVO.CFFILEID, new Cffileid());
  }

}
