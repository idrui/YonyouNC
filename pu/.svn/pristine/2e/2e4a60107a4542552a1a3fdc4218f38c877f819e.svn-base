package nc.impl.pu.m4203;

import nc.bs.pu.m4203.SubcontinFIQueryBP;
import nc.itf.pu.m4203.ISubcontinFIQuery;
import nc.vo.pu.m27.entity.SubcontInSettleVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 委托加工入库单财务副本查询操作实现
 * 
 * @since 6.0
 * @version 2011-1-21 下午03:22:04
 * @author zhaoyha
 */
public class SubcontinFIQueryImpl implements ISubcontinFIQuery {

  @Override
  public SubcontInSettleVO[] settleQuery(String where) throws BusinessException {
    try {
      return new SubcontinFIQueryBP().settleQuery(where);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
