package nc.pubimpl.pu.m20.mm.m55B4;

import nc.pubimpl.pu.m20.mm.action.PushSave20For55B4Action;
import nc.pubitf.pu.m20.mm.m55B4.IPushSave20For55B4;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计划订单推式保存请购单
 * <li>55B4 计划订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-26 下午04:17:37
 */
public class PushSave20For55B4Impl implements IPushSave20For55B4 {

  @Override
  public void pushSaveBills(PraybillVO[] praybills) throws BusinessException {
    try {
      new PushSave20For55B4Action().pushSave(praybills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }
}
