package nc.impl.pu.m4203;

import nc.bs.pu.m4203.SubcontinFIQueryBP;
import nc.itf.pu.m4203.ISubcontinFIQuery;
import nc.vo.pu.m27.entity.SubcontInSettleVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ί�мӹ���ⵥ���񸱱���ѯ����ʵ��
 * 
 * @since 6.0
 * @version 2011-1-21 ����03:22:04
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
