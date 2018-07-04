package nc.pubimpl.pu.m20.aim.m4a08;

import nc.pubimpl.pu.m20.aim.action.PushSave20For4A08Action;
import nc.pubitf.pu.m20.aim.m4A08.IPushSave20For4A08;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 资产配置申请推式保存请购单接口实现类
 * 
 * @since 6.5
 * @version 2014-2-14 上午10:20:26
 * @author fanly3
 */
public class PushSave20For4A08Impl implements IPushSave20For4A08 {

  @Override
  public void pushSaveBills(PraybillVO[] praybills) throws BusinessException {
    try {
      new PushSave20For4A08Action().pushSave(praybills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

}
