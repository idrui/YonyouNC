/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-18 上午08:31:48
 */
package nc.vo.pu.m25.pub;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.fi.MaterialFiVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>查找物料的计划价，并设置
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-6-18 上午08:31:48
 */
public class InvoicePlanPriceSetter {

  public void setPlanPrice(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    Map<String, MaterialFiVO> map = null;
    String pk_org = null;
    String[] pk_materials = null;
    for (InvoiceVO vo : vos) {
      pk_org = vo.getParentVO().getPk_org();
      pk_materials = this.getPK_materials(vo.getChildrenVO());
      if (StringUtils.isEmpty(pk_org) || ArrayUtils.isEmpty(pk_materials)) {
        continue;
      }
      map = MaterialPubService.getFIInfo(pk_materials, pk_org, new String[] {
        MaterialFiVO.PLANPRICE
      });
      this.setItemPlanPrice(vo.getChildrenVO(), map);
    }
  }

  /**
   * 方法功能描述：获取表体物料主键
   * <p>
   * <b>参数说明</b>
   * 
   * @param items
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-6-18 上午08:47:03
   */
  private String[] getPK_materials(InvoiceItemVO[] items) {
    if (ArrayUtils.isEmpty(items)) {
      return null;
    }
    Set<String> set = new HashSet<String>();
    for (InvoiceItemVO item : items) {
      set.add(item.getPk_material());
    }
    return set.toArray(new String[set.size()]);
  }

  /**
   * 方法功能描述：为表体设置计划价
   * <p>
   * <b>参数说明</b>
   * 
   * @param items
   *          <p>
   * @since 6.0
   * @author tianft
   * @time 2010-6-18 上午08:47:20
   */
  private void setItemPlanPrice(InvoiceItemVO[] items,
      Map<String, MaterialFiVO> map) {
    if (ArrayUtils.isEmpty(items) || MapUtils.isEmpty(map)) {
      return;
    }
    // 设置计划价
    for (InvoiceItemVO item : items) {
      if (map.get(item.getPk_material()) != null
          && map.get(item.getPk_material()).getPlanprice() != null) {
        item.setNplanprice(map.get(item.getPk_material()).getPlanprice());
      }
    }
  }

}
