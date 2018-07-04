package nc.bs.pu.m23.fa.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.bd.material.IMaterialEnumConst;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.fi.MaterialFiVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 通过物料属性过滤
 * 到货单表体行存在物料档案的“设备卡片管理”属性为否
 * 到货单表体行存在物料档案的价值管理模式为固定资产
 * @scene
 * 生成转固单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-12-23 下午12:39:10
 * @author wuxla
 */


public class FilterByMateialRule implements IFilterRule<ArriveVO> {

  @Override
  public ArriveVO[] process(ArriveVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }

    // 根据库存组织查出的所属财务组织，用于匹配物料档案的价值管理模式。
    Map<String, String> stockFinanceOrgs = this.getStockFinanceOrgs(vos);

    Set<String> materialSet = new HashSet<String>();
    Map<String, List<String>> fi_matMap = new HashMap<String, List<String>>();
    for (ArriveVO vo : vos) {
      String financeOrg = stockFinanceOrgs.get(vo.getHVO().getPk_org());
      for (ArriveItemVO itemVO : vo.getBVO()) {
        materialSet.add(itemVO.getPk_material());
        List<String> matls = fi_matMap.get(financeOrg);
        if (null == matls) {
          matls = new ArrayList<String>();
          fi_matMap.put(financeOrg, matls);
        }
        matls.add(itemVO.getPk_material());
      }
    }
    String[] materials = materialSet.toArray(new String[materialSet.size()]);
    Map<String, UFBoolean> cardMap = this.getCardMap(materials);
    Map<String, Map<String, UFBoolean>> fixedAssetMap =
        this.getFixedAssetMap(fi_matMap);

    List<ArriveVO> voList = new ArrayList<ArriveVO>();
    for (ArriveVO vo : vos) {
      String financeOrg = stockFinanceOrgs.get(vo.getHVO().getPk_org());
      List<ArriveItemVO> list = new ArrayList<ArriveItemVO>();
      for (ArriveItemVO itemVO : vo.getBVO()) {
        String pk_material = itemVO.getPk_material();
        if (cardMap.get(pk_material) == null
            || !UFBoolean.FALSE.equals(cardMap.get(pk_material))) {
          continue;
        }
        if (!UFBoolean.TRUE
            .equals(fixedAssetMap.get(financeOrg) == null ? UFBoolean.FALSE
                : fixedAssetMap.get(financeOrg).get(pk_material))) {
          continue;
        }
        list.add(itemVO);
      }
      if (list.size() > 0) {
        ArriveItemVO[] itemVOs = list.toArray(new ArriveItemVO[list.size()]);
        vo.setBVO(itemVOs);
        voList.add(vo);
      }
    }
    if (voList.size() > 0) {
      return voList.toArray(new ArriveVO[voList.size()]);
    }
    return null;
  }

  /**
   * 获得物料基本信息——实物物料管理模式是否为设备
   * 
   * @param materials
   * @return
   */
  private Map<String, UFBoolean> getCardMap(String[] materials) {
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    Map<String, MaterialVO> result =
        MaterialPubService.queryMaterialBaseInfo(materials, new String[] {
          MaterialVO.MATERIALMGT
        });
    if (result == null) {
      return map;
    }
    for (Map.Entry<String, MaterialVO> ite : result.entrySet()) {
      UFBoolean isEquip =
          ite.getValue().getMaterialmgt() == null ? UFBoolean.FALSE
              : UFBoolean
                  .valueOf(ite.getValue().getMaterialmgt().intValue() == IMaterialEnumConst.MATERIALMGT_EQUIP);
      map.put(ite.getKey(), isEquip);
    }
    return map;
  }

  /**
   * 获得物料的物料档案的价值管理模式是否为固定资产
   * 
   * @param materials
   * @return
   */
  private Map<String, Map<String, UFBoolean>> getFixedAssetMap(
      Map<String, List<String>> materials) {
    Map<String, Map<String, UFBoolean>> map =
        new HashMap<String, Map<String, UFBoolean>>();
    for (Map.Entry<String, List<String>> ite : materials.entrySet()) {
      Map<String, MaterialFiVO> result =
          MaterialPubService.getFIInfo(
              ite.getValue().toArray(new String[ite.getValue().size()]),
              ite.getKey(), new String[] {
                MaterialFiVO.MATERIALVALUEMGT
              });
      if (null == result) {
        continue;
      }
      Map<String, UFBoolean> matmap = new HashMap<String, UFBoolean>();
      map.put(ite.getKey(), matmap);
      for (Map.Entry<String, MaterialFiVO> entry : result.entrySet()) {
        UFBoolean value =
            entry.getValue() == null ? UFBoolean.FALSE
                : UFBoolean
                    .valueOf(entry.getValue().getMaterialvaluemgt().intValue() == IMaterialEnumConst.MATERIALVALUEMGT_FIXASSET);
        matmap.put(entry.getKey(), value);
      }
    }
    return map;
  }

  /**
   * 获取库存组织所属财务组织。
   * 
   * @param vos
   * @return key为库存组织OID，value为财务组织
   */
  private Map<String, String> getStockFinanceOrgs(ArriveVO[] vos) {
    List<String> stockOrgs = new ArrayList<String>();
    for (ArriveVO vo : vos) {
      stockOrgs.add(vo.getHVO().getPk_org());
    }
    return StockOrgPubService.queryFinanceOrgIDByStockOrgID(stockOrgs
        .toArray(new String[stockOrgs.size()]));
  }
}
