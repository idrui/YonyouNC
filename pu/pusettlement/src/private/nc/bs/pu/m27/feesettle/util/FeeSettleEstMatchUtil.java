package nc.bs.pu.m27.feesettle.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>匹配费用项的费用结算分摊明细与费用暂估分摊明细的对应关系
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-27 下午02:17:04
 */
public class FeeSettleEstMatchUtil {

  // 所有的暂估明细<入库单行主键，费用暂估VO列表>
  private Map<String, ArrayList<FeeEstVO>> allEstDetails;

  // 所有的暂估分摊明细<费用暂估主键，费用分摊明细列表>
  private Map<String, ArrayList<FeeEstDistVO>> allEstFdDetails;

  // 所有的费用结算明细
  private MapList<String, SettleFeeAllotDetailVO> allSettleDetails;

  private List<FeeSettleEstMapping> feeSettleEstMappings = null;

  private List<SettleFeeAllotDetailVO> noneEstBbvos = null;

  public FeeSettleEstMatchUtil(FeeSettleDataContext datactx) {
    // 费用暂估明细、 费用暂估分摊明细
    FeeEstVO[] bbItems = datactx.getEstBbItems();
    FeeEstDistVO[] bbbItems = datactx.getEstBbbItems();
    if (bbbItems != null && bbItems != null) {
      String idkey = FeeEstVO.PK_STOCKPS_B;
      this.allEstDetails = CirVOUtil.getFieldVOList(bbItems, idkey);
      String bidkey = FeeEstDistVO.PK_STOCKPS_FEE;
      this.allEstFdDetails = CirVOUtil.getFieldVOList(bbbItems, bidkey);
    }
    else {
      this.allEstDetails = new HashMap<String, ArrayList<FeeEstVO>>();
      this.allEstFdDetails = new HashMap<String, ArrayList<FeeEstDistVO>>();
    }
    if (datactx.getNoneSavedAllotDetails() != null) {
      this.allSettleDetails = datactx.getNoneSavedAllotDetails();
    }
    else if (datactx.getBeenSavedAllotDetails() != null) {
      this.allSettleDetails = datactx.getBeenSavedAllotDetails();
    }
    else {
      String msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0066")/* @res "进行费用结算分摊明细与暂估费用分摊明细匹配时，出现数据错误！" */;
      ExceptionUtils.wrappBusinessException(msg);
    }
  }

  public List<FeeSettleEstMapping> getFeeSettleEstMappings() {
    return this.feeSettleEstMappings;
  }

  public List<SettleFeeAllotDetailVO> getNoneEstBbvos() {
    return this.noneEstBbvos;
  }

  public void match(SettleBillItemVO item) {
    this.noneEstBbvos = new ArrayList<SettleFeeAllotDetailVO>();
    this.feeSettleEstMappings = new ArrayList<FeeSettleEstMapping>();

    String stockbid = item.getPk_stock_b();
    List<SettleFeeAllotDetailVO> allots = this.allSettleDetails.get(stockbid);
    if (allots == null || allots.size() <= 0) {
      return;
    }
    if (!this.allEstDetails.containsKey(stockbid)) {
      for (SettleFeeAllotDetailVO allot : allots) {
        this.noneEstBbvos.add(allot);
      }
      return;
    }

    // 入库单表体行存在多个费用项暂估
    ArrayList<FeeEstVO> estList = this.allEstDetails.get(stockbid);
    if (estList == null || estList.size() == 0) {
      return;
    }

    Set<String> keyset = new HashSet<String>();
    Set<String> beenEstAllotid = new HashSet<String>();
    for (FeeEstVO estvo : estList) {
      // 费用项暂估的分摊明细
      String stockbbid = estvo.getPk_stockps_fee();
      ArrayList<FeeEstDistVO> estFdVOs = this.allEstFdDetails.get(stockbbid);
      for (FeeEstDistVO fdvo : estFdVOs) {
        for (SettleFeeAllotDetailVO allot : allots) {
          String allotMrl = allot.getPk_srcmaterial();
          String allotid = allot.getPk_allotbillid();
          String allotbid = allot.getPk_allotbillbid();
          String key = allotMrl + "##" + allotid + "##" + allotbid;
          if (keyset.contains(key)) {
            continue;
          }
          boolean bmrlEq = estvo.getPk_srcfeematerial().equals(allotMrl);
          boolean ballotidEq = fdvo.getPk_distbybill().equals(allotid);
          boolean ballotbidEq = fdvo.getPk_distbybill_b().equals(allotbid);
          // 比较费用结算分摊明细与暂估分摊明细是否一样
          if (bmrlEq && ballotidEq && ballotbidEq) {
            FeeSettleEstMapping map =
                new FeeSettleEstMapping(estvo, fdvo, allot);
            this.feeSettleEstMappings.add(map);
            keyset.add(key);// 确保一个费用暂估分摊明细只对应一个结算分摊明细
            beenEstAllotid.add(allot.getPk_settle_feeallot());
          }
        }
      }
    }
    for (SettleFeeAllotDetailVO allot : allots) {
      if (beenEstAllotid.contains(allot.getPk_settle_feeallot())) {
        continue;
      }
      this.noneEstBbvos.add(allot);
    }
  }

}
