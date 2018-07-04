package nc.bs.pu.m27.feesettle.util;

import java.util.ArrayList;
import java.util.List;

public class FeeSettleQueryPara {
  // ��Ҫ��ѯ�ķ��÷�Ʊ��Ӧ����ID
  private List<String> feemrlidList;

  // ��ⵥ������
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
