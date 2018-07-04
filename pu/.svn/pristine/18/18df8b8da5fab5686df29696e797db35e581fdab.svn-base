package nc.vo.pu.m20.rule.margin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.et.m5720.entity.ContractBVO;
import nc.vo.ewm.workorder.WOPlanInVVO;
import nc.vo.mmpac.dmo.entity.DmoVO;
import nc.vo.mmpac.pmo.pac0002.entity.PMOItemVO;
import nc.vo.mmpps.mps0202.PoVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.margin.AbstractPuMarginProcessFactory;
import nc.vo.pu.margin.DefaultPuMarginCondition;
import nc.vo.pu.margin.DefaultPuMarginProcess;
import nc.vo.pu.margin.DefaultPuMarginVOProc;
import nc.vo.pu.margin.IPuMarginCondition;
import nc.vo.pu.margin.IPuMarginProcess;
import nc.vo.pu.margin.IPuMarginVOProcess;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.scmpub.res.billtype.ETBillType;
import nc.vo.scmpub.res.billtype.EWMBillType;
import nc.vo.scmpub.res.billtype.INVPBillType;
import nc.vo.scmpub.res.billtype.MMBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.to.m5x.entity.BillItemVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 请购单尾差处理器生成工厂
 * 
 * @since 6.0
 * @version 2012-4-22 下午8:35:06
 * @author zhaoyha
 */
