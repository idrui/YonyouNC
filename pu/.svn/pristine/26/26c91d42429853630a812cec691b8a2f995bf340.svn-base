/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-7 下午04:57:54
 */
package nc.ui.pu.m4t.editor.body;

import java.util.Map;

import nc.ui.pu.m4t.editor.body.after.AssistUnit;
import nc.ui.pu.m4t.editor.body.after.BatchCode;
import nc.ui.pu.m4t.editor.body.after.Crececountryid;
import nc.ui.pu.m4t.editor.body.after.Csendcountryid;
import nc.ui.pu.m4t.editor.body.after.Ctaxcodeid;
import nc.ui.pu.m4t.editor.body.after.Ctaxcountryid;
import nc.ui.pu.m4t.editor.body.after.Material;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.vo.pu.m4t.entity.InitialEstItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>卡片表体编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-7 下午04:57:54
 */
public class CardBodyAfterEditEventHandler extends
    AbstractCardBodyAfterEditEventHandler {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler#getCalculateListener()
   */
  @Override
  public AbstractRelationCalculateListener getCalculateListener() {
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardBodyAfterEditEventListener> listenerMap) {
    // 物料
    listenerMap.put(InitialEstItemVO.PK_MATERIAL, new Material());

    // 单位
    listenerMap.put(InitialEstItemVO.CASTUNITID, new AssistUnit());

    // 发货国家
    listenerMap.put(InitialEstItemVO.CSENDCOUNTRYID, new Csendcountryid());

    // 收货国家/地区
    listenerMap.put(InitialEstItemVO.CRECECOUNTRYID, new Crececountryid());

    // 报税国/地区
    listenerMap.put(InitialEstItemVO.CTAXCOUNTRYID, new Ctaxcountryid());

    // 税码
    listenerMap.put(InitialEstItemVO.CTAXCODEID, new Ctaxcodeid());

    // 批次号
    listenerMap.put(InitialEstItemVO.VBATCHCODE, new BatchCode());
  }

}
