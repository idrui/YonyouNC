package nc.vo.pu.m4t.rule;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPCPubService;
import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 *            期初暂估单审批时，设置影响表体影响利润中心成本标志
 * @scene
 *      期初暂估单审批
 * @param
 * 
 *
 * @since 6.5
 * @version 2014-10-23 下午7:12:23
 * @author mengjian
 */
public class InitialEstAffectPCCostFlagRule implements IRule<InitialEstVO> {

  @Override
  public void process(InitialEstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // 设置影响表体影响利润中心成本标志
    this.setAffectPCCost(vos);
  }

  /**
   * 根据仓库的是否成本计算来设置影响成本标志（不需要此规则的单据，返回零长的MAP)
   * 
   * @param pk_stordocs
   * @return
   */
  private Map<String, UFBoolean> getEffectByStordoc(String[] pk_stordocs) {
    Map<String, UFBoolean> result = new HashMap<String, UFBoolean>();
    if (null == pk_stordocs || pk_stordocs.length < 1) {
      return result;
    }
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

  private Map<String, UFBoolean> getFlagByMaterial(String[] materials,
      String[] pk_apliabcenters) {
    // 物料【】+利润中心【】获得“是否传存货”属性
    MaterialPCPubService pcservice = new MaterialPCPubService();
    return pcservice.queryTransfermarByMarOIDsAndPfcPkOrgs(pk_apliabcenters,
        materials);
  }

  private void setAffectPCCost(InitialEstVO[] vos) {
    Map<String, UFBoolean> stordocMap = new HashMap<String, UFBoolean>();
    String[] pk_stordocs =
        (String[]) AggVOUtil.getDistinctHeadFieldArray(vos,
            InitialEstHeaderVO.PK_STORDOC, String.class);
    stordocMap = this.getEffectByStordoc(pk_stordocs);
    for (InitialEstVO vo : vos) {
      String pk_stordoc =
          (String) vo.getParentVO().getAttributeValue(
              InitialEstHeaderVO.PK_STORDOC);
      if (null == pk_stordoc) {
        continue;
      }
      UFBoolean isStordoc = stordocMap.get(pk_stordoc);
      // 期初暂估单仓库勾选影响成本；
      if (UFBoolean.FALSE.equals(isStordoc)) {
        continue;
      }
      InitialEstItemVO[] bodyvos = vo.getItems();
      Map<String, UFBoolean> materialMap = new HashMap<String, UFBoolean>();
      String[] materials =
          (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
              InitialEstItemVO.PK_SRCMATERIAL, String.class);
      String[] pk_apliabcenters =
          (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
              InitialEstItemVO.PK_APLIABCENTER, String.class);
      materialMap = this.getFlagByMaterial(materials, pk_apliabcenters);
      if (null == materialMap) {
        continue;
      }
      for (InitialEstItemVO bodyvo : bodyvos) {
        String pk_apliabcenter = bodyvo.getPk_apliabcenter();
        String pk_srcmaterial = bodyvo.getPk_srcmaterial();
        // 期初暂估单利润中心非空；
        if (StringUtils.isNotBlank(pk_apliabcenter)) {
          // 物料档案利润中心页签定义“是否传存货”属性为是；
          UFBoolean isPCCost =
              materialMap.get(pk_apliabcenter + pk_srcmaterial);
          bodyvo.setBaffectpccost(isPCCost);
          bodyvo.setStatus(VOStatus.UPDATED);
        }
      }

    }
  }
}
