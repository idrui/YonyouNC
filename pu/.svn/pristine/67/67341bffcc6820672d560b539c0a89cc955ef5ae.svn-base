/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-22 上午10:51:30
 */
package nc.itf.pu.m25;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <ul>
 * <li>发票查询操作组件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-22 上午10:51:30
 */
public interface IInvoiceQuery {

  /**
   * 根据ids查询货物发票视图。
   * add by liangchen1
   * NC631
   * 
   * @param pids
   * @return
   * @throws BusinessException
   */
  public int getInvoiceTypeByIds(String[] ids) throws BusinessException;

  /**
   * 带查询方案的查询
   * 
   * @param queryScheme 查询方案
   * @return 查询结果集
   * @throws BusinessException
   */
  public InvoiceVO[] queryByQueryScheme(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 查询货物发票附带的费用发票
   * 
   * @param pids
   * @return
   * @throws BusinessException
   */
  public InvoiceVO[] queryFee(String[] pids) throws BusinessException;

  /**
   * 查询货物发票附带的自由状态费用发票。
   * 
   * @param pids
   * @return
   * @throws BusinessException
   */
  public InvoiceVO[] queryFreeFee(String[] pids) throws BusinessException;
}
