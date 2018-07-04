package nc.ui.pu.m23.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m23.editor.card.beforeedit.body.AstUnit;
import nc.ui.pu.m23.editor.card.beforeedit.body.BatchCode;
import nc.ui.pu.m23.editor.card.beforeedit.body.ChangeRate;
import nc.ui.pu.m23.editor.card.beforeedit.body.Material;
import nc.ui.pu.m23.editor.card.beforeedit.body.NeverEditBodyItem;
import nc.ui.pu.m23.editor.card.beforeedit.body.NumHandler;
import nc.ui.pu.m23.editor.card.beforeedit.body.PresentFlag;
import nc.ui.pu.m23.editor.card.beforeedit.body.Project;
import nc.ui.pu.m23.editor.card.beforeedit.body.Rack;
import nc.ui.pu.m23.editor.card.beforeedit.body.ReceiveStore;
import nc.ui.pu.m23.editor.card.beforeedit.body.Reporter;
import nc.ui.pu.pub.editor.card.beforeedit.Casscustid;
import nc.ui.pu.pub.editor.card.beforeedit.ProjectTaskId;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>到货单表体编辑事件的处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>处理编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-24 下午05:00:09
 */
public class CardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  private LoginContext context;

  public LoginContext getContext() {
    return this.context;
  }

  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {

    // 物料
    listenerMap.put(ArriveItemVO.PK_MATERIAL, new Material());
    // 单位
    listenerMap.put(ArriveItemVO.CASTUNITID, new AstUnit());
    // 报告人
    listenerMap.put(ArriveItemVO.CREPORTERID, new Reporter());
    // 到货数量、到货辅数量
    NumHandler numHandler = new NumHandler();
    listenerMap.put(ArriveItemVO.NNUM, numHandler);
    listenerMap.put(ArriveItemVO.NASTNUM, numHandler);
    // 换算率
    listenerMap.put(ArriveItemVO.VCHANGERATE, new ChangeRate());
    // 批次号
    listenerMap.put(ArriveItemVO.VBATCHCODE, new BatchCode());
    // 是否赠品
    listenerMap.put(ArriveItemVO.BPRESENT, new PresentFlag());
    // 收货仓库
    listenerMap.put(ArriveItemVO.PK_RECEIVESTORE, new ReceiveStore());

    listenerMap.put(ArriveItemVO.CPROJECTID, new Project());
    // 货位
    listenerMap.put(ArriveItemVO.PK_RACK, new Rack());

    // 不允许编辑字段
    NeverEditBodyItem neverEditItem = new NeverEditBodyItem();
    // 是否固定换算率
    listenerMap.put(ArriveItemVO.BFIXEDRATE, neverEditItem);
    // 退货是否基于原订单补货
    listenerMap.put(ArriveItemVO.BBACKREFORDER, neverEditItem);
    // 来源订单行是否赠品
    
    listenerMap.put(ArriveItemVO.FPRODUCTCLASS, neverEditItem);
    listenerMap.put(ArriveItemVO.BPRESENTSOURCE, neverEditItem);
    listenerMap.put(ArriveItemVO.NPRESENTASTNUM, neverEditItem);
    listenerMap.put(ArriveItemVO.NPRESENTNUM, neverEditItem);
    listenerMap.put(ArriveItemVO.NTAXRATE, neverEditItem);
    listenerMap.put(ArriveItemVO.PK_REQSTOORG, neverEditItem);
    listenerMap.put(ArriveItemVO.PK_REQSTORE, neverEditItem);
    listenerMap.put(ArriveItemVO.PK_APFINANCEORG, neverEditItem);
    listenerMap.put(ArriveItemVO.PK_APFINANCEORG_V, neverEditItem);
    listenerMap.put(ArriveItemVO.PK_PSFINANCEORG, neverEditItem);
    listenerMap.put(ArriveItemVO.PK_PSFINANCEORG_V, neverEditItem);
    listenerMap.put(ArriveItemVO.DINVALIDDATE, neverEditItem);
    listenerMap.put(ArriveItemVO.NEXCHANGERATE, neverEditItem);
    // 特征码
    listenerMap.put(ArriveItemVO.CFFILEID, neverEditItem);

    // 项目任务
    listenerMap.put(ArriveItemVO.CPROJECTTASKID, new ProjectTaskId());
    // 客户
    listenerMap.put(ArriveItemVO.CASSCUSTID, new Casscustid());
  }

  public void setContext(LoginContext context) {
    this.context = context;
  }
}
