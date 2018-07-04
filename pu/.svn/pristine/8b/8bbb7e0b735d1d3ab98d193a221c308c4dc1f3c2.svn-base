/**
 * 
 */
package nc.pubitf.pu.m25.pu.settle;

import nc.vo.pu.m25.entity.InvoiceWriteBackVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为采购结算提供的回写服务</li>
 * </ul>
 * <p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-1-26 下午03:32:22
 */
public interface IInvoiceSettleWriteBack {

  /**
   * @author xiebo
   * @time 2010-1-26 下午04:12:52
   * @param wbVos 结算组织的回写VO数组。
   * @return void。
   * @throws 回写过程中的异常信息。
   */
  public void writeBack(InvoiceWriteBackVO[] wbVos) throws BusinessException;
}
