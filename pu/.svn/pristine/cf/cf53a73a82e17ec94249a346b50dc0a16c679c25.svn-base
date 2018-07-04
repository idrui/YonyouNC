/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 下午04:26:07
 */
package nc.pubimpl.pu.m20.so.m30;

import nc.pubimpl.pu.m20.so.m30.action.PushSave20For30Action;
import nc.pubitf.pu.m20.so.m30.IPushSave20For30;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-4 下午04:26:07
 */
public class PushSave20For30Impl implements IPushSave20For30 {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m20.so.m30.IPushSave20For30#pushSaveBills(PraybillVO[])
   */
  @Override
  public void pushSaveBills(PraybillVO[] praybills) throws BusinessException {
    try {
      new PushSave20For30Action().pushSave(praybills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

}
