package nc.vo.pu.m21.entity;

import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

/**
 * 付款计划
 * 
 * @since 6.0
 * @version 2010-12-31 上午10:13:24
 * @author wuxla
 */

public class PayPlanVO extends AbstractPayPlanVO {

	/** 合同号 */
	public static final String CCONTRACTID = "ccontractid";

	/** 付款计划 */
	public static final String PK_ORDER = "pk_order";

	/** 付款计划 */
	public static final String PK_ORDER_PAYPLAN = "pk_order_payplan";

	/** 质保金 */
	public static final String ISDEPOSIT = "isdeposit";

	private static final long serialVersionUID = -6593181796752582688L;

	/** 预付款 getter 方法 */
	public UFBoolean getBpreflag() {
		return (UFBoolean) this.getAttributeValue(AbstractPayPlanVO.BPREFLAG);
	}

	/** 合同号 getter 方法 */
	public String getCcontractid() {
		return (String) this.getAttributeValue(PayPlanVO.CCONTRACTID);
	}

	/** 本币币种 getter 方法 */
	public String getCcurrencyid() {
		return (String) this.getAttributeValue(AbstractPayPlanVO.CCURRENCYID);
	}

	/** 币种 getter 方法 */
	public String getCorigcurrencyid() {
		return (String) this
				.getAttributeValue(AbstractPayPlanVO.CORIGCURRENCYID);
	}

	/** 行号 getter 方法 */
	public String getCrowno() {
		return (String) this.getAttributeValue(AbstractPayPlanVO.CROWNO);
	}

	/** 起算日期 getter 方法 */
	@Override
	public UFDate getDbegindate() {
		return (UFDate) this.getAttributeValue(AbstractPayPlanVO.DBEGINDATE);
	}

	/** 账期到期日 getter 方法 */
	@Override
	public UFDate getDenddate() {
		return (UFDate) this.getAttributeValue(AbstractPayPlanVO.DENDDATE);
	}

	/** dr getter 方法 */
	public Integer getDr() {
		return (Integer) this.getAttributeValue(AbstractPayPlanVO.DR);
	}

	/** 起算依据 getter 方法 */
	public String getFeffdatetype() {
		return (String) this.getAttributeValue(AbstractPayPlanVO.FEFFDATETYPE);
	}

	/** 账期号 getter 方法 */
	public Integer getIaccounttermno() {
		return (Integer) this
				.getAttributeValue(AbstractPayPlanVO.IACCOUNTTERMNO);
	}

	/** 账期天数 getter 方法 */
	public Integer getIitermdays() {
		return (Integer) this.getAttributeValue(AbstractPayPlanVO.IITERMDAYS);
	}

	@Override
	public IVOMeta getMetaData() {
		return VOMetaFactory.getInstance().getVOMeta(PUEntity.M21_PAYPLAN);
	}

	/** 累计付款申请本币金额 getter 方法 */
	public UFDouble getNaccumpayappmny() {
		return (UFDouble) this
				.getAttributeValue(AbstractPayPlanVO.NACCUMPAYAPPMNY);
	}

	/** 累计付款申请金额 getter 方法 */
	@Override
	public UFDouble getNaccumpayapporgmny() {
		return (UFDouble) this
				.getAttributeValue(AbstractPayPlanVO.NACCUMPAYAPPORGMNY);
	}

	/** 累计付款本币金额 getter 方法 */
	public UFDouble getNaccumpaymny() {
		return (UFDouble) this
				.getAttributeValue(AbstractPayPlanVO.NACCUMPAYMNY);
	}

	/** 累计付款金额 getter 方法 */
	@Override
	public UFDouble getNaccumpayorgmny() {
		return (UFDouble) this
				.getAttributeValue(AbstractPayPlanVO.NACCUMPAYORGMNY);
	}

	/** 折本汇率 getter 方法 */
	public UFDouble getNexchangerate() {
		return (UFDouble) this
				.getAttributeValue(AbstractPayPlanVO.NEXCHANGERATE);
	}

	/** 本币金额 getter 方法 */
	@Override
	public UFDouble getNmny() {
		return (UFDouble) this.getAttributeValue(AbstractPayPlanVO.NMNY);
	}

