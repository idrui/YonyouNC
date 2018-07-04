/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-3 下午07:27:30
 */
package nc.pubitf.pu.m21.ic.m45;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>查询表体是否开票关闭
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-3 下午07:27:30
 */
public interface IOrderQueryBInvoiceCloseFor45 {

  /**
   * 方法功能描述：根据订单表体数组查询订单表体的“开票关闭”属性
   * <p>
   * <b>参数说明</b>
   * 
   * @param bids
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-3 下午07:29:10
   */
  public Map<String, UFBoolean> getBInvoiceCloseMap(String[] bids)
      throws BusinessException;
}
