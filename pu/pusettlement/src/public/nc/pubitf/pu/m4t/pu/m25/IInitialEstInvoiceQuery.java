/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-19 上午10:13:33
 */
package nc.pubitf.pu.m4t.pu.m25;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单生成采购发票的查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-19 上午10:13:33
 */
public interface IInitialEstInvoiceQuery {

  /**
   * 查询可生成采购发票的期初暂估单
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  InitialEstVO[] query(IQueryScheme queryScheme) throws BusinessException;
}