	/** 原币金额 getter 方法 */
	public UFDouble getNorigmny() {
		return (UFDouble) this.getAttributeValue(AbstractPayPlanVO.NORIGMNY);
	}

	/** 比率 getter 方法 */
	@Override
	public UFDouble getNrate() {
		return (UFDouble) this.getAttributeValue(AbstractPayPlanVO.NRATE);
	}

	/** 总付款金额 getter 方法 */
	public UFDouble getNtotalorigmny() {
		return (UFDouble) this
				.getAttributeValue(AbstractPayPlanVO.NTOTALORIGMNY);
	}

	/** 应付财务组织最新版本 getter 方法 */
	public String getPk_financeorg() {
		return (String) this.getAttributeValue(AbstractPayPlanVO.PK_FINANCEORG);
	}

	/** 应付财务组织 getter 方法 */
	public String getPk_financeorg_v() {
		return (String) this
				.getAttributeValue(AbstractPayPlanVO.PK_FINANCEORG_V);
	}

	/** 所属集团 getter 方法 */
	public String getPk_group() {
		return (String) this.getAttributeValue(AbstractPayPlanVO.PK_GROUP);
	}

	/** 付款计划 getter 方法 */
	public String getPk_order() {
		return (String) this.getAttributeValue(PayPlanVO.PK_ORDER);
	}

	/** 付款计划 getter 方法 */
	public String getPk_order_payplan() {
		return (String) this.getAttributeValue(PayPlanVO.PK_ORDER_PAYPLAN);
	}

	/** 收付款协议账期id getter 方法 */
	public String getPk_paymentch() {
		return (String) this.getAttributeValue(AbstractPayPlanVO.PK_PAYMENTCH);
	}

	/** 收付款协议 getter 方法 */
	public String getPk_payterm() {
		return (String) this.getAttributeValue(AbstractPayPlanVO.PK_PAYTERM);
	}

	/** ts getter 方法 */
	public UFDateTime getTs() {
		return (UFDateTime) this.getAttributeValue(AbstractPayPlanVO.TS);
	}

	/** 预付款 setter 方法 */
	@Override
	public void setBpreflag(UFBoolean bpreflag) {
		this.setAttributeValue(AbstractPayPlanVO.BPREFLAG, bpreflag);
	}

	/** 合同号 setter 方法 */
	public void setCcontractid(String ccontractid) {
		this.setAttributeValue(PayPlanVO.CCONTRACTID, ccontractid);
	}

	/** 本币币种 setter 方法 */
	@Override
	public void setCcurrencyid(String ccurrencyid) {
		this.setAttributeValue(AbstractPayPlanVO.CCURRENCYID, ccurrencyid);
	}

	/** 币种 setter 方法 */
	@Override
	public void setCorigcurrencyid(String corigcurrencyid) {
		this.setAttributeValue(AbstractPayPlanVO.CORIGCURRENCYID,
				corigcurrencyid);
	}

	/** 行号 setter 方法 */
	@Override
	public void setCrowno(String crowno) {
		this.setAttributeValue(AbstractPayPlanVO.CROWNO, crowno);
	}

	/** 起算日期 setter 方法 */
	@Override
	public void setDbegindate(UFDate dbegindate) {
		this.setAttributeValue(AbstractPayPlanVO.DBEGINDATE, dbegindate);
	}

	/** 账期到期日 setter 方法 */
	@Override
	public void setDenddate(UFDate denddate) {
		this.setAttributeValue(AbstractPayPlanVO.DENDDATE, denddate);
	}

	/** dr setter 方法 */
	public void setDr(Integer dr) {
		this.setAttributeValue(AbstractPayPlanVO.DR, dr);
	}

	/** 起算依据 setter 方法 */
	@Override
	public void setFeffdatetype(String feffdatetype) {
		this.setAttributeValue(AbstractPayPlanVO.FEFFDATETYPE, feffdatetype);
	}

	/** 账期号 setter 方法 */
	@Override
	public void setIaccounttermno(Integer iaccounttermno) {
		this.setAttributeValue(AbstractPayPlanVO.IACCOUNTTERMNO, iaccounttermno);
	}

	/** 账期天数 setter 方法 */
	@Override
	public void setIitermdays(Integer itermdays) {
		this.setAttributeValue(AbstractPayPlanVO.IITERMDAYS, itermdays);
	}

