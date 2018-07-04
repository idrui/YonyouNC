package nc.bs.pu.m27.feesettle.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>费用结算所使用的后台数据环境
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-30 上午10:56:29
 */
public class FeeSettleDataContext {

  // <入库单行主键，分摊明细> :取消结算、传IA、取消传IA时，用于存储所查询出来的费用分摊明细
  private MapList<String, SettleFeeAllotDetailVO> beenSavedAllotDetails;

  // 费用暂估分摊明细
  private FeeEstDistVO[] estBbbItems;

  // 费用暂估明细
  private FeeEstVO[] estBbItems;

  // <入库单行主键，分摊明细>:费用结算时，用于存储分摊后的结果
  private MapList<String, SettleFeeAllotDetailVO> noneSavedAllotDetails;

  private Map<String, StockSettleVO> stockSettleVOMap;

  public SettleFeeAllotDetailVO[] getBeenSavedAllotDetailArray() {
    if (this.beenSavedAllotDetails == null
        || this.beenSavedAllotDetails.size() == 0) {
      return null;
    }
    Set<String> keys = this.beenSavedAllotDetails.keySet();
    List<SettleFeeAllotDetailVO> data = new ArrayList<SettleFeeAllotDetailVO>();
    for (String key : keys) {
      data.addAll(this.beenSavedAllotDetails.get(key));
    }
    return data.toArray(new SettleFeeAllotDetailVO[0]);
  }

  public MapList<String, SettleFeeAllotDetailVO> getBeenSavedAllotDetails() {
    return this.beenSavedAllotDetails;
  }

  public FeeEstDistVO[] getEstBbbItems() {
    return this.estBbbItems;
  }

  public FeeEstVO[] getEstBbItems() {
    return this.estBbItems;
  }

  public SettleFeeAllotDetailVO[] getNoneSavedAllotDetailArray() {
    if (this.noneSavedAllotDetails == null
        || this.noneSavedAllotDetails.size() == 0) {
      return null;
    }
    Set<String> keys = this.noneSavedAllotDetails.keySet();
    List<SettleFeeAllotDetailVO> data = new ArrayList<SettleFeeAllotDetailVO>();
    for (String key : keys) {
      data.addAll(this.noneSavedAllotDetails.get(key));
    }
    return data.toArray(new SettleFeeAllotDetailVO[0]);
  }

  public MapList<String, SettleFeeAllotDetailVO> getNoneSavedAllotDetails() {
    return this.noneSavedAllotDetails;
  }

  public Map<String, StockSettleVO> getStockSettleVOMap() {
    return this.stockSettleVOMap;
  }

  public void setBeenSavedAllotDetails(
      MapList<String, SettleFeeAllotDetailVO> beenSavedAllotDetails) {
    this.beenSavedAllotDetails = beenSavedAllotDetails;
  }

  public void setEstBbbItems(FeeEstDistVO[] estBbbItems) {
    this.estBbbItems = estBbbItems;
  }

  public void setEstBbItems(FeeEstVO[] estBbItems) {
    this.estBbItems = estBbItems;
  }

  public void setNoneSavedAllotDetails(
      MapList<String, SettleFeeAllotDetailVO> noneSavedAllotDetails) {
    this.noneSavedAllotDetails = noneSavedAllotDetails;
  }

  public void setStockSettleVOMap(Map<String, StockSettleVO> stockSettleVOMap) {
    this.stockSettleVOMap = stockSettleVOMap;
  }

}
