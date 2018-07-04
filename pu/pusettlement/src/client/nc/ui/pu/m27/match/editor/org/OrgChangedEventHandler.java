package nc.ui.pu.m27.match.editor.org;

import java.util.List;

import nc.ui.pu.pub.editor.org.AbstractOrgChangedEventHandler;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>财务组织的编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-2-25 上午09:21:24
 */
public class OrgChangedEventHandler extends AbstractOrgChangedEventHandler {
  @Override
  public void registerEventListener(List<IOrgChangedEventListener> listenerList) {
    listenerList.add(new FinanceOrganization(this.getListView()));
  }

}
