package nc.vo.pu.m23.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class FillBillInfoFor23 {

  /**
   * 补单据计算属性 固定换算率
   * 
   * @param vo
   */
  public static void fillBillInfo(ArriveVO[] vos) {
    List<String> pk_materials = new ArrayList<String>();
    List<String> castunitids = new ArrayList<String>();
    if (vos == null) {
      return;
    }
    for (ArriveVO vo : vos) {
      ArriveItemVO[] itemVOs = vo.getBVO();
      if (null == itemVOs) {
        continue;
      }
      for (ArriveItemVO itemVO : itemVOs) {
        if (null == itemVO || StringUtils.isEmpty(itemVO.getPk_material())
            || StringUtils.isEmpty(itemVO.getCastunitid())) {
          continue;
        }
        pk_materials.add(itemVO.getPk_material());
        castunitids.add(itemVO.getCastunitid());
      }
    }
    if (CollectionUtils.isEmpty(pk_materials)
        || CollectionUtils.isEmpty(castunitids)) {
      return;
    }
    Map<String, UFBoolean> map =
        MaterialPubService.queryIsFixedRateByMaterialAndMeasdocs(
            pk_materials.toArray(new String[0]),
            castunitids.toArray(new String[0]));
    for (ArriveVO vo : vos) {
      ArriveItemVO[] itemVOs = vo.getBVO();
      if (null == itemVOs) {
        continue;
      }
      if (map != null) {
        for (ArriveItemVO itemVO : itemVOs) {
          if (null == itemVO || StringUtils.isEmpty(itemVO.getPk_material())
              || StringUtils.isEmpty(itemVO.getCastunitid())) {
            continue;
          }
          String key = itemVO.getPk_material() + itemVO.getCastunitid();
          itemVO.setBfixedrate(map.get(key));
        }
      }
    }
  }
}
