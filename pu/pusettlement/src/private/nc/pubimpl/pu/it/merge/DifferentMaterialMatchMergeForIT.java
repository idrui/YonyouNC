package nc.pubimpl.pu.it.merge;

import nc.impl.pu.m27.merge.DifferentMaterialMatchMerge;
import nc.pubimpl.pu.it.rule.MatchMergeFeeDiscountAdjustRule;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumSettleOrderType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.util.SettlePublicUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 进口异物料结算匹配规则
 * 
 * @since 6.31
 * @version 2013-10-30 上午09:07:51
 * @author mengjian
 */
public class DifferentMaterialMatchMergeForIT extends
    DifferentMaterialMatchMerge {

  public DifferentMaterialMatchMergeForIT(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, adjustInvcVos, settleEnv);
  }

  /**
   * 源于进口订单的发票，必须和同源进口明细单的进口入库单结算。
   * 
   * @param voaInvoice
   * @param voaStock
   */
  @Override
  protected void checkInvoiceByOrder(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock) {
    if (SettlePublicUtil.fromSameOrder(voaInvoice, voaStock)) {
      return;
    }
    ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004060_0", "04004060-0251")/*
                                                                 * 源于进口订单的发票，
                                                                 * 必须和同源进口明细单的进口入库单结算
                                                                 * !
                                                                 */);
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
