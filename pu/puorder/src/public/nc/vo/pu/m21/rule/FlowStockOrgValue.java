package nc.vo.pu.m21.rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.TrafficOrgPubService;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.util.remotecallcombination.RemoteCallCombinatorEx;
import nc.vo.util.remotecallcombination.Token;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-3-29 下午07:44:44
 * @author wuxla
 */

public class FlowStockOrgValue implements IPURemoteCallCombinator {

  private IKeyValue keyValue;

  private Token token;

  public FlowStockOrgValue(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  @Override
  public void prepare() {
    //
  }

  @Override
  public void process() {
    //
  }

  public void registerCombineRemoteCall(int[] rows) {
    if (this.token != null) {
      RemoteCallCombinatorEx.getInstance().update(this.token);
    }

    String[] stockorgIDS = this.getStockOrgIDs(rows);
    if (ArrayUtils.isEmpty(stockorgIDS)) {
      return;
    }
    this.token =
        TrafficOrgPubService
            .registerSCM_getTrafficOrgIDSByStockOrgIDS(stockorgIDS);
  }

  /**
   * 设置物流组织
   * 
   * @param rows
   */
  public void setFlowStockOrg(int[] rows) {
    Map<Integer, String> arrvorgMap = new HashMap<Integer, String>();
    for (int i = 0; i < rows.length; ++i) {
      String pk_arrvstoorg =
          (String) this.keyValue.getBodyValue(rows[i],
              OrderItemVO.PK_ARRVSTOORG);
      if (pk_arrvstoorg != null) {
        arrvorgMap.put(Integer.valueOf(rows[i]), pk_arrvstoorg);
      }
      else {
        this.keyValue.setBodyValue(rows[i], OrderItemVO.PK_FLOWSTOCKORG, null);
        this.keyValue
            .setBodyValue(rows[i], OrderItemVO.PK_FLOWSTOCKORG_V, null);
      }
    }

    if (0 == arrvorgMap.size()) {
      return;
    }

    this.setFlowStockOrg(arrvorgMap);
  }

  public void setFlowStockOrgByWar(int row, String pk_org) {
    int[] rows = new int[] {
      row
    };
    if (null == pk_org) {
      this.setFlowStockOrg(rows);
      return;
    }
    Map<Integer, String> arrvorgMap = new HashMap<Integer, String>();
    arrvorgMap.put(Integer.valueOf(row), pk_org);
    this.setFlowStockOrg(arrvorgMap);
  }

  private Map<String, String> getOrgMap(Map<String, String> trafficMap) {
    Map<String, String> map = new HashMap<String, String>();
    Set<String> set = new HashSet<String>();
    for (Entry<String, String> entry : trafficMap.entrySet()) {
      String value = entry.getValue();
      if (value != null) {
        set.add(value);
      }
    }

    if (set.size() == 0) {
      return map;
    }

    String[] orgOids = set.toArray(new String[set.size()]);
    return OrgUnitPubService.getNewVIDSByOrgIDS(orgOids);
  }

  private String[] getStockOrgIDs(int[] rows) {
    Map<Integer, String> arrvorgMap = new HashMap<Integer, String>();
    for (int i = 0; i < rows.length; ++i) {
      String pk_arrvstoorg =
          (String) this.keyValue.getBodyValue(rows[i],
              OrderItemVO.PK_ARRVSTOORG);
      if (pk_arrvstoorg != null) {
        arrvorgMap.put(Integer.valueOf(rows[i]), pk_arrvstoorg);
      }
      else {
        this.keyValue.setBodyValue(rows[i], OrderItemVO.PK_FLOWSTOCKORG, null);
        this.keyValue
            .setBodyValue(rows[i], OrderItemVO.PK_FLOWSTOCKORG_V, null);
      }
    }

    if (0 == arrvorgMap.size()) {
      return null;
    }

    Collection<String> arrvorg = arrvorgMap.values();
    String[] arrvorgs = arrvorg.toArray(new String[arrvorg.size()]);
    return arrvorgs;
  }

  private void setFlowStockOrg(Map<Integer, String> arrvorgMap) {
    Collection<String> arrvorg = arrvorgMap.values();
    String[] arrvorgs = arrvorg.toArray(new String[arrvorg.size()]);
    Map<String, String> trafficMap = null;
    trafficMap =
        TrafficOrgPubService.getTrafficOrgIDSByStockOrgIDSWithReceivedefault(
            arrvorgs, this.token);

    if (trafficMap == null) {
      return;
    }

    Map<String, String> orgVidMap = this.getOrgMap(trafficMap);

    for (Entry<Integer, String> entry : arrvorgMap.entrySet()) {
      String pk_arrvstoorg = entry.getValue();
      String pk_flowstockorg = trafficMap.get(pk_arrvstoorg);
      if (null == pk_flowstockorg) {
        continue;
      }
      int row = entry.getKey().intValue();
      this.keyValue.setBodyValue(row, OrderItemVO.PK_FLOWSTOCKORG,
          pk_flowstockorg);
      this.keyValue.setBodyValue(row, OrderItemVO.PK_FLOWSTOCKORG_V,
          orgVidMap.get(pk_flowstockorg));
    }
  }
}
