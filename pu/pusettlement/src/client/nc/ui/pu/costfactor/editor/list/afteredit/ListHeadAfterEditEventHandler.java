/**
 * $�ļ�˵��$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-22 ����02:50:52
 */
package nc.ui.pu.costfactor.editor.list.afteredit;

import java.util.Map;

import nc.ui.pu.costfactor.editor.list.afteredit.rule.CalFactorOrder;
import nc.ui.pu.pub.editor.list.handler.AbstractListHeadAfterEditEventHandler;
import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.vo.pu.costfactor.entity.CostfactorHeaderVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���� �༭���¼� ѡ����֯ʱ�ı����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-22 ����02:50:52
 */
public class ListHeadAfterEditEventHandler extends
    AbstractListHeadAfterEditEventHandler {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.list.handler.AbstractListEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, IListHeadAfterEditEventListener> listenerMap) {
    listenerMap.put(CostfactorHeaderVO.PK_ORG, new CalFactorOrder());

  }

}
