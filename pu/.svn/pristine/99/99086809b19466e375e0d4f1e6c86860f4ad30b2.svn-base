package nc.bs.pu.qst.newoabase.ace.bp;

import nc.bs.pu.qst.newoabase.plugin.bpplugin.NewoabasePluginPoint;
import nc.vo.pu.qst.newoabase.AggNewoabaseaHeadVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceNewoabaseDeleteBP {

	public void delete(AggNewoabaseaHeadVO[] bills) {

		DeleteBPTemplate<AggNewoabaseaHeadVO> bp = new DeleteBPTemplate<AggNewoabaseaHeadVO>(
				NewoabasePluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggNewoabaseaHeadVO> processer) {
		// TODO 前规则
		IRule<AggNewoabaseaHeadVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggNewoabaseaHeadVO> processer) {
		// TODO 后规则

	}
}
