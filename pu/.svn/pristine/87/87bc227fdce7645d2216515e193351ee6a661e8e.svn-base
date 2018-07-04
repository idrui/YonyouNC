package nc.bs.pu.m27.settlebill.rule;

import nc.bs.pu.m27.feesettle.util.FeeSettleDataContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 检查对应的费用分摊明细是否允许进行取消结算
 * @scene
 * 取消费用结算
 * @param
 * feedatactx 费用结算所使用的后台数据环境
 *
 * @since 6.3
 * @version 2014-10-22 下午4:00:53
 * @author zhangshqb
 */
public class ChkFeeAllotCanDeletelRule implements IRule<SettleBillVO> {

  private SettleFeeAllotDetailVO[] allotDetails;

  public ChkFeeAllotCanDeletelRule(FeeSettleDataContext feedatactx) {
    if (feedatactx == null) {
      return;
    }
    this.allotDetails = feedatactx.getBeenSavedAllotDetailArray();
  }

  @Override
  public void process(SettleBillVO[] vos) {
    if ((this.allotDetails == null) || (this.allotDetails.length == 0)) {
      return;
    }
    StringBuilder error = new StringBuilder();
    for (SettleFeeAllotDetailVO detail : this.allotDetails) {
      // 第一次费用结算并且存在后续的费用结算
      boolean bfirst = ValueUtils.getBoolean(detail.getBestfirstsettle());
      int timesafter = ValueUtils.getInt(detail.getNtimesafterfirst());
      if (bfirst && (timesafter > 0)) {
        String hid = detail.getPk_settlebill();
        String billcode = this.findSettleBillCode(vos, hid);
        if (error.indexOf(billcode) == -1) {
          error.append("[").append(billcode).append("]");
        }
      }
    }
    if (error.length() > 0) {
      String msg = error.toString() + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0074")/*@res "是第一次费用结算并且存在后续的费用结算,不能进行取消操作！"*/;
      ExceptionUtils.wrappBusinessException(msg);
    }
  }

  private String findSettleBillCode(SettleBillVO[] vos, String hid) {
    if ((vos == null) || (hid == null)) {
      return null;
    }
    for (SettleBillVO vo : vos) {
      if (hid.equals(vo.getParentVO().getPk_settlebill())) {
        return vo.getParentVO().getVbillcode();
      }
    }
    return null;
  }
}