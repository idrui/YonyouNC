package nc.bs.pu.it.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.pu.m27.feesettle.util.M4AFeeSettleToIAUtil;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.util.CirVOUtil;

import org.apache.commons.collections.CollectionUtils;

/**
 * 调整货物行直接传调整单不受差异转入方式参数控制
 * 
 * @since 6.31
 * @version 2013-11-20 下午03:05:16
 * @author mengjian
 */
public class M4AFeeSettleToIAUtilForIT extends M4AFeeSettleToIAUtil {

  public M4AFeeSettleToIAUtilForIT(SettleBillHeaderVO header,
      Collection<SettleBillItemVO> items) {
    super(header, items);
  }

  private Map<String, SettleFeeAllotDetailVO> getStlAllotMap(
      Collection<SettleFeeAllotDetailVO> noneEstBbvos) {
    if (CollectionUtils.isEmpty(noneEstBbvos)) {
      return new HashMap<String, SettleFeeAllotDetailVO>();
    }
    return CirVOUtil.createKeyVOMap(noneEstBbvos
        .toArray(new SettleFeeAllotDetailVO[noneEstBbvos.size()]));
  }

  private boolean isAdjustGoods(SettleBillItemVO toIAStlItem) {
    if (EnumMatchRowType.AdjustGoods.value().equals(toIAStlItem.getFrowtype())) {
      return true;
    }
    return false;
  }

  /**
   * 将原始的结算行，按分摊明细拆分<br>
   * 并根据参数及分摊依据的货物结算和暂估情况来生成到I9或IG的结算新行
   * 
   * @param orgItem 原始结算行
   * @param toI9Lst 存放到I9的拆分后结算行
   * @param toIgLst 存放到IG的拆分后结算行
   * @param stlAllotVoLst 结算拆分明细
   * @param forceToCost 是否强制进成本走I9
   */
  @Override
  protected void genToIAAdjBillStlItems(SettleBillItemVO orgItem,
      List<SettleBillItemVO> toI9Lst, List<SettleBillItemVO> toIgLst,
      List<SettleFeeAllotDetailVO> stlAllotVoLst, boolean forceToCost) {
    List<SettleBillItemVO> allToLst =
        this.getToIAStlItemsByAllot(orgItem, stlAllotVoLst);
    Map<String, SettleFeeAllotDetailVO> stlAllotMap =
        this.getStlAllotMap(stlAllotVoLst);
    for (SettleBillItemVO toIAStlItem : allToLst) {
      // 根据需求（见此类的描述），如果费用结算对应的分摊货物已经传过成本，
      // 调差方式为补差，则走损益，forceToCost代表暂估过的费用回冲时，强制走成本
      if (!forceToCost
          && PUParaValue.po13.profit_loss.equals(this.getParaPO13())
          && this.goodsHavenToIA(toIAStlItem, stlAllotMap)
      /* && !this.isAdjustGoods(toIAStlItem) */) {
        toIgLst.add(this.adjustForIGStlItem(toIAStlItem,
            stlAllotMap.get(toIAStlItem.getPk_settle_feeallot()))); // 对IG进行一些处理
      }
      else {
        toI9Lst.add(toIAStlItem);
      }
    }
  }

}
