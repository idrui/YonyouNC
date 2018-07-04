/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 上午10:14:24
 */
package nc.vo.pu.m20.pub;

import java.util.ArrayList;

import nc.vo.pu.m20.entity.writeback.OrderWriteBackVO;

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
 * @time 2010-2-4 上午10:14:24
 */
public class PraybillVOUtil {

  private static PraybillVOUtil instance = new PraybillVOUtil();

  private PraybillVOUtil() {
    // 缺省构造方法
  }

  /**
   * 方法功能描述：单实例模式。
   * <p>
   * <b>参数说明</b>
   * 
   * @return 单实例
   *         <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 下午04:24:07
   */
  public static PraybillVOUtil getInstance() {
    return PraybillVOUtil.instance;
  }

  /**
   * 方法功能描述：从订单回写VO中获取请购单bids。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          回写vo
   * @return 请购单bids
   *         <p>
   * @since 6.0
   * @author linsf
   * @time 2010-2-4 上午10:15:22
   */
  public String[] getBidsFromWBVos(OrderWriteBackVO[] vos) {
    ArrayList<String> retVal = new ArrayList<String>();
    for (OrderWriteBackVO vo : vos) {
      retVal.add(vo.getPk_praybill_b());
    }
    return retVal.toArray(new String[retVal.size()]);
  }
}
