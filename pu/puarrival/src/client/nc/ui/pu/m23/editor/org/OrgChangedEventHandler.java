package nc.ui.pu.m23.editor.org;

import java.util.List;

import nc.ui.pu.pub.editor.org.AbstractOrgChangedEventHandler;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>处理主组织（库存组织）的变化事件：
 * <li>清空表体所有收货仓库的值，并把其置为修改的行状态
 * <li>处理精度
 * <li>设置参照范围
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-26 上午09:48:23
 */
public class OrgChangedEventHandler extends AbstractOrgChangedEventHandler {

  @Override
  public void registerEventListener(List<IOrgChangedEventListener> listenerList) {
    // 清空表头、表体中，相关字段的值
    // handlerList.add(new ClearValueHandler());

    // 处理精度
    // listenerList.add(new Scale());
  }
}
