package nc.pubimpl.pu.m422x.invp.inv9;

import nc.bs.pu.m422x.maintain.StoreReqAppSaveBP;
import nc.pubitf.pu.m422x.invp.inv9.IPushSave422xForInv9;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 库存计划 需求平衡推物资需求申请单实现
 * 
 * @since 6.3
 * @author zhangyhh
 * @time 2014-04-29 15:18:00
 */
public class PushSave422xForInv9Impl implements IPushSave422xForInv9 {

  @Override
  public StoreReqAppVO[] pushSaveBills(StoreReqAppVO[] storereqs)
      throws BusinessException {
    try {
      return new StoreReqAppSaveBP().save(storereqs, null, null);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
