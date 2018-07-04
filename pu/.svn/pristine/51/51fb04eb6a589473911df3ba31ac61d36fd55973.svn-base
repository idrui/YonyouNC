package nc.pubitf.pu.est.m4t;

import nc.vo.pu.est.entity.m4t.InitialBillEstVO;
import nc.vo.pub.BusinessException;

/**
 * 期初暂估单的暂估信息查询
 * 
 * @since 6.0
 * @version 2011-4-12 下午06:55:12
 * @author 田锋涛
 */

public interface IInitEstimateEstPubQuery {
  /**
   * 根据行id查询期初暂估单的暂估信息查询
   * 
   * @param bids 行id数组
   * @return 符合条件的期初单(包括未暂估过的数据)
   * @throws BusinessException
   */
  public InitialBillEstVO[] query(String[] bids) throws BusinessException;
}
