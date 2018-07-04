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
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInHeadVO;
import nc.vo.ic.m45.entity.PurchaseInVO;
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
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.enumeration.EnumDiscounttaxtype;
import nc.vo.pu.pub.rule.NumAndOrigmnySum;
import nc.vo.pu.pub.rule.WeightVolumePieceCalc;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.ICBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-6-22 下午05:39:19
 * @author wuxla
 */

public class ChangeVOAdjust45To21 extends AbstractOrderChangeVOAdjust {

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    Set<String> set = new HashSet<String>();
		for (AggregatedValueObject vo : srcVOs) {
			// 判断源头是否唯一，如唯一则是从一张入库单拉单
			PurchaseInVO avo = (PurchaseInVO) vo;
			PurchaseInBodyVO[] itemVOs = avo.getBodys();
			for (PurchaseInBodyVO itemVO : itemVOs) {
				String pk_order = itemVO.getCfirstbillhid();
				if (pk_order != null)
					set.add(pk_order);
				set.add(pk_order);
			}
		}
    if (set.size() == 1) {
      PurchaseInVO avo = (PurchaseInVO) srcVOs[0];
      PurchaseInBodyVO[] bvo = avo.getBodys();
      String pk_order = bvo[0].getCfirstbillhid();
      if (pk_order != null && !pk_order.isEmpty()) {
        IOrderPaymentQuery paymentQuery =
            NCLocator.getInstance().lookup(IOrderPaymentQuery.class);
        MapList<String, OrderPaymentVO> mapList =
            paymentQuery.queryOrderPaymentByOrderIds(new String[] {
              pk_order
            });
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

  @Override
  protected String[] getNumStrs() {
    return new String[]{"nnum", "nassistnum", "nassistnum"};
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
   * @time 2010-6-3 下午03:11:42
   */
  private void fillInfo(OrderVO[] orderVOs, PurchaseInVO[] purchaseinVOs) {
    for (OrderVO orderVO : orderVOs) {
      BillHelper<OrderVO> helper = new BillHelper<OrderVO>(orderVO);
      OrderItemVO[] items = orderVO.getBVO();
      int[] rows = new int[items.length];
      for (int i = 0; i < items.length; i++) {
        rows[i] = i;
      }

      // Map<String, PurchaseInBodyVO> purchaseinBodyVOMap =
      // AggVOUtil.createItemMap(purchaseinVOs);

      // 计划到货日期
      PlanArriveDate planArriveDate = new PlanArriveDate(helper);
      planArriveDate.setPlanArriveDate(0, items.length - 1);

      ReplenishDefaultValue replenishDefault =
          new ReplenishDefaultValue(helper);
      replenishDefault.setVOInfoByStore(rows, purchaseinVOs);

      // 获得供应商信息
      SupplierInfo supplier = this.getSupplierInfo(helper);
      // 设置供应商的默认值
      SupplierDefaultValueFrmSource vendorDefaultValue =
          new SupplierDefaultValueFrmSource(helper);
      vendorDefaultValue.setDefaultValue(supplier);

      // 退库单到补货是到界面，可以不用设置
      // new TrantypeValue(helper).setTrantypeValue();
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
    }
  }

  /**
   * 方法功能描述：根据退库单生成采购订单
   * <p>
   * <b>参数说明</b>
   * 
   * @param purchaseinVOs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-2 下午08:37:00
   */
  private OrderVO[] getOrderVOs(OrderVO[] vos, PurchaseInVO[] purchaseinVOs) {
    OrderVO[] srcOrderVOs = this.queryNegativeOrders(purchaseinVOs);
    BillIndex index = null;
    if (!ArrayUtils.isEmpty(srcOrderVOs)) {
      // 原来的订单是有制单日期和制单人的。需要清空
      for (OrderVO vo : srcOrderVOs) {
        vo.getHVO().setDmakedate(null);
        vo.getHVO().setBillmaker(null);
      }
      index = new BillIndex(srcOrderVOs);
    }

    OrderVO tempVO = new OrderVO();
    IVOMeta parentMeta = tempVO.getMetaData().getParent();
    IVOMeta childMeta = tempVO.getMetaData().getVOMeta(OrderItemVO.class);
    List<OrderVO> volists = new ArrayList<OrderVO>();
    Map<String, OrderHeaderVO> hmap = new HashMap<String, OrderHeaderVO>();
    Map<String, OrderItemVO> bmap = new HashMap<String, OrderItemVO>();

    for (OrderVO vo : vos) {
      OrderHeaderVO headVO = vo.getHVO();
      for (OrderItemVO itemVO : vo.getBVO()) {
        bmap.put(itemVO.getCsourcebid(), itemVO);
        hmap.put(itemVO.getCsourceid(), headVO);
      }
    }
    for (PurchaseInVO purchaseinVO : purchaseinVOs) {
      PurchaseInHeadVO headVO = purchaseinVO.getHead();
      PurchaseInBodyVO[] bodyVOs = purchaseinVO.getBodys();
      if (ArrayUtils.isEmpty(bodyVOs)) {
        continue;
      }

      for (PurchaseInBodyVO bodyVO : bodyVOs) {
        String csourcebillbid = bodyVO.getCsourcebillbid();
        if (StringUtil.isEmptyWithTrim(csourcebillbid)) {
          // 自制退库单

          OrderHeaderVO headerVO = hmap.get(bodyVO.getCgeneralhid());
          OrderItemVO itemVO = bmap.get(bodyVO.getCgeneralbid());
          headerVO.setFhtaxtypeflag(EnumDiscounttaxtype.TAXOUT.toInteger());
          itemVO.setFtaxtypeflag(EnumDiscounttaxtype.TAXOUT.toInteger());

          OrderVO vo = new OrderVO();
          vo.setHVO(headerVO);
          vo.setBVO(new OrderItemVO[]{itemVO});
          volists.add(vo);
        }
        else if (index != null) {
          // 来自负订单的退库单
          OrderHeaderVO headerVO =
              (OrderHeaderVO) index.get(parentMeta, bodyVO.getCfirstbillhid());
          if (null == headerVO) {
            continue;
          }
          headerVO = (OrderHeaderVO) headerVO.clone();
          headerVO.setPk_order(null);
          OrderItemVO itemVO =
              (OrderItemVO) index.get(childMeta, bodyVO.getCfirstbillbid())
                  .clone();
          itemVO.setPk_order(null);
          itemVO.setPk_order_b(null);
          // 设置值
          this.setDefaultValueWhenFromBackIc(itemVO, headVO, bodyVO);
          OrderVO vo = new OrderVO();
          vo.setHVO(headerVO);
          vo.setBVO(new OrderItemVO[]{itemVO});
          volists.add(vo);
        }
      }
    }

    OrderVO[] orderVOs = volists.toArray(new OrderVO[volists.size()]);

    for (OrderVO orderVO : orderVOs) {
      BillHelper<OrderVO> helper = new BillHelper<OrderVO>(orderVO);
      ReplenishDefaultValue defaultValue = new ReplenishDefaultValue(helper);
      defaultValue.setPositiveOrder();
      defaultValue.setDefaultValue();
    }

    hmap = new HashMap<String, OrderHeaderVO>();
    bmap = new HashMap<String, OrderItemVO>();
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

    for (OrderVO vo : vos) {
      String csourcehid = null;
      List<OrderItemVO> list = new ArrayList<OrderItemVO>();
      for (OrderItemVO itemVO : vo.getBVO()) {
        OrderItemVO newItemVO = bmap.get(itemVO.getCsourcebid());
        if (newItemVO != null) {
          list.add(newItemVO);
          csourcehid = itemVO.getCsourceid();
        }
      }
      if (list.size() > 0) {
        vo.setBVO(list.toArray(new OrderItemVO[list.size()]));
        vo.setHVO(hmap.get(csourcehid));
      }
    }
    return vos;
  }

  /**
   * 方法功能描述：得到退库单数组的所有负订单
   * <p>
   * <b>参数说明</b>
   * 
   * @param purchaseinVOs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-2 下午08:36:56
   */
  private OrderVO[] queryNegativeOrders(PurchaseInVO[] purchaseinVOs) {
    Set<String> set = new HashSet<String>();
    for (PurchaseInVO purchaseinVO : purchaseinVOs) {
      PurchaseInBodyVO[] bodyVOs = purchaseinVO.getBodys();
      if (ArrayUtils.isEmpty(bodyVOs)) {
        continue;
      }
      for (PurchaseInBodyVO bodyVO : bodyVOs) {
        String sourcehid = bodyVO.getCsourcebillhid();
        if (!StringUtil.isEmptyWithTrim(sourcehid)) {
          set.add(sourcehid);
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
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return null;
  }

  /**
   * 方法功能描述：设置补货订单值
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   * @param headVO
   * @param bodyVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-5 上午11:11:06
   */
  private void setDefaultValueWhenFromBackIc(OrderItemVO itemVO,
      PurchaseInHeadVO headVO, PurchaseInBodyVO bodyVO) {
    itemVO.setPk_arrvstoorg(headVO.getPk_org());
    itemVO.setPk_recvstordoc(headVO.getCwarehouseid());

    itemVO.setVfree1(bodyVO.getVfree1());
    itemVO.setVfree2(bodyVO.getVfree2());
    itemVO.setVfree3(bodyVO.getVfree3());
    itemVO.setVfree4(bodyVO.getVfree4());
    itemVO.setVfree5(bodyVO.getVfree5());
    itemVO.setVfree6(bodyVO.getVfree6());
    itemVO.setVfree7(bodyVO.getVfree7());
    itemVO.setVfree8(bodyVO.getVfree8());
    itemVO.setVfree9(bodyVO.getVfree9());
    itemVO.setVfree10(bodyVO.getVfree10());

    itemVO.setCsourcebid(bodyVO.getCgeneralbid());
    itemVO.setCsourceid(headVO.getCgeneralhid());
    itemVO.setCsourcetypecode(ICBillType.PurchaseIn.getCode());
    itemVO.setVsourcetrantype(headVO.getCtrantypeid());
    itemVO.setVsourcecode(headVO.getVbillcode());
    itemVO.setVsourcerowno(bodyVO.getCrowno());

    itemVO.setCfirstid(headVO.getCgeneralhid());
    itemVO.setCfirstbid(bodyVO.getCgeneralbid());
    itemVO.setCfirsttypecode(ICBillType.PurchaseIn.getCode());
    itemVO.setCfirstid(headVO.getCgeneralhid());
    itemVO.setCfirstbid(bodyVO.getCgeneralbid());
    itemVO.setVfirsttrantype(headVO.getCtrantypeid());
    itemVO.setVfirstcode(headVO.getVbillcode());

    itemVO.setCpriceauditid(null);
    itemVO.setCpriceaudit_bb1id(null);
    itemVO.setCpriceaudit_bid(null);

    itemVO.setSourcets(headVO.getTs());
    itemVO.setSourcebts(bodyVO.getTs());

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
    PurchaseInVO[] purchaseinVOs = ArrayUtil.convertArrayType(srcVOs);
    OrderVO[] orderVOs = this.getOrderVOs(vos, purchaseinVOs);
    if (ArrayUtils.isEmpty(orderVOs)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0205")/*
                                                                   * @res
                                                                   * "生成补货订单失败"
                                                                   */);
    }
    this.fillInfo(orderVOs, purchaseinVOs);
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
    new OrderMarginRule(ICBillType.PurchaseIn.getCode(), srcVos).process(vos);
  }

  @Override
  protected void setOrderPrice(OrderVO[] vos) {
    this.setPriceByParaPO16(vos);
  }
}
