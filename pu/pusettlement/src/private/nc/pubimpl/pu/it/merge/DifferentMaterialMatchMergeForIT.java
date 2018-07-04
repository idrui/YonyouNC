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
 * ���������Ͻ���ƥ�����
 * 
 * @since 6.31
 * @version 2013-10-30 ����09:07:51
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
   * Դ�ڽ��ڶ����ķ�Ʊ�������ͬԴ������ϸ���Ľ�����ⵥ���㡣
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
                                                                 * Դ�ڽ��ڶ����ķ�Ʊ��
                                                                 * �����ͬԴ������ϸ���Ľ�����ⵥ����
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
   * //���ý������� 0���ɹ� ��1������
   * 
   * @param splitVos
   */
  @Override
  protected void setFsettletypes(SettleBillVO[] splitVos) {
    for (SettleBillVO bill : splitVos) {
      // ���ý������� 0���ɹ� ��1������
      bill.getParentVO().setFsettletype(EnumSettleOrderType.IT.toInteger());
    }
  }
}
