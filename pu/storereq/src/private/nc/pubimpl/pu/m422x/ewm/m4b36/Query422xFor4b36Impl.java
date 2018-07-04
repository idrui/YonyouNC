package nc.pubimpl.pu.m422x.ewm.m4b36;

import nc.bs.pu.m422x.query.QueryFor4b36BP;
import nc.pubitf.pu.m422x.ewm.m4b36.IQuery422xFor4b36;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class Query422xFor4b36Impl implements IQuery422xFor4b36 {

  @Override
  public StoreReqAppVO[] queryBillBySource(String[] billids)
      throws BusinessException {
    try {
      return new QueryFor4b36BP().queryBillBySource(billids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public StoreReqAppVO[] queryBillBySourceBID(String[] itemids)
      throws BusinessException {
    try {
      return new QueryFor4b36BP().queryBillBySourceBID(itemids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
