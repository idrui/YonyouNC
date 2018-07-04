package nc.bs.pu.cgfa.ace.bp;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.BillStatusEnum;

/**
 * ��׼�����ջص�BP
 */
public class AceCgfaUnSendApproveBP {

	public AggCgfa[] unSend(AggCgfa[] clientBills, AggCgfa[] originBills) {
		// ��VO�־û������ݿ���
		this.setHeadVOStatus(clientBills);
		BillUpdate<AggCgfa> update = new BillUpdate<AggCgfa>();
		AggCgfa[] returnVos = update.update(clientBills, originBills);
		// Ϊ�˷�����ȷ��������һ�����⴦�� ����ܲ 2018-06-11
		String[] ids = AggVOUtil.getPrimaryKeys(returnVos);
		AggCgfa[] newVos = new BillQuery<AggCgfa>(AggCgfa.class).query(ids);
		return newVos;
	}

	private void setHeadVOStatus(AggCgfa[] clientBills) {
		for (AggCgfa clientBill : clientBills) {
			clientBill.getParentVO().setAttributeValue(
					"${vmObject.billstatus}", BillStatusEnum.FREE.value());
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
	}
}
