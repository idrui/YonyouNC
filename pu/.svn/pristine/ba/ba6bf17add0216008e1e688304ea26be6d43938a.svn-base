/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-29 下午03:09:54
 */
package nc.vo.pu.m21.rule;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.util.TimeUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计划到货日期不能早于订单日期
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-29 下午03:09:54
 */
public class PlanArriveDateRule {

  /**
   * 方法功能描述：计划到货日期不能早于订单日期
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-9 上午09:23:41
   */
  public void checkPlanArriveDate(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    StringBuilder sb = new StringBuilder();

    for (OrderVO vo : vos) {
      if (null == vo) {
        continue;
      }

      OrderHeaderVO headerVO = vo.getHVO();
      if (null == headerVO) {
        continue;
      }

      // 订单日期
      UFDate dBillDate = headerVO.getDbilldate();
      if (null == dBillDate) {
        continue;
      }

      String vbillcode = headerVO.getVbillcode();
      vbillcode = StringUtil.isEmptyWithTrim(vbillcode) ? "" : vbillcode;
      boolean first = true;
      for (OrderItemVO itemVO : vo.getBVO()) {
        // 计划到货日期
        UFDate dPlanDate = itemVO.getDplanarrvdate();
        if (null == dPlanDate) {
          continue;
        }
        if (dBillDate.isSameDate(dPlanDate)) {
          continue;
        }
        if (TimeUtils.before(dPlanDate, dBillDate)) {
          if (first) {
            sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0285", null, new String[] {
                  vbillcode
                })/* 订单{0}：\n */);
            first = false;
          }
          sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004030_0",
              "04004030-0314",
              null,
              new String[] {
                itemVO.getCrowno(), String.valueOf(dPlanDate),
                String.valueOf(dBillDate)
              })/* 第{0}行计划到货日期{1}不能早于订单日期{2}\n */);
        }
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

}
