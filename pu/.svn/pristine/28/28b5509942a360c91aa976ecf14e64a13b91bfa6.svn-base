package nc.bs.pu.qst.newoabase.ace.bp;

import nc.bs.pu.qst.newoabase.plugin.bpplugin.NewoabasePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.qst.newoabase.AggNewoabaseaHeadVO;

/**
 * 修改保存的BP
 * 
 */
public class AceNewoabaseUpdateBP {

	public AggNewoabaseaHeadVO[] update(AggNewoabaseaHeadVO[] bills,
			AggNewoabaseaHeadVO[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggNewoabaseaHeadVO> bp = new UpdateBPTemplate<AggNewoabaseaHeadVO>(
				NewoabasePluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggNewoabaseaHeadVO> processer) {
		// TODO 后规则
		IRule<AggNewoabaseaHeadVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggNewoabaseaHeadVO> processer) {
		// TODO 前规则
		IRule<AggNewoabaseaHeadVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
