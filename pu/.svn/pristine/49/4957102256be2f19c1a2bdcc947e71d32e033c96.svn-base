package nc.pubitf.pu.m23.ic.m45;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * 入库单转固时查询到货单是否已经转固
 * 
 * @since 6.0
 * @version 2010-12-21 下午07:01:57
 * @author wuxla
 */

public interface IQueryBTransAssetFor45 {

  /**
   * 入库单转固时查询到货单是否已经转固
   * 
   * @param bids 到货单表体主键
   * @return key：到货单主键 value：已经转固为Y，否则为N
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryBTransAssetFor45(String[] bids)
      throws BusinessException;
}
