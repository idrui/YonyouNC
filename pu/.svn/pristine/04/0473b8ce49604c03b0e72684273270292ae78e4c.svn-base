/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-27 上午11:14:02
 */
package nc.vo.pu.m21.rule;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>检查后续单据
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-27 上午11:14:02
 */
public class DownFlowCheck {

  /**
   * 方法功能描述：某一订单行是否存在后续单据
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-15 下午02:38:48
   */
  public boolean hasDownFlow(OrderItemVO itemVO) {
    if (null == itemVO) {
      return false;
    }

    // 判断累计发票数量 ，累计入库数量 ，累计到货数量，确定是否有后续单据
    return MathTool.compareTo(itemVO.getNaccuminvoicenum(), UFDouble.ZERO_DBL) > 0
        || MathTool.compareTo(itemVO.getNaccumstorenum(), UFDouble.ZERO_DBL) > 0
        || MathTool.compareTo(itemVO.getNaccumarrvnum(), UFDouble.ZERO_DBL) > 0;
  }

  /**
   * 方法功能描述：检查表体后续单据
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVOs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-7 下午06:04:09
   */
  public String hasDownFlow(OrderItemVO[] itemVOs) {
    if (ArrayUtils.isEmpty(itemVOs)) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    for (OrderItemVO itemVO : itemVOs) {
      // 判断是否有后续单据
      if (this.hasDownFlow(itemVO)) {
        sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0301", null, new String[]{itemVO.getCrowno()})/*第{0}行存在后续单据\n*/);
      }
    }

    if (sb.length() > 0) {
      return sb.toString();
    }

    return null;
  }

  /**
   * 方法功能描述：检查某一订单有没有后续单据
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-16 下午03:09:53
   */
  public boolean hasDownFlow(OrderVO orderVO) {
    if (null == orderVO || ArrayUtils.isEmpty(orderVO.getBVO())) {
      return false;
    }

    for (OrderItemVO itemVO : orderVO.getBVO()) {
      if (this.hasDownFlow(itemVO)) {
        return true;
      }
    }

    return false;
  }

  /**
   * 方法功能描述：检查订单有没有后续单据
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-7 下午05:59:22
   */
  public String hasDownFlow(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    for (OrderVO vo : vos) {
      if (null == vo || ArrayUtils.isEmpty(vo.getBVO())) {
        continue;
      }

      boolean first = true;
      for (OrderItemVO itemVO : vo.getBVO()) {
        // 判断是否有后续单据
        if (this.hasDownFlow(itemVO)) {
          if (first) {
            sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0302", null, new String[]{vo.getHVO().getVbillcode()})/*采购订单{0}：\n*/);
            first = false;
          }

          sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0301", null, new String[]{itemVO.getCrowno()})/*第{0}行存在后续单据\n*/);
        }
      }
    }

    if (sb.length() > 0) {
      return sb.toString();
    }

    return null;
  }

}
