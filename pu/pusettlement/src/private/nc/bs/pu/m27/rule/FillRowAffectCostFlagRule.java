package nc.bs.pu.m27.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ic.M45PUServices;
import nc.itf.pu.reference.to.SettleRuleServices;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.pubitf.to.settlerule.ic.MatchSettleRuleResult;
import nc.pubitf.to.settlerule.pu.MatchSettleRuleVOForPu;
import nc.vo.bd.material.IMaterialEnumConst;
import nc.vo.bd.material.fi.MaterialFiVO;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 财务入副本设置是否影响成本标志，包括(采购入、委外入、消耗汇总)
 * 
 * @since 6.0
 * @version 2011-1-28 下午02:38:09
 * @author yinfy
 */
@SuppressWarnings("unchecked")
public class FillRowAffectCostFlagRule<E extends AbstractBill> implements
    IRule<E> {

  @Override
  public void process(E[] bills) {
    // 获得普通采购的入库单VO
    List<E> vos = this.getNormalPUVos(bills, UFBoolean.TRUE);
    if (!CollectionUtils.isEmpty(vos)) {
      this.normPurFill(vos);
    }
    // 获得集中采购的入库单VO
    vos = this.getNormalPUVos(bills, UFBoolean.FALSE);
    if (!CollectionUtils.isEmpty(vos)) {
      this.centralPurFill(vos);
    }

  }

  /**
   * 设置集采类型的单据的影响成本标志
   * 
   * @param voList
   */
  private void centralPurFill(List<E> voList) {
    // 2011-07-27 经wangyf确认
    // 内部交易未启用时，分收集结的采购入库单无法区分是否传成本，
    // 这时采购入库单不可暂估、结算，提示“内部交易未启用，无法处理此种业务。”
    if (!SysInitGroupQuery.isTOEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0070")/*
                                                                   * @res
                                                                   * "内部交易未启用，无法处理此种业务！"
                                                                   */);
    }
    // 将所有表体VO加入List中
    List<CircularlyAccessibleValueObject> cpitems = this.getFillItems(voList);
    Map<String, SuperVO> parapkMap = this.getQueryMatchSettleRuleVO(cpitems);
    Map<SuperVO, MatchSettleRuleResult> sttlRuleMap =
        this.queryMatchResult(parapkMap);
    // 查询物料是否影响成本（根据V61需求，集采也要考虑物料价值属性）
    Map<String, Map<String, UFBoolean>> orgMarValMap =
        this.getEffectByMaterial(voList);
    for (CircularlyAccessibleValueObject item : cpitems) {
      // 根据V61需求，集采也要考虑物料价值属性
      Map<String, UFBoolean> marValMap =
          orgMarValMap.get(item.getAttributeValue(GoodsEstVO.PK_FINANCEORG));
      UFBoolean affectcost =
          marValMap == null ? UFBoolean.TRUE : marValMap.get(item
              .getAttributeValue(GoodsEstVO.PK_MATERIAL));
      String pk_stockps_b =
          (String) item.getAttributeValue(GoodsEstVO.PK_STOCKPS_B);
      MatchSettleRuleResult ruleRlt =
          sttlRuleMap.get(parapkMap.get(pk_stockps_b));
      if (null != ruleRlt && UFBoolean.TRUE.equals(affectcost)) {
        affectcost = ValueUtils.getUFBoolean(ruleRlt.getSendtoia());
      }
      item.setAttributeValue(GoodsEstVO.BAFFECTCOST, affectcost);
    }
  }

  /**
   * 根据物料价值属性来设置是否影响成本标志，继承时请将标志设置到VO上
   * 
   * @param vo
   * @param orgMarValMap
   */
  private void fillByMarValue(E vo,
      Map<String, Map<String, UFBoolean>> orgMarValMap, boolean preEffectFlag) {
    List<E> voList = new ArrayList<E>();
    voList.add(vo);
    List<CircularlyAccessibleValueObject> items = this.getFillItems(voList);
    for (CircularlyAccessibleValueObject item : items) {
      Map<String, UFBoolean> marValMap =
          orgMarValMap.get(item.getAttributeValue(GoodsEstVO.PK_FINANCEORG));
      UFBoolean marAffect =
          marValMap == null ? UFBoolean.TRUE : marValMap.get(item
              .getAttributeValue(GoodsEstVO.PK_MATERIAL));
      // 根据物料价值属性设置影响成本标志
      item.setAttributeValue(
          GoodsEstVO.BAFFECTCOST,
          null == marAffect ? UFBoolean.valueOf(preEffectFlag) : UFBoolean
              .valueOf(preEffectFlag && marAffect.booleanValue()));
    }
  }

  /**
   * 获取物料价值属性中，是否影响成本信息
   * 
   * @param vos
   * @return
   */
  private Map<String, Map<String, UFBoolean>> getEffectByMaterial(List<E> vos) {
    Map<String, Set<String>> fiorg_matsMap = this.getMatSetMapByFiorg(vos);
    Map<String, Map<String, UFBoolean>> result =
        new HashMap<String, Map<String, UFBoolean>>();
    for (Map.Entry<String, Set<String>> fi_mat : fiorg_matsMap.entrySet()) {
      String[] materials =
          fi_mat.getValue().toArray(new String[fi_mat.getValue().size()]);
      String pk_fiorg = fi_mat.getKey();
      // 查询物料的价值管理模式
      Map<String, MaterialFiVO> mapvo =
          MaterialPubService.getFIInfo(materials, pk_fiorg, new String[] {
            MaterialFiVO.MATERIALVALUEMGT
          });
      if (null != mapvo) {
        Map<String, UFBoolean> temp = new HashMap<String, UFBoolean>();
        for (Map.Entry<String, MaterialFiVO> ite : mapvo.entrySet()) {
          // 只有价值管理模式为存货核算的才传存货
          UFBoolean value =
              ite.getValue() == null ? UFBoolean.FALSE
                  : UFBoolean
                      .valueOf(ite.getValue().getMaterialvaluemgt().intValue() == IMaterialEnumConst.MATERIALVALUEMGT_INVCOSTING);
          temp.put(ite.getKey(), value);
        }
        result.put(pk_fiorg, temp);
      }
    }
    return result;
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

  /**
   * 得到财务组织到物料的MAP
   * 
   * @param vos
   * @return
   */
  private Map<String, Set<String>> getMatSetMapByFiorg(List<E> vos) {
    Map<String, Set<String>> fiorg_matsMap = new HashMap<String, Set<String>>();
    for (CircularlyAccessibleValueObject item : this.getFillItems(vos)) {
      String pk_fiorg =
          (String) item.getAttributeValue(GoodsEstVO.PK_FINANCEORG);
      Set<String> mats = fiorg_matsMap.get(pk_fiorg);
      if (null == mats) {
        mats = new HashSet<String>();
      }
      mats.add((String) item.getAttributeValue(GoodsEstVO.PK_MATERIAL));
      if (!fiorg_matsMap.containsKey(pk_fiorg)) {
        fiorg_matsMap.put(pk_fiorg, mats);
      }
    }

    return fiorg_matsMap;
  }

  /**
   * 得到普通采购类型的单据vo
   * 
   * @param vos
   * @param normpur
   * @return
   */
  private List<E> getNormalPUVos(E[] vos, UFBoolean normpur) {
    List<E> result = new ArrayList<E>();
    for (E vo : vos) {
      ISuperVO head = vo.getParent();
      if (normpur.equals(head.getAttributeValue(GoodsEstVO.BNORMPUR))) {
        result.add(vo);
      }
    }
    return result;
  }

  /**
   * 对普通采购的单据设置影响成本标志
   * 
   * @param vos
   */
  private void normPurFill(List<E> vos) {
    E[] voAry = new ListToArrayTool<E>().convertToArray(vos);
    // 查询交易类型是否影响成本
    String[] types =
        (String[]) AggVOUtil.getDistinctHeadFieldArray(voAry,
            PurchaseinFIHeaderVO.CTRANTYPEID, String.class);
    Map<String, UFBoolean> typeMap = this.getEffectByTrantype(types);
    // 仓库影响成本标志
    boolean isStocConsider = this.isConsiderStoredoc();
    Map<String, UFBoolean> stordocMap = new HashMap<String, UFBoolean>();
    if (isStocConsider) {
      String[] pk_stordocs =
          (String[]) AggVOUtil.getDistinctHeadFieldArray(voAry,
              PurchaseinFIHeaderVO.PK_STORDOC, String.class);
      stordocMap = this.getEffectByStordoc(pk_stordocs);
    }
    // 查询物料是否影响成本
    Map<String, Map<String, UFBoolean>> orgMarValMap =
        this.getEffectByMaterial(vos);
    for (E vo : vos) {
      String ttype =
          (String) vo.getParentVO().getAttributeValue(
              PurchaseinFIHeaderVO.CTRANTYPEID);
      // 交易类型影响成本标志
      UFBoolean tranTypeflag = typeMap.get(ttype);
      String pk_stordoc =
          (String) vo.getParentVO().getAttributeValue(
              PurchaseinFIHeaderVO.PK_STORDOC);

      UFBoolean stocFlag =
          isStocConsider ? stordocMap.get(pk_stordoc) : UFBoolean.TRUE;
      this.fillByMarValue(vo, orgMarValMap, UFBoolean.TRUE.equals(tranTypeflag)
          && UFBoolean.TRUE.equals(stocFlag));
    }
  }

  /**
   * 判断交易类型（出入库类型是否影响成本）
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @param types
   * @return
   */
  protected Map<String, UFBoolean> getEffectByTrantype(String[] types) {
    Map<String, UFBoolean> typeMap =
        M45PUServices.getStockTrantypeAffectCostFlag(types);
    return typeMap;
  }

  /**
   * 得到需要设置影响成本标志的VO，采购入、委外入为表体、消耗汇总为表头
   * 
   * @param vos
   * @return
   */
  protected List<CircularlyAccessibleValueObject> getFillItems(List<E> vos) {
    List<CircularlyAccessibleValueObject> filterItems =
        new ArrayList<CircularlyAccessibleValueObject>();
    for (E vo : vos) {
      CircularlyAccessibleValueObject[] items = vo.getChildrenVO();
      for (CircularlyAccessibleValueObject item : items) {
        filterItems.add(item);
      }
    }
    return filterItems;
  }

  /**
   * 得到vo中的库存组织
   * 
   * @param item
   * @return
   */
  protected String getPk_Stockorg(CircularlyAccessibleValueObject item) {
    return (String) item.getAttributeValue(PurchaseinFIItemVO.PK_ORG);
  }

  /**
   * 创建查询内部交易结算关系的参数VO
   * 
   * @param cpItems
   * @return
   */
  protected <T extends SuperVO> Map<String, T> getQueryMatchSettleRuleVO(
      List<CircularlyAccessibleValueObject> cpItems) {
    Map<String, T> retMap = new HashMap<String, T>();
    Set<String> codes = new HashSet<String>();
    for (CircularlyAccessibleValueObject vo : cpItems) {
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
    for (int i = 0; i < cpItems.size(); i++) {
      CircularlyAccessibleValueObject item = cpItems.get(i);
      MatchSettleRuleVOForPu mathvo = new MatchSettleRuleVOForPu();
      mathvo.setCinstockorgid(this.getPk_Stockorg(item));
      mathvo.setCoutfinanceorgid((String) item
          .getAttributeValue(PurchaseinFIItemVO.PK_FINANCEORG));
      mathvo.setCinventoryid((String) item
          .getAttributeValue(PurchaseinFIItemVO.PK_SRCMATERIAL));
      mathvo.setPk_group((String) item
          .getAttributeValue(PurchaseinFIItemVO.PK_GROUP));
      mathvo.setCtranstype(null != code_idMap ? code_idMap.get(item
          .getAttributeValue(PurchaseinFIItemVO.VORDERTRANTYPECODE)) : null);
      retMap.put(
          (String) item.getAttributeValue(PurchaseinFIItemVO.PK_STOCKPS_B),
          (T) mathvo);
    }
    return retMap;
  }

  /**
   * 是否考虑仓库的传成本标志
   * 
   * @return
   */
  protected boolean isConsiderStoredoc() {
    return true;
  }

  /**
   * 查询内部交易结算关系信息：是否影响成本
   * 
   * @param parapk_b
   * @return
   */
  protected <T extends SuperVO> Map<T, MatchSettleRuleResult> queryMatchResult(
      Map<String, T> parapk_b) {
    List<T> matchVos = new ArrayList<T>(parapk_b.values());
    Map<T, MatchSettleRuleResult> mathMap =
        (Map<T, MatchSettleRuleResult>) SettleRuleServices
            .getMathSettleRule((List<MatchSettleRuleVOForPu>) matchVos);
    return mathMap;
  }
}
