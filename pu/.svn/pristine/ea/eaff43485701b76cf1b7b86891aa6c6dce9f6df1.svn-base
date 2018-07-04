/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-31 下午03:24:37
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m21.rule.CurrencyRelated;
import nc.ui.pu.m21.rule.EditableSetter;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.CopyAction;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.rule.PlanArriveDate;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单复制
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-31 下午03:24:37
 */
public class OrderCopyAction extends CopyAction {

	private static final long serialVersionUID = 8590694011156167341L;

	/**
	 * 父类方法重写
	 * 
	 * @see nc.ui.pubapp.uif2app.actions.CopyAction#doAction(java.awt.event.ActionEvent)
	 */
	@Override
	public void doAction(ActionEvent e) throws Exception {
		super.doAction(e);
		BillCardPanel panel = this.getEditor().getBillCardPanel();
		panel.getBodyTabbedPane().setSelectedIndex(0);

		// 设定计划到货日期
		PlanArriveDate planArriveDate = new PlanArriveDate(new CardEditorHelper(
				panel));
		planArriveDate.setPlanArriveDate(0, panel.getRowCount() - 1);

		// @see
		// nc.ui.pu.m21.action.processor.CopyActionProcessor#setHeadValue中设置了dbilldate
		// added by wangzhqf 2015-03-27 15:46 补充本位币和汇率的相关信息，并设置可编辑性
		int[] rows = new int[panel.getRowCount()];
		for (int i = 0; i < panel.getRowCount(); ++i) {
			rows[i] = Integer.valueOf(i);
		}
		new CurrencyRelated(panel).setCurrencyAndExchangeRate(rows);

		// ContractLinker cnt = new ContractLinker(panel);
		// cnt.contractLink(rows, false, true);

		// 控制可编辑性
		new EditableSetter(panel).setEditableAll();
		if (((Boolean) panel.getHeadItem(OrderHeaderVO.BISREPLENISH)
				.getValueObject()).booleanValue()) {
			this.getEditor().getBillCardPanel().getBillTable(OrderPaymentVO.TABSNAME)
					.setEnabled(false);
		}
	}

}
