/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-31 下午08:55:24
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>退货单生成补货订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-31 下午08:55:24
 */
public class AddFromBackArriveAction extends AbstractReferenceAction {

	private static final long serialVersionUID = 4929715746756857774L;

	private IBillCardPanelEditor editor;

	private AbstractAppModel model;

	/**
	 * 父类方法重写
	 * 
	 * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
	 */
	@Override
	public void doAction(ActionEvent e) throws Exception {
		PfUtilClient.childButtonClickedNew(this.createPfButtonClickContext());
		if (PfUtilClient.isCloseOK()) {
			OrderVO[] vos = (OrderVO[]) PfUtilClient.getRetVos();

			// 显示到转单界面上
			this.getTransferViewProcessor().processBillTransfer(vos);
		}
		this.getEditor().getBillCardPanel().getBillTable(OrderPaymentVO.TABSNAME)
				.setEnabled(false);
	}

	/**
	 * @return editor
	 */
	public IBillCardPanelEditor getEditor() {
		return this.editor;
	}

	/**
	 * @return model
	 */
	public AbstractAppModel getModel() {
		return this.model;
	}

	@Override
	public void putValue(String key, Object newValue) {
		if (Action.NAME.equals(key) || Action.SHORT_DESCRIPTION.equals(key)) {
			super.putValue(key, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("common", "14004000-0010")/*
																								 * 退货单
																								 */);
		} else {
			super.putValue(key, newValue);
		}
	}

	/**
	 * @param editor
	 *          要设置的 editor
	 */
	public void setEditor(IBillCardPanelEditor editor) {
		this.editor = editor;
	}

	/**
	 * @param model
	 *          要设置的 model
	 */
	public void setModel(AbstractAppModel model) {
		this.model = model;
	}

	private PfButtonClickContext createPfButtonClickContext() {
		PfButtonClickContext context = new PfButtonClickContext();
		context.setParent(this.getModel().getContext().getEntranceUI());
		context.setSrcBillType(this.getSourceBillType());
		context.setPk_group(this.getModel().getContext().getPk_group());
		context.setUserId(this.getModel().getContext().getPk_loginUser());
		// 如果该节点是由交易类型发布的，那么这个参数应该传交易类型，否则传单据类型
		// 如果该节点是由交易类型发布的，那么这个参数应该传交易类型，否则传单据类型
		String vtrantype = TrantypeFuncUtils.getTrantype(this.getModel()
				.getContext());
		if (StringUtil.isEmptyWithTrim(vtrantype)) {
			context.setCurrBilltype(POBillType.Order.getCode());
		} else {
			context.setCurrBilltype(vtrantype);
		}
		context.setUserObj(null);
		context.setSrcBillId(null);
		context.setBusiTypes(this.getBusitypes());
		// 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
		// 上游的交易类型集合
		context.setTransTypes(this.getTranstypes());
		// 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
		// 2（根据流程配置）、-1（不根据交易类型分组）
		context.setClassifyMode(PfButtonClickContext.NoClassify);
		return context;
	}

	/**
	 * 方法功能描述：补充补货订单信息
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param orderVOs
	 * @param purchaseinVOs
	 *          <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-6-12 上午10:26:30
	 */
	// private void fillInfo(OrderVO[] orderVOs, ArriveVO[] arriveVOs) {
	// for (OrderVO orderVO : orderVOs) {
	// BillHelper<OrderVO> helper = new BillHelper<OrderVO>(orderVO);
	// OrderItemVO[] items = orderVO.getBVO();
	// int[] rows = new int[items.length];
	// for (int i = 0; i < items.length; i++) {
	// rows[i] = i;
	// }
	//
	// // Map<String, ArriveItemVO> arriveItemVOMap =
	// // AggVOUtil.createItemMap(arriveVOs);
	// // Map<String, ArriveHeaderVO> arriveHeadVOMap =
	// // AggVOUtil.createHeadMap(arriveVOs);
	//
	// // 计划到货日期
	// PlanArriveDate planArriveDate = new PlanArriveDate(helper);
	// planArriveDate.setPlanArriveDate(0, items.length - 1);
	//
	// // ReplenishDefaultValue replenishDefault =
	// // new ReplenishDefaultValue(helper);
	// // replenishDefault.setVOInfoByArrive(rows, arriveVOs);
	//
	// // 获得供应商信息
	// SupplierInfo supplier = this.getSupplierInfo(helper);
	// // 设置供应商的默认值
	// SupplierDefaultValueFrmSource vendorDefaultValue =
	// new SupplierDefaultValueFrmSource(helper);
	// vendorDefaultValue.setDefaultValue(supplier);
	//
	// new TrantypeValue(helper).setTrantypeValue();
	// }
	// // 重新计算数量关系
	// this.relationCalculate(orderVOs);
	//
	// for (OrderVO orderVO : orderVOs) {
	// BillHelper<OrderVO> bill = new BillHelper<OrderVO>(orderVO);
	// NumAndOrigmnySum sum = new NumAndOrigmnySum(bill);
	// sum.setBlargessField(OrderItemVO.BLARGESS);
	// sum.sum();
	// }
	//
	// }

