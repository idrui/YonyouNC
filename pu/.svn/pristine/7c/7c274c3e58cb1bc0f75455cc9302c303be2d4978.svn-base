package nc.bs.pu.it.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.pu.m27.feesettle.util.M45FeeSettleToIAUtil;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.collections.CollectionUtils;

/**
 * 调整货物行直接传调整单不受差异转入方式参数控制
 * 
 * @since 6.31
 * @version 2013-11-20 下午03:05:06
 * @author mengjian
 */
public class M45FeeSettleToIAUtilForIT extends M45FeeSettleToIAUtil {

  /**
   * 记录参数po13为损益时，结算单调整货物行传调整单I9
   */
  private Set<String> mapAdjustoodsTOI9 = new HashSet<String>();

  public M45FeeSettleToIAUtilForIT(SettleBillHeaderVO header,
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
    if (!MathTool.isZero(toIAStlItem.getNadjustmny())) {
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
          && this.goodsHavenToIA(toIAStlItem, stlAllotMap)) {
        // // mengjian 参数设置为损益，但存在调整货物的结算单行，调整货物的这部分还是要传调整单的
        // if (this.isAdjustGoods(toIAStlItem)) {
        // this.mapAdjustoodsTOI9.add(toIAStlItem.getPk_settlebill_b());
        // toI9Lst.add((SettleBillItemVO) toIAStlItem.clone());
        // }
        // else {
        toIgLst.add(this.adjustForIGStlItem(
            (SettleBillItemVO) toIAStlItem.clone(),
            stlAllotMap.get(toIAStlItem.getPk_settle_feeallot()))); // 对IG进行一些处理
        // }
      }
      else {
        toI9Lst.add(toIAStlItem);
      }
    }
  }

  /**
   * mengjian 采购不做处理，进口结算时需处理进口调整货物行
   * 
   * @param toI9Datas
   */
  @Override
  protected void processToI9SettleItem(List<SettleBillItemVO> toI9Datas) {
    // 采购不做处理，进口结算时需处理进口调整货物行
    if (PUParaValue.po13.cost.equals(this.getParaPO13())) {
      return;
    }
    for (SettleBillItemVO itemVO : toI9Datas) {
      if (this.mapAdjustoodsTOI9.contains(itemVO.getPk_settlebill_b())) {
        itemVO.setNmoney(itemVO.getNmoney());
      }
    }
  }

  /**
   * mengjian 采购不做处理，进口结算时需处理进口调整货物行
   * 传损益时 调整货物金额肯定是为空或0；不为零的部分传调整单
   * 
   * @param toIGDatas
   */
  @Override
  protected void processToIGSettleItem(List<SettleBillItemVO> toIGDatas) {
    // 采购不做处理，进口结算时需处理进口调整货物行
    for (SettleBillItemVO itemVO : toIGDatas) {
      UFDouble nmoney =
          itemVO.getNmoney().sub(
              MathTool.isZero(itemVO.getNadjustmny()) ? UFDouble.ZERO_DBL
                  : itemVO.getNadjustmny());
      itemVO.setNmoney(nmoney);
    }
  }

}
