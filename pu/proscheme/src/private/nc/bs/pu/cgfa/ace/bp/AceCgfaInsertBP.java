package nc.bs.pu.cgfa.ace.bp;

import nc.bs.pu.cgfa.ace.rule.CalculateSum;
import nc.bs.pu.cgfa.ace.rule.RewriteForPrayBillRule;
import nc.bs.pu.cgfa.plugin.bpplugin.CgfaPluginPoint;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.template.InsertBPTemplate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.pub.util.AggVOUtil;

/**
 * 标准单据新增BP
 */
public class AceCgfaInsertBP {

	public AggCgfa[] insert(AggCgfa[] bills) {

		InsertBPTemplate<AggCgfa> bp = new InsertBPTemplate<AggCgfa>(
				CgfaPluginPoint.INSERT);
		this.addBeforeRule(bp.getAroundProcesser());
		this.addAfterRule(bp.getAroundProcesser());
		AggCgfa[] insert = bp.insert(bills);
		// 为了返回正确的数据做一个特殊处理 王梓懿 2018-06-11
		String[] ids = AggVOUtil.getPrimaryKeys(insert);
		AggCgfa[] newVos = new BillQuery<AggCgfa>(AggCgfa.class).query(ids);
		return newVos;

	}

	/**
	 * 新增后规则
	 * 
	 * @param processor
	 */
	private void addAfterRule(AroundProcesser<AggCgfa> processor) {
		// TODO 新增后规则
		IRule<AggCgfa> rule = null;
		rule = new nc.bs.pubapp.pub.rule.BillCodeCheckRule();
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCbilltype("JT01");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setCodeItem("billno");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.BillCodeCheckRule) rule).setOrgItem("pk_org");
		processor.addAfterRule(rule);
		// 回写请购单规则
		processor.addAfterFinalRule(new RewriteForPrayBillRule());
	}

	/**
	 * 新增前规则
	 * 
	 * @param processor
	 */
	private void addBeforeRule(AroundProcesser<AggCgfa> processer) {
		// TODO 新增前规则
		IRule<AggCgfa> rule = null;
		rule = new nc.bs.pubapp.pub.rule.FillInsertDataRule();
		processer.addBeforeRule(rule);
		rule = new nc.bs.pubapp.pub.rule.CreateBillCodeRule();
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCbilltype("JT01");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setCodeItem("billno");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule)
				.setGroupItem("pk_group");
		((nc.bs.pubapp.pub.rule.CreateBillCodeRule) rule).setOrgItem("pk_org");
		processer.addBeforeRule(rule);
		// 增加计划项数 modify by wangzy 2017-08-07
		CalculateSum rule2 = new CalculateSum();
		processer.addBeforeRule(rule2);

	}
}
