/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-27 上午11:10:07
 */
package nc.bs.pu.est.rule.pricequery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.pub.IOrderPubQuery;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.rule.IEstPriceQueryInfoProvide;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估询订单价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-27 上午11:10:07
 */
public class EstOrderPriceQryStrategy extends AbstractEstPriceQueryStrategy {

  /** 判断数量是否为大于0的标志 */
  UFDouble ispositive = new UFDouble(1);

  /**
   * 为了保正金额的正负和数量一样
   * 
   * @param value
   * @param positive
   * @return
   */
  private UFDouble getCorrectValue(UFDouble value) {
    if (value == null) {
      return null;
    }
    return value.abs().multiply(this.ispositive);
  }

  /**
   * 方法功能描述：得到订单行ID。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pqinfo
   *          询价信息提供接口
   * @return 订单行ID数组
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 上午10:28:18
   */
  private String[] getOrderBID(IEstPriceQueryInfoProvide[] pqinfo) {
    List<String> bids = new ArrayList<String>();
    for (IEstPriceQueryInfoProvide info : pqinfo) {
      String bid = info.getPk_order_b();
      if (!StringUtil.isEmptyWithTrim(bid)) {
        bids.add(bid);
      }
    }
    return bids.toArray(new String[bids.size()]);
  }

  /**
   * 方法功能描述：调用订单服务进行查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bids
   *          订单行ID
   * @return Map<String,订单行VO>
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 上午10:28:51
   */
  private Map<String, OrderItemVO> queryOrder(String[] bids) {
    OrderItemVO[] items = null;
    try {
      items =
          NCLocator.getInstance().lookup(IOrderPubQuery.class)
              .queryOrderItem(bids);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return ArrayUtils.isEmpty(items) ? null : CirVOUtil.createKeyVOMap(items);
  }

  /**
   * 方法功能描述：设置询价结果信息的金额等信息。
   * <p>
   * <b>参数说明</b>
   * 
   * @param reslt
   *          询价结果信息
   * @param item
   *          订单行VO
   * @param info
   *          询价信息提供接口
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 上午10:30:19
   */
  private void setMny(EstPriceQryResltData reslt, OrderItemVO item,
      IEstPriceQueryInfoProvide info) {
    UFDouble ponum = item.getNnum();
    String pk_pocurr = item.getCorigcurrencyid();
    UFDouble potaxrate = item.getNtaxrate();
    UFDouble poexchgrate = item.getNexchangerate();
    int potaxtype = item.getFtaxtypeflag().intValue();
    UFDouble num = info.getnum();
    String pk_curr = info.getorigcurr();
    String pk_loccurr = info.getPk_loccurr();
    UFDouble taxrate = info.getTaxRate();
    UFDouble exchgrate = info.getExchgRate();
    int taxtype = info.getTaxtype();
    // wuxla V61 需要加上VAT相关金额，并判断不可抵扣税率是否相同
    UFDouble nnosubtaxrate = info.getNestnosubtaxrate();
    UFDouble ponosubtaxrate = item.getNnosubtaxrate();
    // 如果数量小于0，将标志置为-1；
    if (num.doubleValue() < 0) {
      this.ispositive = new UFDouble(-1);
    }else{
      this.ispositive = new UFDouble(1);
    }
    if (0 == MathTool.absCompareTo(ponum, num)
        && 0 == MathTool.compareTo(potaxrate, taxrate)
        && 0 == MathTool.compareTo(ponosubtaxrate, nnosubtaxrate)
        && pk_pocurr.equals(pk_curr)
        && (pk_pocurr.equals(pk_loccurr) || poexchgrate.equals(exchgrate))
        && potaxtype == taxtype) {
      reslt.setMny(this.getCorrectValue(item.getNmny()));
      reslt.setTax(this.getCorrectValue(item.getNtax()));
      reslt.setTotalmny(this.getCorrectValue(item.getNtaxmny()));
      reslt.setOrigmny(this.getCorrectValue(item.getNorigmny()));
      // reslt.setOrigtax(item.getNorigtax());
      reslt.setOrigtotalmny(this.getCorrectValue(item.getNorigtaxmny()));
      reslt.setNeedCalc(false);

      // wuxla V61
      reslt.setNosubtax(this.getCorrectValue(item.getNnosubtax()));
      reslt.setCalcostmny(this.getCorrectValue(item.getNcalcostmny()));
      reslt.setCaltaxmny(this.getCorrectValue(item.getNcaltaxmny()));
    }
  }

  /**
   * 方法功能描述：设置询价结果信息的价格。
   * <p>
   * <b>参数说明</b>
   * 
   * @param reslt
   *          询价结果信息
   * @param item
   *          订单行VO
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-7-20 上午10:29:30
   */
  private void setPrice(EstPriceQryResltData reslt, OrderItemVO item) {
    reslt.setOrigPrice(item.getNorignetprice());
    reslt.setOrigTaxPrice(item.getNorigtaxnetprice());
    reslt.setPrice(item.getNnetprice());
    reslt.setTaxPrice(item.getNtaxnetprice());
  }

  @Override
  protected void procResltData(EstPriceQryResltData[] resltData,
      IEstPriceQueryInfoProvide[] pqinfo) {
    String[] bids = this.getOrderBID(pqinfo);
    if (ArrayUtils.isEmpty(bids)) {
      return;
    }
    Map<String, OrderItemVO> poItemMap = this.queryOrder(bids);
    if (null == poItemMap || 0 == poItemMap.size()) {
      return;
    }
    for (int i = 0; i < pqinfo.length; i++) {
      IEstPriceQueryInfoProvide info = pqinfo[i];
      String pobid = info.getPk_order_b();
      if (StringUtil.isEmptyWithTrim(pobid) || !poItemMap.containsKey(pobid)) {
        continue;
      }
      OrderItemVO item = poItemMap.get(pobid);
      EstPriceQryResltData reslt = resltData[i];
      this.setPrice(reslt, item);// 取订单价
      this.setMny(reslt, item, info);// 符合条件，则取订单金额信息
    }
  }

}
