/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 上午11:13:41
 */
package nc.pubimpl.pu.m20.ct.z2;

import nc.bs.pu.m20.query.QueryForZ2BP;
import nc.pubitf.pu.m20.ct.z2.IQuery20ForZ2;
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
 * @time 2010-2-4 上午11:13:41
 */
public class QueryForZ2Impl implements IQuery20ForZ2 {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m20.ct.z2.IQuery20ForZ2#queryPrayBills(java.lang.String)
   */
  @Override
  public PraybillVO[] queryPrayBills(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      QuerySchemeProcessor psor = new QuerySchemeProcessor(queryScheme);
      return new QueryForZ2BP(psor).queryPrayBills();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
