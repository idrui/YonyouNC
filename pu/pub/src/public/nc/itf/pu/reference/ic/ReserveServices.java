/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-10 ����09:29:24
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Ԥ��Ϊ�ɹ��ṩ�ķ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-10 ����09:29:24
 */
public class ReserveServices {

  /**
   * ������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 ����09:36:43
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
   * ���������������ر�
   * <p>
   * <b>����˵��</b>
   * 
   * @param billbids
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 ����09:45:17
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
   * ���������������޶�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 ����09:45:29
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
   * ����������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param billbids
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 ����09:45:40
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
   * ������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param billids
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 ����09:45:48
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
