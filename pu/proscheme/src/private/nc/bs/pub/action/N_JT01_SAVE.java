package nc.bs.pub.action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pubapp.pf.action.AbstractPfAction;
import nc.bs.pubapp.pub.rule.CommitStatusCheckRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.bs.pu.cgfa.plugin.bpplugin.CgfaPluginPoint;
import nc.vo.pu.cgfa.AggCgfa;
import nc.itf.pu.ICgfaMaintain;

public class N_JT01_SAVE extends AbstractPfAction<AggCgfa> {

	protected CompareAroundProcesser<AggCgfa> getCompareAroundProcesserWithRules(
			Object userObj) {
		CompareAroundProcesser<AggCgfa> processor = new CompareAroundProcesser<AggCgfa>(
				CgfaPluginPoint.SEND_APPROVE);
		// TODO 在此处添加审核前后规则
		IRule<AggCgfa> rule = new CommitStatusCheckRule();
		processor.addBeforeRule(rule);
		return processor;
	}

	@Override
	protected AggCgfa[] processBP(Object userObj,
			AggCgfa[] clientFullVOs, AggCgfa[] originBills) {
		ICgfaMaintain operator = NCLocator.getInstance().lookup(
				ICgfaMaintain.class);
		AggCgfa[] bills = null;
		try {
			bills = operator.save(clientFullVOs, originBills);
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		return bills;
	}

}
