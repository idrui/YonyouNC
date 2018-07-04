/**
 *
 */
package nc.bs.pu.m25.ap.rule;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m25.ap.StockMaterialQualityUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.ic.batch.IBatchGenerateForPU;
import nc.pubitf.scmf.qc.qualitylevel.qc.IQueryQualityLevelForQc;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25trantype.entity.InvcTranTypeVO;
import nc.vo.pu.m25trantype.enumeration.SendControlMode;
import nc.vo.pu.m27.query.SettleBillInfo;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmf.qc.qualitylevel.entity.QualityLevelItemVO;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.MMBillType;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description 交易类型检查规则
 * @scene 传应付
 * @param 无
 * 
 * @since 6.3
 * @version 2014-10-22 下午3:41:57
 * @author zhangshqb
 */
public class SendAPTrantypeChkRule implements IRule<InvoiceVO> {
	// 发票对应的结算信息
	private MapList<String, SettleBillInfo> settleInfo = null;

	public SendAPTrantypeChkRule(MapList<String, SettleBillInfo> settleInfo) {
		this.settleInfo = settleInfo;
	}

	public MapList<String, SettleBillInfo> getSettleInfo() {
		return this.settleInfo;
	}

	@Override
	public void process(InvoiceVO[] vos) {
		if (ArrayUtils.isEmpty(vos)) {
			return;
		}
		// 来源于加工费结算单的采购发票传应付不受交易类型影响
		for (InvoiceVO vo : vos) {
			InvoiceItemVO[] bvos = vo.getChildrenVO();
			if (MMBillType.PscSettle.getCode().equals(bvos[0].getCsourcetypecode())) {
				return;
			}
		}
		Map<String, InvcTranTypeVO> ttcodeMap = InvoiceVOUtil.getTranstype(vos);
		if (MapUtils.isEmpty(ttcodeMap)) {
			ExceptionUtils.wrappBusinessException("Fatal error happended,invoice transtype lost!");/*
																									 * -=
																									 * notranslate
																									 * =
																									 * -
																									 */
		}
		for (InvoiceVO vo : vos) {
			if (this.isConsumePur(vo, ttcodeMap)) {
				continue; // 消耗性采购直接传应付
			}
			if (!this.isCtrlToAP(vo, ttcodeMap)) {
				continue;// 不控制传应付，则直接传
			}
			this.checkSettle(vo, ttcodeMap);// 匹配后才能传应付的控制检查
		}
	}

	public void setSettleInfo(MapList<String, SettleBillInfo> settleInfo) {
		this.settleInfo = settleInfo;
	}

