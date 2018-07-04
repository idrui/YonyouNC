package nc.bs.pu.m4202.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.pu.m27.rule.FillRowAffectCostFlagRule;
import nc.itf.pu.reference.to.SettleRuleServices;
import nc.pubitf.to.settlerule.ic.MatchSettleRuleResult;
import nc.pubitf.to.settlerule.ic.MatchSettleRuleVOFor50;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.m4202.entity.VmiFIVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * 消耗汇总财务入副本设置是否影响成本标志
 * 
 * @since 6.0
 * @version 2011-2-23 上午08:49:21
 * @author yinfy
 */
@SuppressWarnings("unchecked")
public class VMIFillRowAffectCostFlagRule extends
    FillRowAffectCostFlagRule<VmiFIVO> {

  @Override
  protected Map<String, UFBoolean> getEffectByTrantype(String[] types) {
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    for (String type : types) {
      map.put(type, UFBoolean.TRUE);// 消耗汇总交易类型一定影响成本（V60需求）
    }
    return map;
  }

  @Override
  protected List<CircularlyAccessibleValueObject> getFillItems(List<VmiFIVO> vos) {
    List<CircularlyAccessibleValueObject> filterItems =
        new ArrayList<CircularlyAccessibleValueObject>();
    for (VmiFIVO vo : vos) {
      filterItems.add(vo.getParentVO());
    }
    return filterItems;
  }

  @Override
  protected String getPk_Stockorg(CircularlyAccessibleValueObject item) {
    return (String) item.getAttributeValue(VmiFIHeaderVO.PK_STOREORG);
  }

  @Override
  protected <T extends SuperVO> Map<String, T> getQueryMatchSettleRuleVO(
      List<CircularlyAccessibleValueObject> cpItems) {
    Map<String, T> retMap = new HashMap<String, T>();
    for (int i = 0; i < cpItems.size(); i++) {
      MatchSettleRuleVOFor50 param = new MatchSettleRuleVOFor50();
      param.setPk_group((String) cpItems.get(i).getAttributeValue(
          VmiFIHeaderVO.PK_GROUP));
      param.setCinstockorgid((String) cpItems.get(i).getAttributeValue(
          VmiFIHeaderVO.PK_STOREORG));
      param.setCoutfinanceorgid((String) cpItems.get(i).getAttributeValue(
          VmiFIHeaderVO.PK_FINANCEORG));
      param.setCinventoryid((String) cpItems.get(i).getAttributeValue(
          VmiFIHeaderVO.PK_SRCMATERIAL));
      retMap.put(
          (String) cpItems.get(i).getAttributeValue(
              PurchaseinFIItemVO.PK_STOCKPS_B), (T) param);
    }
    return retMap;
  }

  @Override
  protected boolean isConsiderStoredoc() {
    return false;
  }

  @Override
  protected <T extends SuperVO> Map<T, MatchSettleRuleResult> queryMatchResult(
      Map<String, T> parapk_b) {
    List<T> matchVos = new ArrayList<T>(parapk_b.values());
    Map<T, MatchSettleRuleResult> mathMap =
        (Map<T, MatchSettleRuleResult>) SettleRuleServices
            .getVMIMathSettleRule((List<MatchSettleRuleVOFor50>) matchVos);
    return mathMap;
  }
}
