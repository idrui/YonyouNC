/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 ����08:26:25
 */
package nc.ui.pu.m422x.editor.card.afteredit;

import java.util.Map;

import nc.ui.pu.m422x.editor.card.afteredit.body.Appdept;
import nc.ui.pu.m422x.editor.card.afteredit.body.AssistUnit;
import nc.ui.pu.m422x.editor.card.afteredit.body.BatchCode;
import nc.ui.pu.m422x.editor.card.afteredit.body.Material;
import nc.ui.pu.m422x.editor.card.afteredit.body.Reqdate;
import nc.ui.pu.pub.editor.card.afteredit.CProject;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����������뵥��Ƭ����༭���¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-16 ����08:26:25
 */
public class CardBodyAfterEditEventHandler extends
    AbstractCardBodyAfterEditEventHandler {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler#getCalculateListener()
   */
  @Override
  public AbstractRelationCalculateListener getCalculateListener() {
    return null;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardBodyAfterEditEventListener> listenerMap) {
    // ���ϱ༭����
    listenerMap.put(StoreReqAppItemVO.PK_MATERIAL, new Material());
    // ���벿�ű༭����
    listenerMap.put(StoreReqAppItemVO.PK_APPDEPT_V, new Appdept());
    // ��λ�༭����
    listenerMap.put(StoreReqAppItemVO.CASTUNITID, new AssistUnit());
    // �������ڱ༭����
    listenerMap.put(StoreReqAppItemVO.DREQDATE, new Reqdate());
    // ��Ŀ
    listenerMap.put(PuAttrNameEnum.cprojectid.name(), new CProject());
    // ���κ�
    listenerMap.put(StoreReqAppItemVO.VBATCHCODE, new BatchCode());
  }

}
