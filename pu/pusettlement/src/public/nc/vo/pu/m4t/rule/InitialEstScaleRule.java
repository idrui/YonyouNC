package nc.vo.pu.m4t.rule;

import nc.ui.pub.bill.IBillItem;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.pub.rule.IScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;

/**
 * vo基本精度处理类
 * 
 * @since 6.0
 * @version 2012-6-14 上午10:29:26
 * @author tianft
 */
public class InitialEstScaleRule implements IScaleProcessor {

	@Override
	public void setScale(BillScaleProcessor scale, TotalValueScale totalScale) {
		this.setScale(scale, PosEnum.body);

		if (totalScale != null) {
			this.setHeadScale(totalScale);
		}
	}

	/**
	 * 精度检查用
	 * 
	 * @param scale
	 */
	public void setScaleForCheck(BillScaleProcessor scale) {
		this.setScale(scale, PosEnum.body, true);
	}

	@Override
	public void setScaleForSingleTable(BillScaleProcessor scale,
			TotalValueScale totalScale) {
		this.setScale(scale, PosEnum.head);
		if (totalScale != null) {
			this.setHeadScale(totalScale);
		}
	}

	private void setHeadScale(TotalValueScale scale) {
		scale.setHeadTailKeys(new String[] { InitialEstHeaderVO.NTOTALASTNUM });
	}

	private void setOrgExchange(BillScaleProcessor scale) {
		FieldInfo rate = new FieldInfo(InitialEstHeaderVO.NEXCHANGERATE,
				IBillItem.HEAD, null);
		FieldInfo srcCurr = new FieldInfo(InitialEstHeaderVO.CORIGCURRENCYID,
				IBillItem.HEAD, null);
		FieldInfo destCurr = new FieldInfo(InitialEstHeaderVO.CCURRENCYID,
				IBillItem.HEAD, null);
		FieldInfo org = new FieldInfo(InitialEstHeaderVO.PK_ORG, IBillItem.HEAD,
				null);
		scale.setOrgExchangeCtlInfo(rate, srcCurr, destCurr, org);
	}

	private void setScale(BillScaleProcessor scale, PosEnum posEnum) {
		this.setScale(scale, posEnum, false);
	}

	private void setScale(BillScaleProcessor scale, PosEnum posEnum,
			boolean forScaleCheck) {
		// 换算率
		String[] changeRates = new String[] { InitialEstItemVO.VCHANGERATE };
		// 本币金额
		String[] mnykeys = new String[] { InitialEstItemVO.NMNY,
				InitialEstItemVO.NTAXMNY, InitialEstItemVO.NTAX,
				InitialEstItemVO.NNOSUBTAX, InitialEstItemVO.NCALTAXMNY,
				InitialEstItemVO.NCALCOSTMNY };
		// 表头合计金额－根据公共需求2011.9.7走模板精度
		String[] headMnykeys = new String[] { InitialEstHeaderVO.NTOTALORIGMNY };
		String[] assistNumkeys = new String[] { InitialEstItemVO.NASTNUM };
		// 主数量
		String[] numkeys = new String[] { InitialEstItemVO.NACCINVOICENUM,
				InitialEstItemVO.NACCSETTLENUM, InitialEstItemVO.NNUM, };
		// 本币价格
		String[] CurrPricekeys = new String[] { InitialEstItemVO.NASTPRICE,
				InitialEstItemVO.NASTTAXPRICE, InitialEstItemVO.NPRICE,
				InitialEstItemVO.NTAXPRICE, InitialEstItemVO.NESTCALCOSTPRICE };
		// 原币价格
		String[] OcurrPricekeys = new String[] { InitialEstItemVO.NASTORIGPRICE,
				InitialEstItemVO.NASTORIGTAXPRICE, InitialEstItemVO.NORIGPRICE,
				InitialEstItemVO.NORIGTAXPRICE };
		// 原币金额
		String[] orgmnykeys = new String[] { InitialEstItemVO.NORIGMNY,
				InitialEstItemVO.NORIGTAXMNY, InitialEstItemVO.NACCWASHMNY };
		// 表体税率
		String[] taxRateKey_B = new String[] { InitialEstItemVO.NTAXRATE,
				InitialEstItemVO.NNOSUBTAXRATE };
		// 非精度检查时
		if (!forScaleCheck) {
			// 表体税率
			scale.setTaxRateCtlInfo(taxRateKey_B, posEnum, null);
			// 换算率精度
			scale.setHslCtlInfo(changeRates, posEnum, null);

			// 折本汇率
			this.setOrgExchange(scale);

			// 表头合计金额－根据公共需求2011.9.7走模板精度
			// 表头合计数量的精度钟老大和孙宝前已经确定抹零处理（金额合计一般都会按币种处理，特殊需要抹零的请各个确认）。
			scale.setMnyCtlInfo(headMnykeys, PosEnum.head, null,
					InitialEstHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
		}
		// 业务单位数量精度
		scale.setNumCtlInfo(assistNumkeys, posEnum, null,
				InitialEstItemVO.CASTUNITID, posEnum, null);
		// 主单位数量精度
		scale.setNumCtlInfo(numkeys, posEnum, null, InitialEstItemVO.CUNITID,
				posEnum, null);
		// 本币金额精度
		scale.setMnyCtlInfo(mnykeys, posEnum, null, InitialEstHeaderVO.CCURRENCYID,
				PosEnum.head, null);

		// 本币单价精度
		scale.setPriceCtlInfo(CurrPricekeys, posEnum, null,
				InitialEstHeaderVO.CCURRENCYID, PosEnum.head, null);
		// 原币单价精度
		scale.setPriceCtlInfo(OcurrPricekeys, posEnum, null,
				InitialEstHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
		// 原币金额精度
		scale.setMnyCtlInfo(orgmnykeys, posEnum, null,
				InitialEstHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
		// 进行计算
		scale.process();
	}
}
