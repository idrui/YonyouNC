package nc.bs.pu.it.util;

import java.util.ArrayList;
import java.util.List;

import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.31
 * @version 2013-11-20 下午03:04:54
 * @author mengjian
 */
public class FeeSettlePrivateUtilForIT {

  /**
   * 方法功能描述：从结算单数组中查找费用结算的对应VO数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   *         费用结算的对应VO数组
   * @since 6.0
   * @author hanbin
   * @time 2010-8-20 下午02:17:45
   */
  public static List<SettleBillVO> findNeedFeeSettleVO(SettleBillVO[] vos) {
    List<SettleBillVO> feevos = new ArrayList<SettleBillVO>();
    if (ArrayUtils.isEmpty(vos)) {
      return feevos;
    }
    for (SettleBillVO vo : vos) {
      for (SettleBillItemVO item : vo.getChildrenVO()) {
        // 如果结算单表体存在劳务行，则认为是费用结算
        if (EnumMatchRowType.Fee.value().equals(item.getFrowtype())) {
          feevos.add(vo);
          break;
        }
        // 如果结算单表体存在折扣行，则认为是费用结算
        if (EnumMatchRowType.Discount.value().equals(item.getFrowtype())) {
          feevos.add(vo);
          break;
        }
        // 如果结算单表体存在进口调整行，则认为是费用结算
        if (EnumMatchRowType.AdjustGoods.value().equals(item.getFrowtype())) {
          feevos.add(vo);
          break;
        }
      }
    }
    return feevos;
  }

}
