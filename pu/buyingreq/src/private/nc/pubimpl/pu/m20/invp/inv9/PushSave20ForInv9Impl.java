package nc.pubimpl.pu.m20.invp.inv9;

import nc.bs.pu.m20.maintain.PraybillInsertBP;
import nc.pubitf.pu.m20.invp.inv9.IPushSave20ForInv9;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 推式保存-库存计划需求平衡
 * 
 * @since 6.3
 * @version 2014-04-30 下午9:08:28
 * @author zhangyhh
 */

public class PushSave20ForInv9Impl implements IPushSave20ForInv9 {

  @Override
  public PraybillVO[] pushSaveBills(PraybillVO[] praybills)
      throws BusinessException {
    try {
      return new PraybillInsertBP().insert(praybills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

}