	/** 累计付款申请本币金额 setter 方法 */
	public void setNaccumpayappmny(UFDouble naccumpayappmny) {
		this.setAttributeValue(AbstractPayPlanVO.NACCUMPAYAPPMNY,
				naccumpayappmny);
	}

	/** 累计付款申请金额 setter 方法 */
	public void setNaccumpayapporgmny(UFDouble naccumpayapporgmny) {
		this.setAttributeValue(AbstractPayPlanVO.NACCUMPAYAPPORGMNY,
				naccumpayapporgmny);
	}

	/** 累计付款本币金额 setter 方法 */
	public void setNaccumpaymny(UFDouble naccumpaymny) {
		this.setAttributeValue(AbstractPayPlanVO.NACCUMPAYMNY, naccumpaymny);
	}

	/** 累计付款金额 setter 方法 */
	public void setNaccumpayorgmny(UFDouble naccumpayorgmny) {
		this.setAttributeValue(AbstractPayPlanVO.NACCUMPAYORGMNY,
				naccumpayorgmny);
	}

	/** 折本汇率 setter 方法 */
	@Override
	public void setNexchangerate(UFDouble nexchangerate) {
		this.setAttributeValue(AbstractPayPlanVO.NEXCHANGERATE, nexchangerate);
	}

	/** 本币金额 setter 方法 */
	@Override
	public void setNmny(UFDouble mny) {
		this.setAttributeValue(AbstractPayPlanVO.NMNY, mny);
	}

	/** 原币金额 setter 方法 */
	@Override
	public void setNorigmny(UFDouble norigmny) {
		this.setAttributeValue(AbstractPayPlanVO.NORIGMNY, norigmny);
	}

	/** 比率 setter 方法 */
	@Override
	public void setNrate(UFDouble nrate) {
		this.setAttributeValue(AbstractPayPlanVO.NRATE, nrate);
	}

	/** 总付款金额 setter 方法 */
	@Override
	public void setNtotalorigmny(UFDouble ntotalorigmny) {
		this.setAttributeValue(AbstractPayPlanVO.NTOTALORIGMNY, ntotalorigmny);
	}

	/** 应付财务组织最新版本 setter 方法 */
	@Override
	public void setPk_financeorg(String pk_financeorg) {
		this.setAttributeValue(AbstractPayPlanVO.PK_FINANCEORG, pk_financeorg);
	}

	/** 应付财务组织 setter 方法 */
	@Override
	public void setPk_financeorg_v(String pk_financeorg_v) {
		this.setAttributeValue(AbstractPayPlanVO.PK_FINANCEORG_V,
				pk_financeorg_v);
	}

	/** 所属集团 setter 方法 */
	@Override
	public void setPk_group(String pk_group) {
		this.setAttributeValue(AbstractPayPlanVO.PK_GROUP, pk_group);
	}

	/** 付款计划 setter 方法 */
	public void setPk_order(String pk_order) {
		this.setAttributeValue(PayPlanVO.PK_ORDER, pk_order);
	}

	/** 付款计划 setter 方法 */
	public void setPk_order_payplan(String pk_order_payplan) {
		this.setAttributeValue(PayPlanVO.PK_ORDER_PAYPLAN, pk_order_payplan);
	}

	/** 收付款协议 setter 方法 */
	@Override
	public void setPk_payterm(String pk_payterm) {
		this.setAttributeValue(AbstractPayPlanVO.PK_PAYTERM, pk_payterm);
	}

	/** 收付款协议账期id setter 方法 */
	@Override
	public void setPk_paytermch(String pk_paymentch) {
		this.setAttributeValue(AbstractPayPlanVO.PK_PAYMENTCH, pk_paymentch);
	}

	/** ts setter 方法 */
	public void setTs(UFDateTime ts) {
		this.setAttributeValue(AbstractPayPlanVO.TS, ts);
	}

	/** 质保金 setter 方法 */
	public void setIsdeposit(UFBoolean isdeposit) {
		this.setAttributeValue(PayPlanVO.ISDEPOSIT, isdeposit);
	}

	/** 质保金 getter 方法 */
	public UFBoolean getIsdeposit() {
		return (UFBoolean) this.getAttributeValue(PayPlanVO.ISDEPOSIT);
	}
}
