package nc.vo.pu.m27.rule;

import java.util.LinkedHashSet;
import java.util.Set;

import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.GeneralInSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.merge.MatchMergeData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.util.SettlePublicUtil;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>自动结算规则</b>
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
 * @time 2010-1-6 下午03:00:44
 */
public class DifferentMaterialMatchRule extends GoodsMatchRule {

  @Override
  public void check(MatchMergeData mmData, SettleEnvironment settleEnvironment)
      throws ValidationException {
    // 执行检查
    this.doCheck(mmData.getGoodsInvcVos(), mmData.getStockVos());
    this.checkInvoiceByOrder(mmData.getGoodsInvcVos(), mmData.getStockVos());
    super.check(mmData, settleEnvironment);
  }

  /**
   * 参照订单或者非自制的采购入库单生成的采购发票不允许与其他采购订单的入库单进行结算
   * 
   * @param voaInvoice
   * @param voaStock
   */
  private void checkInvoiceByOrder(InvoiceSettleVO[] voaInvoice,
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

  private StringBuilder checkStockToAP(StockSettleVO[] ssVos) {
    StringBuilder sb = new StringBuilder();
    Set<String> billcode = new LinkedHashSet<String>();
    Set<String> generalinBillcode = new LinkedHashSet<String>();
    for (StockSettleVO ssVo : ssVos) {
      if (EnumToAPFlag.NotToAP.toInteger().equals(ssVo.getFdirtoaptype())) {
        continue;
      }
      if (ssVo instanceof GeneralInSettleVO) {
        generalinBillcode.add(ssVo.getVbillcode());
      }
      else {
        billcode.add(ssVo.getVbillcode());
      }
    }
    if (generalinBillcode.size() > 0) {
      sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004060_0", "04004060-0149")/* @res "其他入库单不能进行异物料结算：" */);
      for (String bc : generalinBillcode) {
        sb.append("[");
        sb.append(bc);
        sb.append("]");
      }
      sb.append("\n");
    }
    if (billcode.size() > 0) {
      sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004060_0", "04004060-0150")/* @res "以下入库单已经暂估或确认过应付，不允许进行异物料结算：" */);
      for (String bc : billcode) {
        sb.append("[");
        sb.append(bc);
        sb.append("]");
      }
    }

    if (sb.length() == 0) {
      return null;
    }
    return sb;
  }

  private void doCheck(InvoiceSettleVO[] voaInvoice, StockSettleVO[] voaStock)
      throws ValidationException {
    if (ArrayUtils.isEmpty(voaInvoice)) {
      throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004060_0", "04004060-0151")/* @res "请选择货物发票！" */);
    }
    if (ArrayUtils.isEmpty(voaStock)) {
      throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004060_0", "04004060-0152")/* @res "请选择入库单！" */);
    }

    // 1、 选中的入库与发票行中，存在入库数量与发票数量正负方向不一致的数据,请重新选择。
    boolean bStockPlus = false;
    boolean bStockMinus = false;
    {
      for (StockSettleVO stockVO : voaStock) {
        if (MathTool.compareTo(stockVO.getNinnum(), UFDouble.ZERO_DBL) < 0) {
          bStockMinus = true;
        }
        else {
          bStockPlus = true;
        }
      }
      // 库存单据不可正负同时存在
      if (bStockMinus && bStockPlus) {
        throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0153")/*
                                                                     * @res
                                                                     * "入库单不能正负同时存在！"
                                                                     */);
      }
    }
    boolean bInvoicePlus = false;
    boolean bInvoiceMinus = false;
    {
      for (InvoiceSettleVO invoiceVO : voaInvoice) {
        if (MathTool.compareTo(invoiceVO.getNnum(), UFDouble.ZERO_DBL) < 0) {
          bInvoiceMinus = true;
        }
        else {
          bInvoicePlus = true;
        }
      }
      if (bInvoiceMinus && bInvoicePlus) {
        throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0154")/*
                                                                     * @res
                                                                     * "发票不能正负同时存在！"
                                                                     */);
      }
    }
    if (bInvoiceMinus && bStockPlus || bInvoicePlus && bStockMinus) {
      throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004060_0", "04004060-0155")/*
                                                    * @res
                                                    * "选中的入库与发票行中，存在入库数量与发票数量正负方向不一致的数据,请重新选择。"
                                                    */);
    }
    StringBuilder sb = this.checkStockToAP(voaStock);
    if (null != sb) {
      // 检查入库单是否暂估或确认过应付
      throw new ValidationException(sb.toString());
    }
  }

}