public class PrayBillMarginProcessFactory extends
		AbstractPuMarginProcessFactory {

	public PrayBillMarginProcessFactory(String srcBilltype,
			AggregatedValueObject[] curBillVos,
			AggregatedValueObject[] srcBillVos) {
		super(srcBilltype, curBillVos, srcBillVos);
	}

	private List<IPuMarginProcess> createMarginProcess(boolean isFirstTime) {
		// 设置当前要倒挤的行VO
		this.setCurItemVos((SuperVO[]) AggVOUtil.getCombinItemVOs(this
				.getCurBillVos()));
		List<IPuMarginProcess> list = new ArrayList<IPuMarginProcess>();
		if (MMBillType.PlanOrder.getCode().equals(this.getSrcBilltype())) {
			// 计划订单
			this.setSrcItemVoBySrcBillHead();
			MarginParam param = this.createSrc55B4();
			list.add(this.createSrcForCommon(isFirstTime, param));
		} else if (MMBillType.ProduceOrder.getCode().equals(
				this.getSrcBilltype())) {
			// 生产订单
			this.setSrcItemVoBySrcBillHead();
			MarginParam param = this.getParamFor55A2();
			list.add(this.createSrcForCommon(isFirstTime, param));
		} else if (MMBillType.LsProduceOrder.getCode().equals(
				this.getSrcBilltype())) {
			// 离散生产订单
			this.setSrcItemVoBySrcBillHead();
			MarginParam param = this.getParamFor55C2();
			list.add(this.createSrcForCommon(isFirstTime, param));
		} else if (TOBillType.TransOrder.getCode()
				.equals(this.getSrcBilltype())) {
			// 调拨订单
			this.setSrcItemVoBySrcBillBody();
			MarginParam param = this.getParamFor5X();
			list.add(this.createSrcForCommon(isFirstTime, param));
		} else if (SOBillType.Order.getCode().equals(this.getSrcBilltype())) {
			// 销售订单
			this.setSrcItemVoBySrcBillBody();
			MarginParam param = this.getParamFor30();
			list.add(this.createSrcForCommon(isFirstTime, param));
		} else if (EWMBillType.WorkOrder.getCode()
				.equals(this.getSrcBilltype())) {
			// 工单4b36
			this.setSrcItemVoBySrcBillBody();
			MarginParam param = this.getParamFor4B36();
			list.add(this.createSrcForCommon(isFirstTime, param));
		} else if (POBillType.MRBill.getCode().equals(this.getSrcBilltype())) {
			// 物资需求申请单
			this.setSrcItemVoBySrcBillBody();
			MarginParam param = this.getParamFor422x();
			list.add(this.createSrcForCommon(isFirstTime, param));
		} else if (INVPBillType.PoOrder.getCode().equals(this.getSrcBilltype())) {
			// 库存计划
			this.setSrcItemVoBySrcBillHead();
			MarginParam param = this.getParamFor4F();
			list.add(this.createSrcForCommon(isFirstTime, param));
		} else if (ETBillType.CONTRACT.getCode().equals(this.getSrcBilltype())) {
			// 出口合同
			this.setSrcItemVoBySrcBillBody();
			MarginParam param = this.getParamFor5720();
			list.add(this.createSrcForCommon(isFirstTime, param));
		}

		return list;
	}

	private MarginParam createSrc55B4() {
		MarginParam param = new MarginParam();

		param.setSrcNum(PoVO.NNUMBER);
		param.setSrcCastunitid(PoVO.CASSMEASUREID);
		param.setSrcVChangeRate(PoVO.VCHANGERATE);
		param.setSrcNastNum(PoVO.NASTNUM);
		param.setSrcId(PoVO.CPOID);
		param.setSrcItemclazz(PoVO.class);
		return param;
	}

	private IPuMarginProcess createSrcForCommon(boolean isFirstTime,
			MarginParam param) {
		// 创建比较条件
		IPuMarginCondition cond = new DefaultPuMarginCondition(
				this.getCurSrcCommomCondJudgeField(param),
				this.getCurSrcCommonMarginField(param), param.getSrcNum());
		// 查询上游计划订单表头VO（计划订单数量在表头）
		List<IPuMarginCondition> condList = new ArrayList<IPuMarginCondition>();
		condList.add(cond);
		if (ArrayUtils.isEmpty(this.getSrcItemVos())) {
			this.querySrcItemVO(param.getSrcItemclazz(), condList,
					param.getSrcNum(), PraybillItemVO.CSOURCEBID);
		}
		// 做多次转单倒挤时才需要查询下游兄弟VO
		if (!isFirstTime && ArrayUtils.isEmpty(this.getCurSiblingVos())) {
			this.queryCurSiblingVO(PraybillItemVO.class, condList,
					PraybillItemVO.NNUM, PraybillItemVO.CSOURCEBID,
					PraybillItemVO.CSOURCEID);
		}
		// 创建VO加工器
		IPuMarginVOProcess voproc = new DefaultPuMarginVOProc(
				PraybillItemVO.CSOURCEBID, PraybillItemVO.CSOURCEID,
				param.getSrcId());
		return new DefaultPuMarginProcess(cond, voproc, this.getCurItemVos(),
				this.getCurSiblingVos(), this.getSrcItemVos());

	}

	private Map<String, String> getCurSrcCommomCondJudgeField(MarginParam param) {
		Map<String, String> curSrcCondJudgeField = new HashMap<String, String>();
		curSrcCondJudgeField.put(PraybillItemVO.CASTUNITID,
				param.getSrcCastunitid());
		return curSrcCondJudgeField;
	}

	private Map<String, String> getCurSrcCommonMarginField(MarginParam param) {
		Map<String, String> curSrcMarginField = new HashMap<String, String>();
		curSrcMarginField.put(PraybillItemVO.NASTNUM, param.getSrcNastNum());
		return curSrcMarginField;
	}

	private MarginParam getParamFor30() {
		MarginParam param = new MarginParam();

		param.setSrcNum(SaleOrderBVO.NNUM);
		param.setSrcCastunitid(SaleOrderBVO.CASTUNITID);
		param.setSrcVChangeRate(SaleOrderBVO.VCHANGERATE);
		param.setSrcNastNum(SaleOrderBVO.NASTNUM);
		param.setSrcId(SaleOrderBVO.CSALEORDERBID);

		param.setSrcItemclazz(SaleOrderBVO.class);
		return param;
	}

	private MarginParam getParamFor422x() {
		MarginParam param = new MarginParam();
		param.setSrcNum(StoreReqAppItemVO.NNUM);
		param.setSrcCastunitid(StoreReqAppItemVO.CASTUNITID);
		param.setSrcVChangeRate(StoreReqAppItemVO.VCHANGERATE);
		param.setSrcNastNum(StoreReqAppItemVO.NASTNUM);
		param.setSrcId(StoreReqAppItemVO.PK_STOREREQ_B);
		param.setSrcItemclazz(StoreReqAppItemVO.class);
		return param;
	}

	private MarginParam getParamFor5720() {
		MarginParam param = new MarginParam();
		param.setSrcNum(ContractBVO.NNUM);
		param.setSrcCastunitid(ContractBVO.CASTUNITID);
		param.setSrcVChangeRate(ContractBVO.VCHANGERATE);
		param.setSrcNastNum(ContractBVO.NASTNUM);
		param.setSrcId(ContractBVO.PK_CONTRACT_B);
		param.setSrcItemclazz(ContractBVO.class);
		return param;
	}

	private MarginParam getParamFor4B36() {
		MarginParam param = new MarginParam();
		param.setSrcNum(WOPlanInVVO.NNUM);
		param.setSrcCastunitid(WOPlanInVVO.CASTUNITID);
		param.setSrcVChangeRate(WOPlanInVVO.VCHANGERATE);
		param.setSrcNastNum(WOPlanInVVO.NASSISTNUM);
		param.setSrcId(WOPlanInVVO.PK_WO_PLAN_INV);

		param.setSrcItemclazz(WOPlanInVVO.class);
		return param;
	}

	private MarginParam getParamFor4F() {
		MarginParam param = new MarginParam();

		param.setSrcNum(nc.vo.invp.po.entity.PoVO.NNUM);
		param.setSrcCastunitid(nc.vo.invp.po.entity.PoVO.CASTUNITID);
		param.setSrcVChangeRate(nc.vo.invp.po.entity.PoVO.VCHANGERATE);
		param.setSrcNastNum(nc.vo.invp.po.entity.PoVO.NASTNUM);
		param.setSrcId(nc.vo.invp.po.entity.PoVO.PK_PO);
		param.setSrcItemclazz(nc.vo.invp.po.entity.PoVO.class);
		return param;
	}

	private MarginParam getParamFor55A2() {
		MarginParam param = new MarginParam();
		param.setSrcNum(PMOItemVO.NNUM);
		param.setSrcCastunitid(PMOItemVO.CASTUNITID);
		param.setSrcVChangeRate(PMOItemVO.VCHANGERATE);
		param.setSrcNastNum(PMOItemVO.NASTNUM);
		param.setSrcId(PMOItemVO.CMOID);
		param.setSrcItemclazz(PMOItemVO.class);
		return param;
	}

	private MarginParam getParamFor55C2() {
		MarginParam param = new MarginParam();
		param.setSrcNum(DmoVO.NNUM);
		param.setSrcCastunitid(DmoVO.CASTUNITID);
		param.setSrcVChangeRate(DmoVO.VCHANGERATE);
		param.setSrcNastNum(DmoVO.NASTNUM);
		param.setSrcId(DmoVO.PK_DMO);
		param.setSrcItemclazz(DmoVO.class);
		return param;
	}

	private MarginParam getParamFor5X() {
		MarginParam param = new MarginParam();

		param.setSrcNum(BillItemVO.NNUM);
		param.setSrcCastunitid(BillItemVO.CASTUNITID);
		param.setSrcVChangeRate(BillItemVO.VCHANGERATE);
		param.setSrcNastNum(BillItemVO.NASTNUM);
		param.setSrcId(BillItemVO.CBILL_BID);
		param.setSrcItemclazz(BillItemVO.class);
		return param;
	}

	@Override
	protected List<IPuMarginProcess> createFirstTimeMarginProcess() {
		return this.createMarginProcess(true);
	}

}
