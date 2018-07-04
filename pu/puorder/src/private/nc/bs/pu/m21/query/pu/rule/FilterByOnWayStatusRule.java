/**
 * $文件说明$
 *
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-19 下午08:56:04
 */
package nc.bs.pu.m21.query.pu.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.AssertUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据在途状态为过滤掉订单VO,为到货单查询提供服务
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-19 下午08:56:04
 */
public class FilterByOnWayStatusRule implements IFilterRule<OrderVO> {

  StatusOnWayItemVO[] statusVO = null;

  public FilterByOnWayStatusRule(StatusOnWayItemVO[] statusVO) {
    this.statusVO = statusVO;
  }

  @Override
  public OrderVO[] process(OrderVO[] vos) {

    Map<String, UFDouble> numMap = this.getNumMap();

    // 设置可到货数量
    for (OrderVO orderVO : vos) {
      OrderItemVO[] bvos = orderVO.getBVO();
      for (OrderItemVO bvo : bvos) {
        bvo.setNcanarrivenum(numMap.get(bvo.getPk_order_b()));
      }
    }

    return vos;
  }

  /**
   * 方法功能描述：抽出到货状态的在途数量
   * <p>
   * <b>参数说明</b>
   *
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-15 下午04:19:34
   */
  private Map<String, UFDouble> getNumMap() {
    Map<String, UFDouble> numMap = new HashMap<String, UFDouble>();
    for (StatusOnWayItemVO vo : this.statusVO) {
      UFDouble onwayNum = vo.getNonwaynum();
      AssertUtils.assertValue(onwayNum != null, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0137")/*@res "到货在途数量不应该为空!"*/);
      numMap.put(vo.getPk_order_b(), onwayNum);
    }
    return numMap;
  }
}