package nc.vo.pu.m422x.function;

import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFDouble;

public class StoreReqMny {

  /**
   * 整单金额
   * :价税合计
   * 
   * @param vo
   * @return
   */
  public UFDouble getStoreReqMny(AggregatedValueObject vo) {
    if (vo == null || vo.getParentVO() == null || vo.getChildrenVO() == null
        || vo.getChildrenVO().length <= 0) {
      return new UFDouble(0.0);
    }

    UFDouble origmny = ((StoreReqAppVO) vo).getHVO().getNtotalorigmny();
    // 头表主键为空，说明是新增VO,从VO中获取数据计算合计主数量
    if (null == origmny) {
      return new UFDouble(0.0);
    }

    return origmny;
  }

}
