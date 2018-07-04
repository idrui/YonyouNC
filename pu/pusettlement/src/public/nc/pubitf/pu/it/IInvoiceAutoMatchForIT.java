package nc.pubitf.pu.it;

import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;

/**
 * @since 6.31
 * @version 2013-11-25 上午09:45:35
 * @author mengjian
 */
public interface IInvoiceAutoMatchForIT {
  /**
   * 方法功能描述：进口发票自动结算。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @param voInvoice 发票VO
   * @param settleEnv 结算环境
   * @throws BusinessException
   *           <p>
   * @author wangyf
   * @time 2009-6-29 上午11:32:32
   */
  public void invoiceAutoMatch4IT(InvoiceVO[] voInvoice)
      throws BusinessException;

}
