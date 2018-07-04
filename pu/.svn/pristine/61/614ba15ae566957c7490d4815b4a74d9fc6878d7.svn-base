package nc.pubimpl.pu.m20.it.m5801;

import nc.bs.pu.m20.query.QueryFor5801BP;
import nc.pubitf.pu.m20.it.m5801.IQuery20For5801;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 请购单提供给进口合同的查询服务实现类
 * 
 * @since 6.3.1
 * @version 2013-7-3上午10:21:34
 * @author fanly3
 */
public class Query20For5801Impl implements IQuery20For5801 {

  @Override
  public PraybillVO[] queryPrayBills(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return new QueryFor5801BP(queryScheme).queryPrayBills();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
