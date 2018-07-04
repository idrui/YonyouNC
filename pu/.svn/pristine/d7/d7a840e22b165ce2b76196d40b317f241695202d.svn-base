package nc.pubimpl.pu.it;

import java.util.List;
import java.util.Map.Entry;

import nc.bs.pu.it.WithoutInvoiceMatchForIT;
import nc.pubimpl.pu.it.bp.FeeSettleBPForIT;
import nc.pubimpl.pu.it.bp.SettleBillBPForIT;
import nc.pubimpl.pu.it.merge.FeeMatchMergeForIT;
import nc.pubimpl.pu.it.merge.MergeFactoryForIT;
import nc.pubitf.pu.it.ISettleMatchForIT;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.merge.MatchMerge;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.pub.SettleEnvironment.MatchExecType;
import nc.vo.pu.pub.util.ViewConcurrentUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.31
 * @version 2013-9-17 下午01:58:36
 * @author mengjian
 */
public class SettleMatchForITImpl implements ISettleMatchForIT {

  @Override
  public SettleBillVO[] autoMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) throws BusinessException {
    try {
      settleEnv.setSettleType(EnumSettleType.IT_UI_AUTO);
      return this.matchProcess(voaInvoice, voaStock, voaFee, voaDiscount,
          adjustInvcVos, settleEnv);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new SettleBillVO[0];
  }

  @Override
  public SettleBillVO[] differentMaterialMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) throws BusinessException {
    try {
      settleEnv.setSettleType(EnumSettleType.IT_DIFFERENT_MATERIAL);
      return this.matchProcess(voaInvoice, voaStock, voaFee, voaDiscount,
          adjustInvcVos, settleEnv);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new SettleBillVO[0];
  }

  @Override
  public SettleBillVO[] feeMatch(InvoiceSettleVO[] invoices,
      StockSettleVO[] stocks, FeeDiscountSettleVO[] fees,
      FeeDiscountSettleVO[] discounts, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment envi) throws BusinessException {
    try {
      // 1、并发控制
      this.concurrentControl(invoices, stocks, fees, discounts, adjustInvcVos);

      // 2、进行费用结算
      envi.setSettleType(EnumSettleType.IT_FEE);
      FeeMatchMergeForIT merge =
          new FeeMatchMergeForIT(stocks, fees, discounts, adjustInvcVos, envi);
      SettleBillVO[] bills = merge.merge();
      return new FeeSettleBPForIT(stocks, envi).doFeeSettle(bills);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new SettleBillVO[0];
  }

  @Override
  public SettleBillVO[] sameMaterialMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) throws BusinessException {
    try {
      settleEnv.setSettleType(EnumSettleType.IT_SAME_MATERIAL);
      return this.matchProcess(voaInvoice, voaStock, voaFee, voaDiscount,
          adjustInvcVos, settleEnv);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new SettleBillVO[0];
  }

  @Override
  public SettleBillVO[] withoutInvoiceMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) throws BusinessException {
    try {
      // 并发控制
      this.concurrentControl(voaInvoice, voaStock, voaFee, voaDiscount,
          adjustInvcVos);

      // 生成虚拟发票-虚拟发票自动审批-发票自动结算
      WithoutInvoiceMatchForIT bp = new WithoutInvoiceMatchForIT();
      return bp.matchProcess(voaStock);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return new SettleBillVO[0];
  }

  private void concurrentControl(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos) {

    ViewConcurrentUtil util = ViewConcurrentUtil.getInstance();
    if (!ArrayUtils.isEmpty(voaInvoice)) {
      util.concurrentCheck(voaInvoice);
    }
    if (!ArrayUtils.isEmpty(adjustInvcVos)) {
      util.concurrentCheck(adjustInvcVos);
    }
    if (!ArrayUtils.isEmpty(voaStock)) {
      MapList<String, StockSettleVO> btSsMap =
          new MapList<String, StockSettleVO>();
      for (StockSettleVO ssVo : voaStock) {
        btSsMap.put(ssVo.getCbilltypecode(), ssVo);
      }
      for (Entry<String, List<StockSettleVO>> entry : btSsMap.entrySet()) {
        List<StockSettleVO> voLst = entry.getValue();
        StockSettleVO[] vos = voLst.toArray(new StockSettleVO[voLst.size()]);
        util.concurrentCheck(vos);
      }
    }
    if (!ArrayUtils.isEmpty(voaFee)) {
      util.concurrentCheck(voaFee);
    }
    if (!ArrayUtils.isEmpty(voaDiscount)) {
      util.concurrentCheck(voaDiscount);
    }
  }

  /**
   * 各个结算均走此过程。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaInvoice
   * @param voaStock
   * @param voaFee
   * @param voaDiscount
   * @param settleEnv
   * @return
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-3-10 下午03:00:16
   */
  private SettleBillVO[] matchProcess(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) throws BusinessException {
    // 并发控制
    this.concurrentControl(voaInvoice, voaStock, voaFee, voaDiscount,
        adjustInvcVos);

    // 库存、发票VO合并
    MatchMerge merge =
        new MergeFactoryForIT().getMerge(voaInvoice, adjustInvcVos, voaStock,
            voaFee, voaDiscount, settleEnv);
    SettleBillVO[] voaBill = merge.merge();

    if (MatchExecType.mock.equals(settleEnv.getExecType())) {
      // 若为模拟结算,则不保存直接返回
      return voaBill;
    }
    // 保存结算单
    return new SettleBillBPForIT().saveSettleBills(voaBill, settleEnv);
  }

}
