package nc.bs.pu.m23.pf.function;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.split.ISplitBillByStoreAdmin;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * @since 6.0
 * @version 2011-2-23 ÏÂÎç04:11:55
 * @author wuxla
 */

public class SplitByStoAdminFor45 {
  public List<String> splitByStoreAdmin(AggregatedValueObject vo)
      throws BusinessException {
    ISplitBillByStoreAdmin service =
        NCLocator.getInstance().lookup(ISplitBillByStoreAdmin.class);
    return service.splitByStoreAdmin(vo, new String[] {
      ArriveItemVO.PK_ORG, ArriveItemVO.PK_RECEIVESTORE,
      ArriveItemVO.PK_SRCMATERIAL
    });
  }
}
