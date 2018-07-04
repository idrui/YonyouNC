package nc.vo.pu.m27.rule.it;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.merge.MatchMergeData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.rule.FeeMatchRule;
import nc.vo.pub.ValidationException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

public class FeeMatchRuleForIT extends FeeMatchRule {

  @Override
  public void check(MatchMergeData mmData, SettleEnvironment settleEnvironment)
      throws ValidationException {
    this.doCheck(mmData.getGoodsInvcVos(), mmData.getStockVos(),
        mmData.getFeeInvcVos(), mmData.getDiscountInvcVos(),
        mmData.getAdjustInvcVos());
  }

  private void chkDataEmpty(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] voaAdjust) {
    if (!ArrayUtils.isEmpty(voaInvoice)) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0157")/* @res "发票非应税劳务发票，或者没有进行成本要素定义 ！不能进行费用结算!" */;
      ExceptionUtils.wrappBusinessException(msg);
    }
    if (ArrayUtils.isEmpty(voaFee) && ArrayUtils.isEmpty(voaDiscount)
        && ArrayUtils.isEmpty(voaAdjust)) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0158")/* @res "请选择费用类发票或进口调整发票！" */;
      ExceptionUtils.wrappBusinessException(msg);
    }
    if (ArrayUtils.isEmpty(voaStock)) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0152")/* @res "请选择入库单！" */;
      ExceptionUtils.wrappBusinessException(msg);
    }
  }

  private void doCheck(InvoiceSettleVO[] voaInvoice, StockSettleVO[] voaStock,
      FeeDiscountSettleVO[] voaFee, FeeDiscountSettleVO[] voaDiscount,
      FeeDiscountSettleVO[] voaAdjust) {
    // 1、对所选择的入库单、发票进行数据检查
    this.chkDataEmpty(voaInvoice, voaStock, voaFee, voaDiscount, voaAdjust);

    this.checkStockAndFee(voaStock, voaFee);
  }
}
