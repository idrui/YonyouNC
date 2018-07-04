/**   
 * Copyright  2018 Yonyou. All rights reserved.
 * @Description: TODO
 * @author: wangzy   
 * @date: 2018��5��31�� ����10:00:26 
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
 * @date: 2018��5��31�� ����10:00:26
 */
public class LinkQueryReviseRecord extends NCAction {

	private ShowUpableBillForm editor;
	private BillManageModel model;

	public LinkQueryReviseRecord() {
		this.setBtnName("����ɹ�������¼");
		this.setCode("LinkQueryReviseRecord");
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// TODO �Զ����ɵķ������
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
				// ��һ�����йرգ���Ϳ��Ե���
				if (value.booleanValue()) {
					String srcbid = child.getAttributeValue("csrcid") == null ? ""
							: (String) child.getAttributeValue("csrcid");
					bpks.add(srcbid);
				}
			}
		}
		IQueryForLineClose service = NCLocator.getInstance().lookup(
				IQueryForLineClose.class);
		// ��ѯ��صĲɹ�����
		AggCgfa[] data = service.findRelationCgfa(bpks.toArray(new String[bpks
				.size()]));
		this.openCGFARDlg(data);

	}

	/**
	 * �򿪲ɹ�������¼�ڵ�
	 * 
	 * @param selectedVO
	 */
	private void openCGFARDlg(AggregatedValueObject[] destVos) {

		if (!SysInitGroupQuery.isSCEnabled()) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("4005002_0",
									"04005002-0070")/*
													 * @res "ί��ģ��δ���ã��޷�����ί�ⶩ��!"
													 */);
		}

		FuncletInitData initData = null;
		initData = new FuncletInitData();
		// ��ѯ�ɹ�������¼����Ϊ1
		initData.setInitType(1);
		initData.setInitData(destVos);
		FuncRegisterVO funvo = WorkbenchEnvironment.getInstance()
				.getFuncRegisterVO("40040217");
		if (null == funvo) {
			ExceptionUtils.wrappBusinessException("�ɹ�������¼�ڵ�δ����");
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
		// TODO �Զ����ɵķ������
		Object[] selobjs = model.getSelectedOperaDatas();
		if (null == selobjs) {
			return false;
		}
		AggCgfa[] agg = new AggCgfa[selobjs.length];
		for (int i = 0; i < selobjs.length; i++) {
			agg[i] = (AggCgfa) selobjs[i];
		}

		for (AggCgfa aggCgfa : agg) {
			// ��ÿ���ۺ�vo���Ƿ����йرյ������һ���ۺ�voû���йرյ����ݾͲ�����
			boolean hasN = false;
			if (aggCgfa == null || aggCgfa.getChildrenVO() == null) {
				continue;
			}
			for (CircularlyAccessibleValueObject child : aggCgfa
					.getChildrenVO()) {
				UFBoolean value = (UFBoolean) child
						.getAttributeValue("whetherlineclose");
				// ��һ�����йرգ���Ϳ��Ե���
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
