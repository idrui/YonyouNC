/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-16 上午11:39:37
 */
package nc.bs.pu.est.rule.pricequery;

import nc.vo.pu.est.rule.IEstPriceQueryInfoProvide;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总暂估取订单价
 * <li>根据需求，直接取消耗汇总收记录的价格
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-16 上午11:39:37
 */
public class VMIEstOrderPriceQryStrategy extends AbstractEstPriceQueryStrategy {

  private void setPrice(EstPriceQryResltData reslt,
      IEstPriceQueryInfoProvide info) {
    reslt.setOrigPrice(info.getBillOPrice());
    reslt.setOrigTaxPrice(info.getBillOTaxPrice());
    reslt.setPrice(info.getBillPrice());
    reslt.setTaxPrice(info.getBillTaxPrice());
  }

  @Override
  protected void procResltData(EstPriceQryResltData[] resltData,
      IEstPriceQueryInfoProvide[] pqinfo) {
    for (int i = 0; i < pqinfo.length; i++) {
      IEstPriceQueryInfoProvide info = pqinfo[i];
      EstPriceQryResltData reslt = resltData[i];
      this.setPrice(reslt, info);
    }
  }

}
