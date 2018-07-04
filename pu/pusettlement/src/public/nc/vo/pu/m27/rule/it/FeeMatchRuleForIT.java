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
              "04004060-0157")/* @res "��Ʊ��Ӧ˰����Ʊ������û�н��гɱ�Ҫ�ض��� �����ܽ��з��ý���!" */;
      ExceptionUtils.wrappBusinessException(msg);
    }
    if (ArrayUtils.isEmpty(voaFee) && ArrayUtils.isEmpty(voaDiscount)
        && ArrayUtils.isEmpty(voaAdjust)) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0158")/* @res "��ѡ������෢Ʊ����ڵ�����Ʊ��" */;
      ExceptionUtils.wrappBusinessException(msg);
    }
    if (ArrayUtils.isEmpty(voaStock)) {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0152")/* @res "��ѡ����ⵥ��" */;
      ExceptionUtils.wrappBusinessException(msg);
    }
  }

  private void doCheck(InvoiceSettleVO[] voaInvoice, StockSettleVO[] voaStock,
      FeeDiscountSettleVO[] voaFee, FeeDiscountSettleVO[] voaDiscount,
      FeeDiscountSettleVO[] voaAdjust) {
    // 1������ѡ�����ⵥ����Ʊ�������ݼ��
    this.chkDataEmpty(voaInvoice, voaStock, voaFee, voaDiscount, voaAdjust);

    this.checkStockAndFee(voaStock, voaFee);
  }
}
