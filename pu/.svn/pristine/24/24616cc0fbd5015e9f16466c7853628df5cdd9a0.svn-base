package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.ApproveStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.pu.cgfa.plugin.bpplugin.CgfaPluginPoint;
import nc.vo.pu.cgfa.AggCgfa;
import nc.itf.pu.ICgfaMaintain;

public class N_JT01_APPROVE extends AbstractPfAction<AggCgfa> {

	public N_JT01_APPROVE() {
		super();
	}

	@Override
	protected CompareAroundProcesser<AggCgfa> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggCgfa> processor = new CompareAroundProcesser<AggCgfa>(
				CgfaPluginPoint.APPROVE);
		processor.addBeforeRule(new ApproveStatusCheckRule());
		return processor;
	}

	@Override
	protected AggCgfa[] processBP(Object userObj,
			AggCgfa[] clientFullVOs, AggCgfa[] originBills) {
		AggCgfa[] bills = null;
		ICgfaMaintain operator = NCLocator.getInstance().lookup(
				ICgfaMaintain.class);
		try {
			bills = operator.approve(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
