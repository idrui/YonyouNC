package nc.vo.pu.m21.rule;

import java.util.Iterator;
import java.util.List;

import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.util.VORowNoUtils;

/**
 * @since 6.0
 * @version 2011-1-23 ÏÂÎç04:33:05
 * @author wuxla
 */

public class PayPlanRowNoSetter {

  public static void setRowNo(PayPlanViewVO toVO, List<Object> list) {
    String toHid = toVO.getPk_order();
    UFDouble max = UFDouble.ZERO_DBL;
    for (Iterator<Object> iter = list.iterator(); iter.hasNext();) {
      PayPlanViewVO view = (PayPlanViewVO) iter.next();
      if (null == view) {
        continue;
      }
      if (!toHid.equals(view.getPk_order())) {
        continue;
      }

      UFDouble crowno = VORowNoUtils.getUFDouble(view.getCrowno());
      if (max.compareTo(crowno) < 0) {
        max = crowno;
      }
    }

    max = max.add(VORowNoUtils.STEP_VALUE);

    toVO.setCrowno(VORowNoUtils.getCorrectString(max));
  }
}
