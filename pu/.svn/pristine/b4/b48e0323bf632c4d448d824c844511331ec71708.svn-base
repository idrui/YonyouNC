package nc.pubimpl.pu.m20.mm.c2;

import nc.pubimpl.pu.m20.mm.action.PushSave20ForC2Action;
import nc.pubitf.pu.m20.mm.c2.IPushSave20ForC2;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 离散生产订单产能分流推式创建请购订单实现类
 * 
 * @since 6.3
 * @version 2012-10-29 下午06:45:22
 * @author fanly3
 */
public class PushSave20ForC2Impl implements IPushSave20ForC2 {

  @Override
  public void pushSaveBills(PraybillVO[] praybills) throws BusinessException {
    try {
      new PushSave20ForC2Action().pushSave(praybills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }
}
