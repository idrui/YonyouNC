/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-10 上午10:48:46
 */
package nc.itf.pu.reference.to;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.to.m5x.pu.m20.IRewrite5XFor20;
import nc.pubitf.to.m5x.pu.m21.IRewrite5XFor21;
import nc.pubitf.to.m5x.to.m5x.RewriteArrangedNumPara;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>调拨订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-10 上午10:48:46
 */
public class M5XServices {

  /**
   * 方法功能描述：请购单回写调拨订单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-11 上午09:24:43
   */
  public static void writeback5XFor20(RewriteArrangedNumPara[] paras)
      throws BusinessException {
    if (!SysInitGroupQuery.isTOEnabled()) {
      return;
    }
    IRewrite5XFor20 service =
        NCLocator.getInstance().lookup(IRewrite5XFor20.class);
    service.rewrite5XArrangedNumFor20(paras);
  }

  /**
   * 方法功能描述：采购订单回写调拨订单
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-10 上午10:51:59
   */
  public static void writeback5XFor21(RewriteArrangedNumPara[] paras)
      throws BusinessException {
    if (!SysInitGroupQuery.isTOEnabled()) {
      return;
    }
    IRewrite5XFor21 service =
        NCLocator.getInstance().lookup(IRewrite5XFor21.class);
    service.rewrite5XArrangedNumFor21(paras);
  }

}
