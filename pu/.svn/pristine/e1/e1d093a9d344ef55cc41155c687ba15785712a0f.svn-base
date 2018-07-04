package nc.bs.pu.m27.feesettle.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import nc.bs.pu.m27.feesettle.util.FeeSettleDataContext;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * 
 * @description
 * 持久化结算分摊明细
 * @scene
 * 费用结算
 * @param
 * datactx 费用结算所使用的后台数据环境 
 *
 * @since 6.3
 * @version 2014-10-22 下午4:15:22
 * @author zhangshqb
 */
public class SaveFeeAllotDetailRule implements IRule<SettleBillVO> {

  private MapList<String, SettleFeeAllotDetailVO> details;

  public SaveFeeAllotDetailRule(FeeSettleDataContext datactx) {
    this.details = datactx.getNoneSavedAllotDetails();
  }

  @Override
  public void process(SettleBillVO[] vos) {
    if (this.details == null || this.details.size() == 0) {
      return;
    }

    // 1、得到待持久化数据
    SettleFeeAllotDetailVO[] beforeSaveDetails = this.getSaveAllotDetails();

    // 2、结算分摊明细持久化
    new VOInsert<SettleFeeAllotDetailVO>().insert(beforeSaveDetails);
  }

  private SettleFeeAllotDetailVO[] getSaveAllotDetails() {
    if (this.details == null || this.details.size() == 0) {
      return null;
    }
    List<SettleFeeAllotDetailVO> data = new ArrayList<SettleFeeAllotDetailVO>();
    for (Entry<String, List<SettleFeeAllotDetailVO>> entry : this.details
        .entrySet()) {
      data.addAll(entry.getValue());
    }
    return data.toArray(new SettleFeeAllotDetailVO[data.size()]);
  }
}
