package nc.pubimpl.pu.it.merge;

import nc.impl.pu.m27.merge.SameMaterialMerge;
import nc.pubimpl.pu.it.rule.MatchMergeFeeDiscountAdjustRule;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumSettleOrderType;
import nc.vo.pu.m27.pub.SettleEnvironment;

/**
 * 进口同物料结算匹配规则
 * 
 * @since 6.31
 * @version 2013-10-30 上午09:08:10
 * @author mengjian
 */
public class SameMaterialMergeForIT extends SameMaterialMerge {

  public SameMaterialMergeForIT(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, adjustInvcVos, settleEnv);
  }

  @Override
  protected void recordFeeDiscountInfo(SettleBillItemVO[] voaItem,
      StockSettleVO[] m_voaStock) {
    new MatchMergeFeeDiscountAdjustRule(this.getSettleEnv(), m_voaStock)
        .process(voaItem);
  }

  /**
   * //设置结算类型 0：采购 ；1：进口
   * 
   * @param splitVos
   */
  @Override
  protected void setFsettletypes(SettleBillVO[] splitVos) {
    for (SettleBillVO bill : splitVos) {
      // 设置结算类型 0：采购 ；1：进口
      bill.getParentVO().setFsettletype(EnumSettleOrderType.IT.toInteger());
    }
  }
}
