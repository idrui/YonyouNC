package nc.ui.pu.m21.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m21.editor.card.beforeedit.header.AccountBank;
import nc.ui.pu.m21.editor.card.beforeedit.header.Corigcurrencyid;
import nc.ui.pu.m21.editor.card.beforeedit.header.Deliveradd;
import nc.ui.pu.m21.editor.card.beforeedit.header.Dept;
import nc.ui.pu.m21.editor.card.beforeedit.header.Employee;
import nc.ui.pu.m21.editor.card.beforeedit.header.Freecust;
import nc.ui.pu.m21.editor.card.beforeedit.header.Payment;
import nc.ui.pu.m21.editor.card.beforeedit.header.Supplier;
import nc.ui.pu.m21.editor.card.beforeedit.header.Vtrantypecode;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.vo.pu.m21.entity.OrderHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单卡片界面表头编辑前事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-2-25 上午09:19:13
 */
public class CardHeadTailBeforeEditEventHandler extends
    AbstractCardHeadTailBeforeEditEventHandler {

  @Override
  public void registerEventListener(
      Map<String, ICardHeadTailBeforeEditEventListener> listenerMap) {
    // 业务员的编辑前事件
    listenerMap.put(OrderHeaderVO.CEMPLOYEEID, new Employee());
    // 采购部门
    listenerMap.put(OrderHeaderVO.PK_DEPT_V, new Dept());
    // 采购部门-OID
    listenerMap.put(OrderHeaderVO.PK_DEPT, new Dept());
    // 交易类型
    listenerMap.put(OrderHeaderVO.CTRANTYPEID, new Vtrantypecode());
    // 供应商
    listenerMap.put(OrderHeaderVO.PK_SUPPLIER, new Supplier());
    listenerMap.put(OrderHeaderVO.PK_FREECUST, new Freecust());
    // 银行账户
    listenerMap.put(OrderHeaderVO.PK_BANKDOC, new AccountBank());
    // 供应商发货地址
    listenerMap.put(OrderHeaderVO.PK_DELIVERADD, new Deliveradd());
    // 币种
    listenerMap.put(OrderHeaderVO.CORIGCURRENCYID, new Corigcurrencyid());
    
    listenerMap.put(OrderHeaderVO.PK_PAYTERM, new Payment());
  }
}
