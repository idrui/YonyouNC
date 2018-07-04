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
 * <li>整单无税本币金额
 * </ul>
 * <p>
 * <p>
 * 
 * @since 6.0
 * @version 2011-4-15 下午03:23:28
 * @author xihy1
 */
public class TotalNoTaxMny {

  public static final String NMNY = "nmny";

  /**
   * 方法功能描述：根据单据VO返回单据整单无税本币金额.如果结果为零，则返回null
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return totalNoTaxmny
   */
  public UFDouble getTotalNoTaxMny(AggregatedValueObject vo) {
    if (null == vo) {
      return null;
    }

    CircularlyAccessibleValueObject[] aggVOs = vo.getChildrenVO();
    UFDouble totalNoTaxMny = UFDouble.ZERO_DBL;
    if (ArrayUtils.isEmpty(aggVOs)) {
      return null;
    }
    for (CircularlyAccessibleValueObject obj : aggVOs) {
      if (obj == null) {
        continue;
      }
      totalNoTaxMny =
          MathTool.add(totalNoTaxMny,
              (UFDouble) obj.getAttributeValue(TotalNoTaxMny.NMNY));
    }
    if (UFDouble.ZERO_DBL == totalNoTaxMny) {
      return null;
    }

    return totalNoTaxMny;
  }
}
