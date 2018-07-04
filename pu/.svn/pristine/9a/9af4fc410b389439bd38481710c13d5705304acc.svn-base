package nc.vo.pu.m21.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.pu.MaterialPuVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>查询并设置物料的最高限价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-5-10 下午05:36:24
 */
public class MaxPrice {
  private IKeyValue keyValue;

  public MaxPrice(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setMaxPrice(int[] rows) {
    // 从物料档案的采购组织页签查询最高限价
    String pk_purchaseorg =
        (String) this.keyValue.getHeadValue(OrderHeaderVO.PK_ORG);
    List<String> itemIds = new ArrayList<String>();
    for (int row : rows) {
      String pk_material =
          (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_MATERIAL);
      if (pk_material != null) {
        itemIds.add(pk_material);
      }
    }

    Map<String, MaterialPuVO> map = null;
    map =
        MaterialPubService.queryMaterialPurchaseInfoMap(itemIds,
            pk_purchaseorg, new String[] {
              MaterialPuVO.PK_MATERIAL, MaterialPuVO.MAXPRICE
            });

    // 把最高限价设置到界面上
    for (int row : rows) {
      String pk_material =
          (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_MATERIAL);
      if (pk_material != null && map != null && map.containsKey(pk_material)) {
        this.keyValue.setBodyValue(row, OrderItemVO.NMAXPRICE,
            map.get(pk_material).getMaxprice());
      }
    }
  }
}
