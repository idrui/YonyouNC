package nc.bs.pu.m21.pf.function;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.split.ISplitBillByStoreAdmin;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * @since 6.0
 * @version 2011-2-23 ÏÂÎç02:59:58
 * @author wuxla
 */

public class SplitByStoAdminFor45 {
  public List<String> splitByStoreAdmin(AggregatedValueObject vo)
      throws BusinessException {
    ISplitBillByStoreAdmin service =
        NCLocator.getInstance().lookup(ISplitBillByStoreAdmin.class);
    return service.splitByStoreAdmin(vo, new String[] {
      OrderItemVO.PK_ARRVSTOORG, OrderItemVO.PK_RECVSTORDOC,
      OrderItemVO.PK_SRCMATERIAL
    });
  }
}
