package nc.bs.pu.m27.feesettle;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m27.feesettle.rule.PrepareFeeSettleDataRule;
import nc.bs.pu.m27.feesettle.util.FeeSettleDataContext;
import nc.bs.pu.m27.feesettle.util.FeeSettlePrivateUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.pcia.PCIAServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;

/**
 * 
 * @description
 *            费用结算时，取消利润中心传存货
 * @scene
 *      费用结算
 * @param
 * 
 *
 * @since 6.5
 * @version 2014-10-26 上午12:02:40
 * @author megnjian
 */
public class FeeSettleCancelToPCIABP implements IRule<SettleBillVO> {

  private boolean bneedQuery = false;

  private FeeSettleDataContext datactx;

  public FeeSettleCancelToPCIABP() {
    this.bneedQuery = true;
    this.datactx = new FeeSettleDataContext();
  }

  public FeeSettleCancelToPCIABP(FeeSettleDataContext feedatactx) {
    this.datactx = feedatactx;
  }

  @Override
  public void process(SettleBillVO[] vos) {
    // 费用结算相关的结算单
    List<SettleBillVO> myvos = FeeSettlePrivateUtil.findNeedFeeSettleVO(vos);
    if (myvos == null || myvos.size() == 0) {
      return;
    }
    SettleBillVO[] feevos = myvos.toArray(new SettleBillVO[0]);
    if (this.bneedQuery) {
      // 准备结算费用分摊明细、费用暂估数据
      new PrepareFeeSettleDataRule(false, true, this.datactx).process(feevos);
    }
    if (this.datactx.getBeenSavedAllotDetails() == null) {
      return;
    }
    List<String> hids = new ArrayList<String>();
    List<String> bbids = new ArrayList<String>();
    // 如果存在费用结算行，需要传递费用分摊明细主键来进行删除I9、IG
    SettleFeeAllotDetailVO[] allotDetails = null;
    allotDetails = this.datactx.getBeenSavedAllotDetailArray();
    for (SettleFeeAllotDetailVO detail : allotDetails) {
      hids.add(detail.getPk_settlebill());
      bbids.add(detail.getPk_settle_feeallot());
    }
    String[] csrcids = hids.toArray(new String[hids.size()]);
    String[] csrcbbids = bbids.toArray(new String[bbids.size()]);
    if (csrcids.length > 0 && csrcbbids.length > 0) {
      // mengjian by 20141021 启用利润中心存货核算时
      if (SysInitGroupQuery.isPCIAEnabled()) {
        // 采购费用结算后又取消结算时删除利润中心存货核算的入库调整单
        PCIAServices.deleteI9ForPOCancelFeeSettle(csrcids, csrcbbids);
      }
    }
  }

}
