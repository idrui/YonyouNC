package nc.impl.pu.m27.merge;

import java.util.ArrayList;
import java.util.HashSet;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

/**
 * <p>
 * <b>发票自动匹配，但是有库存单据</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author wangyf
 * @time 2010-3-25 上午09:14:30
 */
public class InvoiceAutoMatchWithStockMerge extends AutoMatchMerge {

  /**
   * InvoiceAutoMatchWithStockMerge 的构造子
   * 
   * @param voaInvoice
   * @param voaStock
   * @param voaFee
   * @param voaDiscount
   * @param settleEnv
   */
  public InvoiceAutoMatchWithStockMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, settleEnv);

    // 根据发票找到来源单据，如果没有来源，也不用结算
    HashSet<String> hsetSrcBillType =
        CirVOUtil.getDistinctFieldSet(this.getInvoiceVOs(),
            InvoiceSettleVO.CSOURCETYPECODE);
    if ((hsetSrcBillType == null) || (hsetSrcBillType.size() == 0)) {
      return;
    }

    String sSrcBillType = hsetSrcBillType.iterator().next();
    if (PubAppTool.isEqual(sSrcBillType, POBillType.Order.getCode())
        || PubAppTool.isEqual(sSrcBillType, SCBillType.Order.getCode())) {
      // 只能和来源订单的入库单结算
      this.setAddedMergeCondition(this.AddedMergeCondition_ByOrder);
    }
    else {
      // 只能和来源库存单据结算
      this.setAddedMergeCondition(this.AddedMergeCondition_ByStock);
    }

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
  protected ArrayList<SettleBillItemVO> mergeProcess(){

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
      this.getDataClassify()[i].sumupResidualBill();
      if (this.getDataClassify()[i].getFinishedInvoices() != null) {
        this.getTotalFinishedInvoices().addAll(
            this.getDataClassify()[i].getFinishedInvoices());
      }
      if (this.getDataClassify()[i].getUnfinishedInvoices() != null) {
        this.getTotalUnfinishedInvoices().addAll(
            this.getDataClassify()[i].getUnfinishedInvoices());
      }
    }

    return listItemVO;

  }

}
