package nc.vo.pu.m21.rule;

import nc.itf.pu.m21.IOrderPayPlanData;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.bd.payment.IPaymentUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * @since 6.0
 * @version 2011-1-19 下午02:33:56
 * @author wuxla
 */

public class PayPlanData implements IOrderPayPlanData {

	private static final long serialVersionUID = -7881614001511598581L;

	private String ccurrencyid;

	private String corigcurrencyid;

	private OrderHeaderVO headVO;

	private OrderItemVO itemVO;

	private UFDouble nexchangerate;

	private OrderPaymentVO[] paymentVOs;

	public PayPlanData(OrderVO vo) {
		this.headVO = vo.getHVO();
		this.itemVO = vo.getBVO()[0];
		this.paymentVOs = (OrderPaymentVO[]) vo
				.getChildren(OrderPaymentVO.class);
		this.corigcurrencyid = this.headVO.getCorigcurrencyid();
		String pk_apfinanceorg = this.itemVO.getPk_apfinanceorg();
		this.ccurrencyid = OrgUnitPubService.queryOrgCurrByPk(pk_apfinanceorg);
		this.nexchangerate = this.itemVO.getNexchangerate();
	}

	@Override
	public String getCcurrencyid() {
		return this.ccurrencyid;
	}

	@Override
	public String getCorigcurrencyid() {
		return this.corigcurrencyid;
	}

	@Override
	public UFDate getFeffdatetype(String feffdatetype) {
		// 目前只取订单审核日期
		if (null == feffdatetype) {
			return null;
		}
		if (IPaymentUtil.PURCHASE_ORDER_APPROVE_DATE.equals(feffdatetype)) {
			return this.headVO.getTaudittime();
		}
		return null;
	}

	@Override
	public UFDouble getNexchangerate() {
		return this.nexchangerate;
	}

	@Override
	public UFDouble getNtotalorigmny() {
		return this.headVO.getNtotalorigmny();
	}

	@Override
	public String getPk_fiorg() {
		return this.itemVO.getPk_apfinanceorg();
	}

	@Override
	public String getPk_fiorg_v() {
		return this.itemVO.getPk_apfinanceorg_v();
	}

	@Override
	public String getPk_group() {
		return this.headVO.getPk_group();
	}

	@Override
	public String getPk_payterm() {
		return this.headVO.getPk_payterm();
	}

	@Override
	public OrderPaymentVO[] getPaymentVOs() {
		return this.paymentVOs;
	}

}
