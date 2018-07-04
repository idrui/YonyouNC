/**
 * 
 */
package nc.pubitf.pu.m25.pu.settle;

import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购结算自动传应付的服务
 * </ul>
 * <p>
 * <p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-1-26 下午03:36:29
 */
public interface IInvoiceSettleSendAP {

  /**
   * @author xiebo
   * @time 2010-1-26 下午04:03:00
   * @param ids : 要传应付的发票表头id数组
   * @return 已正确传应付的发票VO数组。
   * @throws BusinessException
   */
  public InvoiceVO[] autoSendAP(String[] ids) throws BusinessException;
}
