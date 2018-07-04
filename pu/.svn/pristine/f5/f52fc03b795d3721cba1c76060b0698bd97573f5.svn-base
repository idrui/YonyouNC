/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 下午08:38:51
 */
package nc.ui.pu.m422x.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m422x.editor.card.beforeedit.header.AppDept;
import nc.ui.pu.m422x.editor.card.beforeedit.header.AppPsn;
import nc.ui.pu.m422x.editor.card.beforeedit.header.Trantypecode;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请单卡片界面表头编辑前事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-16 下午08:38:51
 */
public class CardHeadTailBeforeEditEventHandler extends
    AbstractCardHeadTailBeforeEditEventHandler {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailBeforeEditEventListener> listenerMap) {
    listenerMap.put(StoreReqAppHeaderVO.PK_APPDEPTH_V, new AppDept());
    listenerMap.put(StoreReqAppHeaderVO.PK_APPPSNH, new AppPsn());
    listenerMap.put(StoreReqAppHeaderVO.CTRANTYPEID, new Trantypecode());

  }

}
