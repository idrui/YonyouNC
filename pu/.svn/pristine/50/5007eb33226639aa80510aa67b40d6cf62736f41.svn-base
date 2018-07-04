package nc.itf.pu.reference.pcia;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pcia.m4632.po.I2AdjustBackData;
import nc.pubitf.pcia.m4632.po.IPCIA4632ForPOSettle;
import nc.pubitf.pcia.m4639.po.IPCIA4639ForPOFeeSettle;
import nc.pubitf.pcia.m4639.po.IPCIA4639ForPOSettle;
import nc.pubitf.pcia.m4639.po.P4639AdjustBackData;
import nc.vo.pcia.m4639.entity.P4639BillVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 结算传利润中心存货核算入库单、调整单
 * 货物、费用
 * 
 * @since 6.36
 * @version 2014-10-18 下午12:16:55
 * @author mengjian
 */
public class PCIAServices {

  /**
   * 采购取消结算删除利润中心存货核算的入库调整单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param csrcids 来源单据ID(采购结算单ID)
   * @param csrcbids 来源单据行ID(采购结算单的行ID)
   * @throws BusinessException <p>
   * @author mengjian
   * @time 2014-10-21 上午11:33:05
   */
  public static void cancelSettleI9(String[] csrcids, String[] csrcbids)
      throws BusinessException {
    IPCIA4639ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IPCIA4639ForPOSettle.class);
    iaSettle.deleteP4639ForPOUnsettle(csrcids);
  }

  /**
   * 方法功能描述：采购费用结算后又取消结算时删除利润中心存货核算的入库调整单
   * <p>
   * <b>参数说明</b>
   * 
   * @param csrcids 来源单据ID(采购结算单ID)
   * @param csrcbids 来源单据行ID(采购结算单的行ID)
   * @throws BusinessException <p>
   * @since 6.36
   * @author mengjian
   * @time 2014-10-21 上午11:33:05
   */
  public static void deleteI9ForPOCancelFeeSettle(String[] csrcids,
      String[] csrcbids) {
    if (csrcids == null || csrcbids == null) {
      return;
    }
    IPCIA4639ForPOFeeSettle iaSettle =
        NCLocator.getInstance().lookup(IPCIA4639ForPOFeeSettle.class);
    try {
      iaSettle.deleteP4639ForPOCancelFeeSettle(csrcids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 采购普通货物结算回冲期初暂估形成的利润中心存货核算红字采购入库单
   * 
   * @param bills 利润中心存货核算采购入库单单据VO
   * @return 保存后的利润中心存货核算采购入库单单据VO
   * @throws BusinessException
   */
  public static void insertI2ForPOQCSettleAdjustBack(SettleBillVO[] voaIA)
      throws BusinessException {
    IPCIA4632ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IPCIA4632ForPOSettle.class);
    iaSettle.insertP4632ForPOQCSettleAdjustBack(voaIA);
  }

  /**
   * 方法功能描述：采购费用结算时传利润中心存货核算的入库调整单（I9）
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills(P4639BillVO[])
   * @throws BusinessException <p>
   * @since 6.36
   * @author mengjian
   * @time 2014-10-21 上午11:33:05
   */
  public static void insertI9ForPOFeeSettle(P4639BillVO[] bills) {
    if (bills == null || bills.length == 0) {
      return;
    }
    IPCIA4639ForPOFeeSettle iaSettle =
        NCLocator.getInstance().lookup(IPCIA4639ForPOFeeSettle.class);
    try {
      iaSettle.insertP4639ForPOFeeSettle(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：采购费用结算回冲暂估形成的利润中心存货核算入库调整单(用于费用结算时回冲费用暂估所生产的I9)
   * <p>
   * <b>参数说明</b>
   * 
   * @param datas
   * @throws BusinessException <p>
   * @since 6.36
   * @author mengjian
   * @time 2014-10-21 上午11:33:05
   */
  public static void insertI9ForPOFeeSettleAdjustBack(
      P4639AdjustBackData[] datas) {
    if (datas == null || datas.length == 0) {
      return;
    }
    IPCIA4639ForPOFeeSettle iaSettle =
        NCLocator.getInstance().lookup(IPCIA4639ForPOFeeSettle.class);
    try {
      iaSettle.insertP4639ForPOFeeSettleAdjustBack(datas);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 采购普通货物结算生成利润中心存货核算采购入库单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaIA
   * @throws BusinessException <p>
   * @author mengjian
   * @time 2014-10-21 上午11:33:05
   */
  public static void settleToI2(SettleBillVO[] voaIA) throws BusinessException {
    IPCIA4632ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IPCIA4632ForPOSettle.class);
    iaSettle.insertP4632ForPOSettle(voaIA);
  }

  /**
   * 采购结算回冲暂估形成的利润中心存货核算采购入库单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaIA
   * @throws BusinessException <p>
   * @author mengjian
   * @time 2014-10-21 上午11:33:05
   */
  public static void settleToI2ForAdjustBack(I2AdjustBackData[] voaBack)
      throws BusinessException {
    IPCIA4632ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IPCIA4632ForPOSettle.class);
    iaSettle.insertP4632ForPOSettleAdjustBack(voaBack);
  }

  /**
   * 采购结算生成利润中心存货核算入库调整单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaIA
   * @throws BusinessException <p>
   * @author mengjian
   * @time 2014-10-21 上午11:33:05
   */
  public static void settleToI9(SettleBillVO[] bills) throws BusinessException {
    IPCIA4639ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IPCIA4639ForPOSettle.class);
    iaSettle.insertP4639ForPOSettle(bills);
  }

  /**
   * 采购取消普通货物结算删除利润中心存货核算的采购入库单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param csrcids 来源单据ID(采购结算单ID)
   * @param csrcbids 来源单据行ID(采购结算单行ID)
   * @throws BusinessException <p>
   * @author mengjian
   * @time 2014-10-21 上午11:33:05
   */
  public static void unsettleI2(String[] csrcids, String[] csrcbids)
      throws BusinessException {
    IPCIA4632ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IPCIA4632ForPOSettle.class);
    iaSettle.deleteP4632ForPOUnsettle(csrcids);
  }
}
