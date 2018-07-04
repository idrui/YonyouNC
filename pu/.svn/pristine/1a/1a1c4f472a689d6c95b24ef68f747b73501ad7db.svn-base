package nc.itf.pu.reference.ic;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.general.IICGenRewritePara;
import nc.pubitf.ic.m49.m21.IRewrite49For21;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存借入单为采购提供的服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-6-29 下午06:02:07
 */
public class M49PUServices {

  /**
   * 方法功能描述：采购订单回写库存借入单49
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras 回写参数
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-6-29 下午02:54:11
   */
  public static void writeback49For21(IICGenRewritePara[] paras) {
    IRewrite49For21 service =
        NCLocator.getInstance().lookup(IRewrite49For21.class);
    try {
      service.rewrite49TranoutNumFor21(paras);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

}
