package nc.bs.pu.m27.feesettle;

import java.util.List;

import nc.bs.pu.m27.feesettle.rule.PrepareFeeSettleDataRule;
import nc.bs.pu.m27.feesettle.rule.SettleDetailAndEstInfoSetRule;
import nc.bs.pu.m27.feesettle.rule.WriteNtimesafterfirstRule;
import nc.bs.pu.m27.feesettle.rule.WriteStockSettleNumRule;
import nc.bs.pu.m27.feesettle.util.FeeSettleDataContext;
import nc.bs.pu.m27.feesettle.util.FeeSettlePrivateUtil;
import nc.bs.pu.m27.plugin.SettlebillPluginPoint;
import nc.bs.pu.m27.settlebill.rule.ChkFeeAllotCanDeletelRule;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;

/**
 * 
 * @description
 * 完成费用结算的业务实现
 * @scene
 * 删除结算单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午4:05:58
 * @author zhangshqb
 */
public class FeeSettleDeleteBP implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] bills) {
    // 费用结算相关的结算单
    List<SettleBillVO> myvos = FeeSettlePrivateUtil.findNeedFeeSettleVO(bills);
    if (myvos == null || myvos.size() == 0) {
      return;
    }
    SettleBillVO[] feevos = myvos.toArray(new SettleBillVO[0]);

    // 结算分摊明细
    FeeSettleDataContext feectx = new FeeSettleDataContext();
    // 准备结算费用分摊明细、费用暂估数据
    new PrepareFeeSettleDataRule(false, true, feectx).process(feevos);
    SettleFeeAllotDetailVO[] details = feectx.getBeenSavedAllotDetailArray();
    if (details == null || details.length == 0) {
      // 没有费用分摊明细意味着不是费用结算，直接返回
      return;
    }
    SettlebillPluginPoint pt = SettlebillPluginPoint.CancelFeeSettleBP;
    AroundProcesser<SettleBillVO> proc = new AroundProcesser<SettleBillVO>(pt);

    // 删除前检查：如果是第一次费用结算，并且存在后续的费用结算，则不能删除
    proc.addBeforeFinalRule(new ChkFeeAllotCanDeletelRule(feectx));

    proc.before(feevos);

    new VODelete<SettleFeeAllotDetailVO>().delete(details);

    // 回写入库单(累计费用结算次数)
    proc.addAfterFinalRule(new WriteStockSettleNumRule(false));
    // 回写暂估费用物料第一次结算的结算单ID
    proc.addAfterFinalRule(new SettleDetailAndEstInfoSetRule(false, feectx));
    // 回写结算分摊明细的后续累计费用结算次数
    proc.addAfterFinalRule(new WriteNtimesafterfirstRule(false));
    // 删除费用结算单前删除其所传递的存货核算单据
    proc.addAfterFinalRule(new FeeSettleCancelToIABP(feectx));
    // 删除费用结算单前删除其所传递的利润中心存货核算单据
    proc.addAfterFinalRule(new FeeSettleCancelToPCIABP(feectx));
    proc.after(feevos);
  }
}
