package nc.bs.pu.cgfa.ace.bp;

import nc.bs.pu.cgfa.ace.rule.CalculateSum;
import nc.bs.pu.cgfa.ace.rule.RewriteForPrayBillUpdateRule;
import nc.bs.pu.cgfa.plugin.bpplugin.CgfaPluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.pub.util.AggVOUtil;

/**
 * �޸ı����BP
 * 
 */
public class AceCgfaUpdateBP {

	public AggCgfa[] update(AggCgfa[] bills, AggCgfa[] originBills) {
		// �����޸�ģ��
		UpdateBPTemplate<AggCgfa> bp = new UpdateBPTemplate<AggCgfa>(
				CgfaPluginPoint.UPDATE);
		// ִ��ǰ����
		this.addBeforeRule(bp.getAroundProcesser());
		// ִ�к����
		this.addAfterRule(bp.getAroundProcesser());
		AggCgfa[] update = bp.update(bills, originBills);
		// Ϊ�˷�����ȷ��������һ�����⴦�� ����ܲ 2018-06-11
		String[] ids = AggVOUtil.getPrimaryKeys(update);
		AggCgfa[] newVos = new BillQuery<AggCgfa>(AggCgfa.class).query(ids);
		return newVos;
	}

	private void addAfterRule(CompareAroundProcesser<AggCgfa> processer) {
		// TODO �����
		IRule<AggCgfa> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("JT01");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCodeItem("billno");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processer.addAfterRule(rule);

		// ��д�빺�� 2017-05-08
		processer.addAfterRule(new RewriteForPrayBillUpdateRule());

	}

	private void addBeforeRule(CompareAroundProcesser<AggCgfa> processer) {
		// TODO ǰ����
		IRule<AggCgfa> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillUpdateDataRule();
		processer.addBeforeRule(rule);
		nc.impl.pubapp.pattern.rule.ICompareRule<AggCgfa> ruleCom = new nc.bs.pubapp.pub.rule.UpdateBillCodeRule();
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCbilltype("JT01");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setCodeItem("billno");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.UpdateBillCodeRule) ruleCom)
				.setOrgItem("pk_org");
		processer.addBeforeRule(ruleCom);
		// ���¼ƻ����� modify by wangzy 2017-08-07 ֮ǰ���ڱ༭���¼�������Ϊ�༭ǰ�Է�ֹ����
		CalculateSum rule2 = new CalculateSum();
		processer.addBeforeRule(rule2);
	}

}
