package nc.vo.pu.m21.vochange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.pubitf.ct.purdaily.ICtQueryForOrder;
import nc.pubitf.scmf.sourcing.sour4pu.ISourcePUService;
import nc.vo.ct.purdaily.entity.CtPubillViewVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.PriceQuoter;
import nc.vo.pu.m21.rule.RelationCalculate;
import nc.vo.pu.m21.rule.RowNoRule;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmf.sourcing.entity.SourceVO;
import nc.vo.scmf.sourcing.entity.SupplierPrice;
import nc.vo.scmpub.res.billtype.ECBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 与电子采购排程计划的vo交换后期处理类
 * 
 * @since 6.0
 * @version 2012-10-25 下午01:56:36
 * @author lixyp
 */
public class ChangeVOAdjustEC49To21 extends AbstractOrderChangeVOAdjust {

  private static final String EC49TYPEID = "1001ZF10000000006X79";

  /**
   * 根据单价联动计算。
   * 电商排程有一个净价字段，一般如果自动推单没有寻到价的话，会手工维护这个字段，然后手工推单。
   * 这个字段会通过vo交换对过来，订单询价的第一步就是判断如果这个字段有值就根据它来联动，不询价了。
   * 
   * @param vos 采购订单聚合vo
   * @return 没有价格的表体VO集合。
   */
  private List<OrderItemVO> calcByNqtorigtaxnetprc(OrderVO[] vos) {
    RelationCalculate cal = new RelationCalculate();
    List<OrderItemVO> retList = new ArrayList<OrderItemVO>();
    UFDouble discount = new UFDouble(100.00);
    for (OrderVO vo : vos) {
      List<OrderItemVO> calItemVOList = new ArrayList<OrderItemVO>();
      for (OrderItemVO itemVo : vo.getBVO()) {
        if (itemVo.getNitemdiscountrate() == null) {
          itemVo.setNitemdiscountrate(discount);
        }
        if (itemVo.getNqtorigtaxprice() != null) {
          calItemVOList.add(itemVo);
        }
        else {
          retList.add(itemVo);
        }
      }

      if (calItemVOList.size() > 0) {
        OrderVO calVo = new OrderVO();
        calVo.setHVO(vo.getHVO());
        calVo
            .setBVO(calItemVOList.toArray(new OrderItemVO[calItemVOList.size()]));
        cal.calculate(calVo, OrderItemVO.NQTORIGTAXPRICE);
      }
    }

    return retList;
  }

  @Override
  protected String[] getNumStrs() {
    return new String[]{"main_num", "num", "num"};
  }
  
  /**
   * 设置价格
   * 
   * @param vos
   */
  private void setDefaultPrice(OrderVO[] vos) {
    RelationCalculate cal = new RelationCalculate();
    for (OrderVO vo : vos) {
      PriceQuoter priceQuoter = new PriceQuoter(new BillHelper<OrderVO>(vo));
      Integer[] rows = new Integer[vo.getBVO().length];
      for (int i = 0; i < rows.length; i++) {
        rows[i] = Integer.valueOf(i);
      }
      Map<Integer, String> changeRow = priceQuoter.setDefaultPrice(rows);
      OrderVO calVo = new OrderVO();
      for (Entry<Integer, String> entry : changeRow.entrySet()) {
        calVo.setHVO(vo.getHVO());
        calVo.setBVO(new OrderItemVO[] {
          vo.getBVO()[entry.getKey().intValue()]
        });
        cal.calculate(calVo, entry.getValue());
      }
    }
  }

  /**
   * 分四步定采购订单价。
   * 
   * @param vos 订单聚合vo
   * @throws BusinessException
   */
  private void setPrice(OrderVO[] vos) {
    // 1. 根据单价联动计算。（VO对照由对照净价改为对照单价）
    List<OrderItemVO> calcRet = this.calcByNqtorigtaxnetprc(vos);
    // 2. 根据来源总括订单寻价。
    List<OrderItemVO> ctRet = this.setPriceByCT(calcRet);
    // 3. 根据寻源算法寻价。
    this.setPriceBySourcing(ctRet, vos);
    // 4. 根据订单询价参数寻价
    this.setPriceByPara(vos);
  }

