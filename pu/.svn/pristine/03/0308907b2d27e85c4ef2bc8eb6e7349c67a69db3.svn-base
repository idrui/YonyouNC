/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-25 下午02:44:17
 */
package nc.itf.pu.reference.ia;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ia.mi2.po.IIAI2ForPOConfirm;
import nc.pubitf.ia.mi2.po.IIAI2ForPOEstimate;
import nc.pubitf.ia.mi2.po.IIAI2ForPOVMIEstimate;
import nc.pubitf.ia.mi9.po.IIAI9ForPOFeeEstimate;
import nc.pubitf.ia.mi9.po.IIAI9ForPOVMIFeeEstimate;
import nc.vo.ia.mi2.entity.I2BillVO;
import nc.vo.ia.mi9.entity.I9BillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>IA提供给采购确认和暂估的服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-25 下午02:44:17
 */
public class IAForEstConfirmServices {

  /**
   * 方法功能描述：采购入库单直接确认成本。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-9 上午11:17:55
   */
  public static I2BillVO[] confirm(I2BillVO[] bills) {
    IIAI2ForPOConfirm service =
        NCLocator.getInstance().lookup(IIAI2ForPOConfirm.class);
    try {
      return service.insertI2ForPOConfirm(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * 方法功能描述：生成存货核算的暂估单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-9 上午10:55:23
   */
  public static I2BillVO[] estimate(I2BillVO[] bills) {
    IIAI2ForPOEstimate service =
        NCLocator.getInstance().lookup(IIAI2ForPOEstimate.class);
    I2BillVO[] retbills = null;
    try {
      retbills = service.insertI2ForPOEstimate(bills);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return retbills;
  }

  /**
   * 方法功能描述：费用暂估生成I9(存货核算入库调整单)。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills I9
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-22 上午10:20:42
   */
  public static I9BillVO[] feeEstimate(I9BillVO[] bills) {
    IIAI9ForPOFeeEstimate service =
        NCLocator.getInstance().lookup(IIAI9ForPOFeeEstimate.class);
    try {
      return service.insertI9ForPOFeeEstimate(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * 方法功能描述：取消费用暂估I9单据。
   * <p>
   * <b>参数说明</b>
   * 
   * @param stockHIDs 入库头ID
   * @param stockBIDs 入库行ID
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-22 上午10:23:06
   */
  public static void feeUnEstimate(String[] stockHIDS, String[] stockBIDs) {
    IIAI9ForPOFeeEstimate service =
        NCLocator.getInstance().lookup(IIAI9ForPOFeeEstimate.class);
    try {
      service.deleteI9ForPOCancelFeeEstimate(stockHIDS, stockBIDs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：采购入库单取消直接确认成本。
   * <p>
   * <b>参数说明</b>
   * 
   * @param fiHIDs 采购入表头IDs
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-9 上午11:19:06
   */
  public static void unConfirm(String[] fiHIDs,String[] fiBIDs) {
    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }
    IIAI2ForPOConfirm service =
        NCLocator.getInstance().lookup(IIAI2ForPOConfirm.class);
    try {
      service.deleteI2ForPOUnconfirm(fiHIDs,fiBIDs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：取消存货核算的暂估单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param fiHIDs 入库头ID
   * @param fiBIDs 　入库体ID
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-9 上午10:54:52
   */
  public static void unEstimate(String[] fiHIDs, String[] fiBIDs) {
    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }
    IIAI2ForPOEstimate service =
        NCLocator.getInstance().lookup(IIAI2ForPOEstimate.class);
    try {
      service.deleteI2ForPOUnestimate(fiHIDs, fiBIDs);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：取消消耗汇总存货核算的暂估单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param fiHIDs VMI头ID
   * @param fiBIDs 　VMI体ID
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-9 上午10:54:52
   */
  public static void unVMIEstimate(String[] fiHIDs, String[] fiBIDs) {
    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }
    IIAI2ForPOVMIEstimate service =
        NCLocator.getInstance().lookup(IIAI2ForPOVMIEstimate.class);
    try {
      service.deleteI2ForPOVMIUnestimate(fiHIDs);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：取消消耗汇总费用暂估I9单据。
   * <p>
   * <b>参数说明</b>
   * 
   * @param stockHIDs VMI头ID
   * @param stockBIDs VMI行ID
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-22 上午10:23:06
   */
  public static void unVMIFeeEstimate(String[] stockHIDS, String[] stockBIDs) {
    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }
    IIAI9ForPOVMIFeeEstimate service =
        NCLocator.getInstance().lookup(IIAI9ForPOVMIFeeEstimate.class);
    try {
      service.deleteI9ForPOVMIFeeUnestimate(stockHIDS);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：消耗汇总暂估生成存货核算的暂估单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills I2单据数组
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-19 上午10:55:23
   */
  public static I2BillVO[] VMIEstimate(I2BillVO[] bills) {
    IIAI2ForPOVMIEstimate service =
        NCLocator.getInstance().lookup(IIAI2ForPOVMIEstimate.class);
    I2BillVO[] retbills = null;
    try {
      retbills = service.insertI2ForPOVMIEstimate(bills);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return retbills;
  }

  /**
   * 方法功能描述：消耗汇总费用暂估生成I9(存货核算入库调整单)。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills I9
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-6-22 上午10:20:42
   */
  public static I9BillVO[] VMIFeeEstimate(I9BillVO[] bills) {
    IIAI9ForPOVMIFeeEstimate service =
        NCLocator.getInstance().lookup(IIAI9ForPOVMIFeeEstimate.class);
    try {
      return service.insertI9ForPOVMIFeeEstimate(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

}