	/**
	 * 方法功能描述：根据退货单生成采购订单
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param arriveVOs
	 * @return <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-6-12 上午09:41:39
	 */
	// private OrderVO[] getOrderVOs(OrderVO[] vos, ArriveVO[] arriveVOs) {
	// OrderVO[] srcOrderVOs = this.queryNegativeOrders(arriveVOs);
	// if (ArrayUtils.isEmpty(srcOrderVOs)) {
	// return null;
	// }
	//
	// BillIndex index = new BillIndex(srcOrderVOs);
	// IVOMeta parentMeta = srcOrderVOs[0].getMetaData().getParent();
	// IVOMeta childMeta =
	// srcOrderVOs[0].getMetaData().getVOMeta(OrderItemVO.class);
	//
	// List<OrderHeaderVO> orderHeaderVOList = new ArrayList<OrderHeaderVO>();
	// List<OrderItemVO> orderItemVOList = new ArrayList<OrderItemVO>();
	//
	// for (ArriveVO arriveVO : arriveVOs) {
	// ArriveHeaderVO arriveHeaderVO = arriveVO.getHVO();
	// ArriveItemVO[] arriveItemVOs = arriveVO.getBVO();
	// if (ArrayUtils.isEmpty(arriveItemVOs)) {
	// continue;
	// }
	//
	// for (ArriveItemVO arriveItemVO : arriveItemVOs) {
	// String pkOrder = arriveItemVO.getPk_order();
	// String pkOrderB = arriveItemVO.getPk_order_b();
	// OrderHeaderVO orderHeaderVO =
	// (OrderHeaderVO) index.get(parentMeta, pkOrder).clone();
	// OrderItemVO orderItemVO =
	// (OrderItemVO) index.get(childMeta, pkOrderB).clone();
	//
	// orderHeaderVO.setPk_order(null);
	// orderItemVO.setPk_order(null);
	// orderItemVO.setPk_order_b(null);
	// // 设置值
	// this.setDefaultValueWhenFromBackRC(orderItemVO, arriveHeaderVO,
	// arriveItemVO);
	// orderHeaderVOList.add(orderHeaderVO);
	// orderItemVOList.add(orderItemVO);
	// }
	// }
	//
	// OrderHeaderVO[] orderHeaderVOs =
	// orderHeaderVOList.toArray(new OrderHeaderVO[0]);
	// OrderItemVO[] orderItemVOs = orderItemVOList.toArray(new OrderItemVO[0]);
	//
	// BillComposite<OrderVO> bc = new BillComposite<OrderVO>(OrderVO.class);
	// OrderVO tempVO = new OrderVO();
	// bc.append(tempVO.getMetaData().getParent(), orderHeaderVOs);
	// bc.append(tempVO.getMetaData().getVOMeta(OrderItemVO.class), orderItemVOs);
	// OrderVO[] orderVOs = bc.composite();
	//
	// for (OrderVO orderVO : orderVOs) {
	// BillHelper<OrderVO> helper = new BillHelper<OrderVO>(orderVO);
	// ReplenishDefaultValue defaultValue = new ReplenishDefaultValue(helper);
	// defaultValue.setPositiveOrder();
	// defaultValue.setDefaultValue();
	// }
	//
	// Map<String, OrderHeaderVO> hmap = new HashMap<String, OrderHeaderVO>();
	// Map<String, OrderItemVO> bmap = new HashMap<String, OrderItemVO>();
	// for (OrderVO orderVO : orderVOs) {
	// String csourcehid = null;
	// for (OrderItemVO itemVO : orderVO.getBVO()) {
	// bmap.put(itemVO.getCsourcebid(), itemVO);
	// csourcehid = itemVO.getCsourceid();
	// }
	// hmap.put(csourcehid, orderVO.getHVO());
	// }
	//
	// for (OrderVO vo : vos) {
	// String csourcehid = null;
	// List<OrderItemVO> list = new ArrayList<OrderItemVO>();
	// for (OrderItemVO itemVO : vo.getBVO()) {
	// list.add(bmap.get(itemVO.getCsourcebid()));
	// csourcehid = itemVO.getCsourceid();
	// }
	// vo.setBVO(list.toArray(new OrderItemVO[list.size()]));
	// vo.setHVO(hmap.get(csourcehid));
	// }
	// return vos;
	// }

