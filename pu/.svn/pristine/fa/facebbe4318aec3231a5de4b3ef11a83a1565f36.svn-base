/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-21 下午04:28:01
 */
package nc.pubimpl.pu.m20.am.m4b36;

import nc.bs.pu.m20.query.QueryFor4b36BP;
import nc.pubitf.pu.m20.am.m4b36.IQuery20For4b36;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

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
 * @author GGR
 * @time 2010-5-21 下午04:28:01
 */
public class Query20For4b36Impl implements IQuery20For4b36 {

  @Override
  public PraybillVO[] queryBillBySource(String[] billid)
      throws BusinessException {
    try {
      return new QueryFor4b36BP().queryBillBySource(billid);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public PraybillVO[] queryBillBySourceBID(String[] itemids)
      throws BusinessException {
    try {
      return new QueryFor4b36BP().queryBillBySourceBID(itemids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
