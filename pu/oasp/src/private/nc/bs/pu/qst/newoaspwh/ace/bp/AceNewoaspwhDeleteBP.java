package nc.bs.pu.qst.newoaspwh.ace.bp;

import nc.bs.pu.qst.newoaspwh.plugin.bpplugin.NewoaspwhPluginPoint;
import nc.vo.pu.qst.newoaspwh.AggNewoaspwhaHeadVO;

import nc.impl.pubapp.pattern.data.bill.template.DeleteBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;


/**
 * ��׼����ɾ��BP
 */
public class AceNewoaspwhDeleteBP {

	public void delete(AggNewoaspwhaHeadVO[] bills) {

		DeleteBPTemplate<AggNewoaspwhaHeadVO> bp = new DeleteBPTemplate<AggNewoaspwhaHeadVO>(
				NewoaspwhPluginPoint.DELETE);
		// ����ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ����ִ�к�ҵ�����
		this.addAfterRule(bp.getAroundProcesser());
		bp.delete(bills);
	}

	private void addBeforeRule(AroundProcesser<AggNewoaspwhaHeadVO> processer) {
		// TODO ǰ����
		IRule<AggNewoaspwhaHeadVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillDeleteStatusCheckRule();
		processer.addBeforeRule(rule);
	}

	/**
	 * ɾ����ҵ�����
	 * 
	 * @param processer
	 */
	private void addAfterRule(AroundProcesser<AggNewoaspwhaHeadVO> processer) {
		// TODO �����

	}
}
