package nc.bs.pu.m23.pf.function;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.position.IPositionForSplit;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * 采购订单参照退货单，按照物料+采购组织匹配采购岗设置获取对应的采购岗，然后按照采购岗分单。
 * 
 * @since 6.0
 * @version 2010-11-5 下午01:52:04
 * @author wuxla
 */

public class SplitBackArrByPosFor21 {

  /**
   * 按照物料+采购组织匹配采购岗设置获取对应的采购岗，然后按照采购岗分单。
   * 
   * @param vo 到货单VO
   * @return
   * @throws BusinessException
   */
  public List<String> splitByPosition(AggregatedValueObject vo)
      throws BusinessException {
    IPositionForSplit service =
        NCLocator.getInstance().lookup(IPositionForSplit.class);
    return service.splitHorgByPosition(vo, new String[] {
      ArriveHeaderVO.PK_PURCHASEORG, ArriveItemVO.PK_MATERIAL, null
    }, PositionHeaderVO.purchasePosition);
  }
}
