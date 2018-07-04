/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-16 下午08:35:46
 */
package nc.ui.pu.m422x.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m422x.editor.card.beforeedit.body.Apppsn;
import nc.ui.pu.m422x.editor.card.beforeedit.body.AssitUnit;
import nc.ui.pu.m422x.editor.card.beforeedit.body.Cbs;
import nc.ui.pu.m422x.editor.card.beforeedit.body.ChangeRate;
import nc.ui.pu.m422x.editor.card.beforeedit.body.Material;
import nc.ui.pu.m422x.editor.card.beforeedit.body.Nextbalanceorg;
import nc.ui.pu.m422x.editor.card.beforeedit.body.Project;
import nc.ui.pu.m422x.editor.card.beforeedit.body.Reqstordoc;
import nc.ui.pu.m422x.editor.card.beforeedit.body.VBatchCode;
import nc.ui.pu.pub.editor.card.beforeedit.ProjectTaskId;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求申请单卡片界面表体编辑前事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-16 下午08:35:46
 */
public class CardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    // 需求仓库
    listenerMap.put(StoreReqAppItemVO.PK_REQSTORDOC, new Reqstordoc());
    // 申请人
    listenerMap.put(StoreReqAppItemVO.PK_APPPSN, new Apppsn());
    // 单位
    listenerMap.put(StoreReqAppItemVO.CASTUNITID, new AssitUnit());
    // 换算率
    listenerMap.put(StoreReqAppItemVO.VCHANGERATE, new ChangeRate());
    listenerMap.put(StoreReqAppItemVO.VBATCHCODE, new VBatchCode());
    listenerMap.put(StoreReqAppItemVO.PK_MATERIAL, new Material());
    // 项目任务
    listenerMap.put(PraybillItemVO.CPROJECTTASKID, new ProjectTaskId());
    // 项目
    listenerMap.put(PraybillItemVO.CPROJECTID, new Project());
    // CBS
    listenerMap.put(StoreReqAppItemVO.CBS, new Cbs());
    // 下次平衡库存组织
    listenerMap
        .put(StoreReqAppItemVO.PK_NEXTBALANCEORG_V, new Nextbalanceorg());
    // 下次平衡库存组织
    listenerMap.put(StoreReqAppItemVO.PK_NEXTBALANCEORG, new Nextbalanceorg());
  }

}
