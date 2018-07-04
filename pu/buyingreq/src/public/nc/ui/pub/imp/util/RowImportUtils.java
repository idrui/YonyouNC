package nc.ui.pub.imp.util;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.Action;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pub.bill.action.BillTableLineAction;
import nc.ui.pub.bill.tableaction.AddLineAction;
import nc.ui.pub.bill.tableaction.InsertLineAction;
import nc.ui.pubapp.uif2app.actions.IBatchBodyLine;
import nc.vo.bd.material.MaterialVO;

public class RowImportUtils {

	private BillCardPanel billCardPanel;

	public RowImportUtils(BillCardPanel billCardPanel) {
		this.billCardPanel = billCardPanel;
	}

	/**
	 * 参照多选处理 - 主子表
	 * 
	 * @param currentRow
	 * @param key
	 * @param isAddNullLine
	 *            为true则新增行，为false则复制行
	 * @return void
	 * @throws MetaDataException
	 */
	public void MoreRowImprot(int currentRow, List<Map<String, String>> lines,
			Map<String, String> colNameInfos, boolean isAddLine)
			throws MetaDataException {

		if (lines == null || lines.size() <= 1) {
			return;
		}

		boolean isInsert = this.insertLine(currentRow, lines.size() - 1,
				isAddLine);

		List<Integer> addRowIndexes = isInsert ? this.getInsertRowIndex(
				currentRow, lines.size()) : this.getAddRowIndex(currentRow,
				lines.size());
		if (addRowIndexes == null || addRowIndexes.size() == 0) {
			return;
		}

		for (int i = 0; i < addRowIndexes.size(); i++) {
			Map<String, String> line = lines.get(i);
			Map<String, String> material = new HashMap<String, String>();
			for (String name : line.keySet()) {

				String key = colNameInfos.get(name);
				String value = line.get(name);
				if (key.equals("cmaterialvid")
						|| key.equals("cmaterialvid.name")
						|| key.equals("ggxhhth")) {
					material.put(key, value);
				}

				this.billCardPanel.setBodyValueAt(value, addRowIndexes.get(i)
						.intValue(), key);
			}
			// 对物料编码和物料名称特殊处理
			String pk_material = queryFormaterial(material);
			if (pk_material != null) {
				this.billCardPanel.setBodyValueAt(pk_material, addRowIndexes
						.get(i).intValue(), "cmaterialvid");
			}
		}

		// 批量执行编辑公式
		int startrow = currentRow;
		int endrow = currentRow + lines.size() - 1;
		BillModel model = this.billCardPanel.getBillModel();
		model.loadLoadRelationItemValue(startrow, endrow);
		model.loadEditRelationItemValue(startrow, endrow+1, "cmaterialvid");
		// 执行编辑公式
		model.execEditFormulasByRows(startrow, endrow+1);

	}

	/**
	 * @Title: queryFormaterial
	 * @Description: TODO
	 * @param material
	 * @return
	 * @return: String
	 * @throws MetaDataException
	 */
	private String queryFormaterial(Map<String, String> material)
			throws MetaDataException {
		// TODO 自动生成的方法存根
		String wherePartOne = "";
		String wherePartTwo = "";
		String wherePartAll = "";
		for (Entry<String, String> map : material.entrySet()) {
			if (map.getValue() != null) {
				String key = map.getKey();
				if (key.equals("cmaterialvid")) {
					key = "code";
				} else if (key.equals("cmaterialvid.name")) {
					key = "name";
				} else if (key.equals("ggxhhth")) {
					key = "materialspec";
				}
				String value = map.getValue();
				if (map.getKey().equals("cmaterialvid")) {
					wherePartOne += key + "='" + value + "' and ";
				}
				if (map.getKey().equals("cmaterialvid")
						|| map.getKey().equals("cmaterialvid.name")) {
					wherePartTwo += key + "='" + value + "' and ";
				}

				wherePartAll += key + "='" + value + "' and ";
			}

		}
		wherePartAll=wherePartAll.substring(0, wherePartAll.length()-4);
		wherePartOne=wherePartOne.substring(0, wherePartOne.length()-4);
		wherePartTwo=wherePartTwo.substring(0, wherePartTwo.length()-4);
		List<nc.vo.bd.material.MaterialVO> res = (List<MaterialVO>) MDPersistenceService
				.lookupPersistenceQueryService()
				.queryBillOfVOByCond(nc.vo.bd.material.MaterialVO.class,
						wherePartAll, false);
		if (res.size() >= 1) {
			return res.get(0).getPrimaryKey();
		} else {

			List<nc.vo.bd.material.MaterialVO> res1 = (List<MaterialVO>) MDPersistenceService
					.lookupPersistenceQueryService().queryBillOfVOByCond(
							nc.vo.bd.material.MaterialVO.class, wherePartTwo,
							false);
			if (res1.size() >= 1) {
				return res1.get(0).getPrimaryKey();
			} else {
				List<nc.vo.bd.material.MaterialVO> res2 = (List<MaterialVO>) MDPersistenceService
						.lookupPersistenceQueryService().queryBillOfVOByCond(
								nc.vo.bd.material.MaterialVO.class,
								wherePartOne, false);
				if (res2.size() >= 1) {

					return res2.get(0).getPrimaryKey();
				}
				return null;

			}
		}
	}

