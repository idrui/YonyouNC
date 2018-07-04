package nc.bs.pu.qst.newoaspwh.ace.bp;

import nc.bs.pu.qst.newoaspwh.plugin.bpplugin.NewoaspwhPluginPoint;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.qst.newoaspwh.AggNewoaspwhaHeadVO;

/**
 * �޸ı����BP
 * 
 */
public class AceNewoaspwhUpdateBP {

	public AggNewoaspwhaHeadVO[] update(AggNewoaspwhaHeadVO[] bills,
			AggNewoaspwhaHeadVO[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggNewoaspwhaHeadVO> bp = new UpdateBPTemplate<AggNewoaspwhaHeadVO>(
				NewoaspwhPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		return bp.update(bills, originBills);
	}

	private void addAfterRule(CompareAroundProcesser<AggNewoaspwhaHeadVO> processer) {
		// TODO �����
		IRule<AggNewoaspwhaHeadVO> rule = null;

	}

	private void addBeforeRule(CompareAroundProcesser<AggNewoaspwhaHeadVO> processer) {
		// TODO ǰ����
		IRule<AggNewoaspwhaHeadVO> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
	}

}
