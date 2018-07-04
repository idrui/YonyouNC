package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.ml.NCLangRes;
import nc.ui.pu.uif2.PUBillManageModel;
import nc.ui.pu.uif2.PUUIState;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.vo.bd.material.plan.MaterialPlanVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.pub.AdDaysUtil;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单的新增、修改保存
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-27 下午07:40:16
 */
public class PraybillSaveAction extends SaveScriptAction {

	private static final long serialVersionUID = 6556301184427090719L;

	private boolean hasAsk = false;

	private boolean isExecuted = true;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		if (PUUIState.EDIT_REVISE == this.getPUUIState()) {
			this.setActionName("SAVEREVISE");
		} else {
			this.setActionName("SAVEBASE");
		}

		// 检查需求日期，建议订货日期，请购日期
		if (!this.checkDate((PraybillVO) this.editor.getValue())) {
			// 主要是在配置了interceptor时，只return的话平台并不知道。所以需要实现isExecuted()方法，告知平台返回。参见TransferViewProcessor
			this.isExecuted = false;
			return;
		}
		this.isExecuted = true;

		// super.doAction(e);

		/*********** 2016-12-36增加保存设备备件编码 ***************/
		// 2018-01-04 由于逻辑已经写好，不做太多改动，不放在bp，就在这做了，修改为只控制新增不管修改保存
		String pk_praybill = (String) ((nc.ui.pu.m20.view.PraybillBillForm) this.editor)
				.getBillCardPanel().getHeadItem("pk_praybill").getValueObject();
		if (pk_praybill == null || "".equals(pk_praybill)) {
			doInsertSaveBeforeAction();
		} else {
			doUpdateSaveBeforeAction();
		}

		super.doAction(e);
		/***************************************************/

