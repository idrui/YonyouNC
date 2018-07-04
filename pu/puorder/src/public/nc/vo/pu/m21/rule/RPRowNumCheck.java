/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-17 下午01:17:29
 */
package nc.vo.pu.m21.rule;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货计划已到货数量不能大于数量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-17 下午01:17:29
 */
public class RPRowNumCheck {

  /**
   * 方法功能描述：到货计划已到货数量不能大于数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param rpVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-9 上午09:35:52
   */
  public void numCheck(OrderReceivePlanVO[] rpVOs) {
    if (ArrayUtils.isEmpty(rpVOs)) {
      return;
    }

    StringBuilder sb = new StringBuilder();
    for (OrderReceivePlanVO rpVO : rpVOs) {
      if (null == rpVO) {
        continue;
      }
      if (MathTool.compareTo(rpVO.getNaccumarrvnum(), rpVO.getNnum()) > 0
          || MathTool.compareTo(rpVO.getNaccumstorenum(), rpVO.getNnum()) > 0) {
        sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
            "04004030-0335", null, new String[] {
              rpVO.getVbillcode()
            })/* 到货计划{0}已到货数量不能大于数量\n */);
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }
}
