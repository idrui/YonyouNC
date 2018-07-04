package nc.bs.pu.qst.newoabase.ace.bp;

import nc.bs.pu.qst.newoabase.plugin.bpplugin.NewoabasePluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.qst.newoabase.AggNewoabaseaHeadVO;

/**
 * 标准单据新增BP
 */
public class AceNewoabaseInsertBP {

	public AggNewoabaseaHeadVO[] insert(AggNewoabaseaHeadVO[] bills) {

		InsertBPTemplate<AggNewoabaseaHeadVO> bp = new InsertBPTemplate<AggNewoabaseaHeadVO>(
				NewoabasePluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * 新增后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggNewoabaseaHeadVO> processor) {
		// TODO 新增后规则
		IRule<AggNewoabaseaHeadVO> rule = null;
	}

	/**
	 * 新增前规则
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggNewoabaseaHeadVO> processer) {
		// TODO 新增前规则
		IRule<AggNewoabaseaHeadVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
