package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.pu.cgfa.plugin.bpplugin.CgfaPluginPoint;
import nc.vo.pu.cgfa.AggCgfa;
import nc.itf.pu.ICgfaMaintain;

public class N_JT01_DELETE extends AbstractPfAction<AggCgfa> {

	@Override
	protected CompareAroundProcesser<AggCgfa> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggCgfa> processor = new CompareAroundProcesser<AggCgfa>(
				CgfaPluginPoint.SCRIPT_DELETE);
		// TODO 在此处添加前后规则
		return processor;
	}

	@Override
	protected AggCgfa[] processBP(Object userObj,
			AggCgfa[] clientFullVOs, AggCgfa[] originBills) {
		ICgfaMaintain operator = NCLocator.getInstance().lookup(
				ICgfaMaintain.class);
		try {
			operator.delete(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return clientFullVOs;
	}

}
