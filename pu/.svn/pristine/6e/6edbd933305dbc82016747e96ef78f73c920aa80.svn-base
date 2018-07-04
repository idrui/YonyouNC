package nc.impl.pu.m27.merge;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>无发票匹配</b>
 * <ul>
 * <li>支持单价和结算金额为零的结算，如有暂估应付，只冲暂估，不传应付
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
 * @time 2010-3-25 上午10:24:27
 */
public class WithoutInoiceMatchMerge extends SameMaterialMerge {

  /**
   * 为进口提供
   * 
   * @param voaInvoice
   * @param voaStock
   * @param voaFee
   * @param voaDiscount
   * @param adjustInvcVos
   * @param settleEnv
   */
  public WithoutInoiceMatchMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, adjustInvcVos, settleEnv);

    // 自动结算入库数量不可超出发票数量
    this.getSettleEnv().setAllowStockBeyondInvoice(true);
    this.getSettleEnv().setStockHaveToMatchAll(false);
    // 根据来源单据入库单匹配
    this.setAddedMergeCondition(this.AddedMergeCondition_ByStock);
  }

  public WithoutInoiceMatchMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, settleEnv);

    // 自动结算入库数量不可超出发票数量
    this.getSettleEnv().setAllowStockBeyondInvoice(true);
    this.getSettleEnv().setStockHaveToMatchAll(false);
    // 根据来源单据入库单匹配
    this.setAddedMergeCondition(this.AddedMergeCondition_ByStock);
  }

  @Override
  protected void checkAfter() throws BusinessException {
    // 无发票结算不用检查
  }

  @Override
  protected boolean isCanSplit(SettleBillVO vo) {
    // 无发票结算不分单
    return false;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pu.m27.merge.SameMaterialMerge#splitSettleBills(nc.vo.pu.m27.entity.SettleBillItemVO[])
   */
  @Override
  protected SettleBillVO[] splitSettleBills(SettleBillItemVO[] voaOrgItem)
      throws BusinessException {
    SettleBillVO[] vosBill = super.splitSettleBills(voaOrgItem);

    if (ArrayUtils.isEmpty(vosBill)) {
      return null;
    }

    // 设置虚拟发票结算标志
    for (int i = 0; i < vosBill.length; i++) {
      vosBill[i].getParentVO().setBvirtualsettle(UFBoolean.TRUE);
    }
    // 无发票结算不分单
    return vosBill;
  }

}
