/**
 * 
 */
package nc.ui.pu.m25.editor.card.afteredit;

import java.util.Map;

import nc.ui.pu.m25.editor.card.afteredit.body.BatchCode;
import nc.ui.pu.m25.editor.card.afteredit.body.InvoiceAstUnit;
import nc.ui.pu.m25.editor.card.afteredit.body.Material;
import nc.ui.pu.m25.editor.card.afteredit.body.Memo;
import nc.ui.pu.m25.editor.card.afteredit.body.Taxcode;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����༭���¼�����ַ���</li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-26 ����09:48:00
 */
public class CardBodyAfterEditEventHandler extends
    AbstractCardBodyAfterEditEventHandler {

  @Override
  public AbstractRelationCalculateListener getCalculateListener() {
    return null;
  }

  @Override
  public void registerEventListener(
      Map<String, ICardBodyAfterEditEventListener> listenerMap) {
    listenerMap.put(InvoiceItemVO.PK_MATERIAL, new Material());// ����
    listenerMap.put(InvoiceItemVO.VMEMOB, new Memo());// ��ע
    listenerMap.put(InvoiceItemVO.CASTUNITID, new InvoiceAstUnit());
    listenerMap.put(InvoiceItemVO.VBATCHCODE, new BatchCode());
    listenerMap.put(InvoiceItemVO.CTAXCODEID, new Taxcode());

    // add by liangchen1 NC631�����ڲɹ�����Ϊ�̳и���������ṩ������չ
    this.registerExpandEventListener(listenerMap);
  }

  /**
   * ������չ
   * liangchen1
   */
  protected void registerExpandEventListener(
      Map<String, ICardBodyAfterEditEventListener> listenerMap) {
    // do nothing
  }

}
