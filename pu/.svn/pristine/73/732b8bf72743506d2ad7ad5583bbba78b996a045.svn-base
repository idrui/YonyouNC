package nc.ui.pu.cgfa.action;

import java.awt.event.ActionEvent;

import nc.bs.uif2.IActionCode;
import nc.ui.pu.uif2.PUBillManageModel;
import nc.ui.pu.uif2.PUUIState;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.trade.businessaction.IPFACTION;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.ActionInitializer;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.cgfa.Cgfab;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * @author wangzym
 * @version 2017年3月1日 上午11:15:33
 */
public class SaveAction extends SaveScriptAction {

	/**
	 * 重写doAction方法，要在表头计算表体合计
	 */
	public nc.ui.pubapp.uif2app.view.ShowUpableBillForm editor;

	public SaveAction() {
		ActionInitializer.initializeAction(this, IActionCode.SAVE);
		this.setActionName(IPFACTION.SAVE);
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根

		super.doAction(e);
		/*model.setUiState(UIState.EDIT);
		BillCardPanel bcp = this.getEditor().getBillCardPanel();
		Cgfab[] bVO = (Cgfab[]) bcp.getBillModel().getBodyValueVOs(
				Cgfab.class.getName());
		this.editor.getValue();
		// 获取表体的数据行
		int lens = bVO.length;
		// 获取表体的数量总计;
		int sum = 0;
		for (Cgfab cgfab : bVO) {
			int plan_num = (int) cgfab.getAttributeValue("plan_num");
			sum += plan_num;
		}
		// 将数据行信息，总数信息赋给表头 （没有测试计划项数的数据类型）
		bcp.setHeadItem("sumnum", lens);
		bcp.setHeadItem("sumplannum", sum);
		super.doAction(e);
		if (UIState.NOT_EDIT == this.getModel().getUiState()) {
			// 设置为普通态
			((PUBillManageModel) this.getModel())
					.setPuUIState(PUUIState.NOT_EDIT);
		}*/
	}

	public nc.ui.pubapp.uif2app.view.ShowUpableBillForm getEditor() {
		return editor;
	}

	public void setEditor(nc.ui.pubapp.uif2app.view.ShowUpableBillForm editor) {
		this.editor = editor;
	}

}
