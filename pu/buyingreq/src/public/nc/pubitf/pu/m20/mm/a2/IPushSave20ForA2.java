/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-29 上午10:11:45
 */
package nc.pubitf.pu.m20.mm.a2;

import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b> 推式保存-生产订单
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-29 上午10:11:45
 */
public interface IPushSave20ForA2 {

  /**
   * 方法功能描述：生产订单推式保存请购单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param praybills
   * @throws BusinessException
   *           <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-29 上午10:13:09
   */
  void pushSaveBills(PraybillVO[] praybills) throws BusinessException;

}
