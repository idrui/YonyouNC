/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-26 下午07:09:31
 */
package nc.pubitf.pu.m20.pu.m21;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
 * 请购单提供给采购订单的服务
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-26 下午07:09:31
 */
public interface IQuery20For21 {

  /**
   * 方法功能描述：请购单为采购订单提供的转单查询服务
   * <p>
   * <b>参数说明</b>
   * 
   * @param sql
   * @return 请购单数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author linsf
   * @time 2010-1-26 下午07:18:17
   */
  PraybillVO[] queryPrayBills(IQueryScheme queryScheme)
      throws BusinessException;

}
