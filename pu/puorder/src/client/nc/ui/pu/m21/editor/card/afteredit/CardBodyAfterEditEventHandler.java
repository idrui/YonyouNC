package nc.ui.pu.m21.editor.card.afteredit;

import java.util.Map;

import nc.ui.pu.m21.editor.card.afteredit.body.AccountDay;
import nc.ui.pu.m21.editor.card.afteredit.body.Accrate;
import nc.ui.pu.m21.editor.card.afteredit.body.ArriveStoreOrg;
import nc.ui.pu.m21.editor.card.afteredit.body.ArrliabCenter;
import nc.ui.pu.m21.editor.card.afteredit.body.AssistUnit;
import nc.ui.pu.m21.editor.card.afteredit.body.BatchCode;
import nc.ui.pu.m21.editor.card.afteredit.body.Ccontractid;
import nc.ui.pu.m21.editor.card.afteredit.body.CheckDate;
import nc.ui.pu.m21.editor.card.afteredit.body.Customer;
import nc.ui.pu.m21.editor.card.afteredit.body.DestCountry;
import nc.ui.pu.m21.editor.card.afteredit.body.EffectAddMonth;
import nc.ui.pu.m21.editor.card.afteredit.body.ExchangeRate;
import nc.ui.pu.m21.editor.card.afteredit.body.Material;
import nc.ui.pu.m21.editor.card.afteredit.body.OrigCountry;
import nc.ui.pu.m21.editor.card.afteredit.body.PaymentDay;
import nc.ui.pu.m21.editor.card.afteredit.body.QtUnit;
import nc.ui.pu.m21.editor.card.afteredit.body.ReceiveAddress;
import nc.ui.pu.m21.editor.card.afteredit.body.ReceiveCountry;
import nc.ui.pu.m21.editor.card.afteredit.body.ReceiveWarehouse;
import nc.ui.pu.m21.editor.card.afteredit.body.RequestStoreOrg;
import nc.ui.pu.m21.editor.card.afteredit.body.SendCountry;
import nc.ui.pu.m21.editor.card.afteredit.body.SettleFinanceOrg;
import nc.ui.pu.m21.editor.card.afteredit.body.SpecContractLinkDim;
import nc.ui.pu.m21.editor.card.afteredit.body.SupplierAddrress;
import nc.ui.pu.m21.editor.card.afteredit.body.TaxCode;
import nc.ui.pu.m21.editor.card.afteredit.body.TaxCountry;
import nc.ui.pu.pub.editor.card.afteredit.CProject;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.pub.enumeration.PuAttrNameEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单卡片表体编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-2-25 上午09:15:33
 */
public class CardBodyAfterEditEventHandler extends
    AbstractCardBodyAfterEditEventHandler {

  @Override
  public AbstractRelationCalculateListener getCalculateListener() {
    return null;
  }

  @Override
  public void registerEventListener(
      Map<String, ICardBodyAfterEditEventListener> listenerMap) {
    // 物料编辑处理
    listenerMap.put(OrderItemVO.PK_MATERIAL, new Material());
    // 需求库存组织编辑处理
    listenerMap.put(OrderItemVO.PK_REQSTOORG_V, new RequestStoreOrg());
    // 收货库存组织编辑处理
    listenerMap.put(OrderItemVO.PK_ARRVSTOORG_V, new ArriveStoreOrg());
    // 结算财务组织的编辑后事件
    listenerMap.put(OrderItemVO.PK_PSFINANCEORG_V, new SettleFinanceOrg());
    // 收货仓库的编辑后事件
    listenerMap.put(OrderItemVO.PK_RECVSTORDOC, new ReceiveWarehouse());
    // 业务单位的编辑后事件
    listenerMap.put(OrderItemVO.CASTUNITID, new AssistUnit());
    // 报价单位的编辑后事件
    listenerMap.put(OrderItemVO.CQTUNITID, new QtUnit());
    // 合同的编辑后事件
    listenerMap.put(OrderItemVO.CCONTRACTID, new Ccontractid());
    // 全局本位币汇率
    listenerMap.put(OrderItemVO.NGROUPEXCHGRATE, new ExchangeRate());
    // 集团本位币汇率
    listenerMap.put(OrderItemVO.NGLOBALEXCHGRATE, new ExchangeRate());
    listenerMap.put(OrderItemVO.VBATCHCODE, new BatchCode());
    // 项目
    listenerMap.put(PuAttrNameEnum.cprojectid.name(), new CProject());
    // 发货国编辑后事件
    listenerMap.put(OrderItemVO.CSENDCOUNTRYID, new SendCountry());
    // 收货国编辑后事件
    listenerMap.put(OrderItemVO.CRECECOUNTRYID, new ReceiveCountry());
    // 报税国编辑后事件
    listenerMap.put(OrderItemVO.CTAXCOUNTRYID, new TaxCountry());
    // 客户编辑后事件
    listenerMap.put(OrderItemVO.CASSCUSTID, new Customer());
    // 原产国编辑后事件
    listenerMap.put(OrderItemVO.CORIGCOUNTRYID, new OrigCountry());
    // 目的国编辑后事件
    listenerMap.put(OrderItemVO.CDESTICOUNTRYID, new DestCountry());
    // 税码
    listenerMap.put(OrderItemVO.CTAXCODEID, new TaxCode());
    // 供应商发货地址
    listenerMap.put(OrderItemVO.VVENDDEVADDR, new SupplierAddrress());
    // 收货地址
    listenerMap.put(OrderItemVO.PK_RECEIVEADDRESS, new ReceiveAddress());
    // 收货利润中心 by guoyk 2014年9月25日
    listenerMap.put(OrderItemVO.PK_ARRLIABCENTER_V, new ArrliabCenter());
    // 关联合同的特殊匹配维度
    SpecContractLinkDim specContractLink = new SpecContractLinkDim();
    listenerMap.put(OrderItemVO.CDEVAREAID, specContractLink);
    listenerMap.put(OrderItemVO.CQUALITYLEVELID, specContractLink);
    listenerMap.put(OrderItemVO.CPRODUCTORID, specContractLink);

    // 固定结账日
    listenerMap.put(OrderPaymentVO.CHECKDATA, new CheckDate());
    listenerMap.put(OrderPaymentVO.ACCRATE, new Accrate());
    listenerMap.put(OrderPaymentVO.ACCOUNTDAY, new AccountDay());
    listenerMap.put(OrderPaymentVO.PAYMENTDAY, new PaymentDay());
    listenerMap.put(OrderPaymentVO.EFFECTADDMONTH, new EffectAddMonth());
    
  }

}
