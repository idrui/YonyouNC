/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-18 下午09:47:30
 */
package nc.bs.pu.m21.query.pu;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.query.OrderQueryUtil;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为到货单提供查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-18 下午09:47:30
 */
public class OrderQueryFor23BP {
  /**
   * 方法功能描述：为到货单参照生成提供查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @param isLazy
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-14 下午04:14:32
   */
  public OrderVO[] query(String cond, UFBoolean isLazy) {
    // 根据ID查询订单VO
    OrderVO[] queryVos = OrderQueryUtil.queryFor45_23(cond, isLazy);

    AroundProcesser<OrderVO> processer =
        new AroundProcesser<OrderVO>(OrderPluginPoint.PULL23);
    this.addRule(processer);
    queryVos = processer.after(queryVos);

    return queryVos;
  }

  private void addRule(AroundProcesser<OrderVO> processer) {
    // 按在途状态过滤
    // 如果执行完拆行，此出两个规则都可以删除
    // processer.addAfterRule(new FilterByOnWayStatusRule());
    // 计算可到货数量
    // processer.addAfterRule(new CanArriveNumCalcRule());
    if (null == processer) {
      return;
    }
  }

}
