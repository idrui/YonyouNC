package nc.pubimpl.pu.m23.ic.m45;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.pubitf.pu.m23.ic.m45.IQueryBTransAssetFor45;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 入库单转固时查询到货单是否已经转固
 * 
 * @since 6.0
 * @version 2010-12-21 下午07:06:03
 * @author wuxla
 */

public class QueryBTransAssetFor45Impl implements IQueryBTransAssetFor45 {

  @Override
  public Map<String, UFBoolean> queryBTransAssetFor45(String[] bids)
      throws BusinessException {
    try {
      Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
      if (ArrayUtils.isEmpty(bids)) {
        return map;
      }

      // 加锁
      VOConcurrentTool tool = new VOConcurrentTool();
      tool.lock(ArriveItemVO.class, bids);

      VOQuery<ArriveItemVO> query =
          new VOQuery<ArriveItemVO>(ArriveItemVO.class, new String[] {
            ArriveItemVO.PK_ARRIVEORDER_B, ArriveItemVO.BTRANSASSET
          });
      ArriveItemVO[] itemVOs = query.query(bids);
      if (ArrayUtils.isEmpty(itemVOs)) {
        return map;
      }

      for (ArriveItemVO itemVO : itemVOs) {
        map.put(itemVO.getPk_arriveorder_b(), itemVO.getBtransasset());
      }

      return map;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
