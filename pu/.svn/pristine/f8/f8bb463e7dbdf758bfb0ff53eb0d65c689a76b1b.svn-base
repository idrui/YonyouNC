/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-17 下午03:31:38
 */
package nc.vo.pu.m21.rule;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderReceivePlanUtils;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>累计到货计划数量不能大于订单数量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-17 下午03:31:38
 */
public class RPNaccumrpnumCheck {

  /**
   * 方法功能描述：累计到货计划数量不能大于订单数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param rpVOs
   * @param orderVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 上午08:48:01
   */
  public void checkNaccumrpnum(OrderReceivePlanVO[] rpVOs, OrderVO orderVO) {
    if (ArrayUtils.isEmpty(rpVOs) || null == orderVO) {
      return;
    }

    StringBuilder sb = new StringBuilder();
    OrderItemVO[] itemVOs =
        OrderReceivePlanUtils.getOrderItemVOs(rpVOs, orderVO);

    UFDouble dBRPNum = UFDouble.ZERO_DBL;
    for (int i = 0; i < itemVOs.length; ++i) {
      if (null == itemVOs[i]) {
        continue;
      }

      dBRPNum = this.getRPNumForBId(rpVOs, itemVOs[i].getPk_order_b());
      if (MathTool.compareTo(dBRPNum, itemVOs[i].getNnum()) > 0) {
        sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004030_0",
            "04004030-0334",
            null,
            new String[] {
              itemVOs[i].getCrowno(), String.valueOf(dBRPNum),
              String.valueOf(itemVOs[i].getNnum())
            })/* 订单行号{0}：到货计划数量总和{1}超出订单数量{2} ，请修改\n */);
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

  /**
   * 方法功能描述：得到某订单行的到货计划数量 =SUM（非删除行数量）
   * <p>
   * <b>参数说明</b>
   * 
   * @param rpVOs
   * @param pkOrderB
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 上午08:54:27
   */
  private UFDouble getRPNumForBId(OrderReceivePlanVO[] rpVOs, String pkOrderB) {
    if (ArrayUtils.isEmpty(rpVOs) || StringUtil.isEmptyWithTrim(pkOrderB)) {
      return null;
    }

    UFDouble dSumRPNum = UFDouble.ZERO_DBL;
    for (int i = 0; i < rpVOs.length; ++i) {
      if (ObjectUtils.equals(pkOrderB, rpVOs[i].getPk_order_b())
          && rpVOs[i].getStatus() != VOStatus.DELETED) {
        dSumRPNum = MathTool.add(dSumRPNum, rpVOs[i].getNnum());
      }
    }

    return dSumRPNum;
  }
}