	private List<Integer> getAddRowIndex(int currentRow, int length) {
		List<Integer> indexes = new ArrayList<Integer>();
		for (int i = 0; i < length; i++) {
			indexes.add(Integer.valueOf(currentRow + i + 1));
		}
		return indexes;
	}

	private List<Integer> getInsertRowIndex(int currentRow, int length) {
		List<Integer> indexes = new ArrayList<Integer>();
		for (int i = length; i > 0; i--) {
			indexes.add(Integer.valueOf(currentRow + i - 1));
		}
		return indexes;
	}

	private boolean insertLine(int baseRow, int rowNum, boolean isAddLine) {
		BillScrollPane scrollPane = this.billCardPanel.getBodyPanel();
		Action action = null;
		ActionEvent actionEvent = null;
		// 是否合计行
		Boolean isNeedCalculate = null;
		boolean isInsert = false;
		if (baseRow == -1) {
			rowNum += 1;
		}
		if (isAddLine) {
			/*
			 * //如果baseRow==-1说明是新增的而不是修改的返回false if(baseRow==-1){ return false;
			 * }
			 */
			if (baseRow == this.billCardPanel.getBillModel().getRowCount() - 1) {
				action = scrollPane
						.getBillTableAction(BillTableLineAction.ADDLINE);
				actionEvent = new ActionEvent(scrollPane.getTable(),
						BillTableLineAction.ADDLINE, "AddLine");
			} else {
				action = scrollPane
						.getBillTableAction(BillTableLineAction.INSERTLINE);
				isInsert = true;
			}
		} else {
			scrollPane.getBillTableAction(BillTableLineAction.COPYLINE)
					.actionPerformed(actionEvent);
			action = scrollPane
					.getBillTableAction(BillTableLineAction.PASTELINE);
			isNeedCalculate = this.billCardPanel.getBillModel()
					.isNeedCalculate();
		}
		if (isNeedCalculate != null) {
			this.billCardPanel.getBillModel().setNeedCalculate(false);
		}

		// 如果Action实现了批量接口，则走批量接口方法
		if (action instanceof IBatchBodyLine) {
			((IBatchBodyLine) action).batchBodyLineOperate(rowNum);
		} else {
			for (int i = 0; action != null && i < rowNum; i++) {
				// 因为粘贴行时会触发合计事件，而合计行非常耗性能，所以只在批量粘贴最后一行时进行合计 by yinyxa 2011-9-19
				if (i == (rowNum - 1) && isNeedCalculate != null) {
					this.billCardPanel.getBillModel().setNeedCalculate(
							isNeedCalculate);
				}
				// 新增行和插入行，循环结束后调用批量方法
				if (action instanceof AddLineAction
						|| action instanceof InsertLineAction)
					continue;

				action.actionPerformed(actionEvent);
			}
			if (action instanceof AddLineAction) {
				if (actionEvent.getID() == BillScrollPane.AUTOADDLINE) {
					scrollPane.addLine(rowNum, false);
				} else {
					scrollPane.addLine(rowNum, true);
				}
			} else if (action instanceof InsertLineAction) {
				scrollPane.addLine(rowNum, false);
				int row = scrollPane.getTable().getSelectedRow();
				if (row >= 0) {
					scrollPane.insertLine(row, rowNum);
				}
			}

		}
		return isInsert;
	}

	private int[] toArray(List<Integer> rowList) {
		int[] rows = new int[rowList.size()];
		int length = rows.length;
		for (int i = 0; i < length; i++) {
			rows[i] = rowList.get(i).intValue();
		}
		return rows;
	}

}
