package nc.itf.pu.reference.ic;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.m4a.IGeneralInServiceForPuFeeSettle;
import nc.pubitf.ic.m4a.IParameter4AForFeeSettle;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>其他入库单提供给采购的服务接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-8 上午10:20:06
 */
public class M4APUServices {

  /**
   * 方法功能描述：更新累计费用结算次数、次数是增量的。如：费用结算是+1，取消结算时-1
   * <p>
   * <b>参数说明</b>
   * 
   * @param paras
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-8 上午10:25:06
   */
  public static void rewriteNaccumfeesettlecount(
      IParameter4AForFeeSettle[] paras) {
    try {
      IGeneralInServiceForPuFeeSettle service =
          NCLocator.getInstance().lookup(IGeneralInServiceForPuFeeSettle.class);
      service.rewriteNaccumfeesettlecount(paras);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }
}
