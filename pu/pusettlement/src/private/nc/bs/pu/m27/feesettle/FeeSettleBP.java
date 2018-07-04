package nc.bs.pu.m27.feesettle;

import nc.bs.pu.est.m45.rule.settle.PurchsInSettleUpdateRule;
import nc.bs.pu.m27.feesettle.distribute.FirstDisResult;
import nc.bs.pu.m27.feesettle.distribute.FirstDistributeUtil;
import nc.bs.pu.m27.feesettle.distribute.SecondDistributeUtil;
import nc.bs.pu.m27.feesettle.rule.FeeSettleToAPRule;
import nc.bs.pu.m27.feesettle.rule.PrepareFeeSettleDataRule;
import nc.bs.pu.m27.feesettle.rule.SaveFeeAllotDetailRule;
import nc.bs.pu.m27.feesettle.rule.SettleDetailAndEstInfoSetRule;
import nc.bs.pu.m27.feesettle.rule.WriteNtimesafterfirstRule;
import nc.bs.pu.m27.feesettle.rule.WriteStockSettleNumRule;
import nc.bs.pu.m27.feesettle.util.FeeSettleDataContext;
import nc.bs.pu.m27.plugin.SettlebillPluginPoint;
import nc.bs.pu.m27.settlebill.rule.AuditInfoRule;
import nc.bs.pu.m27.settlebill.rule.BillCodeRule;
import nc.bs.pu.m27.settlebill.rule.FillOrgCurrencyRule;
import nc.bs.pu.m27.settlebill.rule.FillUnitidRule;
import nc.bs.pu.m27.settlebill.rule.OrgInfoFillRule;
import nc.bs.pu.m27.settlebill.rule.RowNoRule;
import nc.bs.pu.m27.settlebill.rule.SettleBillVOCheckRule;
import nc.bs.pu.m27.settlebill.rule.WritebackInvoiceRule;
import nc.bs.pu.m27.settlebill.rule.WritebackPoint;
import nc.bs.pu.m4202.rule.VMISettleUpdateRule;
import nc.bs.pu.m4203.rule.SubcontInSettleUpdateRule;
import nc.bs.pu.m4t.settle.rule.InitialSettleUpdateRule;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>完成费用结算的业务实现
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-6 下午03:14:06
 */
public class FeeSettleBP {
	private boolean bAutoToIA = true;

	private SettleEnvironment envi;

	private StockSettleVO[] stocks;

	public FeeSettleBP(StockSettleVO[] stocks, SettleEnvironment envi) {
		this.envi = envi;
		this.stocks = stocks;
	}

	public SettleBillVO[] doFeeSettle(SettleBillVO[] bills) {
		if (this.envi == null || this.stocks == null) {
			return null;
		}
		if (bills == null || bills.length != 1) {
			return null;
		}
		if (!this.getBOnlyFeeSettle()) {
			// 如果不是费用结算，则是否传IA = 货物结算是否传IA
			this.bAutoToIA = bills[0].getParentVO().getBtoia().booleanValue();
		}
		// 进行费用分摊
		MapList<String, SettleFeeAllotDetailVO> details = null;
		details = this.doDistribute(bills[0]);
		// 把数据放到自定义的Context
		FeeSettleDataContext datactx = new FeeSettleDataContext();
		datactx.setNoneSavedAllotDetails(details);
		datactx.setStockSettleVOMap(CirVOUtil.createKeyVOMap(this.stocks));

		IPluginPoint point = SettlebillPluginPoint.FeeSettleBP;
		AroundProcesser<SettleBillVO> pc = new AroundProcesser<SettleBillVO>(point);

		// 添加BP规则
		this.addBeforeRule(pc);
		this.addAfterRule(pc, datactx);

		pc.before(bills);

		if (this.getBOnlyFeeSettle()) {
			// 如果是费用结算，则需要结算单持久化；否则货物结算时已持久化
			BillInsert<SettleBillVO> bo = new BillInsert<SettleBillVO>();
			bo.insert(bills);
		}
		pc.after(bills);

		return bills;
	}

