package nc.pubimpl.pu.it.merge;

import java.util.ArrayList;
import java.util.HashSet;

import nc.impl.pu.m27.merge.GoodsMatchMerge;
import nc.pubimpl.pu.it.rule.MatchMergeFeeDiscountAdjustRule;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;

/**
 * @since 6.31
 * @version 2013-11-26 上午09:30:57
 * @author mengjian
 */
public class InvoiceAutoMatchWithStockMergeForIT extends AutoMatchMergeForIT {

  public InvoiceAutoMatchWithStockMergeForIT(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, adjustInvcVos, settleEnv);
    // 根据发票找到来源单据，如果没有来源，也不用结算
    HashSet<String> hsetSrcBillType =
        CirVOUtil.getDistinctFieldSet(this.getInvoiceVOs(),
            InvoiceSettleVO.CSOURCETYPECODE);
    if (hsetSrcBillType == null || hsetSrcBillType.size() == 0) {
      return;
    }

    // 来源同一明细单
    this.setAddedMergeCondition(this.AddedMergeCondition_ByOrder);
  }

  /**
   * 方法功能描述：简要描述本方法的功能。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-10 下午03:09:32
   */
  @Override
  protected ArrayList<SettleBillItemVO> mergeProcess()  {

    // -----------3---------------
    // 存放所有合并完的结算体
    ArrayList<SettleBillItemVO> listItemVO = new ArrayList<SettleBillItemVO>();
    ArrayList<SettleBillItemVO> listTempItemVO = null;
    for (int i = 0; i < this.getDataClassify().length; i++) {

      // 负结算
      listTempItemVO =
          this.mergeInvoiceStock(this.getDataClassify()[i],
              GoodsMatchMerge.CombineType_MinusInvoiceStock);
      if (listTempItemVO != null) {
        listItemVO.addAll(listTempItemVO);
      }

      // 正结算
      listTempItemVO =
          this.mergeInvoiceStock(this.getDataClassify()[i],
              GoodsMatchMerge.CombineType_PlusInvoiceStock);
      if (listTempItemVO != null) {
        listItemVO.addAll(listTempItemVO);
      }

      // 把结算完成的和结算未完成的记录下来
      this.totalFinishedAndUnFinished(i);
    }

    return listItemVO;

  }

  @Override
  protected void recordFeeDiscountInfo(SettleBillItemVO[] voaItem,
      StockSettleVO[] m_voaStock) {
    new MatchMergeFeeDiscountAdjustRule(this.getSettleEnv(), m_voaStock)
        .process(voaItem);
  }
}