		if (UIState.NOT_EDIT == this.getModel().getUiState()) {
			// 设置为普通态
			((PUBillManageModel) this.getModel())
					.setPuUIState(PUUIState.NOT_EDIT);
		}
	}

	/**
	 * @Title: doUpdateSaveBeforeAction
	 * @Description: TODO
	 * @return: void
	 */
	private void doUpdateSaveBeforeAction() {

		// 说明是修改保存
		// 这个时候修改表体的计划状态和备件编码或代码
		String billCode = (String) ((nc.ui.pu.m20.view.PraybillBillForm) this.editor)
				.getBillCardPanel().getHeadItem("vbillcode").getValueObject();
		if (billCode == null) {
			ExceptionUtils.wrappBusinessException("请购单号不能为空！");
		}
		// String billCode =
		// ((PraybillVO)model.getSelectedData()).getParent().getAttributeValue("vbillcode").toString();
		if (!billCode.contains("-")) {
			ExceptionUtils.wrappBusinessException("请购单号不符合规则！");
		}
		String[] pscode = billCode.split("-");
		String a = pscode[1];
		nc.ui.pub.bill.BillCardPanel bcp = ((nc.ui.pu.m20.view.PraybillBillForm) this.editor)
				.getBillCardPanel();
		PraybillItemVO[] itms = (PraybillItemVO[]) bcp.getBillModel()
				.getBodyValueVOs(PraybillItemVO.class.getName());
		Map<Integer, PraybillItemVO> li = new HashMap<Integer, PraybillItemVO>();
		for (int i = 0; i < itms.length; i++) {
			PraybillItemVO praybillItemVO = itms[i];
			if (praybillItemVO.getPrimaryKey() == null
					|| "".equals(praybillItemVO.getPrimaryKey())) {
				li.put(i, praybillItemVO);
			}

		}
		// model.setUiState(UIState.EDIT);
		for (Entry<Integer, PraybillItemVO> entry : li.entrySet()) {
			int i = entry.getKey();
			PraybillItemVO praybillItemVO = entry.getValue();
			String row = praybillItemVO.getCrowno();
			// bcp.setBodyValueAt(billCode+"-"+row, i, "cde_equipment");
			bcp.setBodyValueAt("01", i, "sts_req");// 新增更改状态为‘计划录入’

			// 对行号进行处理 2018-03-02
			String newRowNo = prepareRow(row);
			if ("".endsWith(newRowNo)) {
				// 返回空的话说明行号是空则字段就先不赋值
				continue;
			}
			bcp.setBodyValueAt(a + "_" + newRowNo, i, "cde_equipment");
		}

	}

	/**
	 * @Title: doInsertSaveBeforeAction
	 * @Description: TODO
	 * @return: void
	 */
	private void doInsertSaveBeforeAction() {
		// TODO 自动生成的方法存根

		String billCode = (String) ((nc.ui.pu.m20.view.PraybillBillForm) this.editor)
				.getBillCardPanel().getHeadItem("vbillcode").getValueObject();
		if (billCode == null) {
			ExceptionUtils.wrappBusinessException("请购单号不能为空！");
		}
		// String billCode =
		// ((PraybillVO)model.getSelectedData()).getParent().getAttributeValue("vbillcode").toString();
		if (!billCode.contains("-")) {
			ExceptionUtils.wrappBusinessException("请购单号不符合规则！");
		}
		String[] pscode = billCode.split("-");
		String a = pscode[1];
		nc.ui.pub.bill.BillCardPanel bcp = ((nc.ui.pu.m20.view.PraybillBillForm) this.editor)
				.getBillCardPanel();
		PraybillItemVO[] itms = (PraybillItemVO[]) bcp.getBillModel()
				.getBodyValueVOs(PraybillItemVO.class.getName());
		// model.setUiState(UIState.EDIT);
		for (int i = 0; i < itms.length; i++) {
			PraybillItemVO praybillItemVO = itms[i];
			String row = praybillItemVO.getCrowno();
			// bcp.setBodyValueAt(billCode+"-"+row, i, "cde_equipment");
			bcp.setBodyValueAt("01", i, "sts_req");// 新增更改状态为‘计划录入’

			// 对行号进行处理 2018-03-02
			String newRowNo = prepareRow(row);
			if ("".endsWith(newRowNo)) {
				// 返回空的话说明行号是空则字段就先不赋值
				continue;
			}
			bcp.setBodyValueAt(a + "_" + newRowNo, i, "cde_equipment");
		}

	}

	/**
	 * @Title: prepareRow
	 * @Description: TODO
	 * @param row
	 * @return
	 * @return: String
	 */
	private String prepareRow(String row) {
		// TODO 自动生成的方法存根
		if (row == null || "".equals(row)) {
			return "";
		}
		Integer intValue = Integer.valueOf(row);
		Integer value = intValue.intValue() / 10;
		String rowNo = value.toString();
		int len = rowNo.length();
		if (len == 1) {
			return "000" + rowNo;
		} else if (len == 2) {
			return "00" + rowNo;

		} else if (len == 3) {
			return "0" + rowNo;
		} else if (len == 4) {
			return rowNo;
		} else {
			ExceptionUtils.wrappBusinessException("目前只支持最多5位行号，请检查。");
			return null;
		}
	}

	@Override
	public boolean isExecuted() {
		return this.isExecuted && super.isExecuted();
	}

	/**
	 * 方法功能描述:检查需求日期，建议订货日期，请购日期。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param card
	 *            <p>
	 * @since 6.0
	 * @author GGR
	 * @time 2010-3-30 下午06:03:19
	 */
	private boolean checkDate(PraybillVO vo) {
		if (!this.hasAsk) {
			UFDate billDate = vo.getHVO().getDbilldate() == null ? null : vo
					.getHVO().getDbilldate().asLocalBegin();
			if (billDate == null) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4004020_0",
										"04004020-0119")/*
														 * @res "请先输入请购日期！"
														 */);
			}
			if (null != billDate && null != vo.getBVO()) {
				// 没通过请购日期与建议订货日期、需求日期校验的行号。
				StringBuffer billDateWarnRows = new StringBuffer();
				// 没通过建议订货日期和需求日期校验的行号。
				StringBuffer sugDateErrRows = new StringBuffer();

				for (PraybillItemVO item : vo.getBVO()) {
					UFDate reqDate = item.getDreqdate() == null ? null : item
							.getDreqdate().asLocalBegin();
					UFDate suggestdate = item.getDsuggestdate() == null ? null
							: item.getDsuggestdate().asLocalBegin();

					// 校验请购日期与建议订货日期、需求日期的关系。
					if (null != reqDate
							&& null != suggestdate
							&& (reqDate.beforeDate(billDate) || suggestdate
									.beforeDate(billDate))) {
						if (billDateWarnRows.length() > 0) {
							billDateWarnRows.append(",");
						}
						billDateWarnRows.append(item.getCrowno());
					}

					// 校验建议订货日期和需求日期的关系。
					if (null != reqDate && null != suggestdate
							&& suggestdate.after(reqDate)) {
						sugDateErrRows.append(item.getCrowno());
					}
				}

				// 拼装警告信息。
				String warning = "";
				if (billDateWarnRows.length() > 0) {
					warning += NCLangRes.getInstance().getStrByID("4004020_0",
							"04004020-0086", null,
							new String[] { billDateWarnRows.toString() })/*
																		 * 第{0}行：
																		 * 请购日期大于建议订货日期或需求日期
																		 * 。
																		 */;
				}
				if (sugDateErrRows.length() > 0) {
					if (warning != "") {
						warning += "\n";
					}
					warning += NCLangRes.getInstance().getStrByID("4004020_0",
							"04004020-0102", null,
							new String[] { sugDateErrRows.toString() })/*
																		 * 第{0}行：
																		 * 建议订货日期大于需求日期
																		 * 。
																		 */;
				}

				if (!StringUtils.isEmpty(warning)) {
					warning += "\n"
							+ nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
									.getStrByID("4004020_0", "04004020-0103");/*
																			 * @res
																			 * "是否继续保存？"
																			 */
					if (MessageDialog
							.showYesNoDlg(
									null,
									nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
											.getStrByID("4004020_0",
													"04004020-0015")/*
																	 * @res "提示"
																	 */,
									warning) == UIDialog.ID_NO) {
						return false;
					}
				}
			}
		} else {
			this.hasAsk = false;
		}
		return true;
	}

	/**
	 * 取得物料计划信息中的提前期信息。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param keyValue
	 * @param rows
	 * @return <p>
	 * @since 6.0
	 * @author GGR
	 * @time 2010-4-20 上午09:04:41
	 */
	private Map<String, MaterialPlanVO> getAdDaysInfo(PraybillItemVO[] items) {
		HashSet<String> pk_materials = new HashSet<String>();
		for (int i = 0, len = items.length; i < len; i++) {
			String pk_material = items[i].getPk_material();
			if (!StringUtil.isEmptyWithTrim(pk_material)) {
				pk_materials.add(pk_material);
			}
		}

		String pk_org = this.getModel().getContext().getPk_org();

		return MaterialPubService.queryMaterialPlanMapInfoByPks(
				pk_materials.toArray(new String[pk_materials.size()]), pk_org,
				new String[] { MaterialPlanVO.AHEADBATCH,
						MaterialPlanVO.AHEADCOFF, MaterialPlanVO.FIXEDAHEAD,
						MaterialPlanVO.PK_MATERIAL });
	}

	private PUUIState getPUUIState() {
		return ((PUBillManageModel) this.getModel()).getPuUIState();
	}

	/**
	 * 请购单保存时，检查建议订货日期是否大于（需求日期-固定提前期），如是，则提示“××行建议订货日期晚于应采购日期，是否保存”。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param items
	 * @param dpraydate
	 * @return <p>
	 * @since 6.0
	 * @author GGR
	 * @time 2010-6-24 下午04:28:30
	 */
	protected boolean checkAdvanceDaysBatch(PraybillItemVO[] items,
			UFDate dpraydate) {

		Map<String, MaterialPlanVO> dayinfos = this.getAdDaysInfo(items);
		if (dayinfos != null) {

			StringBuffer errorLins = new StringBuffer();
			UFDate ddemanddate = null;
			UFDate dsuggestdate = null;

			for (int i = 0; i < items.length; i++) {
				String pk_material = items[i].getPk_material();

				if (!StringUtil.isEmptyWithTrim(pk_material)) {
					continue;
				}
				MaterialPlanVO vo = dayinfos.get(pk_material);
				UFDouble num = items[i].getNnum();
				if (null == vo || null == num) {
					continue;
				}

				ddemanddate = items[i].getDreqdate();
				dsuggestdate = items[i].getDsuggestdate();
				if (dpraydate != null && ddemanddate != null
						&& dsuggestdate != null) {
					int intAdvanceValue = AdDaysUtil.getAdDaysArith(num,
							vo.getFixedahead(), vo.getAheadcoff(),
							vo.getAheadbatch());
					if (intAdvanceValue != 0) {
						if (!(ddemanddate.after(dsuggestdate
								.getDateAfter(intAdvanceValue)) || dsuggestdate
								.getDateAfter(intAdvanceValue).equals(
										ddemanddate))) {
							errorLins
									.append(NCLangRes.getInstance()
											.getStrByID(
													"4004020_0",
													"04004020-0087",
													null,
													new String[] { items[i]
															.getCrowno() })/*
																			 * 第{
																			 * 0
																			 * }
																			 * 行建议订货日期晚于日期
																			 * 。
																			 * \
																			 * n
																			 */);
						}
					}
				}
			}

			if (errorLins.length() > 0) {
				int ret = MessageDialog.showYesNoDlg(
						null,
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
								"4004020_0", "04004020-0015")/* @res "提示" */,
						errorLins
								+ nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
										.getStrByID("4004020_0",
												"04004020-0074")/*
																 * @res
																 * "是否继续保存？"
																 */);
				if (ret == UIDialog.ID_NO) {
					ShowStatusBarMsgUtil.showStatusBarMsg(
							nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
									.getStrByID("common", "MD1")/* "保存失败" */,
							this.getModel().getContext());
					return false;
				}
			}

		}
		return true;
	}
}
