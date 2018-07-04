/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-7 ����04:55:11
 */
package nc.ui.pu.m4t.editor.body;

import java.util.Map;

import nc.ui.pu.m4t.editor.body.before.Apfinanceorg;
import nc.ui.pu.m4t.editor.body.before.Apliabcenter;
import nc.ui.pu.m4t.editor.body.before.AssistUnit;
import nc.ui.pu.m4t.editor.body.before.Cffileid;
import nc.ui.pu.m4t.editor.body.before.ChangeRate;
import nc.ui.pu.m4t.editor.body.before.Ctaxcodeid;
import nc.ui.pu.m4t.editor.body.before.EditableByVAT;
import nc.ui.pu.m4t.editor.body.before.Material;
import nc.ui.pu.m4t.editor.body.before.Ncaltaxmny;
import nc.ui.pu.m4t.editor.body.before.VBatchCode;
import nc.ui.pu.pub.editor.card.beforeedit.Casscustid;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.m4t.entity.InitialEstItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ƭ�������༭ǰ�¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-7 ����04:55:11
 */
public class CardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    // Ӧ����֯
    listenerMap.put(InitialEstItemVO.PK_APFINANCEORG_V, new Apfinanceorg());
    // ��λ
    listenerMap.put(InitialEstItemVO.CASTUNITID, new AssistUnit());
    // ������
    listenerMap.put(InitialEstItemVO.VCHANGERATE, new ChangeRate());
    // ���κ�
    listenerMap.put(InitialEstItemVO.VBATCHCODE, new VBatchCode());
    // ����
    listenerMap.put(InitialEstItemVO.PK_MATERIAL, new Material());
    // �ͻ�
    listenerMap.put(InitialEstItemVO.CASSCUSTID, new Casscustid());
    // ������/����
    listenerMap.put(InitialEstItemVO.CSENDCOUNTRYID, new EditableByVAT());
    // �ջ���/����
    listenerMap.put(InitialEstItemVO.CRECECOUNTRYID, new EditableByVAT());
    // ��˰��/����
    listenerMap.put(InitialEstItemVO.CTAXCOUNTRYID, new EditableByVAT());
    // ��˰���
    listenerMap.put(InitialEstItemVO.NCALTAXMNY, new Ncaltaxmny());
    // ˰��
    listenerMap.put(InitialEstItemVO.CTAXCODEID, new Ctaxcodeid());
    // ��������
    listenerMap.put(InitialEstItemVO.PK_APLIABCENTER_V, new Apliabcenter());

    // ������༭�ֶ�
//    NeverEditBodyItem neverEditItem = new NeverEditBodyItem();
    // ������
    listenerMap.put(InitialEstItemVO.CFFILEID, new Cffileid());
  }

}
