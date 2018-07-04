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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ѯ���������ϵ�����޼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-5-10 ����05:36:24
 */
public class MaxPrice {
  private IKeyValue keyValue;

  public MaxPrice(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setMaxPrice(int[] rows) {
    // �����ϵ����Ĳɹ���֯ҳǩ��ѯ����޼�
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

    // ������޼����õ�������
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
