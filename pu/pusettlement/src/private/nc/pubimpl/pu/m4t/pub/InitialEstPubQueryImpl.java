/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-19 下午05:57:37
 */
package nc.pubimpl.pu.m4t.pub;

import nc.pubitf.pu.m4t.pub.IInitialEstPubQuery;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>公共查询服务实现
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-19 下午05:57:37
 */
public class InitialEstPubQueryImpl implements IInitialEstPubQuery {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m4t.pub.IInitialEstPubQuery#isExistFromOrder(java.lang.String[])
   */
  @Override
  public boolean isExistFromOrder(String[] bid) throws BusinessException {
    return false;
  }

}
