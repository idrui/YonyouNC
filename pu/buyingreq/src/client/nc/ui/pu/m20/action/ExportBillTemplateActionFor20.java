package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JComponent;

import nc.ui.trade.excelimport.ExcelImporter;
import nc.ui.trade.excelimport.InputItem;
import nc.ui.trade.excelimport.InputItemCreator;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.ui.uif2.excelimport.DefaultUIF2ImportableEditor;
import nc.ui.uif2.excelimport.ExportExcelTemplateAction;

public class ExportBillTemplateActionFor20 extends ExportExcelTemplateAction{

	private static final long serialVersionUID = 8307967196418933185L;
	/**
	 * 
	 */
	private ExcelImporter ei = new ExcelImporter();

	@Override
	public void setBtnName(String btnName) {
		putValue("Name", "导出Excel格式");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		JComponent parent = getModel().getContext().getEntranceUI();
		ei.exportExcelTemplate(parent, getInputItems());
		ShowStatusBarMsgUtil.showStatusBarMsg("导出成功！", getModel().getContext());

	}

	public List<InputItem> getInputItems() {
		
		IBillCardPanelEditor billCardPanelEditor = ((DefaultUIF2ImportableEditor)getImportableEditor()).getBillcardPanelEditor();
		
		List<InputItem> items = null;
		
		if(billCardPanelEditor != null)
		{
			processBillCardPanel(billCardPanelEditor);
			
			items = InputItemCreator.getInputItems(billCardPanelEditor.getBillCardPanel().getBillData(), false);
		}
		
		return items;
	}

	private void processBillCardPanel(IBillCardPanelEditor billCardPanelEditor) {
		billCardPanelEditor.getBillCardPanel().getHeadTailItem("pk_org").setNull(true);//库存组织
		billCardPanelEditor.getBillCardPanel().getHeadTailItem("pk_org").setShow(true);//库存组织
		billCardPanelEditor.getBillCardPanel().getHeadTailItem("pk_org").setEdit(true);;//库存组织
		
		billCardPanelEditor.getBillCardPanel().getHeadTailItem("vbillcode").setNull(true);//请购单号
		
		billCardPanelEditor.getBillCardPanel().getBodyItem("name_manufac").setShow(true);//原始供应商
		billCardPanelEditor.getBillCardPanel().getBodyItem("name_manufac").setEdit(true);;//原始供应商
		
		billCardPanelEditor.getBillCardPanel().getBodyItem("materialname").setNull(true);//物料名称
//		billCardPanelEditor.getBillCardPanel().getBodyItem("materialspec").setNull(true);//规格型号或图号
//		billCardPanelEditor.getBillCardPanel().getBodyItem("ycg").setNull(true);//原始制造商
		billCardPanelEditor.getBillCardPanel().getBodyItem("castunitid").setNull(true);//单位
	}

	@Override
	protected boolean isActionEnable() {
		return true;
	}
}