	/**
	 * 检查发票是否完全匹配入库
	 * 
	 * @param vo
	 */
	private void checkMatchStocks(InvoiceVO vo) {
		for (InvoiceItemVO item : vo.getChildrenVO()) {
			List<SettleBillInfo> stockInfo = this.getSettleInfo().get(item.getPk_invoice_b());
			if (stockInfo == null || stockInfo.size() == 0) {
				ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"4004050_0", "04004050-0019")/*
													 * @res "发票未匹配到入库信息，不能传应付！"
													 */);
			}
		}
	}

	/**
	 * 物料质量等级的检查
	 * 
	 * @param vo
	 */
	private void checkQuality(InvoiceVO vo) {
		// 1.检查是否匹配到入库单
		// 2.找出入库单类型为采购入和委外入的入库单（按需求，只检查这两种入库单的物料质量等级是否合格）
		// 3.判断这些入库的物料是否非免检且批次号管理，如果是，查询批次档案是否质量等级合格
		if (vo == null) {
			return;
		}
		// 检查发票是否完全匹配入库
		this.checkMatchStocks(vo);
		// 检查入库物料的质量等级
		this.checkStockMaterialQuality(vo);
	}

	private boolean checkQualityByBatchPK(String[] batchPks) {
		try {
			// 调用批次档案的服务,查询批次的质量等级
			Map<String, String> batchQcLevel = NCLocator.getInstance().lookup(IBatchGenerateForPU.class)
					.queryQualitylevel(batchPks);
			String[] pk_qualitylvs = batchQcLevel.values().toArray(new String[batchQcLevel.size()]);
			// 调用质量等级的服务,查询是否质量等级合格
			Map<String, QualityLevelItemVO> levelItemMap = NCLocator.getInstance()
					.lookup(IQueryQualityLevelForQc.class).queryQualityLevel(pk_qualitylvs);
			// 如果不存在质量等级，视为合格
			if (levelItemMap.size() == 0) {
				return true;
			}
			for (Entry<String, QualityLevelItemVO> itemEntry : levelItemMap.entrySet()) {
				// 质量等级中有不合格的即视为不合格
				if (UFBoolean.FALSE.equals(itemEntry.getValue().getBqualified())) {
					return false;
				}
			}
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return true;
	}

	private void checkSettle(InvoiceVO vo, Map<String, InvcTranTypeVO> ttcodeMap) {
		String ttcode = vo.getParentVO().getVtrantypecode();
		if (!ttcodeMap.containsKey(ttcode)) {
			ExceptionUtils.wrappBusinessException("Fatal error happended,invoice transtype lost!");/*
																									 * -=
																									 * notranslate
																									 * =
																									 * -
																									 */
		}
		// 检查是否结算完毕
		// this.checkSettleFinish(vo);
		InvcTranTypeVO ttvo = ttcodeMap.get(ttcode);
		UFBoolean checkQuality = ttvo.getBcheckquality();
		// 不进行质量合格检查
		if (!ValueUtils.getBoolean(checkQuality)) {
			return;
		}
		// 与发票结算的入库单质量合格检查
		this.checkQuality(vo);
	}

	/**
	 * 检查是否全部结算
	 * 
	 * @param vo
	 */
	private void checkSettleFinish(InvoiceVO vo) {
		for (InvoiceItemVO item : vo.getChildrenVO()) {
			// UFDouble mny = MathTool.nvl(item.getNmny());
			// wuxla v61
			UFDouble mny = MathTool.nvl(item.getNcalcostmny());
			UFDouble settleMny = MathTool.nvl(item.getNaccumsettmny());
			if (!mny.equals(settleMny)) {
				ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"4004050_0", "04004050-0020")/*
													 * @res "采购发票未结算完毕，不允许传应付!"
													 */);
			}
		}
	}

	/**
	 * 检查入库对应的物料是否质量合格
	 * 
	 * @param vo
	 */
	private void checkStockMaterialQuality(InvoiceVO vo) {
		// 查询采购入库物料批次
		String[] batchPkFor45 = this.queryStockMaterialBatchCode(vo, ICBillType.PurchaseIn.getCode());
		// 查询委托加工入库物料批次
		String[] batchPkFor47 = this.queryStockMaterialBatchCode(vo, ICBillType.SubContinIn.getCode());
		String[] batchPks = ArrayUtil.combinArrays(batchPkFor45, batchPkFor47);
		// 没找到批次管理且非免检的物料，返回
		if (ArrayUtils.isEmpty(batchPks)) {
			return;
		}
		if (!this.checkQualityByBatchPK(batchPks)) {
			ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
					"04004050-0021")/*
									 * @res "发票对应的入库单物料质量检查不合格，不允许传应付!"
									 */);
		}
	}

	/**
	 * 是否消耗性采购
	 * 
	 * @param vo
	 * @param ttcodeMap
	 * @return
	 */
	private boolean isConsumePur(InvoiceVO vo, Map<String, InvcTranTypeVO> ttcodeMap) {
		String ttcode = vo.getParentVO().getVtrantypecode();
		if (!ttcodeMap.containsKey(ttcode)) {
			ExceptionUtils.wrappBusinessException("Fatal error happended,invoice transtype lost!");/*
																									 * -=
																									 * notranslate
																									 * =
																									 * -
																									 */
		}
		UFBoolean consume = ttcodeMap.get(ttcode).getBconsumepur();
		return ValueUtils.getBoolean(consume);
	}

	/**
	 * 传应付是否按入库匹配控制
	 * 
	 * @param vo
	 * @param ttcodeMap
	 * @return
	 */
	private boolean isCtrlToAP(InvoiceVO vo, Map<String, InvcTranTypeVO> ttcodeMap) {
		String ttcode = vo.getParentVO().getVtrantypecode();
		if (!ttcodeMap.containsKey(ttcode)) {
			ExceptionUtils.wrappBusinessException("Fatal error happended,invoice transtype lost!");/*
																									 * -=
																									 * notranslate
																									 * =
																									 * -
																									 */
		}
		Integer toapmode = ttcodeMap.get(ttcode).getItoarapmode();
		if (SendControlMode.MODE_NO_CTRL.value().equals(toapmode)) {
			return false; // 不控制
		}
		return true;// 匹配后传应付
	}

	/**
	 * 查询与发票匹配的入库单的物料批次信息，这些物料属非免检且批次管理的物料。
	 * 
	 * @param vo
	 *            发票vo
	 * @param billType
	 *            入库类型
	 * @return 入库物料对应的批次
	 */
	private String[] queryStockMaterialBatchCode(InvoiceVO vo, String billType) {
		// 入库行id
		Set<String> stockbidSet = new HashSet<String>();
		// 物料批次
		Set<String> batchCodePKSet = new HashSet<String>();
		for (InvoiceItemVO item : vo.getChildrenVO()) {
			for (SettleBillInfo info : this.getSettleInfo().get(item.getPk_invoice_b())) {
				if (info.getVstockbilltype().equals(billType)) {
					stockbidSet.add(info.getPk_stock_b());
				}
			}
		}
		StockMaterialQualityUtil util = new StockMaterialQualityUtil();
		if (stockbidSet.size() > 0) {
			String[] bids = stockbidSet.toArray(new String[stockbidSet.size()]);
			// 查询非免检且批次管理的物料的批次主键
			Map<String, String> idMap = util.queryQualityMaterial(bids, billType);
			if (idMap != null) {
				for (String bid : bids) {
					String pk_batchcode = idMap.get(bid);
					if (pk_batchcode != null) {
						batchCodePKSet.add(pk_batchcode);
					}
				}
			}
		}
		return batchCodePKSet.toArray(new String[batchCodePKSet.size()]);
	}

}
