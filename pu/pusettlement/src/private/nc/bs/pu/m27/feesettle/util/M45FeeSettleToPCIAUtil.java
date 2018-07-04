package nc.bs.pu.m27.feesettle.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.pu.reference.pcia.PCIAServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pcia.m4639.po.P4639AdjustBackData;
import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购入库单进行费用结算时传存货核算
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-12 下午01:53:24
 */
public class M45FeeSettleToPCIAUtil extends AbstractToPCIAUtil {

	public M45FeeSettleToPCIAUtil(SettleBillHeaderVO header,
			Collection<SettleBillItemVO> items) {
		super(header, items);
	}

	@Override
	public void toPCIA(FeeSettleDataContext datactx) {
		if (this.items == null || this.items.size() == 0 || datactx == null) {
			return;
		}
		this.ssVOMap = datactx.getStockSettleVOMap();
		List<P4639AdjustBackData> rushDatas = new ArrayList<P4639AdjustBackData>();
		List<SettleBillItemVO> toP4639Datas = new ArrayList<SettleBillItemVO>();
		Set<SettleFeeAllotDetailVO> allAllotVOLst = new HashSet<SettleFeeAllotDetailVO>();

		PUParaValue.po12 po12 = this.getParaPO12();// 得到暂估调整方式

		FeeSettleEstMatchUtil matchUtil = new FeeSettleEstMatchUtil(datactx);
		for (SettleBillItemVO item : this.items) {
			matchUtil.match(item);
			// 存在费用暂估
			List<FeeSettleEstMapping> mappings = matchUtil.getFeeSettleEstMappings();
			if (mappings != null && mappings.size() != 0) {
				// 如果是回冲，则按暂估数据准备回冲数据，生成回冲单、正式存货入库单，即暂估回冲传成本P4639。
				if (po12.equals(PUParaValue.po12.rush)) {
					List<P4639AdjustBackData> p4639Data = this.getRushToP4639Data(item,
							mappings);
					if (p4639Data != null) {
						rushDatas.addAll(p4639Data);
					}
					this.genToPCIAAdjBillStlItems(item, toP4639Datas,
							this.getSettleFeeAllot(mappings), true);
				} else {
					// 费用暂估后进行调差
					this.genEstToIAAdjBillStlItems(item, toP4639Datas, mappings);
				}
				allAllotVOLst.addAll(this.getSettleFeeAllot(mappings));
			}
			// 不存在费用暂估
			List<SettleFeeAllotDetailVO> noneEstBbvos = matchUtil.getNoneEstBbvos();
			if (noneEstBbvos != null && noneEstBbvos.size() != 0) {
				this.genToPCIAAdjBillStlItems(item, toP4639Datas, noneEstBbvos, false);
				allAllotVOLst.addAll(noneEstBbvos);
			}
		}
		// 费用暂估回冲
		P4639AdjustBackData[] torushdata = rushDatas
				.toArray(new P4639AdjustBackData[rushDatas.size()]);
		// mengjian by 20141021 启用利润中心存货核算时
		if (SysInitGroupQuery.isPCIAEnabled()) {
			PCIAServices.insertI9ForPOFeeSettleAdjustBack(torushdata);
		}
		// 将P4639、IG数据传存货
		this.submitToPCIA(toP4639Datas, allAllotVOLst);
	}

	private List<P4639AdjustBackData> combineP4639AdjustData(
			List<P4639AdjustBackData> p4639adjdataLst) {
		Map<String, P4639AdjustBackData> srcbAdjMap = new HashMap<String, P4639AdjustBackData>();
		for (P4639AdjustBackData adjData : p4639adjdataLst) {
			// 暂估时传P4639的行ID进行合并，保证不产生过多的单据
			String srcbid = adjData.getCapportionid();
			P4639AdjustBackData sumAdjData = srcbAdjMap.get(srcbid);
			if (null != sumAdjData) {
				UFDouble mny = sumAdjData.getNmny();
				sumAdjData.setNmny(MathTool.add(mny, adjData.getNmny()));
				UFDouble factor = sumAdjData.getNfactor1();
				sumAdjData.setNfactor1(MathTool.add(factor, adjData.getNfactor1()));
				factor = sumAdjData.getNfactor2();
				sumAdjData.setNfactor2(MathTool.add(factor, adjData.getNfactor2()));
				factor = sumAdjData.getNfactor3();
				sumAdjData.setNfactor3(MathTool.add(factor, adjData.getNfactor3()));
				factor = sumAdjData.getNfactor4();
				sumAdjData.setNfactor4(MathTool.add(factor, adjData.getNfactor4()));
				factor = sumAdjData.getNfactor5();
				sumAdjData.setNfactor5(MathTool.add(factor, adjData.getNfactor5()));
				factor = sumAdjData.getNfactor6();
				sumAdjData.setNfactor6(MathTool.add(factor, adjData.getNfactor6()));
				factor = sumAdjData.getNfactor7();
				sumAdjData.setNfactor7(MathTool.add(factor, adjData.getNfactor7()));
				factor = sumAdjData.getNfactor8();
				sumAdjData.setNfactor8(MathTool.add(factor, adjData.getNfactor8()));
			} else {
				srcbAdjMap.put(srcbid, adjData);
			}
		}
		return new ArrayList<P4639AdjustBackData>(srcbAdjMap.values());
	}

