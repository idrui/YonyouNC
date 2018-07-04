package nc.pubimpl.pu.m4203;

import nc.bs.pu.m4203.SubcontinFIDupBP;
import nc.bs.pu.m4203.SubcontinFICancelDupBP;
import nc.pubitf.pu.m4203.ISubcontinFIMaintain;
import nc.vo.ic.m47.entity.SubcontInVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 委托加工入库单财务（采购结算）副本维护服务的实现。
 * 
 * @since 6.0
 * @version 2011-1-20 下午12:14:39
 * @author zhaoyha
 */
public class SubcontinFIMaintainImpl implements ISubcontinFIMaintain {

  @Override
  public void cancelDuplicate(String[] hids) throws BusinessException {
    try {
      new SubcontinFICancelDupBP().cancelDuplicate(hids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void duplicate(SubcontInVO[] vos) throws BusinessException {
    try {
      new SubcontinFIDupBP().duplicate(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
