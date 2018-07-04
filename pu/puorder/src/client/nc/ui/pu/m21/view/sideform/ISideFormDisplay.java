package nc.ui.pu.m21.view.sideform;

/**
 * <p>
 * <b>本接口主要完成以下功能：</b>
 * <ul>
 * <li>显示和重置form的内容
 * </ul>
 * <p>
 * 
 * @since 6.0
 * @version 2011-4-20 上午08:33:38
 * @author xihy1
 */

public interface ISideFormDisplay {
  /**
   * 显示form的内容
   * <p>
   * <b>参数说明</b>
   * 
   * @param mediator
   *          订单侧边栏辅助对象
   * @return
   *         <p>
   * @author xihy1
   * @time 2011-4-20 上午08:33:38
   */
  void display(OrderSideFormMediator mediator);

  /**
   * 重置form的内容
   * <p>
   * <b>参数说明</b>
   * 
   * @param mediator
   *          订单侧边栏辅助对象
   * @return
   *         <p>
   * @author xihy1
   * @time 2011-4-20 上午08:33:38
   */
  void reset(OrderSideFormMediator mediator);

}
