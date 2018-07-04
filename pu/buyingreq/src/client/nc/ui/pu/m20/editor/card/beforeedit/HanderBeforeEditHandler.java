package nc.ui.pu.m20.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m20.editor.card.beforeedit.header.Plandept;
import nc.ui.pu.m20.editor.card.beforeedit.header.Planpsn;
import nc.ui.pu.m20.editor.card.beforeedit.header.Project;
import nc.ui.pu.m20.editor.card.beforeedit.header.ScType;
import nc.ui.pu.m20.editor.card.beforeedit.header.Trantypecode;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.vo.pu.m20.entity.PraybillHeaderVO;

/**
 * <p>
 * <b>�빺����ͷ�༭�¼��Ĵ������࣬������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ע��༭ǰ�¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-24 ����05:00:09
 */
public class HanderBeforeEditHandler extends
    AbstractCardHeadTailBeforeEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailBeforeEditEventListener> listenerMap) {

    // �빺����
    listenerMap.put(PraybillHeaderVO.CTRANTYPEID, new Trantypecode());

    // �ƻ�����
    listenerMap.put(PraybillHeaderVO.PK_PLANDEPT, new Plandept());
    listenerMap.put(PraybillHeaderVO.PK_PLANDEPT_V, new Plandept());

    // �ƻ�Ա
    listenerMap.put(PraybillHeaderVO.PK_PLANPSN, new Planpsn());

    // ��Ŀ
    listenerMap.put(PraybillHeaderVO.CHPROJECTID, new Project());
    // �Ƿ�ί��
    listenerMap.put(PraybillHeaderVO.BSCTYPE, new ScType());

  }

}
