package nc.bs.pu.qst.newoaspwh.ace.bp;

import nc.bs.pu.qst.newoaspwh.plugin.bpplugin.NewoaspwhPluginPoint;
import nc.vo.pu.qst.newoaspwh.AggNewoaspwhaHeadVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * 标准单据删除BP
 */
public class AceNewoaspwhDeleteBP {

	public void delete(AggNewoaspwhaHeadVO[] bills) {

		DeleteBPTemplate<AggNewoaspwhaHeadVO> bp = new DeleteBPTemplate<AggNewoaspwhaHeadVO>(
				NewoaspwhPluginPoint.DELETE);
		// 增加执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 增加执行后业务规则
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggNewoaspwhaHeadVO> processer) {
		// TODO 前规则
		IRule<AggNewoaspwhaHeadVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * 删除后业务规则
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggNewoaspwhaHeadVO> processer) {
		// TODO 后规则

	}
}
