package nc.pubitf.pu.m27;

import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票自动结算
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author wangyf
 * @time 2009-6-29 上午11:28:09
 */
public interface IInvoiceAutoMatch {
  /**
   * 方法功能描述：发票自动结算。
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
  public void invoiceAutoMatch(InvoiceVO[] voInvoice) throws BusinessException;

}
