package nc.pubitf.pu.m422x.invp.inv9;

import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;

/**
 * ���ƻ� ����ƽ���������������뵥
 * 
 * @since 6.3
 * @author zhangyhh
 * @time  2014-04-29  15:18:00
 */
public interface IPushSave422xForInv9 {

  StoreReqAppVO[] pushSaveBills(StoreReqAppVO[] storereqs)
      throws BusinessException;

}
