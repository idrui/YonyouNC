package nc.ui.pu.m27.match.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.pubitf.pu.m25.pu.settle.InvoiceQueryResultFor27;
import nc.ui.pu.m27.match.editor.event.InvoiceDisplayEvent;
import nc.ui.pu.m27.match.editor.event.MatchDisplayEvent;
import nc.ui.pu.m27.match.editor.event.SettleBillDisplayEvent;
import nc.ui.pu.m27.match.editor.event.StockDisplayEvent;
import nc.ui.pu.m27.match.model.SettleUIState.DistState;
import nc.ui.pu.m27.match.view.listener.DataRowNo;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillListManageModel;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.MatchMaterialVO;
import nc.vo.pu.m27.entity.MatchMaterialVOUtil;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.merge.MatchMergeData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.rule.MatchRule;
import nc.vo.pu.m27.rule.MatchRuleFactory;
import nc.vo.pu.m27.util.ClassifyInvoiceByType;
import nc.vo.pu.m27.util.CombineToInvoiceSettleVO;
import nc.vo.pub.ValidationException;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>界面模型类，此模型为采购结算界面、VMI结算界面共用
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-11 下午07:10:54
 */
public class MatchManageModel extends BillListManageModel {
	/** 结算环境 */
	private SettleEnvironment environment;

	/** 用于结算的折扣发票 */
	private FeeDiscountSettleVO[] matchDiscountInvoices;

	/** 用于结算的费用发票 */
	private FeeDiscountSettleVO[] matchFeeInvoices;

	/** 用于结算的货物发票 */
	private InvoiceSettleVO[] matchInvoiceVOs;

	/** 匹配界面显示的VO */
	private MatchMaterialVO[] matchMaterialVOs;

	/** 用于结算的入库数据 */
	private StockSettleVO[] matchStockVOs;

	/** 查询出的折扣类发票 */
	private FeeDiscountSettleVO[] queryDiscountInvoices;

	/** 查询出的费用发票 */
	private FeeDiscountSettleVO[] queryFeeInvoices;

	/** 查询出的货物发票 */
	private InvoiceSettleVO[] queryInvoiceVOs;

	/** 查询出的入库数据 */
	private StockSettleVO[] queryStockVOs;

	/** 选中的发票的行ID */
	private Set<String> selectedInvoices = new HashSet<String>();

	/** 选中的入库单的行ID */
	private Set<String> selectedStocks = new HashSet<String>();

	/** 结算生成的结算单 */
	private SettleBillVO[] settleBillVOs;

	@Override
	public SettleUIState getAppUiState() {
		AppUiState auist = super.getAppUiState();
		if (!(auist instanceof SettleUIState)) {
			return new SettleUIState(DistState.not_dist, DistState.not_dist);
		}
		return (SettleUIState) auist;
	}

	public FeeDiscountSettleVO[] getMatchDiscountInvoices() {
		return this.matchDiscountInvoices;
	}

	public FeeDiscountSettleVO[] getMatchFeeInvoices() {
		return this.matchFeeInvoices;
	}

	public InvoiceSettleVO[] getMatchInvoiceVOs() {
		return this.matchInvoiceVOs;
	}

	public MatchMaterialVO[] getMatchMaterialVOs() {
		return this.matchMaterialVOs;
	}

	public StockSettleVO[] getMatchStockVOs() {
		return this.matchStockVOs;
	}

	public FeeDiscountSettleVO[] getQueryDiscountInvoices() {
		return this.queryDiscountInvoices;
	}

	public FeeDiscountSettleVO[] getQueryFeeInvoices() {
		return this.queryFeeInvoices;
	}

	public InvoiceSettleVO[] getQueryInvoiceVOs() {
		return this.queryInvoiceVOs;
	}

	public StockSettleVO[] getQueryStockVOs() {
		return this.queryStockVOs;
	}

	public Set<String> getSelectedInvoices() {
		return this.selectedInvoices;
	}

	public InvoiceSettleVO[] getSelectedInvoiceSettleVOs() {
		List<InvoiceSettleVO> selectedInvoiceVos = new ArrayList<InvoiceSettleVO>();
		this.getSelectedInvoiceSettleVOs(selectedInvoiceVos,
				this.getQueryInvoiceVOs());
		this.getSelectedInvoiceSettleVOs(selectedInvoiceVos,
				this.getQueryFeeInvoices());
		this.getSelectedInvoiceSettleVOs(selectedInvoiceVos,
				this.getQueryDiscountInvoices());
		return selectedInvoiceVos.toArray(new InvoiceSettleVO[selectedInvoiceVos
				.size()]);
	}

	public Set<String> getSelectedStocks() {
		return this.selectedStocks;
	}

	public StockSettleVO[] getSelectedStockSettleVOs() {
		Set<String> stockIds = this.getSelectedStocks();
		StockSettleVO[] stockVos = this.getQueryStockVOs();
		List<StockSettleVO> selectedStockVos = new ArrayList<StockSettleVO>();
		if (!ArrayUtils.isEmpty(stockVos)) {
			for (StockSettleVO stockVo : stockVos) {
				if (stockIds.contains(stockVo.getPk_stockps_b())) {
					selectedStockVos.add(stockVo);
				}
			}
		}
		return selectedStockVos.toArray(new StockSettleVO[selectedStockVos.size()]);
	}

	public SettleBillVO[] getSettleBillVOs() {
		return this.settleBillVOs;
	}

	public SettleEnvironment getSettleEnvironment() {
		return this.environment;
	}

