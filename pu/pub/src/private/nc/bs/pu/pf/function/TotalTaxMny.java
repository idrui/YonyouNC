package nc.bs.pu.pf.function;

import nc.vo.am.common.util.ArrayUtils;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>整单本币价税合计
 * </ul>
 * <p>
 * <p>
 * 
 * @since 6.0
 * @version 2011-4-15 下午03:28:50
 * @author xihy1
 */
public class TotalTaxMny {

  public static final String NTAXMNY = "ntaxmny";

  /**
   * 方法功能描述：返回VO整单价税合计
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return totalTaxMny
   */
  public UFDouble getTotalTaxMny(AggregatedValueObject vo) {
    if (null == vo) {
      return null;
    }

    CircularlyAccessibleValueObject[] aggVOs = vo.getChildrenVO();
    UFDouble totalTaxMny = UFDouble.ZERO_DBL;
    if (ArrayUtils.isEmpty(aggVOs)) {
      return null;
    }
    for (CircularlyAccessibleValueObject obj : aggVOs) {
      if (obj == null) {
        continue;
      }
      totalTaxMny =
          MathTool.add(totalTaxMny,
              (UFDouble) obj.getAttributeValue(TotalTaxMny.NTAXMNY));
    }
    if (UFDouble.ZERO_DBL == totalTaxMny) {
      return null;
    }

    return totalTaxMny;
  }
}
