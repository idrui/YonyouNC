/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-13 下午03:40:25
 */
package nc.impl.pu.m21;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.pu.m21.action.OrderApproveAction;
import nc.impl.pu.m21.action.OrderSendApproveAction;
import nc.impl.pu.m21.action.OrderUnArppoveAction;
import nc.impl.pu.m21.action.OrderUnSendApproveAction;
import nc.itf.pu.m21.IOrderApprove;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单的审批组件实现
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-13 下午03:40:25
 */
public class OrderAppoveImpl implements IOrderApprove {

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IOrderApprove#approve(nc.vo.pu.m21.entity.OrderVO[])
   */
  @Override
  public OrderVO[] approve(OrderVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new OrderApproveAction().approve(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IOrderApprove#sendArppove(nc.vo.pu.m21.entity.OrderVO[])
   */
  @Override
  public OrderVO[] sendapprove(OrderVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new OrderSendApproveAction().sendApprove(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IOrderApprove#unapprove(nc.vo.pu.m21.entity.OrderVO[])
   */
  @Override
  public OrderVO[] unapprove(OrderVO[] vos, AbstractCompiler2 script)
      throws BusinessException {

    try {
      return new OrderUnArppoveAction().unapprove(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;

  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IOrderApprove#unSendapprove(nc.vo.pu.m21.entity.OrderVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public OrderVO[] unSendapprove(OrderVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new OrderUnSendApproveAction().unSendApprove(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
