package nc.vo.pu.m27.rule;

import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.merge.MatchMergeData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.ValidationException;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>同物料结算规则</b>
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
 * @time 2010-1-4 下午02:55:43
 */
public class SameMaterialMatchRule extends GoodsMatchRule {

  public static String[] getRBInvoiceFixedRule() {
    // 物料相同、供应商相同、财务组织相同、主无税单价相同
    return new String[] {
      InvoiceSettleVO.PK_ORG, InvoiceSettleVO.PK_SRCMATERIAL,
      InvoiceSettleVO.PK_SUPPLIER,
      // 主无税单价相同 演变为：原币相同、主无税单价相同
      InvoiceSettleVO.NORIGPRICE, InvoiceSettleVO.CORIGCURRENCYID
    };
  }

  public static String[] getRBStockFixedRule() {
    // 物料相同、财务组织相同
    return new String[] {
      StockSettleVO.PK_FINANCEORG, StockSettleVO.PK_SRCMATERIAL
    };
  }

  @Override
  public void check(MatchMergeData mmData, SettleEnvironment settleEnvironment)
      throws ValidationException {
    // a) 发票不能为空
    // b) 入库单不能为空
    // c) 手工结算不支持只有折扣、费用类发票的结算

    if (ArrayUtils.isEmpty(mmData.getGoodsInvcVos())
        && ArrayUtils.isEmpty(mmData.getStockVos())) {
      throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0166")/*@res "请选择货物发票、入库单或消耗汇总单！"*/);
    }
    if ((!ArrayUtils.isEmpty(mmData.getFeeInvcVos()) || !ArrayUtils
        .isEmpty(mmData.getDiscountInvcVos()))
        && ArrayUtils.isEmpty(mmData.getGoodsInvcVos())) {
      throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0167")/*@res "选择劳务、折扣发票后，必须选择货物发票！"*/);
    }
    super.check(mmData, settleEnvironment);
  }

}