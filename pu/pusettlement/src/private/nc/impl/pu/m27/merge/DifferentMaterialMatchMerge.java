package nc.impl.pu.m27.merge;

import java.util.ArrayList;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.util.SettlePublicUtil;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>异物料匹配</b>
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
 * @time 2010-3-25 上午10:21:23
 */
public class DifferentMaterialMatchMerge extends GoodsMatchMerge {

  public DifferentMaterialMatchMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, adjustInvcVos, settleEnv);

    // 入库数量是否可超出发票数量
    this.getSettleEnv().setAllowStockBeyondInvoice(true);
    this.getSettleEnv().setStockHaveToMatchAll(true);

  }

  public DifferentMaterialMatchMerge(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, settleEnv);

    // 入库数量是否可超出发票数量
    this.getSettleEnv().setAllowStockBeyondInvoice(true);
    this.getSettleEnv().setStockHaveToMatchAll(true);

  }

  @Override
  protected void checkAfter() throws BusinessException {
    //
  }

  @Override
  protected void checkBefore() throws BusinessException {

    // 入库单行的本次发票结算金额之和不等于发票行的本次发票结算金额之和,异存货结算不能进行！
    DataClassify curData = this.getDataClassify()[0];
    UFDouble dAccumInvoiceMoney = UFDouble.ZERO_DBL;
    {
      for (InvoiceSettleVO voInvoice : curData.getOrigInvoices()) {
        // --------check begin
        dAccumInvoiceMoney =
            MathTool.add(dAccumInvoiceMoney,
                voInvoice.getNcurrentinvoicesettlemny());
      }
    }
    UFDouble dAccumStockInvoiceMoney = UFDouble.ZERO_DBL;
    {
      for (StockSettleVO voStock : curData.getOrigStockes()) {
        // --------check begin
        dAccumStockInvoiceMoney =
            MathTool.add(dAccumStockInvoiceMoney,
                voStock.getNcurrentinvoicesettlemny());
      }
    }
    if (MathTool.compareTo(dAccumInvoiceMoney, dAccumStockInvoiceMoney) != 0) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004060_0", "04004060-0109")/*
                                                    * @res
                                                    * "入库单行的本次发票结算金额之和不等于发票行的本次发票结算金额之和,异存货结算不能进行！"
                                                    */);
    }
    this.checkInvoiceByOrder(
        curData.getOrigInvoices().toArray(
            new InvoiceSettleVO[curData.getOrigInvoices().size()]),
        curData.getOrigStockes().toArray(
            new StockSettleVO[curData.getOrigStockes().size()]));
  }

  /**
   * 参照订单或者非自制的采购入库单生成的采购发票不允许与其他采购订单的入库单进行结算
   * 
   * @param voaInvoice
   * @param voaStock
   */
  protected void checkInvoiceByOrder(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock) {
    if (SettlePublicUtil.fromSameOrder(voaInvoice, voaStock)) {
      return;
    }
    ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004060_0", "04004060-0108")/*
                                                                 * @res
                                                                 * "参照订单或者非自制的采购入库单生成的采购发票，不允许与其他来源采购订单的入库单进行结算！"
                                                                 */);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.m27.merge.MatchMerge#formSettleBillItems()
   */
  @Override
  protected SettleBillItemVO[] formSettleBillItems() throws BusinessException {

    // -----------3---------------
    // 存放所有合并完的结算体：库存、发票
    ArrayList<SettleBillItemVO> listItemVO = new ArrayList<SettleBillItemVO>();
    for (StockSettleVO stockVO : this.getDataClassify()[0].getOrigStockes()) {
      SettleBillItemVO newItem =
          this.createItemByStock(stockVO, stockVO.getNcurrentsettlenum(),
              EnumMatchRowType.StockInDiffMatch);
      // 重新计算结算金额和单价
      newItem.setNgoodsmoney(stockVO.getNcurrentinvoicesettlemny());// 前台记的分摊到入库单的发票金额
      UFDouble gdPrice =
          ScaleUtils.getScaleUtilAtBS().adjustSoPuPriceScale(
              stockVO.getNcurrentinvoicesettlemny().div(
                  stockVO.getNcurrentsettlenum(), UFDouble.DEFAULT_POWER),
              stockVO.getCorigcurrencyid());
      newItem.setNgoodsprice(gdPrice);
      newItem.setNprice(gdPrice);
      newItem.setNmoney(newItem.getNgoodsmoney());
      // 如有费用结算，则总的结算金额和单价后续规则会有处理
      listItemVO.add(newItem);
    }
    for (InvoiceSettleVO invoiceVO : this.getDataClassify()[0]
        .getOrigInvoices()) {
      listItemVO
          .add(this.createItemByInvoice(invoiceVO,
              invoiceVO.getNcurrentsettlenum(),
              EnumMatchRowType.InvoiceInDiffMatch));
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

    DataClassify data =
        new DataClassify(this.getInvoiceVOs(), this.getStockVOs());
    data.classifyPlusMinus();

    this.setDataClassify(new DataClassify[] {
      data
    });

  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.m27.merge.MatchMerge#splitSettleBills()
   */
  @Override
  protected SettleBillVO[] splitSettleBills(SettleBillItemVO[] voaOrgItem)
      throws BusinessException {
    // 分单
    if (ArrayUtils.isEmpty(voaOrgItem)) {
      return null;
    }
    // 异物料结算无分单
    return this.splitBill(voaOrgItem);
  }
}
