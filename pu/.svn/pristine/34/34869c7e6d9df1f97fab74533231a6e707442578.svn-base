package nc.bs.pu.m20.pf.function.split;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.scmf.pu.ordertranstype.pu.IOrderTranstypeSplit;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * 分单函数
 * 
 * @since 6.0
 * @version 2011-1-20 上午10:02:08
 * @author liuchx
 */
public class SpPrayByTrans {

  /**
   * 按照物料+采购组织匹配订单类型设置获取对应的订单类型，然后按照订单类型分单。
   * 
   * @param vo VO
   * @return
   * @throws BusinessException
   */
  public List<String> splitByTrans(AggregatedValueObject vo)
      throws BusinessException {

    IOrderTranstypeSplit service =
        NCLocator.getInstance().lookup(IOrderTranstypeSplit.class);
    return service.splitBorgByOrderTrans(vo, new String[] {
      PraybillItemVO.PK_GROUP, PraybillItemVO.PK_ORG,
      PraybillItemVO.PK_SRCMATERIAL
    });
  }
}
