package nc.vo.pu.m27.rule;

import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.pu.m27.rule.it.FeeMatchRuleForIT;

public class MatchRuleFactory {

  private MatchRule checker = null;

  /**
   * ƥ������ȷ��ʱ�Ĺ�����
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param iType
   * @return <p>
   * @author wangyf
   * @time 2010-1-19 ����02:47:01
   */
  public MatchRule getRule(EnumSettleType settleType) {
    if (settleType == EnumSettleType.SAME_MATERIAL) {
      return this.checker = new SameMaterialMatchRule();
    }
    else if (settleType == EnumSettleType.DIFFERENT_MATERIAL) {
      return this.checker = new DifferentMaterialMatchRule();
    }
    else if (settleType == EnumSettleType.FEE) {
      return this.checker = new FeeMatchRule();
    }
    else if (settleType == EnumSettleType.WITHOUT_INVOICE) {
      return this.checker = new WithoutInvoiceMatchRule();
    }
    else if (settleType == EnumSettleType.UI_AUTO
        || EnumSettleType.INVOICE_AUTO == settleType) {
      return this.checker = new AutoMatchRule();
    }
    else if (settleType == EnumSettleType.VOI_CONSUME) {
      return this.checker = new VoiConsumeMatchRule();
    }
    else if (settleType == EnumSettleType.VOI_CONSUME_FEE) {
      return this.checker = new VoiConsumeFeeMatchRule();
    }
    else if (settleType == EnumSettleType.IT_SAME_MATERIAL) {
      return this.checker = new SameMaterialMatchRule();
    }
    else if (settleType == EnumSettleType.IT_DIFFERENT_MATERIAL) {
      return this.checker = new DifferentMaterialMatchRule();
    }
    else if (settleType == EnumSettleType.IT_FEE) {
      return this.checker = new FeeMatchRuleForIT();
    }
    else if (settleType == EnumSettleType.IT_WITHOUT_INVOICE) {
      return this.checker = new WithoutInvoiceMatchRule();
    }
    else if (settleType == EnumSettleType.IT_UI_AUTO
        || EnumSettleType.IT_INVOICE_AUTO == settleType) {
      return this.checker = new AutoMatchRule();
    }
    return this.checker;
  }

}
