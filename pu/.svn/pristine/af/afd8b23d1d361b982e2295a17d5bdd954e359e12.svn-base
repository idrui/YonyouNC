/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-17 下午01:46:38
 */
package nc.vo.pu.m21.rule;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>检查到货计划有后续单据或相关订单行已关闭
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-17 下午01:46:38
 */
public class RPDownFlowOrCloseCheck {

  /**
   * 方法功能描述：到货计划有后续单据或相关订单行已关闭
   * <p>
   * <b>参数说明</b>
   * 
   * @param rpVOs
   * @param orderVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 下午02:01:30
   */
  public String downFlowOrCloseCheck(OrderReceivePlanVO[] rpVOs, OrderVO orderVO) {
    if (ArrayUtils.isEmpty(rpVOs) || null == orderVO) {
      return null;
    }

    StringBuilder sb = new StringBuilder();
    // Map<String, OrderItemVO> map = AggVOUtil.createItemMap(new OrderVO[] {
    // orderVO
    // });
    BillIndex index = new BillIndex(new OrderVO[] {
      orderVO
    });
    IVOMeta meta = orderVO.getMetaData().getVOMeta(OrderItemVO.class);

    for (OrderReceivePlanVO rpVO : rpVOs) {
      // OrderItemVO itemVO = map.get(rpVO.getPk_order_b());
      OrderItemVO itemVO = (OrderItemVO) index.get(meta, rpVO.getPk_order_b());
      UFDouble naccumarrvnum = rpVO.getNaccumreceivenum();
      // 关闭
      if (this.isClose(itemVO)
          || MathTool.compareTo(naccumarrvnum, UFDouble.ZERO_DBL) > 0) {
        sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
            "04004030-0332", null, new String[] {
              rpVO.getVbillcode()
            })/* 到货计划{0}有后续单据或相关订单行已关闭\n */);
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
