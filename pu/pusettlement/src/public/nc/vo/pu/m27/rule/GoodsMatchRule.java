package nc.vo.pu.m27.rule;

import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.merge.MatchMergeData;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.ValidationException;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ICBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>公共结算规则</b>
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
 * @time 2010-1-6 下午03:01:03
 */
public class GoodsMatchRule extends MatchRule {

  @Override
  public void check(MatchMergeData mmData, SettleEnvironment settleEnvironment)
      throws ValidationException {
    // 执行检查
    this.doCheck(mmData.getStockVos());
    // 检查是否有费用暂估应付的入库单，且发票也已经传过应付
    this.checkEstApFee(mmData);
  }

  private void checkEstApFee(MatchMergeData mmData) {
    // 调用工具类检查
    new FeeEstApCheckRule(mmData.getStockVos(), mmData.getFeeInvcVos()).check();
  }

  private void doCheck(StockSettleVO[] voaStock) throws ValidationException {
    // 自动结算、手工结算公共规则：
    // 19. 采购入库单行、采购发票行可参与所有结算（包括自动结算）的都必须满足以下条件：
    // b) 已全部结算的采购入库单只能参与费用结算。提示“异物料结算不允许选择已结采购入库单，不能进行异物料结算！”。
    // g) 资产类存货入资产仓满足以上条件也可以参与结算。

    // 17. 入库单中调拨入库单和其他入库单只能参与费用结算，如果用户选中这两种单据进行同物料结算、异物料结算、无发票结算操作时，则要提示“
    // 调拨入库单或者其他入库单无法进行此操作。”

    if (ArrayUtils.isEmpty(voaStock)) {
      return;
    }
    for (int i = 0; i < voaStock.length; i++) {

      // 入库单中调拨入库单和其他入库单只能参与费用结算，如果用户选中这两种单据进行同物料结算、异物料结算、无发票结算操作时，则要提示“
      // 调拨入库单或者其他入库单无法进行此操作。”
      if (voaStock[i].getCbilltypecode().equals(ICBillType.GeneralIn.getCode())
          || voaStock[i].getCbilltypecode()
              .equals(ICBillType.TransIn.getCode())) {
        throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0163")/*@res "其他入库单无法进行此操作。"*/);
      }

      // 已全部结算的采购入库单只能参与费用结算。提示“已全部结算的入库单不能进行此结算！”。
      if (MathTool.compareTo(voaStock[i].getNinnum(), voaStock[i]
          .getNaccumsettlenum()) == 0) {
        throw new ValidationException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0164")/*@res "入库单'"*/ + voaStock[i].getVbillcode()
            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0165")/*@res "'已全部结算，不能进行此结算！"*/);
      }
    }

  }
}