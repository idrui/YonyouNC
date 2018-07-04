/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-3 上午08:44:05
 */
package nc.itf.pu.reference.ia;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ia.mi2.po.I2AdjustBackData;
import nc.pubitf.ia.mi2.po.IIAI2ForPOSettle;
import nc.pubitf.ia.mi9.po.I9AdjustBackData;
import nc.pubitf.ia.mi9.po.IIAI9ForPOFeeSettle;
import nc.pubitf.ia.mi9.po.IIAI9ForPOSettle;
import nc.pubitf.ia.mig.po.IIAIGForPOFeeSettle;
import nc.pubitf.ia.mig.po.IIAIGForPOSettle;
import nc.vo.ia.mi2.entity.I2BillVO;
import nc.vo.ia.mi9.entity.I9BillVO;
import nc.vo.ia.mig.entity.IGBillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>IA提供给采购结算27的服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-3 上午08:44:05
 */
public class M27IAServices {

  /**
   * 采购取消结算删除存货核算的入库调整单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param csrcids 来源单据ID(采购结算单ID)
   * @param csrcbids 来源单据行ID(采购结算单的行ID)
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-30 上午11:33:05
   */
  public static void cancelSettleI9(String[] csrcids, String[] csrcbids)
      throws BusinessException {
    IIAI9ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI9ForPOSettle.class);
    iaSettle.deleteI9ForPOUnsettle(csrcids);
  }

  /**
   * 采购取消结算删除存货核算的入库调整单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param csrcids 来源单据ID(采购结算单ID)
   * @param csrcbids 来源单据行ID(采购结算单的行ID)
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-30 上午11:33:05
   */
  public static void cancelSettleIG(String[] csrcids, String[] csrcbids)
      throws BusinessException {
    IIAIGForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IIAIGForPOSettle.class);
    iaSettle.deleteIGForPOUnsettle(csrcids);
  }

  /**
   * 方法功能描述：采购费用结算后又取消结算时删除存货核算的入库调整单
   * <p>
   * <b>参数说明</b>
   * 
   * @param csrcids 来源单据ID(采购结算单ID)
   * @param csrcbids 来源单据行ID(采购结算单的行ID)
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-8-19 下午01:37:32
   */
  public static void deleteI9ForPOCancelFeeSettle(String[] csrcids,
      String[] csrcbids) {
    if (csrcids == null || csrcbids == null) {
      return;
    }
    IIAI9ForPOFeeSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI9ForPOFeeSettle.class);
    try {
      iaSettle.deleteI9ForPOCancelFeeSettle(csrcids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：采购取消费用结算删除存货核算的损益调整单
   * <p>
   * <b>参数说明</b>
   * 
   * @param csrcids
   * @param csrcbids
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-2 下午03:14:12
   */
  public static void deleteIGForPOUnfeeSettle(String[] csrcids) {
    if (csrcids == null) {
      return;
    }
    IIAIGForPOFeeSettle iaSettle =
        NCLocator.getInstance().lookup(IIAIGForPOFeeSettle.class);
    try {
      iaSettle.deleteIGForPOUnfeeSettle(csrcids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 采购普通货物结算回冲期初暂估形成的存货核算红字采购入库单
   * 
   * @param bills 存货核算采购入库单单据VO
   * @return 保存后的存货核算采购入库单单据VO
   * @throws BusinessException
   */
  public static void insertI2ForPOQCSettleAdjustBack(I2BillVO[] voaIA)
      throws BusinessException {
    IIAI2ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI2ForPOSettle.class);
    iaSettle.insertI2ForPOQCSettleAdjustBack(voaIA);
  }

  /**
   * 方法功能描述：采购费用结算时传存货核算的入库调整单（I9）
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-8-19 下午01:34:12
   */
  public static void insertI9ForPOFeeSettle(I9BillVO[] bills) {
    if (bills == null || bills.length == 0) {
      return;
    }
    IIAI9ForPOFeeSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI9ForPOFeeSettle.class);
    try {
      iaSettle.insertI9ForPOFeeSettle(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：采购费用结算回冲暂估形成的存货核算入库调整单(用于费用结算时回冲费用暂估所生产的I9)
   * <p>
   * <b>参数说明</b>
   * 
   * @param datas
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-2 下午03:16:52
   */
  public static void insertI9ForPOFeeSettleAdjustBack(I9AdjustBackData[] datas) {
    if (datas == null || datas.length == 0) {
      return;
    }
    IIAI9ForPOFeeSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI9ForPOFeeSettle.class);
    try {
      iaSettle.insertI9ForPOFeeSettleAdjustBack(datas);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：采购费用结算生成存货核算损益调整单(费用结算生成IG)
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   * @throws BusinessException <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-2 下午03:13:22
   */
  public static void insertIGForPOFeeSettle(IGBillVO[] bills) {
    if (bills == null || bills.length == 0) {
      return;
    }
    IIAIGForPOFeeSettle iaSettle =
        NCLocator.getInstance().lookup(IIAIGForPOFeeSettle.class);
    try {
      iaSettle.insertIGForPOFeeSettle(bills);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 采购普通货物结算生成存货核算采购入库单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaIA
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-30 上午11:32:05
   */
  public static void settleToI2(I2BillVO[] voaIA) throws BusinessException {
    IIAI2ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI2ForPOSettle.class);
    iaSettle.insertI2ForPOSettle(voaIA);
  }

  /**
   * 采购结算回冲暂估形成的存货核算采购入库单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaIA
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-30 上午11:32:38
   */
  public static void settleToI2ForAdjustBack(I2AdjustBackData[] voaBack)
      throws BusinessException {
    IIAI2ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI2ForPOSettle.class);
    // insertI2ForPOAdjustBack
    iaSettle.insertI2ForPOSettleAdjustBack(voaBack);
  }

  /**
   * 采购结算生成存货核算入库调整单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaIA
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-30 上午11:33:05
   */
  public static void settleToI9(I9BillVO[] bills) throws BusinessException {
    IIAI9ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI9ForPOSettle.class);
    iaSettle.insertI9ForPOSettle(bills);
  }

  /**
   * 采购结算生成存货核算入库调整单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaIA
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-30 上午11:33:05
   */
  public static void settleToIG(IGBillVO[] bills) throws BusinessException {
    IIAIGForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IIAIGForPOSettle.class);
    iaSettle.insertIGForPOSettle(bills);
  }

  /**
   * 采购取消普通货物结算删除存货核算的采购入库单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param csrcids 来源单据ID(采购结算单ID)
   * @param csrcbids 来源单据行ID(采购结算单行ID)
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-30 上午11:33:05
   */
  public static void unsettleI2(String[] csrcids, String[] csrcbids)
      throws BusinessException {
    IIAI2ForPOSettle iaSettle =
        NCLocator.getInstance().lookup(IIAI2ForPOSettle.class);
    iaSettle.deleteI2ForPOUnsettle(csrcids);
  }
}
