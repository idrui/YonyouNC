package nc.vo.pu.m422x.function;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.position.IPositionForSplit;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.position.entity.PositionHeaderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * 物资需求申请按计划岗分单
 * 
 * @since 6.0
 * @version 2012-6-1 上午10:38:55
 * @author lixyp
 */
public class SplitByPosFor20 {

  public List<String> splitByPosition(AggregatedValueObject vo)
      throws BusinessException {
    IPositionForSplit splitItf =
        NCLocator.getInstance().lookup(IPositionForSplit.class);

    return splitItf.splitHorgByPosition(vo, new String[] {
      StoreReqAppHeaderVO.PK_ORG, StoreReqAppItemVO.PK_MATERIAL, null
    }, PositionHeaderVO.planPosition);
  }
}
