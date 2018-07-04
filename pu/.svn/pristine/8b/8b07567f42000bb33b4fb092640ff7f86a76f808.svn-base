package nc.bs.pu.m23.fa.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.IMaterialEnumConst;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 根据物料周转材属性过滤
 * @scene
 * 周转材直领
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-12-28 上午07:54:35
 * @author wuxla
 */

public class FilterByMaterTurnOverRule implements IFilterRule<ArriveVO> {

  @Override
  public ArriveVO[] process(ArriveVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    Set<String> matreialSet = new HashSet<String>();
    for (ArriveVO vo : vos) {
      if (ArrayUtils.isEmpty(vo.getBVO())) {
        continue;
      }
      for (ArriveItemVO itemVO : vo.getBVO()) {
        matreialSet.add(itemVO.getPk_material());
      }
    }

    Map<String, UFBoolean> turnoverMap =
        this.getTurnOverMap(matreialSet.toArray(new String[matreialSet.size()]));

    List<ArriveVO> voList = new ArrayList<ArriveVO>();
    for (ArriveVO vo : vos) {
      if (ArrayUtils.isEmpty(vo.getBVO())) {
        continue;
      }
      List<ArriveItemVO> list = new ArrayList<ArriveItemVO>();
      for (ArriveItemVO itemVO : vo.getBVO()) {
        if (itemVO != null
            && UFBoolean.TRUE.equals(turnoverMap.get(itemVO.getPk_material()))) {
          list.add(itemVO);
        }
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

  private Map<String, UFBoolean> getTurnOverMap(String[] pk_materials) {
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    Map<String, MaterialVO> result =
        MaterialPubService.queryMaterialBaseInfo(pk_materials, new String[] {
          MaterialVO.MATERIALMGT
        });
    if (result == null) {
      return map;
    }
    for (Map.Entry<String, MaterialVO> ite : result.entrySet()) {
      UFBoolean isTurnover =
          ite.getValue().getMaterialmgt() == null ? UFBoolean.FALSE
              : UFBoolean
                  .valueOf(ite.getValue().getMaterialmgt().intValue() == IMaterialEnumConst.MATERIALMGT_TURNOVER);
      map.put(ite.getKey(), isTurnover);
    }
    return map;
  }
}
