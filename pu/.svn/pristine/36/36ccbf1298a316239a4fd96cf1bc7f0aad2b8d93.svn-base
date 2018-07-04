/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-31 下午05:04:13
 */
package nc.vo.pu.m21.rule;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>判断订单是否关闭
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-31 下午05:04:13
 */
public class CloseCheck {

  /**
   * 方法功能描述：判断表体是否关闭
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVOs
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-7 下午06:08:50
   */
  public String closeCheck(OrderItemVO[] itemVOs) {
    if (ArrayUtils.isEmpty(itemVOs)) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    for (OrderItemVO itemVO : itemVOs) {
      // 判断激活状态
      if (this.isClose(itemVO)) {
        sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0299", null, new String[]{itemVO.getCrowno()})/*第{0}行已关闭\n*/);
      }
    }

    if (sb.length() > 0) {
      return sb.toString();
    }

    return null;
  }

  /**
   * 方法功能描述：判断订单是否关闭
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-4 下午04:55:21
   */
  public String closeCheck(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    StringBuilder sb = new StringBuilder();

    for (OrderVO vo : vos) {
      if ((null == vo) || ArrayUtils.isEmpty(vo.getBVO())) {
        continue;
      }

      boolean first = true;
      for (OrderItemVO itemVO : vo.getBVO()) {
        // 判断激活状态
        if (this.isClose(itemVO)) {
          if (first) {
            sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0283", null, new String[]{vo.getHVO().getVbillcode()})/*订单{0}\n*/);
            first = false;
          }

          sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0299", null, new String[]{itemVO.getCrowno()})/*第{0}行已关闭\n*/);
        }

      }
    }

    if (sb.length() > 0) {
      return sb.toString();
    }

    return null;
  }

  /**
   * 方法功能描述：订单行是否关闭
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-15 下午01:42:21
   */
  private boolean isClose(OrderItemVO itemVO) {
    if (null == itemVO) {
      return false;
    }

    // 判断激活状态
    Integer iActive = itemVO.getFisactive();
    return ObjectUtils.equals(iActive, EnumActive.CLOSE.value());
  }
}
