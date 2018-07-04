package nc.vo.pu.m422x.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.IMaterialEnumConst;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.AggregatedValueObject;

/**
 * 物资需求申请按委外分单
 * 
 * @since 6.0
 * @version 2012-6-1 上午10:45:24
 * @author lixyp
 */
public class SplitBySCFor20 {

  public List<String> splitBySC(AggregatedValueObject vo) {
    StoreReqAppVO storeReqAppVO = (StoreReqAppVO) vo;

    // 批量获取物料库存组织信息。
    List<String> materialPks = new ArrayList<String>();
    for (StoreReqAppItemVO storeReqAppItemVO : storeReqAppVO.getBVO()) {
      materialPks.add(storeReqAppItemVO.getPk_material());
    }
    Map<String, MaterialStockVO> marterialStockMapping =
        MaterialPubService.queryMaterialStockInfo(materialPks
            .toArray(new String[materialPks.size()]), storeReqAppVO.getHVO()
            .getPk_org(), new String[] {
          MaterialStockVO.MARTYPE
        });

    // 为每行表体设置是否委外标志
    List<String> result = new ArrayList<String>();
    for (StoreReqAppItemVO storeReqAppItemVO : storeReqAppVO.getBVO()) {
      if (marterialStockMapping.get(storeReqAppItemVO.getPk_material()) != null
          && IMaterialEnumConst.MATERTYPE_OT.equals(marterialStockMapping.get(
              storeReqAppItemVO.getPk_material()).getMartype())) {
        result.add("SC");
      }
      else {
        result.add("NSC");
      }
    }

    return result;
  }
}
