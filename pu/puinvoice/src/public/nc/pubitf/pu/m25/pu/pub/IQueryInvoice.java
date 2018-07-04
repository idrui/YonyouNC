/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-11-3 下午03:48:04
 */
package nc.pubitf.pu.m25.pu.pub;

import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票查询接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-11-3 下午03:48:04
 */
public interface IQueryInvoice {

  /**
   * 方法功能描述：检查交易类型是否被引用
   * <p>
   * <b>参数说明</b>
   * 
   * @param transType
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-11-3 下午03:48:54
   */
  public String[] checkTransTypeReference(String[] transType)
      throws BusinessException;

  InvoiceVO[] queryInvoiceByOrderBid(String[] bids) throws BusinessException;
}
