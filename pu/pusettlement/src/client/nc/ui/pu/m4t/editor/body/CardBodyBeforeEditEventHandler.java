/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-7 下午04:55:11
 */
package nc.ui.pu.m4t.editor.body;

import java.util.Map;

import nc.ui.pu.m4t.editor.body.before.Apfinanceorg;
import nc.ui.pu.m4t.editor.body.before.Apliabcenter;
import nc.ui.pu.m4t.editor.body.before.AssistUnit;
import nc.ui.pu.m4t.editor.body.before.Cffileid;
import nc.ui.pu.m4t.editor.body.before.ChangeRate;
import nc.ui.pu.m4t.editor.body.before.Ctaxcodeid;
import nc.ui.pu.m4t.editor.body.before.EditableByVAT;
import nc.ui.pu.m4t.editor.body.before.Material;
import nc.ui.pu.m4t.editor.body.before.Ncaltaxmny;
import nc.ui.pu.m4t.editor.body.before.VBatchCode;
import nc.ui.pu.pub.editor.card.beforeedit.Casscustid;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.m4t.entity.InitialEstItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>卡片界面表体编辑前事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-7 下午04:55:11
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
    // 应付组织
    listenerMap.put(InitialEstItemVO.PK_APFINANCEORG_V, new Apfinanceorg());
    // 单位
    listenerMap.put(InitialEstItemVO.CASTUNITID, new AssistUnit());
    // 换算率
    listenerMap.put(InitialEstItemVO.VCHANGERATE, new ChangeRate());
    // 批次号
    listenerMap.put(InitialEstItemVO.VBATCHCODE, new VBatchCode());
    // 物料
    listenerMap.put(InitialEstItemVO.PK_MATERIAL, new Material());
    // 客户
    listenerMap.put(InitialEstItemVO.CASSCUSTID, new Casscustid());
    // 发货国/地区
    listenerMap.put(InitialEstItemVO.CSENDCOUNTRYID, new EditableByVAT());
    // 收货国/地区
    listenerMap.put(InitialEstItemVO.CRECECOUNTRYID, new EditableByVAT());
    // 报税国/地区
    listenerMap.put(InitialEstItemVO.CTAXCOUNTRYID, new EditableByVAT());
    // 计税金额
    listenerMap.put(InitialEstItemVO.NCALTAXMNY, new Ncaltaxmny());
    // 税码
    listenerMap.put(InitialEstItemVO.CTAXCODEID, new Ctaxcodeid());
    // 利润中心
    listenerMap.put(InitialEstItemVO.PK_APLIABCENTER_V, new Apliabcenter());

    // 不允许编辑字段
//    NeverEditBodyItem neverEditItem = new NeverEditBodyItem();
    // 特征码
    listenerMap.put(InitialEstItemVO.CFFILEID, new Cffileid());
  }

}
