/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-11-4 上午08:25:03
 */
package nc.bs.pu.m20.pf.function.split;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.position.IPositionForSplit;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>生成采购订单时根据采购岗分单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-11-4 上午08:25:03
 */
public class SplitPrayByPosFor21 {

  /**
   * 按照物料+采购组织匹配采购岗设置获取对应的采购岗，然后按照采购岗分单。
   * 
   * @param vo 请购单VO
   * @return
   * @throws BusinessException
   */
  public List<String> splitByPosition(AggregatedValueObject vo)
      throws BusinessException {
    IPositionForSplit service =
        NCLocator.getInstance().lookup(IPositionForSplit.class);
    return service.splitBorgByPosition(vo, new String[] {
      PraybillItemVO.PK_PURCHASEORG, PraybillItemVO.PK_MATERIAL, null
    }, PositionHeaderVO.purchasePosition);
  }
}
