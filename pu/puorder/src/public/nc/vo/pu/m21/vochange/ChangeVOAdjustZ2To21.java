package nc.vo.pu.m21.vochange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bd.userdef.UserDefCheckUtils;
import nc.itf.pu.reference.pp.PPServices;
import nc.pubitf.ct.purdaily.ICtQueryForOrder;
import nc.pubitf.pu.m20.pub.IQueryPrayBill;
import nc.vo.ct.purdaily.entity.AggCtPuVO;
import nc.vo.ct.purdaily.entity.CtPaymentVO;
import nc.vo.ct.purdaily.entity.CtPuBVO;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.m21.rule.NumValueSetter;
import nc.vo.pu.m21.rule.OrganizationDefaultValue;
import nc.vo.pu.m21.rule.PlanArriveDate;
import nc.vo.pu.m21.rule.RelationCalculate;
import nc.vo.pu.m21.rule.margin.OrderMarginRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.PPBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-6-22 下午12:06:32
 * @author wuxla
 */

public class ChangeVOAdjustZ2To21 extends AbstractOrderChangeVOAdjust {

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    // 处理付款协议
    this.dealPayment(srcVOs, destVOs);
    OrderVO[] vos = ArrayUtil.convertArrayType(destVOs);
    this.setBusiDate(vos);
    // 补全订单来源单据主数量，用于最后一次拉单计算
    this.fillOrderSourceNum(srcVOs, vos);
    // 直运标志补全
    this.setDirectValue(vos);
    // 自定义项检查，上下游不一致
    this.checkUserDef(vos);

    // 补全体积件数等信息
    this.calWeightVolumePiece(vos);

