/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-8 下午05:02:51
 */
package nc.itf.pu.m21;

import nc.vo.pu.m21.entity.ApDataVO;
import nc.vo.pu.m21.entity.AvgSaleQueryVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>联查接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-8 下午05:02:51
 */
public interface IOrderLinkBillQuery {

  /**
   * 供应商应付
   * 
   * @param pk_org 采购组织
   * @param pk_supplier 供应商
   * @return 值VO：应付组织，订单应付，业务应付，财务应付，订单付款，付款未核销金额，供应商信用额度
   * @throws BusinessException
   */
  ApDataVO[] getApData(String pk_org, String pk_supplier)
      throws BusinessException;

  /**
   * 方法功能描述：销量查询
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-10-23 上午11:22:30
   */
  AvgSaleQueryVO[] querySaleData(AvgSaleQueryVO[] vos) throws BusinessException;

}
