/**
 * 
 */
package nc.ui.pu.m25.editor.org;

import java.util.List;

import nc.ui.pu.pub.editor.org.AbstractOrgChangedEventHandler;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;

/**
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li></li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-25 上午11:20:29
 */
public class OrgChangedEventHandler extends AbstractOrgChangedEventHandler {

  @Override
  public void registerEventListener(List<IOrgChangedEventListener> listenerList) {
    listenerList.add(new MainOrgChgListener());
  }

}
