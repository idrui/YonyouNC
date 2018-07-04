/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-24 上午10:37:17
 */
package nc.itf.pu.reference.to;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.pubitf.to.settlerule.ic.IMatchSettleruleServiceForIc;
import nc.pubitf.to.settlerule.ic.MatchSettleRuleResult;
import nc.pubitf.to.settlerule.ic.MatchSettleRuleVOFor50;
import nc.pubitf.to.settlerule.pu.IMatchSettleruleServiceForPu;
import nc.pubitf.to.settlerule.pu.MatchSettleRuleVOForPu;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.log.Log;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>内部交易结算关系提供給采购的服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-24 上午10:37:17
 */
public class SettleRuleServices {

  /**
   * 方法功能描述：根据参数获得成本域
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_financeorg 调出财务组织
   * @param pk_stockorg 调入库存组织
   * @param pk_group 集团
   * @param vordertrantype 订单类型
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-10 下午04:47:16
   */
  public static String getCostRegion(String pk_financeorg, String pk_stockorg,
      String pk_group, String vordertrantype) {
    if (!SysInitGroupQuery.isTOEnabled()) {
      return null;
    }
    MatchSettleRuleVOForPu mathvo = new MatchSettleRuleVOForPu();
    List<MatchSettleRuleVOForPu> matchParaList =
        new ArrayList<MatchSettleRuleVOForPu>();
    mathvo.setCinstockorgid(pk_stockorg);
    mathvo.setCoutfinanceorgid(pk_financeorg);
    mathvo.setCinventoryid(null);
    mathvo.setPk_group(pk_group);
    mathvo.setCtranstype(vordertrantype);
    matchParaList.add(mathvo);

    Map<MatchSettleRuleVOForPu, MatchSettleRuleResult> map =
        SettleRuleServices.getMathSettleRule(matchParaList);
    if (null == map || null == map.get(mathvo)) {
      return null;
    }

    return map.get(mathvo).getCostRegion();
  }

  /**
   * 采购入库单匹配内部交易结算规则
   * 
   * @param matchParaList
   * @return
   */
  public static Map<MatchSettleRuleVOForPu, MatchSettleRuleResult> getMathSettleRule(
      List<MatchSettleRuleVOForPu> matchParaList) {
    IMatchSettleruleServiceForPu srv =
        NCLocator.getInstance().lookup(IMatchSettleruleServiceForPu.class);
    try {
      return srv.matchSettleruleForPu(matchParaList);
    }
    catch (BusinessException e) {
      // 日志异常
      Log.debug(e.getMessage());
      return null;
    }
  }

  /**
   * 消耗汇总匹配内部交易结算规则
   * 
   * @param matchParaList
   * @return
   */
  public static Map<MatchSettleRuleVOFor50, MatchSettleRuleResult> getVMIMathSettleRule(
      List<MatchSettleRuleVOFor50> matchParaList) {
    IMatchSettleruleServiceForIc srv =
        NCLocator.getInstance().lookup(IMatchSettleruleServiceForIc.class);
    try {
      return srv.matchSettleruleFor50(matchParaList);
    }
    catch (BusinessException e) {
      // 日志异常
      Log.debug(e.getMessage());
      return null;
    }
  }

  /**
   * 方法功能描述：匹配以结算财务组织作为调出组织，库存组织所属财务作为调入组织的调拨关系<br>
   * 只查询两个财务组织不同的调拨关系
   * <p>
   * <b>参数说明</b>
   * 
   * @param fivos 库存财务VO数组
   * @return MAP(库存财务表体ID，匹配到的调拨规则结果VO--此VO有可能为空)
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-24 上午11:46:10
   */
  public static Map<String, MatchSettleRuleResult> matchSettleRule(
      PurchaseinFIVO[] fivos) {
    Map<String, MatchSettleRuleResult> retMap =
        new HashMap<String, MatchSettleRuleResult>();
    if (ArrayUtils.isEmpty(fivos)) {
      return retMap;
    }
    // 过滤出集采分收集结要匹配调拨关系的入库单
    List<PurchaseinFIItemVO> cpItems =
        SettleRuleServices.filterCenterPurchase(fivos);
    if (CollectionUtils.isEmpty(cpItems)) {
      return retMap;
    }
    List<MatchSettleRuleVOForPu> matchParaList =
        new ArrayList<MatchSettleRuleVOForPu>();
    for (int i = 0; i < cpItems.size(); i++) {
      PurchaseinFIItemVO item = cpItems.get(i);
      MatchSettleRuleVOForPu mathvo = new MatchSettleRuleVOForPu();
      mathvo.setCinstockorgid(item.getPk_org());
      mathvo.setCoutfinanceorgid(item.getPk_financeorg());
      mathvo.setCinventoryid(item.getPk_srcmaterial());
      mathvo.setPk_group(item.getPk_group());
      mathvo.setCtranstype(item.getVordertrantypecode());
      matchParaList.add(mathvo);
    }
    Map<MatchSettleRuleVOForPu, MatchSettleRuleResult> mathMap =
        SettleRuleServices.getMathSettleRule(matchParaList);
    SettleRuleServices.reGenMathResult(retMap, cpItems, mathMap, matchParaList);
    return retMap;
  }

  private static List<PurchaseinFIItemVO> filterCenterPurchase(
      PurchaseinFIVO[] vos) {
    String[] storeorgs =
        (String[]) AggVOUtil.getDistinctHeadFieldArray(vos,
            PurchaseinFIHeaderVO.PK_ORG, String.class);
    Map<String, String> storeFiMap = null;
    storeFiMap = StockOrgPubService.queryFinanceOrgIDByStockOrgID(storeorgs);
    if (null == storeFiMap || MapUtils.isEmpty(storeFiMap)) {
      return null;
    }
    List<PurchaseinFIItemVO> filterItems = new ArrayList<PurchaseinFIItemVO>();
    for (PurchaseinFIVO vo : vos) {
      PurchaseinFIHeaderVO head = vo.getParentVO();
      String instoreorg = head.getPk_org();
      PurchaseinFIItemVO[] items = vo.getChildrenVO();
      if (ArrayUtils.isEmpty(items) && !storeFiMap.containsKey(instoreorg)) {
        continue;
      }
      for (PurchaseinFIItemVO item : items) {
        String outfiorg = item.getPk_financeorg();
        String infiorg = storeFiMap.get(instoreorg);
        if (!outfiorg.equals(infiorg)) {
          filterItems.add(item);
        }
      }
    }
    return filterItems;
  }

  private static void reGenMathResult(
      Map<String, MatchSettleRuleResult> retMap,
      List<PurchaseinFIItemVO> cpItems,
      Map<MatchSettleRuleVOForPu, MatchSettleRuleResult> mathMap,
      List<MatchSettleRuleVOForPu> matchParaList) {
    for (int i = 0; i < cpItems.size(); i++) {
      String pk_stockps_b = cpItems.get(i).getPk_stockps_b();
      MatchSettleRuleResult mathReslt = mathMap.get(matchParaList.get(i));
      retMap.put(pk_stockps_b, mathReslt);
    }
  }

}
