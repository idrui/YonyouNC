/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-21 上午11:53:25
 */
package nc.ui.pu.est.rule;

import java.util.ArrayList;
import java.util.List;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>对费用暂估的行时行过滤
 * <li>如果用户未录入金额，则认为不想进行暂估，则过滤掉
 * <li>如果有主键，说明已经暂估或结算，则也过滤掉
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-21 上午11:53:25
 */
public class EstFilterZeroFeeRule {
  public AggregatedValueObject[] filter(AggregatedValueObject[] vos) {
    for (AggregatedValueObject vo : vos) {
      CircularlyAccessibleValueObject[] items = vo.getChildrenVO();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }
      List<CircularlyAccessibleValueObject> newItems =
          new ArrayList<CircularlyAccessibleValueObject>();
      for (CircularlyAccessibleValueObject item : items) {
        String pk = null;
        try {
          pk = item.getPrimaryKey();
        }
        catch (BusinessException e) {
          // 日志异常
          ExceptionUtils.wrappException(e);
        }
        UFDouble mny = (UFDouble) item.getAttributeValue(FeeEstVO.NESTMNY);
        if (StringUtil.isEmptyWithTrim(pk)
            && (0 != MathTool.compareTo(mny, UFDouble.ZERO_DBL))) {
          newItems.add(item);
        }
      }
      if (0 == newItems.size()) {
        vo.setChildrenVO(null);
      }
      else {
        CircularlyAccessibleValueObject[] nitems =
            new ListToArrayTool<CircularlyAccessibleValueObject>()
                .convertToArray(newItems);
        vo.setChildrenVO(nitems);
      }
    }
    return vos;
  }
}
