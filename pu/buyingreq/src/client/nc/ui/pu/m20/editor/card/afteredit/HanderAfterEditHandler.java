package nc.ui.pu.m20.editor.card.afteredit;

import java.util.Map;

import nc.ui.pu.m20.editor.card.afteredit.header.Bdirecttransit;
import nc.ui.pu.m20.editor.card.afteredit.header.BillDate;
import nc.ui.pu.m20.editor.card.afteredit.header.Bsctype;
import nc.ui.pu.m20.editor.card.afteredit.header.Project;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.vo.pu.m20.entity.PraybillHeaderVO;

/**
 * <p>
 * <b>�빺����ͷ�༭�¼��Ĵ������࣬������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����༭ǰ�¼�
 * <li>����༭���¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-24 ����05:00:09
 */
public class HanderAfterEditHandler extends
    AbstractCardHeadTailAfterEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailAfterEditEventListener> listenerMap) {
    // ί��
    listenerMap.put(PraybillHeaderVO.BSCTYPE, new Bsctype());
    // ֱ��
    listenerMap.put(PraybillHeaderVO.BDIRECTTRANSIT, new Bdirecttransit());
    // ��Ŀ
    listenerMap.put(PraybillHeaderVO.CHPROJECTID, new Project());
    // �빺����
    listenerMap.put(PraybillHeaderVO.DBILLDATE, new BillDate());

  }

}
