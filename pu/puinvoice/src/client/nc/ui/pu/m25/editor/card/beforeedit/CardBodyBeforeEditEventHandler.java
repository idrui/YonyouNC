/**
 * 
 */
package nc.ui.pu.m25.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m25.editor.card.beforeedit.body.ApFinanceOrg;
import nc.ui.pu.m25.editor.card.beforeedit.body.Apliabcenter;
import nc.ui.pu.m25.editor.card.beforeedit.body.Cffileid;
import nc.ui.pu.m25.editor.card.beforeedit.body.ChangeRate;
import nc.ui.pu.m25.editor.card.beforeedit.body.InvoiceAstNum;
import nc.ui.pu.m25.editor.card.beforeedit.body.InvoiceAstUnit;
import nc.ui.pu.m25.editor.card.beforeedit.body.InvoiceNum;
import nc.ui.pu.m25.editor.card.beforeedit.body.Material;
import nc.ui.pu.m25.editor.card.beforeedit.body.Money;
import nc.ui.pu.m25.editor.card.beforeedit.body.Ncaltaxmny;
import nc.ui.pu.m25.editor.card.beforeedit.body.StoreHouse;
import nc.ui.pu.m25.editor.card.beforeedit.body.TaxCode;
import nc.ui.pu.m25.editor.card.beforeedit.body.VBatchCode;
import nc.ui.pu.pub.editor.card.beforeedit.Casscustid;
import nc.ui.pu.pub.editor.card.beforeedit.ProjectTaskId;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li></li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-26 ����09:49:45
 */
public class CardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  /*
   * (non-Javadoc)
   * @see
   * nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler
   * #registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    listenerMap.put(InvoiceItemVO.PK_MATERIAL, new Material());// ��������
    listenerMap.put(InvoiceItemVO.PK_STORDOC, new StoreHouse());// ����ֿ�
    listenerMap.put(InvoiceItemVO.NASTNUM, new InvoiceAstNum());// ��������
    listenerMap.put(InvoiceItemVO.NNUM, new InvoiceNum());// ����������
    listenerMap.put(InvoiceItemVO.NMNY, new Money());// ������
    listenerMap.put(InvoiceItemVO.VCHANGERATE, new ChangeRate());// ���廻����
    listenerMap.put(InvoiceItemVO.CASTUNITID, new InvoiceAstUnit());// ���嵥λ
    listenerMap.put(InvoiceItemVO.PK_APFINANCEORG_V, new ApFinanceOrg());// ����Ӧ����֯
    listenerMap.put(InvoiceItemVO.VBATCHCODE, new VBatchCode());// ���κ�
    listenerMap.put(InvoiceItemVO.CASSCUSTID, new Casscustid()); // �ͻ�

    // ��Ŀ����
    listenerMap.put(InvoiceItemVO.CPROJECTTASKID, new ProjectTaskId());

    listenerMap.put(InvoiceItemVO.NCALTAXMNY, new Ncaltaxmny());// ��˰���
    listenerMap.put(InvoiceItemVO.CTAXCODEID, new TaxCode());// ˰��
    listenerMap.put(InvoiceItemVO.PK_APLIABCENTER_V, new Apliabcenter());// ��������
    // add by liangchen1 NC631�����ڲɹ�����Ϊ�̳и���������ṩ������չ

    // ������༭�ֶ�
//    NeverEditBodyItem neverEditItem = new NeverEditBodyItem();
    // ������
    listenerMap.put(InvoiceItemVO.CFFILEID, new Cffileid());

    this.registerExpandEventListener(listenerMap);
  }

  /**
   * ������չ
   * liangchen1
   */
  protected void registerExpandEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    // do nothing
  }

}
