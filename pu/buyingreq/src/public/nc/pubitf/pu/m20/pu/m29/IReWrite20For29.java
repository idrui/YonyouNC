/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-26 下午07:45:29
 */
package nc.pubitf.pu.m20.pu.m29;

import nc.vo.pu.m20.entity.writeback.M29WriteBackVO;
import nc.vo.pub.BusinessException;

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
 * @time 2010-1-26 下午07:45:29
 */
public interface IReWrite20For29 {
  /**
   * 方法功能描述：询报价单回写请购单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 下午04:18:03
   */
  void backWriteGenNum(M29WriteBackVO[] vos) throws BusinessException;

}
