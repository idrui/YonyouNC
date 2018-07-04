/**
 * 寻源处理规则类
 */
package nc.vo.pu.m21.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmf.sourcing.entity.SourceReturnVO;
import nc.vo.scmf.sourcing.entity.SupplierPrice;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>寻源处理规则
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-19 下午01:45:13
 */
public class QuerySourceRule implements IRule<OrderVO> {

  /** 寻源返回结果vo与上游调用这bid的map映射 */
  private Map<String, SourceReturnVO> bidSrcRtnMap;

  /**
   * 寻源返回的vo，包含组织、供应商价格信息等
   */
  private SourceReturnVO[] sourceReturnVOs;

  public QuerySourceRule(SourceReturnVO[] sourceReturnVOs) {
    this.sourceReturnVOs = sourceReturnVOs;
    // map初始化
    this.initReturnMap(sourceReturnVOs);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    if (ArrayUtils.isEmpty(this.sourceReturnVOs)) {
      // ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
      // .getStrByID("4004030_0", "04004030-0354")/*
      // * @res
      // * "寻源vo数组为空！"
      // */);
      TimeLog.logStart();
      TimeLog.info("寻源vo数组为空！")/* -=notranslate=- */;
      return;
    }
    // 根据寻源结果，补全价格等信息
    this.fillQuerySourceInfo(vos);
    // 寻源未果，补报价单位信息
    this.fillQtUnitInfo(vos);
    // 根据订单询价参数进行询价
    this.setDefaultPriceByPara(vos);

  }

  private boolean exitsPrice(SupplierPrice price) {
    return price != null
        && (price.getNorigprice() != null || price.getNorigtaxprice() != null
            || price.getNorigprice() != null || price.getNorigtaxprice() != null);
  }

  /**
   * 报价单位信息补充
   * 
   * @param vos
   */
  private void fillQtUnitInfo(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      for (OrderItemVO item : vo.getBVO()) {
        if (StringUtils.isEmpty(item.getCqtunitid())) {
          item.setCqtunitid(item.getCastunitid());
          item.setNqtunitnum(item.getNastnum());
        }
      }
    }
  }

  /**
   * <p>
   * 使用场景：
   * <ul>
   * <li>根据寻源结果，补全价格等信息
   * </ul>
   * 
   * @param vos
   */
  private void fillQuerySourceInfo(OrderVO[] vos) {
    for (int i = 0; i < vos.length; i++) {
      OrderItemVO[] itemvos = vos[i].getBVO();
      OrderHeaderVO headerVO = vos[i].getHVO();
      for (OrderItemVO itemvo : itemvos) {
        // 根据来源bid获得对应的寻源结果
        SourceReturnVO srcRtnVO = this.bidSrcRtnMap.get(itemvo.getCsourcebid());
        if (srcRtnVO == null || !this.exitsPrice(srcRtnVO.getSupplierPrice())) {
          TimeLog.logStart();
          TimeLog.info("寻源数组未传递，或者未寻到价格"); /* -=notranslate=- */
          continue;
        }
        SupplierPrice price = srcRtnVO.getSupplierPrice();

        // 主无税单价
        itemvo.setNorigprice(price.getNorigprice());
        // 主含税单价
        itemvo.setNorigtaxprice(price.getNorigtaxprice());
        // 无税单价
        itemvo.setNqtorigprice(price.getNorigprice());
        // 含税单价
        itemvo.setNqtorigtaxprice(price.getNorigtaxprice());
        // 税率
        itemvo.setNtaxrate(price.getNtaxrate());
        // 报价单位
        itemvo.setCqtunitid(price.getCastunitid());
        // 原币币种
        if (headerVO.getCorigcurrencyid() == null) {
          headerVO.setCorigcurrencyid(price.getCurrency());
        }
      }
    }
  }

  /**
   * 寻源未寻到价格的vo
   * 
   * @param vos
   * @return
   */
  private OrderVO[] filterNoPriceBills(OrderVO[] vos) {
    List<OrderVO> bills = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      List<OrderItemVO> items = new ArrayList<OrderItemVO>();
      for (OrderItemVO item : vo.getBVO()) {
        if (UFDouble.ZERO_DBL.equals(MathTool.nvl(item.getNorigprice()))
            && UFDouble.ZERO_DBL.equals(MathTool.nvl(item.getNorigtaxprice()))
            && UFDouble.ZERO_DBL.equals(MathTool.nvl(item.getNqtorigprice()))
            && UFDouble.ZERO_DBL
                .equals(MathTool.nvl(item.getNqtorigtaxprice()))) {
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
      return bills.toArray(new OrderVO[bills.size()]);
    }
    return null;
  }

  /**
   * 构造寻源返回结果vo与上游调用这bid的map映射
   * 
   * @param sourceReturnVOs 寻源结果返回vo数组
   */
  private void initReturnMap(SourceReturnVO[] srcReturnVOs) {
    this.bidSrcRtnMap = new HashMap<String, SourceReturnVO>();
    if (ArrayUtils.isEmpty(srcReturnVOs)) {
      return;
    }
    for (SourceReturnVO vo : srcReturnVOs) {
      if (StringUtils.isEmpty(vo.getParaBillRowId())) {
        // 这里不属于业务校验，如果有此种情况，请开发添加paraBillRowId的处理
        ExceptionUtils
            .wrappBusinessException("the paraBillRowId in SourceReturnVO is must. please assign source bill pk value to it.");/*
                                                                                                                               * -=
                                                                                                                               * notranslate
                                                                                                                               * =
                                                                                                                               * -
                                                                                                                               */
      }
      this.bidSrcRtnMap.put(vo.getParaBillRowId(), vo);
    }
  }

  /**
   * 设置价格
   * 
   * @param vos
   */
  private void setDefaultPrice(OrderVO[] vos) {
    RelationCalculate cal = new RelationCalculate();
    for (OrderVO vo : vos) {
      BillHelper<OrderVO> bill = new BillHelper<OrderVO>(vo);
      PriceQuoter priceQuoter = new PriceQuoter(bill);
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

  private void setDefaultPriceByPara(OrderVO[] vos) {
    OrderVO[] filterVOs = this.filterNoPriceBills(vos);
    if (ArrayUtils.isEmpty(filterVOs)) {
      return;
    }
    this.setDefaultPrice(filterVOs);
  }
}
