/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-11 下午09:06:09
 */
package nc.ui.pu.m21.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m21.editor.card.beforeedit.body.AssistUnit;
import nc.ui.pu.m21.editor.card.beforeedit.body.BatchCode;
import nc.ui.pu.m21.editor.card.beforeedit.body.Blargess;
import nc.ui.pu.m21.editor.card.beforeedit.body.ChangeRate;
import nc.ui.pu.m21.editor.card.beforeedit.body.Ctaxcodeid;
import nc.ui.pu.m21.editor.card.beforeedit.body.DiscountRate;
import nc.ui.pu.m21.editor.card.beforeedit.body.Material;
import nc.ui.pu.m21.editor.card.beforeedit.body.ReceiveWarehouse;
import nc.ui.pu.m21.editor.card.beforeedit.body.RequestDepartment;
import nc.ui.pu.pub.editor.card.beforeedit.Casscustid;
import nc.ui.pu.pub.editor.card.beforeedit.GlobalExchangeRate;
import nc.ui.pu.pub.editor.card.beforeedit.GroupExchangeRate;
import nc.ui.pu.pub.editor.card.beforeedit.ProjectTaskId;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单修订卡片界面表体编辑前事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-11 下午09:06:09
 */
public class ReviseCardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pu.pub.editor.card.handler.AbstractCardEventHandler#registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    // 收货仓库的编辑前事件
    listenerMap.put(OrderItemVO.PK_RECVSTORDOC, new ReceiveWarehouse());
    listenerMap.put(OrderItemVO.PK_REQDEPT_V, new RequestDepartment());
    listenerMap.put(OrderItemVO.BLARGESS, new Blargess());
    // 物料
    listenerMap.put(OrderItemVO.PK_MATERIAL, new Material());
    // 业务单位的编辑前事件
    listenerMap.put(OrderItemVO.CASTUNITID, new AssistUnit());
    // 报价单位的编辑前事件（跟业务单位相同）
    listenerMap.put(OrderItemVO.CQTUNITID, new AssistUnit());
    // 换算率
    listenerMap.put(OrderItemVO.VCHANGERATE, new ChangeRate(
        OrderItemVO.VCHANGERATE));
    // 报价单位换算率
    listenerMap.put(OrderItemVO.VQTUNITRATE, new ChangeRate(
        OrderItemVO.VQTUNITRATE));
    // 批次号
    listenerMap.put(OrderItemVO.VBATCHCODE, new BatchCode());
    listenerMap.put(OrderItemVO.NGLOBALEXCHGRATE, new GlobalExchangeRate());// 全局本位币汇率
    listenerMap.put(OrderItemVO.NGROUPEXCHGRATE, new GroupExchangeRate());// 集团本位币汇率
    // 项目任务
    listenerMap.put(PraybillItemVO.CPROJECTTASKID, new ProjectTaskId());
    // 客户
    listenerMap.put(OrderItemVO.CASSCUSTID, new Casscustid());
    // 税码
    listenerMap.put(OrderItemVO.CTAXCODEID, new Ctaxcodeid());
    // 折扣
    listenerMap.put(OrderItemVO.NITEMDISCOUNTRATE, new DiscountRate());
  }
}