  /**
   * 从总括订单询价，总括订单从来源取。
   * 
   * @param vos 订单聚合VO数组
   * @return 没有从总括订单取得单价的表体集合。
   */
  private List<OrderItemVO> setPriceByCT(List<OrderItemVO> calcRet) {
    // 拼装总括订单查询参数。
    List<String> bidList = new ArrayList<String>();
    for (OrderItemVO itemVo : calcRet) {
      if (itemVo.getCsourcebid() != null) {
        bidList.add(itemVo.getCsourcebid());
      }
    }

    // 调用总括订单接口查询。
    Map<String, CtPubillViewVO> viewMap = null;
    if (bidList.size() > 0) {
      ICtQueryForOrder ctQuery =
          NCLocator.getInstance().lookup(ICtQueryForOrder.class);
      try {
        viewMap =
            ctQuery.queryViewVOsByItemPks(bidList.toArray(new String[bidList
                .size()]));
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }

    // 找出没有寻到价的表体VO，放入itemVoList，准备下一步的询价。
    List<OrderItemVO> itemVoList = new ArrayList<OrderItemVO>();
    CtPubillViewVO viewVo = null;
    for (OrderItemVO itemVo : calcRet) {
      if (itemVo.getNqtorigprice() != null
          && itemVo.getNqtorigtaxprice() != null) {
        continue;
      }
      // 如果总括订单的查询结果就是空，那么所有表体都将进入下一步询价。
      if (viewMap == null) {
        itemVoList.add(itemVo);
      }
      else {
        // 找到对应询价结果并赋值。
        viewVo = viewMap.get(itemVo.getCsourcebid());
        if (viewVo != null) {
          itemVo.setNqtorigprice(viewVo.getNqtorigprice());
          itemVo.setNqtorigtaxprice(viewVo.getNqtorigtaxprice());
        }

        // 无税单价和含税单价只要有一个为空就进入下一步询价。
        if (itemVo.getNqtorigprice() == null
            || itemVo.getNqtorigtaxprice() == null) {
          itemVoList.add(itemVo);
        }
      }
    }
    return itemVoList;
  }

  /**
   * 根据参数寻单价。
   * 
   * @param vos 没有寻到价格的采购订单聚合VO数组。
   */
  private void setPriceByPara(OrderVO[] vos) {
    List<OrderVO> bills = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      List<OrderItemVO> items = new ArrayList<OrderItemVO>();
      for (OrderItemVO item : vo.getBVO()) {
        if (MathTool.isZero(item.getNqtorigprice())
            && MathTool.isZero(item.getNqtorigtaxprice())) {
          items.add(item);
        }
      }
      if (items.size() > 0) {
        OrderVO bill = new OrderVO();
        bill.setHVO(vo.getHVO());
        bill.setBVO(items.toArray(new OrderItemVO[items.size()]));
        bills.add(bill);
      }
    }
    if (bills.size() > 0) {
      this.setDefaultPrice(bills.toArray(new OrderVO[bills.size()]));
    }
  }

  /**
   * 根据寻源算法寻采购订单价。
   * 
   * @param itemVoList 没有从总括订单寻到单价的表体集合。
   */
  private void setPriceBySourcing(List<OrderItemVO> itemVoList, OrderVO[] vos) {
    if (itemVoList.isEmpty()) {
      return;
    }
    // 拼装寻源算法所需的参数。
    SourceVO[] sourceVos = new SourceVO[itemVoList.size()];
    String[] puOrgs = new String[itemVoList.size()];
    for (int i = 0; i < itemVoList.size(); i++) {
      SourceVO sourcevo = new SourceVO();
      sourcevo.setPk_mar(itemVoList.get(i).getPk_material());
      sourcevo.setSCflag(UFBoolean.FALSE);
      sourcevo.setCastunitid(itemVoList.get(i).getCastunitid());
      sourceVos[i] = sourcevo;

      puOrgs[i] = itemVoList.get(i).getPk_org();
    }

    // 调用寻源算法接口。
    ISourcePUService sourcePuService =
        NCLocator.getInstance().lookup(ISourcePUService.class);
    SupplierPrice[] prices = new SupplierPrice[itemVoList.size()];
    try {
      prices = sourcePuService.queryForOrderBill(sourceVos, puOrgs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    // 根据寻源结果赋值。
    SupplierPrice price = null;
    for (int i = 0; i < prices.length; i++) {
      price = prices[i];
      if (price != null) {
        itemVoList.get(i).setNqtorigprice(price.getNorigprice());
        itemVoList.get(i).setNqtorigtaxprice(price.getNorigtaxprice());
      }
    }

    // mengjian 询完价格后需要联动计算
    RelationCalculate cal = new RelationCalculate();
    for (OrderVO vo : vos) {
      cal.calculate(vo, OrderItemVO.NQTORIGTAXPRICE);
    }

  }

  /**
   * 由于EC单据类型的ID和CODE不一致，在这里转换一下。
   * 
   * @param vos 订单vo数组
   */
  private void setSourceBilltype(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      for (OrderItemVO itemVo : vo.getBVO()) {
        if (ECBillType.EC49.getCode().equals(itemVo.getCfirsttypecode())) {
          itemVo.setCfirsttypecode(ChangeVOAdjustEC49To21.EC49TYPEID);
        }
      }
    }
  }

  @Override
  protected OrderVO[] fillInformation(OrderVO[] vos,
      AggregatedValueObject[] srcVOs) {
    // 设置行号
    new RowNoRule().process(vos);
    this.setSourceBilltype(vos);
    // mengjian 先补税率
    this.fillVatInfo(vos);
    super.fillInformation(vos, srcVOs);
    // 询价格
    this.setPrice(vos);
    // 临时代码，补交易类型，平台提供接口后去掉下面的逻辑。
    // new TrantypeValueRule(vos).setTrantypeValue();
    // 后台推单，需要补充流程。
    PfServiceScmUtil.setBusiType(vos, POBillType.Order.getCode());
    return vos;
  }
}
