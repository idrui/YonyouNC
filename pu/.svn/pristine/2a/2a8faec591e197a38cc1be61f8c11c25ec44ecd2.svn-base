package nc.pubimpl.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDate;

import org.apache.commons.lang.ArrayUtils;

/**
 * 如果计划到货日期<订单日期，计划到货日期取订单日期
 * 2012-7-5 王印芬、吴小亮、阙颖共同确认，bug号NCdp203938968
 * 
 * @since 6.0
 * @version 2012-7-5 下午03:06:42
 * @author wuxla
 */
public class PlanArrDateOrderDateRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    for (OrderVO vo : vos) {
      if (null == vo) {
        continue;
      }
      OrderItemVO[] itemVOs = vo.getBVO();
      if (null == itemVOs) {
        continue;
      }
      UFDate dbilldate = vo.getHVO().getDbilldate();
      if (null == dbilldate) {
        continue;
      }
      for (OrderItemVO itemVO : itemVOs) {
        if (itemVO.getDplanarrvdate() == null) {
          continue;
        }
        if (itemVO.getDplanarrvdate().before(dbilldate)) {
          itemVO.setDplanarrvdate(dbilldate);
        }
      }
    }
  }
}
