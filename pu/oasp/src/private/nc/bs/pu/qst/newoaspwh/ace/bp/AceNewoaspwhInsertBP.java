package nc.bs.pu.qst.newoaspwh.ace.bp;

import nc.bs.pu.qst.newoaspwh.plugin.bpplugin.NewoaspwhPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.qst.newoaspwh.AggNewoaspwhaHeadVO;

/**
 * 标准单据新增BP
 */
public class AceNewoaspwhInsertBP {

	public AggNewoaspwhaHeadVO[] insert(AggNewoaspwhaHeadVO[] bills) {

		InsertBPTemplate<AggNewoaspwhaHeadVO> bp = new InsertBPTemplate<AggNewoaspwhaHeadVO>(
				NewoaspwhPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		return bp.insert(bills);

	}

	/**
	 * 新增后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggNewoaspwhaHeadVO> processor) {
		// TODO 新增后规则
		IRule<AggNewoaspwhaHeadVO> rule = null;
	}

	/**
	 * 新增前规则
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggNewoaspwhaHeadVO> processer) {
		// TODO 新增前规则
		IRule<AggNewoaspwhaHeadVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
	}
}
