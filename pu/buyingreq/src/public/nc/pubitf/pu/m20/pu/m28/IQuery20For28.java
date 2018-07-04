/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-26 下午07:26:38
 */
package nc.pubitf.pu.m20.pu.m28;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
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
 * @time 2010-1-26 下午07:26:38
 */
public interface IQuery20For28 {

  /**
   * 方法功能描述：请购单为价格审批单提供的转单查询服务
   * <p>
   * <b>参数说明</b>
   * 
   * @param sql
   * @return 转单的请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author linsf
   * @time 2010-1-26 下午07:28:55
   */
  PraybillVO[] queryPrayBills(IQueryScheme scheme) throws BusinessException;

}
