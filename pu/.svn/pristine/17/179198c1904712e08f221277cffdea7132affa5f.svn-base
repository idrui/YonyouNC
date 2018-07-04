package nc.ui.pu.m21.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m21.editor.card.beforeedit.body.Arrvstoorg;
import nc.ui.pu.m21.editor.card.beforeedit.body.AssistUnit;
import nc.ui.pu.m21.editor.card.beforeedit.body.BatchCode;
import nc.ui.pu.m21.editor.card.beforeedit.body.Blargess;
import nc.ui.pu.m21.editor.card.beforeedit.body.CalTaxMny;
import nc.ui.pu.m21.editor.card.beforeedit.body.Ccontractid;
import nc.ui.pu.m21.editor.card.beforeedit.body.Cffileid;
import nc.ui.pu.m21.editor.card.beforeedit.body.ChangeRate;
import nc.ui.pu.m21.editor.card.beforeedit.body.Cqpbaseschemeid;
import nc.ui.pu.m21.editor.card.beforeedit.body.Ctaxcodeid;
import nc.ui.pu.m21.editor.card.beforeedit.body.DestArea;
import nc.ui.pu.m21.editor.card.beforeedit.body.DiscountRate;
import nc.ui.pu.m21.editor.card.beforeedit.body.Effectaddmonth;
import nc.ui.pu.m21.editor.card.beforeedit.body.Effectmonth;
import nc.ui.pu.m21.editor.card.beforeedit.body.Flowstockorg;
import nc.ui.pu.m21.editor.card.beforeedit.body.Material;
import nc.ui.pu.m21.editor.card.beforeedit.body.Nexchangerate;
import nc.ui.pu.m21.editor.card.beforeedit.body.OrigArea;
import nc.ui.pu.m21.editor.card.beforeedit.body.Psfinanceorg;
import nc.ui.pu.m21.editor.card.beforeedit.body.ReceiveAddress;
import nc.ui.pu.m21.editor.card.beforeedit.body.ReceiveWarehouse;
import nc.ui.pu.m21.editor.card.beforeedit.body.Reqstoorg;
import nc.ui.pu.m21.editor.card.beforeedit.body.RequestDepartment;
import nc.ui.pu.m21.editor.card.beforeedit.body.RequestWarehouse;
import nc.ui.pu.m21.editor.card.beforeedit.body.Venddevaddr;
import nc.ui.pu.pub.editor.card.beforeedit.Casscustid;
import nc.ui.pu.pub.editor.card.beforeedit.GlobalExchangeRate;
import nc.ui.pu.pub.editor.card.beforeedit.GroupExchangeRate;
import nc.ui.pu.pub.editor.card.beforeedit.ProjectTaskId;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单卡片界面表体编辑前事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-2-25 上午09:18:01
 */
public class CardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    // 业务单位的编辑前事件
    listenerMap.put(OrderItemVO.CASTUNITID, new AssistUnit());
    // 报价单位的编辑前事件（跟业务单位相同）
    listenerMap.put(OrderItemVO.CQTUNITID, new AssistUnit());
    // 需求仓库的编辑前事件
    listenerMap.put(OrderItemVO.PK_REQSTORDOC, new RequestWarehouse());
    // 收货仓库的编辑前事件
    listenerMap.put(OrderItemVO.PK_RECVSTORDOC, new ReceiveWarehouse());
    // 需求部门的编辑前事件
    listenerMap.put(OrderItemVO.PK_REQDEPT_V, new RequestDepartment());
    // 合同
    listenerMap.put(OrderItemVO.CCONTRACTID, new Ccontractid());
    // 物料
    listenerMap.put(OrderItemVO.PK_MATERIAL, new Material());
    // 需求库存组织
    listenerMap.put(OrderItemVO.PK_REQSTOORG_V, new Reqstoorg());
    // 收货库存组织
    listenerMap.put(OrderItemVO.PK_ARRVSTOORG_V, new Arrvstoorg());
    // 结算财务组织
    listenerMap.put(OrderItemVO.PK_PSFINANCEORG_V, new Psfinanceorg());
    // 换算率
    listenerMap.put(OrderItemVO.VCHANGERATE, new ChangeRate(
        OrderItemVO.VCHANGERATE));
    // 报价单位换算率
    listenerMap.put(OrderItemVO.VQTUNITRATE, new ChangeRate(
        OrderItemVO.VQTUNITRATE));
    // 批次号
    listenerMap.put(OrderItemVO.VBATCHCODE, new BatchCode());
    listenerMap.put(OrderItemVO.CQPBASESCHEMEID, new Cqpbaseschemeid());
    listenerMap.put(OrderItemVO.PK_FLOWSTOCKORG_V, new Flowstockorg());
    listenerMap.put(OrderItemVO.NGLOBALEXCHGRATE, new GlobalExchangeRate());// 全局本位币汇率
    listenerMap.put(OrderItemVO.NGROUPEXCHGRATE, new GroupExchangeRate());// 集团本位币汇率
    // 是否赠品
    listenerMap.put(OrderItemVO.BLARGESS, new Blargess());
    // 项目任务
    listenerMap.put(OrderItemVO.CPROJECTTASKID, new ProjectTaskId());
    // 客户
    listenerMap.put(OrderItemVO.CASSCUSTID, new Casscustid());
    // 计税金额
    listenerMap.put(OrderItemVO.NCALTAXMNY, new CalTaxMny());
    // 税码
    listenerMap.put(OrderItemVO.CTAXCODEID, new Ctaxcodeid());
    // 目的地区
    listenerMap.put(OrderItemVO.CDESTIAREAID, new DestArea());
    // 原产地区
    listenerMap.put(OrderItemVO.CORIGAREAID, new OrigArea());
    // 折扣
    listenerMap.put(OrderItemVO.NITEMDISCOUNTRATE, new DiscountRate());
    // 供应商发货地址
    listenerMap.put(OrderItemVO.VVENDDEVADDR, new Venddevaddr());
    // 收货地址
    listenerMap.put(OrderItemVO.PK_RECEIVEADDRESS, new ReceiveAddress());
    // 折本汇率
    listenerMap.put(OrderItemVO.NEXCHANGERATE, new Nexchangerate());

    // 付款协议页签生效月
    listenerMap.put(OrderPaymentVO.EFFECTMONTH, new Effectmonth());
    // 付款协议页签附加月
    listenerMap.put(OrderPaymentVO.EFFECTADDMONTH, new Effectaddmonth());

//    // 不允许编辑字段
//    NeverEditBodyItem neverEditItem = new NeverEditBodyItem();
    // 特征码
    listenerMap.put(OrderItemVO.CFFILEID, new Cffileid());
  }

}
