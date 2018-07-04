/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-7 ����04:52:35
 */
package nc.ui.pu.m4t.editor.head;

import java.util.Map;

import nc.ui.pu.m4t.editor.head.before.Bizpsn;
import nc.ui.pu.m4t.editor.head.before.Dept;
import nc.ui.pu.m4t.editor.head.before.ExchangeRate;
import nc.ui.pu.m4t.editor.head.before.InitialEstTranstype;
import nc.ui.pu.m4t.editor.head.before.Managepsn;
import nc.ui.pu.m4t.editor.head.before.Purchaseorg;
import nc.ui.pu.m4t.editor.head.before.Stordoc;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ڳ��ݹ�����Ƭ�����ͷ�༭ǰ�¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-7 ����04:52:35
 */
public class CardHeadTailBeforeEditEventHandler extends
    AbstractCardHeadTailBeforeEditEventHandler {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailBeforeEditEventListener> listenerMap) {
    // �ֿ�
    listenerMap.put(InitialEstHeaderVO.PK_STORDOC, new Stordoc());
    // �ɹ�Ա
    listenerMap.put(InitialEstHeaderVO.PK_BIZPSN, new Bizpsn());
    // �ɹ�����
    listenerMap.put(InitialEstHeaderVO.PK_DEPT_V, new Dept());
    // ����Ա
    listenerMap.put(InitialEstHeaderVO.PK_MANAGEPSN, new Managepsn());
    // �۱�����
    listenerMap.put(InitialEstHeaderVO.NEXCHANGERATE, new ExchangeRate());
    // ��������
    listenerMap.put(InitialEstHeaderVO.CTRANTYPEID, new InitialEstTranstype());
    // �ɹ���֯
    listenerMap.put(InitialEstHeaderVO.PK_PURCHASEORG_V, new Purchaseorg());

  }

}
