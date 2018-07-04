package nc.itf.pu.m24;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-13 下午07:41:37
 */
public interface IPricestlQuery {

  /**
   * 方法功能描述：查询价格结算单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-7-13 下午07:41:49
   */
  PricestlVO[] queryPrayBills(IQueryScheme queryScheme)
      throws BusinessException;

}
