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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ý�����ʹ�õĺ�̨���ݻ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-8-30 ����10:56:29
 */
public class FeeSettleDataContext {

  // <��ⵥ����������̯��ϸ> :ȡ�����㡢��IA��ȡ����IAʱ�����ڴ洢����ѯ�����ķ��÷�̯��ϸ
  private MapList<String, SettleFeeAllotDetailVO> beenSavedAllotDetails;

  // �����ݹ���̯��ϸ
  private FeeEstDistVO[] estBbbItems;

  // �����ݹ���ϸ
  private FeeEstVO[] estBbItems;

  // <��ⵥ����������̯��ϸ>:���ý���ʱ�����ڴ洢��̯��Ľ��
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
