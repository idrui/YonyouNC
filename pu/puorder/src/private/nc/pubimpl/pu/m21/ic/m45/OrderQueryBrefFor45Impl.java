/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-5 下午05:12:30
 */
package nc.pubimpl.pu.m21.ic.m45;

import java.util.Map;

import nc.bs.pu.m21.query.OrderQueryBrefBP;
import nc.pubitf.pu.m21.ic.m45.IOrderQueryBrefFor45;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>补货订单参照退库单，查询需要拼接的语句实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-5 下午05:12:30
 */
public class OrderQueryBrefFor45Impl implements IOrderQueryBrefFor45 {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m21.ic.m45.IOrderQueryBrefFor45#getBrefwhenreturnMap(java.lang.String[])
   */
  @Override
  public Map<String, UFBoolean> getBrefwhenreturnMap(String[] ids)
      throws BusinessException {
    try {
      return new OrderQueryBrefBP().getBrefwhenreturnMap(ids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

}
