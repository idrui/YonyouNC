package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.pu.cgfa.plugin.bpplugin.CgfaPluginPoint;
import nc.vo.pu.cgfa.AggCgfa;
import nc.itf.pu.ICgfaMaintain;

public class N_JT01_SAVEBASE extends AbstractPfAction<AggCgfa> {

	@Override
	protected CompareAroundProcesser<AggCgfa> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggCgfa> processor = null;
		AggCgfa[] clientFullVOs = (AggCgfa[]) this.getVos();
		if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
				.getPrimaryKey())) {
			processor = new CompareAroundProcesser<AggCgfa>(
					CgfaPluginPoint.SCRIPT_UPDATE);
		} else {
			processor = new CompareAroundProcesser<AggCgfa>(
					CgfaPluginPoint.SCRIPT_INSERT);
		}
		// TODO 在此处添加前后规则
		IRule<AggCgfa> rule = null;

		return processor;
	}

	@Override
	protected AggCgfa[] processBP(Object userObj,
			AggCgfa[] clientFullVOs, AggCgfa[] originBills) {

		AggCgfa[] bills = null;
		try {
			ICgfaMaintain operator = NCLocator.getInstance()
					.lookup(ICgfaMaintain.class);
			if (!StringUtil.isEmptyWithTrim(clientFullVOs[0].getParentVO()
					.getPrimaryKey())) {
				bills = operator.update(clientFullVOs, originBills);
			} else {
				bills = operator.insert(clientFullVOs, originBills);
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}
}
