/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-29 上午10:21:45
 */
package nc.pubimpl.pu.m20.mm.a2;

import nc.pubimpl.pu.m20.mm.action.PushSave20ForA2Action;
import nc.pubitf.pu.m20.mm.a2.IPushSave20ForA2;
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
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-29 上午10:21:45
 */
public class PushSave20ForA2Impl implements IPushSave20ForA2 {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m20.mm.a2.IPushSave20ForA2#pushSaveBills(nc.vo.pub.AggregatedValueObject[])
   */
  @Override
  public void pushSaveBills(PraybillVO[] praybills) throws BusinessException {
    try {
      new PushSave20ForA2Action().pushSave(praybills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }
}
