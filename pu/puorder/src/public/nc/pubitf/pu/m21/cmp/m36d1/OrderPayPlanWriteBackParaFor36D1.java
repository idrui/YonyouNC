package nc.pubitf.pu.m21.cmp.m36d1;

import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/**
 * �ʽ𸶿����뵥��д��������ƻ�����VO
 * 
 * @since 6.3.1
 * @version 2013-9-17 ����02:40:48
 * @author fanly3
 */
public class OrderPayPlanWriteBackParaFor36D1 {

	/** �������뱾�ҽ������� */
	private UFDouble diffMny;

	/** ��������������� */
	private UFDouble diffOrigMny;

	/** ��������ƻ�PK */
	private String pk_order_payplan;

	/**
	 * ��������ƻ�TS
	 */
	private UFDateTime ts;

	public void setTs(UFDateTime ufDateTime) {
		this.ts = ufDateTime;
	}

	public UFDateTime getTs() {
		return ts;
	}

	public UFDouble getDiffMny() {
		return this.diffMny;
	}

	public UFDouble getDiffOrigMny() {
		return this.diffOrigMny;
	}

	public String getPk_order_payplan() {
		return this.pk_order_payplan;
	}

	public void setDiffMny(UFDouble diffMny) {
		this.diffMny = diffMny;
	}

	public void setDiffOrigMny(UFDouble diffOrigMny) {
		this.diffOrigMny = diffOrigMny;
	}

	public void setPk_order_payplan(String pk_order_payplan) {
		this.pk_order_payplan = pk_order_payplan;
	}
}
