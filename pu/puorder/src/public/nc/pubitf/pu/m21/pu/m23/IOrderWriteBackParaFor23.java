/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-5 上午08:40:26
 */
package nc.pubitf.pu.m21.pu.m23;

import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单回写订单的参数接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-5 上午08:40:26
 */
public interface IOrderWriteBackParaFor23 extends IOrderWriteBackPara {

  /**
   * <p>
   * 到货回写订单时的累计途损数量
   **/
  public UFDouble getDiffWasteNum();

}
