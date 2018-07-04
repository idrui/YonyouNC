package nc.pubitf.pu.m21.cmp.m36d1;

import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/**
 * 资金付款申请单回写订单付款计划参数VO
 * 
 * @since 6.3.1
 * @version 2013-9-17 下午02:40:48
 * @author fanly3
 */
public class OrderPayPlanWriteBackParaFor36D1 {

	/** 付款申请本币金额差异量 */
	private UFDouble diffMny;

	/** 付款申请金额差异量 */
	private UFDouble diffOrigMny;

	/** 订单付款计划PK */
	private String pk_order_payplan;

	/**
	 * 订单付款计划TS
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