	/**
	 * 方法功能描述：初始化界面入库域。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param VOs
	 *          <p>
	 * @since 6.0
	 * @author tianft
	 * @time 2010-1-19 下午04:15:56
	 */
	public void initInvoice(InvoiceQueryResultFor27 result) {
		if (result == null) {
			this.queryInvoiceVOs = null;
			this.queryFeeInvoices = null;
			this.queryDiscountInvoices = null;
		} else {
			// 初始化发票单据
			this.queryInvoiceVOs = result.getGoodsInvoices();
			this.queryFeeInvoices = result.getFeeInvoices();
			this.queryDiscountInvoices = result.getDiscountInvoices();
		}
		this.selectedInvoices = new HashSet<String>();
		// 发出发票查询的事件
		this.fireEvent(new InvoiceDisplayEvent(null));
	}

	/**
	 * 方法功能描述：初始化待结算的VO（入库单VO、发票VO等）
	 * <p>
	 * <b>参数说明</b>
	 * <p>
	 * 
	 * @since 6.0
	 * @author duy
	 * @throws ValidationException
	 * @time 2010-10-1 下午05:12:37
	 */
	public void initMatchVos() throws ValidationException {
		// 获得选中的入库单的VO
		StockSettleVO[] selectedStockVos = this.getSelectedStockSettleVOs();

		// 获得选中的发票的VO
		InvoiceSettleVO[] selectedInvoiceVos = this.getSelectedInvoiceSettleVOs();

		// 对于所选择的发票进行分类
		ClassifyInvoiceByType classify = new ClassifyInvoiceByType(
				selectedInvoiceVos);
		classify.classifyInvoice();

		// 把参与结算的入库单和发票VO设置到Model中
		this.matchInvoiceVOs = classify.getGoodsInvoices();
		this.matchStockVOs = selectedStockVos;
		this.matchFeeInvoices = classify.getFeeInvoices();
		this.matchDiscountInvoices = classify.getDiscountInvoices();

		// 结算规则的初步过滤
		MatchRule checker = new MatchRuleFactory().getRule(this
				.getSettleEnvironment().getSettleType());
		checker.check(new MatchMergeData(this.matchStockVOs, this.matchInvoiceVOs,
				this.matchFeeInvoices, this.matchDiscountInvoices), this
				.getSettleEnvironment());

		// 创建结算匹配用（第二个界面） 的VO数组
		MatchMaterialVO[] vos = MatchMaterialVOUtil.assembleMatchMaterialVO(
				selectedStockVos, this.matchInvoiceVOs, this.getSettleEnvironment()
						.getSettleType());
		// 缓存到model
		this.matchMaterialVOs = vos;

		// 发出匹配显示的事件
		this.fireEvent(new MatchDisplayEvent(null));
	}

	public void initSettleBillVOs(SettleBillVO[] settleBills) {
		this.settleBillVOs = settleBills;
		// 发出结算完毕结算单显示的事件
		this.fireEvent(new SettleBillDisplayEvent(null));
	}

	public void initSettleEnvironment(String pk_org) {
		this.environment = new SettleEnvironment(pk_org, EnumSettleType.UI_AUTO);
	}

	/**
	 * 方法功能描述：初始化界面入库单数据。
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param VOs
	 *          入库单VO
	 *          <p>
	 * @since 6.0
	 * @author tianft
	 * @time 2010-1-19 下午04:15:56
	 */
	public void initStock(StockSettleVO[] vos) {
		this.queryStockVOs = vos;
		this.selectedStocks = new HashSet<String>();

		// 发出入库单查询的事件
		this.fireEvent(new StockDisplayEvent(null));
	}

	/**
	 * 设置费用的分摊状态，会重设UIState
	 * 
	 * @param dstate
	 */
	public void setFeeDistUIState(DistState dstate) {
		SettleUIState state = this.getAppUiState();
		state.setFdstate(dstate);
		this.setAppUiState(state);
	}

	/**
	 * 设置发票（异物料结算）的分摊状态，会重设UIState
	 * 
	 * @param dstate
	 */
	public void setInvcDistUIState(DistState dstate) {
		SettleUIState state = this.getAppUiState();
		state.setIdstate(dstate);
		this.setAppUiState(state);
	}

	/**
	 * 重新该方法，将不再使用UI工厂II的行选中方法
	 * 
	 * @see nc.ui.pubapp.uif2app.model.BillManageModel#setSelectedOperaRows(int[])
	 */
	@Override
	public void setSelectedOperaRows(int[] selectedRows) {
		// 不再使用UI工厂II的行选中方法
	}

	private void getSelectedInvoiceSettleVOs(
			List<InvoiceSettleVO> selectedInvoiceVos, InvoiceSettleVO[] invoices) {
		if (ArrayUtils.isEmpty(invoices)) {
			return;
		}
		Set<String> invoiceIds = this.getSelectedInvoices();
		for (InvoiceSettleVO invoice : invoices) {
			if (invoiceIds.contains(invoice.getPk_invoice_b())) {
				selectedInvoiceVos.add(invoice);
			}
		}
	}

	@Override
	public List getRelaSortObject() {
		List<nc.ui.pu.m27.match.view.listener.DataRowNo> dataRowNos = new ArrayList<>();
		for (InvoiceSettleVO invoiceSettleVO : getInvoiceSettleVO()) {
			DataRowNo rowNo = new nc.ui.pu.m27.match.view.listener.DataRowNo(invoiceSettleVO, 0);
			dataRowNos.add(rowNo);
		}
		return dataRowNos;
	}

	public InvoiceSettleVO[] getInvoiceSettleVO() {
		CombineToInvoiceSettleVO combine = new CombineToInvoiceSettleVO();
    combine.setGoodsInvoices(this.getQueryInvoiceVOs());
    combine.setFeeInvoices(this.getQueryFeeInvoices());
    combine.setDiscountInvoices(this.getQueryDiscountInvoices());
    InvoiceSettleVO[] invoices = combine.combineInvoice();
    return invoices;
	}

}