	/**
	 * 方法功能描述：查询供应商信息
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param keyValue
	 * @return <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-6-5 下午04:19:43
	 */
	// private SupplierInfo getSupplierInfo(IKeyValue keyValue) {
	// String pk_supplier =
	// (String) keyValue.getHeadValue(OrderHeaderVO.PK_SUPPLIER);
	// if (pk_supplier == null) {
	// return null;
	// }
	// String pk_purchaseorg =
	// (String) keyValue.getHeadValue(OrderHeaderVO.PK_ORG);
	// SupplierInfo supplier = null;
	// try {
	// supplier =
	// NCLocator.getInstance().lookup(IOrderSupplierQuery.class)
	// .querySupplier(pk_supplier, pk_purchaseorg);
	// }
	// catch (BusinessException e) {
	// // 日志异常
	// ExceptionUtils.wrappException(e);
	//
	// }
	// return supplier;
	// }

	/**
	 * 方法功能描述：得到退货单数组的所有负订单
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param arriveVOs
	 * @return <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-6-12 上午09:45:43
	 */
	// private OrderVO[] queryNegativeOrders(ArriveVO[] arriveVOs) {
	// Set<String> set = new HashSet<String>();
	// for (ArriveVO arriveVO : arriveVOs) {
	// ArriveItemVO[] bodyVOs = arriveVO.getBVO();
	// if (ArrayUtils.isEmpty(bodyVOs)) {
	// continue;
	// }
	// for (ArriveItemVO bodyVO : bodyVOs) {
	// String sourceid = bodyVO.getCsourceid();
	// if (!StringUtil.isEmptyWithTrim(sourceid)) {
	// set.add(sourceid);
	// }
	// }
	// }
	//
	// if (set.isEmpty()) {
	// return null;
	// }
	//
	// String[] pks = set.toArray(new String[0]);
	// try {
	// IOrderQuery query = NCLocator.getInstance().lookup(IOrderQuery.class);
	// return query.queryOrderVOsByIds(pks, UFBoolean.FALSE);
	// }
	// catch (BusinessException e) {
	// ExceptionUtils.wrappException(e);
	// }
	//
	// return null;
	// }

	/**
	 * 方法功能描述：重新计算数量关系
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param orderVOs
	 *          <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-6-12 下午04:01:02
	 */
	// private void relationCalculate(OrderVO[] orderVOs) {
	// RelationCalculate cal = new RelationCalculate();
	// for (OrderVO orderVO : orderVOs) {
	// cal.calculate(orderVO, OrderItemVO.NNUM);
	// }
	// }

	/**
	 * 方法功能描述：
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param orderItemVO
	 * @param arriveHeaderVO
	 * @param arriveItemVO
	 *          <p>
	 * @since 6.0
	 * @author wuxla
	 * @time 2010-6-12 上午10:19:11
	 */
	// private void setDefaultValueWhenFromBackRC(OrderItemVO orderItemVO,
	// ArriveHeaderVO arriveHeaderVO, ArriveItemVO arriveItemVO) {
	// orderItemVO.setPk_recvstordoc(arriveItemVO.getPk_receivestore());
	//
	// orderItemVO.setVfree1(arriveItemVO.getVfree1());
	// orderItemVO.setVfree2(arriveItemVO.getVfree2());
	// orderItemVO.setVfree3(arriveItemVO.getVfree3());
	// orderItemVO.setVfree4(arriveItemVO.getVfree4());
	// orderItemVO.setVfree5(arriveItemVO.getVfree5());
	// orderItemVO.setVfree6(arriveItemVO.getVfree6());
	// orderItemVO.setVfree7(arriveItemVO.getVfree7());
	// orderItemVO.setVfree8(arriveItemVO.getVfree8());
	// orderItemVO.setVfree9(arriveItemVO.getVfree9());
	// orderItemVO.setVfree10(arriveItemVO.getVfree10());
	//
	// // 暂时将来源设置为退货单，后面会设置为采购订单
	// orderItemVO.setCsourcebid(arriveItemVO.getPk_arriveorder_b());
	// orderItemVO.setCsourceid(arriveHeaderVO.getPk_arriveorder());
	// orderItemVO.setCsourcetypecode(POBillType.Arrive.getCode());
	// orderItemVO.setVsourcetrantype(arriveHeaderVO.getVtrantypecode());
	// orderItemVO.setVsourcecode(arriveHeaderVO.getVbillcode());
	// orderItemVO.setVsourcerowno(arriveItemVO.getCrowno());
	//
	// orderItemVO.setCfirstid(arriveHeaderVO.getPk_arriveorder());
	// orderItemVO.setCfirstbid(arriveItemVO.getPk_arriveorder_b());
	// orderItemVO.setCfirsttypecode(POBillType.Arrive.getCode());
	// orderItemVO.setVfirsttrantype(arriveHeaderVO.getVtrantypecode());
	// orderItemVO.setVfirstcode(arriveHeaderVO.getVbillcode());
	// orderItemVO.setVsourcerowno(arriveItemVO.getCrowno());
	//
	// orderItemVO.setSourcets(arriveHeaderVO.getTs());
	// orderItemVO.setSourcebts(arriveItemVO.getTs());
	//
	// orderItemVO.setNnum(arriveItemVO.getNcanreplnum());
	// }
}
