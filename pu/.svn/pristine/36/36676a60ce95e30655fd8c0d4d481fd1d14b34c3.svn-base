package nc.vo.pu.m27.rule;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m27.ISettleMatch;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.merge.MatchMergeData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>费用结算规则，本类主要完成以下功能：</b>
 * <ul>
 * <li>对应选择的入库单、发票进行过滤、并对于数据的合法性进行校验
 * <li>结算前检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wangyf
 * @time 2010-7-30 下午01:28:06
 */
public class FeeMatchRule extends MatchRule {

  @Override
  public void check(MatchMergeData mmData, SettleEnvironment settleEnvironment)
      throws ValidationException {
    // 执行检查
    this.doCheck(mmData.getGoodsInvcVos(), mmData.getStockVos(),
        mmData.getFeeInvcVos(), mmData.getDiscountInvcVos());
  }

  private void checkEstApFee(StockSettleVO[] ssVos, FeeDiscountSettleVO[] feeVos) {
    // 调用工具类检查,是否有费用暂估应付的入库单，且发票也已经传过应付
    new FeeEstApCheckRule(ssVos, feeVos).check();
  }

  private void chkDataEmpty(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount) {
    if (!ArrayUtils.isEmpty(voaInvoice)) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0157")/* @res "采购发票非应税劳务发票，或者没有进行成本要素定义 ！不能进行费用结算!" */;
      ExceptionUtils.wrappBusinessException(msg);
    }
    if (ArrayUtils.isEmpty(voaFee) && ArrayUtils.isEmpty(voaDiscount)) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0158")/* @res "请选择费用类发票！" */;
      ExceptionUtils.wrappBusinessException(msg);
    }
    if (ArrayUtils.isEmpty(voaStock)) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0152")/* @res "请选择入库单！" */;
      ExceptionUtils.wrappBusinessException(msg);
    }
  }

  private void chkFeeInvoiceCostFactor(FeeDiscountSettleVO[] voaFee) {
    if (ArrayUtils.isEmpty(voaFee)) {
      return;
    }
    for (FeeDiscountSettleVO fee : voaFee) {
      String billcode = fee.getVbillcode();
      if (StringUtils.isEmpty(fee.getPk_costfactor())) {
        // 要素主键
        String msg =
            billcode
                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4004060_0", "04004060-0159")/* @res "没有进行成本要素定义,不可以进行费用结算！" */;
        ExceptionUtils.wrappBusinessException(msg);
      }
      if (fee.getFapportionmode() == null) {
        // 分摊方式
        String msg =
            billcode
                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4004060_0", "04004060-0160")/*
                                                  * @res
                                                  * "在成本要素定义中没有定义分摊方式,不可以进行费用结算！"
                                                  */;
        ExceptionUtils.wrappBusinessException(msg);
      }
    }
  }

  private void chkSettleTOIA(StockSettleVO[] voaStock) {
    // 入库单参与结算后但未传存货核算的单据行也不可以参与费用结算。
    try {
      NCLocator.getInstance().lookup(ISettleMatch.class)
          .checkGoodsSettleTOIA(voaStock);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void chkStockBaffectcost(StockSettleVO[] voaStock) {
    // 检查所勾选的采购入库单交易类型属性“是否影响成本”
    // 如果存在该属性为否的采购入库单则不允许进行费用结算，提示“不影响成本的采购入库单不能进行费用结算！”
    StringBuilder error = new StringBuilder();
    for (StockSettleVO stock : voaStock) {
      UFBoolean baffectcost = stock.getBaffectcost();
      if (UFBoolean.TRUE.equals(baffectcost)) {
        continue;
      }
      error.append("[").append(stock.getVbillcode()).append("]");
    }
    if (error.length() == 0) {
      return;
    }
    String msg =
        error.toString()
            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                "4004060_0", "04004060-0161")/* @res "不影响成本的入库单不能进行费用结算！" */;
    ExceptionUtils.wrappBusinessException(msg);
  }

  private void chkStockBalreadyToIA(StockSettleVO[] voaStock) {
    // 所勾选的采购入库单必须为暂估数量不为０、或者已与发票结算并已传存货、或者已确认应付和成本，否则不可以参与费用结算。
    // 也就是说必须已有存货核算记录的入库单才可以进行费用结算，
    StringBuilder error = new StringBuilder();
    for (StockSettleVO stock : voaStock) {
      // 进行过货物暂估时，读取FTOIAFLAG标记进行判断是否传IA
      Integer ftoia = stock.getFdirtoiatype();
      if (EnumToIAFlag.EstimateToIA.value().equals(ftoia)) {
        continue;
      }
      if (EnumToIAFlag.ConfirmToIA.value().equals(ftoia)) {
        continue;
      }
      // 进行过货物结算时，判断是否影响成本来判断是否传IA(前面已经判断影响成本)
      if (!MathTool.isZero(stock.getNaccumsettlenum())) {
        continue;
      }
      error.append("[").append(stock.getVbillcode()).append("]");
    }
    if (error.length() == 0) {
      return;
    }
    String msg =
        error.toString()
            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                "4004060_0", "04004060-0162")/* @res "没有对应的存货核算记录,不可以进行费用结算！" */;
    ExceptionUtils.wrappBusinessException(msg);
  }

  private void doCheck(InvoiceSettleVO[] voaInvoice, StockSettleVO[] voaStock,
      FeeDiscountSettleVO[] voaFee, FeeDiscountSettleVO[] voaDiscount) {
    // 1、对所选择的入库单、发票进行数据检查
    this.chkDataEmpty(voaInvoice, voaStock, voaFee, voaDiscount);
    this.checkStockAndFee(voaStock, voaFee);
  }

  protected void checkStockAndFee(StockSettleVO[] voaStock,
      FeeDiscountSettleVO[] voaFee) {
    // 2、检查所勾选的采购入库单交易类型属性“是否影响成本”
    this.chkStockBaffectcost(voaStock);

    // 3、检查采购入库单是否已有存货核算记录
    this.chkStockBalreadyToIA(voaStock);

    // 4、检查费用类发票必须在成本要素中进行了相关分摊方式定义
    this.chkFeeInvoiceCostFactor(voaFee);

    // 5、入库单参与结算后但未传存货核算的单据行也不可以参与费用结算。
    this.chkSettleTOIA(voaStock);

    // 6、调用工具类检查,是否有费用暂估应付的入库单，且发票也已经传过应付
    this.checkEstApFee(voaStock, voaFee);
  }
}
