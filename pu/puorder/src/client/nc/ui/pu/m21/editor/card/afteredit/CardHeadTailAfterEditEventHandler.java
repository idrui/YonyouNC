package nc.ui.pu.m21.editor.card.afteredit;

import java.util.Map;

import nc.ui.pu.m21.editor.card.afteredit.header.FreeCust;
import nc.ui.pu.m21.editor.card.afteredit.header.InvcSupplier;
import nc.ui.pu.m21.editor.card.afteredit.header.OrderDateAndCurrency;
import nc.ui.pu.m21.editor.card.afteredit.header.Payment;
import nc.ui.pu.m21.editor.card.afteredit.header.Project;
import nc.ui.pu.m21.editor.card.afteredit.header.ReceiveCustomer;
import nc.ui.pu.m21.editor.card.afteredit.header.RefWhenReturn;
import nc.ui.pu.m21.editor.card.afteredit.header.SpecContractLinkDim;
import nc.ui.pu.m21.editor.card.afteredit.header.Supplier;
import nc.ui.pu.m21.editor.card.afteredit.header.SupplierSendAddress;
import nc.ui.pu.m21.editor.card.afteredit.header.TaxRate;
import nc.ui.pu.m21.editor.card.afteredit.header.TaxType;
import nc.ui.pu.m21.editor.card.afteredit.header.Vtrantypecode;
import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.vo.pu.m21.entity.OrderHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单卡片表头编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-2-25 上午09:16:47
 */
public class CardHeadTailAfterEditEventHandler extends
		AbstractCardHeadTailAfterEditEventHandler {

	@Override
	public void registerEventListener(
			Map<String, ICardHeadTailAfterEditEventListener> listenerMap) {
		// 供应商的编辑后事件
		listenerMap.put(OrderHeaderVO.PK_SUPPLIER, new Supplier());
		// 开票供应商的编辑后事件
		listenerMap.put(OrderHeaderVO.PK_INVCSUPLLIER, new InvcSupplier());
		// 订单日期的编辑后事件
		listenerMap.put(OrderHeaderVO.DBILLDATE, new OrderDateAndCurrency());
		// 币种的编辑后事件
		listenerMap.put(OrderHeaderVO.CORIGCURRENCYID,
				new OrderDateAndCurrency());
		// 整单扣税类别的编辑后事件
		listenerMap.put(OrderHeaderVO.FHTAXTYPEFLAG, new TaxType());
		// 整单税率的编辑后事件
		listenerMap.put(OrderHeaderVO.NHTAXRATE, new TaxRate());
		// 表头项目的编辑后事件
		listenerMap.put(OrderHeaderVO.PK_PROJECT, new Project());
		// 订单类型
		listenerMap.put(OrderHeaderVO.CTRANTYPEID, new Vtrantypecode());
		// 表头收货单位编辑后事件
		listenerMap.put(OrderHeaderVO.PK_RECVCUSTOMER, new ReceiveCustomer());
		// 散户
		listenerMap.put(OrderHeaderVO.PK_FREECUST, new FreeCust());
		// 退货基于原订单补货
		listenerMap.put(OrderHeaderVO.BREFWHENRETURN, new RefWhenReturn());
		// 退货
		listenerMap.put(OrderHeaderVO.BRETURN, new RefWhenReturn());
		// 供应商发货地址
		listenerMap.put(OrderHeaderVO.PK_DELIVERADD, new SupplierSendAddress());
		// 关联合同的特殊匹配维度
		SpecContractLinkDim specContractLink = new SpecContractLinkDim();
		listenerMap.put(OrderHeaderVO.PK_TRANSPORTTYPE, specContractLink);

		// 付款协议
		listenerMap.put(OrderHeaderVO.PK_PAYTERM, new Payment());
	}

}