	private void addAfterRule(AroundProcesser<SettleBillVO> proc,
			FeeSettleDataContext datactx) {

		// 查询费用暂估明细(把数据放到FeeSettleDataContext中去)
		proc.addAfterFinalRule(new PrepareFeeSettleDataRule(true, datactx));
		// 更新费用结算分摊明细一些信息(结算单主键、行主键、第一次费用暂估结算标志、费用所在的结算单行ID等)
		// 回写费用暂估表(暂估费用物料第一次结算的结算单ID、行ID)
		proc.addAfterFinalRule(new SettleDetailAndEstInfoSetRule(true, datactx));

		// 持久化结算分摊明细
		proc.addAfterFinalRule(new SaveFeeAllotDetailRule(datactx));

		// 回写结算分摊明细的后续累计费用结算次数
		proc.addAfterFinalRule(new WriteNtimesafterfirstRule(true));

		// 回写入库单(累计费用结算次数)
		proc.addAfterFinalRule(new WriteStockSettleNumRule(true));

		if (this.getBOnlyFeeSettle()) {
			// 如果不是费用结算，则在货物结算时已处理回写采购发票、费用发票传应付
			proc.addAfterFinalRule(new WritebackInvoiceRule(WritebackPoint.SAVE));
			proc.addAfterFinalRule(new FeeSettleToAPRule());
			// 增加保存后规则：回写采购入财务
			proc.addAfterFinalRule(new PurchsInSettleUpdateRule(WritebackPoint.SAVE));
			// 增加保存后规则：回写消耗汇总财务
			proc.addAfterFinalRule(new VMISettleUpdateRule(WritebackPoint.SAVE));
			// 增加保存后规则：回写委托加工入财务
			proc.addAfterFinalRule(new SubcontInSettleUpdateRule(WritebackPoint.SAVE));
			// 增加保存后规则：回写期初暂估单
			proc.addAfterFinalRule(new InitialSettleUpdateRule(WritebackPoint.SAVE));
		}
		// 传存货核算
		if (this.bAutoToIA && SysInitGroupQuery.isIAEnabled()) {
			proc.addAfterFinalRule(new FeeSettleToIABP(datactx));
			if (SysInitGroupQuery.isPCIAEnabled()) {
				proc.addAfterFinalRule(new FeeSettleToPCIABP(datactx));
			}
		}
	}

	private void addBeforeRule(AroundProcesser<SettleBillVO> proc) {
		if (!this.getBOnlyFeeSettle()) {
			return; // 如果不是费用结算，则在货物结算时以下规则均已处理
		}
		// 增加保存前规则：单据号处理
		proc.addBeforeFinalRule(new BillCodeRule());

		// 增加保存前规则：审计信息
		proc.addBeforeFinalRule(new AuditInfoRule());

		// 增加保存前规则：补全行号
		proc.addBeforeFinalRule(new RowNoRule());

		// 增加保存前规则：财务组织版本信息补充
		proc.addBeforeFinalRule(new OrgInfoFillRule());

		// 补全组织本位币
		proc.addBeforeFinalRule(new FillOrgCurrencyRule());
		// 补全物料主单位
		proc.addBeforeFinalRule(new FillUnitidRule());
		// proc保存前规则：vo校验
		proc.addBeforeFinalRule(new SettleBillVOCheckRule());
	}

	private MapList<String, SettleFeeAllotDetailVO> doDistribute(SettleBillVO bill) {
		// 1、入库单费用之间的分摊(第一次分摊)
		FirstDistributeUtil firstDistribute = new FirstDistributeUtil(bill,
				this.stocks);
		firstDistribute.distribute();
		FirstDisResult[] firstReuslts = firstDistribute.getFirstResults();

		// 2、货物结算单、货物暂估之间的费用的费用分摊(第二次分摊)
		CostfactorViewVO[] factors = firstDistribute.getTotalFactors();
		SecondDistributeUtil secondDistribute = new SecondDistributeUtil(
				this.stocks, firstReuslts, factors);
		// <入库单行ID,相应的费用分摊明细>
		return secondDistribute.distribute();
	}

	private boolean getBOnlyFeeSettle() {
		if (this.envi == null) {
			return true;
		}
		EnumSettleType type = this.envi.getSettleType();
		if (type == EnumSettleType.FEE) {
			return true;
		} else if (type == EnumSettleType.VOI_CONSUME_FEE) {
			return true;
		}
		return false;
	}
}
