package nc.pubimpl.pu.m20.sc.m61;

import nc.bs.pu.m20.query.QueryFor61BP;
import nc.pubitf.pu.m20.sc.m61.IQuery20For61;
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
 * @time 2010-2-4 上午11:11:24
 */
public class QueryFor61Impl implements IQuery20For61 {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m20.sc.m61.IQuery20For61#queryPrayBills(java.lang.String)
   */
  @Override
  public PraybillVO[] queryPrayBills(IQueryScheme scheme)
      throws BusinessException {
    try {
      QuerySchemeProcessor psor = new QuerySchemeProcessor(scheme);
      return new QueryFor61BP(psor).queryPrayBills();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
