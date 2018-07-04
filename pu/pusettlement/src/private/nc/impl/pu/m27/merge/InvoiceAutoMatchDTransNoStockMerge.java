package nc.impl.pu.m27.merge;

import java.util.ArrayList;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.InvoiceSettleVOUtil;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>直运发票无入库单的匹配：</b>
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
 * @time 2010-3-24 下午04:39:37
 */
public class InvoiceAutoMatchDTransNoStockMerge extends GoodsMatchMerge {
  public InvoiceAutoMatchDTransNoStockMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, settleEnv);

  }

  /**
   * 形成结算单
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voInvoice
   * @return <p>
   * @author wangyf
   * @time 2010-3-24 下午04:55:38
   */
  private SettleBillItemVO settleInvoice(InvoiceSettleVO voInvoice) {
    UFDouble dWillSettleNum =
        MathTool.sub(InvoiceSettleVOUtil.getCurrentRealSettleNum(voInvoice),
            voInvoice.getNcurrentaccumsettlenum());
    if (MathTool.compareTo(dWillSettleNum, UFDouble.ZERO_DBL) == 0) {
      return null;
    }

    return this.createItemByInvoice(voInvoice, dWillSettleNum,
        EnumMatchRowType.InvoiceDTransPO);

  }

  @Override
  protected void checkAfter() throws BusinessException {
    //
  }

  @Override
  protected void checkBefore() throws BusinessException {
    //
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.m27.merge.MatchMerge#formSettleBillItems()
   */
  @Override
  protected SettleBillItemVO[] formSettleBillItems() throws BusinessException {
    // TODO 分摊
    // by zhaoyh,tianft,suncong1,zyx3 V60与V57保持一致，直运无入库结算不能同时进行费用结算
    // V6X再考虑支持费用结算，并且结算时会将没有入库单的费用结算行去除掉的算法也要去掉
    // new FeeAllot(this.getInvoiceVOs(), this.getFeeVOs(),
    // this.getDiscountVOs(),
    // this.getSettleEnv()).allot();
    // 存放所有合并完的结算体
    ArrayList<SettleBillItemVO> listItemVO = new ArrayList<SettleBillItemVO>();
    for (InvoiceSettleVO voInvioce : this.getInvoiceVOs()) {
      SettleBillItemVO voItem = this.settleInvoice(voInvioce);
      if (voItem != null) {
        listItemVO.add(voItem);
      }
    }
    // 劳务、折扣
    ArrayList<SettleBillItemVO> listTempItemVO = this.mergeFeeDiscount();
    if (listTempItemVO != null) {
      listItemVO.addAll(listTempItemVO);
    }
    return ListUtil.isEmpty(listItemVO) ? null : listItemVO
        .toArray(new SettleBillItemVO[listItemVO.size()]);
  }

  @Override
  protected void packOrigData() throws BusinessException {
    return;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.m27.merge.MatchMerge#splitSettleBills(nc.vo.pu.m27.entity.SettleBillItemVO[])
   */
  @Override
  protected SettleBillVO[] splitSettleBills(SettleBillItemVO[] voaOrgItem)
      throws BusinessException {
    // 分单
    if (ArrayUtils.isEmpty(voaOrgItem)) {
      return null;
    }
    // 父方法的分单
    SettleBillVO[] vos = this.splitBill(voaOrgItem);
    return vos;
  }
}
