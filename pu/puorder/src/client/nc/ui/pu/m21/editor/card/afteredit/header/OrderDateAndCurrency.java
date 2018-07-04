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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ڵı༭���¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-2-25 ����10:20:43
 */
public class OrderDateAndCurrency implements
		ICardHeadTailAfterEditEventListener {

	@Override
	public void afterEdit(CardHeadTailAfterEditEvent event) {
		CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());

		int[] rows = new int[editor.getItemCount()];
		for (int i = 0; i < rows.length; i++) {
			// ���ֲ�����
			rows[i] = i;
		}
		String key = event.getKey();
		if (OrderHeaderVO.DBILLDATE.equals(key)
				&& event.getBillCardPanel().getHeadItem(OrderHeaderVO.CORIGCURRENCYID)
						.getValueObject() == null) {
			return;
		}
		// zhangshqb 636���� ���ָ��ĺ���ձ���۸�
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

		// ���䱾λ�Һͻ��ʵ������Ϣ�������ÿɱ༭��
		new CurrencyRelated(event.getBillCardPanel())
				.setCurrencyAndExchangeRate(rows);

		// ��������-�۱�����
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
		// ����ƻ���������
		new PlanArriveDate(editor).setPlanArriveDate(0, editor.getItemCount() - 1);
		// mengjian ���ݲ���PO16�Զ�ѯ�������Զ�ѯ��
		this.setDefaultPrice(event);

	}

	/**
	 * ��ձ��嵥��
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
	 * ���ݲ���PO16�Զ�ѯ�������Զ�ѯ�� mengjian
	 * 
	 * @param event
	 */
	@SuppressWarnings("restriction")
	private void setDefaultPrice(CardHeadTailAfterEditEvent event) {
		PriceParam pricceParam = PriceParam.Currency;
		String key = event.getKey();
		if (OrderHeaderVO.CORIGCURRENCYID.equals(key)) {
			// ����
			pricceParam = PriceParam.Currency;
		} else if (OrderHeaderVO.DBILLDATE.equals(key)) {
			// ��������
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
