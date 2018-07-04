package nc.impl.pu.m27.merge;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m27.merge.MatchMerge;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.util.SettlePublicUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>费用匹配,本类主要完成以下功能：</b>
 * <ul>
 * <li>主要用于费用结算时匹配费用类发票、入库单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wangyf
 * @time 2010-7-30 下午04:51:30
 */
public class FeeMatchMerge extends MatchMerge {

  public FeeMatchMerge(StockSettleVO[] stocks, FeeDiscountSettleVO[] fees,
      FeeDiscountSettleVO[] discounts, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment envi) {
    // 费用结算时，货物发票肯定为空
    super(null, stocks, fees, discounts, adjustInvcVos, envi);
  }

  public FeeMatchMerge(StockSettleVO[] stocks, FeeDiscountSettleVO[] fees,
      FeeDiscountSettleVO[] discounts, SettleEnvironment envi) {
    // 费用结算时，货物发票肯定为空
    super(null, stocks, fees, discounts, envi);
  }

  @Override
  protected void checkAfter() throws BusinessException {
    //
  }

  @Override
  protected void checkBefore() throws BusinessException {
    //
  }

  @Override
  protected SettleBillItemVO[] formSettleBillItems() throws BusinessException {

    // 合并入库单、费用劳务发票
    ArrayList<SettleBillItemVO> listItemVO = new ArrayList<SettleBillItemVO>();
    for (StockSettleVO stockVO : this.getStockVOs()) {
      SettleBillItemVO itemvo =
          this.createItemByStock(stockVO, UFDouble.ZERO_DBL,
              EnumMatchRowType.StockFeeSettle);
      listItemVO.add(itemvo); // 纯由库存单据生成ITEM
    }
    this.setStockFeeRowMnyNumInfo(listItemVO);// 重新设置结算金额数量单价等
    ArrayList<SettleBillItemVO> listTempItemVO = this.mergeFeeDiscount();
    if (listTempItemVO != null) {
      // 设置入库单结算行的发票日期
      this.setinvoicebilldate(listItemVO, listTempItemVO);
      listItemVO.addAll(listTempItemVO);// 费用+折扣生成结算ITEM
    }

    return listItemVO.toArray(new SettleBillItemVO[listItemVO.size()]);
  }

  @Override
  protected void packOrigData() throws BusinessException {
    //
  }

  // modify by liangchen1 private to protected
  protected void setStockFeeRowMnyNumInfo(List<SettleBillItemVO> listItemVO) {
    CostfactorViewVO[] cfviews = this.getSettleEnv().getCostFactorViews();
    for (SettleBillItemVO item : listItemVO) {
      item.setNgoodsmoney(null);
      item.setNgoodsprice(null);
      item.setNprice(null);
      item.setNsettlenum(null);
      UFDouble sumMny = SettlePublicUtil.getSumCostfactor(item, cfviews);
      sumMny = MathTool.add(sumMny, item.getNdiscount());
      item.setNmoney(sumMny);
    }
  }

  @Override
  protected SettleBillVO[] splitSettleBills(SettleBillItemVO[] voaOrgItem)
      throws BusinessException {
    // 分单
    if (ArrayUtils.isEmpty(voaOrgItem)) {
      return null;
    }

    // 费用结算不存在分单，此方法中会填充信息：集团、主组织、操作人、单据日期等信息
    SettleBillVO[] bills = this.splitBill(voaOrgItem);
    for (SettleBillVO bill : bills) {
      // 费用结算，默认都会传IA
      bill.getParentVO().setBtoia(UFBoolean.TRUE);
    }
    return bills;
  }

  /**
   * 关于 只有费用发票和入库单做结算 会计期间计算日期不能为空! 的问题，
   * wangss 2015年2月9日 (星期一) 12:59 邮件
   * 采购发票、费用发票多行与一行入库单结算时，取发票开票日期最大值作为存货采购入的会计期间计算日期。
   * added by wangzhqf 为了给后续的I9ItemVO.DACCOUNTDATE[会计期间计算日期]使用),
   * 需要保证通过入库单生成的结算单行的Invoicebilldate有值
   * 
   * @param listItemVO 入库单结算行
   * @param listTempItemVO 费用折扣 结算行
   */

  private void setinvoicebilldate(ArrayList<SettleBillItemVO> listItemVO,
      ArrayList<SettleBillItemVO> listTempItemVO) {
    // 最新时间
    UFDate lastestDate = null;
    for (SettleBillItemVO feeDisctItemVO : listTempItemVO) {
      if (lastestDate == null) {
        lastestDate = feeDisctItemVO.getInvoicebilldate();
      }
      else {
        if (feeDisctItemVO.getInvoicebilldate().after(lastestDate)) {
          lastestDate = feeDisctItemVO.getInvoicebilldate();
        }
      }
    }

    // 设置入库单结算行.开票日期
    for (SettleBillItemVO item : listItemVO) {
      item.setInvoicebilldate(lastestDate);
    }

  }
}
