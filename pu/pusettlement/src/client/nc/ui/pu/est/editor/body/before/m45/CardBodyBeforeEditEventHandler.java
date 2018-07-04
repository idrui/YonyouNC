package nc.ui.pu.est.editor.body.before.m45;

import java.util.Map;

import nc.ui.pu.est.editor.body.before.Ctaxcodeid;
import nc.ui.pu.est.editor.body.before.FeeExchgRate;
import nc.ui.pu.est.editor.body.before.Material;
import nc.ui.pu.est.editor.body.before.Ncaltaxmny;
import nc.ui.pu.est.editor.body.before.Supplier;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.est.entity.FeeEstVO;

/**
 * <p>
 * <b>����༭�¼��Ĵ����࣬������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����༭ǰ�¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-06-17 ����05:00:09
 */
public class CardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    // ����
    listenerMap.put(FeeEstVO.PK_FEEMATERIAL, new Material());
    // �۱�����
    listenerMap.put(FeeEstVO.NESTEXCHGRATE, new FeeExchgRate());
    listenerMap.put(FeeEstVO.NCALTAXMNY, new Ncaltaxmny());
    // ˰��
    listenerMap.put(FeeEstVO.CTAXCODEID, new Ctaxcodeid());
    // ��Ӧ��
    listenerMap.put(FeeEstVO.PK_SUPPLIER, new Supplier());
  }
}
