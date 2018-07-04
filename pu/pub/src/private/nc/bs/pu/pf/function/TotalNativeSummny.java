package nc.bs.pu.pf.function;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>得到一张单据的本币价税合计
 * </ul>
 * <p>
 * 
 * @since 6.0
 * @version 2011-4-15 下午04:16:22
 * @author xihy1
 */

public class TotalNativeSummny {
  /**
   * 该方法为注册函数，用于配于如审批中，作为一个是否可以审批通过的条件。
   */
  public UFDouble getTotalNativeSummny(AggregatedValueObject vo) {
    return new TotalTaxMny().getTotalTaxMny(vo);
  }

}