    vos = this.fillInformation(vos, srcVOs);
    // vat信息补全
    this.fillVatInfo(vos);
    // 做数量的尾差处理（倒挤）
    this.processMargin(vos, srcVOs);
    // 处理业务流程字段
    this.fillBusiTypeInfo(vos);
    // 转单或推单打开界面时是否进行自动询价
    this.setOrderPrice(vos);
    return vos;
  }

  @Override
  protected String[] getNumStrs(){
    return new String[]{"nnum", "nastnum", "nqtunitnum"};
  }
  
  
  private void setBusiDate(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    UFDate date = AppContext.getInstance().getBusiDate();
    for (OrderVO vo : vos) {
      vo.getHVO().setDbilldate(date);
    }
  }
  
  private void checkUserDef(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    UserDefCheckUtils.check(vos, new Class[] {
      OrderHeaderVO.class, OrderItemVO.class
    });
  }
  
  /**
   * 处理付款协议的带入问题：
   * 
   * @param srcVOs
   * @param destVOs
   * @throws BusinessException
   */
  private void dealPayment(AggregatedValueObject[] srcVOs,
      AggregatedValueObject[] destVOs) throws BusinessException {
    List<CtPaymentVO> ctPaymentVOs = this.getCtPaymentVOs(srcVOs);
    if (ctPaymentVOs == null) {
      for (OrderVO vo : (OrderVO[]) destVOs) {
        vo.getHVO().setPk_payterm(null);
      }
      return;
    }
    List<OrderPaymentVO> list = new LinkedList<OrderPaymentVO>();
    // 遍历合同付款协议，转化成订单付款协议
    for (CtPaymentVO ctPaymentVO : ctPaymentVOs) {
      OrderPaymentVO vo = new OrderPaymentVO();
      vo.setAccountday(ctPaymentVO.getOutaccountdate());
      vo.setAccrate(ctPaymentVO.getAccrate());
      vo.setCheckdata(ctPaymentVO.getCheckdata());
      vo.setEffectaddmonth(ctPaymentVO.getEffectaddmonth());
      vo.setEffectdateadddate(ctPaymentVO.getEffectdateadddate());
      vo.setEffectmonth(ctPaymentVO.getEffectmonth());
      vo.setIsdeposit(ctPaymentVO.getIsdeposit());
      vo.setPaymentday(ctPaymentVO.getPaymentday());
      vo.setPk_balatype(ctPaymentVO.getPk_balatype());
      vo.setPk_payperiod(ctPaymentVO.getPk_payperiod());
      vo.setPk_rate(ctPaymentVO.getPk_rate());
      vo.setPrepayment(ctPaymentVO.getPrepayment());
      vo.setShoworder(ctPaymentVO.getShoworder());
      list.add(vo);
    }
    OrderPaymentVO[] orderPaymentVOs = list.toArray(new OrderPaymentVO[0]);
    for (AggregatedValueObject ordervo : destVOs) {
      ((OrderVO) ordervo).setChildren(OrderPaymentVO.class, orderPaymentVOs);
    }
  }

  /**
   * 获取付款协议
   * 1、如果多个合同，则不带付款协议
   * 2、如果一个合同，则带付款协议
   * 
   * @param srcVOs
   * @return
   * @throws BusinessException
   */
  private List<CtPaymentVO> getCtPaymentVOs(AggregatedValueObject[] srcVOs)
      throws BusinessException {
    // 用一个set集合，存放传进来合同的主键
    Set<String> pkSet = new HashSet<>();
    for (AggregatedValueObject srcVO : srcVOs) {
      pkSet.add(srcVO.getParentVO().getPrimaryKey());
    }
    // 如果多于一个主键，返回空
    if (pkSet.size() > 1) {
      return null;
    }
    String[] pks = pkSet.toArray(new String[0]);
    // 调用合同的接口，获取合同主键对应的付款协议
    ICtQueryForOrder ctQueryForOrder =
        NCLocator.getInstance().lookup(ICtQueryForOrder.class);
    MapList<String, CtPaymentVO> map =
        ctQueryForOrder.queryCtPaymentVOsByBillId(pks);
    List<CtPaymentVO> ctPaymentVOs = map.get(pks[0]);
    return ctPaymentVOs;
  }

  /**
   * 获取合同和源头请购单表体VO的映射关系
   * 
   * @param ctVOs
   *          合同聚合VO
   * @return 合同和源头请购单的映射关系，key=合同BID，value=源头请购单表体VO。
   */
  private Map<String, PraybillItemVO> getCTPrayMapping(AggCtPuVO[] ctVOs) {
    Set<String> praybillBids = new HashSet<String>();
    Set<String> priceAuditBids = new HashSet<String>();
    Map<String, String> ctPrayBidMapping = new HashMap<String, String>();
    Map<String, PraybillItemVO> ctPrayItemMapping =
        new HashMap<String, PraybillItemVO>();

    // 如果来源是请购单，则直接收集，否则收集来源PK，再通过来源PK查出请购单。
    for (AggCtPuVO vo : ctVOs) {
      CtPuBVO[] ctitemvos = vo.getCtPuBVO();
      for (CtPuBVO ctitemVO : ctitemvos) {
        if (POBillType.PrayBill.getCode().equals(ctitemVO.getVsrctype())) {
          praybillBids.add(ctitemVO.getCsrcid());
          ctPrayBidMapping.put(ctitemVO.getPk_ct_pu_b(), ctitemVO.getCsrcbid());
        }
        else if (PPBillType.PriceAudit.getCode().equals(ctitemVO.getVsrctype())) {
          priceAuditBids.add(ctitemVO.getCsrcbid());
        }
      }
    }

    // 查出来源价格审批单，并收集源头请购单。
    if (!priceAuditBids.isEmpty()) {
      String[][] priceAuditItemDatas =
          PPServices.queryPriceAuditItemByBids(priceAuditBids
              .toArray(new String[priceAuditBids.size()]));
      if (priceAuditItemDatas != null) {
        String vfirsttype = null;
        String cfirstbid = null;
        String pk_priceaudit_b = null;

        for (String[] itemData : priceAuditItemDatas) {
          vfirsttype = itemData[0];
          cfirstbid = itemData[1];
          pk_priceaudit_b = itemData[2];

          if (POBillType.PrayBill.getCode().equals(vfirsttype)) {
            praybillBids.add(cfirstbid);
            for (AggCtPuVO vo : ctVOs) {
              for (CtPuBVO ctitemVO : vo.getCtPuBVO()) {
                if (pk_priceaudit_b.equals(ctitemVO.getCsrcbid())) {
                  ctPrayBidMapping.put(ctitemVO.getPk_ct_pu_b(), cfirstbid);
                  break;
                }
              }
            }
          }
        }
      }
    }

    // 查询来源请购单并拼装结果返回。
    IQueryPrayBill praybillService =
        NCLocator.getInstance().lookup(IQueryPrayBill.class);
    if (!praybillBids.isEmpty()) {
      try {
        Map<String, PraybillItemVO> praybillItems =
            praybillService.queryItemByPK(
                praybillBids.toArray(new String[praybillBids.size()]),
                new String[] {
                  PraybillItemVO.PK_PRAYBILL_B, PraybillItemVO.PK_ORG,
                  PraybillItemVO.PK_ORG_V, PraybillItemVO.PK_REQDEPT,
                  PraybillItemVO.PK_REQDEPT_V
                });
        for (Entry<String, String> entry : ctPrayBidMapping.entrySet()) {
          ctPrayItemMapping.put(entry.getKey(),
              praybillItems.get(entry.getValue()));
        }
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }

    return ctPrayItemMapping;
  }

  private void setPrice(OrderItemVO itemVO, CtPuBVO ctbvo) {
    if (null == ctbvo) {
      return;
    }
    itemVO.setNorigprice(ctbvo.getNorigprice());
    itemVO.setNorigtaxprice(ctbvo.getNorigtaxprice());
  }

  @Override
  protected void fillInformation(BillHelper<OrderVO> helper, int[] rows) {
    super.fillInformation(helper, rows);
    // 界面操作，不用处理
    // new TrantypeValue(helper).setTrantypeValue();
    // 计划到货日期
    PlanArriveDate planArriveDate = new PlanArriveDate(helper);
    planArriveDate.setPlanArriveDate(0, rows.length - 1);
  }

  @Override
  protected OrderVO[] fillInformation(OrderVO[] vos,
      AggregatedValueObject[] srcVOs) {
    AggCtPuVO[] ctVOs = ArrayUtil.convertArrayType(srcVOs);
    BillIndex index = new BillIndex(ctVOs);
    IVOMeta voMeta = new AggCtPuVO().getMetaData().getVOMeta(CtPuBVO.class);
    Map<String, PraybillItemVO> ctPraybillMapping =
        this.getCTPrayMapping(ctVOs);
    if (!ctPraybillMapping.isEmpty()) {
      PraybillItemVO praybillItem = null;
      for (OrderVO newVO : vos) {
        for (OrderItemVO itemVO : newVO.getBVO()) {
          if (itemVO.getPk_reqstoorg() == null) {
            praybillItem = ctPraybillMapping.get(itemVO.getCsourcebid());
            if (praybillItem != null) {
              itemVO.setPk_reqstoorg(praybillItem.getPk_org());
              itemVO.setPk_reqstoorg_v(praybillItem.getPk_org_v());
              itemVO.setPk_reqdept(praybillItem.getPk_reqdept());
              itemVO.setPk_reqdept_v(praybillItem.getPk_reqdept_v());
            }
          }
        }
      }
    }

    OrderVO[] newVOs = super.fillInformation(vos, srcVOs);

    for (OrderVO newVO : newVOs) {
      for (OrderItemVO itemVO : newVO.getBVO()) {
        String csourcebid = itemVO.getCsourcebid();
        CtPuBVO ctbvo = (CtPuBVO) index.get(voMeta, csourcebid);
        this.setPrice(itemVO, ctbvo);
      }
    }

    return newVOs;
  }

  @Override
  protected void fillVatInfo(OrderVO[] vos) {
    // vat 直接对照
    return;
  }

  @Override
  protected OrganizationDefaultValue getOrgDefaultValueSetter(
      BillHelper<OrderVO> helper) {
    OrganizationDefaultValue setter = super.getOrgDefaultValueSetter(helper);
    setter.setClear(false);
    return setter;
  }

  @Override
  protected void processMargin(OrderVO[] vos, AggregatedValueObject[] srcVos) {
    new OrderMarginRule(CTBillType.PurDaily.getCode(), srcVos).process(vos);
  }

  @Override
  protected void relationCalculate(RelationCalculate cal, OrderVO vo) {
    List<OrderItemVO> nozeroNumItems = new ArrayList<OrderItemVO>();
    for (OrderItemVO item : vo.getBVO()) {
      if (MathTool.isZero(item.getNnum())) {
        item.setNqtunitnum(UFDouble.ZERO_DBL);
        item.setNastnum(UFDouble.ZERO_DBL);
        item.setNnum(UFDouble.ZERO_DBL);
        continue;
      }
      nozeroNumItems.add(item);
    }
    // 2012-07-03 tianft：合同订单控制类型如果不控制数量，目前订单可以反复拉单，对于这种情况数量为0的情况只用单价反算
    if (nozeroNumItems.size() > 0) {
      OrderVO calVO = new OrderVO();
      calVO.setHVO(vo.getHVO());
      calVO
          .setBVO(nozeroNumItems.toArray(new OrderItemVO[nozeroNumItems.size()]));

      // 2013-01-31
      // lixyp：在根据单价进行联动计算的时候，如果报价数量为空有可能会使数量的计算出现误差，在此先根据主数量把辅数量和报价数量补上，后面的联动计算就不会出问题了。
      // int[] rows = new int[calVO.getBVO().length];
      // for (int i = 0; i < rows.length; ++i) {
      // rows[i] = i;
      // }
      //
      // 2015-08-12 sw 主数量和来源主数量一致时，认为是1次拉单，辅数量、报价数量直接带下来，不需要根据主数量计算
      OrderItemVO[] itemVOs = calVO.getBVO();
      List<Integer> rowList = new ArrayList<Integer>();
      int length = itemVOs.length;
      for (int i = 0; i < length; ++i) {
        if (MathTool
            .compareTo(itemVOs[i].getNnum(), itemVOs[i].getNsourcenum()) != 0) {
          rowList.add(Integer.valueOf(i));
        }
      }
      // 如果再计算会有误差，只有主数量不一致时才计算。
      if (rowList.size() > 0) {
        int[] rows =
            ArrayUtils
                .toPrimitive(rowList.toArray(new Integer[rowList.size()]));
        NumValueSetter setter = new NumValueSetter(new BillHelper<OrderVO>(vo));
        setter.setNastnum(rows);
        if (OrderVOUtil.isTaxPrior(vo.getHVO().getPk_org())) {
        	cal.calculate(vo, OrderItemVO.NQTORIGTAXPRICE);
        }
        else {
        	cal.calculate(vo, OrderItemVO.NQTORIGPRICE);
        }
      }
    }
  }
  
  /**
   * 判断是否是一次完整拉单，注：合单以及单据表体中存在主数量不相等的物料时都认为不是一次完整拉单
   * @param srcVOs
   * @param destVOs
   * @return
   */
	private boolean isSinglePull(OrderVO vo) {
		boolean isSinglePull = true;
			OrderItemVO[] bvo = vo.getBVO();
			for (OrderItemVO item : bvo) {
				if (MathTool.compareTo(item.getNnum(), item.getNsourcenum()) != 0) {
					isSinglePull = false;
					break;
				}
			}
		return isSinglePull;
	}

  @Override
	protected void setOrderPrice(OrderVO[] vos) {
		List<OrderVO> NoSinglePullVOs = new ArrayList<OrderVO>();
		for (OrderVO orderVO : vos) {
			boolean singlePull = this.isSinglePull(orderVO);
			if (!singlePull) {
				NoSinglePullVOs.add(orderVO);
			}
		}
		if (!NoSinglePullVOs.isEmpty()) {
			this.setPriceByParaPO16(NoSinglePullVOs
					.toArray(new OrderVO[NoSinglePullVOs.size()]));
		}
	}
}
