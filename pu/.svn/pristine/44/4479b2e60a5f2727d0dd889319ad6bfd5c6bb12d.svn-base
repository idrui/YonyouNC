package nc.vo.pu.m27.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.IMaterialEnumConst;
import nc.vo.bd.material.fi.MaterialFiVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算单表体工具类
 * <li>
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author wangyf
 * @time 2010-3-30 下午03:58:16
 */
public class SettleBillItemVOUtil {

  /**
   * 对于集采的结算单行在传存货核算前要清库存组织和仓库（仓库清除走VO对照）
   * 
   * @param sitem
   * @param ssVo
   */
  public static void clearCenterPurInfo(SettleBillItemVO sitem,
      StockSettleVO ssVo) {
    if (!UFBoolean.TRUE.equals(ssVo.getBnormpur())) {
      sitem.setPk_arrstockorg(null);
      sitem.setPk_arrstockorg_v(null);
    }
  }

  /**
   * 对于集采的结算单行在传存货核算前要清库存组织和仓库（仓库清除走VO对照）
   * 
   * @param sitem
   * @param ssVo
   */
  public static void clearCenterPurInfo(SettleBillVO[] vos,
      Map<String, StockSettleVO> ssVOMap) {
    if (ssVOMap == null) {
      return;
    }
    for (SettleBillVO vo : vos) {
      for (SettleBillItemVO item : vo.getChildrenVO()) {
        String pk_stock_b = item.getPk_stock_b();
        if (!ssVOMap.containsKey(pk_stock_b)) {
          continue;
        }
        SettleBillItemVOUtil.clearCenterPurInfo(item, ssVOMap.get(pk_stock_b));
      }
    }
  }

  /**
   * 获取物料价值属性中，是否影响成本信息（即是否传存货核算）
   * 
   * @param volst
   * @return MAP{财务组织--MAP{pk_material,是否传存货核算}}
   */
  public static <E extends AbstractBill> Map<String, Map<String, UFBoolean>> getEffectByMaterial(
      List<E> volst) {
    Map<String, Set<String>> fiorg_matsMap =
        SettleBillItemVOUtil.getMatSetMapByFiorg(volst);
    Map<String, Map<String, UFBoolean>> result =
        new HashMap<String, Map<String, UFBoolean>>();
    for (Map.Entry<String, Set<String>> fi_mat : fiorg_matsMap.entrySet()) {
      String[] materials =
          fi_mat.getValue().toArray(new String[fi_mat.getValue().size()]);
      String pk_fiorg = fi_mat.getKey();
      // 查询物料的价值管理模式
      Map<String, MaterialFiVO> mapvo =
          MaterialPubService.getFIInfo(materials, pk_fiorg, new String[] {
            MaterialFiVO.MATERIALVALUEMGT
          });
      if (null != mapvo) {
        Map<String, UFBoolean> temp = new HashMap<String, UFBoolean>();
        for (Map.Entry<String, MaterialFiVO> ite : mapvo.entrySet()) {
          // 只有价值管理模式为存货核算的才传存货
          UFBoolean value =
              ite.getValue() == null ? UFBoolean.FALSE
                  : UFBoolean
                      .valueOf(ite.getValue().getMaterialvaluemgt().intValue() == IMaterialEnumConst.MATERIALVALUEMGT_INVCOSTING);
          temp.put(ite.getKey(), value);
        }
        result.put(pk_fiorg, temp);
      }
    }
    return result;
  }

  /**
   * 检查一个结算单行是否传成本（是否影响成本）
   * 
   * @param sitem 结算单行VO
   * @param ssVo 结算单行对应的入库单行结算VO，可为空<br>
   *          如为空，则不根据入库单做判断，只根据其它规则判断
   * @return
   */
  public static boolean isSettleToIA(SettleBillItemVO sitem, StockSettleVO ssVo) {
    boolean affect = UFBoolean.TRUE.booleanValue();
    if (null != ssVo) {
      affect = UFBoolean.TRUE.equals(ssVo.getBaffectcost());
    }
    return affect;
  }

  /**
   * 检查一个结算单行是否传利润中心成本（是否影响利润中心成本）
   * 
   * @param sitem 结算单行VO
   * @param ssVo 结算单行对应的入库单行结算VO，可为空<br>
   *          如为空，则不根据入库单做判断，只根据其它规则判断
   * @return
   */
  public static boolean isSettleToPCIA(SettleBillItemVO sitem,
      StockSettleVO ssVo) {
    boolean affect = UFBoolean.TRUE.booleanValue();
    if (null != ssVo) {
      affect = UFBoolean.TRUE.equals(ssVo.getBaffectpciacost());
    }
    return affect;
  }

  /**
   * 此行是否需要传成本
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo <p>
   * @author wangyf
   * @time 2010-3-30 下午04:03:53
   */
  public static boolean isStockLine(SettleBillItemVO vo) {
    // 劳务、折扣、异物料结算之发票不传成本，其他都需要计算
    if (EnumMatchRowType.Fee.value().equals(vo.getFrowtype())
        || EnumMatchRowType.Discount.value().equals(vo.getFrowtype())
        || EnumMatchRowType.InvoiceInDiffMatch.value().equals(vo.getFrowtype())) {
      return false;
    }

    return true;
  }

  /**
   * 得到财务组织到物料的MAP
   * 
   * @param volst
   * @return
   */
  private static <E extends AbstractBill> Map<String, Set<String>> getMatSetMapByFiorg(
      List<E> volst) {
    Map<String, Set<String>> fiorg_matsMap = new HashMap<String, Set<String>>();
    for (AggregatedValueObject vo : volst) {
      for (CircularlyAccessibleValueObject item : vo.getChildrenVO()) {
        String pk_fiorg =
            (String) item.getAttributeValue(SettleBillItemVO.PK_ORG);
        Set<String> mats = fiorg_matsMap.get(pk_fiorg);
        if (null == mats) {
          mats = new HashSet<String>();
        }
        mats.add((String) item.getAttributeValue(SettleBillItemVO.PK_MATERIAL));
        if (!fiorg_matsMap.containsKey(pk_fiorg)) {
          fiorg_matsMap.put(pk_fiorg, mats);
        }
      }
    }
    return fiorg_matsMap;
  }

}
