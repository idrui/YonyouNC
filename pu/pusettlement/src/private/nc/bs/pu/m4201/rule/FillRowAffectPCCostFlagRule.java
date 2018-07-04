package nc.bs.pu.m4201.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ic.M45PUServices;
import nc.itf.pu.reference.pcto.PCTOSettleRuleServices;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPCPubService;
import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.pcto.settlerule.para.MatchSettleRuleParaVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 设置影响利润中心成本标志
 * 
 * @since 6.5
 * @version 2014-5-14 下午02:24:10
 * @author mengjian
 */
public class FillRowAffectPCCostFlagRule implements IRule<PurchaseinFIVO> {

  @Override
  public void process(PurchaseinFIVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // 获不跨利润中心采购的入库单VO
    List<PurchaseinFIVO> normalVos = this.getSpecialPUVos(vos, UFBoolean.FALSE);
    if (!CollectionUtils.isEmpty(normalVos)) {
      this.setAffectPCCostFlag(vos, UFBoolean.FALSE);
    }
    // 获得跨利润中心采购的入库单VO
    List<PurchaseinFIVO> specialVos = this.getSpecialPUVos(vos, UFBoolean.TRUE);
    if (!CollectionUtils.isEmpty(specialVos)) {
      if (!SysInitGroupQuery.isPCTOEnabled()) {
        /*ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0250")
                                                                     * @res
                                                                     * "利润中心内部交易未启用，无法处理此种业务！"
                                                                     );*/
      	this.setAffectPCCostFlag(vos, UFBoolean.FALSE);
      }
      else{
      	this.setAffectPCCostFlag(vos, UFBoolean.TRUE);
      }    
    }
  }

  /**
   * 根据仓库的是否成本计算来设置影响成本标志（不需要此规则的单据，返回零长的MAP)
   * 
   * @param pk_stordocs
   * @return
   */
  private Map<String, UFBoolean> getEffectByStordoc(String[] pk_stordocs) {
    Map<String, UFBoolean> result = new HashMap<String, UFBoolean>();
    // 查询仓库是否进行存货成本计算标志
    StordocVO[] stordocVOs =
        StordocPubService.queryStordocByPks(pk_stordocs, new String[] {
          StordocVO.PK_STORDOC, StordocVO.ISCALCULATEDINVCOST
        });
    if (!ArrayUtils.isEmpty(stordocVOs)) {
      for (StordocVO vo : stordocVOs) {
        result.put(vo.getPk_stordoc(), vo.getIscalculatedinvcost());
      }
    }
    return result;
  }

  private Map<String, UFBoolean> getEffectByTrantype(PurchaseinFIVO[] vos) {
    // 查询交易类型是否影响成本
    String[] types =
        (String[]) AggVOUtil.getDistinctHeadFieldArray(vos,
            PurchaseinFIHeaderVO.CTRANTYPEID, String.class);
    return M45PUServices.getStockTrantypeAffectCostFlag(types);
  }

  /**
   * 得到所有的表体信息
   * 
   * @param vos
   * @return
   */
  private List<PurchaseinFIItemVO> getFillItems(PurchaseinFIVO[] vos) {
    List<PurchaseinFIItemVO> filterItems = new ArrayList<PurchaseinFIItemVO>();
    for (PurchaseinFIVO vo : vos) {
      PurchaseinFIItemVO[] items = vo.getChildrenVO();
      for (PurchaseinFIItemVO item : items) {
        filterItems.add(item);
      }
    }
    return filterItems;
  }

  private Map<String, UFBoolean> getMaterialMap(PurchaseinFIVO[] vos) {
    String[] materials =
        (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
            PurchaseinFIItemVO.PK_SRCMATERIAL, String.class);
    // String[] pk_apliabcenters =
    // (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
    // PurchaseinFIItemVO.PK_ARRLIABCENTER, String.class);
    PurchaseinFIItemVO[] itemvos =
        (PurchaseinFIItemVO[]) AggVOUtil.getCombinItemVOs(vos);
    List<String> pk_apliabcenters = new ArrayList<String>();
    for (PurchaseinFIItemVO itemvo : itemvos) {
      if (null != itemvo.getPk_apliabcenter()) {
        pk_apliabcenters.add(itemvo.getPk_apliabcenter());
      }
      else {
        pk_apliabcenters.add(itemvo.getPk_arrliabcenter());
      }
    }
    // 物料【】+利润中心【】获得“是否传存货”属性
    MaterialPCPubService pcservice = new MaterialPCPubService();
    return pcservice.queryTransfermarByMarOIDsAndPfcPkOrgs(
        pk_apliabcenters.toArray(new String[0]), materials);
  }

