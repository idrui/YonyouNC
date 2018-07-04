/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 下午08:29:51
 */
package nc.ui.pu.m422x.editor.card.afteredit;

import java.util.Map;

import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;

import nc.ui.pu.m422x.editor.card.afteredit.header.Appdepth;
import nc.ui.pu.m422x.editor.card.afteredit.header.Apppsnh;
import nc.ui.pu.m422x.editor.card.afteredit.header.Dbilldate;
import nc.ui.pu.m422x.editor.card.afteredit.header.Freqtypeflag;
import nc.ui.pu.m422x.editor.card.afteredit.header.Project;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请单卡片表头编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-16 下午08:29:51
 */
public class CardHeadTailAfterEditEventHandler extends
    AbstractCardHeadTailAfterEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailAfterEditEventListener> listenerMap) {
    // 申请部门的编辑后事件
    listenerMap.put(StoreReqAppHeaderVO.PK_APPDEPTH_V, new Appdepth());
    // 申请日期的编辑后事件
    listenerMap.put(StoreReqAppHeaderVO.DBILLDATE, new Dbilldate());
    listenerMap.put(StoreReqAppHeaderVO.PK_APPPSNH, new Apppsnh());
    listenerMap.put(StoreReqAppHeaderVO.FREQTYPEFLAG, new Freqtypeflag());
    listenerMap.put(StoreReqAppHeaderVO.PK_PROJECT, new Project());
  }

}
