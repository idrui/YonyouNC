package nc.bs.pu.m23.pf.function;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.scmf.pu.ordertranstype.pu.IOrderTranstypeSplit;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * 采购订单参照退货单，按照订单类型分单
 * 
 * @since 6.0
 * @version 2011-1-20 上午10:51:08
 * @author wuxla
 */

public class SplitBackArrByTransFor21 {

  /**
   * 按照订单类型分单
   * 
   * @param vo
   * @return
   * @throws BusinessException
   */
  public List<String> splitByTrans(AggregatedValueObject vo)
      throws BusinessException {
    IOrderTranstypeSplit service =
        NCLocator.getInstance().lookup(IOrderTranstypeSplit.class);
    return service.splitHorgByOrderTrans(vo, new String[] {
      ArriveHeaderVO.PK_GROUP, ArriveHeaderVO.PK_PURCHASEORG,
      ArriveItemVO.PK_SRCMATERIAL
    });
  }
}
