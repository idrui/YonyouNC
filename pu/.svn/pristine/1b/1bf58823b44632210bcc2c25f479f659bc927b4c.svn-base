/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 下午08:26:25
 */
package nc.ui.pu.m422x.editor.card.afteredit;

import java.util.Map;

import nc.ui.pu.m422x.editor.card.afteredit.body.Appdept;
import nc.ui.pu.m422x.editor.card.afteredit.body.AssistUnit;
import nc.ui.pu.m422x.editor.card.afteredit.body.BatchCode;
import nc.ui.pu.m422x.editor.card.afteredit.body.Material;
import nc.ui.pu.m422x.editor.card.afteredit.body.Reqdate;
import nc.ui.pu.pub.editor.card.afteredit.CProject;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请单卡片表体编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-16 下午08:26:25
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
    // 物料编辑处理
    listenerMap.put(StoreReqAppItemVO.PK_MATERIAL, new Material());
    // 申请部门编辑处理
    listenerMap.put(StoreReqAppItemVO.PK_APPDEPT_V, new Appdept());
    // 单位编辑处理
    listenerMap.put(StoreReqAppItemVO.CASTUNITID, new AssistUnit());
    // 需求日期编辑处理
    listenerMap.put(StoreReqAppItemVO.DREQDATE, new Reqdate());
    // 项目
    listenerMap.put(PuAttrNameEnum.cprojectid.name(), new CProject());
    // 批次号
    listenerMap.put(StoreReqAppItemVO.VBATCHCODE, new BatchCode());
  }

}
