/**
 * 
 */
package nc.ui.pu.m25.editor.card.afteredit;

import java.util.Map;

import nc.ui.pu.m25.editor.card.afteredit.header.ArriveDate;
import nc.ui.pu.m25.editor.card.afteredit.header.BillDate;
import nc.ui.pu.m25.editor.card.afteredit.header.CurrencyType;
import nc.ui.pu.m25.editor.card.afteredit.header.ExchangeRate;
import nc.ui.pu.m25.editor.card.afteredit.header.FreeCust;
import nc.ui.pu.m25.editor.card.afteredit.header.GroupAndGlobalExchangeRate;
import nc.ui.pu.m25.editor.card.afteredit.header.InvoiceTranstype;
import nc.ui.pu.m25.editor.card.afteredit.header.PurDept;
import nc.ui.pu.m25.editor.card.afteredit.header.PurchaseOrg;
import nc.ui.pu.m25.editor.card.afteredit.header.ReceCountry;
import nc.ui.pu.m25.editor.card.afteredit.header.SendCountry;
import nc.ui.pu.m25.editor.card.afteredit.header.StockOrg;
import nc.ui.pu.m25.editor.card.afteredit.header.Supplier;
import nc.ui.pu.m25.editor.card.afteredit.header.TaxCountry;
import nc.ui.pu.m25.editor.card.afteredit.header.TaxRate;
import nc.ui.pu.m25.editor.card.afteredit.header.TaxType;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;

/**
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ͷ����༭���¼��ַ���</li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-26 ����09:48:42
 */
public class CardHeadTailAfterEditEventHandler extends
    AbstractCardHeadTailAfterEditEventHandler {

  /*
   * (non-Javadoc)
   * @see
   * nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailAfterEditEventHandler
   * #registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailAfterEditEventListener> listenerMap) {
    listenerMap.put(InvoiceHeaderVO.DBILLDATE, new BillDate());// ��Ʊ����
    listenerMap.put(InvoiceHeaderVO.DARRIVEDATE, new ArriveDate());// Ʊ������
    listenerMap.put(InvoiceHeaderVO.CORIGCURRENCYID, new CurrencyType());// ����(ԭ��)
    listenerMap.put(InvoiceHeaderVO.NEXCHANGERATE, new ExchangeRate());// �۱�����
    listenerMap.put(InvoiceHeaderVO.PK_FREECUST, new FreeCust());// ɢ��
    listenerMap.put(InvoiceHeaderVO.PK_DEPT_V, new PurDept());// �ɹ�����
    listenerMap.put(InvoiceHeaderVO.PK_SUPPLIER, new Supplier());// ��Ӧ��
    listenerMap.put(InvoiceHeaderVO.NTAXRATEH, new TaxRate());// ˰��
    listenerMap.put(InvoiceHeaderVO.FTAXTYPEFLAGH, new TaxType());// ��˰���
    listenerMap.put(InvoiceHeaderVO.PK_STOCKORG_V, new StockOrg());// �����֯
    listenerMap.put(InvoiceHeaderVO.PK_PURCHASEORG_V, new PurchaseOrg());// �ɹ���֯
    listenerMap.put(InvoiceHeaderVO.NGROUPEXCHGRATE,
        new GroupAndGlobalExchangeRate());// ���ű�λ�һ���
    listenerMap.put(InvoiceHeaderVO.NGLOBALEXCHGRATE,
        new GroupAndGlobalExchangeRate());// ȫ�ֱ�λ�һ���
    listenerMap.put(InvoiceHeaderVO.CTRANTYPEID, new InvoiceTranstype());// ��������
    listenerMap.put(InvoiceHeaderVO.CRECECOUNTRYID, new ReceCountry());// �ջ���
    listenerMap.put(InvoiceHeaderVO.CSENDCOUNTRYID, new SendCountry());// ������
    listenerMap.put(InvoiceHeaderVO.CTAXCOUNTRYID, new TaxCountry());// ��˰��

    // add by liangchen1 NC631�����ڲɹ�����Ϊ�̳и���������ṩ������չ
    this.registerExpandEventListener(listenerMap);
  }

  /**
   * ������չ
   * liangchen1
   */
  protected void registerExpandEventListener(
      Map<String, ICardHeadTailAfterEditEventListener> listenerMap) {
    // do nothing
  }

}
