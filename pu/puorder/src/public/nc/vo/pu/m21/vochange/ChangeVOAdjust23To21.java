package nc.vo.pu.m21.vochange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderPaymentQuery;
import nc.itf.pu.m21.IOrderQuery;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.query.supplier.SupplierInfo;
import nc.vo.pu.m21.rule.PlanArriveDate;
import nc.vo.pu.m21.rule.RelationCalculate;
import nc.vo.pu.m21.rule.ReplenishDefaultValue;
import nc.vo.pu.m21.rule.SupplierDefaultValueFrmSource;
import nc.vo.pu.m21.rule.margin.OrderMarginRule;
import nc.vo.pu.m21.rule.vat.OrderVatInfoSrcFillRule;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.rule.NumAndOrigmnySum;
import nc.vo.pu.pub.rule.WeightVolumePieceCalc;
import nc.vo.pu.pub.rule.WeightVolumePieceSum;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-6-22 下午05:39:05
 * @author wuxla
 */

public class ChangeVOAdjust23To21 extends AbstractOrderChangeVOAdjust {
	@Override
	public AggregatedValueObject[] batchAdjustAfterChange(
			AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
			ChangeVOAdjustContext adjustContext) throws BusinessException {
		Set<String> set = new HashSet<String>();
		for (AggregatedValueObject vo : srcVOs) {
			ArriveVO avo = (ArriveVO) vo;
			ArriveItemVO[] itemVOs = avo.getBVO();
			for (ArriveItemVO itemVO : itemVOs) {
				String pk_order = itemVO.getPk_order();
				if (pk_order != null)
					set.add(pk_order);
			}
		}
		if (set.size() == 1) {
			ArriveVO avo = (ArriveVO) srcVOs[0];
			ArriveItemVO[] bvo = avo.getBVO();
			String pk_order = bvo[0].getPk_order();
			if (pk_order != null && !pk_order.isEmpty()) {
				IOrderPaymentQuery paymentQuery = NCLocator.getInstance().lookup(
						IOrderPaymentQuery.class);
				MapList<String, OrderPaymentVO> mapList = paymentQuery
						.queryOrderPaymentByOrderIds(new String[] { pk_order });
				List<OrderPaymentVO> list = mapList.get(pk_order);
				if (list == null || list.isEmpty()) {
					return super.batchAdjustAfterChange(srcVOs, destVOs, adjustContext);
				}
				for (AggregatedValueObject object : destVOs) {
					((OrderVO) object).setChildren(OrderPaymentVO.class,
							list.toArray(new OrderPaymentVO[0]));
				}
			}
		}
		AggregatedValueObject[] vos = super.batchAdjustAfterChange(srcVOs, destVOs,
				adjustContext);
		if (set.size() > 1) {
			for (AggregatedValueObject vo : vos) {
				((OrderVO) vo).getHVO().setPk_payterm(null);
			}
		}
		return vos;
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
	private void fillInfo(OrderVO[] orderVOs) {
		for (OrderVO orderVO : orderVOs) {
			BillHelper<OrderVO> helper = new BillHelper<OrderVO>(orderVO);
			OrderItemVO[] items = orderVO.getBVO();
			int[] rows = new int[items.length];
			for (int i = 0; i < items.length; i++) {
				rows[i] = i;
			}

			// Map<String, ArriveItemVO> arriveItemVOMap =
			// AggVOUtil.createItemMap(arriveVOs);
			// Map<String, ArriveHeaderVO> arriveHeadVOMap =
			// AggVOUtil.createHeadMap(arriveVOs);

			// 计划到货日期
			PlanArriveDate planArriveDate = new PlanArriveDate(helper);
			planArriveDate.setPlanArriveDate(0, items.length - 1);

			// ReplenishDefaultValue replenishDefault =
			// new ReplenishDefaultValue(helper);
			// replenishDefault.setVOInfoByArrive(rows, arriveVOs);

			// 获得供应商信息
			SupplierInfo supplier = this.getSupplierInfo(helper);
			// 设置供应商的默认值
			SupplierDefaultValueFrmSource vendorDefaultValue = new SupplierDefaultValueFrmSource(
					helper);
			vendorDefaultValue.setDefaultValue(supplier);

			// new TrantypeValue(helper).setTrantypeValue();
			// 因为到界面，可以不用设置流程
			// new BusitypeFillRule(helper, POBillType.Order.getCode()).process();
		}

    RelationCalculate cal = new RelationCalculate();
    
    for (OrderVO orderVO : orderVOs) {
      BillHelper<OrderVO> bill = new BillHelper<OrderVO>(orderVO);
      // 重新计算表体数量关系
      WeightVolumePieceCalc wvpCal = new WeightVolumePieceCalc(bill);
      cal.calculate(orderVO, OrderItemVO.NNUM, wvpCal);
      
      // 计算整单数量和价税合计
			NumAndOrigmnySum sum = new NumAndOrigmnySum(bill);
			sum.setBlargessField(OrderItemVO.BLARGESS);
			sum.sum();
			WeightVolumePieceSum wvpsum = new WeightVolumePieceSum(bill);
			wvpsum.sum();
		}

	}

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
	private OrderVO[] getOrderVOs(OrderVO[] vos, ArriveVO[] arriveVOs) {
		OrderVO[] srcOrderVOs = this.queryNegativeOrders(arriveVOs);
		if (ArrayUtils.isEmpty(srcOrderVOs)) {
			return null;
		}
		// 原来的订单是有制单日期和制单人的。需要清空
		for (OrderVO vo : srcOrderVOs) {
			vo.getHVO().setDmakedate(null);
			vo.getHVO().setBillmaker(null);
		}
		BillIndex index = new BillIndex(srcOrderVOs);
		IVOMeta parentMeta = srcOrderVOs[0].getMetaData().getParent();
		IVOMeta childMeta = srcOrderVOs[0].getMetaData().getVOMeta(
				OrderItemVO.class);

		ArrayList<OrderVO> volists = new ArrayList<OrderVO>();

		for (ArriveVO arriveVO : arriveVOs) {
			ArriveHeaderVO arriveHeaderVO = arriveVO.getHVO();
			ArriveItemVO[] arriveItemVOs = arriveVO.getBVO();
			if (ArrayUtils.isEmpty(arriveItemVOs)) {
				continue;
			}

			for (ArriveItemVO arriveItemVO : arriveItemVOs) {
				String pkOrder = arriveItemVO.getPk_order();
				String pkOrderB = arriveItemVO.getPk_order_b();
				OrderHeaderVO orderHeaderVO = (OrderHeaderVO) index.get(parentMeta,
						pkOrder).clone();
				OrderItemVO orderItemVO = (OrderItemVO) index.get(childMeta, pkOrderB)
						.clone();

				orderHeaderVO.setPk_order(null);
				orderItemVO.setPk_order(null);
				orderItemVO.setPk_order_b(null);
				// 设置值
				this.setDefaultValueWhenFromBackRC(orderItemVO, arriveHeaderVO,
						arriveItemVO);
				OrderVO vo = new OrderVO();
				vo.setHVO(orderHeaderVO);
				vo.setBVO(new OrderItemVO[]{orderItemVO});
				volists.add(vo);
			}
		}

		OrderVO[] orderVOs = volists.toArray(new OrderVO[volists.size()]);

		for (OrderVO orderVO : orderVOs) {
			BillHelper<OrderVO> helper = new BillHelper<OrderVO>(orderVO);
			ReplenishDefaultValue defaultValue = new ReplenishDefaultValue(helper);
			defaultValue.setPositiveOrder();
			defaultValue.setDefaultValue();
		}

		Map<String, OrderHeaderVO> hmap = new HashMap<String, OrderHeaderVO>();
		Map<String, OrderItemVO> bmap = new HashMap<String, OrderItemVO>();
		for (OrderVO orderVO : orderVOs) {
			String csourcehid = null;
			for (OrderItemVO itemVO : orderVO.getBVO()) {
				bmap.put(itemVO.getCsourcebid(), itemVO);
				csourcehid = itemVO.getCsourceid();
				if (!hmap.containsKey(csourcehid)) {
					hmap.put(csourcehid, orderVO.getHVO());
				}
			}
		}
		Map<String, OrderHeaderVO> hmapold = new HashMap<String, OrderHeaderVO>();
		for (OrderVO vo : vos) {
			String csourcehid = null;
			List<OrderItemVO> list = new ArrayList<OrderItemVO>();
			for (OrderItemVO itemVO : vo.getBVO()) {
				list.add(bmap.get(itemVO.getCsourcebid()));
				csourcehid = itemVO.getCsourceid();
			}
			vo.setBVO(list.toArray(new OrderItemVO[list.size()]));
			OrderHeaderVO head = hmap.get(csourcehid);
			if (head != null) {
				hmap.remove(csourcehid);
				hmapold.put(csourcehid, head);
			} else {
				head = hmapold.get(csourcehid);
				if (head != null) {
					head = (OrderHeaderVO) head.clone();
				}
			}
			vo.setHVO(head);
		}
		return vos;
	}

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
	private OrderVO[] queryNegativeOrders(ArriveVO[] arriveVOs) {
		Set<String> set = new HashSet<String>();
		for (ArriveVO arriveVO : arriveVOs) {
			ArriveItemVO[] bodyVOs = arriveVO.getBVO();
			if (ArrayUtils.isEmpty(bodyVOs)) {
				continue;
			}
			for (ArriveItemVO bodyVO : bodyVOs) {
				String sourceid = bodyVO.getCsourceid();
				if (!StringUtil.isEmptyWithTrim(sourceid)) {
					set.add(sourceid);
				}
			}
		}

		if (set.isEmpty()) {
			return null;
		}

		String[] pks = set.toArray(new String[0]);
		try {
			IOrderQuery query = NCLocator.getInstance().lookup(IOrderQuery.class);
			return query.queryOrderVOsByIds(pks, UFBoolean.FALSE);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}

		return null;
	}

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
	private void setDefaultValueWhenFromBackRC(OrderItemVO orderItemVO,
			ArriveHeaderVO arriveHeaderVO, ArriveItemVO arriveItemVO) {
		orderItemVO.setPk_recvstordoc(arriveItemVO.getPk_receivestore());

		orderItemVO.setVfree1(arriveItemVO.getVfree1());
		orderItemVO.setVfree2(arriveItemVO.getVfree2());
		orderItemVO.setVfree3(arriveItemVO.getVfree3());
		orderItemVO.setVfree4(arriveItemVO.getVfree4());
		orderItemVO.setVfree5(arriveItemVO.getVfree5());
		orderItemVO.setVfree6(arriveItemVO.getVfree6());
		orderItemVO.setVfree7(arriveItemVO.getVfree7());
		orderItemVO.setVfree8(arriveItemVO.getVfree8());
		orderItemVO.setVfree9(arriveItemVO.getVfree9());
		orderItemVO.setVfree10(arriveItemVO.getVfree10());

		// 暂时将来源设置为退货单，后面会设置为采购订单
		orderItemVO.setCsourcebid(arriveItemVO.getPk_arriveorder_b());
		orderItemVO.setCsourceid(arriveHeaderVO.getPk_arriveorder());
		orderItemVO.setCsourcetypecode(POBillType.Arrive.getCode());
		orderItemVO.setVsourcetrantype(arriveHeaderVO.getCtrantypeid());
		orderItemVO.setVsourcecode(arriveHeaderVO.getVbillcode());
		orderItemVO.setVsourcerowno(arriveItemVO.getCrowno());

		orderItemVO.setCfirstid(arriveHeaderVO.getPk_arriveorder());
		orderItemVO.setCfirstbid(arriveItemVO.getPk_arriveorder_b());
		orderItemVO.setCfirsttypecode(POBillType.Arrive.getCode());
		orderItemVO.setVfirsttrantype(arriveHeaderVO.getCtrantypeid());
		orderItemVO.setVfirstcode(arriveHeaderVO.getVbillcode());
		orderItemVO.setVfirstrowno(arriveItemVO.getCrowno());

		orderItemVO.setCpriceauditid(null);
		orderItemVO.setCpriceaudit_bb1id(null);
		orderItemVO.setCpriceaudit_bid(null);

		orderItemVO.setSourcets(arriveHeaderVO.getTs());
		orderItemVO.setSourcebts(arriveItemVO.getTs());

		orderItemVO.setNnum(arriveItemVO.getNcanreplnum());
	}

	@Override
	protected void calWeightVolumePiece(OrderVO[] vos) {
	}

	@Override
	protected OrderVO[] fillInformation(OrderVO[] vos,
			AggregatedValueObject[] srcVOs) {
		if (ArrayUtils.isEmpty(srcVOs)) {
			return null;
		}
		ArriveVO[] arriveVOs = ArrayUtil.convertArrayType(srcVOs);
		OrderVO[] orderVOs = this.getOrderVOs(vos, arriveVOs);
		if (ArrayUtils.isEmpty(orderVOs)) {
			ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("4004030_0", "04004030-0205")/*
																																	 * @res
																																	 * "生成补货订单失败"
																																	 */);
		}
		this.fillInfo(orderVOs);
		return orderVOs;
	}

	@Override
	protected void fillVatInfo(OrderVO[] vos) {
		OrderVatInfoSrcFillRule vatRule = new OrderVatInfoSrcFillRule();
		vatRule.setResetVat(false);// 不强制替换，
		vatRule.process(vos);
	}

	@Override
	protected void processMargin(OrderVO[] vos, AggregatedValueObject[] srcVos) {
		new OrderMarginRule(POBillType.Arrive.getCode(), srcVos).process(vos);
	}

	@Override
	protected void setOrderPrice(OrderVO[] vos) {
		this.setPriceByParaPO16(vos);
	}
}
