package nc.pubitf.pu.m422x.pubquery;

import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pub.BusinessException;

/**
 * 物资需求申请单提供给外模块的公共查询服务
 * 
 * @since 6.3
 * @version 2012-10-25 下午03:20:02
 * @author fanly3
 */
public interface IStoreReqPubQuery {

  /**
   * 通过物资需求申请单表体ID查询对应的物资需求申请单表体VO。支持批量查询。
   * 
   * @param bids 物资需求申请单表体id数组
   * @return 物资需求申请单表体VO
   * @throws BusinessException
   */
  StoreReqAppItemVO[] queryItemVOByBids(String[] bids) throws BusinessException;

  StoreReqAppItemVO[] queryItemVOByCSourcebids(String[] bids, String[] fields)
      throws BusinessException;
}
