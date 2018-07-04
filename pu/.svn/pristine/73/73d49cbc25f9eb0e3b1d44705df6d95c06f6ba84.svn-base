/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-13 下午04:58:17
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
 * <li>为期初暂估单提供的查询服务BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-13 下午04:58:17
 */
public class OrderQueryFor4TBP {
  public OrderVO[] queryFor4t(String condition, UFBoolean isLazy) {
    OrderVO[] queryVos = OrderQueryUtil.queryFor45_23(condition, isLazy);

    AroundProcesser<OrderVO> processer =
        new AroundProcesser<OrderVO>(OrderPluginPoint.PULL4T);
    this.addRule(processer);
    queryVos = processer.after(queryVos);

    return queryVos;
  }

  private void addRule(AroundProcesser<OrderVO> processer) {
    // processer.addAfterRule(new InitialEstNumCalcRule());
    if (null == processer) {
      return;
    }
  }
}
