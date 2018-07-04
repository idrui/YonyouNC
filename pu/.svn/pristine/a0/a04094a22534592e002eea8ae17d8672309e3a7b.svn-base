package nc.bs.pu.m27.feesettle.util;

import java.util.ArrayList;
import java.util.List;

public class FeeSettleQueryPara {
  // 需要查询的费用发票对应物料ID
  private List<String> feemrlidList;

  // 入库单行主键
  private List<String> stockbidList;

  public FeeSettleQueryPara() {
    this.feemrlidList = new ArrayList<String>();
    this.stockbidList = new ArrayList<String>();
  }

  public void addFeemrlid(String feemrlid) {
    if (this.feemrlidList.contains(feemrlid)) {
      return;
    }
    this.feemrlidList.add(feemrlid);
  }

  public void addStockbid(String stockbid) {
    if (this.stockbidList.contains(stockbid)) {
      return;
    }
    this.stockbidList.add(stockbid);
  }

  public List<String> getFeemrlidList() {
    return this.feemrlidList;
  }

  public List<String> getStockbidList() {
    return this.stockbidList;
  }
}
