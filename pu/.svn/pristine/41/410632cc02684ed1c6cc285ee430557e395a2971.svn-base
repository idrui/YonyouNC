/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 下午07:13:34
 */
package nc.bs.pu.m20.pf.function;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.pu.MaterialPuVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物料所属采购组织检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-5 下午07:13:34
 */
public class PurchaseOrg {
  /**
   * 方法功能描述：按照物料档案上的采购组织字段值校验<br>
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-7-5 下午06:19:35
   */
  public UFBoolean purchaseOrgMatch(AggregatedValueObject vo)
      throws BusinessException {

    // 合法性检查
    if (vo == null || vo.getParentVO() == null || vo.getChildrenVO() == null
        || vo.getChildrenVO().length <= 0) {
      return UFBoolean.FALSE;
    }

    PraybillItemVO[] items = ((PraybillVO) vo).getBVO();
    HashMap<String, HashSet<String>> orgMaterials =
        new HashMap<String, HashSet<String>>();

    for (PraybillItemVO item : items) {
      // 物料主键数组
      String material = item.getPk_material();
      // 采购组织
      String stockOrgPK = item.getPk_purchaseorg();

      if (orgMaterials.containsKey(stockOrgPK)) {
        orgMaterials.get(stockOrgPK).add(material);
      }
      else {
        HashSet<String> list = new HashSet<String>();
        list.add(material);
        orgMaterials.put(stockOrgPK, list);
      }

    }

    for (Map.Entry<String, HashSet<String>> upEntry : orgMaterials.entrySet()) {
      HashSet<String> list = upEntry.getValue();
      MaterialPuVO[] mrlPuVOArray = null;

      mrlPuVOArray =
          MaterialPubService.queryMaterialPurchaseInfoByPks(
              list.toArray(new String[list.size()]), upEntry.getKey(),
              new String[] {
                MaterialPuVO.PK_MATERIAL
              });

      if (mrlPuVOArray == null || mrlPuVOArray.length != list.size()) {
        return UFBoolean.FALSE;
      }
    }

    return UFBoolean.TRUE;
  }
}
