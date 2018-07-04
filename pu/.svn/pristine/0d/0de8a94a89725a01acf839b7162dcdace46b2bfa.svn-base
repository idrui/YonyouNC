package nc.itf.pu.reference.et;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.et.m5720.pu.m21.IM5720For21;
import nc.pubitf.et.m5720arrange.Rewrite5720ArrangePara;
import nc.pubitf.et.m5720arrange.pu.m20.IRewriteM5720For20;
import nc.pubitf.et.m5720arrange.pu.m21.IRewriteM5720For21;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>出口合同5720提供给采购的服务适配
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.31
 * @author zhangyhh
 * @time 2010-2-3 上午08:44:05
 */
public class M5720PUServices {

  /**
   * 方法功能描述：请购单回写出口合同5720
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras
   *          回写参数
   *          <p>
   * @since 6.31
   * @author zhangyhh
   * @time 2013-8-9 下午09:54:11
   */
  public static void writeback5720For20(Rewrite5720ArrangePara[] paras) {
    if (!SysInitGroupQuery.isETEnabled()) {
      return;
    }
    try {
      IRewriteM5720For20 service =
          NCLocator.getInstance().lookup(IRewriteM5720For20.class);
      service.rewrite5720ArrangeNumFor20(paras);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：采购订单回写出口合同5720
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras
   *          回写参数
   *          <p>
   * @since 6.31
   * @author zhangyhh
   * @time 2013-8-9 下午09:54:11
   */
  public static void writeback5720For21(Rewrite5720ArrangePara[] paras) {
    if (!SysInitGroupQuery.isETEnabled()) {
      return;
    }
    try {
      IRewriteM5720For21 service =
          NCLocator.getInstance().lookup(IRewriteM5720For21.class);
      service.rewrite5720ArrangeNumFor21(paras);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：采购订单回写出口合同5720价格
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras
   *          回写参数
   *          <p>
   * @since 6.31
   * @author zhangyhh
   * @time 2013-8-9 下午09:54:11
   */
  public static void writeback5720For21price(Map<String, UFDouble> pricemap) {
    if (!SysInitGroupQuery.isETEnabled()) {
      return;
    }
    try {
      IM5720For21 service = NCLocator.getInstance().lookup(IM5720For21.class);
      service.rewrite5720POPriceFor21(pricemap);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

}
