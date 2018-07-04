/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-2 下午03:03:48
 */
package nc.bs.pu.m21.maintain.rule.save;

import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.pub.MapSet;

/**
 * @description
 *              采购订单保存后进行库存组织与物料的匹配检查
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:26:19
 * @author luojw
 */
public class MaterialInStorckOrgRule implements IRule<OrderVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    MapSet<String, String> mapset = this.getMapSet(vos);

    if (0 == mapset.size()) {
      return;
    }

    Set<String> orgs = mapset.keySet();
    for (String org : orgs) {
      Set<String> set = mapset.get(org);
      MaterialPubService.checkMaterialVisiabilityInStorckOrg(org,
          set.toArray(new String[set.size()]));
    }

  }

  /**
   * 方法功能描述：把表体物料按照收货库存组织分组
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-2 下午03:28:01
   */
  private MapSet<String, String> getMapSet(OrderVO[] vos) {
    MapSet<String, String> mapset = new MapSet<String, String>();

    for (OrderVO vo : vos) {
      OrderItemVO[] itemVOs = vo.getBVO();
      for (OrderItemVO itemVO : itemVOs) {
        String pk_arrvstoreorg = itemVO.getPk_arrvstoorg();
        if (StringUtil.isEmptyWithTrim(itemVO.getPk_arrvstoorg())) {
          continue;
        }

        mapset.put(pk_arrvstoreorg, itemVO.getPk_material());
      }
    }

    return mapset;
  }

}