	private void genEstToIAAdjBillStlItems(SettleBillItemVO item,
			List<SettleBillItemVO> toP4639Lst, List<FeeSettleEstMapping> mappings) {
		// 存在费用暂估时，进行调差处理
		if (item == null || mappings == null || mappings.size() == 0) {
			return;
		}
		for (int i = 0, len = mappings.size(); i < len; i++) {
			if (!UFBoolean.TRUE.equals(mappings.get(i).getFeeestvo().getBtoia())) {
				continue;
			}
			// 调整金额 = 费用暂估金额-费用结算金额
			UFDouble estmny = mappings.get(i).getEstallotvo().getNdistmny();
			SettleFeeAllotDetailVO settleallotvo = mappings.get(i).getSettleallotvo();
			UFDouble settlemny = settleallotvo.getNallotmoney();
			SettleBillItemVO newItem = this.getToPCIAStlItem(item,
					MathTool.sub(settlemny, estmny), settleallotvo);
			if (null == newItem) {
				continue;
			}
			if (PUParaValue.po13.cost.equals(this.getParaPO13())) {
				toP4639Lst.add(newItem);
			}
		}
	}

	private PUParaValue.po12 getParaPO12() {
		// 参数PO12(暂估处理方式)
		String org = this.header.getPk_org();
		if (StringUtils.isEmpty(org)) {
			String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
					"4004060_0", "04004060-0068")/*
																				 * @res "入库单/消耗汇总进行费用结算取参数时，出现主组织为空错误！"
																				 */;
			ExceptionUtils.wrappBusinessException(msg);
			return null;
		}
		return PUSysParamUtil.getPO12(org);
	}

	private List<P4639AdjustBackData> getRushToP4639Data(SettleBillItemVO item,
			List<FeeSettleEstMapping> mappings) {
		// 存在费用暂估，需要进行回冲费用暂估时使用
		if (item == null || mappings == null || mappings.size() == 0) {
			return null;
		}
		String pk_stock_b = item.getPk_stock_b();
		StockSettleVO stockSettleVO = this.ssVOMap.get(pk_stock_b);
		UFBoolean baffectpciacost = stockSettleVO.getBaffectpciacost();
		if (baffectpciacost == null || !baffectpciacost.booleanValue()) {
			return null;
		}
		// 使用P4639的调整vo类，基于费用分摊明细进行回冲
		List<P4639AdjustBackData> p4639bills = new ArrayList<P4639AdjustBackData>();
		for (FeeSettleEstMapping mapping : mappings) {
			if (!UFBoolean.TRUE.equals(mapping.getFeeestvo().getBtoia())) {
				continue;
			}
			SettleFeeAllotDetailVO settleallotvo = mapping.getSettleallotvo();
			FeeEstDistVO estallotvo = mapping.getEstallotvo();
			P4639AdjustBackData voRush = new P4639AdjustBackData();
			voRush.setVsettlecode(this.header.getVbillcode());
			voRush.setCsrcid(item.getPk_settlebill());
			voRush.setCsrcbid(settleallotvo.getPk_settle_feeallot());
			voRush.setVsettlerowno(item.getCrowno());
			voRush.setCicid(item.getPk_stock());// 暂估费用时的P4639来源ID（即采购入ID）
			voRush.setCapportionid(estallotvo.getPk_iasrc_b());// 暂估费用时的P4639来源行ID
			voRush.setNmny(estallotvo.getNdistmny());// 费用分摊金额

			// by 20141217 mengjian 取不到发票日期，取业务日期
      UFDate date = item.getInvoicebilldate();
      if(date == null){
        date = AppContext.getInstance().getBusiDate();
      }
      voRush.setDaccountdate(date);

			CostfactorViewVO cfVo = settleallotvo.getCostfactorvo();
			Integer cfNo = cfVo.getIfactororder();
			if (Integer.valueOf(1).equals(cfNo)) {
				voRush.setNfactor1(estallotvo.getNdistmny());
			} else if (Integer.valueOf(2).equals(cfNo)) {
				voRush.setNfactor2(estallotvo.getNdistmny());
			} else if (Integer.valueOf(3).equals(cfNo)) {
				voRush.setNfactor3(estallotvo.getNdistmny());
			} else if (Integer.valueOf(4).equals(cfNo)) {
				voRush.setNfactor4(estallotvo.getNdistmny());
			} else if (Integer.valueOf(5).equals(cfNo)) {
				voRush.setNfactor5(estallotvo.getNdistmny());
			} else if (Integer.valueOf(6).equals(cfNo)) {
				voRush.setNfactor6(estallotvo.getNdistmny());
			} else if (Integer.valueOf(7).equals(cfNo)) {
				voRush.setNfactor7(estallotvo.getNdistmny());
			} else if (Integer.valueOf(8).equals(cfNo)) {
				voRush.setNfactor8(estallotvo.getNdistmny());
			}
			p4639bills.add(voRush);
		}
		// 根据暂估时传P4639的行ID进行合并，保证不产生过多的回冲单据
		p4639bills = this.combineP4639AdjustData(p4639bills);
		return p4639bills;
	}

	private List<SettleFeeAllotDetailVO> getSettleFeeAllot(
			List<FeeSettleEstMapping> mappings) {
		List<SettleFeeAllotDetailVO> bbs = new ArrayList<SettleFeeAllotDetailVO>();
		for (FeeSettleEstMapping mapping : mappings) {
			SettleFeeAllotDetailVO settleallotvo = mapping.getSettleallotvo();
			bbs.add(settleallotvo);
		}
		return bbs;
	}

	@Override
	protected String getSourceTypeForVOChg() {
		return POBillType.PurchaseInSettleBill.getCode();
	}
}
