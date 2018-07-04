package nc.bs.pu.qst.newoaspwh.ace.bp;

import nc.bs.pu.qst.newoaspwh.plugin.bpplugin.NewoaspwhPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.qst.newoaspwh.AggNewoaspwhaHeadVO;

/**
 * 修改保存的BP
 * 
 */
public class AceNewoaspwhUpdateBP {

	public AggNewoaspwhaHeadVO[] update(AggNewoaspwhaHeadVO[] bills,
			AggNewoaspwhaHeadVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggNewoaspwhaHeadVO> bp = new UpdateBPTemplate<AggNewoaspwhaHeadVO>(
				NewoaspwhPluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggNewoaspwhaHeadVO> processer) {
		// TODO 后规则
		IRule<AggNewoaspwhaHeadVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggNewoaspwhaHeadVO> processer) {
		// TODO 前规则
		IRule<AggNewoaspwhaHeadVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
