/**
 * 
 */
package nc.ui.pu.m25.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m25.editor.card.beforeedit.header.AccountBank;
import nc.ui.pu.m25.editor.card.beforeedit.header.BizPerson;
import nc.ui.pu.m25.editor.card.beforeedit.header.Dept;
import nc.ui.pu.m25.editor.card.beforeedit.header.EditableByVAT;
import nc.ui.pu.m25.editor.card.beforeedit.header.ExchangeRate;
import nc.ui.pu.m25.editor.card.beforeedit.header.FreeCust;
import nc.ui.pu.m25.editor.card.beforeedit.header.InvoiceTranstype;
import nc.ui.pu.pub.editor.card.beforeedit.GlobalExchangeRate;
import nc.ui.pu.pub.editor.card.beforeedit.GroupExchangeRate;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;

/**
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ͷ�༭ǰ�¼�ע��
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-26 ����09:50:29
 */
public class CardHeadTailBeforeEditEventHandler extends
    AbstractCardHeadTailBeforeEditEventHandler {

  /*
   * (non-Javadoc)
   * @see
   * nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailBeforeEditEventHandler
   * #registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailBeforeEditEventListener> listenerMap) {
    listenerMap.put(InvoiceHeaderVO.CTRANTYPEID, new InvoiceTranstype());// ��Ʊ����
    listenerMap.put(InvoiceHeaderVO.PK_BANKACCBAS, new AccountBank());// �����˻�
    listenerMap.put(InvoiceHeaderVO.PK_BIZPSN, new BizPerson());// �ɹ�Ա
    listenerMap.put(InvoiceHeaderVO.PK_DEPT_V, new Dept());// ����
    listenerMap.put(InvoiceHeaderVO.NEXCHANGERATE, new ExchangeRate());// �۱�����
    listenerMap.put(InvoiceHeaderVO.NGLOBALEXCHGRATE, new GlobalExchangeRate());// ȫ�ֱ�λ�һ���
    listenerMap.put(InvoiceHeaderVO.NGROUPEXCHGRATE, new GroupExchangeRate());// ���ű�λ�һ���
    listenerMap.put(InvoiceHeaderVO.PK_FREECUST, new FreeCust());// ɢ��
    // ������/����
    listenerMap.put(InvoiceHeaderVO.CSENDCOUNTRYID, new EditableByVAT());
    // �ջ���/����
    listenerMap.put(InvoiceHeaderVO.CRECECOUNTRYID, new EditableByVAT());
    // ��˰��/����
    listenerMap.put(InvoiceHeaderVO.CTAXCOUNTRYID, new EditableByVAT());

    // add by liangchen1 NC631�����ڲɹ�����Ϊ�̳и���������ṩ������չ
    this.registerExpandEventListener(listenerMap);

  }

  /**
   * ������չ
   * liangchen1
   */
  protected void registerExpandEventListener(
      Map<String, ICardHeadTailBeforeEditEventListener> listenerMap) {
    // do nothing
  }

}
