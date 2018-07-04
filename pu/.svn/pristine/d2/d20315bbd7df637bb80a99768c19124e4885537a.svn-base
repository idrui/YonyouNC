package nc.vo.pu.m27.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m27.ISettleMatch;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.merge.MatchMergeData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.collections.CollectionUtils;
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
 * @time 2010-1-6 下午03:00:23
 */
public class AutoMatchRule extends MatchRule {

  /**
   * 发票与入库单结算
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-8 上午11:49:13
   */
  public static String[] getInvoiceStockFixedRule() {
    // □√ 财务组织相同 □√ 供应商相同 □√ 物料相同

    return new String[] {
      StockSettleVO.PK_FINANCEORG, StockSettleVO.PK_SRCMATERIAL,
      StockSettleVO.PK_SUPPLIER
    };
  }

  /**
   * 发票对冲规则
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-8 上午10:32:30
   */
  public static String[] getRBInvoiceFixedRule() {

    // □√ 财务组织相同 　　　□√ 供应商相同
    // □√ 物料相同 　　　□√ 发票类型相同
    // □√ 主无税单价相同

    return new String[] {
      InvoiceSettleVO.PK_ORG, InvoiceSettleVO.PK_SRCMATERIAL,
      InvoiceSettleVO.PK_SUPPLIER, InvoiceSettleVO.VTRANTYPECODE,
      // 主无税单价相同 演变为：原币相同、主无税单价相同
      InvoiceSettleVO.NORIGPRICE, InvoiceSettleVO.CORIGCURRENCYID
    };
  }

  /**
   * 红兰入库单规则
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-8 上午10:33:07
   */
  public static String[] getRBStockFixedRule() {
    // □√ 财务组织相同 □√ 物料相同

    return new String[] {
      StockSettleVO.PK_FINANCEORG, StockSettleVO.PK_SRCMATERIAL
    };
  }

  @Override
  public void check(MatchMergeData mmData, SettleEnvironment settleEnvironment)
      throws ValidationException {
    //
  }

  @Override
  public void filter(MatchMergeData mmData, SettleEnvironment settleEnvironment) {
    super.filter(mmData, settleEnvironment);
    // 过滤掉与当前货物发票不相关的费用发票
    this.filterNonRelationFeeDiscount(mmData);
    // 过滤掉已经暂估的费用发票
    this.filterEstAPFee(mmData);
  }

  private void filterEstAPFee(MatchMergeData mmData) {
    if (ArrayUtils.isEmpty(mmData.getStockVos())
        || ArrayUtils.isEmpty(mmData.getFeeInvcVos())) {
      return;
    }
    try {
      MapList<String, String> stockFeeEstApMap =
          NCLocator.getInstance().lookup(ISettleMatch.class)
              .getEstAPFeeMaterial(mmData.getStockVos());
      if (stockFeeEstApMap.size() == 0) {
        return;
      }
      // 过滤掉暂估应付的费用发票
      this.filterEstAPFee(mmData, stockFeeEstApMap);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

  private void filterEstAPFee(MatchMergeData mmData,
      MapList<String, String> stockFeeEstApMap) {
    FeeDiscountSettleVO[] vos = mmData.getFeeInvcVos();
    Set<String> estMarSet = new HashSet<String>();
    for (Entry<String, List<String>> entry : stockFeeEstApMap.entrySet()) {
      estMarSet.addAll(entry.getValue());
    }
    List<FeeDiscountSettleVO> filterLst = new ArrayList<FeeDiscountSettleVO>();
    for (FeeDiscountSettleVO vo : vos) {
      // 如果暂估过应付且发票已经传过应付则直接过滤掉
      if (UFBoolean.TRUE.equals(vo.getBapflag())
          && estMarSet.contains(vo.getPk_material())) {
        continue;
      }
      filterLst.add(vo);
    }
    mmData.setFeeInvcVos(CollectionUtils.isEmpty(filterLst) ? null : filterLst
        .toArray(new FeeDiscountSettleVO[filterLst.size()]));
  }

  private List<FeeDiscountSettleVO> filterNonRelationFeeDiscount(
      FeeDiscountSettleVO[] feeDiscntVos, Set<String> goodsInvcs) {
    List<FeeDiscountSettleVO> filterLst = new ArrayList<FeeDiscountSettleVO>();
    if (!ArrayUtils.isEmpty(feeDiscntVos)) {
      for (FeeDiscountSettleVO feeDiscount : feeDiscntVos) {
        String pk_invoice = feeDiscount.getPk_invoice();
        if (goodsInvcs.contains(pk_invoice)
            || goodsInvcs.contains(feeDiscount.getPk_parentinvoice())) {
          filterLst.add(feeDiscount);
        }
      }
    }
    return filterLst;
  }

  private void filterNonRelationFeeDiscount(MatchMergeData mmData) {
    Set<String> goodsInvcs = null;
    if (ArrayUtils.isEmpty(mmData.getGoodsInvcVos())) {
      goodsInvcs = new HashSet<String>();
    }
    else {
      goodsInvcs =
          CirVOUtil.getDistinctFieldSet(mmData.getGoodsInvcVos(),
              InvoiceSettleVO.PK_INVOICE);
    }
    List<FeeDiscountSettleVO> filterLst =
        this.filterNonRelationFeeDiscount(mmData.getDiscountInvcVos(),
            goodsInvcs);
    mmData.setDiscountInvcVos(CollectionUtils.isEmpty(filterLst) ? null
        : filterLst.toArray(new FeeDiscountSettleVO[filterLst.size()]));
    filterLst =
        this.filterNonRelationFeeDiscount(mmData.getFeeInvcVos(), goodsInvcs);
    mmData.setFeeInvcVos(CollectionUtils.isEmpty(filterLst) ? null : filterLst
        .toArray(new FeeDiscountSettleVO[filterLst.size()]));
  }

}
