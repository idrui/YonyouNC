/**   
 * Copyright  2017 Yonyou. All rights reserved.
 * 
 * @Title: ImportDataAction.java 
 * @Prject: pu
 * @Package: nc.ui.pu.m20.action 
 * @Description: TODO
 * @author: wangzy   
 * @date: 2017年9月15日 下午5:34:13 
 * @version: V6.5   
 */
package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.md.model.MetaDataException;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.imp.util.MyExcelImporter;
import nc.ui.pub.imp.util.RowImportUtils;
import nc.ui.pubapp.uif2app.view.util.BillRowNoUtils;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.IVOMetaStatisticInfo;
import nc.vo.pub.SuperVO;

/**
 * @ClassName: ImportDataAction
 * @Description: TODO
 * @author: wangzy
 * @date: 2017年9月15日 下午5:34:13
 */
public class ImportDataAction extends NCAction {
	private IBillCardPanelEditor editor;

	private AbstractAppModel model;

	private static final long serialVersionUID = 1L;

	public IBillCardPanelEditor getEditor() {
		return editor;
	}

	public void setEditor(IBillCardPanelEditor editor) {
		this.editor = editor;
	}

	public AbstractAppModel getModel() {
		return model;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
	}

	public ImportDataAction() {
		this.setBtnName("导入模板数据");
		this.setCode("importfromexcle");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		MyExcelImporter imp = new MyExcelImporter();
		// 读取excle 相关数据
		List<Map<String, String>> readData = imp.importFromExcel(getModel()
				.getContext().getEntranceUI());
		 Map<String, String> kvMap=getColNameInfoByBodyCard(this.editor.getBillCardPanel(),"pk_praybill_b");
		 setValueIntoPanel(this.editor.getBillCardPanel(), kvMap, readData);
		//将数据转换成请购单的vo
		/*PraybillItemVO[] prayBillBvos = (PraybillItemVO[]) convert2VO(readData);
		addLine(prayBillBvos);*/

	}

	/**
	 * @Title: addLine
	 * @Description: TODO
	 * @param prayBillBvos
	 * @return: void
	 *//*
	private void addLine(PraybillItemVO[] prayBillBvos) {
		// TODO 自动生成的方法存根
		// 获得当前界面的表体行数
		int row = this.getEditor().getBillCardPanel().getRowCount();

		for (int n = 0; n < prayBillBvos.length; n++) {
			// 自动增行
			this.getEditor().getBillCardPanel().addLine();
			// 设置行vo
			this.getEditor().getBillCardPanel().getBillModel()
					.setBodyRowVO(prayBillBvos[n], n + row);
			BillRowNoUtils.addLineRowNo(this.getEditor().getBillCardPanel(),
					"crowno");
		}
		this.getEditor().getBillCardPanel().getBillModel()
				.loadLoadRelationItemValue();

	}

	*//**
	 * @Title: convert2VO
	 * @Description: TODO
	 * @param readData
	 * @return
	 * @return: SuperVO
	 *//*
	private SuperVO[] convert2VO(List<Map<String, String>> readData) {
		// TODO 自动生成的方法存根
		PraybillItemVO bvo = new PraybillItemVO();

		IVOMetaStatisticInfo info = bvo.getMetaData().getStatisticInfo();
		Map<String, IAttributeMeta> map = ((nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaStatisticInfo) info)
				.getTableColumnIndex();
		Map<String, String> relation = new HashMap<String, String>();
		Map<String, String> tempMap = new HashMap<String, String>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		// 由于系统默认提供的与需要的键值与需要的相反，取反操作
		for (Entry<String, IAttributeMeta> entry : map.entrySet()) {
			relation.put(entry.getValue().toString(), entry.getKey());

		}

		for (Map<String, String> data : readData) {
			// （name，pk）
			for (Entry<String, String> map2 : data.entrySet()) {
				String id = relation.get(map2.getKey());
				String is[]=id.split("\\.");
				String value = map2.getValue();
				tempMap.put(is[1], value);
			}
			list.add(tempMap);
			tempMap.clear();
		}
		SuperVO[] bvos = createBodyVos(list);
		return bvos;
	}

	*//**
	 * @Title: createBodyVos
	 * @Description: TODO
	 * @param list
	 * @return
	 * @return: SuperVO[]
	 *//*
	private SuperVO[] createBodyVos(List<Map<String, String>> list) {
		// TODO 自动生成的方法存根
		List<PraybillItemVO> bvos = new ArrayList<PraybillItemVO>();
		for (Map<String, String> map : list) {
			PraybillItemVO bvo = new PraybillItemVO();
			for (Entry<String, String> praybillItemVO : map.entrySet()) {
				bvo.setAttributeValue(praybillItemVO.getKey(),
						praybillItemVO.getKey());
			}
			bvos.add(bvo);
		}
		return bvos.toArray(new PraybillItemVO[bvos.size()]);
	}*/
	
	//建哥提供的方法
	public Map<String, String> getColNameInfoByBodyCard(BillCardPanel cardPanel, String tablecode){
		BillItem[] items = cardPanel.getBillData().getBodyItemsForTable(tablecode);
		
		Map<String, String> colNameInfoMap = new HashMap<String, String>();
		if(items != null && items.length > 0){
			for(BillItem item : items){
				String colName = item.getName();
				String colKey = item.getKey();
				
				colNameInfoMap.put(colName, colKey);
			}
		}
		
		return colNameInfoMap;
	}
	
	public void setValueIntoPanel(BillCardPanel cardPanel, Map<String, String> colNameInfos, List<Map<String,String>> lines){
		if(lines == null || lines.size() == 0 || lines.get(0) == null){
			return;
		}
		
		RowImportUtils util = new RowImportUtils(cardPanel);
		
		int currentRow = editor.getBillCardPanel().getBillTable().getSelectedRow();
		try {
			util.MoreRowImprot(currentRow, lines, colNameInfos, true);
		} catch (MetaDataException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}

}
