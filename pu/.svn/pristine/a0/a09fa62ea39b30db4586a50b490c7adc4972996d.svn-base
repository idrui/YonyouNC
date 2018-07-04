/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-3 下午07:31:39
 */
package nc.pubimpl.pu.m21.ic.m45;

import java.util.HashMap;
import java.util.Map;

import nc.bs.pu.m21.query.OrderQueryBInvoiceCloseBP;
import nc.pubitf.pu.m21.ic.m45.IOrderQueryBInvoiceCloseFor45;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

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
 * @time 2010-7-3 下午07:31:39
 */
public class OrderQueryBInvoiceCloseFor45Impl implements
    IOrderQueryBInvoiceCloseFor45 {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m21.ic.m45.IOrderQueryBInvoiceCloseFor45#getBInvoiceCloseMap(java.lang.String[])
   */
  @Override
  public Map<String, UFBoolean> getBInvoiceCloseMap(String[] bids)
      throws BusinessException {
    try {
      return new OrderQueryBInvoiceCloseBP().getBInvoiceCloseMap(bids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return new HashMap<String, UFBoolean>();
  }

}