  /**
   * 创建查询利润中心内部交易结算关系的参数VO,并且对表体分类
   * 
   * @param cpItems
   * @return
   */
  private Map<MatchSettleRuleParaVO, List<String>> getQueryMatchSettleRuleVO(
      List<PurchaseinFIItemVO> cpItems) {
    Map<String, MatchSettleRuleParaVO> retMap =
        new HashMap<String, MatchSettleRuleParaVO>();
    Map<MatchSettleRuleParaVO, List<String>> paraMap = new HashMap<>();
    Set<String> codes = new HashSet<String>();
    for (PurchaseinFIItemVO vo : cpItems) {
      if (!StringUtils.isBlank((String) vo
          .getAttributeValue(PurchaseinFIItemVO.VORDERTRANTYPECODE))) {
        codes.add((String) vo
            .getAttributeValue(PurchaseinFIItemVO.VORDERTRANTYPECODE));
      }
    }
    Map<String, String> code_idMap = null;
    if (codes.size() > 0) {
      code_idMap =
          PfServiceScmUtil.getTrantypeidByCode(codes.toArray(new String[codes
              .size()]));
    }
    for (PurchaseinFIItemVO item : cpItems) {
      String typecode = null != code_idMap ? code_idMap.get(item
          .getVordertrantypecode()) : null;
      StringBuilder builder = new StringBuilder();
      builder.append(item.getPk_group())
      .append(item.getPk_srcmaterial()).append(item.getPk_arrliabcenter())
      .append(item.getPk_apliabcenter()).append(typecode);
      MatchSettleRuleParaVO mathvo = retMap.get(builder.toString());
      if(mathvo == null){
        mathvo = new MatchSettleRuleParaVO(Integer.valueOf(5), item.getPk_group(),
              item.getPk_srcmaterial());
      mathvo.setCinprofitcenterid(item.getPk_arrliabcenter());// 调入利润中心
      mathvo.setCoutprofitcenterid(item.getPk_apliabcenter());// 调出利润中心
      mathvo.setCtranstypeid(typecode);
      retMap.put(builder.toString(), mathvo);
      }
      List<String> items = paraMap.get(mathvo);
      if(items == null){
        items = new ArrayList<>();
        paraMap.put(mathvo, items);
      }
      items.add(item.getPk_stockps_b());
    }
    return paraMap;
  }

  /**
   * 根据是否跨利润中心区分入库单
   * 
   * @param vos
   * @param flag
   * @return
   */
  private List<PurchaseinFIVO> getSpecialPUVos(PurchaseinFIVO[] vos,
      UFBoolean flag) {
    List<PurchaseinFIVO> resvos = new ArrayList<PurchaseinFIVO>();
    for (PurchaseinFIVO vo : vos) {
      PurchaseinFIHeaderVO headerVO = vo.getParentVO();
      PurchaseinFIItemVO[] itemvos = vo.getChildrenVO();
      List<PurchaseinFIItemVO> itemvos1 = new ArrayList<PurchaseinFIItemVO>();
      List<PurchaseinFIItemVO> itemvos2 = new ArrayList<PurchaseinFIItemVO>();
      for (PurchaseinFIItemVO itemvo : itemvos) {
        String pk_apliabcenter = itemvo.getPk_apliabcenter();
        String pk_arrliabcenter = itemvo.getPk_arrliabcenter();
        if (StringUtils.isEmpty(pk_apliabcenter)
            || StringUtils.contains(pk_apliabcenter, pk_arrliabcenter)) {
          itemvos1.add(itemvo);
        }
        else {
          itemvos2.add(itemvo);
        }
      }
      PurchaseinFIVO tempvo = new PurchaseinFIVO();
      tempvo.setParentVO(headerVO);
      if (UFBoolean.FALSE.equals(flag)) {
        if (CollectionUtils.isEmpty(itemvos1)) {
          tempvo = null;
        }
        else {
          tempvo.setChildrenVO(itemvos1.toArray(new PurchaseinFIItemVO[0]));
        }
      }
      else {
        if (CollectionUtils.isEmpty(itemvos2)) {
          tempvo = null;
        }
        else {
          tempvo.setChildrenVO(itemvos2.toArray(new PurchaseinFIItemVO[0]));
        }
      }
      if (null != tempvo) {
        resvos.add(tempvo);
      }
    }
    return resvos;
  }

