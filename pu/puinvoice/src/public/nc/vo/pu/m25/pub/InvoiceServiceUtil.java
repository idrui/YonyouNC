/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-13 上午10:42:13
 */
package nc.vo.pu.m25.pub;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m25.IDefaultPriceQuery;
import nc.itf.pu.m25.IInvoiceMaintain;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>工具类，用于获取发票相关的服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-13 上午10:42:13
 */
public class InvoiceServiceUtil {

  /** 发票维护服务，增删改查，冻结解冻等 */
  private static IInvoiceMaintain maintainSerivce = null;

  /** 发票询价服务 */
  private static IDefaultPriceQuery priceQuerySerivce = null;

  static {
    InvoiceServiceUtil.maintainSerivce =
        NCLocator.getInstance().lookup(IInvoiceMaintain.class);
    InvoiceServiceUtil.priceQuerySerivce =
        NCLocator.getInstance().lookup(IDefaultPriceQuery.class);
  }

  public static IInvoiceMaintain getMaintainSerivce() {
    return InvoiceServiceUtil.maintainSerivce;
  }

  public static IDefaultPriceQuery getPriceQuerySerivce() {
    return InvoiceServiceUtil.priceQuerySerivce;
  }

}
