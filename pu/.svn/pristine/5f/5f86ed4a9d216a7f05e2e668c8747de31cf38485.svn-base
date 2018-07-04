/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-18 上午10:33:14
 */
package nc.impl.pu.m21.action.rule.rp;

import java.util.Map.Entry;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.pub.OrderReceivePlanUtils;
import nc.vo.pubapp.pattern.pub.MapSet;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>判断某订单的到货计划的存货是否存在于库存组织中
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-18 上午10:33:14
 */
public class StoreAndMaterialRule implements IRule<BatchOperateVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(BatchOperateVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    OrderReceivePlanVO[] rpVOs = OrderReceivePlanUtils.getAddAndUpVOs(vos[0]);
    if (ArrayUtils.isEmpty(rpVOs)) {
      return;
    }

    // String[] saCalBodyId = this.getPks(rpVOs,
    // OrderReceivePlanVO.PK_ARRVSTOORG);
    // if (ArrayUtils.isEmpty(saCalBodyId)) {
    // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
    // .getNCLangRes().getStrByID("4004030_0", "04004030-0165")/*
    // * @res
    // * "到货计划的库存组织为空，不能进行库存组织与存货的匹配性检查"
    // */);
    // }

    // String[] saBId = this.getPks(rpVOs, OrderReceivePlanVO.PK_ORDER_B);
    // if (ArrayUtils.isEmpty(saBId)) {
    // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
    // .getNCLangRes().getStrByID("4004030_0", "04004030-0166")/*
    // * @res
    // * "存在没有物料的行，请检查"
    // */);
    // }

    // 检查与库存组织不匹配的物料
    MapSet<String, String> mapset = this.getMapSet(rpVOs);
    for (Entry<String, Set<String>> entry : mapset.entrySet()) {
      String key = entry.getKey();
      Set<String> set = entry.getValue();
      String[] mats = set.toArray(new String[set.size()]);
      MaterialPubService.checkMaterialVisiabilityInStorckOrg(key, mats);
    }
  }

  private MapSet<String, String> getMapSet(OrderReceivePlanVO[] rpVOs) {
    MapSet<String, String> mapset = new MapSet<String, String>();
    for (OrderReceivePlanVO rp : rpVOs) {
      mapset.put(rp.getPk_arrvstoorg(), rp.getPk_material());
    }
    return mapset;
  }

  /**
   * 方法功能描述：得到主键，不含NULL或空串，并过滤重复值
   * <p>
   * <b>参数说明</b>
   * 
   * @param voaPlanItem
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 上午11:11:12
   */
  // private String[] getPks(OrderReceivePlanVO[] rpVOs, String fieldName) {
  // if (ArrayUtils.isEmpty(rpVOs)) {
  // return null;
  // }
  //
  // Set<String> set = new HashSet<String>();
  // for (OrderReceivePlanVO itemVO : rpVOs) {
  // String pk = (String) itemVO.getAttributeValue(fieldName);
  // if (!StringUtil.isEmptyWithTrim(pk)) {
  // set.add(pk);
  // }
  // }
  //
  // if (set.size() > 0) {
  // String[] pks = set.toArray(new String[0]);
  // return pks;
  // }
  //
  // return null;
  // }

}
