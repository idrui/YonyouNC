/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-8 下午05:08:45
 */
package nc.impl.pu.m21;

import nc.impl.pu.m21.action.ApDataAction;
import nc.impl.pu.m21.action.AvgSaleAction;
import nc.itf.pu.m21.IOrderLinkBillQuery;
import nc.vo.pu.m21.entity.ApDataVO;
import nc.vo.pu.m21.entity.AvgSaleQueryVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>联查接口实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-8 下午05:08:45
 */
public class OrderLinkBillQueryImpl implements IOrderLinkBillQuery {

  @Override
  public ApDataVO[] getApData(String pk_org, String pk_supplier)
      throws BusinessException {
    try {
      return new ApDataAction().getApData(pk_org, pk_supplier);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public AvgSaleQueryVO[] querySaleData(AvgSaleQueryVO[] vos)
      throws BusinessException {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    try {
      return new AvgSaleAction().querySaleData(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
