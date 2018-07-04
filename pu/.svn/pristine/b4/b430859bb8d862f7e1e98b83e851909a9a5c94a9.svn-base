package nc.ui.pu.m21.editor.card.afteredit.header;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pu.m21.editor.card.afteredit.PriceQuoterUtil;
import nc.ui.pu.m21.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.m21.rule.ContractLinker;
import nc.ui.pu.m21.rule.CurrencyRelated;
import nc.ui.pu.m21.rule.RelationCalculateAfterQuoter;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.PlanArriveDate;
import nc.vo.pu.pub.enumeration.PriceParam;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单日期的编辑后事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-2-25 上午10:20:43
 */
public class OrderDateAndCurrency implements
		ICardHeadTailAfterEditEventListener {

	@Override
	public void afterEdit(CardHeadTailAfterEditEvent event) {
		CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());

		int[] rows = new int[editor.getItemCount()];
		for (int i = 0; i < rows.length; i++) {
			// 币种查找行
			rows[i] = i;
		}
		String key = event.getKey();
		if (OrderHeaderVO.DBILLDATE.equals(key)
				&& event.getBillCardPanel().getHeadItem(OrderHeaderVO.CORIGCURRENCYID)
						.getValueObject() == null) {
			return;
		}
		// zhangshqb 636需求 币种更改后清空表体价格
		if (OrderHeaderVO.CORIGCURRENCYID.equals(key)) {
			Map<Integer, String> map = this.clearPrice(event);
			RelationCalculateAfterQuoter tool = new RelationCalculateAfterQuoter(
					event.getBillCardPanel());
			tool.relationCalculate(map);
			if(event.getBillCardPanel().getHeadItem(OrderHeaderVO.DBILLDATE)
          .getValueObject() == null){
			  return;
			}
		}

		// 补充本位币和汇率的相关信息，并设置可编辑性
		new CurrencyRelated(event.getBillCardPanel())
				.setCurrencyAndExchangeRate(rows);

		// 关联计算-折本汇率
		new RelationCalculate().calculate(event.getBillCardPanel(), rows,
				OrderItemVO.NEXCHANGERATE);

		Object value = event.getValue();
		if (value == null) {
			return;
		}
		Integer[] integerRows = new Integer[rows.length];
		for (int i = 0; i < rows.length; ++i) {
			integerRows[i] = Integer.valueOf(rows[i]);
		}
		ContractLinker contractLinker = new ContractLinker(event);
		contractLinker.contractLink(integerRows, false, false);
		// 计算计划到货日期
		new PlanArriveDate(editor).setPlanArriveDate(0, editor.getItemCount() - 1);
		// mengjian 根据参数PO16自动询价条件自动询价
		this.setDefaultPrice(event);

	}

	/**
	 * 清空表体单价
	 * 
	 * @param event
	 */
	private Map<Integer, String> clearPrice(CardHeadTailAfterEditEvent event) {
		CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());
		Map<Integer, String> map = new HashMap<Integer, String>();
		int count = editor.getEditor().getRowCount();
		for (int i = 0; i < count; i++) {
			editor.clearBodyValue(i, new String[] { OrderItemVO.NNETPRICE,
					OrderItemVO.NGLOBALMNY, OrderItemVO.NGLOBALTAXMNY,
					OrderItemVO.NORIGTAXMNY });
			editor.clearHeadValue(OrderHeaderVO.NTOTALORIGMNY);
			map.put(Integer.valueOf(i), OrderItemVO.NNETPRICE);
		}
		return map;
	}

	/**
	 * 根据参数PO16自动询价条件自动询价 mengjian
	 * 
	 * @param event
	 */
	@SuppressWarnings("restriction")
	private void setDefaultPrice(CardHeadTailAfterEditEvent event) {
		PriceParam pricceParam = PriceParam.Currency;
		String key = event.getKey();
		if (OrderHeaderVO.CORIGCURRENCYID.equals(key)) {
			// 币种
			pricceParam = PriceParam.Currency;
		} else if (OrderHeaderVO.DBILLDATE.equals(key)) {
			// 单据日期
			pricceParam = PriceParam.BillDate;
		}
		CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());
		int[] rows = new int[editor.getItemCount()];
		for (int i = 0; i < editor.getItemCount(); ++i) {
			rows[i] = i;
		}
		PriceQuoterUtil priceQuoterUtil = new PriceQuoterUtil();
		priceQuoterUtil
				.setDefaultPrice(event.getBillCardPanel(), pricceParam, rows);
	}

}
