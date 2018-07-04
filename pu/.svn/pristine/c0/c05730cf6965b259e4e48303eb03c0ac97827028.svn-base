/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-10 上午09:29:24
 */
package nc.itf.pu.reference.ic;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.reserve.SupplyBillOperator;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>预留为采购提供的服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-10 上午09:29:24
 */
public class ReserveServices {

  /**
   * 方法功能描述：审批
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 上午09:36:43
   */
  public static void auditSupply(OrderVO[] vos) {
    SupplyBillOperator service =
        NCLocator.getInstance().lookup(SupplyBillOperator.class);
    try {
      service.auditSupply(POBillType.Order.getCode(), vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：关闭
   * <p>
   * <b>参数说明</b>
   * 
   * @param billbids
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 上午09:45:17
   */
  public static void closeSupply(String[] billbids) {
    SupplyBillOperator service =
        NCLocator.getInstance().lookup(SupplyBillOperator.class);
    try {
      service.closeSupply(POBillType.Order.getCode(), billbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：修订
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 上午09:45:29
   */
  public static void modifySupply(OrderVO[] vos) {
    SupplyBillOperator service =
        NCLocator.getInstance().lookup(SupplyBillOperator.class);
    try {
      service.modifySupply(POBillType.Order.getCode(), vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：打开
   * <p>
   * <b>参数说明</b>
   * 
   * @param billbids
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 上午09:45:40
   */
  public static void openSupply(String[] billbids) {
    SupplyBillOperator service =
        NCLocator.getInstance().lookup(SupplyBillOperator.class);
    try {
      service.openSupply(POBillType.Order.getCode(), billbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：弃审
   * <p>
   * <b>参数说明</b>
   * 
   * @param billids
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 上午09:45:48
   */
  public static void unAuditSupply(String[] billids) {
    SupplyBillOperator service =
        NCLocator.getInstance().lookup(SupplyBillOperator.class);
    try {
      service.unAuditSupply(POBillType.Order.getCode(), billids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
