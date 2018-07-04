/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-13 上午08:24:49
 */
package nc.bs.pu.m21.pf.function;

import java.util.HashSet;
import java.util.Set;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>业务检查函数：订单表体物料是否允许重复
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-13 上午08:24:49
 */
public class InventoryDistinct {

  /**
   * 方法功能描述：单据体物料编码是否重复
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return 不重复返回true；否则返回false
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-13 上午08:39:03
   */
  public UFBoolean isMaterialDistinct(AggregatedValueObject vo)
      throws BusinessException {
    if (null == vo) {
      return UFBoolean.FALSE;
    }

    OrderVO orderVO = (OrderVO) vo;
    OrderItemVO[] itemVOs = orderVO.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return UFBoolean.FALSE;
    }

    if (1 == itemVOs.length) {
      return UFBoolean.TRUE;
    }

    Set<String> set = new HashSet<String>();
    for (OrderItemVO itemVO : itemVOs) {
      if (null == itemVO || this.isDeleteOrRevise(itemVO)) {
        continue;
      }

      String pkMaterial = itemVO.getPk_material();
      if (StringUtil.isEmptyWithTrim(pkMaterial) || set.contains(pkMaterial)) {
        return UFBoolean.FALSE;
      }

      set.add(pkMaterial);
    }

    return UFBoolean.TRUE;
  }

  /**
   * 方法功能描述：判断行是否为删除的行或者历史行
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   * @return 删除的行及历史行返回true，否则返回false
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-13 上午09:16:03
   */
  private boolean isDeleteOrRevise(OrderItemVO itemVO) {
    if (VOStatus.DELETED == itemVO.getStatus()) {
      return true;
    }

    if (EnumActive.REVISEHISTORY.value().equals(itemVO.getFisactive())) {
      return true;
    }

    return false;
  }

}
