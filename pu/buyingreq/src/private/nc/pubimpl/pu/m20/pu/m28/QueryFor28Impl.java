/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 上午11:16:17
 */
package nc.pubimpl.pu.m20.pu.m28;

import nc.bs.pu.m20.query.QueryFor28BP;
import nc.pubitf.pu.m20.pu.m28.IQuery20For28;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

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
 * @time 2010-2-4 上午11:16:17
 */
public class QueryFor28Impl implements IQuery20For28 {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m20.pu.m28.IQuery20For28#queryPrayBills(java.lang.String)
   */
  @Override
  public PraybillVO[] queryPrayBills(IQueryScheme scheme)
      throws BusinessException {
    try {
      QuerySchemeProcessor psor = new QuerySchemeProcessor(scheme);
      return new QueryFor28BP(psor).queryPrayBills();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
