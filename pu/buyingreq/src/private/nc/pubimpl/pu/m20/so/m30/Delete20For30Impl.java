/**
 * $文件说明$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-1 上午08:58:02
 */
package nc.pubimpl.pu.m20.so.m30;

import nc.pubimpl.pu.m20.so.m30.action.Delete20For30Action;
import nc.pubitf.pu.m20.so.m30.IDelete20For30;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单为销售订单提供的删除服务实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-1 上午08:58:02
 */
public class Delete20For30Impl implements IDelete20For30 {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m20.so.m30.IDelete20For30#deleteBills(java.lang.String[])
   */
  @Override
  public void deleteBills(String[] sourcebids) throws BusinessException {
    try {
      new Delete20For30Action().deleteBills(sourcebids);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

}
