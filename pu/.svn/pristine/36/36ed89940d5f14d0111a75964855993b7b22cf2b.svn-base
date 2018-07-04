/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-22 下午02:50:52
 */
package nc.ui.pu.costfactor.editor.list.afteredit;

import java.util.Map;

import nc.ui.pu.costfactor.editor.list.afteredit.rule.CalFactorOrder;
import nc.ui.pu.pub.editor.list.handler.AbstractListHeadAfterEditEventHandler;
import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.vo.pu.costfactor.entity.CostfactorHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>监听 编辑后事件 选择组织时改变序号
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-22 下午02:50:52
 */
public class ListHeadAfterEditEventHandler extends
    AbstractListHeadAfterEditEventHandler {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.list.handler.AbstractListEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, IListHeadAfterEditEventListener> listenerMap) {
    listenerMap.put(CostfactorHeaderVO.PK_ORG, new CalFactorOrder());

  }

}
