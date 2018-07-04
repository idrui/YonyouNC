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
 * 修改保存的BP
 * 
 */
public class AceCgfaUpdateBP {

	public AggCgfa[] update(AggCgfa[] bills, AggCgfa[] originBills) {
		// 调用修改模板
		UpdateBPTemplate<AggCgfa> bp = new UpdateBPTemplate<AggCgfa>(
				CgfaPluginPoint.UPDATE);
		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());
		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		AggCgfa[] update = bp.update(bills, originBills);
		// 为了返回正确的数据做一个特殊处理 王梓懿 2018-06-11
		String[] ids = AggVOUtil.getPrimaryKeys(update);
		AggCgfa[] newVos = new BillQuery<AggCgfa>(AggCgfa.class).query(ids);
		return newVos;
	}

	private void addAfterRule(CompareAroundProcesser<AggCgfa> processer) {
		// TODO 后规则
		IRule<AggCgfa> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("JT01");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCodeItem("billno");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processer.addAfterRule(rule);

		// 回写请购单 2017-05-08
		processer.addAfterRule(new RewriteForPrayBillUpdateRule());

	}

	private void addBeforeRule(CompareAroundProcesser<AggCgfa> processer) {
		// TODO 前规则
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
		// 更新计划项数 modify by wangzy 2017-08-07 之前放在编辑后事件，改正为编辑前以防止并发
		CalculateSum rule2 = new CalculateSum();
		processer.addBeforeRule(rule2);
	}

}
