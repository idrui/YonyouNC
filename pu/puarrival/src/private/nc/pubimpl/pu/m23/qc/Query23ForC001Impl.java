package nc.pubimpl.pu.m23.qc;

import java.util.HashSet;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.pu.m23.qc.IQuery23ForC001;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 提供给质检的查询服务实现类
 * 
 * @since 6.3
 * @version 2013-3-8 下午12:21:40
 * @author fanly3
 */
public class Query23ForC001Impl implements IQuery23ForC001 {

  @Override
  public Set<String> queryBidsOfSecondCheck(String[] bids)
      throws BusinessException {
    try {
      VOQuery<ArriveItemVO> query =
          new VOQuery<ArriveItemVO>(ArriveItemVO.class);
      String condition = "and naccumchecknum >= nnum";
      ArriveItemVO[] items = query.query(bids, condition);
      Set<String> bidSet = new HashSet<String>();
      for (ArriveItemVO item : items) {
        bidSet.add(item.getPk_arriveorder_b());
      }
      return bidSet;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

}
