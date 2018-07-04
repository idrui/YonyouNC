package nc.bs.pu.m27.settlebill.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.m27.ISettleMatch;
import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleQuery;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 取消结算（删除结算单）前，需要判断发票是否已经传应付的检查：<br>
 * 如果发票已经传应付，并且入库单也已经传过应付，则不允许取消结算<br>
 * 即采购发票结算后传应付时回冲过的暂估应付，则该发票对应的结算单不可以删除<br>
 * 入库单已经进行过费用暂估应付，费用发票已经传应付，也不允许取消结算<br>
 * @scene
 * 删除结算单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午4:10:17
 * @author zhangshqb
 */
public class InvoiceToAPCheckRule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    // 检查货物结算
    this.checkGoodSettle(vos);
    // 检查费用结算
    this.checkFeeSettle(vos);
  }

  private void checkFee(FeeDiscountSettleVO[] feeVos,
      StockSettleVO[] stockSettleVOs) {

    if (ArrayUtils.isEmpty(feeVos)) {
      return;
    }
    MapList<String, String> stockFeeEstApMap = null;
    try {
      stockFeeEstApMap =
          NCLocator.getInstance().lookup(ISettleMatch.class)
              .getEstAPFeeMaterial(stockSettleVOs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (null == stockFeeEstApMap || stockFeeEstApMap.size() == 0) {
      return;
    }
    List<String> estStockLst = new ArrayList<String>();
    for (Entry<String, List<String>> entry : stockFeeEstApMap.entrySet()) {
      Set<String> feeMarSet = new HashSet<String>(entry.getValue());
      for (FeeDiscountSettleVO fee : feeVos) {
        if (UFBoolean.TRUE.equals(fee.getBapflag())
            && feeMarSet.contains(fee.getPk_material())) {
          estStockLst.add(entry.getKey());
          break;
        }
      }
    }
    if (estStockLst.size() > 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0075")/*@res "入库单已经进行过费用暂估应付，费用发票已经传应付,不能取消结算！"*/);
    }

  }

  private void checkFeeSettle(SettleBillVO[] vos) {
    // 提取入库单的bID和发票的bID
    List<String> invoicebids = new ArrayList<String>();
    List<String> stockBids = new ArrayList<String>();
    for (SettleBillVO vo : vos) {
      SettleBillItemVO[] items = vo.getChildrenVO();
      for (SettleBillItemVO item : items) {
        if (EnumMatchRowType.Fee.toInteger().equals(item.getFrowtype())
            || EnumMatchRowType.Discount.toInteger().equals(item.getFrowtype())) {
          if (item.getPk_invoice_b() != null) {
            invoicebids.add(item.getPk_invoice_b());
          }
        }
        // 入库行bid
        if (item.getPk_stock_b() != null) {
          stockBids.add(item.getPk_stock_b());
        }
      }
    }
    if (stockBids.size() == 0 || invoicebids.size() == 0) {
      return;
    }
    StockInfoUtil stockUtil = new StockInfoUtil(vos);
    List<StockSettleVO> stockSettleVOList = new ArrayList<StockSettleVO>();
    for (String bid : stockBids) {
      StockSettleVO vo = stockUtil.getStockSettleVO(bid);
      stockSettleVOList.add(vo);
    }
    if (stockSettleVOList.size() == 0) {
      return;
    }
    // 查询费用发票结算vo
    ViewQuery<FeeDiscountSettleVO> query =
        new ViewQuery<FeeDiscountSettleVO>(FeeDiscountSettleVO.class);
    FeeDiscountSettleVO[] fees =
        query.query(invoicebids.toArray(new String[invoicebids.size()]));
    this.checkFee(fees,
        stockSettleVOList.toArray(new StockSettleVO[stockSettleVOList.size()]));
  }

  /**
   * @param vos
   */
  private void checkGoodSettle(SettleBillVO[] vos) {
    // 提取入库单的ID和发票的ID
    List<String> invoiceHids = new ArrayList<String>();
    List<String> stockBids = new ArrayList<String>();
    for (SettleBillVO vo : vos) {
      SettleBillItemVO[] items = vo.getChildrenVO();
      for (SettleBillItemVO item : items) {
        if (item.getPk_invoice() != null) {
          invoiceHids.add(item.getPk_invoice());
        }
        // 入库行id
        if (item.getPk_stock_b() != null) {
          stockBids.add(item.getPk_stock_b());
        }
      }
    }

    boolean isInvoiceToAP = false;
    // 如果有入库单行，则需要判断发票是否已经传应付
    if (stockBids.size() > 0) {
      isInvoiceToAP = this.isInvoiceToAP(invoiceHids);
    }
    // 如果没有入库单行，则直接返回
    else {
      return;
    }

    // 如果发票没有传应付（或者没有发票行），直接返回
    if (!isInvoiceToAP) {
      return;
    }
    // 入库单是否暂估应付
    boolean isStockToAP = this.isStockToAP(vos, stockBids);
    if (isStockToAP) {
      String errMessage = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0076")/*@res "入库单和发票都已经传过应付，不能取消结算！"*/;
      ExceptionUtils.wrappBusinessException(errMessage);
    }
  }

  private boolean isInvoiceToAP(List<String> invoiceHids) {
    boolean flag = false;
    int hidLength = invoiceHids.size();
    if (hidLength == 0) {
      return flag;
    }

    IInvoiceSettleQuery query =
        NCLocator.getInstance().lookup(IInvoiceSettleQuery.class);
    try {
      flag = query.isExistsSentAP(invoiceHids.toArray(new String[hidLength]));
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return flag;
  }

  private boolean isStockToAP(SettleBillVO[] vos, List<String> stockBids) {
    boolean flag = false;
    int hidLength = stockBids.size();
    if (hidLength == 0) {
      return flag;
    }
    StockInfoUtil stockUtil = new StockInfoUtil(vos);
    for (String bid : stockBids) {
      StockSettleVO vo = stockUtil.getStockSettleVO(bid);
      if (EnumToAPFlag.EstimateToAP.value().equals(vo.getFdirtoaptype())
          || EnumToAPFlag.ConfirmToAP.value().equals(vo.getFdirtoaptype())) {
        flag = true;
        break;
      }
    }

    return flag;
  }
}