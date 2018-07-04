package nc.bs.pu.cgfa.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.VOStatus;

/**
 * 标准单据弃审的BP
 */
public class AceCgfaUnApproveBP {

	public AggCgfa[] unApprove(AggCgfa[] clientBills, AggCgfa[] originBills) {
		for (AggCgfa clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggCgfa> update = new BillUpdate<AggCgfa>();
		AggCgfa[] returnVos = update.update(clientBills, originBills);
		// 为了返回正确的数据做一个特殊处理 王梓懿 2018-06-11
		String[] ids = AggVOUtil.getPrimaryKeys(returnVos);
		AggCgfa[] newVos = new BillQuery<AggCgfa>(AggCgfa.class).query(ids);
		return newVos;
	}
}
