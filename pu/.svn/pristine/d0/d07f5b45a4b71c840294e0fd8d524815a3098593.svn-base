package nc.ui.pu.m20.editor.card.afteredit.body;

import nc.ui.pu.m20.editor.card.afteredit.body.pub.SetOrdertrantypeF;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.ClientContext;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.view.util.RefMoreSelectedUtils;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.rule.CalculateBodyDays;
import nc.vo.pu.m20.rule.CastunitidUtil;
import nc.vo.pu.m20.rule.ChangeRateUtil;
import nc.vo.pu.m20.rule.SetEmployee;
import nc.vo.pu.m20.rule.SetOrgVidValue;
import nc.vo.pu.m20.rule.SetPrice;
import nc.vo.pu.m20.rule.SetPurchaseorg;
import nc.vo.pu.m21.rule.PurchaseOrgValue;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * <p>
 * <b>物料编辑事件处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>设置采购组织和订单类型
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-02-02 下午01:25:25
 */
public class Material implements ICardBodyAfterEditEventListener {

	@Override
	public void afterEdit(CardBodyAfterEditEvent event) {
		// 多选物料的时候不需要加载关联项，设置标识。避免加载关联项带来的性能问题。
		ClientContext clientContext = (ClientContext) event.getContext();
		clientContext.setNeedLoadRelationItem(false);
		int[] rows = null;
		try {
			// 物料的多选处理
			RefMoreSelectedUtils utils = new RefMoreSelectedUtils(
					event.getBillCardPanel());
			// 王梓懿 2018-01-11 因为鞍钢苏万斌要求，在导入的时候不考虑物料多选，影响使用
			if (event.isExcelImprting()) {
				rows = new int[] { event.getRow() };
				// end
			} else {
				rows = utils.refMoreSelected(event.getRow(),
						PraybillItemVO.PK_MATERIAL, true);
			}
		} finally {
			// 恢复这个标志。
			clientContext.setNeedLoadRelationItem(true);
		}

		// 清空表体当前选择行相关项目
		this.clear(event.getBillCardPanel(), rows);
		if (rows.length == 1
				&& ObjectUtil.isEmptyWithTrim(event.getBillCardPanel()
						.getBodyValueAt(rows[0], PraybillItemVO.PK_MATERIAL))) {
			return;
		}
		CardEditorHelper kv = new CardEditorHelper(event.getBillCardPanel());
		/*
		 * add by wandl 批量设置表体采购组织vid,解决效率问题！
		 */
		this.processPurchaseOrgVid(kv);
		// 设置单位
		new CastunitidUtil(kv).setCastUnit(rows);

		// 设置“是否固定换算率”、“换算率”。
		new ChangeRateUtil().setChgRateAndFixedFlag(kv, rows);

		// 设置采购组织默认值、参照和是否可编辑
		new SetPurchaseorg().setPurchaseorg(kv, rows);

		// 设置采购员
		new SetEmployee().setEmployee(kv, rows);

		// 设置订单类型默认值和参照
		new SetOrdertrantypeF().setDefOrdertrantypecode(
				event.getBillCardPanel(), kv, rows);

		// 在数量已经有值的情况下，设置主数量=数量
		this.setNnum(event.getBillCardPanel(), rows);

		// 根据参数PO29请购单价格处理规则设置主本币含税单价
		new SetPrice().setPrice(kv, rows);

		// 设置需求日期和建议订货日期
		new CalculateBodyDays().setAdvDays(kv, rows);
	}

	private void clear(BillCardPanel card, int[] rows) {
		for (int row : rows) {
			card.setBodyValueAt(null, row, PraybillItemVO.DREQDATE);
			card.setBodyValueAt(null, row, PraybillItemVO.DSUGGESTDATE);
			card.setBodyValueAt(null, row, PraybillItemVO.VCHANGERATE);
			// card.setBodyValueAt(null, row, PraybillItemVO.NNUM);
			card.setBodyValueAt(null, row, PraybillItemVO.NTAXMNY);
			card.setBodyValueAt(null, row, PraybillItemVO.NTAXPRICE);
			card.setBodyValueAt(null, row, PraybillItemVO.PK_PURCHASEORG);
			card.setBodyValueAt(null, row, PraybillItemVO.PK_EMPLOYEE);
			card.setBodyValueAt(null, row, PraybillItemVO.PK_SUGGESTSUPPLIER);
			card.setBodyValueAt(null, row, PraybillItemVO.VFREE1);
			card.setBodyValueAt(null, row, PraybillItemVO.VFREE2);
			card.setBodyValueAt(null, row, PraybillItemVO.VFREE3);
			card.setBodyValueAt(null, row, PraybillItemVO.VFREE4);
			card.setBodyValueAt(null, row, PraybillItemVO.VFREE5);
			card.setBodyValueAt(null, row, PraybillItemVO.VFREE6);
			card.setBodyValueAt(null, row, PraybillItemVO.VFREE7);
			card.setBodyValueAt(null, row, PraybillItemVO.VFREE8);
			card.setBodyValueAt(null, row, PraybillItemVO.VFREE9);
			card.setBodyValueAt(null, row, PraybillItemVO.VFREE10);
			card.setBodyValueAt(null, row, PraybillItemVO.VBATCHCODE);
			card.setBodyValueAt(null, row, PraybillItemVO.PK_BATCHCODE);
			card.setBodyValueAt(UFBoolean.TRUE, row,
					PraybillItemVO.BCANPURCHASEORGEDIT);
		}
	}

	private void setNnum(BillCardPanel card, int[] rows) {
		// 循环设置
		for (int i = 0, len = rows.length; i < len; i++) {
			UFDouble nnum = ValueUtils.getUFDouble(card.getBodyValueAt(rows[i],
					PraybillItemVO.NNUM));
			if (null == nnum) {
				continue;
			}
			String vchangerate = ValueUtils.getString(card.getBodyValueAt(
					rows[i], PraybillItemVO.VCHANGERATE));
			UFDouble nastnum = HslParseUtil.hslDivUFDouble(vchangerate, nnum);
			// 因为物料改变时设置的单位和主单位是一样的，这里设置数量和主数量一样
			card.setBodyValueAt(nastnum, rows[i], PraybillItemVO.NASTNUM);

		}
	}

	/*
	 * add by wandl 批量设置表体采购组织vid,解决效率问题！
	 */
	private void processPurchaseOrgVid(CardEditorHelper card) {
		new SetOrgVidValue(card).setPurchaseOrgValue();
	}
}
