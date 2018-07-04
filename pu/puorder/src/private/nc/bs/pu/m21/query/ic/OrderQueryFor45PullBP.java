/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-14 上午11:18:15
 */
package nc.bs.pu.m21.query.ic;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.query.OrderQueryUtil;
import nc.bs.pu.m21.query.ic.rule.FilterByOnWayStatusRule;
import nc.bs.pu.m21.query.ic.rule.StorePrivilegeChkRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为采购入库单提供的拉单查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-14 上午11:18:15
 */
public class OrderQueryFor45PullBP {

  /**
   * 方法功能描述：为入库单参照生成提供查询。
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
        new AroundProcesser<OrderVO>(OrderPluginPoint.PULL45);
    this.addRule(processer);
    queryVos = processer.after(queryVos);
    return queryVos;
  }

  private void addRule(AroundProcesser<OrderVO> processer) {
    // 按在途状态过滤
    processer.addAfterRule(new FilterByOnWayStatusRule());
    // 计算可入库数量
    // processer.addAfterRule(new CanStockNumCalcRule());
    // 按库管员权限过滤
    processer.addAfterRule(new StorePrivilegeChkRule());
  }
}
