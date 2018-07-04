package nc.pubimpl.pu.m21.pu.m23;

import nc.bs.pu.m21.writeback.pu.OrderWriteBackFor23BP;
import nc.pubitf.pu.m21.pu.m23.IOrderWriteBackFor23;
import nc.pubitf.pu.m21.pu.m23.IOrderWriteBackParaFor23;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class OrderWriteBackFor23Impl implements IOrderWriteBackFor23 {

  @Override
  public void writeBackFor23(IOrderWriteBackParaFor23[] wbVos,
      UFBoolean isUserConfirm) throws BusinessException {
    try {
      new OrderWriteBackFor23BP(isUserConfirm).writeback(wbVos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

}