  /**
   * 查询利润中心内部交易结算关系信息：是否影响成本
   * 
   * @param parapk_b
   * @return
   */
  private Map<String, UFBoolean> queryMatchResult(
      Map<MatchSettleRuleParaVO, List<String>> parapk_b) {
    MatchSettleRuleParaVO[] matchVos =
        parapk_b.keySet().toArray(new MatchSettleRuleParaVO[0]);
    Map<MatchSettleRuleParaVO, UFBoolean> mathMap =
        PCTOSettleRuleServices.getMathPCTOSettleMap(matchVos);
    Map<String, UFBoolean> sttlRuleMap = new HashMap<>();
    for(Map.Entry<MatchSettleRuleParaVO, UFBoolean> entry : mathMap.entrySet()){
      for(String pkb : parapk_b.get(entry.getKey())){
        sttlRuleMap.put(pkb, entry.getValue());
      }
    }
    return sttlRuleMap;
  }

  private void setAffectPCCostFlag(PurchaseinFIVO[] vos, UFBoolean flag) {
    Map<String, UFBoolean> stordocMap = new HashMap<String, UFBoolean>();
    String[] pk_stordocs =
        (String[]) AggVOUtil.getDistinctHeadFieldArray(vos,
            PurchaseinFIHeaderVO.PK_STORDOC, String.class);
    stordocMap = this.getEffectByStordoc(pk_stordocs);
    Map<String, UFBoolean> ctrantypeidMap = this.getEffectByTrantype(vos);

    Map<MatchSettleRuleParaVO, List<String>> parapkMap = new HashMap<>();
    Map<String, UFBoolean> sttlRuleMap = new HashMap<>();
    // 跨利润中心时，利润中心内部结算规则的“调出方是否传存货核算”属性为是
    if (UFBoolean.TRUE.equals(flag)) {
      List<PurchaseinFIItemVO> cpitems = this.getFillItems(vos);
      parapkMap = this.getQueryMatchSettleRuleVO(cpitems);
      sttlRuleMap = this.queryMatchResult(parapkMap);
    }

    for (PurchaseinFIVO vo : vos) {
      String pk_stordoc =
          (String) vo.getParentVO().getAttributeValue(
              PurchaseinFIHeaderVO.PK_STORDOC);
      UFBoolean isStordoc = stordocMap.get(pk_stordoc);
      // 仓库勾选影响成本；
      if (UFBoolean.FALSE.equals(isStordoc)) {
        continue;
      }
      if (UFBoolean.FALSE.equals(flag)) {
        // 不跨利润中心时，采购入库单交易类型影响成本勾选
        String ctrantypeid =
            (String) vo.getParentVO().getAttributeValue(
                PurchaseinFIHeaderVO.CTRANTYPEID);
        UFBoolean isTrantype = ctrantypeidMap.get(ctrantypeid);
        // 交易类型是否勾选影响成本；
        if (UFBoolean.FALSE.equals(isTrantype)) {
          continue;
        }
      }
      PurchaseinFIItemVO[] bodyvos = vo.getChildrenVO();
      Map<String, UFBoolean> materialMap = this.getMaterialMap(vos);
      if (null == materialMap) {
        continue;
      }
      for (PurchaseinFIItemVO bodyvo : bodyvos) {

        if (UFBoolean.TRUE.equals(flag)) {
          // 跨利润中心时，利润中心内部结算规则的“调出方是否传存货核算”属性为是
          String pk_stockps_b = bodyvo.getPk_stockps_b();
          bodyvo.setBaffectpccost(sttlRuleMap.get(pk_stockps_b));
          if (UFBoolean.FALSE.equals(bodyvo.getBaffectpccost())) {
            continue;
          }
        }
        String pk_apliabcenter = bodyvo.getPk_apliabcenter();
        String pk_srcmaterial = bodyvo.getPk_srcmaterial();
        // 物料档案利润中心页签定义“是否传存货”属性为是；
        if (StringUtils.isNotBlank(pk_apliabcenter)) {
          // 结算利润中心非空；
          UFBoolean isPCCost =
              materialMap.get(pk_apliabcenter + pk_srcmaterial);
          bodyvo.setBaffectpccost(isPCCost);
        }
        else {
          // 结算利润中心为空取收货利润中心
          UFBoolean isPCCost =
              materialMap.get(bodyvo.getPk_arrliabcenter() + pk_srcmaterial);
          bodyvo.setBaffectpccost(isPCCost);
        }
      }

    }
  }
}
