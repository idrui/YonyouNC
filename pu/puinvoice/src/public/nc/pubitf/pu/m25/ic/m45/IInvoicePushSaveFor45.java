/**
 * 
 */
package nc.pubitf.pu.m25.ic.m45;

import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购入库单推式保存发票服务
 * </ul>
 * <p>
 * <p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-1-26 下午03:44:17
 */
public interface IInvoicePushSaveFor45 {

  /**
   * @author xiebo
   * @time 2010-1-26 下午04:21:21
   * @param vos 推式保存VO交换完的发票VO数组。
   * @return 保存后的发票VO数组。
   * @throws 处理过程中的异常信息。
   */
  public InvoiceVO[] pushSave(InvoiceVO[] vos) throws BusinessException;
}
