/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 ����08:41:46
 */
package nc.ui.pu.m422x.editor.org;

import java.util.List;

import nc.ui.pu.pub.editor.org.AbstractOrgChangedEventHandler;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����֯�ı༭���¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-16 ����08:41:46
 */
public class OrgChangedEventHandler extends AbstractOrgChangedEventHandler {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.pub.editor.org.AbstractOrgChangedEventHandler#registerEventListener(java.util.List)
   */
  @Override
  public void registerEventListener(List<IOrgChangedEventListener> listenerList) {
    listenerList.add(new StockOrganization());
  }

}
