package nc.bs.pu.est.rule.pricequery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.env.BSContext;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.org.CostRegionPubService;
import nc.vo.bd.material.IMaterialEnumConst;
import nc.vo.pu.est.rule.IEstPriceQueryInfoProvide;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleObjectFactory;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 暂估取存货核销处的计划价<br>
 * 按照成本域+主账簿来确定是否按照计划价来暂估
 * 
 * @since 6.0
 * @version 2010-11-26 上午10:54:19
 * @author zhaoyha
 */
public class EstCostPlanPriceQryStrategy extends AbstractEstPriceQueryStrategy {

  private Map<String, String> getCostrgAccountMap(
      IEstPriceQueryInfoProvide[] pqinfo) {
    Set<String> costr = new HashSet<String>();
    for (IEstPriceQueryInfoProvide pqi : pqinfo) {
      String pk_costr = pqi.getCostregion();
      if (StringUtils.isBlank(pk_costr)) {
        continue;
      }
      costr.add(pk_costr);
    }
    return CostRegionPubService.queryAccountingBookIDByCostRegionID(costr
        .toArray(new String[costr.size()]));
  }

  private MapList<String, String> getCostrgMaterialMap(
      IEstPriceQueryInfoProvide[] pqinfo) {
    MapList<String, String> crMarMap = new MapList<String, String>();
    for (IEstPriceQueryInfoProvide pqi : pqinfo) {
      String pk_cr = pqi.getCostregion();
      if (StringUtils.isBlank(pk_cr)) {
        continue;
      }
      crMarMap.put(pk_cr, pqi.getPk_srcmaterial());
    }
    return crMarMap;
  }

  private Map<String, UFDouble> getPlanPrice(IEstPriceQueryInfoProvide[] pqinfo) {
    // 得到成本域的主账薄
    Map<String, String> crAccntMap = this.getCostrgAccountMap(pqinfo);
    // 得到成本域到物料的MAP
    MapList<String, String> crMarMap = this.getCostrgMaterialMap(pqinfo);
    if (MapUtils.isEmpty(crAccntMap) || crMarMap.size() == 0) {
      return null;
    }
    Map<String, UFDouble> retMap = new HashMap<String, UFDouble>();
    for (Map.Entry<String, String> entry : crAccntMap.entrySet()) {
      String pk_cr = entry.getKey();
      String pk_accnt = entry.getValue();
      List<String> midList = crMarMap.get(pk_cr);
      if (CollectionUtils.isEmpty(midList)) {
        continue;
      }
      // 得到使用计划价进行计价的物料
      String[] planPrcMars =
          this.getPlanPriceMaterial(
              midList.toArray(new String[midList.size()]), pk_cr, pk_accnt);
      if (ArrayUtils.isEmpty(planPrcMars)) {
        continue;
      }
      // 得到物料对应的计划价
      Map<String, UFDouble> marPlanPrcMap =
          this.queryMarPlanPrice(planPrcMars, pk_cr, pk_accnt);
      // 将计划价放到（成本域+物料－》计划价）的MAP中
      this.putRetMap(retMap, marPlanPrcMap, pk_cr);
    }
    return retMap;
  }

  private String[] getPlanPriceMaterial(String[] mids, String pk_cr,
      String pk_accnt) {
    Map<String, Integer> marModeMap =
        MaterialPubService.queryCostModeByPks(mids, pk_cr, pk_accnt);
    if (MapUtils.isEmpty(marModeMap)) {
      return null;
    }
    List<String> planPrcList = new ArrayList<String>();
    for (Map.Entry<String, Integer> entry : marModeMap.entrySet()) {
      String pk_srcmaterial = entry.getKey();
      Integer mode = entry.getValue();
      // 只有使用计划价计价方式的才处理
      if (Integer.valueOf(IMaterialEnumConst.MARCOSTMODE_PLANPRICE)
          .equals(mode)) {
        planPrcList.add(pk_srcmaterial);
      }
    }
    return planPrcList.toArray(new String[planPrcList.size()]);
  }

  private void putRetMap(Map<String, UFDouble> retMap,
      Map<String, UFDouble> marPlanPrcMap, String pk_cr) {
    for (Map.Entry<String, UFDouble> entry : marPlanPrcMap.entrySet()) {
      String key = pk_cr + entry.getKey();
      retMap.put(key, entry.getValue());
    }
  }

  private Map<String, UFDouble> queryMarPlanPrice(String[] mids, String pk_cr,
      String pk_accnt) {
    return MaterialPubService.queryCostPlanedPriceByPks(mids, pk_cr, pk_accnt);
  }

  @Override
  protected void procResltData(EstPriceQryResltData[] resltData,
      IEstPriceQueryInfoProvide[] pqinfo) {
    // 得到（成本域+物料－》计划价）的MAP
    Map<String, UFDouble> priceMap = this.getPlanPrice(pqinfo);
    if (priceMap == null) {
      return;
    }
    for (int i = 0; i < pqinfo.length; i++) {
      String pk_cr = pqinfo[i].getCostregion();
      String pk_srcmaterial = pqinfo[i].getPk_srcmaterial();
      UFDouble price = MathTool.nvl(priceMap.get(pk_cr + pk_srcmaterial));
      int digit =
          new ScaleObjectFactory(BSContext.getInstance().getGroupID())
              .getPriceScaleObject().getDigit(pqinfo[i].getorigcurr());
      price = price.add(UFDouble.ZERO_DBL, digit);// 取采购单价精度
      resltData[i].setPrice(price);
    }
  }

}
