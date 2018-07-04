/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 下午06:34:35
 */
package nc.pubitf.pu.m21.so.m30;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售订单参照协同采购订单查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 下午06:34:35
 */
public interface ICoopOrderQueryFor30 {

  /**
   * 方法功能描述：销售订单参照协同采购订单查询
   * <p>
   * <b>参数说明</b>
   * 
   * @param condition
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-28 下午06:37:07
   */
  OrderVO[] coopOrderQueryFor30(IQueryScheme queryScheme)
      throws BusinessException;
}
