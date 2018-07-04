/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018年5月31日 上午10:00:26 
 * @version: V6.5   
 */
package nc.ui.pu.cgfa.action;

import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.pu.IQueryForLineClose;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.cgfa.Cgfa;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sm.funcreg.FuncRegisterVO;

/**
 * @Description: TODO
 * @author: wangzy
 * @date: 2018年5月31日 上午10:00:26
 */
public class LinkQueryReviseRecord extends NCAction {

	private ShowUpableBillForm editor;
	private BillManageModel model;

	public LinkQueryReviseRecord() {
		this.setBtnName("联查采购方案记录");
		this.setCode("LinkQueryReviseRecord");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO 自动生成的方法存根
		Object[] selobjs = model.getSelectedOperaDatas();
		AggCgfa[] agg = new AggCgfa[selobjs.length];
		for (int i = 0; i < selobjs.length; i++) {
			agg[i] = (AggCgfa) selobjs[i];
		}
		Set<String> bpks = new HashSet<String>();
		for (AggCgfa aggCgfa : agg) {
			for (CircularlyAccessibleValueObject child : aggCgfa
					.getChildrenVO()) {
				UFBoolean value = (UFBoolean) child
						.getAttributeValue("whetherlineclose");
				// 有一行有行关闭，则就可以点亮
				if (value.booleanValue()) {
					String srcbid = child.getAttributeValue("csrcid") == null ? ""
							: (String) child.getAttributeValue("csrcid");
					bpks.add(srcbid);
				}
			}
		}
		IQueryForLineClose service = NCLocator.getInstance().lookup(
				IQueryForLineClose.class);
		// 查询相关的采购方案
		AggCgfa[] data = service.findRelationCgfa(bpks.toArray(new String[bpks
				.size()]));
		this.openCGFARDlg(data);

	}

	/**
	 * 打开采购方案记录节点
	 * 
	 * @param selectedVO
	 */
	private void openCGFARDlg(AggregatedValueObject[] destVos) {

		if (!SysInitGroupQuery.isSCEnabled()) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4005002_0",
									"04005002-0070")/*
													 * @res "委外模块未启用，无法生成委外订单!"
													 */);
		}

		FuncletInitData initData = null;
		initData = new FuncletInitData();
		// 查询采购方案记录设置为1
		initData.setInitType(1);
		initData.setInitData(destVos);
		FuncRegisterVO funvo = WorkbenchEnvironment.getInstance()
				.getFuncRegisterVO("40040217");
		if (null == funvo) {
			ExceptionUtils.wrappBusinessException("采购方案记录节点未启用");
		}
		FuncletWindowLauncher.openFuncNodeDialog(WorkbenchEnvironment
				.getInstance().getWorkbench(), funvo, initData, null, true,
				false);

	}

	/**
	 * @return the editor
	 */
	public ShowUpableBillForm getEditor() {
		return editor;
	}

	/**
	 * @param editor
	 *            the editor to set
	 */
	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

	/**
	 * @return the model
	 */
	public BillManageModel getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	@Override
	protected boolean isActionEnable() {
		// TODO 自动生成的方法存根
		Object[] selobjs = model.getSelectedOperaDatas();
		if (null == selobjs) {
			return false;
		}
		AggCgfa[] agg = new AggCgfa[selobjs.length];
		for (int i = 0; i < selobjs.length; i++) {
			agg[i] = (AggCgfa) selobjs[i];
		}

		for (AggCgfa aggCgfa : agg) {
			// 看每个聚合vo里是否有行关闭的如果有一个聚合vo没有行关闭的数据就不可用
			boolean hasN = false;
			if (aggCgfa == null || aggCgfa.getChildrenVO() == null) {
				continue;
			}
			for (CircularlyAccessibleValueObject child : aggCgfa
					.getChildrenVO()) {
				UFBoolean value = (UFBoolean) child
						.getAttributeValue("whetherlineclose");
				// 有一行有行关闭，则就可以点亮
				if (value.booleanValue()) {
					hasN = true;
				}
			}
			if (!hasN) {
				return false;
			}
		}

		return true;

	}

}
