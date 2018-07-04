package nc.pubimpl.pu.m20.invp.m4f;

import nc.pubimpl.pu.m20.invp.action.PushSave20For4FAction;
import nc.pubitf.pu.m20.invp.m4f.IPushSave20For4F;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 推式保存-库存计划计划订单
 * 
 * @since 6.0
 * @version 2011-12-12 下午05:08:28
 * @author 田锋涛
 */

public class PushSave20For4FImpl implements IPushSave20For4F {

  @Override
  public void pushSaveBills(PraybillVO[] praybills) throws BusinessException {
    try {
      new PushSave20For4FAction().pushSave(praybills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

}
