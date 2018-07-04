/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 下午08:41:46
 */
package nc.ui.pu.m422x.editor.org;

import java.util.List;

import nc.ui.pu.pub.editor.org.AbstractOrgChangedEventHandler;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存组织的编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-16 下午08:41:46
 */
public class OrgChangedEventHandler extends AbstractOrgChangedEventHandler {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.org.AbstractOrgChangedEventHandler#registerEventListener(java.util.List)
   */
  @Override
  public void registerEventListener(List<IOrgChangedEventListener> listenerList) {
    listenerList.add(new StockOrganization());
  }

}
