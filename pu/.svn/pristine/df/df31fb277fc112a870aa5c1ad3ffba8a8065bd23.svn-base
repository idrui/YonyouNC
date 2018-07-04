package nc.itf.pu.reference.pcia;

import nc.vo.pcia.m4632.entity.P4632BillVO;
import nc.vo.pcia.m4639.entity.P4639BillVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.pubitf.pcia.m4632.po.IPCIA4632ForPOEstimate;
import nc.pubitf.pcia.m4639.po.IPCIA4639ForPOFeeEstimate;

import nc.bs.framework.common.NCLocator;

/**
 * 货物/费用暂估（货物/费用取消暂估）传利润中心调利润中心存货接口
 * 
 * @since 6.36
 * @version 2014-10-20 上午10:23:33
 * @author mengjian
 */
public class PCIAForEstConfirmServices {

  /**
   * 方法功能描述：采购入库单直接确认利润中心成本。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   * @return <p>
   * @since 6.36
   * @author mengjian
   * @time 2014-10-21 上午11:17:55
   */
  /*  public static P4632BillVO[] confirm(PurchaseinFIVO[] bills) {
      IPCIA4632ForPOConfirm service =
          NCLocator.getInstance().lookup(IPCIA4632ForPOConfirm.class);
      try {
        return service.insertP4632ForPOConfirm(bills);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      return null;
    }*/

  /**
   * 方法功能描述：生成利润中心存货核算的暂估单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   * @return <p>
   * @since 6.36
   * @author mengjian
   * @time 2014-10-21 上午10:55:23
   */
  public static P4632BillVO[] estimate(PurchaseinFIVO[] bills) {
    IPCIA4632ForPOEstimate service =
        NCLocator.getInstance().lookup(IPCIA4632ForPOEstimate.class);
    P4632BillVO[] retbills = null;
    try {
      retbills = service.insertP4632ForPOEstimate(bills);
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
   * @param bills 4639(利润中心存货核算-入库调整单)
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @return P4639BillVO[]
   * @time 2010-6-22 上午10:20:42
   */
  public static P4639BillVO[] feeEstimate(P4639BillVO[] bills) {
    IPCIA4639ForPOFeeEstimate service =
        NCLocator.getInstance().lookup(IPCIA4639ForPOFeeEstimate.class);
    try {
      return service.insertP4639ForPOFeeEstimate(bills);
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
    IPCIA4639ForPOFeeEstimate service =
        NCLocator.getInstance().lookup(IPCIA4639ForPOFeeEstimate.class);
    try {
      service.deleteP4639ForPOCancelFeeEstimate(stockHIDS, stockBIDs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：采购入库单取消直接确认利润中心成本。
   * <p>
   * <b>参数说明</b>
   * 
   * @param fiHIDs 采购入表头IDs
   *          <p>
   * @since 6.36
   * @author mengjian
   * @time 2014-10-21 上午11:19:06
   */
  /*public static void unConfirm(String[] fiHIDs, String[] fiBIDs) {
    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }
    IPCIA4632ForPOConfirm service =
        NCLocator.getInstance().lookup(IPCIA4632ForPOConfirm.class);
    try {
      service.deleteP4632ForPOUnconfirm(fiHIDs, fiBIDs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }*/

  /**
   * 方法功能描述：取消利润中心存货核算的暂估单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param fiHIDs 入库头ID
   * @param fiBIDs 　入库体ID
   *          <p>
   * @since 6.36
   * @author mengjian
   * @time 2014-10-21 上午10:54:52
   */
  public static void unEstimate(String[] fiHIDs, String[] fiBIDs) {
    if (!SysInitGroupQuery.isIAEnabled()) {
      return;
    }
    IPCIA4632ForPOEstimate service =
        NCLocator.getInstance().lookup(IPCIA4632ForPOEstimate.class);
    try {
      service.deleteP4632ForPOUnestimate(fiHIDs, fiBIDs);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
