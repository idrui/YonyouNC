/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-14 下午04:10:32
 */
package nc.pubimpl.pu.m21.pu.m4t;

import nc.bs.pu.m21.writeback.pu.OrderWriteBackFor4TBP;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.pubitf.pu.m21.pu.m4t.IOrderWriteBackFor4T;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单回写采购订单实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-14 下午04:10:32
 */
public class OrderWriteBackFor4TImpl implements IOrderWriteBackFor4T {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m21.pu.m4t.IOrderWriteBackFor4T#writeBackFor45(nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara[])
   */
  @Override
  public void writeBackFor4T(IOrderWriteBackPara[] wbVos)
      throws BusinessException {
    try {
      new OrderWriteBackFor4TBP().writeBackFor4T(wbVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
