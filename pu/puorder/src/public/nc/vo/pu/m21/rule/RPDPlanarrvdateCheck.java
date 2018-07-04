/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-17 下午03:02:49
 */
package nc.vo.pu.m21.rule;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计划到货日期不早于订单日期
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-17 下午03:02:49
 */
public class RPDPlanarrvdateCheck {

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param rpVOs
   * @param orderVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 下午03:04:28
   */
  public void checkDPlanarrvdate(OrderReceivePlanVO[] rpVOs, OrderVO orderVO) {
    if (ArrayUtils.isEmpty(rpVOs) || null == orderVO
        || null == orderVO.getHVO()) {
      return;
    }

    UFDate dbilldate = orderVO.getHVO().getDbilldate();
    if (null == dbilldate) {
      return;
    }

    StringBuilder sb = new StringBuilder();
    for (OrderReceivePlanVO rpVO : rpVOs) {
      if (null == rpVO) {
        continue;
      }

      UFDate dplanarrvdate = rpVO.getDplanarrvdate();
      if (dplanarrvdate == null) {
        continue;
      }
      if (dplanarrvdate.isSameDate(dbilldate)) {
        continue;
      }
      if (dplanarrvdate.before(dbilldate)) {
        sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
            "04004030-0333", null, new String[] {
              rpVO.getVbillcode()
            })/* 到货计划{0}计划到货日期不能早于订单日期\n */);
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }
}
