package nc.ui.pu.m21.rule;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.md.data.access.NCObject;
import nc.ui.ml.NCLangRes;
import nc.ui.scmpub.tempsave.interceptor.SaveTempSrcCheckInterceptor;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.ml.NCLangRes4VoTransl;

public class OrderSaveTempSrcCheckInterceptor extends
		SaveTempSrcCheckInterceptor {

	private String srcFirstField;
	private String errormsg = NCLangRes.getInstance().getStrByID("uif2",
			"DefaultExceptionHanler-000005")/* 操作失败! */;

	@Override
	public boolean beforeDoAction(Action action, ActionEvent e) {
		boolean b = super.beforeDoAction(action, e);
		Object value = this.getBillFormEditor().getValue();
		if (b) {
			boolean check = this.check(value);
			if (!check) {
				ShowStatusBarMsgUtil.showErrorMsg(errormsg, NCLangRes4VoTransl
						.getNCLangRes().getStrByID("tempsave", "2tempsave-00029")/*
																																			 * 单据存在来源，
																																			 * 不能支持暂存
																																			 * !
																																			 */, this
						.getBillFormEditor().getModel().getContext());
				return false;
			}
		}
		return b;
	}

	private boolean check(Object value) {
		if (value == null) {
			return true;
		}
		NCObject ncobj = NCObject.newInstance(value);
		Object obj = ncobj.getAttributeValue(this.srcFirstField);
		if (obj == null) {
			return true;
		}

		if (obj.getClass().isArray()) {
			Object[] arrayObjs = (Object[]) obj;
			for (Object arrayObj : arrayObjs) {
				if (arrayObj != null) {
					return false;
				}
			}
		} else {
			if (obj != null) {
				return false;
			}
		}
		return true;
	}

	public String getSrcFirstField() {
		return srcFirstField;
	}

	public void setSrcFirstField(String srcFirstField) {
		this.srcFirstField = srcFirstField;
	}
}
